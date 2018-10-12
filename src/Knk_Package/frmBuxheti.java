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
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;

public class frmBuxheti extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();

	//objekti per lidhje
	Connection conn=null;
	//objekti per vendosje te rezultatit
	ResultSet res=null;
	//objekti per query
	PreparedStatement pst=null;
	
	JLabel lblBuxheti = new JLabel("");
	JLabel lblBuxheti1 = new JLabel("");
	private JTextField txtVleraKonvertuar;
	Locale currentLocale;
	ResourceBundle messages;
	
	
	public String merrBuxhetin()
	{
		return frmShitjet.getBuxhet();
	}

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmBuxheti frame = new frmBuxheti();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	UserPreferences userPreferences = new UserPreferences();
	/**
	 * Create the frame.
	 */
	public frmBuxheti() {
		setResizable(false);
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

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				
				lblBuxheti1.setText(merrBuxhetin());
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmBuxheti.class.getResource("/img2/icons/1.png")));
		conn=sqlConnect.connectDB();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 905, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1214, 26);
		contentPane.add(menuBar);
		
		JMenu menu = new JMenu(messages.getString("Blerjet"));
		menuBar.add(menu);
		JMenuItem menuItem = new JMenuItem(messages.getString("ShikoBlerjet"));
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD0, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmBlej.main(null);
				dispose();
			}
		});
		menu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem(messages.getString("ShtoBlerje"));
		menuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD1, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmShikoBlerjet.main(null);
				dispose();
			}
		});
		menu.add(menuItem_1);
		
		JMenu menu_1 = new JMenu(messages.getString("Shitjet"));
		menuBar.add(menu_1);
		
		JMenuItem menuItem_2 = new JMenuItem(messages.getString("ShikoShitjet"));
		menuItem_2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD2, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmShikoShitjet.main(null);
				dispose();
			}
		});
		menu_1.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem(messages.getString("ShitMakina"));
		menuItem_3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD3, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmShitjet.main(null);
				dispose();
			}
		});
		menu_1.add(menuItem_3);
		
		JMenu menu_2 = new JMenu(messages.getString("Administrator"));
		menuBar.add(menu_2);
		
		JMenuItem menuItem_4 = new JMenuItem(messages.getString("Puntort"));
		menuItem_4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD4, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmPunetoret.main(null);
				dispose();
			}
		});
		menu_2.add(menuItem_4);
		
		JMenuItem menuItem_5 = new JMenuItem(messages.getString("Buxheti"));
		menuItem_5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD5, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmBuxheti.main(null);
				dispose();
			}
		});
		menu_2.add(menuItem_5);
		
		JMenu menu_3 = new JMenu(messages.getString("Prdoruesi"));
		menuBar.add(menu_3);
		
		JMenuItem menuItem_7 = new JMenuItem(messages.getString("tmkyu"));
		menuItem_7.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD6, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		menuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmLogin.main(null);
				dispose();
			}
		});
		menu_3.add(menuItem_7);
		
		JMenuItem menuItem_8 = new JMenuItem(messages.getString("DilNgaProgrami"));
		menuItem_8.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD7, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		menuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		menu_3.add(menuItem_8);
		
		JMenu menu_4 = new JMenu(messages.getString("Informata"));
		menuBar.add(menu_4);
		
		JMenuItem menuItem_9 = new JMenuItem(messages.getString("Ndihma"));
		menuItem_9.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		menuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try 
				{
					if(messages.getString("Informata").equals("Informata"))
					{
					File f = new File("C:\\Users\\Endrin\\eclipse-workspace\\Projekti KNK\\src\\programFiles\\Informata.pdf");
			        Desktop.getDesktop().open(f);
					}
					else
					{
						File f = new File("C:\\Users\\Endrin\\eclipse-workspace\\Projekti KNK\\src\\programFiles\\INFORMATION.pdf");
				        Desktop.getDesktop().open(f);
					}
				}
				catch (Exception e) 
				{
					JOptionPane.showMessageDialog(null, messages.getString("text18") + e.getMessage());
				}
				
			}
		});
		menu_4.add(menuItem_9);
		
		JMenuItem menuItem_10 = new JMenuItem(messages.getString("Udhezues"));
		menuItem_10.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		menuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try 
				{
					if(messages.getString("Udhezues").equals("Udh\u00EBzues"))
					{
					File f = new File("C:\\Users\\Endrin\\eclipse-workspace\\Projekti KNK\\src\\programFiles\\ManualiPerdorimit.pdf");
			        Desktop.getDesktop().open(f);
					}
					else
					{
						File f = new File("C:\\Users\\Endrin\\eclipse-workspace\\Projekti KNK\\src\\programFiles\\INSTRUCTION-MANUAL.pdf");
				        Desktop.getDesktop().open(f);
					}
				} 
				catch (Exception e1) 
				{
					JOptionPane.showMessageDialog(null, messages.getString("text18") + e1.getMessage());
				}
			}
		});
		menu_4.add(menuItem_10);
		
		
		lblBuxheti1.setForeground(Color.CYAN);
		lblBuxheti1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblBuxheti1.setBounds(489, 210, 214, 29);
		contentPane.add(lblBuxheti1);
		
		JLabel lblBuxhetiIKompanis = new JLabel(messages.getString("buxhetiKompanise"));
		lblBuxhetiIKompanis.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblBuxhetiIKompanis.setForeground(Color.WHITE);
		lblBuxhetiIKompanis.setBounds(331, 68, 225, 31);
		contentPane.add(lblBuxhetiIKompanis);
		
		JLabel lblShumaEParave = new JLabel(messages.getString("shParave"));
		lblShumaEParave.setForeground(Color.WHITE);
		lblShumaEParave.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblShumaEParave.setBounds(180, 210, 214, 29);
		contentPane.add(lblShumaEParave);
		
		JLabel label_1 = new JLabel("\u20AC");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		label_1.setBounds(715, 212, 70, 24);
		contentPane.add(label_1);
		
		JLabel lblKonvertoShumnN = new JLabel(messages.getString("konverto"));
		lblKonvertoShumnN.setForeground(Color.WHITE);
		lblKonvertoShumnN.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblKonvertoShumnN.setBounds(150, 366, 228, 29);
		contentPane.add(lblKonvertoShumnN);
		
		JComboBox cmbCurrency = new JComboBox();
		cmbCurrency.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmbCurrency.setModel(new DefaultComboBoxModel(new String[] {"USD", "GBP", "LEK", "CHF"}));
		cmbCurrency.setBounds(422, 366, 114, 29);
		contentPane.add(cmbCurrency);
		
		txtVleraKonvertuar = new JTextField();
		txtVleraKonvertuar.setEditable(false);
		txtVleraKonvertuar.setForeground(Color.BLACK);
		txtVleraKonvertuar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtVleraKonvertuar.setEnabled(false);
		txtVleraKonvertuar.setBounds(563, 366, 208, 29);
		contentPane.add(txtVleraKonvertuar);
		txtVleraKonvertuar.setColumns(10);
		
		JButton btnKonverto = new JButton(messages.getString("konv"));
		btnKonverto.addKeyListener(new KeyAdapter() {
							@Override
							public void keyPressed(KeyEvent e) {
								
								if(e.getKeyCode()==KeyEvent.VK_ENTER)
								{
									if(cmbCurrency.getSelectedIndex()==0)
									{
										//1EUR - 1.17 USD
										double _input = Double.parseDouble(lblBuxheti1.getText());
										double _output = _input * 1.17;
										txtVleraKonvertuar.setText(String.valueOf(_output));
									}
									else if(cmbCurrency.getSelectedIndex()==1)
									{
										//1EUR - 0.88 GBP
										double _input = Double.parseDouble(lblBuxheti1.getText());
										double _output = _input * 0.88;
										txtVleraKonvertuar.setText(String.valueOf(_output));
									}
									else if(cmbCurrency.getSelectedIndex()==2)
									{
										//1EUR - 126.47 LEK
										double _input = Double.parseDouble(lblBuxheti1.getText());
										double _output = _input * 126.47;
										txtVleraKonvertuar.setText(String.valueOf(_output));
									}
									else
									{
										//1EUR - 119.33 CHF
										double _input = Double.parseDouble(lblBuxheti1.getText());
										double _output = _input * 119.33;
										txtVleraKonvertuar.setText(String.valueOf(_output));
			   		                }
									
									
								}
			 			}
						});
		
		btnKonverto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(cmbCurrency.getSelectedIndex()==0)
				{
					//1EUR - 1.17 USD
					double _input = Double.parseDouble(lblBuxheti1.getText());
					double _output = _input * 1.17;
					txtVleraKonvertuar.setText(String.valueOf(_output));
				}
				else if(cmbCurrency.getSelectedIndex()==1)
				{
					//1EUR - 0.88 GBP
					double _input = Double.parseDouble(lblBuxheti1.getText());
					double _output = _input * 0.88;
					txtVleraKonvertuar.setText(String.valueOf(_output));
				}
				else if(cmbCurrency.getSelectedIndex()==2)
				{
					//1EUR - 126.47 LEK
					double _input = Double.parseDouble(lblBuxheti1.getText());
					double _output = _input * 126.47;
					txtVleraKonvertuar.setText(String.valueOf(_output));
				}
				else
				{
					//1EUR - 119.33 CHF
					double _input = Double.parseDouble(lblBuxheti1.getText());
					double _output = _input * 119.33;
					txtVleraKonvertuar.setText(String.valueOf(_output));
				}
				
				
				
			}
		});
		btnKonverto.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnKonverto.setBounds(377, 448, 132, 38);
		contentPane.add(btnKonverto);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(frmSignUp.class.getResource("/img2/banner.jpg")));
		label.setBounds(0, 0, 887, 593);
		contentPane.add(label);
	}
}
