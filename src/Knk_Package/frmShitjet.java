//Nora
package Knk_Package;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

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
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerDateModel;
import java.util.Date;
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
import java.beans.PropertyChangeListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

public class frmShitjet extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private int id;
	private static double buxheti;
	private int i = 0;
	
	//objekti per lidhje
	Connection conn=null;
	//objekti per vendosje te rezultatit
	ResultSet res=null;
	//objekti per query
	PreparedStatement pst=null;
	private JTable tblVeturat1;
	private JTextField txtSearch;
	Locale currentLocale;
	ResourceBundle messages;
	
	JButton btnShitMakinen;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmShitjet frame = new frmShitjet();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setSize(1200, 735);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public final static String getBuxhet() {
		return Double.toString(buxheti);
	}
	
	public void updateTable1()
	{
		try 
		{
			String sql="select vId as 'ID', brendi as 'Brendi', modeli as 'Modeli', motori as 'Motori', tipi as 'Tipi', ngjyra as'Ngjyra', vitiProdhimit as 'Viti i Prodhimit',numriDyerve as 'Numri i Dyerve', karburanti as 'Karburanti', transmetuesi as 'Transmetuesi', targat as 'Targat', cmimi as 'Cmimi' from veturat";
			pst=conn.prepareStatement(sql);
			//objekti qe mundeson ekzekutimin e querit dhe vendosjen e rez ne objektin res.
			res=pst.executeQuery();
			
			tblVeturat1.setModel(DbUtils.resultSetToTableModel(res));
			
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
	public frmShitjet() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmShitjet.class.getResource("/img2/icons/1.png")));
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
				updateTable1();
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
		
		JMenuItem mntmShtoBlerje = new JMenuItem(messages.getString("ShtoBlerje"));
		mntmShtoBlerje.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD1, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmShtoBlerje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmBlej.main(null);
				dispose();
			}
		});
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
		
		JMenuItem mntmUdhezues = new JMenuItem(messages.getString("Udhezues"));
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 92, 928, 678);
		panel.add(scrollPane);
		
		tblVeturat1 = new JTable();
		tblVeturat1.addKeyListener(new KeyAdapter() {
							@Override
							public void keyPressed(KeyEvent e) {
							
							if(e.getKeyCode()==KeyEvent.VK_TAB)
							{
								btnShitMakinen.requestFocus();
							}
							}
						});
		tblVeturat1.setDefaultEditor(Object.class, null);
		tblVeturat1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		scrollPane.setViewportView(tblVeturat1);
		tblVeturat1.setBackground(UIManager.getColor("Button.background"));
		tblVeturat1.setRowMargin(2);
		tblVeturat1.setRowHeight(20);
		tblVeturat1.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		tblVeturat1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				//kodi i cili e merr id e rreshtit te selektuar.
				
				DefaultTableModel model=(DefaultTableModel)tblVeturat1.getModel();
				id=(int)model.getValueAt(tblVeturat1.getSelectedRow(),0);
				
				try 
				{
					String sql="select * from veturat where vid='"+id+"'";
					pst=conn.prepareStatement(sql);
					res=pst.executeQuery();
					
					pst.close();
					
				}
				catch (Exception e) 
				{
					JOptionPane.showMessageDialog(null, messages.getString("text10")+e.getMessage());
				}
				
				
			}
		});
		
		JButton btnFshij = new JButton(messages.getString("text38"));
		
		btnFshij.addKeyListener(new KeyAdapter() {
							@Override
							public void keyTyped(KeyEvent e) {
								
								if(e.getKeyCode()==KeyEvent.VK_ENTER)
								{
								try 
									{
										String sql="Delete from veturat where vid='"+id+"'";
										pst=conn.prepareStatement(sql);
									pst.execute();
										pst.close();
										updateTable1();
										
								}
									catch(Exception ee) 
									{
										JOptionPane.showMessageDialog(null, messages.getString("text11")+ee.getMessage());
									}
								}
							}
					});
		
		btnFshij.setIcon(null);
		btnFshij.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try 
				{
					String sql="Delete from veturat where vid='"+id+"'";
					pst=conn.prepareStatement(sql);
					pst.execute();
					pst.close();
					updateTable1();
					
				}
				catch(Exception ee) 
				{
					JOptionPane.showMessageDialog(null, messages.getString("text11")+ee.getMessage());
				}
				
			}
		});
		btnFshij.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnFshij.setBounds(1008, 718, 163, 52);
		panel.add(btnFshij);
		
		btnShitMakinen = new JButton(messages.getString("text39"));
		
		
	
		
		btnShitMakinen.addKeyListener(new KeyAdapter() {
							@Override
							public void keyPressed(KeyEvent e) {
								
								if(e.getKeyCode()==KeyEvent.VK_ENTER) 
								{
									PreparedStatement statement = null;
									try 
									{
										statement = conn.prepareStatement("SELECT * FROM veturat WHERE vId=?");
									    statement.setInt(1, id);
									    ResultSet rs = statement.executeQuery();
									    if(rs.next())
									    {
									    	double cmimi = rs.getDouble("cmimi");
									    	buxheti = buxheti + cmimi;
											JOptionPane.showMessageDialog(null, messages.getString("text12") + cmimi +" EURO");
									    }
									    
									    ++i;
									    File file = new File("C:/Users/HP/Desktop/KNK/Projekti/Faturat/Fatura-"+i+".txt");
									    //file.setReadOnly();
									    file.getParentFile().mkdirs();
								        //file.setWritable(false);
				
								        PrintWriter writer = new PrintWriter(file);
								        writer.println(messages.getString("text40"));
								        
								        ResultSet rs2 = statement.executeQuery();
										    if(rs2.next())
										    {
										    	String brendi = rs2.getString("brendi");
										    	String modeli = rs2.getString("modeli");
										    	String motori = rs2.getString("motori");
										        String tipi = rs2.getString("tipi");
										        String ngjyra = rs2.getString("ngjyra");
										        String vitiProdhimit = rs2.getString("vitiProdhimit");
										        String numriDyerve = rs2.getString("numriDyerve");
										        String karburanti = rs2.getString("karburanti");
										        String transmetuesi = rs2.getString("transmetuesi");
										        String targat = rs2.getString("targat");
										        String cmimi = rs2.getString("cmimi");
										        
										     
												writer.println(messages.getString("Brendi")+ brendi);
												writer.println(messages.getString("Modeli") + modeli);
												writer.println(messages.getString("Motori") + motori);
												writer.println(messages.getString("Tipi") + tipi);
												writer.println(messages.getString("Ngjyra") + ngjyra);
												writer.println(messages.getString("VitiP") + vitiProdhimit);
												writer.println(messages.getString("NrDyerve") + numriDyerve);
												writer.println(messages.getString("Karburanti") + karburanti);
												writer.println(messages.getString("Transmetuesi") + transmetuesi);
												writer.println(messages.getString("Targat") + targat);
												writer.println(messages.getString("Cmimi") + cmimi);
												
										    }
									    
									pst.close();
										
										writer.println(messages.getString("text41") + java.time.LocalDate.now());
										writer.println(messages.getString("text46") + java.time.LocalTime.now());
										writer.close();
									    
									    String sqll="Delete from veturat where vid='"+id+"'";
										pst=conn.prepareStatement(sqll);
										pst.execute();
										pst.close();
										updateTable1();
										
									
									} 
							catch (Exception e1) 
									{
										JOptionPane.showMessageDialog(null, messages.getString("text13")+e1.getMessage());
								}
									finally {
									    try {
								        statement.close();
									    }catch(Exception e2){
									    	JOptionPane.showMessageDialog(null,messages.getString("text13")+e2.getMessage());
								    }
						}
								
								}
							}
						});
		
		btnShitMakinen.setIcon(null);
	
		btnShitMakinen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PreparedStatement statement = null;
				try 
				{
					statement = conn.prepareStatement("SELECT * FROM veturat WHERE vId=?");
				    statement.setInt(1, id);
				    ResultSet rs = statement.executeQuery();
				    if(rs.next())
				    {
				    	double cmimi = rs.getDouble("cmimi");
				    	buxheti = buxheti + cmimi;
						JOptionPane.showMessageDialog(null, messages.getString("text12") + cmimi +" EURO");
				    }
				    
				    ++i;
				    File file = new File("C:/Users/HP/Desktop/KNK/Projekti/Faturat/Fatura-"+i+".txt");
				    //file.setReadOnly();
				    file.getParentFile().mkdirs();
			        //file.setWritable(false);

			        PrintWriter writer = new PrintWriter(file);
			        writer.println(messages.getString("text40"));
			        
			        ResultSet rs2 = statement.executeQuery();
					    if(rs2.next())
					    {
					    	String brendi = rs2.getString("brendi");
					    	String modeli = rs2.getString("modeli");
					    	String motori = rs2.getString("motori");
					        String tipi = rs2.getString("tipi");
					        String ngjyra = rs2.getString("ngjyra");
					        String vitiProdhimit = rs2.getString("vitiProdhimit");
					        String numriDyerve = rs2.getString("numriDyerve");
					        String karburanti = rs2.getString("karburanti");
					        String transmetuesi = rs2.getString("transmetuesi");
					        String targat = rs2.getString("targat");
					        String cmimi = rs2.getString("cmimi");
					        
					        writer.println(messages.getString("Brendi")+ brendi);
							writer.println(messages.getString("Modeli") + modeli);
							writer.println(messages.getString("Motori") + motori);
							writer.println(messages.getString("Tipi") + tipi);
							writer.println(messages.getString("Ngjyra") + ngjyra);
							writer.println(messages.getString("VitiP") + vitiProdhimit);
							writer.println(messages.getString("NrDyerve") + numriDyerve);
							writer.println(messages.getString("Karburanti") + karburanti);
							writer.println(messages.getString("Transmetuesi") + transmetuesi);
							writer.println(messages.getString("Targat") + targat);
							writer.println(messages.getString("Cmimi") + cmimi);
							
					    }
					    
						pst.close();
					
					writer.println(messages.getString("text41") + java.time.LocalDate.now());
					writer.println("Ne oren: " + java.time.LocalTime.now());
					writer.close();
				    
				    String sqll="Delete from veturat where vid='"+id+"'";
					pst=conn.prepareStatement(sqll);
					pst.execute();
					pst.close();
					updateTable1();
					
					
				} 
				catch (Exception e1) 
				{
					JOptionPane.showMessageDialog(null, messages.getString("text13")+e1.getMessage());
				}
				finally {
				    try {
				        statement.close();
				    }catch(Exception e2){
				    	JOptionPane.showMessageDialog(null, messages.getString("text13")+e2.getMessage());
				    }
				}
				
			}
			
		});
		btnShitMakinen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnShitMakinen.setBounds(1008, 612, 163, 52);
		panel.add(btnShitMakinen);
		
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				frmMain.main(null);
				dispose();
			}
		});
		label.setIcon(new ImageIcon(frmShitjet.class.getResource("/img2/back_white3535.png")));
		label.setBounds(21, 11, 40, 38);
		panel.add(label);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(frmShitjet.class.getResource("/img2/search_60.png")));
		lblNewLabel.setBounds(950, 35, 66, 39);
		panel.add(lblNewLabel);
		
		JLabel lblListaVeturave = new JLabel(messages.getString("Lista"));
		lblListaVeturave.setForeground(Color.WHITE);
		lblListaVeturave.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblListaVeturave.setBounds(24, 56, 152, 25);
		panel.add(lblListaVeturave);
		
		txtSearch = new JTextField();
		txtSearch.setBackground(UIManager.getColor("Button.background"));
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) 
			{
				try 
				{
					
					
					String query1="select * from  veturat where brendi like '"+txtSearch.getText()+"%'";
					pst=conn.prepareStatement(query1);
					pst.execute();
					
					//objekti qe mundeson ekzekutimin e querit dhe vendosjen e rez ne objektin res.
					res=pst.executeQuery();
					tblVeturat1.setModel(DbUtils.resultSetToTableModel(res));
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
		txtSearch.setBounds(722, 35, 230, 33);
		panel.add(txtSearch);
		
		JLabel lblKerko = new JLabel(messages.getString("Kerko"));
		lblKerko.setForeground(Color.WHITE);
		lblKerko.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblKerko.setBounds(560, 40, 152, 25);
		panel.add(lblKerko);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(frmShitjet.class.getResource("/img2/banner.jpg")));
		label_2.setBounds(0, 0, 1214, 810);
		panel.add(label_2);

		
	}
}
