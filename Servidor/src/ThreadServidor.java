import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;

public class ThreadServidor extends Thread{
    //atributo socket
    private Socket sktCliente ;

    private int id;





    // defina un atributo identificador localde tipo int
    public ThreadServidor(Socket pSocket, int pId) throws Exception{
        // asigne el valor a los atributos correspondientes
        this.sktCliente= pSocket;
        this.id   = pId;

    }

    public void run() {
        System.out.println("Inicio de nuevo thread." + id);
        try (PrintWriter escritor = new
                PrintWriter(sktCliente.getOutputStream(), true);
             BufferedReader lector = new BufferedReader(new InputStreamReader(sktCliente.getInputStream()))){

            procesar(lector,escritor);
//            escritor.close();
//            lector.close();
//            sktCliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void procesar(BufferedReader lector, PrintWriter escritor) throws IOException {
        String linea= lector.readLine();
        boolean terminado = false;
        while(!terminado && !linea.equals("CLOSE") ) {
            switch (linea) {
                case "CONNECT":
                    System.out.println("enter connected");
                    escritor.println("CONNECTED");
                    break;
                case "LIST":
//                    escritor.println("history.jpg;;starWars.jpg;;space.jpg;;spacex.jpg");
                    String res = "";
                    File dir = new File("data");
                    File[] directoryListing = dir.listFiles();
                    if (directoryListing != null) {
                        for (File child : directoryListing) {
                            res += child.getName() + ";;";
                        }
                    }
                    res = res.substring(0, res.length() - 2);
                    System.out.println("sending " + res);
                    escritor.println(res);
                    break;
                default:
                    String temp = linea.split("::")[1];
//                    OutputStream outputStream = sktCliente.getOutputStream();

//                    BufferedImage image = ImageIO.read(new File("data/" + temp));
//
//                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                    ImageIO.write(image, "jpg", byteArrayOutputStream);
//
//                    byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
//                    outputStream.write(size);
//                    outputStream.write(byteArrayOutputStream.toByteArray());
//                    outputStream.flush();
//                    outputStream.flush();
//                    System.out.println("done");

                    File myFile = new File ("data/"+temp);
                    byte [] mybytearray  = new byte [(int)myFile.length()];
                    FileInputStream fis = new FileInputStream(myFile);
                    BufferedInputStream bis = new BufferedInputStream(fis);
                    bis.read(mybytearray,0,mybytearray.length);
                    OutputStream os=  sktCliente.getOutputStream();
                    System.out.println("Sending " + temp + "(" + mybytearray.length + " bytes)");
                    os.write(mybytearray,0,mybytearray.length);
                    os.flush();
                    System.out.println("Done.");

//                    System.out.println("Socket cerrado" + sktCliente.isClosed());

                    escritor.close();
                    lector.close();
                    bis.close();
                    os.close();
                    fis.close();
                    terminado = true;

//                    System.out.println("Socket cerrado" + sktCliente.isClosed());
                    break;
            }
            if(!terminado){
                linea = lector.readLine();
            }

        }

        escritor.println("ADIOS");

        sktCliente.close();
    }
}