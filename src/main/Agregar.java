package main;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import org.jdesktop.swingx.JXDatePicker;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;

import java.awt.Color;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Agregar extends JPanel {
	private JTextField textField;
	private String[] tipos;
	private String[] subtipos;
	private JPanel contentPane;
	private JButton btnImg;
	
	/**
	 * Create the panel.
	 * @param contentPane 
	 */
	public Agregar(JPanel contentPane) {
		setBounds(100, 100, 900, 540);
		setLayout(null);
		this.contentPane=contentPane;
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(30, 294, 100, 14);
		add(lblNombre);
		
		JLabel lblPrecioP = new JLabel("Precio al publico:");
		lblPrecioP.setBounds(30, 334, 100, 14);
		add(lblPrecioP);
		
		JLabel lblPrecioC = new JLabel("Precio de compra:");
		lblPrecioC.setBounds(30, 374, 100, 14);
		add(lblPrecioC);
		
		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(30, 414, 100, 14);
		add(lblStock);
		
		JLabel lblDescripcion = new JLabel("Descripci\u00F3n:");
		lblDescripcion.setBounds(461, 294, 100, 14);
		add(lblDescripcion);
		
		JLabel lblFecha = new JLabel("Fecha de compra:");
		lblFecha.setBounds(461, 412, 100, 14);
		add(lblFecha);
		
		JPanel panel_fecha = new JPanel();
		panel_fecha.setBounds(571, 408, 106, 22);
		add(panel_fecha);
		panel_fecha.setLayout(new GridLayout(0, 1, 0, 0));
		
		JXDatePicker picker = new JXDatePicker();
	    picker.setDate(Calendar.getInstance().getTime());
	    picker.setFormats(new SimpleDateFormat("dd-MM-yyyy"));
	    panel_fecha.add(picker);
	    
	    tipos = Consultas.buscarTipos(false);
	    JComboBox combo_tipo = new JComboBox(tipos);
	    combo_tipo.setBounds(461, 27, 265, 20);
	    add(combo_tipo);
	    
	    subtipos = Consultas.buscarSubTipos();
	    JComboBox combo_subtipo = new JComboBox(subtipos);
	    combo_subtipo.setBounds(461, 77, 265, 20);
	    add(combo_subtipo);
	    
	    JButton btntipo_nuevo = new JButton("Nuevo");
	    btntipo_nuevo.setBounds(736, 26, 89, 23);
	    add(btntipo_nuevo);
	    
	    JButton btnsubtipo_nuevo = new JButton("Nuevo");
	    btnsubtipo_nuevo.setBounds(736, 76, 89, 23);
	    add(btnsubtipo_nuevo);
	    
	    JPanel panel_text = new JPanel();
	    panel_text.setBounds(571, 292, 259, 96);
	    add(panel_text);
	    panel_text.setLayout(new GridLayout(0, 1, 0, 0));
	    

	    JTextArea descrip = new JTextArea ();
	    descrip.setBounds(140, 354, 259, 96);
	    descrip.setEditable(true);
	    JScrollPane scroll = new JScrollPane (descrip);
	    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

	    //Add Textarea in to middle panel
	    panel_text.add ( scroll );
	    
	    JSpinner spinner_stock = new JSpinner(new SpinnerNumberModel(0, 0, 5000, 1));
	    spinner_stock.setBounds(140, 410, 106, 20);
	    add(spinner_stock);
	    
	    JSpinner spinner_compra = new JSpinner(new SpinnerNumberModel(100.0, 0.0, 10000.0, 0.1));
	    spinner_compra.setBounds(140, 370, 106, 20);
	    add(spinner_compra);
	    
	    JSpinner spinner_precioP = new JSpinner(new SpinnerNumberModel(100.0, 0.0, 10000.0, 0.1));
	    spinner_precioP.setBounds(140, 330, 106, 20);
	    add(spinner_precioP);
	    
	    textField = new JTextField();
	    textField.setBounds(140, 290, 259, 20);
	    add(textField);
	    textField.setColumns(10);
	    
	    btnImg = new JButton("");
	    btnImg.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		JFileChooser filechooser= new JFileChooser();
	    	    filechooser.setDialogTitle("Selecciona la imagen");
	    	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	    	            "JPG & GIF Images", "jpg", "gif");
	    	    filechooser.setFileFilter(filter);
	    	    
	    	    filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	    	    int returnval = filechooser.showOpenDialog(null);
	    	    if(returnval == JFileChooser.APPROVE_OPTION){
	    	    	btnImg.setIcon(new ImageIcon(filechooser.getCurrentDirectory() + "\\" + filechooser.getSelectedFile().getName()));
	    	    }
	    	    
	    	}
	    	
	    });
	    btnImg.setBounds(86, 27, 200, 200);
	    add(btnImg);
	    btnImg.setIcon(new ImageIcon("D:\\Google Drive\\DAM\\Segundo\\Acceso a Datos\\Practicas Hechas\\InventarioHibernate\\anadir.png"));
	    btnImg.setBackground(UIManager.getColor("Button.light"));
	    btnImg.setBorderPainted(false);
	    btnImg.setBorder(null);
	    
	    JLabel lbl_foto = new JLabel("Foto");
	    lbl_foto.setBounds(30, 30, 46, 14);
	    add(lbl_foto);
	    
	    JButton btn_añadir = new JButton("A\u00F1adir");
	    btn_añadir.setBounds(637, 469, 89, 23);
	    add(btn_añadir);
	    
	    JButton btnCancelar = new JButton("Cancelar");
	    btnCancelar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		volverBusqueda();
	    		
	    	}
	    });
	    btnCancelar.setBounds(736, 469, 89, 23);
	    add(btnCancelar);
	
	}
	
	public void volverBusqueda(){
		
		this.contentPane.removeAll();
		Busqueda b = new Busqueda(this.contentPane);
		b.setVisible(true);
		this.contentPane.add(b);
		
		SwingUtilities.updateComponentTreeUI(b);
		
	}
}
