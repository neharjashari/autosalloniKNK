package Knk_Package;

import java.awt.Color;
import java.awt.BorderLayout;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
	

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Timer;
import java.util.TimerTask;

import java.awt.Toolkit;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.Cursor;



public class frmLogin extends JFrame {

		private JPanel contentPane;
		private static JTextField txtPerdoruesi;
		//objekti per lidhje
		Connection conn=null;
		//objekti per vendosje te rezultatit
		ResultSet res=null;
		//objekti per query
		PreparedStatement pst=null;
		private JPasswordField txtFjalekalimi;
		Locale currentLocale;
		ResourceBundle messages;
		JLabel lblPerdoruesi;
		JLabel lblFjalekalimi;
		JLabel lblRegjistrohu;
		JLabel lblHarro;
		JButton btnKyçu;
		private int tries = 3;

		public final static String getUser() {
			return txtPerdoruesi.getText();
		}

		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmLogin frame = new frmLogin();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null); // frame eshte i pozicionuar ne qender 
				} catch (Exception e) {
					e.printStackTrace();
				}
				
		
			}
		});
	}
	
	
	public static String SHA1(String input) throws NoSuchAlgorithmException
	{
		try {
			MessageDigest mDigest = MessageDigest.getInstance("SHA1");
			byte[] result = mDigest.digest(input.getBytes());
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i<result.length; i++)
			{
				sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	UserPreferences userPreferences = new UserPreferences();
	/**
	 * Create the frame.
	 */
	public frmLogin() {
		setResizable(false);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmBlej.class.getResource("/img2/icons/1.png")));
	
		
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
		ButtonGroup group= new ButtonGroup();
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(240, 261, 406, 301);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblPerdoruesi = new JLabel("P\u00EBrdoruesi:");
		
		lblPerdoruesi.setForeground(Color.WHITE);
		lblPerdoruesi.setBounds(24, 64, 88, 22);
		panel.add(lblPerdoruesi);
		lblPerdoruesi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		lblFjalekalimi = new JLabel("Fjal\u00EBkalimi:");
		lblFjalekalimi.setForeground(Color.WHITE);
		lblFjalekalimi.setBounds(25, 129, 87, 22);
		panel.add(lblFjalekalimi);
		lblFjalekalimi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		lblHarro = new JLabel("Keni harruar fjal\u00EBkalimin?");
		
		JRadioButton rdbAL = new JRadioButton("Shqip");
		rdbAL.setBounds(267, 250, 65, 23);
		panel.add(rdbAL);
		rdbAL.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbAL.setForeground(Color.WHITE);
		rdbAL.setOpaque(false);
		rdbAL.setSelected(true);
		rdbAL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userPreferences.setLang("al");
				currentLocale = new Locale(new String("al"), new String("AL"));
				try {
					messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
				} catch (MissingResourceException k) {
					k.printStackTrace();
				}
				
				lblPerdoruesi.setText(messages.getString("Perdoruesi"));
				lblFjalekalimi.setText(messages.getString("Fjalekalimi"));
				lblRegjistrohu.setText(messages.getString("Regjistrohu"));
				lblHarro.setText(messages.getString("text51"));
				btnKyçu.setText(messages.getString("text52"));
			}
		});
		group.add(rdbAL);
		
		JRadioButton rdbEN = new JRadioButton("English");
		rdbEN.setBounds(267, 276, 68, 23);
		panel.add(rdbEN);
		rdbEN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbEN.setForeground(Color.WHITE);
		rdbEN.setOpaque(false);
		rdbEN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				userPreferences.setLang("en");
				currentLocale = new Locale(new String("en"), new String("US"));
				try {
					messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
				} catch (MissingResourceException k) {
					k.printStackTrace();
				}
				lblPerdoruesi.setText(messages.getString("Perdoruesi"));
				lblFjalekalimi.setText(messages.getString("Fjalekalimi"));
				lblRegjistrohu.setText(messages.getString("Regjistrohu"));
				lblHarro.setText(messages.getString("text51"));
				btnKyçu.setText(messages.getString("text52"));
				
			}
		});
		group.add(rdbEN);
		
		txtPerdoruesi = new JTextField();
		txtPerdoruesi.setBackground(UIManager.getColor("Button.background"));
		txtPerdoruesi.setBounds(165, 61, 218, 29);
		panel.add(txtPerdoruesi);
		txtPerdoruesi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtPerdoruesi.setColumns(10);
		
		btnKyçu = new JButton("Kyçu");
		btnKyçu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				try {
					
					if(tries <= 0) 
					{
				     JOptionPane.showMessageDialog(null, messages.getString("text47"));
					 return;
					}
					
					String perdoruesi = txtPerdoruesi.getText();
					String fjalekalimi = txtFjalekalimi.getText();
					
					if(perdoruesi.equals("") || fjalekalimi.equals(""))
					{
					   JOptionPane.showMessageDialog(null, messages.getString("text1"));
					   return;
					}
					
					String fjalekalimiHash = SHA1(fjalekalimi);
						
						pst = conn.prepareStatement("SELECT * FROM perdoruesit WHERE username=? and fjalekalimi=?");
					    pst.setString(1, perdoruesi);
					    pst.setString(2, fjalekalimiHash);
					    res = pst.executeQuery();
					    
					    String username;
					    String password;
					    				    
					    if(res.next())
					    {
					    	username = res.getString("username");
						    password = res.getString("fjalekalimi");
						    
						    if(username.equals(perdoruesi) && password.equals(fjalekalimiHash))
							{
						    	frmMain.main(null);
								dispose();
							}
						    else
						    {
						    	JOptionPane.showMessageDialog(null, messages.getString("text2"));
						    	txtPerdoruesi.setText("");
						    	txtFjalekalimi.setText("");
						    	txtPerdoruesi.requestFocus();
						    }
						    
					    }	
					    else
				            {
		                    	JOptionPane.showMessageDialog(null, messages.getString("text2"));
								txtPerdoruesi.setText("");
					    		txtFjalekalimi.setText("");
					    		txtPerdoruesi.requestFocus();
					    		tries--;
						    }
			   
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, messages.getString("text3") +ex.getMessage());
				}
			}
		});
		btnKyçu.setIcon(null);
		btnKyçu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					if(tries <= 0) {
										JOptionPane.showMessageDialog(null,  messages.getString("text47") );
										return;
									}
					
					String perdoruesi = txtPerdoruesi.getText();
					String fjalekalimi = txtFjalekalimi.getText();
					
					if(perdoruesi.equals("") || fjalekalimi.equals(""))
				    {
				    	JOptionPane.showMessageDialog(null, messages.getString("text14"));
				    	return;
				    }
				    
					String fjalekalimiHash = SHA1(fjalekalimi);
						
						pst = conn.prepareStatement("SELECT * FROM perdoruesit WHERE username=? and fjalekalimi=?");
					    pst.setString(1, perdoruesi);
					    pst.setString(2, fjalekalimiHash);
					    res = pst.executeQuery();
					    
					    String username;
					    String password;
					    				    
					    if(res.next())
					    {
					    	username = res.getString("username");
						    password = res.getString("fjalekalimi");
						    
						    if(username.equals(perdoruesi) && password.equals(fjalekalimiHash))
							{
						    	frmMain.main(null);
								dispose();
							}
						    else
						    {
						    	JOptionPane.showMessageDialog(null, messages.getString("text2"));
						    	txtPerdoruesi.setText("");
						    	txtFjalekalimi.setText("");
						    	txtPerdoruesi.requestFocus();
						    }
						    
					    }	
					    else
					    	  {
					    			JOptionPane.showMessageDialog(null,messages.getString("text2") );
					    			txtPerdoruesi.setText("");
					    			txtFjalekalimi.setText("");
					    			txtPerdoruesi.requestFocus();
					    				tries--;
					    		  }
				      		    }
				catch (Exception e) {
					JOptionPane.showMessageDialog(null, messages.getString("text3")+e.getMessage());
				}
				
				
			}
		});
		btnKyçu.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnKyçu.setBounds(248, 196, 135, 32);
		
		btnKyçu.addKeyListener(new KeyAdapter() {
	/*duhet per anglisht*/						@Override
							public void keyPressed(KeyEvent eventi)
							{
								if(eventi.getKeyCode()==KeyEvent.VK_ENTER)
								{					
									try {
										
										if(tries <= 0) {
											JOptionPane.showMessageDialog(null, messages.getString("text47"));
											
											return;
										}
										
										String perdoruesi = txtPerdoruesi.getText();
										String fjalekalimi = txtFjalekalimi.getText();
										
										if(perdoruesi.equals("") || fjalekalimi.equals(""))
										{
										   JOptionPane.showMessageDialog(null, messages.getString("text1"));
										   return;
										}
										
										String fjalekalimiHash = SHA1(fjalekalimi);
											
											pst = conn.prepareStatement("SELECT * FROM perdoruesit WHERE username=? and fjalekalimi=?");
										    pst.setString(1, perdoruesi);
										    pst.setString(2, fjalekalimiHash);
										    res = pst.executeQuery();
										    
										    String username;
										    String password;
										    				    
										    if(res.next())
										    {
										    	username = res.getString("username");
											    password = res.getString("fjalekalimi");
											    
											    if(username.equals(perdoruesi) && password.equals(fjalekalimiHash))
												{
											    	frmMain.main(null);
													dispose();
												}
											    else
											    {
											    	JOptionPane.showMessageDialog(null, messages.getString("text2"));
										    	txtPerdoruesi.setText("");
											    	txtFjalekalimi.setText("");
											    	txtPerdoruesi.requestFocus();
											    }
											    
										    }
										    else
										    {
										    	JOptionPane.showMessageDialog(null, messages.getString("text2"));
										    	txtPerdoruesi.setText("");
										    	txtFjalekalimi.setText("");
										    	txtPerdoruesi.requestFocus();
										    	tries--;
										    }
				
										}
								
									catch (Exception ex) 
									{
										JOptionPane.showMessageDialog(null, messages.getString("text3")+ex.getMessage());
									}
									
								}
								
							}
						});
		panel.add(btnKyçu);
		
		lblRegjistrohu = new JLabel("Regjistrohu");
		lblRegjistrohu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblRegjistrohu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRegjistrohu.setForeground(Color.WHITE);
		lblRegjistrohu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				frmSignUp.main(null);
				dispose();
				
			}
		});
		lblRegjistrohu.setBounds(24, 236, 88, 22);
		panel.add(lblRegjistrohu);
		
		txtFjalekalimi = new JPasswordField();
		txtFjalekalimi.setBackground(UIManager.getColor("Button.background"));
		txtFjalekalimi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent eventi)
			{
				if(eventi.getKeyCode()==KeyEvent.VK_ENTER)
				{					
					try {
						
		  				if(tries <= 0) {
											JOptionPane.showMessageDialog(null, messages.getString("text47"));
											return;
										}
				
						
						String perdoruesi = txtPerdoruesi.getText();
						String fjalekalimi = txtFjalekalimi.getText();
						
						if(perdoruesi.equals("") || fjalekalimi.equals(""))
						{
						   JOptionPane.showMessageDialog(null, messages.getString("text1"));
						   return;
						}
						
						String fjalekalimiHash = SHA1(fjalekalimi);
							
							pst = conn.prepareStatement("SELECT * FROM perdoruesit WHERE username=? and fjalekalimi=?");
						    pst.setString(1, perdoruesi);
						    pst.setString(2, fjalekalimiHash);
						    res = pst.executeQuery();
						    
						    String username;
						    String password;
						    				    
						    if(res.next())
						    {
						    	username = res.getString("username");
							    password = res.getString("fjalekalimi");
							    
							    if(username.equals(perdoruesi) && password.equals(fjalekalimiHash))
								{
							    	frmMain.main(null);
									dispose();
								}
							    else
							    {
							    	JOptionPane.showMessageDialog(null, messages.getString("text2"));
							    	txtPerdoruesi.setText("");
							    	txtFjalekalimi.setText("");
							    	txtPerdoruesi.requestFocus();
							    }
						    }
							    else
							    	   {
							    	   	JOptionPane.showMessageDialog(null, messages.getString("text2"));
							    	  	txtPerdoruesi.setText("");
							    	   	txtFjalekalimi.setText("");
							    	   	txtPerdoruesi.requestFocus();
							    	   	tries--;
							    	    }
							    	

						    }		    
						
				
					catch (Exception ex) 
					{
						JOptionPane.showMessageDialog(null, messages.getString("text3")+ex.getMessage());
					}
					
				}
				
			}
		});
		txtFjalekalimi.setBounds(165, 131, 218, 29);
		panel.add(txtFjalekalimi);
		
	
		lblHarro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblHarro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				frmForgotPassword.main(null);
				dispose();
			}
		});
		lblHarro.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHarro.setForeground(Color.WHITE);
		lblHarro.setBounds(10, 278, 160, 19);
		panel.add(lblHarro);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(frmLogin.class.getResource("/img2/albania_ico.png")));
		lblNewLabel.setBounds(341, 254, 30, 19);
		panel.add(lblNewLabel);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(frmLogin.class.getResource("/img2/england_ico.png")));
		label_3.setBounds(341, 276, 30, 20);
		panel.add(label_3);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(frmLogin.class.getResource("/img2/background-nice-2.jpg")));
		label_2.setBounds(0, 0, 406, 301);
		panel.add(label_2);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(frmLogin.class.getResource("/img2/Logo3.png")));
		label.setBounds(81, 24, 724, 198);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(frmLogin.class.getResource("/img2/banner.jpg")));
		label_1.setBounds(0, 0, 899, 611);
		contentPane.add(label_1);
	}
}