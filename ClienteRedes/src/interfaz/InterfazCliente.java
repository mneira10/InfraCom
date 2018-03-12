package interfaz;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import solucion.Cliente;

@SuppressWarnings("serial")
public class InterfazCliente extends JFrame {
	
	Cliente cliente;
	
	PanelOpciones panelOpciones;
	
	public InterfazCliente() {
		cliente = new Cliente();
		try {
			cliente.crearSocket("", 0);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Cliente");
		setResizable(false);
		
		panelOpciones = new PanelOpciones(this);
		add(panelOpciones, BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
	}
	
	public void conectar() {
		try {
			cliente.conectar();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public void listar() {
		try {
			System.out.println(cliente.list());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public void descargar() {
		try {
			cliente.retr("");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		new InterfazCliente();
	}
}
