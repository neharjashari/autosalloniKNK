package Knk_Package;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.omg.CORBA.portable.InputStream;

import Knk_Package.sqlConnect;
import net.proteanit.sql.DbUtils;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.Format;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.HashSet;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;

import java.awt.FlowLayout;
import java.text.DateFormat;
import java.text.Format;
import java.util.Date;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.beans.PropertyChangeEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.Toolkit;

public class frmBlej extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private int id;
	private double buxheti;
	
	
	//objekti per lidhje
	Connection conn=null;
	//objekti per vendosje te rezultatit
	ResultSet res=null;
	//objekti per query
	PreparedStatement pst=null;
	private JTextField txtBrendi;
	private JTextField txtModeli;
	private JTextField txtMotori;
	private JTable tblVeturat2;
	private JTextField txtCmimi;
	private JTextField txtVitiProdhimit;
	private JTextField txtSearch;
	Locale currentLocale;
	ResourceBundle messages;
	
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmBlej frame = new frmBlej();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setSize(1200, 735);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	public void updateTable2()
	{
		try 
		{
			String sql="select vid as 'ID', brendi as 'Brendi', modeli as 'Modeli', cmimi as 'Çmimi' from veturat";
			pst=conn.prepareStatement(sql);
			//objekti qe mundeson ekzekutimin e querit dhe vendosjen e rez ne objektin res.
			res=pst.executeQuery();
			
			tblVeturat2.setModel(DbUtils.resultSetToTableModel(res));
			
			pst.close();
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, messages.getString("text5")+e.getMessage());
		}
	}
	
	
	UserPreferences userPreferences = new UserPreferences();
	/**
	 * Create the frame.
	 */
	public frmBlej() {
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

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				updateTable2();
			}
		});
		conn=sqlConnect.connectDB();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1232, 883);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnBlerjet = new JMenu(messages.getString("Blerjet"));
		menuBar.add(mnBlerjet);
		
		JMenuItem mntmShikoBlerjet = new JMenuItem(messages.getString("ShikoBlerjet"));
		mntmShikoBlerjet.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD0, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmShikoBlerjet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmShikoBlerjet.main(null);
				dispose();
			}
		});
		mnBlerjet.add(mntmShikoBlerjet);
		
		JMenuItem mntmShtoBlerje = new JMenuItem( messages.getString("ShtoBlerje"));
		mntmShtoBlerje.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD1, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnBlerjet.add(mntmShtoBlerje);
		
		JMenu mnShitjet = new JMenu(messages.getString("Shitjet"));
		menuBar.add(mnShitjet);
		
		JMenuItem mntmShikoShitjet = new JMenuItem(messages.getString("ShikoShitjet"));
		mntmShikoShitjet.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD2, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmShikoShitjet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmShikoShitjet.main(null);
				dispose();
			}
		});
		mnShitjet.add(mntmShikoShitjet);
		
		JMenuItem mntmShitMakina = new JMenuItem(messages.getString("ShitMakina"));
		mntmShitMakina.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD3, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmShitMakina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmShitjet.main(null);
				dispose();
			}
		});
		mnShitjet.add(mntmShitMakina);
		
		JMenu mnAdministratori = new JMenu(messages.getString("Administrator"));
		mnAdministratori.setEnabled(false);
		menuBar.add(mnAdministratori);
		
		JMenuItem mntmPuntort = new JMenuItem(messages.getString("Puntort"));
		mntmPuntort.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD4, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmPuntort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmPunetoret.main(null);
				dispose();
			}
		});
		mnAdministratori.add(mntmPuntort);
		
		JMenuItem mntmBuxheti = new JMenuItem(messages.getString("Buxheti"));
		mntmBuxheti.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD5, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmBuxheti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmBuxheti.main(null);
				dispose();
			}
		});
		mnAdministratori.add(mntmBuxheti);
		
		JMenu mnPrdoruesi = new JMenu(messages.getString("Prdoruesi"));
		menuBar.add(mnPrdoruesi);
		
		JMenuItem mntmkyu = new JMenuItem(messages.getString("tmkyu"));
		mntmkyu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD6, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmkyu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
				frmLogin.main(null);
			}
		});
		
		
		mnPrdoruesi.add(mntmkyu);
		
		JMenuItem mntmDilNgaProgrami = new JMenuItem(messages.getString("DilNgaProgrami"));
		mntmDilNgaProgrami.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD7, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmDilNgaProgrami.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		mnPrdoruesi.add(mntmDilNgaProgrami);
		
		JMenu mnNdihma = new JMenu(messages.getString("Ndihma"));
		menuBar.add(mnNdihma);
		
		JMenuItem mntmInformata = new JMenuItem(messages.getString("Informata"));
		mntmInformata.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mntmInformata.addActionListener(new ActionListener() {
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
		mnNdihma.add(mntmInformata);
		
		JMenuItem mntmUdhezues = new JMenuItem( messages.getString("Udhezues"));
		mntmUdhezues.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		mntmUdhezues.addActionListener(new ActionListener() {
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
		mnNdihma.add(mntmUdhezues);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 1214, 810);
		contentPane.add(panel);
		
		JLabel lblBrendi = new JLabel(messages.getString("Brendi"));
		lblBrendi.setForeground(Color.WHITE);
		lblBrendi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBrendi.setBounds(34, 115, 162, 22);
		panel.add(lblBrendi);
		
		JLabel lblModeli = new JLabel(messages.getString("Modeli"));
		lblModeli.setForeground(Color.WHITE);
		lblModeli.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblModeli.setBounds(34, 162, 162, 22);
		panel.add(lblModeli);
		
		JLabel lblMotori = new JLabel(messages.getString("Motori"));
		lblMotori.setForeground(Color.WHITE);
		lblMotori.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMotori.setBounds(34, 206, 162, 22);
		panel.add(lblMotori);
		
		JLabel lblKarburanti = new JLabel(messages.getString("Karburanti"));
		lblKarburanti.setForeground(Color.WHITE);
		lblKarburanti.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblKarburanti.setBounds(34, 409, 162, 22);
		panel.add(lblKarburanti);
		
		JLabel lblVitiP = new JLabel(messages.getString("VitiP"));
		lblVitiP.setForeground(Color.WHITE);
		lblVitiP.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblVitiP.setBounds(34, 343, 162, 22);
		panel.add(lblVitiP);
		
		JLabel lblNrDyerve = new JLabel(messages.getString("NrDyerve"));
		lblNrDyerve.setForeground(Color.WHITE);
		lblNrDyerve.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNrDyerve.setBounds(34, 376, 162, 22);
		panel.add(lblNrDyerve);
		
		JLabel lblTipi = new JLabel(messages.getString("Tipi"));
		lblTipi.setForeground(Color.WHITE);
		lblTipi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTipi.setBounds(34, 250, 162, 22);
		panel.add(lblTipi);
		
		JLabel lblTransmetuesi = new JLabel(messages.getString("Transmetuesi"));
		lblTransmetuesi.setForeground(Color.WHITE);
		lblTransmetuesi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTransmetuesi.setBounds(34, 454, 162, 22);
		panel.add(lblTransmetuesi);
		
		JLabel lblTargat = new JLabel(messages.getString("Targat"));
		lblTargat.setForeground(Color.WHITE);
		lblTargat.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTargat.setBounds(34, 498, 162, 22);
		panel.add(lblTargat);
		
		txtBrendi = new JTextField();
		txtBrendi.setBackground(UIManager.getColor("Button.background"));
		txtBrendi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtBrendi.setColumns(10);
		txtBrendi.setBounds(206, 116, 223, 25);
		txtBrendi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) 
			{
				char vchar = e.getKeyChar();
				if(!Character.isAlphabetic(vchar))
				{					
					getToolkit().beep();
					e.consume();
				}
			}
		});
		panel.add(txtBrendi);
		
		txtModeli = new JTextField();
		txtModeli.setBackground(UIManager.getColor("Button.background"));
		txtModeli.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtModeli.setColumns(10);
		txtModeli.setBounds(206, 159, 223, 28);
		panel.add(txtModeli);
		
		txtMotori = new JTextField();
		txtMotori.setBackground(UIManager.getColor("Button.background"));
		txtMotori.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtMotori.setColumns(10);
		txtMotori.setBounds(206, 207, 223, 28);
		panel.add(txtMotori);
		
		JLabel lblKthehu = new JLabel("");
		lblKthehu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				frmMain.main(null);
				dispose();
			}
		});
		lblKthehu.setIcon(new ImageIcon(frmBlej.class.getResource("/img2/back_white3535.png")));
		lblKthehu.setBounds(24, 13, 56, 38);
		panel.add(lblKthehu);
		
		JComboBox cmbTipi = new JComboBox();
		cmbTipi.setModel(new DefaultComboBoxModel(new String[] {messages.getString("tipi1"), "Sedan", "Hatchback", messages.getString("tipi2"), messages.getString("tipi3"), messages.getString("tipi4"), "SUV", "Minivan"}));
		cmbTipi.setBounds(206, 252, 223, 28);
		panel.add(cmbTipi);
		
		JRadioButton rdbtn3 = new JRadioButton("3");
		buttonGroup_1.add(rdbtn3);
		rdbtn3.setOpaque(false);
		rdbtn3.setForeground(Color.WHITE);
		rdbtn3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtn3.setBounds(227, 374, 70, 25);
		panel.add(rdbtn3);
		
		JRadioButton rdbtn5 = new JRadioButton("5");
		buttonGroup_1.add(rdbtn5);
		rdbtn5.setOpaque(false);
		rdbtn5.setForeground(Color.WHITE);
		rdbtn5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtn5.setBounds(322, 374, 70, 25);
		panel.add(rdbtn5);
		
		JComboBox cmbKarburanti = new JComboBox();
		cmbKarburanti.setModel(new DefaultComboBoxModel(new String[] {messages.getString("karb1"), messages.getString("karb2"),messages.getString("karb3"), messages.getString("karb4"), messages.getString("karb5")}));
		cmbKarburanti.setBounds(206, 406, 223, 28);
		panel.add(cmbKarburanti);
		
		JRadioButton rdbtnManual = new JRadioButton("Manual");
		buttonGroup_2.add(rdbtnManual);
		rdbtnManual.setOpaque(false);
		rdbtnManual.setForeground(Color.WHITE);
		rdbtnManual.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnManual.setBounds(218, 453, 79, 25);
		panel.add(rdbtnManual);
		
		JRadioButton rdbtnAutomatik = new JRadioButton(messages.getString("Automatik"));
		buttonGroup_2.add(rdbtnAutomatik);
		rdbtnAutomatik.setOpaque(false);
		rdbtnAutomatik.setForeground(Color.WHITE);
		rdbtnAutomatik.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnAutomatik.setBounds(322, 453, 127, 25);
		panel.add(rdbtnAutomatik);
		
		JComboBox cmbTargat = new JComboBox();
		cmbTargat.setModel(new DefaultComboBoxModel(new String[] {messages.getString("Targat1"), messages.getString("Targat2"), messages.getString("Targat3")}));
		cmbTargat.setBounds(206, 498, 223, 28);
		panel.add(cmbTargat);
		
		JComboBox cmbNgjyrat = new JComboBox();		
	    cmbNgjyrat.setModel(new DefaultComboBoxModel(new String[] {messages.getString("ngjyra1"), messages.getString("ngjyra2"), messages.getString("ngjyra3"), messages.getString("ngjyra4"), messages.getString("ngjyra5"), messages.getString("ngjyra6"), messages.getString("ngjyra7"), messages.getString("ngjyra8")}));
		cmbNgjyrat.setBounds(206, 294, 223, 28);
		panel.add(cmbNgjyrat);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(627, 135, 547, 642);
		panel.add(scrollPane);
		
		tblVeturat2 = new JTable();
		scrollPane.setViewportView(tblVeturat2);
		tblVeturat2.setBackground(UIManager.getColor("Button.background"));
		tblVeturat2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tblVeturat2.setEnabled(false);
		
		JButton btnBlej = new JButton(messages.getString("text26"));
		btnBlej.addKeyListener(new KeyAdapter() {
							@Override
							public void keyPressed(KeyEvent arg0) {
								
								if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
								{
									try
									{
										if(txtBrendi.getText().equals("") || txtModeli.getText().equals("") || txtMotori.getText().equals("") || txtVitiProdhimit.getText().equals("")
												|| (!rdbtn3.isSelected() && !rdbtn5.isSelected()) || (!rdbtnManual.isSelected() && !rdbtnAutomatik.isSelected()) || txtCmimi.getText().equals(""))
												{
													
													JOptionPane.showMessageDialog(null,messages.getString("text6"));
													return;
												}
											
										
										String tipi = (String)cmbTipi.getSelectedItem();
										
										int dyert;
										if(rdbtn3.isSelected())
										{
											dyert=3;
										}
										else
										{
											dyert=5;
										}
										
										String transmetuesi;
										if(rdbtnManual.isSelected())
										{
											transmetuesi="Manual";
										}
										else
										{
											transmetuesi=messages.getString("Automatik");
										}
										
										String targat = (String)cmbTargat.getSelectedItem();
										
										String karburanti = (String)cmbKarburanti.getSelectedItem();
										
										String vitiProdhimit = txtVitiProdhimit.getText();
										
										String ngjyra = cmbNgjyrat.getSelectedItem().toString();
										
										
										String query = " insert into Veturat (brendi,modeli,motori,tipi,ngjyra,vitiProdhimit,numriDyerve,karburanti,transmetuesi,targat,cmimi) values "
									        + " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
				
									      PreparedStatement preparedStmt = conn.prepareStatement(query);
									      preparedStmt.setString (1, txtBrendi.getText());
									      preparedStmt.setString (2, txtModeli.getText());
									      preparedStmt.setString (3, txtMotori.getText());
									      preparedStmt.setString (4, tipi);
									      preparedStmt.setString (5, ngjyra);
									      preparedStmt.setString (6, vitiProdhimit);
									      preparedStmt.setInt 	 (7, dyert);
									      preparedStmt.setString (8, karburanti);
									      preparedStmt.setString (9, transmetuesi);
									      preparedStmt.setString (10, targat);
									      preparedStmt.setString (11,txtCmimi.getText());
									     
									      preparedStmt.execute();
									      
			  					          preparedStmt.close();
									      
									     
										updateTable2();
										
										
										txtBrendi.setText("");
										txtBrendi.requestFocus();
										txtModeli.setText("");
										txtMotori.setText("");
										txtCmimi.setText("");
				
									} 
									catch (Exception e2) 
									{
										JOptionPane.showMessageDialog(null, messages.getString("text7")+e2.getMessage());
									}
									
								
								}
								else
								{
									return;
								}
							}
						});
		
		btnBlej.setIcon(null);
		btnBlej.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				try 
				{
					if(txtBrendi.getText().equals("") || txtModeli.getText().equals("") || txtMotori.getText().equals("") || txtVitiProdhimit.getText().equals("")
							|| (!rdbtn3.isSelected() && !rdbtn5.isSelected()) || (!rdbtnManual.isSelected() && !rdbtnAutomatik.isSelected()) || txtCmimi.getText().equals(""))
							{
								
								JOptionPane.showMessageDialog(null, messages.getString("text6"));
								return;
							}
						
					
					
					String tipi = (String)cmbTipi.getSelectedItem();
					
					int dyert;
					if(rdbtn3.isSelected())
					{
						dyert=3;
					}
					else
					{
						dyert=5;
					}
					
					String transmetuesi;
					if(rdbtnManual.isSelected())
					{
						transmetuesi="Manual";
					}
					else
					{
						transmetuesi=messages.getString("Automatik");
					}
					
					String targat = (String)cmbTargat.getSelectedItem();
					
					String karburanti = (String)cmbKarburanti.getSelectedItem();
					
					
					String vitiProdhimit = txtVitiProdhimit.getText();
					
					String ngjyra = cmbNgjyrat.getSelectedItem().toString();					
					
					String query = " insert into Veturat (brendi,modeli,motori,tipi,ngjyra,vitiProdhimit,numriDyerve,karburanti,transmetuesi,targat,cmimi) values "
				        + " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

				      PreparedStatement preparedStmt = conn.prepareStatement(query);
				      preparedStmt.setString (1, txtBrendi.getText());
				      preparedStmt.setString (2, txtModeli.getText());
				      preparedStmt.setString (3, txtMotori.getText());
				      preparedStmt.setString (4, tipi);
				      preparedStmt.setString (5, ngjyra);
				      preparedStmt.setString (6, vitiProdhimit);
				      preparedStmt.setInt 	 (7, dyert);
				      preparedStmt.setString (8, karburanti);
				      preparedStmt.setString (9, transmetuesi);
				      preparedStmt.setString (10, targat);
				      preparedStmt.setString (11,txtCmimi.getText());
				     
				      preparedStmt.execute();
				      
				      preparedStmt.close();
				      
				     
					//updateTable1();
					updateTable2();
					
					
					txtBrendi.setText("");
					txtBrendi.requestFocus();
					txtModeli.setText("");
					txtMotori.setText("");
					txtCmimi.setText("");

				} 
				catch (Exception e2) 
				{
					JOptionPane.showMessageDialog(null, messages.getString("text7") +e2.getMessage());
				}
				
			}
		});
		btnBlej.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBlej.setBounds(277, 592, 152, 43);
		panel.add(btnBlej);
		
		JLabel lblLista = new JLabel(messages.getString("Lista"));
		lblLista.setForeground(Color.WHITE);
		lblLista.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLista.setBounds(627, 79, 152, 25);
		panel.add(lblLista);
		
		JLabel lblCmimi = new JLabel(messages.getString("Cmimi"));
		lblCmimi.setForeground(Color.WHITE);
		lblCmimi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCmimi.setBounds(35, 543, 161, 22);
		panel.add(lblCmimi);
		
		txtCmimi = new JTextField();
		txtCmimi.setBackground(UIManager.getColor("Button.background"));
		txtCmimi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtCmimi.setColumns(10);
		txtCmimi.setBounds(206, 540, 223, 28);
		txtCmimi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vchar = e.getKeyChar();
				if(!Character.isDigit(vchar))
				{					
					getToolkit().beep();
					e.consume();
				}
			}
		});
		panel.add(txtCmimi);
		
		JLabel label_12 = new JLabel("\u20AC");
		label_12.setForeground(Color.WHITE);
		label_12.setFont(new Font("Tahoma", Font.PLAIN, 19));
		label_12.setBounds(445, 541, 70, 24);
		panel.add(label_12);
		
		txtVitiProdhimit = new JTextField();
		txtVitiProdhimit.setBackground(UIManager.getColor("Button.background"));
		txtVitiProdhimit.setColumns(10);
		txtVitiProdhimit.setBounds(206, 341, 223, 26);
		txtVitiProdhimit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e)  {
				char vchar = e.getKeyChar();
				if(!Character.isDigit(vchar))
				{					
					getToolkit().beep();
					e.consume();
				}
				
			}
		});
		panel.add(txtVitiProdhimit);
		
		JLabel lblShtoBlerjen = new JLabel(messages.getString("ShtoBlerjen"));
		lblShtoBlerjen.setForeground(Color.WHITE);
		lblShtoBlerjen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblShtoBlerjen.setBounds(34, 79, 168, 25);
		panel.add(lblShtoBlerjen);
		
		JLabel lblKerko = new JLabel(messages.getString("Kerko"));
		lblKerko.setForeground(Color.WHITE);
		lblKerko.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblKerko.setBounds(944, 41, 152, 25);
		panel.add(lblKerko);
		
		txtSearch = new JTextField();
		txtSearch.setBackground(UIManager.getColor("Button.background"));
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) 
			{
				try 
				{
					
					
					String query1="select vid as 'ID', brendi as 'Brendi', modeli as 'Modeli', cmimi as 'Çmimi' from  veturat where brendi like '"+txtSearch.getText()+"%'";		
					pst=conn.prepareStatement(query1);
					pst.execute();

					//objekti qe mundeson ekzekutimin e querit dhe vendosjen e rez ne objektin res.
					res=pst.executeQuery();
					tblVeturat2.setModel(DbUtils.resultSetToTableModel(res));
					
					
		
					
					pst.close();
				} 
				catch (Exception ex) 
				{
					JOptionPane.showMessageDialog(null, messages.getString("text8")+ex.getMessage());
				}
			}
		});
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSearch.setColumns(10);
		txtSearch.setBounds(944, 79, 230, 25);
		panel.add(txtSearch);
		
		JLabel lblNgjyra = new JLabel(messages.getString("Ngjyra"));
		lblNgjyra.setForeground(Color.WHITE);
		lblNgjyra.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNgjyra.setBounds(34, 297, 162, 22);
		panel.add(lblNgjyra);
		
		JLabel label_16 = new JLabel("");
		label_16.setIcon(new ImageIcon(frmBlej.class.getResource("/img2/banner.jpg")));
		label_16.setBounds(0, 0, 1214, 834);
		panel.add(label_16);
		
		
		
	}
}