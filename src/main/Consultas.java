package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import tablas.Componente;
import tablas.SessionFactoryUtil;
import tablas.Subtipo;
import tablas.Tipo;

public class Consultas {
	
	/**
	 * Metodo para filtrar datos en el JTable
	 * @param modelo
	 * @param busqueda
	 * @param tipo
	 */
	public static void buscar(DefaultTableModel modelo, JTextField busqueda, JComboBox<String> tipo){
		
		SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionF.openSession();
		
		String texto_busqueda = "";
		String texto_tipo = "";
		
		if(busqueda.getText().equals("")){	
			texto_busqueda="%";	
		}else{
			texto_busqueda = busqueda.getText();
		}
		
		if(tipo.getSelectedItem().toString().equals("Todos")){	
			texto_tipo="%";	
		}else{
			texto_tipo=tipo.getSelectedItem().toString();
		}
		
		Query cons = sesion.createQuery("FROM Componente AS c, Subtipo AS st, Tipo AS t "
									  + "WHERE c.subtipo = st.cod "
									  + "AND st.tipo = t.cod "
									  + "AND t.nombre LIKE '%" + texto_tipo + "%' "
									  + "AND c.nombre LIKE '%" + texto_busqueda + "%'");
		
		List<Object> filas = cons.list();
		Iterator<Object> iter = filas.iterator();
		
		while(iter.hasNext()){

			Object[] tablas = (Object[]) iter.next();
			Componente comp = (Componente) tablas[0];
			if(comp.getEstado()){
				Object[] fila = new Object[7];
				fila[0] = comp.getCod();
				fila[1] = comp.getNombre();
				fila[2] = comp.getPrecioP();
				fila[3] = comp.getPrecioC();
				fila[4] = comp.getStock();
				fila[5] = comp.getFechaCompra();
				fila[6] = comp.getSubtipo().getNombre();
				modelo.addRow(fila);
			}
			
		}

		sesion.close();
		sesionF.close();
		
	}
	
	/**
	 * Metodo para buscar los tipos en la base de datos
	 * @param todos Si es true añadirá "Todos" en la lista
	 * @return
	 */
	public static String[] buscarTipos(Boolean todos){
		
		ArrayList<String> array_tipos = new ArrayList<>();
		
		SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionF.openSession();

		Query cons = sesion.createQuery("FROM Tipo");
		
	
		List<Object> filas = cons.list();	
		Iterator<Object> iter = filas.iterator();
		
		if(todos){
			array_tipos.add("Todos");
		}
		
		while(iter.hasNext()){
			
			Tipo t = (Tipo) iter.next();
			
			array_tipos.add(t.getNombre());
		
		}

		sesion.close();
		sesionF.close();

		String[] tipos = new String[array_tipos.size()];
		
		for(int i=0; i<array_tipos.size(); i++){
			
			tipos[i] = array_tipos.get(i);
			
		}
		
		return tipos;
		
	}
	
	/**
	 * Metodo para buscar los tipos en la base de datos
	 * @return
	 */
	public static String[] buscarSubTipos(String tipo){
		
		ArrayList<String> array_tipos = new ArrayList<>();
		
		SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionF.openSession();

		Query cons = sesion.createQuery("FROM Subtipo "
										+ "WHERE tipo.nombre LIKE '" + tipo + "'");
		
	
		List<Object> filas = cons.list();	
		Iterator<Object> iter = filas.iterator();
		
		while(iter.hasNext()){
			
			Subtipo st = (Subtipo) iter.next();
			
			array_tipos.add(st.getNombre());
		
		}

		sesion.close();
		sesionF.close();

		String[] subtipos = new String[array_tipos.size()];
		
		for(int i=0; i<array_tipos.size(); i++){
			
			subtipos[i] = array_tipos.get(i);
			
		}
		
		return subtipos;
		
	}
	
	//Esto falla cuando se añade un tipo y subtipo y a la vez se añade componente REVISAR MAS ADELANTE
	/**
	 * Metodo para añadir un componente a la base de datos
	 * @throws ParseException 
	 */
	public static void añadirComponente(String nombre, Double precio_p, Double precio_c, int stock, String desc, String fecha_compra, String foto, String sub_tipo) throws ParseException{
		
		SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionF.openSession(); 
		Transaction trans = sesion.beginTransaction();
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		
		Componente c = new Componente();
		c.setNombre(nombre);
		c.setPrecioP(precio_p);
		c.setPrecioC(precio_c);
		c.setStock(stock);
		c.setEstado(true);
		c.setDescripcion(desc);
		c.setFechaCompra((Date) formato.parse(fecha_compra));
		c.setFoto(foto);
		System.out.println(sub_tipo);
		Subtipo subtipo = buscarSubtipo(sub_tipo);
		c.setSubtipo(subtipo);
		
		sesion.save(c);
		trans.commit();		
		sesion.close();
		sesionF.close();
		
	}
	
