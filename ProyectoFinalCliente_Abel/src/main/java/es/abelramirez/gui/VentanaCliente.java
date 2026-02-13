package es.abelramirez.gui;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Cliente SSL con interfaz estilo NetBeans.
 */
public class VentanaCliente extends javax.swing.JFrame {

    // --- ATRIBUTOS LÓGICOS ---
    private SSLSocket socket;
    private DataOutputStream dos;
    private DataInputStream dis;

    /**
     * Constructor
     */
    public VentanaCliente() {
        initComponents();
        habilitar(false); // Al principio solo podemos conectar

        // Listeners de Conexión
        bConectar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bConectarActionPerformed(e);
            }
        });
        bDesconectar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bDesconectarActionPerformed(e);
            }
        });

        // Listeners de Acciones
        bListar.addActionListener(e -> enviarAccion("LISTAR"));
        bResultados.addActionListener(e -> enviarAccion("RESULTADOS"));
        bVotar.addActionListener(e -> accionVotar());
        bSalir.addActionListener(e -> accionSalir());
    }

    private void habilitar(boolean conectado) {
        // Panel Conexión
        bConectar.setEnabled(!conectado);
        bDesconectar.setEnabled(conectado);
        tIP.setEnabled(!conectado);
        tPuerto.setEnabled(!conectado);

        // Panel Acciones
        bListar.setEnabled(conectado);
        bVotar.setEnabled(conectado);
        bResultados.setEnabled(conectado);
        bSalir.setEnabled(conectado);
    }

    /**
     * CÓDIGO GENERADO POR NETBEANS (Diseño)
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        panelConexion = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tIP = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tPuerto = new javax.swing.JTextField();
        bConectar = new javax.swing.JButton();
        bDesconectar = new javax.swing.JButton();

        panelAcciones = new javax.swing.JPanel();
        bListar = new javax.swing.JButton();
        bVotar = new javax.swing.JButton();
        bResultados = new javax.swing.JButton();
        bSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cliente Votación SSL");

        // --- PANEL DE CONEXIÓN ---
        panelConexion.setBorder(javax.swing.BorderFactory.createTitledBorder("Configuración de Conexión"));

        jLabel1.setText("IP Servidor:");
        tIP.setText("localhost");

        jLabel2.setText("Puerto:");
        tPuerto.setText("5555");

        bConectar.setText("Conectar");

        bDesconectar.setText("Desconectar");
        bDesconectar.setEnabled(false);

        javax.swing.GroupLayout panelConexionLayout = new javax.swing.GroupLayout(panelConexion);
        panelConexion.setLayout(panelConexionLayout);
        panelConexionLayout.setHorizontalGroup(
                panelConexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelConexionLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelConexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(panelConexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tIP, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                        .addComponent(tPuerto))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                .addGroup(panelConexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(bConectar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bDesconectar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        panelConexionLayout.setVerticalGroup(
                panelConexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelConexionLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelConexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(tIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bConectar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelConexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(tPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bDesconectar))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        // --- PANEL DE ACCIONES ---
        panelAcciones.setBorder(javax.swing.BorderFactory.createTitledBorder("Acciones"));

        bListar.setText("Listar Candidatos");
        bVotar.setText("Votar");
        bResultados.setText("Ver Resultados");
        bSalir.setText("Salir");

        javax.swing.GroupLayout panelAccionesLayout = new javax.swing.GroupLayout(panelAcciones);
        panelAcciones.setLayout(panelAccionesLayout);
        panelAccionesLayout.setHorizontalGroup(
                panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelAccionesLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(bListar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bVotar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bResultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        panelAccionesLayout.setVerticalGroup(
                panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelAccionesLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(bListar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bVotar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bResultados)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                .addComponent(bSalir)
                                .addContainerGap())
        );

        // Layout Principal
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(panelConexion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(panelAcciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panelConexion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(panelAcciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>

    // --- LÓGICA DE CONEXIÓN ---

    private void bConectarActionPerformed(java.awt.event.ActionEvent evt) {
        new Thread(() -> {
            try {
                String ip = tIP.getText();
                int puerto = Integer.parseInt(tPuerto.getText());

                // 1. Cargar TrustStore (Certificados de confianza)
                System.setProperty("javax.net.ssl.trustStore", "ssl/client_truststore.jks");
                System.setProperty("javax.net.ssl.trustStorePassword", "123456");

                // 2. Conectar
                SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
                socket = (SSLSocket) sf.createSocket(ip, puerto);

                dos = new DataOutputStream(socket.getOutputStream());
                dis = new DataInputStream(socket.getInputStream());

                habilitar(true);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El puerto debe ser un número.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al conectar: " + e.getMessage());
            }
        }).start();
    }

    private void bDesconectarActionPerformed(java.awt.event.ActionEvent evt) {
        accionSalir();
    }

    // --- LÓGICA DE ACCIONES ---

    private void enviarAccion(String comando) {
        new Thread(() -> {
            try {
                dos.writeUTF(comando);
                String respuesta = dis.readUTF();
                // Mostramos la respuesta en un Popup
                JOptionPane.showMessageDialog(this, respuesta, "Respuesta del Servidor", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error de comunicación: " + e.getMessage());
                habilitar(false);
            }
        }).start();
    }

    private void accionVotar() {
        new Thread(() -> {
            try {
                dos.writeUTF("VOTAR");

                // Recibir: "¿A quién quieres votar?"
                String pregunta = dis.readUTF();

                // Pedir input al usuario
                String candidato = JOptionPane.showInputDialog(this, pregunta);

                if (candidato != null && !candidato.trim().isEmpty()) {
                    dos.writeUTF(candidato); // Enviar voto
                    String confirmacion = dis.readUTF(); // Recibir confirmación
                    JOptionPane.showMessageDialog(this, confirmacion);
                } else {
                    // Si cancela, para no romper el protocolo, enviamos algo vacío
                    dos.writeUTF("");
                    dis.readUTF(); // Limpiar buffer de respuesta de error
                    JOptionPane.showMessageDialog(this, "Votación cancelada.");
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al votar: " + e.getMessage());
                habilitar(false);
            }
        }).start();
    }

    private void accionSalir() {
        try {
            if (dos != null) dos.writeUTF("END");
            if (socket != null && !socket.isClosed()) socket.close();
        } catch (IOException e) {
            // Ignorar error al cerrar
        }
        habilitar(false);
        JOptionPane.showMessageDialog(this, "Desconectado del servidor.");
    }

    // --- MAIN ---

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(() -> new VentanaCliente().setVisible(true));
    }

    // Variables declaration
    private javax.swing.JButton bConectar;
    private javax.swing.JButton bDesconectar;
    private javax.swing.JButton bListar;
    private javax.swing.JButton bResultados;
    private javax.swing.JButton bSalir;
    private javax.swing.JButton bVotar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel panelAcciones;
    private javax.swing.JPanel panelConexion;
    private javax.swing.JTextField tIP;
    private javax.swing.JTextField tPuerto;
}
