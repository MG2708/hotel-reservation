/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* a simple chat application with GUI
* user connects as client
* code for client frame where client connects to server and receives the messages from server
*/
package package_chat; //package name where class is located

//import the required classes and variables from the "ServerFrame" class.
import static package_chat.ServerFrame.dout;
import static package_chat.ServerFrame.ss;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

/**
 *
 * @author nihar, ojaswini, mudit
 */

//class representing the client-side GUI
public class ClientFrame extends javax.swing.JFrame implements Runnable {
    static Socket s; //endpoint for communications between the client and server
    static DataInputStream dis; //read primitive data from the input stream, here from server
    static DataOutputStream dout; //to write data that can later be read by a data input stream, here by server
    //static String currentUser = "";

    /**
     * Creates new form chat_client
     */
    //constructor call
    public ClientFrame() {
       //initializes the GUI components that are declared and placed using the NetBeans GUI editor.
       initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        msg_area = new javax.swing.JTextArea();
        msg_text = new javax.swing.JTextField();
        msg_send = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        msg_area.setEditable(false);
        msg_area.setColumns(20);
        msg_area.setRows(5);
        jScrollPane1.setViewportView(msg_area);

        msg_send.setText("send");
        msg_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                msg_sendActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Algerian", 1, 18)); // NOI18N
        jLabel1.setText("guest");

        jLabel2.setText("MESSAGE:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(msg_text, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(msg_send)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(msg_text, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(msg_send)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void close() throws IOException {
        if (s != null) {
            s.close();
        }
    }
    
    public void run(){
        //currentUser = "Client";
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientFrame().setVisible(true);
            }
        });
        
         try {
            String msgin = "";
            
            s = new Socket("127.0.0.1",1201); // ip address is of localhost because server is running on the same mschine
            dis = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());

            while (!msgin.equals("exit")) {
                msgin = dis.readUTF();
                msg_area.setText(msg_area.getText() + "\n  Manager : " + msgin);
            }
            s.close();

        } catch (Exception e) {
            //handle the exception here
        }finally{
            Thread.currentThread().interrupt();
        }  
    }

    //method to retrieve text message from the text field and sends it to the server using the "dout" object.
    // appends the sent message to the client's message area.
    //activated when button pressed
    private void msg_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_msg_sendActionPerformed
        // TODO add your handling code here:
        
         try{
        String msg="";
        msg=msg_text.getText();
        dout.writeUTF(msg);
        msg_area.append("\nYou: " + msg + "\n");
        msg_text.setText("");
        }
        catch(Exception e)
        {
        //handle the exception here
        }
        
    }//GEN-LAST:event_msg_sendActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ClientFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ClientFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ClientFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ClientFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        currentUser = "Client";
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ClientFrame().setVisible(true);
//            }
//        });
//        
//         try {
//            String msgin = "";
//            
//            s = new Socket("127.0.0.1",1201); // ip address is of localhost because server is running on the same mschine
//            dis = new DataInputStream(s.getInputStream());
//            dout = new DataOutputStream(s.getOutputStream());
//
//            while (!msgin.equals("exit")) {
//                msgin = dis.readUTF();
//                msg_area.setText(msg_area.getText() + "\n Manager : " + msgin);
//            }
//
//        } catch (Exception e) {
//            //handle the exception here
//        }
//        
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextArea msg_area;
    private javax.swing.JButton msg_send;
    private javax.swing.JTextField msg_text;
    // End of variables declaration//GEN-END:variables
}