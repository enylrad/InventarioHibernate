package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.jvnet.substance.*;

import com.mysql.jdbc.Connection;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.sql.SQLException;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setTitle("Gestor de inventario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1097, 613);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel busqueda = new JPanel();
		contentPane.add(busqueda, BorderLayout.NORTH);
		busqueda.setLayout(new BorderLayout(0, 0));
		
		JPanel texto = new JPanel();
		texto.setBorder(new TitledBorder(null, "Busqueda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		busqueda.add(texto, BorderLayout.CENTER);
		texto.setLayout(new GridLayout(0, 1, 0, 0));
		
		textField = new JTextField();
		texto.add(textField);
		textField.setColumns(10);
		
		JPanel tipo = new JPanel();
		tipo.setBorder(new TitledBorder(null, "Tipo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		busqueda.add(tipo, BorderLayout.EAST);
		tipo.setLayout(new GridLayout(0, 1, 0, 0));
		
		JComboBox comboBox = new JComboBox();
		tipo.add(comboBox);
		
		JPanel tabla = new JPanel();
		contentPane.add(tabla, BorderLayout.CENTER);
		
		//mostrarTabla(null, tabla);
		
		JPanel acciones = new JPanel();
		contentPane.add(acciones, BorderLayout.EAST);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultLookAndFeelDecorated(true);
		SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.ModerateSkin");
		
	}
	
	public void mostrarTabla(Connection conexion, JPanel tabla) throws SQLException {

		DefaultTableModel modelo = new DefaultTableModel();
		JTable table = new JTable();

		table.setEnabled(true);
		table.setBorder(null);
		table.setModel(modelo);
		modelo.fireTableDataChanged();

		modelo.addColumn("ID");
		modelo.addColumn("Nombre");
		modelo.addColumn("Precio publico");
		modelo.addColumn("Precio coste");
		modelo.addColumn("Stock");
		modelo.addColumn("Descripción");
		modelo.addColumn("Fecha de compra");
		modelo.addColumn("Estado");
		modelo.addColumn("Tipo");

		/*
		TicketUtil tickets_bus = new TicketUtil();
		ArrayList<Ticket> tickets = null;
		ids_table.removeAll(ids_table);

		int pos;

		if (text_buscar.getText().equals("")) {

			con_id = false;

			if (login.isAdmin()) {

				tickets = tickets_bus.buscar(conexion, group.getSelection()
						.getActionCommand(), devolverDepartamento());

			} else {
				tickets = tickets_bus.buscar(conexion, group.getSelection()
						.getActionCommand(), devolverDepartamento(), login
						.getId());

				// Hacer consulta solo para ver sus tickets

			}

		} else {

			try {

				con_id = true;

				if (login.isAdmin()) {

					tickets = tickets_bus.buscar(conexion, Integer
							.parseInt(text_buscar.getText()), group
							.getSelection().getActionCommand(),
							devolverDepartamento());

				} else {
					tickets = tickets_bus.buscar(conexion, Integer
							.parseInt(text_buscar.getText()), group
							.getSelection().getActionCommand(),
							devolverDepartamento(), login.getId());

					// Hacer consulta solo para ver sus tickets

				}

			} catch (NumberFormatException e) {

				text_buscar.setText("");
				JOptionPane.showMessageDialog(null, "Debes introducir números");
				todo_bien = false;

			}
		}
	
		if (todo_bien) {
			pos = 0;
			Ticket ticket = null;

			UsuarioUtil usuutil = null;
			Usuario usuario = null;

			for (int i = 0; i < tickets.size(); i++) {
				pos++;

				Object[] fila = new Object[6];
				ticket = tickets.get(i);
				fila[0] = ticket.getId();
				fila[1] = ticket.getEstado();
				fila[2] = ticket.getFecha_apert();
				fila[3] = ticket.getFecha_cerr();

				usuario = new Usuario();
				usuutil = new UsuarioUtil();

				if (con_id) {
					usuario = usuutil.getUsuarioTicket(conexion,
							ticket.getId(), devolverDepartamento(), group
									.getSelection().getActionCommand(), pos);
				} else {
					usuario = usuutil.getUsuarioTicket(conexion,
							devolverDepartamento(), group.getSelection()
									.getActionCommand(), pos);
				}

				ids_table.add(usuario.getId());
				fila[4] = usuario.getNombre();
				fila[5] = usuario.getDepartament();

				modelo.addRow(fila);

			}
			*/

			// Para que no se puedan modificar los campos
			for (int j = 0; j < table.getColumnCount(); j++) {

				Class<?> col_class = table.getColumnClass(j);
				table.setDefaultEditor(col_class, null); // remove editor

			}

			tabla.removeAll();

			tabla.add(new JScrollPane(table), BorderLayout.CENTER);

			SwingUtilities.updateComponentTreeUI(tabla);

		}

}
