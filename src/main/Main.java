package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.jvnet.substance.*;

import java.awt.GridLayout;

public class Main extends JFrame {

	private JPanel contentPane;

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
		setBounds(100, 100, 900, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultLookAndFeelDecorated(true);
		SubstanceLookAndFeel
				.setSkin("org.jvnet.substance.skin.BusinessBlackSteelSkin");

		Busqueda b = new Busqueda(contentPane);
		b.setVisible(true);
		contentPane.add(b);

		SwingUtilities.updateComponentTreeUI(b);

	}
}
