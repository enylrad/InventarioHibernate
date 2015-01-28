package main;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.sql.Date;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Agregar extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public Agregar() {
		setBounds(100, 100, 1097, 613);
		setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(30, 30, 100, 14);
		add(lblNombre);
		
		JLabel lblPrecioP = new JLabel("Precio al publico:");
		lblPrecioP.setBounds(30, 55, 100, 14);
		add(lblPrecioP);
		
		JLabel lblPrecioC = new JLabel("Precio de compra:");
		lblPrecioC.setBounds(30, 80, 100, 14);
		add(lblPrecioC);
		
		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(30, 105, 100, 14);
		add(lblStock);
		
		JLabel lblDescripcion = new JLabel("Descripci\u00F3n:");
		lblDescripcion.setBounds(30, 130, 100, 14);
		add(lblDescripcion);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(30, 155, 100, 14);
		add(lblFecha);
		
		textField = new JTextField();
		textField.setBounds(123, 152, 107, 20);
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DatePicker date = new DatePicker();
			}
		});
		btnNewButton.setBounds(253, 151, 89, 23);
		add(btnNewButton);
		
		
		
	}
}
