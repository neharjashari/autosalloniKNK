package Knk_Package;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Knk_Package.sqlConnect;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

import java.math.*;
import java.security.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JMenuItem;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class frmSignUp extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmri;
	private JTextField txtMbiemri;
	private JTextField txtEmriIPerdoruesit;
	private JTextField txtEmail;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPasswordField txtFjalekalimi;
	private JTextField txtAdresa;
	private JTextField txtTel;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	Locale currentLocale;
	ResourceBundle messages;
	
	//objekti per lidhje
	Connection conn=null;
	//objekti per vendosje te rezultatit
	ResultSet res=null;
	//objekti per query
	PreparedStatement pst=null;
	private JPasswordField txtFjalekalimi2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmSignUp frame = new frmSignUp();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static String MD5(String input)
	{
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);
			while(hashtext.length() < 32)
			{
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
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
	public frmSignUp() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmSignUp.class.getResource("/img2/icons/1.png")));
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

		setIconImage(Toolkit.getDefaultToolkit().getImage(frmSignUp.class.getResource("/img2/camaro_512.png")));
		conn=sqlConnect.connectDB();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 905, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblRegjistrimiIPerdoruesve = new JLabel(messages.getString("lblRegjistrimiIPerdoruesve"));
		lblRegjistrimiIPerdoruesve.setForeground(Color.WHITE);
		lblRegjistrimiIPerdoruesve.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRegjistrimiIPerdoruesve.setBounds(309, 56, 269, 25);
		contentPane.add(lblRegjistrimiIPerdoruesve);
		
		JLabel lblEmri = new JLabel(messages.getString("lblEmri"));
		lblEmri.setForeground(Color.WHITE);
		lblEmri.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmri.setBounds(237, 131, 214, 22);
		contentPane.add(lblEmri);
		
		JLabel lblMbiemri = new JLabel(messages.getString("lblMbiemri"));
		lblMbiemri.setForeground(Color.WHITE);
		lblMbiemri.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMbiemri.setBounds(237, 172, 214, 22);
		contentPane.add(lblMbiemri);
		
		JLabel lblEmriIPerdoruesit = new JLabel(messages.getString("lblEmriIPerdoruesit"));
		lblEmriIPerdoruesit.setForeground(Color.WHITE);
		lblEmriIPerdoruesit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmriIPerdoruesit.setBounds(237, 244, 205, 22);
		contentPane.add(lblEmriIPerdoruesit);
		
		JLabel lblEmail = new JLabel(messages.getString("lblEmail"));
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmail.setBounds(237, 279, 214, 22);
		contentPane.add(lblEmail);
		
		txtEmri = new JTextField();
		txtEmri.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				
				char keyChar = arg0.getKeyChar();
				
				if(!Character.isAlphabetic(keyChar))
				{
					arg0.consume();
					Toolkit.getDefaultToolkit().beep();
				}
				
			}
		});
		txtEmri.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtEmri.setBounds(461, 133, 187, 22);
		contentPane.add(txtEmri);
		txtEmri.setColumns(10);
		
		txtMbiemri = new JTextField();
		txtMbiemri.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				
				char keyChar = arg0.getKeyChar();
				
				if(!Character.isAlphabetic(keyChar))
				{
					arg0.consume();
					Toolkit.getDefaultToolkit().beep();
				}
			}
		});
		txtMbiemri.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtMbiemri.setColumns(10);
		txtMbiemri.setBounds(461, 174, 187, 22);
		contentPane.add(txtMbiemri);
		
		txtEmriIPerdoruesit = new JTextField();
		txtEmriIPerdoruesit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtEmriIPerdoruesit.setColumns(10);
		txtEmriIPerdoruesit.setBounds(461, 246, 187, 22);
		contentPane.add(txtEmriIPerdoruesit);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtEmail.setColumns(10);
		txtEmail.setBounds(461, 281, 187, 22);
		contentPane.add(txtEmail);
		
		JLabel lblFjalkalimi = new JLabel(messages.getString("Fjalekalimi"));
		lblFjalkalimi.setForeground(Color.WHITE);
		lblFjalkalimi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFjalkalimi.setBounds(237, 314, 214, 22);
		contentPane.add(lblFjalkalimi);
		
		txtFjalekalimi = new JPasswordField();
		txtFjalekalimi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtFjalekalimi.setBounds(461, 316, 187, 22);
		
		contentPane.add(txtFjalekalimi);
		
		JLabel lblAdresa = new JLabel(messages.getString("Adresa"));
		lblAdresa.setForeground(Color.WHITE);
		lblAdresa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAdresa.setBounds(237, 421, 214, 22);
		contentPane.add(lblAdresa);
		
		JLabel lblQyteti = new JLabel(messages.getString("Qyteti"));
		lblQyteti.setForeground(Color.WHITE);
		lblQyteti.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblQyteti.setBounds(237, 456, 214, 22);
		contentPane.add(lblQyteti);
		
		txtAdresa = new JTextField();
		txtAdresa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtAdresa.setColumns(10);
		txtAdresa.setBounds(461, 423, 187, 22);
		contentPane.add(txtAdresa);
		
		JComboBox cmbQyteti = new JComboBox();
		cmbQyteti.setModel(new DefaultComboBoxModel(new String[] {"", messages.getString("Qyteti1"), "Prizren", "Ferizaj", "Gjakove", messages.getString("Qyteti2"), "Gjilan", messages.getString("Qyteti3"), "Rahovec", messages.getString("Qyteti4"), "Skenderaj", "Drenas", messages.getString("Qyteti5"),messages.getString("Qyteti6"), "Istog", "Shtime",messages.getString("Qyteti7"), "Kastriot", "Kamenice", "Dragash"}));
		cmbQyteti.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmbQyteti.setBounds(461, 456, 187, 22);
		contentPane.add(cmbQyteti);
		
		JLabel lblNumriITelefonit = new JLabel(messages.getString("lblNumriITelefonit"));
		lblNumriITelefonit.setForeground(Color.WHITE);
		lblNumriITelefonit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNumriITelefonit.setBounds(237, 386, 214, 22);
		contentPane.add(lblNumriITelefonit);
		
		txtTel = new JTextField();
		txtTel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTel.setColumns(10);
		txtTel.setBounds(461, 388, 187, 22);
		txtTel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) 
			{
				//Kur perdoruesi te shenoje shkronja tek fusha Numri i Telefonit ato nuk paraqiten, largohen dhe degjohet nje beep
				char vchar = e.getKeyChar();
				if(!Character.isDigit(vchar))
				{					
					getToolkit().beep();
					e.consume();// per me i largu nese shkrujna shkronja 
				}
			}
		});
		contentPane.add(txtTel);
		
		JLabel lblGjinia = new JLabel(messages.getString("Gjinia"));
		lblGjinia.setForeground(Color.WHITE);
		lblGjinia.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGjinia.setBounds(237, 207, 214, 22);
		contentPane.add(lblGjinia);
		
		JRadioButton rdbtnMashkull = new JRadioButton(messages.getString("rdbtnMashkull"));
		rdbtnMashkull.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnMashkull.setForeground(Color.WHITE);
		buttonGroup_1.add(rdbtnMashkull);
		rdbtnMashkull.setBounds(461, 212, 102, 25);
		rdbtnMashkull.setOpaque(false);
		contentPane.add(rdbtnMashkull);
		
		JRadioButton rdbtnFemer = new JRadioButton(messages.getString("rdbtnFemer"));
		rdbtnFemer.setForeground(Color.WHITE);
		rdbtnFemer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonGroup_1.add(rdbtnFemer);
		rdbtnFemer.setBounds(570, 212, 127, 25);
		rdbtnFemer.setOpaque(false);
		contentPane.add(rdbtnFemer);
		
		JCheckBox chKushtet = new JCheckBox(messages.getString("chKushtet"));
		chKushtet.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chKushtet.setForeground(Color.WHITE);
		chKushtet.setBounds(237, 516, 262, 25);
		contentPane.add(chKushtet);
		chKushtet.setOpaque(false);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(chKushtet, popupMenu);
		
	
		
	
		
		JButton btnRegjistrohu = new JButton(messages.getString("Regjistrohu"));
		
		btnRegjistrohu.addKeyListener(new KeyAdapter() {
							@Override
							public void keyPressed(KeyEvent e) {
								
								if(e.getKeyCode()==KeyEvent.VK_ENTER)
							{
									if(txtEmri.getText().equals("") || txtMbiemri.getText().equals("") || txtEmail.getText().equals("") || txtEmriIPerdoruesit.getText().equals("")
											|| txtTel.getText().equals("") || txtAdresa.getText().equals("")  || (!rdbtnFemer.isSelected() && !rdbtnMashkull.isSelected()) || (cmbQyteti.getSelectedIndex() == -1))
									{
										JOptionPane.showMessageDialog(null,"Ju lutem plotesoni te gjitha fushat perkatese");
										return;
									}
									
										String emri = txtEmri.getText();
										emri = emri.substring(0, 1).toUpperCase() + emri.substring(1);
															
										String mbiemri = txtMbiemri.getText();
										mbiemri = mbiemri.substring(0, 1).toUpperCase() + mbiemri.substring(1);
										
										String gjinia;
										if(rdbtnFemer.isSelected())
										{
											gjinia = "Femer";
										}
										else
										{
											gjinia = "Mashkull";
										}
										
										if(txtFjalekalimi.getText().length()<8)
										{
											JOptionPane.showMessageDialog(null, "Fjalekalimi duhet te jete me i gjate se 8 karaktere");
											return;
										}
										
										
										String emailPattern = "[a-zA-Z0-9._]{1,20}@[a-zA-Z]{1,10}[.]{1}[a-zA-Z]{2,3}";
										Pattern pattern = Pattern.compile(emailPattern);
										Matcher regMatcher = pattern.matcher(txtEmail.getText());
										if(!regMatcher.matches())
										{
										   JOptionPane.showMessageDialog(null,"Formati i email-it nuk eshte ne rregull");
										   txtEmail.setText("");
										   txtEmail.requestFocus();
										   return;
										}
										
										String usernamePattern = "[a-zA-Z] {1,10}[.]{1}[a-zA-Z]{1,10}";
										Pattern patternn = Pattern.compile(usernamePattern);
										Matcher regMatcherr = pattern.matcher(txtEmriIPerdoruesit.getText());
										if(!regMatcher.matches())
										{
										   JOptionPane.showMessageDialog(null,"Formati i email-it nuk eshte ne rregull");
										   txtEmriIPerdoruesit.setText("");
										   txtEmriIPerdoruesit.requestFocus();
										   return;
										}
										
										
										
										String username = txtEmriIPerdoruesit.getText();
										
										String email = txtEmail.getText();
										
										String qyteti;
										qyteti = String.valueOf(cmbQyteti.getSelectedItem());
										
										String fjalekalimi = txtFjalekalimi.getText();
										
										String fjalekalimi2 = txtFjalekalimi2.getText();
										
										if(!fjalekalimi.equals(fjalekalimi2))
										{
											JOptionPane.showMessageDialog(null, "Fjalekalimet e shenuara nuk përputhen!");
											return;
										}
										
										String nrTel = txtTel.getText();
										
			 						 String adresa = txtAdresa.getText();
										
										
										if(!chKushtet.isSelected())
										{
											JOptionPane.showMessageDialog(null, "Per tu regjistruar duhet te pranoni kushtet e perdorimit!");
											return;
										}
										
										try 
										{
											String sql="insert into Perdoruesit(pId, emri, mbiemri, gjinia, username, email, fjalekalimi, nrTel, adresa, qyteti) "
													+ "values (default,'"+emri+"','"+mbiemri+"','"+gjinia+"','"+username+"','"+email+"','"+SHA1(fjalekalimi)+"','"+nrTel+"','"+adresa+"','"+qyteti+"');";
											pst=conn.prepareStatement(sql);
											pst.execute();
											pst.close();
											
											JOptionPane.showMessageDialog(null, "Ne email-in e juaj do te dergohet kodi i aktivizimit!");
											frmLogin.main(null);
											dispose();
											
										}
										catch (Exception ex) 
										{
											JOptionPane.showMessageDialog(null, "Gabim gjate regjistrimit te perdoruesit!	"+ex.getMessage());
										}
										
										
									
								}
							}
						});
		
		btnRegjistrohu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(txtEmri.getText().equals("") || txtMbiemri.getText().equals("") || txtEmail.getText().equals("") || txtEmriIPerdoruesit.getText().equals("")
						|| txtTel.getText().equals("") || txtAdresa.getText().equals("")  || (!rdbtnFemer.isSelected() && !rdbtnMashkull.isSelected()) || (cmbQyteti.getSelectedIndex() == -1))
				{
					JOptionPane.showMessageDialog(null,messages.getString("text6"));
					return;
				}
				else
				{
					String emri = txtEmri.getText();
					emri = emri.substring(0, 1).toUpperCase() + emri.substring(1);
										
					String mbiemri = txtMbiemri.getText();
					mbiemri = mbiemri.substring(0, 1).toUpperCase() + mbiemri.substring(1);
					
					String gjinia;
					if(rdbtnFemer.isSelected())
					{
						gjinia = "Femer";
					}
					else
					{
						gjinia = "Mashkull";
					}
					
					if(txtFjalekalimi.getText().length()<8)
					{
						JOptionPane.showMessageDialog(null, messages.getString("text42"));
						return;
					}
					
					
					String emailPattern = "[a-zA-Z0-9._]{1,20}@[a-zA-Z]{1,10}[.]{1}[a-zA-Z]{2,3}";
					Pattern pattern = Pattern.compile(emailPattern);
					Matcher regMatcher = pattern.matcher(txtEmail.getText());
					if(!regMatcher.matches())
					{
					   JOptionPane.showMessageDialog(null,messages.getString("text43"));
					   txtEmail.setText("");
					   txtEmail.requestFocus();
					   return;
					}
					
					String usernamePattern = "[a-zA-Z] {1,10}[.]{1}[a-zA-Z]{1,10}";
					Pattern patternn = Pattern.compile(usernamePattern);
					Matcher regMatcherr = pattern.matcher(txtEmriIPerdoruesit.getText());
					if(!regMatcher.matches())
					{
					   JOptionPane.showMessageDialog(null,messages.getString("text43"));
					   txtEmriIPerdoruesit.setText("");
					   txtEmriIPerdoruesit.requestFocus();
					   return;
					}
					
					
					
					String username = txtEmriIPerdoruesit.getText();
					
					String email = txtEmail.getText();
					
					String qyteti;
					qyteti = String.valueOf(cmbQyteti.getSelectedItem());
					
					String fjalekalimi = txtFjalekalimi.getText();
					
					String fjalekalimi2 = txtFjalekalimi2.getText();
					
					if(!fjalekalimi.equals(fjalekalimi2))
					{
						JOptionPane.showMessageDialog(null, messages.getString("text44"));
						return;
					}
					
					String nrTel = txtTel.getText();
					
					String adresa = txtAdresa.getText();
					
					
					if(!chKushtet.isSelected())
					{
						JOptionPane.showMessageDialog(null, messages.getString("text15"));
						return;
					}
					
					try 
					{
						String sql="insert into Perdoruesit(pId, emri, mbiemri, gjinia, username, email, fjalekalimi, nrTel, adresa, qyteti) "
								+ "values (default,'"+emri+"','"+mbiemri+"','"+gjinia+"','"+username+"','"+email+"','"+SHA1(fjalekalimi)+"','"+nrTel+"','"+adresa+"','"+qyteti+"');";
						pst=conn.prepareStatement(sql);
						pst.execute();
						pst.close();
						
						JOptionPane.showMessageDialog(null, messages.getString("text17"));
						frmLogin.main(null);
						dispose();
						
					}
					catch (Exception e) 
					{
						JOptionPane.showMessageDialog(null, messages.getString("text16")+e.getMessage());
					}
					
					
				}
			}
		});
		btnRegjistrohu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnRegjistrohu.setBounds(570, 506, 127, 40);
		contentPane.add(btnRegjistrohu);
		
		JLabel lblShkruajPrapFjalekalimin = new JLabel(messages.getString("text45"));
		lblShkruajPrapFjalekalimin.setForeground(Color.WHITE);
		lblShkruajPrapFjalekalimin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblShkruajPrapFjalekalimin.setBounds(237, 349, 214, 22);
		contentPane.add(lblShkruajPrapFjalekalimin);
		
		txtFjalekalimi2 = new JPasswordField();
		txtFjalekalimi2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtFjalekalimi2.setBounds(461, 351, 187, 22);
		contentPane.add(txtFjalekalimi2);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(frmSignUp.class.getResource("/img2/banner.jpg")));
		label.setBounds(0, 0, 887, 593);
		contentPane.add(label);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}