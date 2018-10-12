package Knk_Package;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.util.Properties;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
//import javax.activation.*;
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
import Knk_Package.SenderForm;

import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.JOptionPane;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
public class frmForgotPassword extends JFrame {

	private JPanel contentPane;
	private JTextField txtDergo;
	private JLabel lblKodi;
	private JTextField txtKodi;
	private JButton btnVerifiko;
	private String str = "";
	Connection conn=null;
	//objekti per vendosje te rezultatit
	ResultSet res=null;
	//objekti per query
	PreparedStatement pst=null;
	private JLabel label;
	private static String email;
	Locale currentLocale;
	ResourceBundle messages;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmForgotPassword frame = new frmForgotPassword();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static String getEmail()
	{
		return email;
	}
	UserPreferences userPreferences = new UserPreferences();
	/**
	 * Create the frame.
	 */
	public frmForgotPassword() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmForgotPassword.class.getResource("/img2/icons/1.png")));
		userPreferences = userPreferences.Preference();
		String lang = userPreferences.getLang();

		if (lang.equals("al")) {

			currentLocale = new Locale(new String("al"), new String("AL"));
			try {
				messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);

			}

			catch (MissingResourceException e) {
				e.printStackTrace();

			}

		} else {
			currentLocale = new Locale(new String("en"), new String("US"));
			try {
				messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);

			} catch (MissingResourceException e) {
				e.printStackTrace();

			}

		

		}

		conn=sqlConnect.connectDB();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 905, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(415, 78, 56, 25);
		contentPane.add(lblEmail);
		
		JLabel lblPerdoruesi = new JLabel("");
		lblPerdoruesi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPerdoruesi.setForeground(Color.WHITE);
		lblPerdoruesi.setBounds(320, 156, 126, 16);
		contentPane.add(lblPerdoruesi);
		
		txtDergo = new JTextField();
		txtDergo.setBounds(331, 132, 224, 32);
		contentPane.add(txtDergo);
		txtDergo.setColumns(10);
		
		
		
		JButton btnDergo = new JButton(messages.getString("dergo"));
		btnDergo.addKeyListener(new KeyAdapter() {
							@Override
							public void keyPressed(KeyEvent e) {
								
								if(e.getKeyCode()==KeyEvent.VK_ENTER)
								{
									try 
									{
										email = txtDergo.getText();
										if(txtDergo.getText().isEmpty())
										{
											JOptionPane.showMessageDialog(null,messages.getString("text19") );
											return;
										}
															
										pst = conn.prepareStatement("SELECT * FROM perdoruesit WHERE email = ?");
									    pst.setString(1, email);
									    res = pst.executeQuery();
									    
									    String emailDB;
									
									    if(res.next())
										{
									   		emailDB=res.getString("email");
									   		
									   		if(email.equals(emailDB))
									   		{
									   			//Pjesa e kodit qe gjeneron kodin autentifikues dhe e dergon ne email
												Random rand = new Random();
										         
										        for(int i=0;i<5;i++)
										        { 
										        	str += "" + rand.nextInt(10);
										        }
													SenderForm.totoMail = txtDergo.getText();
													SenderForm.kodiV = str;
													SenderForm snf = new SenderForm();
													snf.jButton1ActionPerformed(null);
													lblKodi.setVisible(true);
													txtKodi.setVisible(true);
													btnVerifiko.setVisible(true);
											}
									   	}
									    else
								   		{
								   			JOptionPane.showMessageDialog(null, messages.getString("text20"));
								   			txtDergo.setText("");
								   			txtDergo.requestFocus();
								   			return;
								   		}
								   
									
									}
									catch(Exception ex)
									{
										JOptionPane.showMessageDialog(null, messages.getString("text21") +ex.getMessage());
										return;
									}
									
									
								}
							}
						});
		
		btnDergo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDergo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try 
				{
					email = txtDergo.getText();
					if(txtDergo.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null, messages.getString("text19"));
						return;
					}
										
					pst = conn.prepareStatement("SELECT * FROM perdoruesit WHERE email = ?");
				    pst.setString(1, email);
				    res = pst.executeQuery();
				    
				    String emailDB;
				
				    if(res.next())
					{
				   		emailDB=res.getString("email");
				   		
				   		if(email.equals(emailDB))
				   		{
				   			//Pjesa e kodit qe gjeneron kodin autentifikues dhe e dergon ne email
							Random rand = new Random();
					         
					        for(int i=0;i<5;i++)
					        { 
					        	str += "" + rand.nextInt(10);
					        }
								SenderForm.totoMail = txtDergo.getText();
								SenderForm.kodiV = str;
								SenderForm snf = new SenderForm();
								snf.jButton1ActionPerformed(null);
								lblKodi.setVisible(true);
								txtKodi.setVisible(true);
								btnVerifiko.setVisible(true);
						}
				   	}
				    else
			   		{
			   			JOptionPane.showMessageDialog(null, messages.getString("text20"));
			   			txtDergo.setText("");
			   			txtDergo.requestFocus();
			   			return;
			   		}
			   
				
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, messages.getString("text21") +ex.getMessage());
					return;
				}
				
				
	
			}
		});
		
		
		
		btnDergo.setBounds(378, 203, 131, 41);
		contentPane.add(btnDergo);
		
		lblKodi = new JLabel(messages.getString("text23"));
		lblKodi.setForeground(Color.WHITE);
		lblKodi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblKodi.setBounds(367, 315, 153, 25);
		lblKodi.setVisible(false);
		contentPane.add(lblKodi);
		
		txtKodi = new JTextField();
		txtKodi.setBounds(332, 372, 222, 32);
		contentPane.add(txtKodi);
		txtKodi.setVisible(false);
		txtKodi.setColumns(10);
		
		btnVerifiko = new JButton(messages.getString("verif"));
		btnVerifiko.addKeyListener(new KeyAdapter() {
							@Override
							public void keyPressed(KeyEvent e) {
								
								if(e.getKeyCode()==KeyEvent.VK_ENTER)
								{
									if(txtKodi.getText().equals(String.valueOf(str)))
									{
										frmReset.main(null);
										dispose();
								}
				
									else
									{
										JOptionPane.showMessageDialog(null,messages.getString("text22"));
									}
								}
							}
						});
		
		btnVerifiko.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnVerifiko.setVisible(false);
		btnVerifiko.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				if(txtKodi.getText().equals(String.valueOf(str)))
				{
					frmReset.main(null);
					dispose();
				}

				else
				{
					JOptionPane.showMessageDialog(null,messages.getString("text22"));
				}
			}
		});
		btnVerifiko.setBounds(378, 437, 131, 41);
		contentPane.add(btnVerifiko);
		
		
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(frmForgotPassword.class.getResource("/img2/banner.jpg")));
		label.setBounds(0, 0, 887, 593);
		contentPane.add(label);
	}
}




