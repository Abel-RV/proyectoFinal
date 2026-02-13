package es.abelramirez.threads;

import es.abelramirez.ModeloCandidatos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class HiloServidor extends Thread {
    private ServerSocket serverSocket;
    private ExecutorService pool;
    private ModeloCandidatos mc;

    public HiloServidor(ServerSocket serverSocket, ExecutorService pool, ModeloCandidatos mc) {
        this.serverSocket = serverSocket;
        this.pool = pool;
        this.mc = mc;
    }

    @Override
    public void run() {
        try{
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                HiloVotacion hiloVotacion = new HiloVotacion(socket,mc);
                pool.submit(hiloVotacion);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
