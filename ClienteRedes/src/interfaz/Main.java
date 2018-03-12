package interfaz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import solucion.Cliente;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Cliente c = new Cliente();
		c.crearSocket("localhost", 9000);
		String str;
		boolean terminado = false;
		while(!terminado && (str = in.readLine()) != null ) {
			switch (str) {
				case "CONNECT":
					c.conectar();
					break;
				case "LIST":
					System.out.println(Arrays.toString(c.list()));
					break;
				case "RETR":
					String nombre = in.readLine();
					c.retr(nombre);
					terminado = true;
					System.out.println("conexion termiada");
					break;

				default:
					System.out.println("Comando no identificado");
			}
		}
		System.out.println("conexion termiada1");

	}
}
