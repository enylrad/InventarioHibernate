package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import tablas.Componente;
import tablas.SessionFactoryUtil;
import tablas.Tipo;

public class Consultas {
	
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
									  + "WHERE c.subtipo.cod = st.id "
									  + "AND st.tipo.cod = t.cod "
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
	
	public static String[] buscarTipos(){
		
		ArrayList<String> array_tipos = new ArrayList<>();
		
		SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionF.openSession();

		Query cons = sesion.createQuery("FROM Tipo");
		
	
		List<Object> filas = cons.list();	
		Iterator<Object> iter = filas.iterator();
		
		array_tipos.add("Todos");
		
		while(iter.hasNext()){
			
			Tipo t = (Tipo) iter.next();
			
			array_tipos.add(t.getNombre());
		
		}

		sesion.close();

		String[] tipos = new String[array_tipos.size()];
		
		for(int i=0; i<array_tipos.size(); i++){
			
			tipos[i] = array_tipos.get(i);
			
		}
		
		return tipos;
		
	}
	
}
