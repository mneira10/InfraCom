import java.io.IOException;
import java.net.ServerSocket;

public class Servidor {


    public static void main(String[] args) throws Exception {

        String ip = "localhost";
        int puerto = 9000;
        int cont = 0 ;
        ServerSocket ss = null;
        boolean continuar = true;
        // defina variable para contar e identificar los threads
        try
        {
            ss =new ServerSocket(puerto);
//            ss.setSoTimeout(5000);
            ss.setReceiveBufferSize(655300);

        }
        catch (IOException e) {
            System.err.println("No pudo crear socket en el puerto:"+ puerto);
            System.exit(-1);
        }
        System.out.println("Listening on port " + puerto);
        while (continuar) {
            new ThreadServidor(ss.accept(),cont++).start();
            // incremente identificador de thread
        }
        ss.close();
    }
}
