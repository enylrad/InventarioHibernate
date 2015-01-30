package main;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

import java.awt.GridLayout;

import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.Date;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import tablas.Componente;



public class Busqueda extends JPanel {

	private JPanel busqueda;
	private JPanel resultados;
	private JPanel botones;
	private JPanel busca;
	private JButton btn_agregar;
	private JButton btn_modificar;
	private JButton btn_eliminar;
	private JButton btn_detalles;
	private JTextField text_busqueda;
	private JComboBox combo_tipo;
	private JButton btn_salir;
	private DefaultTableModel modelo;
	private JTable table;
	
	private String[] tipos;
	private JButton btnBuscar;
	private JPanel panel_buscar;
	private JPanel panel_tipo;
	private JPanel contentPane;
	
	/**
	 * Create the panel.
	 * @param contentPane 
	 */
	public Busqueda(JPanel contentPane) {
		setBounds(100, 100, 900, 540);
		setLayout(new BorderLayout(0, 0));
		this.contentPane = contentPane;
		
		tipos = Consultas.buscarTipos(true);
		
		busqueda =new JPanel();
		add(busqueda, BorderLayout.NORTH);
		GridBagLayout gbl_busqueda = new GridBagLayout();
		gbl_busqueda.columnWidths = new int[] {550, 148, 129};
		gbl_busqueda.rowHeights = new int[] {43};
		gbl_busqueda.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_busqueda.rowWeights = new double[]{0.0};
		busqueda.setLayout(gbl_busqueda);
		
		busca = new JPanel();
		busca.setBorder(new TitledBorder(null, "Busqueda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_busca = new GridBagConstraints();
		gbc_busca.anchor = GridBagConstraints.NORTH;
		gbc_busca.fill = GridBagConstraints.HORIZONTAL;
		gbc_busca.insets = new Insets(0, 0, 5, 5);
		gbc_busca.gridx = 0;
		gbc_busca.gridy = 0;
		busqueda.add(busca, gbc_busca);
		busca.setLayout(new GridLayout(0, 1, 0, 0));
		
		text_busqueda = new JTextField();
		busca.add(text_busqueda);
		text_busqueda.setColumns(10);
		
		panel_tipo = new JPanel();
		GridBagConstraints gbc_panel_tipo = new GridBagConstraints();
		gbc_panel_tipo.anchor = GridBagConstraints.NORTH;
		gbc_panel_tipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_tipo.insets = new Insets(0, 0, 5, 5);
		gbc_panel_tipo.gridx = 1;
		gbc_panel_tipo.gridy = 0;
		busqueda.add(panel_tipo, gbc_panel_tipo);
		panel_tipo.setBorder(new TitledBorder(null, "Tipo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_tipo.setLayout(new GridLayout(0, 1, 0, 0));
		
		combo_tipo = new JComboBox(tipos);
		panel_tipo.add(combo_tipo);
		
		panel_buscar = new JPanel();
		GridBagConstraints gbc_panel_buscar = new GridBagConstraints();
		gbc_panel_buscar.insets = new Insets(0, 0, 5, 5);
		gbc_panel_buscar.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_buscar.gridx = 2;
		gbc_panel_buscar.gridy = 0;
		busqueda.add(panel_buscar, gbc_panel_buscar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mostrarTabla();
				
			}
		});
		panel_buscar.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnBuscar.setAlignmentX(0.5f);
		panel_buscar.add(btnBuscar);
		
		resultados = new JPanel();
		resultados.setLayout(new GridLayout(0, 1));
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
		btn_agregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cargarAgregar();
				
			}
		});
		GridBagConstraints gbc_btn_agregar = new GridBagConstraints();
		gbc_btn_agregar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_agregar.insets = new Insets(0, 0, 5, 5);
		gbc_btn_agregar.gridx = 1;
		gbc_btn_agregar.gridy = 1;
		botones.add(btn_agregar, gbc_btn_agregar);
		
		btn_modificar = new JButton("Modificar");
		btn_modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cargarModificar();
				
			}
		});
		btn_modificar.setEnabled(false);
		GridBagConstraints gbc_btn_modificar = new GridBagConstraints();
		gbc_btn_modificar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_modificar.insets = new Insets(0, 0, 5, 5);
		gbc_btn_modificar.gridx = 1;
		gbc_btn_modificar.gridy = 3;
		botones.add(btn_modificar, gbc_btn_modificar);
		
		btn_salir = new JButton("Salir");
		btn_salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		
		btn_detalles = new JButton("Detalles");
		btn_detalles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_detalles.setEnabled(false);
		GridBagConstraints gbc_btn_detalles = new GridBagConstraints();
		gbc_btn_detalles.insets = new Insets(0, 0, 5, 5);
		gbc_btn_detalles.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_detalles.gridx = 1;
		gbc_btn_detalles.gridy = 5;
		botones.add(btn_detalles, gbc_btn_detalles);
		
		btn_eliminar = new JButton("Eliminar");
		btn_eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_eliminar.setEnabled(false);
		GridBagConstraints gbc_btn_eliminar = new GridBagConstraints();
		gbc_btn_eliminar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_eliminar.anchor = GridBagConstraints.SOUTH;
		gbc_btn_eliminar.insets = new Insets(0, 0, 5, 5);
		gbc_btn_eliminar.gridx = 1;
		gbc_btn_eliminar.gridy = 10;
		botones.add(btn_eliminar, gbc_btn_eliminar);
		GridBagConstraints gbc_btn_salir = new GridBagConstraints();
		gbc_btn_salir.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_salir.anchor = GridBagConstraints.SOUTH;
		gbc_btn_salir.insets = new Insets(0, 0, 5, 5);
		gbc_btn_salir.gridx = 1;
		gbc_btn_salir.gridy = 11;
		botones.add(btn_salir, gbc_btn_salir);
		
		mostrarTabla();
	}
	
	public void mostrarTabla() {
		
		modelo = null;
		table = null;
		
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
		modelo.addColumn("Fecha de compra");
		modelo.addColumn("Subtipo");

		Consultas.buscar(modelo, text_busqueda, combo_tipo);

		// Para que no se puedan modificar los campos
		for (int j = 0; j < table.getColumnCount(); j++) {

			Class<?> col_class = table.getColumnClass(j);
			table.setDefaultEditor(col_class, null); // remove editor

		}

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				confBotonesHabilitados();

			}
		});
		
		resultados.removeAll();
		
		resultados.add(new JScrollPane(table), BorderLayout.CENTER);

		SwingUtilities.updateComponentTreeUI(this);

	}
	
	public void confBotonesHabilitados(){
		
		if (table.getSelectedRow() != -1) {

			btn_modificar.setEnabled(true);
			btn_eliminar.setEnabled(true);
			btn_detalles.setEnabled(true);

		} else {

			btn_modificar.setEnabled(false);
			btn_eliminar.setEnabled(false);
			btn_detalles.setEnabled(false);

		}
		
	}
	
	public void cargarAgregar(){
		
		this.contentPane.removeAll();
		Agregar a = new Agregar(this.contentPane);
		a.setVisible(true);
		this.contentPane.add(a);
		
		SwingUtilities.updateComponentTreeUI(a);
	
	}
	
	public void cargarModificar(){
		
		Componente comp = Consultas.buscarComponente((int) table.getValueAt(table.getSelectedRow(), 0));
		
		this.contentPane.removeAll();
		Modificar m = new Modificar(comp, contentPane);
		m.setVisible(true);
		this.contentPane.add(m);
		
		SwingUtilities.updateComponentTreeUI(m);
		
	}

}
