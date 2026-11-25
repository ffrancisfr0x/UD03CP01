import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000);){
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println("Conectando...");

            String mensaje;
            while ((mensaje = input.readLine()) != null) {
                out.println(mensaje);
                if (mensaje.trim().equalsIgnoreCase("FIN")) {
                    break;
                }
                String respuesta = in.readLine();
                if (respuesta == null) {
                    break;
                }
                System.out.println("Servidor: " + respuesta);
            }

            System.out.println("Cliente finalizado");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
