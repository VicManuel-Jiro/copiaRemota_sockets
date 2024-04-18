
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Jiro
 */
public class Servidor extends javax.swing.JFrame implements Runnable {

    int puerto = 9000, c = 0;
    String log, tarea;
    File file = null;
    FileInputStream original = null;
    FileOutputStream copia = null;
    FileInputStream copiaLectura = null;
    String[] nodos = null;
    String ruta = "";
    String rutaA = "";
    String cont = "";

    /**
     * Creates new form Servidor
     */
    public Servidor() {
        initComponents();
        try {
            String contenido = "Beso en la boca es cosa del pasado\nLa moda ahora es enamorar pelado(2x)\n\nlas manos hacia arriba\nBate palmas sin parar\nMoviendo todo el cuerpo\nY comienzas a bajar(2x)\n\nAhora quiero verte saltar\nverte saltar\nAhora quiero verte mover\nverte mover\nAhora quiero verte bailar\nverte bailar\nAhora quiero verte besar\nverte besar\n\nBeso en la boca es cosa del pasado\nLa moda ahora es enamorar pelado(2x)\n\nlas manos hacia arriba\nBate palmas sin parar\nMoviendo todo el cuerpo\nY comienzas a bajar(2x)\n\nAhora quiero verte saltar\nverte saltar\nAhora quiero verte mover\nverte mover\nAhora quiero verte bailar\nverte bailar\nAhora quiero verte besar\nverte besar\n\nBeso en la boca es cosa del pasado\nLa moda ahora es enamorar pelado(2x)\n\nhay ponte serio\n\nlas manos hacia arriba\nBate palmas sin parar\nMoviendo todo el cuerpo\nY comienzas a bajar(2x)\n\nbeso en la boca.........(2x)\n\nquero by quero by\n\nbeso en la boca...........(2x)\n\nhay ponte serio";
            file = new File("original.txt");
            if (!file.exists()) {
                FileOutputStream original = new FileOutputStream(file);
                original.write(contenido.getBytes());
                original.close();
            }
            ruta = file.getAbsolutePath();
            rutaA = ruta.replace("original.txt", "");
            System.out.println(ruta);
        } catch (IOException e) {
            this.TxtLog.append("no se creo el archivo original");
        }
        this.setLocationRelativeTo(null);
        Thread hilo = new Thread(this);
        hilo.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TxtLog = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setText("Servidor");

        TxtLog.setColumns(20);
        TxtLog.setRows(5);
        jScrollPane1.setViewportView(TxtLog);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(169, 169, 169)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Servidor().setVisible(true);

            }
        }
        );
    }//*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea TxtLog;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    //@Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        log = "\nEsperando conexión...\n";
        this.TxtLog.append(log);

        try {
            ServerSocket servidor = new ServerSocket(this.puerto);
            while (true) {
                Socket socket = servidor.accept();
                DataInputStream paquete = new DataInputStream(socket.getInputStream());

                this.log = paquete.readUTF();
                this.tarea = this.log;
                if (this.log.equals("Abriendo conexión")) {
                    //mensaje de bienvenida servidor abre primero
                    c = 1;
                    this.TxtLog.append("\n" + this.log + "\n\n");
                    DataOutputStream enviar_datos = new DataOutputStream(socket.getOutputStream());
                    enviar_datos.writeUTF(ruta);
                } else if (c == 0) {
                    String msg = "\nAbriendo conexión\n\n";
                    c = 1;
                    this.TxtLog.append(msg + log.replace("$", " ") + "\n");
                    //tarea y mensaje de bienvenida cliente abre primero
                    tarea(1, socket);
                    //respuesta
                } else {
                    System.out.println(this.tarea);
                    this.TxtLog.append(log.replace("$", " ") + "\n");
                    //tarea happy path
                    tarea(0, socket);
                }
                socket.close();
            }
        } catch (ConnectException e1) {
            this.TxtLog.append(e1.getMessage() + "\n");
        } catch (UnknownHostException e2) {
            System.out.println(e2.getMessage() + "\n");
        } catch (IOException e3) {
            System.out.println(e3.getMessage() + "\n");
        }
    }//*/

    private void tarea(int o, Socket socket) {
        try {
            int error = 0;
            nodos = log.split("\\$");
            System.out.println(nodos[0]);
            System.out.println(nodos[1]);
            if (nodos.length == 2) {
                //lectura
                ruta = nodos[1].trim();
                file = new File(ruta);
                if (file.exists()) {
                    original = new FileInputStream(ruta);
                    int content;

                    while ((content = original.read()) != -1) {
                        cont += (char) content;
                    }
                    cont += "\n";
                    //System.out.println("contenido");
                    //System.out.println(c);
                } else {
                    error = 1;
                    cont = "";
                    DataOutputStream enviar_datos = new DataOutputStream(socket.getOutputStream());
                    enviar_datos.writeUTF(cont + "\nEl archivo no existe.");
                    this.TxtLog.append("El archivo no existe.\n\n");
                    cont = "";
                }
            } else {
                //copia
                System.out.println(nodos[2]);
                ruta = nodos[1].trim();
                file = new File(ruta);
                String ruta2 = nodos[2].trim();
                if (file.exists()) {
                    original = new FileInputStream(ruta);

                    int content;

                    while ((content = original.read()) != -1) {
                        cont += (char) content;
                    }
                    //realizar copia
                    file = new File(ruta2);
                    if (!file.exists()) {
                        file.delete();
                    }
                    FileOutputStream copia = new FileOutputStream(file);
                    copia.write(cont.getBytes());
                    copia.close();
                    cont = "";

                } else {
                    error = 1;
                    cont = "";
                    DataOutputStream enviar_datos = new DataOutputStream(socket.getOutputStream());
                    enviar_datos.writeUTF(cont + "\nEl archivo de origen no existe.");
                    this.TxtLog.append("El archivo de origen no existe.\n\n");
                    cont = "";
                }
            }
            if (error == 0) {
                if (o == 0) {
                    //respuesta happy path
                    DataOutputStream enviar_datos = new DataOutputStream(socket.getOutputStream());
                    enviar_datos.writeUTF(cont + "\nRealizado.");
                    this.TxtLog.append("Realizado.\n\n");
                    cont = "";
                } else {
                    DataOutputStream enviar_datos = new DataOutputStream(socket.getOutputStream());
                    enviar_datos.writeUTF("Conexión establecida$\n" + cont + "\nRealizado.");
                    this.TxtLog.append("Realizado.\n\n");
                    cont = "";
                }
            }
            error = 0;
        } catch (ConnectException e1) {
            this.TxtLog.append(e1.getMessage() + "\n");
        } catch (UnknownHostException e2) {
            System.out.println(e2.getMessage() + "\n");
        } catch (IOException e3) {
            System.out.println(e3.getMessage() + "\n");
        }

    }

}
