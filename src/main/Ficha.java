package main;
import javax.swing.JPanel;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import org.jdesktop.swingx.JXDatePicker;

import tablas.Componente;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.JSpinner;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Ficha extends JPanel {

	private JTextField txt_nombre;
	private String[] tipos;
	private String[] subtipos;
	private JPanel contentPane;
	private JButton img_prod;
	private JTextArea txt_desc;
	private JSpinner spin_stock;
	private JSpinner spin_compra;
	private JSpinner spin_precioP;
	private JXDatePicker picker;
	private String foto;
	private JComboBox combo_tipo;
	private JComboBox combo_subtipo;
	private int id_mod;
	private JTextField txt_id;

	/**
	 * Create the panel.
	 * 
	 * @param contentPane
	 */
	public Ficha(Componente comp, JPanel contentPane) {
		setBounds(100, 100, 900, 540);
		setLayout(null);
		this.contentPane = contentPane;
		
		id_mod = comp.getCod();

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(460, 62, 100, 14);
		add(lblNombre);

		JLabel lblPrecioP = new JLabel("Precio al publico:");
		lblPrecioP.setBounds(460, 102, 100, 14);
		add(lblPrecioP);

		JLabel lblPrecioC = new JLabel("Precio de compra:");
		lblPrecioC.setBounds(460, 142, 100, 14);
		add(lblPrecioC);

		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(460, 182, 100, 14);
		add(lblStock);

		JLabel lblDescripcion = new JLabel("Descripci\u00F3n:");
		lblDescripcion.setBounds(460, 304, 100, 14);
		add(lblDescripcion);

		JLabel lblFecha = new JLabel("Fecha de compra:");
		lblFecha.setBounds(460, 413, 100, 14);
		add(lblFecha);

		JPanel panel_fecha = new JPanel();
		panel_fecha.setBounds(570, 409, 106, 22);
		add(panel_fecha);
		panel_fecha.setLayout(new GridLayout(0, 1, 0, 0));

		picker = new JXDatePicker();
		picker.getEditor().setEnabled(false);
		picker.setDate(comp.getFechaCompra());
		picker.setFormats(new SimpleDateFormat("yyyy-MM-dd"));
		panel_fecha.add(picker);

		tipos = Consultas.buscarTipos(false);
		combo_tipo = new JComboBox(tipos);
		combo_tipo.setEnabled(false);
		combo_tipo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				if (combo_tipo.getSelectedItem() != null) {

					renovarComboSubTipo();

				}

			}
		});
		combo_tipo.setBounds(564, 220, 265, 20);
		add(combo_tipo);

		subtipos = Consultas.buscarSubTipos(combo_tipo.getSelectedItem()
				.toString());
		combo_subtipo = new JComboBox(subtipos);
		combo_subtipo.setEnabled(false);
		combo_subtipo.setBounds(564, 260, 265, 20);
		add(combo_subtipo);

		JPanel panel_text = new JPanel();
		panel_text.setBounds(570, 302, 259, 96);
		add(panel_text);
		panel_text.setLayout(new GridLayout(0, 1, 0, 0));

		txt_desc = new JTextArea();
		txt_desc.setEnabled(false);
		txt_desc.setBounds(140, 354, 259, 96);
		txt_desc.setEditable(false);
		txt_desc.setText(comp.getDescripcion());
		JScrollPane scroll = new JScrollPane(txt_desc);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		// Add Textarea in to middle panel
		panel_text.add(scroll);

		spin_stock = new JSpinner(new SpinnerNumberModel(0, 0, 5000, 1));
		spin_stock.setEnabled(false);
		spin_stock.setBounds(570, 178, 106, 20);
		add(spin_stock);
		spin_stock.setValue(comp.getStock());

		spin_compra = new JSpinner(new SpinnerNumberModel(100.0, 0.0, 10000.0,
				0.1));
		spin_compra.setEnabled(false);
		spin_compra.setBounds(570, 138, 106, 20);
		add(spin_compra);
		spin_compra.setValue(comp.getPrecioC());

		spin_precioP = new JSpinner(new SpinnerNumberModel(100.0, 0.0, 10000.0,
				0.1));
		spin_precioP.setEnabled(false);
		spin_precioP.setBounds(570, 98, 106, 20);
		add(spin_precioP);
		spin_precioP.setValue(comp.getPrecioP());

		txt_nombre = new JTextField();
		txt_nombre.setEnabled(false);
		txt_nombre.setBounds(570, 58, 259, 20);
		add(txt_nombre);
		txt_nombre.setColumns(10);
		txt_nombre.setText(comp.getNombre());

		img_prod = new JButton("");
		img_prod.setBounds(89, 62, 310, 310);
		add(img_prod);

		try {
			foto = comp.getFoto();
			img_prod.setIcon(new ImageIcon(foto));
		} catch (NullPointerException npe) {
			JOptionPane.showMessageDialog(null, "Imagen no encontrada", null,
					JOptionPane.WARNING_MESSAGE);
		}

		img_prod.setBackground(UIManager.getColor("Button.light"));
		img_prod.setBorderPainted(false);
		img_prod.setBorder(null);

		JLabel lbl_foto = new JLabel("Foto:");
		lbl_foto.setBounds(33, 66, 46, 14);
		add(lbl_foto);

		JButton btn_aceptar = new JButton("Aceptar");
		btn_aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				volverBusqueda();
				
			}
		});

		btn_aceptar.setBounds(740, 435, 89, 23);
		add(btn_aceptar);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(460, 223, 46, 14);
		add(lblTipo);
		
		JLabel lbl_sub = new JLabel("Subtipo:");
		lbl_sub.setBounds(460, 263, 46, 14);
		add(lbl_sub);
		
		JLabel lblNewLabel = new JLabel("Ficha:");
		lblNewLabel.setBounds(27, 24, 46, 14);
		add(lblNewLabel);
		
		txt_id = new JTextField();
		txt_id.setEnabled(false);
		txt_id.setBounds(83, 21, 86, 20);
		txt_id.setText(id_mod + "");
		add(txt_id);
		txt_id.setColumns(10);

	}

	public void volverBusqueda() {

		this.contentPane.removeAll();
		Busqueda b = new Busqueda(this.contentPane);
		b.setVisible(true);
		this.contentPane.add(b);

		SwingUtilities.updateComponentTreeUI(b);

	}

	public void renovarComboTipo() {

		tipos = Consultas.buscarTipos(false);
		combo_tipo.removeAllItems();

		for (int i = 0; i < tipos.length; i++) {
			combo_tipo.addItem(tipos[i].toString());
		}

	}

	public void renovarComboSubTipo() {

		subtipos = Consultas.buscarSubTipos(combo_tipo.getSelectedItem()
				.toString());
		combo_subtipo.removeAllItems();

		for (int i = 0; i < subtipos.length; i++) {
			combo_subtipo.addItem(subtipos[i].toString());
		}

	}
	
}
