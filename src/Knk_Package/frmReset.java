
package Knk_Package;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;

public class frmReset extends JFrame {

	private JPanel contentPane;
	private JLabel label;
	private JPasswordField txtFjalekalimi;
	private JPasswordField txtKonfirmo;
	
	//objekti per lidhje
	Connection conn=null;
	//objekti per vendosje te rezultatit
	ResultSet res=null;
	//objekti per query
	PreparedStatement pst=null;
	Locale currentLocale;
	ResourceBundle messages;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmReset frame = new frmReset();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public String getEmail()
	{
		return frmForgotPassword.getEmail();
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
	public frmReset() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmReset.class.getResource("/img2/icons/1.png")));
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
		
		JLabel lblFjalekalimiIRi = new JLabel(messages.getString("text31"));
		lblFjalekalimiIRi.setForeground(Color.WHITE);
		lblFjalekalimiIRi.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblFjalekalimiIRi.setBounds(337, 98, 212, 25);
		contentPane.add(lblFjalekalimiIRi);
		
		JLabel lblKonfirmoniFjalekalimin = new JLabel(messages.getString("text32"));
		lblKonfirmoniFjalekalimin.setForeground(Color.WHITE);
		lblKonfirmoniFjalekalimin.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblKonfirmoniFjalekalimin.setBounds(337, 262, 212, 25);
		contentPane.add(lblKonfirmoniFjalekalimin);
		
		txtFjalekalimi = new JPasswordField();
		txtFjalekalimi.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtFjalekalimi.setBounds(341, 151, 205, 32);
		contentPane.add(txtFjalekalimi);
		
		txtKonfirmo = new JPasswordField();
		txtKonfirmo.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtKonfirmo.setBounds(337, 313, 212, 32);
		contentPane.add(txtKonfirmo);
		
		JButton btnRuaj = new JButton(messages.getString("text36"));
		btnRuaj.addKeyListener(new KeyAdapter() {
							@Override
							public void keyPressed(KeyEvent e) {
								
							if(e.getKeyCode()==KeyEvent.VK_ENTER)
								{
									try 
									{
										String newPassword = txtFjalekalimi.getText();
										String passKonfirmo = txtKonfirmo.getText();
										String email = getEmail();
										
									if(newPassword.length()<8)
									{
										JOptionPane.showMessageDialog(null, messages.getString("text42"));
											return;
										}
										
										if(!newPassword.equals(passKonfirmo))
								{
											JOptionPane.showMessageDialog(null, messages.getString("text33"));
										txtFjalekalimi.setText("");
											txtKonfirmo.setText("");
											txtFjalekalimi.requestFocus();
											return;
										}
									
									String query="UPDATE perdoruesit SET fjalekalimi = '"+SHA1(newPassword)+"' WHERE email = '"+email+"'";
										pst=conn.prepareStatement(query);
									pst.execute();
										JOptionPane.showMessageDialog(null, messages.getString("text34"));
										pst.close();
										
										frmLogin.main(null);
									dispose();
									}
									catch (Exception ex) {
										JOptionPane.showMessageDialog(null, messages.getString("text35") + ex.getMessage());
									}
								}
							}
						});
		
		
		
		
		btnRuaj.setFont(new Font("Dialog", Font.PLAIN, 20));
		btnRuaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					String newPassword = txtFjalekalimi.getText();
					String passKonfirmo = txtKonfirmo.getText();
					String email = getEmail();
					if(newPassword.length()<8)
												{
												JOptionPane.showMessageDialog(null,messages.getString("text42") );
												return;
												}
					if(!newPassword.equals(passKonfirmo))
					{
						JOptionPane.showMessageDialog(null, messages.getString("text33"));
						txtFjalekalimi.setText("");
						txtKonfirmo.setText("");
						txtFjalekalimi.requestFocus();
						return;
					}
					
					String query="UPDATE perdoruesit SET fjalekalimi = '"+SHA1(newPassword)+"' WHERE email = '"+email+"'";
					pst=conn.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null, messages.getString("text34"));
					pst.close();
					
					frmLogin.main(null);
					dispose();
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, messages.getString("text35") + ex.getMessage());
				}
			}
		});
		btnRuaj.setBounds(377, 434, 132, 35);
		contentPane.add(btnRuaj);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(frmReset.class.getResource("/img2/banner.jpg")));
		label.setBounds(0, 0, 887, 593);
		contentPane.add(label);
	}
}
