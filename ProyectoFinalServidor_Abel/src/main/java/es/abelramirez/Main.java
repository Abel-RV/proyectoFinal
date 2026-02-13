package es.abelramirez;

import es.abelramirez.threads.HiloServidor;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.setProperty("javax.net.ssl.keyStore", "ssl/server_keystore.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "123456");

        ModeloCandidatos mc = new ModeloCandidatos();
        ExecutorService pool = Executors.newFixedThreadPool(5);
        int puerto =1000;
        try{
            SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            SSLServerSocket serverSocket = (SSLServerSocket) ssf.createServerSocket();
            HiloServidor hiloServidor = new HiloServidor(serverSocket,pool,mc);
            hiloServidor.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}