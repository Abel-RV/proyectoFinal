package es.abelramirez.threads;

import es.abelramirez.ModeloCandidatos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class HiloVotacion extends Thread{
    private Socket socket;
    private ModeloCandidatos mc;

    public HiloVotacion(Socket socket,ModeloCandidatos mc) {
        this.socket = socket;
        this.mc = mc;
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
                    case "LISTAR"->{
                        List<String> candidatos = mc.obtenerListasNombre();
                        dos.writeUTF(candidatos.toString());
                    }

                    case "VOTAR"->{
                        dos.writeUTF("Introduce el nombre del candidato a votar: ");
                        String nombre = dis.readUTF();

                        boolean registrado = mc.votar(nombre);

                        if(registrado){
                            dos.writeUTF("Voto confirmado para: "+nombre);
                        }
                    }

                    case "RESULTADOS"->{
                        dos.writeUTF(mc.obtenerResultados().toString());
                    }

                    default -> {
                        dos.writeUTF("ERROR");
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
