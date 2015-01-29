package main;

import javax.swing.JPanel;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.JSpinner;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Agregar extends JPanel {
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
    private String foto = "D:\\Google Drive\\DAM\\Segundo\\Acceso a Datos\\Practicas Hechas\\InventarioHibernate\\anadir.png";
	private JComboBox combo_tipo;
	private JComboBox combo_subtipo;
    
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
		
		picker = new JXDatePicker();
	    picker.setDate(Calendar.getInstance().getTime());
	    picker.setFormats(new SimpleDateFormat("yyyy-MM-dd"));
	    panel_fecha.add(picker);
	    
	    tipos = Consultas.buscarTipos(false);
	    combo_tipo = new JComboBox(tipos);
	    combo_tipo.setBounds(461, 27, 265, 20);
	    add(combo_tipo);
	    
	    subtipos = Consultas.buscarSubTipos();
	    combo_subtipo = new JComboBox(subtipos);
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
	    

	    txt_desc = new JTextArea ();
	    txt_desc.setBounds(140, 354, 259, 96);
	    txt_desc.setEditable(true);
	    JScrollPane scroll = new JScrollPane (txt_desc);
	    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

	    //Add Textarea in to middle panel
	    panel_text.add ( scroll );
	    
	    spin_stock = new JSpinner(new SpinnerNumberModel(0, 0, 5000, 1));
	    spin_stock.setBounds(140, 410, 106, 20);
	    add(spin_stock);
	    
	    spin_compra = new JSpinner(new SpinnerNumberModel(100.0, 0.0, 10000.0, 0.1));
	    spin_compra.setBounds(140, 370, 106, 20);
	    add(spin_compra);
	    
	    spin_precioP = new JSpinner(new SpinnerNumberModel(100.0, 0.0, 10000.0, 0.1));
	    spin_precioP.setBounds(140, 330, 106, 20);
	    add(spin_precioP);
	    
	    txt_nombre = new JTextField();
	    txt_nombre.setBounds(140, 290, 259, 20);
	    add(txt_nombre);
	    txt_nombre.setColumns(10);
	    
	    img_prod = new JButton("");
	    img_prod.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		JFileChooser filechooser= new JFileChooser();
	    	    filechooser.setDialogTitle("Selecciona la imagen");
	    	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	    	            "JPG & GIF Images", "jpg", "gif");
	    	    filechooser.setFileFilter(filter);
	    	    
	    	    filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	    	    int returnval = filechooser.showOpenDialog(null);
	    	    if(returnval == JFileChooser.APPROVE_OPTION){
	    	    	foto = filechooser.getCurrentDirectory() + "\\" + filechooser.getSelectedFile().getName();
	    	    	img_prod.setIcon(new ImageIcon(foto));
	    	    }
	    	    
	    	}
	    	
	    });
	    img_prod.setBounds(86, 27, 200, 200);
	    add(img_prod);
	    img_prod.setIcon(new ImageIcon(foto));
	    img_prod.setBackground(UIManager.getColor("Button.light"));
	    img_prod.setBorderPainted(false);
	    img_prod.setBorder(null);
	    
	    JLabel lbl_foto = new JLabel("Foto");
	    lbl_foto.setBounds(30, 30, 46, 14);
	    add(lbl_foto);
	    
	    JButton btn_añadir = new JButton("A\u00F1adir");
	    btn_añadir.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		if(comprobacionesVacio()){
	    			
	    			String nombre = txt_nombre.getText();
	    			Double precio_p = (Double) spin_precioP.getValue();
	    			Double precio_c = (Double) spin_compra.getValue();
	    			int stock = (Integer) spin_stock.getValue();
	    			String desc = txt_desc.getText();
	    			int sub_tipo = combo_subtipo.getSelectedIndex();
	    			
	    			

	    			
	    			
	    
				            
				            
	    			
	    			//Consultas.añadirComponente(nombre, precio_p, precio_c, stock, desc, fecha_compra, foto, sub_tipo);
	    			JOptionPane.showMessageDialog(null, "Se ha añadido el componente a la base de datos.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
	    			
	    			volverBusqueda();
	    			
	    		}
	    		
	    	}
	    });
	    btn_añadir.setBounds(637, 469, 89, 23);
	    add(btn_añadir);
	    
	    JButton btnCancelar = new JButton("Cancelar");
	    btnCancelar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		
	    		
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
	
	public boolean comprobacionesVacio(){
		
		ArrayList<String> campos = new ArrayList<>();
		
		if(txt_nombre.getText().equals("")){
			campos.add("nombre");
		}
		
		if(txt_desc.getText().equals("")){
			campos.add("descripción");
		}
		
		if(campos.size() > 0 && campos != null){
			JOptionPane.showMessageDialog(null, "Los siguientes campos no pueden estar vacios: " + campos.toString(), "¡Atención!", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		return true;
		
	}
}
