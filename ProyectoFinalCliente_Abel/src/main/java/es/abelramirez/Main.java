package es.abelramirez;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.setProperty("javax.net.ssl.trustStore", "ssl/client_truststore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "123456");

        String host = "localhost";
        int puerto= 5555;

        try{
            SSLSocketFactory sf =(SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) sf.createSocket(host,puerto);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            Scanner sc = new Scanner(System.in);

            System.out.println("Conexión establecida.");

            boolean salir = false;
            while (!salir) {
                System.out.println("\n--- SISTEMA DE VOTACIÓN ---");
                System.out.println("1. Listar Candidatos");
                System.out.println("2. Votar");
                System.out.println("3. Ver Resultados");
                System.out.println("4. Salir");
                System.out.print("Elige una opción: ");

                String opcion = sc.nextLine();
                String comando = "";

                // Mapeo de opciones a comandos del protocolo
                switch (opcion) {
                    case "1": comando = "LISTAR"; break;
                    case "2": comando = "VOTAR"; break;
                    case "3": comando = "RESULTADOS"; break;
                    case "4": comando = "END"; break;
                    default:
                        System.out.println("Opción no válida.");
                        continue;
                }

                // Enviar comando
                dos.writeUTF(comando);

                // Manejar respuestas según el protocolo
                if (comando.equals("END")) {
                    salir = true;
                } else if (comando.equals("VOTAR")) {
                    System.out.println(dis.readUTF());
                    String voto = sc.nextLine();
                    dos.writeUTF(voto);
                    System.out.println("SERVIDOR: " + dis.readUTF());
                } else {
                    System.out.println("RESPUESTA SERVIDOR:");
                    System.out.println(dis.readUTF());
                }
            }
            socket.close();
            System.out.println("Desconectado.");

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {

        }
    }
}