	/**
	 * Busca un subtipo por el nombre
	 * @param nombre
	 * @return
	 */
	
	public static Subtipo buscarSubtipo(String nombre){
		
		SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionF.openSession();
		
		Subtipo subtipo = new Subtipo();
		
		Query cons = sesion.createQuery("FROM Subtipo "
										+ "WHERE nombre LIKE '" + nombre + "'");
		System.out.println(cons);
		List<Subtipo> filas = cons.list();	
		Iterator<Subtipo> iter = filas.iterator();
		while(iter.hasNext()){
			System.out.println("hola");
			subtipo = (Subtipo) iter.next();
			
		}

		sesion.close();
		sesionF.close();
		return subtipo;
		
	}
	
	/**
	 * Añade un tipo a la BBDD
	 * @param tipo
	 */
	public static void añadirTipo(String tipo){
		
		if(!comprobarDuplicadosNombreT(tipo)){
			
			SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
			Session sesion = sesionF.openSession(); 
			Transaction trans = sesion.beginTransaction();
	
			Tipo t = new Tipo();
			t.setNombre(tipo);
		
			sesion.save(t);
			trans.commit();		
			sesion.close();
			sesionF.close();
			
		}else{
			
			JOptionPane.showMessageDialog(null, "El nombre ya esta en uso.", null, JOptionPane.WARNING_MESSAGE);
			
		}
		
	}
	
	/**
	 * Añade un Subtipo a la BBDD
	 * @param subtipo
	 * @param tipo
	 */
	public static void añadirSubtipo(String subtipo, String tipo){
		
		if(!comprobarDuplicadosNombreST(subtipo)){
			
			SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
			Session sesion = sesionF.openSession(); 
			Transaction trans = sesion.beginTransaction();
	
			Subtipo st = new Subtipo();
			st.setNombre(subtipo);
			
			Tipo t = Consultas.buscarTipoPorNombre(tipo);
			
			st.setTipo(t);
		
			sesion.save(st);
			trans.commit();		
			sesion.close();
			sesionF.close();
			
		}else{
			
			JOptionPane.showMessageDialog(null, "El nombre ya esta en uso.", null, JOptionPane.WARNING_MESSAGE);
			
		}
		
	}
	
	public static Tipo buscarTipoPorNombre(String nombre){
		
		SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionF.openSession();

		Query cons = sesion.createQuery("FROM Tipo WHERE nombre LIKE '" + nombre + "'");
		
		Tipo t = new Tipo();
	
		List<Object> filas = cons.list();	
		Iterator<Object> iter = filas.iterator();
		
		while(iter.hasNext()){
			
			t = (Tipo) iter.next();
		
		}

		sesion.close();
		sesionF.close();
		
		return t;
	}
	
	public static boolean comprobarDuplicadosNombreT(String nombre){
		
		SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionF.openSession();

		Query cons = sesion.createQuery("FROM Tipo WHERE nombre LIKE '" + nombre + "'");
		
		Tipo t = new Tipo();
	
		List<Object> filas = cons.list();	
		Iterator<Object> iter = filas.iterator();
		
		while(iter.hasNext()){
			
			t = (Tipo) iter.next();
			
			sesion.close();
			return true;
		
		}

		sesion.close();
		sesionF.close();
		return false;
	}
	
	public static boolean comprobarDuplicadosNombreST(String nombre){
		
		SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionF.openSession();

		Query cons = sesion.createQuery("FROM Subtipo WHERE nombre LIKE '" + nombre + "'");
		
		Subtipo st = new Subtipo();
	
		List<Object> filas = cons.list();	
		Iterator<Object> iter = filas.iterator();
		
		while(iter.hasNext()){
			
			st = (Subtipo) iter.next();
			
			sesion.close();
			return true;
		
		}

		sesion.close();
		sesionF.close();
		return false;
	}
	
	public static Componente buscarComponente(int id){
		
		SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionF.openSession();
		
		Componente c = new Componente();
		
		Query cons = sesion.createQuery("FROM Componente "
										+ "WHERE cod = " + id);
		
		List<Componente> filas = cons.list();	
		Iterator<Componente> iter = filas.iterator();
		
		while(iter.hasNext()){
			
			c = (Componente) iter.next();
			
		}
		
		sesion.close();
		sesionF.close();
		return c;
		
	}
	
}
