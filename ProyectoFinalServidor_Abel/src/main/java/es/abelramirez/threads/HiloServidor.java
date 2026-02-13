package es.abelramirez.threads;

import es.abelramirez.ModeloCandidatos;
import es.abelramirez.gui.VentanaServidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class HiloServidor extends Thread {
    private ServerSocket serverSocket;
    private ExecutorService pool;
    private ModeloCandidatos mc;
    private VentanaServidor vs;

    public HiloServidor(ServerSocket serverSocket, ExecutorService pool, ModeloCandidatos mc,  VentanaServidor vs) {
        this.serverSocket = serverSocket;
        this.pool = pool;
        this.mc = mc;
        this.vs = vs;
    }

    @Override
    public void run() {
        try{
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                HiloVotacion hiloVotacion = new HiloVotacion(socket,mc,vs);
                pool.submit(hiloVotacion);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
