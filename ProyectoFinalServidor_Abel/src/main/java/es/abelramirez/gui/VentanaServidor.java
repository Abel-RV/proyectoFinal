package es.abelramirez.gui;

import es.abelramirez.ModeloCandidatos;
import es.abelramirez.threads.HiloServidor;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VentanaServidor extends javax.swing.JFrame {

    private SSLServerSocket serverSocket;
    private ExecutorService pool;
    private ModeloCandidatos modeloCandidatos;


    public VentanaServidor() {
        initComponents();

        modeloCandidatos = new ModeloCandidatos();

        bIniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bIniciarActionPerformed(e);
            }
        });

        bParar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bPararActionPerformed(e);
            }
        });
    }


    private void habilitar(boolean encendido) {
        bIniciar.setEnabled(!encendido);
        bParar.setEnabled(encendido);
        tPuerto.setEnabled(!encendido);
        tMaxClients.setEnabled(!encendido);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tPuerto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tMaxClients = new javax.swing.JTextField();
        bIniciar = new javax.swing.JButton();
        bParar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Servidor Votación SSL");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Configuración SSL"));

        jLabel1.setText("Puerto");
        tPuerto.setText("5555");

        jLabel2.setText("Max. Clientes");
        tMaxClients.setText("10");

        bIniciar.setText("Iniciar");

        bParar.setText("Parar");
        bParar.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(23, 23, 23)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tMaxClients, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(bIniciar, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(bParar, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(tPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bIniciar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tMaxClients, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2)
                                        .addComponent(bParar))
                                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>

    private void bIniciarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int puerto = Integer.parseInt(tPuerto.getText());
            int numClientes = Integer.parseInt(tMaxClients.getText());

            System.setProperty("javax.net.ssl.keyStore", "ssl/server_keystore.jks");
            System.setProperty("javax.net.ssl.keyStorePassword", "123456");

            SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            serverSocket = (SSLServerSocket) ssf.createServerSocket(puerto);

            pool = Executors.newFixedThreadPool(numClientes);

            HiloServidor servidor = new HiloServidor(serverSocket, pool, modeloCandidatos, this);
            servidor.start();

            System.out.println("Servidor SSL INICIADO en puerto " + puerto);
            habilitar(true);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El puerto y clientes deben ser números enteros.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al iniciar servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void bPararActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // Cerrar el socket principal
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
            // Parar el pool de hilos
            if (pool != null && !pool.isShutdown()) {
                pool.shutdownNow();
            }

            System.out.println("Servidor DETENIDO.");
            habilitar(false);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al detener: " + e.getMessage());
        }
    }

    // --- MÉTODO MAIN (Punto de entrada) ---

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(VentanaServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaServidor().setVisible(true);
            }
        });
    }
    private javax.swing.JButton bIniciar;
    private javax.swing.JButton bParar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField tMaxClients;
    private javax.swing.JTextField tPuerto;
}