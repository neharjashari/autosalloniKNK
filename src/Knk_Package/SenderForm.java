package Knk_Package;
import java.util.Properties;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.net.InetAddress;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.net.UnknownHostException;
import javax.swing.*;
import Knk_Package.sqlConnect;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.util.Random;
import java.awt.Font;
import Knk_Package.frmForgotPassword;

import javax.swing.JOptionPane;
import java.sql.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hammad
 */
public class SenderForm extends javax.swing.JFrame {

    /**
     * Creates new form SenderForm
     */
	Connection conn=null;
	//objekti per vendosje te rezultatit
	ResultSet res=null;
	//objekti per query
	PreparedStatement pst=null;
	public static String totoMail = "";
	public static String kodiV = "";
    public SenderForm() {
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

        jFileChooser1 = new javax.swing.JFileChooser();
        jFileChooser2 = new javax.swing.JFileChooser();
        jFileChooser3 = new javax.swing.JFileChooser();
        lblSendTo = new javax.swing.JLabel();
        lblSendTo.setFont(new Font("Tahoma", Font.PLAIN, 25));
        txtSendTo = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 204, 255));

        lblSendTo.setText("Email:");

        txtSendTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        btnSend.setText("Dergoni");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(btnSend, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(97)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(txtSendTo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(lblSendTo)
        							.addPreferredGap(ComponentPlacement.RELATED, 294, Short.MAX_VALUE)))))
        			.addGap(243))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap(93, Short.MAX_VALUE)
        			.addComponent(lblSendTo)
        			.addGap(18)
        			.addComponent(txtSendTo, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(btnSend, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
        			.addGap(127))
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    public void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
              
  
  String host="smtp.gmail.com";  
  
  final String user = "autosalloniroyce@gmail.com";
  
  final String password = "Autosalloni1234";
  
  if(!user.equals("") && !password.equals(""))
  {
  String SMTP_PORT = "465";
  String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";  
  
  //String to="bitf11m036@pucit.edu.pk";//change accordingly  
  String to=totoMail;
  
   //Get the session object  
   Properties props = new Properties();
   props.put("mail.smtp.starttls.enable", "true");
   props.put("mail.smtp.host",host);  
   props.put("mail.smtp.auth", "true");  
   props.put("mail.debug", "true");
   props.put("mail.smtp.port", SMTP_PORT);
   props.put("mail.smtp.socketFactory.port", SMTP_PORT);
   props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
   props.put("mail.smtp.socketFactory.fallback", "false");  
   
   Session session = Session.getDefaultInstance(props,  
    new javax.mail.Authenticator() {
        
    protected PasswordAuthentication getPasswordAuthentication() {  
    return new PasswordAuthentication(user,password);  
      }  
    });  
  
   //Compose the message  
    try {  
     MimeMessage message = new MimeMessage(session);  
        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");  
        message.setFrom(new InternetAddress(user));  
        message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
        //message.setSubject("Subject");  
        message.setSubject("Password Recovery Email");
        message.setText("Kodi juaj i verifikimit eshte:" + kodiV);  
        //send the message  
        Transport.send(message);  
  
     
     JOptionPane.showMessageDialog(null,"Kodi i verifikimit u dergua me sukses");
     
     } catch (MessagingException e) {e.printStackTrace();}  
  }
  else 
  {
      JOptionPane.showMessageDialog(null,"Ju lutem shenoni emailin");
  }
  	

  
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SenderForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SenderForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SenderForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SenderForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SenderForm().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSend;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JFileChooser jFileChooser2;
    private javax.swing.JFileChooser jFileChooser3;
    private javax.swing.JLabel lblSendTo;
    private javax.swing.JTextField txtSendTo;
}