package es.abelramirez.threads;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloVotacion extends Thread{
    private Socket socket;

    public HiloVotacion(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try(DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());) {

            while (true) {
                String comando = dis.readUTF();
                if(comando.equalsIgnoreCase("END")){
                    break;
                }
                switch (comando) {
                    case "LISTAR":
                        dos.writeUTF("");
                }
            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
