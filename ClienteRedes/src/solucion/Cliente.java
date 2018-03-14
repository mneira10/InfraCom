package solucion;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Cliente {
	
	Socket socket;
	BufferedReader in;
	PrintWriter out;
	
	public static final String PAR = ";;";
	public static final String COM = "::";
	
	public void crearSocket(String nombreHost, int puerto) throws UnknownHostException, IOException {
		socket = new Socket(nombreHost, puerto);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
	}
	
	public String[] list() throws IOException {
		out.println("LIST");
		return in.readLine().split(PAR);
	}
	
	public void retr(String nombre) throws IOException {
		out.println("RETR::" + nombre);
//		File f = new File("." + File.separator + nombre);
//		if (f.createNewFile()) {
//			FileOutputStream fos = new FileOutputStream(f);
//			InputStream fr = socket.getInputStream();
//			int count;
//			byte[] bytes = new byte[128 * 1024];
//
//			while ((count = fr.read(bytes)) > 0) {
//				System.out.println(count);
//				System.out.println("data received: " + new String(bytes));
//				fos.write(bytes, 0, count);
//			}
//			System.out.println("done");
//			fos.close();
//		} else {
//			System.out.println(nombre + " already exists");
//		}
//		InputStream inputStream = socket.getInputStream();
//
//		ImageInputStream imgStream = ImageIO.createImageInputStream(inputStream);
//
//		System.out.println("Reading: " + System.currentTimeMillis());
//
//
//
//		byte[] sizeAr = new byte[4];
//		inputStream.read(sizeAr);
//		int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();
//
//		byte[] imageAr = new byte[size];
//		inputStream.read(imageAr);
//
//		BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));
//
//		System.out.println("Received " + image.getHeight() + "x" + image.getWidth() + ": " + System.currentTimeMillis());
//		ImageIO.write(image, "jpg", new File("data/"+nombre));
//		System.out.println("Escrito a disco");
		int bytesRead;
		int current = 0;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;

		try {

			System.out.println("Connecting...");

			// receive file
//			byte [] mybytearray  = new byte [602238600];
			byte [] mybytearray  = new byte [602238600];
			InputStream is = socket.getInputStream();

			fos = new FileOutputStream("data/"+nombre);
			bos = new BufferedOutputStream(fos);
			bytesRead = is.read(mybytearray,0,mybytearray.length);
			current = bytesRead;

			String msq = "";
			do {
				bytesRead = is.read(mybytearray, current, (mybytearray.length-current));
                if (bytesRead != -1) {
                	System.out.println((bytesRead + " bytes read"));
				}
				if(bytesRead >= 0) current += bytesRead;
			} while(bytesRead > -1);

			bos.write(mybytearray, 0 , current);

			bos.flush();
			System.out.println("File " + nombre
					+ " downloaded (" + current + " bytes read)");
		}
		finally {
			if (fos != null) fos.close();
			if (bos != null) bos.close();

		}



	}
	
	public void close() throws IOException {
		out.println("CLOSE");
		System.out.println(in.readLine());
		socket.close();
		in.close();
		out.close();
	}
}






















