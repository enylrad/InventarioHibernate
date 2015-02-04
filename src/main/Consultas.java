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
	 * 
	 * @param modelo
	 * @param busqueda
	 * @param tipo
	 */
	public static void buscar(DefaultTableModel modelo, JTextField busqueda, JComboBox<String> tipo, boolean numero) {

		SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionF.openSession();
		
		Transaction trans = sesion.beginTransaction();
		trans.commit();

		String texto_busqueda = "";
		String texto_tipo = "";

		if (busqueda.getText().equals("")) {

			texto_busqueda = "%";

		} else {

			texto_busqueda = busqueda.getText();

		}

		if (tipo.getSelectedItem().toString().equals("Todos")) {

			texto_tipo = "%";

		} else {

			texto_tipo = tipo.getSelectedItem().toString();

		}

		Query cons = null;
		
		if(numero){
			cons = sesion.createQuery("FROM Componente AS c, Subtipo AS st, Tipo AS t "
											+ "WHERE c.subtipo = st.cod " + "AND st.tipo = t.cod "
											+ "AND t.nombre LIKE '%" + texto_tipo + "%' "
											+ "AND c.cod = " + texto_busqueda);
		
		}else{
			cons = sesion.createQuery("FROM Componente AS c, Subtipo AS st, Tipo AS t "
											+ "WHERE c.subtipo = st.cod " + "AND st.tipo = t.cod "
											+ "AND t.nombre LIKE '%" + texto_tipo + "%' "
											+ "AND c.nombre LIKE '%" + texto_busqueda + "%'");
		}

		List<Object> filas = cons.list();
		Iterator<Object> iter = filas.iterator();

		while (iter.hasNext()) {

			Object[] tablas = (Object[]) iter.next();
			Componente comp = (Componente) tablas[0];

			if (comp.getEstado()) {
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

	}

	/**
	 * Metodo para buscar los tipos en la base de datos
	 * 
	 * @param todos Si es true anyadirá "Todos" en la lista
	 * @return
	 */
	public static String[] buscarTipos(Boolean todos) {

		ArrayList<String> array_tipos = new ArrayList<>();

		SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionF.openSession();

		Transaction trans = sesion.beginTransaction();
		trans.commit();

		Query cons = sesion.createQuery("FROM Tipo");

		List<Object> filas = cons.list();
		Iterator<Object> iter = filas.iterator();

		if (todos) {

			array_tipos.add("Todos");

		}

		while (iter.hasNext()) {

			Tipo t = (Tipo) iter.next();

			array_tipos.add(t.getNombre());

		}

		sesion.close();

		String[] tipos = new String[array_tipos.size()];

		for (int i = 0; i < array_tipos.size(); i++) {

			tipos[i] = array_tipos.get(i);

		}

		return tipos;

	}

	/**
	 * Buscar los tipos en la base de datos
	 * 
	 * @return
	 */
	public static String[] buscarSubTipos(String tipo) {

		ArrayList<String> array_tipos = new ArrayList<>();

		SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionF.openSession();

		Transaction trans = sesion.beginTransaction();
		trans.commit();

		Query cons = sesion.createQuery("FROM Subtipo "
				+ "WHERE tipo.nombre LIKE '" + tipo + "'");

		List<Object> filas = cons.list();
		Iterator<Object> iter = filas.iterator();

		while (iter.hasNext()) {

			Subtipo st = (Subtipo) iter.next();

			array_tipos.add(st.getNombre());

		}

		sesion.close();

		String[] subtipos = new String[array_tipos.size()];

		for (int i = 0; i < array_tipos.size(); i++) {

			subtipos[i] = array_tipos.get(i);

		}

		return subtipos;

	}

	/**
	 * Metodo para anyadir un componente a la base de datos
	 * 
	 * @throws ParseException
	 */
	public static void anyadirComponente(String nombre, Double precio_p,
			Double precio_c, int stock, String desc, String fecha_compra,
			String foto, String sub_tipo) throws ParseException {
		
		Subtipo subtipo = buscarSubtipo(sub_tipo);

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

		c.setSubtipo(subtipo);
		
		sesion.update(subtipo);
		sesion.save(c);
		trans.commit();
		sesion.close();

	}
	
	/**
	 * Modifica los valores del componente
	 * @param id
	 * @param nombre
	 * @param precio_p
	 * @param precio_c
	 * @param stock
	 * @param desc
	 * @param fecha_compra
	 * @param foto
	 * @param sub_tipo
	 * @throws ParseException
	 */
	public static void modificarComponente(int id, String nombre, Double precio_p,
			Double precio_c, int stock, String desc, String fecha_compra,
			String foto, String sub_tipo) throws ParseException {

		Subtipo subtipo = buscarSubtipo(sub_tipo);
		
		SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionF.openSession();
		Transaction trans = sesion.beginTransaction();

		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

		Componente c = new Componente();
		c = (Componente) sesion.load(Componente.class, id);

		c.setNombre(nombre);
		c.setPrecioP(precio_p);
		c.setPrecioC(precio_c);
		c.setStock(stock);
		c.setEstado(true);
		c.setDescripcion(desc);
		c.setFechaCompra((Date) formato.parse(fecha_compra));
		c.setFoto(foto);

		
		
		c.setSubtipo(subtipo);

		sesion.update(c);
		trans.commit();
		sesion.close();

	}
	

	/**
	 * Busca un subtipo por el nombre
	 * 
	 * @param nombre
	 * @return
	 */

	public static Subtipo buscarSubtipo(String nombre) {

		SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionF.openSession();

		Transaction trans = sesion.beginTransaction();
		trans.commit();

		Subtipo subtipo = new Subtipo();

		Query cons = sesion.createQuery("FROM Subtipo WHERE nombre LIKE '" + nombre +"'");

		List<Subtipo> filas = cons.list();
		Iterator<Subtipo> iter = filas.iterator();
		
		while (iter.hasNext()) {
			
			subtipo = (Subtipo) iter.next();
			
		}

		sesion.close();
		return subtipo;

	}

	/**
	 * Anyade un tipo a la BBDD
	 * 
	 * @param tipo
	 */
	public static void anyadirTipo(String tipo) {

		if (!comprobarDuplicadosNombreT(tipo)) {

			SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
			Session sesion = sesionF.openSession();
			Transaction trans = sesion.beginTransaction();

			Tipo t = new Tipo();

			t.setNombre(tipo);

			sesion.save(t);
			trans.commit();
			sesion.close();

		} else {

			JOptionPane.showMessageDialog(null, "El nombre ya esta en uso.",
					null, JOptionPane.WARNING_MESSAGE);

		}

	}

	/**
	 * Anyade un Subtipo a la BBDD
	 * 
	 * @param subtipo
	 * @param tipo
	 */
	public static void anyadirSubtipo(String subtipo, String tipo) {

		if (!comprobarDuplicadosNombreST(subtipo)) {

			SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
			Session sesion = sesionF.openSession();
			Transaction trans = sesion.beginTransaction();

			Subtipo st = new Subtipo();
			st.setNombre(subtipo);

			Tipo t = Consultas.buscarTipoPorNombre(tipo);

			st.setTipo(t);

			sesion.update(t);
			sesion.save(st);
			trans.commit();
			sesion.close();

		} else {

			JOptionPane.showMessageDialog(null, "El nombre ya esta en uso.",
					null, JOptionPane.WARNING_MESSAGE);

		}

	}

	/**
	 * Buscar un tipo por nombre
	 * 
	 * @param nombre
	 * @return
	 */
	public static Tipo buscarTipoPorNombre(String nombre) {

		SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionF.openSession();

		Transaction trans = sesion.beginTransaction();
		trans.commit();

		Query cons = sesion.createQuery("FROM Tipo WHERE nombre LIKE '"
				+ nombre + "'");

		Tipo t = new Tipo();

		List<Object> filas = cons.list();
		Iterator<Object> iter = filas.iterator();

		while (iter.hasNext()) {

			t = (Tipo) iter.next();

		}

		sesion.close();

		return t;
	}
	
	/**
	 * Buscar subtipo por el nombre
	 * @param nombre
	 * @return
	 */
	public static Subtipo buscarSubtipoPorNombre(String nombre) {

		SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionF.openSession();

		Transaction trans = sesion.beginTransaction();
		trans.commit();

		Query cons = sesion.createQuery("FROM Subtipo WHERE nombre LIKE '"
				+ nombre + "'");

		Subtipo st = new Subtipo();
		
		List<Object> filas = cons.list();
		Iterator<Object> iter = filas.iterator();

		while (iter.hasNext()) {

			st = (Subtipo) iter.next();

		}

		sesion.close();

		return st;
	}

	/**
	 * Comprueba nombres duplicados en la tabla tipos
	 * 
	 * @param nombre
	 * @return
	 */
	public static boolean comprobarDuplicadosNombreT(String nombre) {

		SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionF.openSession();

		Transaction trans = sesion.beginTransaction();
		trans.commit();

		Query cons = sesion.createQuery("FROM Tipo WHERE nombre LIKE '"
				+ nombre + "'");

		Tipo t = new Tipo();

		List<Object> filas = cons.list();
		Iterator<Object> iter = filas.iterator();

		while (iter.hasNext()) {

			t = (Tipo) iter.next();

			sesion.close();
			sesionF.close();
			
			return true;

		}

		sesion.close();

		return false;
	}

	/**
	 * Comprueba nombres duplicados en la tabla subtipos
	 * 
	 * @param nombre
	 * @return
	 */
	public static boolean comprobarDuplicadosNombreST(String nombre) {

		SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionF.openSession();

		Transaction trans = sesion.beginTransaction();
		trans.commit();

		Query cons = sesion.createQuery("FROM Subtipo WHERE nombre LIKE '"
				+ nombre + "'");

		Subtipo st = new Subtipo();

		List<Object> filas = cons.list();
		Iterator<Object> iter = filas.iterator();

		while (iter.hasNext()) {

			st = (Subtipo) iter.next();

			sesion.close();
			sesionF.close();
			
			return true;

		}

		sesion.close();

		return false;
	}

	/**
	 * Busca Componente por id
	 * 
	 * @param id
	 * @return
	 */
	public static Componente buscarComponente(int id) {

		SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionF.openSession();

		Transaction trans = sesion.beginTransaction();
		trans.commit();

		Componente c = new Componente();

		Query cons = sesion.createQuery("FROM Componente " + "WHERE cod = "
				+ id);

		List<Componente> filas = cons.list();
		Iterator<Componente> iter = filas.iterator();

		while (iter.hasNext()) {

			c = (Componente) iter.next();

		}

		sesion.close();

		return c;

	}
	
	/**
	 * Modifica el nombre del tipo
	 * @param tipoact
	 * @param tipomod
	 */
	public static void modificarTipo(String tipoact, String tipomod){
		if (!comprobarDuplicadosNombreT(tipomod)) {
			
			SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
			Session sesion = sesionF.openSession();
			Transaction trans = sesion.beginTransaction();

			Tipo t = buscarTipoPorNombre(tipoact);
			
			t = (Tipo) sesion.load(Tipo.class, t.getCod());
			
			t.setNombre(tipomod);
			
			sesion.update(t);
			
			trans.commit();
			sesion.close();
			
			JOptionPane.showMessageDialog(null, "Se ha modificado el nombre del tipo", null, JOptionPane.INFORMATION_MESSAGE);

		} else {

			JOptionPane.showMessageDialog(null, "El nombre ya esta en uso.",
					null, JOptionPane.WARNING_MESSAGE);

		}
		
	}

	/**
	 * Modifica el nombre del subtipo
	 * @param subtipoact
	 * @param subtipomod
	 */
	public static void modificarSubtipo(String subtipoact, String subtipomod) {

		if (!comprobarDuplicadosNombreT(subtipomod)) {

			SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
			Session sesion = sesionF.openSession();
			Transaction trans = sesion.beginTransaction();

			Subtipo st = buscarSubtipoPorNombre(subtipoact);

			st = (Subtipo) sesion.load(Subtipo.class, st.getCod());

			st.setNombre(subtipomod);

			sesion.update(st);
			trans.commit();
			sesion.close();

			JOptionPane.showMessageDialog(null,
					"Se ha modificado el nombre del subtipo", null,
					JOptionPane.INFORMATION_MESSAGE);

		} else {

			JOptionPane.showMessageDialog(null, "El nombre ya esta en uso.",
					null, JOptionPane.WARNING_MESSAGE);

		}

	}
	
	/**
	 * Elimina un componente
	 * @param comp
	 */
	public static void eliminarComponente(Componente comp){
		
		SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionF.openSession();
		Transaction trans = sesion.beginTransaction();
		
		comp = (Componente) sesion.load(Componente.class, comp.getCod());
		
		
		sesion.delete(comp);
		
		trans.commit();
		sesion.close();
		
		JOptionPane.showMessageDialog(null, "Se ha eliminado el componente", null, JOptionPane.INFORMATION_MESSAGE);

		
	}

}
