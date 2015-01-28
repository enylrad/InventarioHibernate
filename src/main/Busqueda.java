package main;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

import java.awt.GridLayout;

import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class Busqueda extends JPanel {

	private JPanel busqueda;
	private JPanel resultados;
	private JPanel botones;
	private JPanel busca;
	private JPanel tipo;
	private JButton btn_agregar;
	private JButton btn_actualizar;
	private JButton btn_eliminar;
	private JButton btn_detalles;
	private JTextField textField;
	private JComboBox comboBox;
	private JButton btn_salir;
	private DefaultTableModel modelo;
	private JTable table;
	
	/**
	 * Create the panel.
	 */
	public Busqueda() {
		setBounds(100, 100, 1097, 613);
setLayout(new BorderLayout(0, 0));
		
		busqueda = new JPanel();
		add(busqueda, BorderLayout.NORTH);
		busqueda.setLayout(new BorderLayout(0, 0));
		
		busca = new JPanel();
		busca.setBorder(new TitledBorder(null, "Busqueda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		busqueda.add(busca, BorderLayout.CENTER);
		busca.setLayout(new GridLayout(0, 1, 0, 0));
		
		textField = new JTextField();
		busca.add(textField);
		textField.setColumns(10);
		
		tipo = new JPanel();
		tipo.setBorder(new TitledBorder(null, "Tipo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		busqueda.add(tipo, BorderLayout.EAST);
		tipo.setLayout(new GridLayout(0, 1, 0, 0));
		
		comboBox = new JComboBox();
		tipo.add(comboBox);
		
		resultados = new JPanel();
		add(resultados, BorderLayout.CENTER);
		
		botones = new JPanel();
		add(botones, BorderLayout.EAST);
		GridBagLayout gbl_botones = new GridBagLayout();
		gbl_botones.columnWidths = new int[] {30, 0, 30, 0};
		gbl_botones.rowHeights = new int[] {30, 0, 30, 0, 30, 0, 30, 0, 30, 30, 30, 100, 30};
		gbl_botones.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_botones.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		botones.setLayout(gbl_botones);
		
		btn_agregar = new JButton("A\u00F1adir");
		GridBagConstraints gbc_btn_agregar = new GridBagConstraints();
		gbc_btn_agregar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_agregar.insets = new Insets(0, 0, 5, 5);
		gbc_btn_agregar.gridx = 1;
		gbc_btn_agregar.gridy = 1;
		botones.add(btn_agregar, gbc_btn_agregar);
		
		btn_actualizar = new JButton("Actualizar");
		btn_actualizar.setEnabled(false);
		GridBagConstraints gbc_btn_actualizar = new GridBagConstraints();
		gbc_btn_actualizar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_actualizar.insets = new Insets(0, 0, 5, 5);
		gbc_btn_actualizar.gridx = 1;
		gbc_btn_actualizar.gridy = 3;
		botones.add(btn_actualizar, gbc_btn_actualizar);
		
		btn_eliminar = new JButton("Eliminar");
		btn_eliminar.setEnabled(false);
		GridBagConstraints gbc_btn_eliminar = new GridBagConstraints();
		gbc_btn_eliminar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_eliminar.anchor = GridBagConstraints.SOUTH;
		gbc_btn_eliminar.insets = new Insets(0, 0, 5, 5);
		gbc_btn_eliminar.gridx = 1;
		gbc_btn_eliminar.gridy = 5;
		botones.add(btn_eliminar, gbc_btn_eliminar);
		
		btn_detalles = new JButton("Detalles");
		btn_detalles.setEnabled(false);
		GridBagConstraints gbc_btn_detalles = new GridBagConstraints();
		gbc_btn_detalles.insets = new Insets(0, 0, 5, 5);
		gbc_btn_detalles.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_detalles.gridx = 1;
		gbc_btn_detalles.gridy = 7;
		botones.add(btn_detalles, gbc_btn_detalles);
		
		btn_salir = new JButton("Salir");
		btn_salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		GridBagConstraints gbc_btn_salir = new GridBagConstraints();
		gbc_btn_salir.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_salir.anchor = GridBagConstraints.SOUTH;
		gbc_btn_salir.insets = new Insets(0, 0, 5, 5);
		gbc_btn_salir.gridx = 1;
		gbc_btn_salir.gridy = 11;
		botones.add(btn_salir, gbc_btn_salir);
	}
	
	public void mostrarTabla() {

		modelo = new DefaultTableModel();
		table = new JTable();

		table.setEnabled(true);
		table.setBorder(null);
		table.setModel(modelo);
		modelo.fireTableDataChanged();

		modelo.addColumn("ID");
		modelo.addColumn("Nombre");
		modelo.addColumn("Precio al publico");
		modelo.addColumn("Precio de compra");
		modelo.addColumn("Stock");
		modelo.addColumn("Departamento");
/*
		TicketUtil tickets_bus = new TicketUtil();
		ArrayList<Ticket> tickets = null;
		ids_table.removeAll(ids_table);

	


		for (int i = 0; i < tickets.size(); i++) {

			Object[] fila = new Object[6];
			ticket = tickets.get(i);
			fila[0] = ticket.getId();
			fila[1] = ticket.getEstado();
			fila[2] = ticket.getFecha_apert();
			fila[3] = ticket.getFecha_cerr();
			fila[4] = usuario.getNombre();
			fila[5] = usuario.getDepartament();

			modelo.addRow(fila);

		}

		// Para que no se puedan modificar los campos
		for (int j = 0; j < table.getColumnCount(); j++) {

			Class<?> col_class = table.getColumnClass(j);
			table.setDefaultEditor(col_class, null); // remove editor

		}

		

		panel_central.removeAll();

		panel_central.add(new JScrollPane(table), BorderLayout.CENTER);

		SwingUtilities.updateComponentTreeUI(panel_central);
*/

	}

}
