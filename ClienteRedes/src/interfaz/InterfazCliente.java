package interfaz;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.*;

import solucion.Cliente;

@SuppressWarnings("serial")
public class InterfazCliente extends JFrame {
	
	Cliente cliente;
	
	PanelOpciones panelOpciones;
	PanelLista panelLista;
	JLabel imagen;

	public InterfazCliente() {
		cliente = new Cliente();
		
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Cliente");
		setResizable(false);

		imagen = new JLabel();
		add(imagen, BorderLayout.EAST);

		loadImage(null);

		panelOpciones = new PanelOpciones(this);
		add(panelOpciones, BorderLayout.SOUTH);

		panelLista = new PanelLista(this);
		add(panelLista, BorderLayout.CENTER);
		
		pack();
		setVisible(true);
	}

	private void loadImage(String archivo) {
		if (archivo == null) {
			archivo = "default.jpg";
		}
		archivo = "data/" + archivo;
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File(archivo));
			if (myPicture == null) {
                myPicture = ImageIO.read(new File("data/default.jpg"));
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image dimg = myPicture.getScaledInstance(150, 220,
				Image.SCALE_SMOOTH);
		imagen.setIcon(new ImageIcon(dimg));
	}

	public void conexion() {
		try {
		    panelLista.limpiar();
			cliente.crearSocket("localhost", 9000);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public void listar() {
		try {
			String[] lista = cliente.list();
			panelLista.actualizarLista(lista);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public void descargar() {
		try {
			String archivo = panelLista.darArchivoSeleccionado();
			if (archivo == null) {
				System.out.println("no hay archivo seleccionado");
				return;
			}
			cliente.retr(archivo);
			loadImage(archivo);
            cliente = new Cliente();
            cliente.crearSocket("localhost", 9000);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		new InterfazCliente();
	}
}
