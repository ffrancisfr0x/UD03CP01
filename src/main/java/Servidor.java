import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(5000)) {
            System.out.println("Servidor eco escuchando en puerto 5000");

            while (true) {
                try (Socket cliente = server.accept();
                     BufferedReader input = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                     PrintWriter out = new PrintWriter(cliente.getOutputStream(), true)) {

                    System.out.println("Cliente conectado: " + cliente.getInetAddress());
                    String linea;

                    while ((linea = input.readLine()) != null) {
                        if (linea.trim().equalsIgnoreCase("FIN")) {
                            System.out.println("Cliente solicita cierre.");
                            break;
                        }
                        System.out.println("Recibido: " + linea);
                        out.println(linea.toUpperCase());
                    }
                }
                System.out.println("Esperando nuevo cliente...");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}