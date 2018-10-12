
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

public class frmPunetoret extends JFrame {

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
	private JTable tblPerdoruesit;
	private JTextField txtSearch;
	Locale currentLocale;
	ResourceBundle messages;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmPunetoret frame = new frmPunetoret();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setSize(1200, 735);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	public void updateTable()
	{
		try 
		{
			String sql="select * from perdoruesit";
			pst=conn.prepareStatement(sql);
			//objekti qe mundeson ekzekutimin e querit dhe vendosjen e rez ne objektin res.
			res=pst.executeQuery();
			
			tblPerdoruesit.setModel(DbUtils.resultSetToTableModel(res));
			
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
	public frmPunetoret() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmMain.class.getResource("/img2/icons/1.png")));
		
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
				updateTable();
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
		
		
		mntmShitMakina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmShitjet.main(null);
				dispose();
			}
		});
		mnShitjet.add(mntmShitMakina);
		
		JMenu mnAdministratori = new JMenu(messages.getString("Administrator"));
		menuBar.add(mnAdministratori);
		
		JMenuItem mntmPuntort = new JMenuItem(messages.getString("Puntort"));
		mnAdministratori.add(mntmPuntort);
		mntmPuntort.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD4, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		
		
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
		JButton btnLargo = new JButton(messages.getString("text28"));
		tblPerdoruesit = new JTable();
		tblPerdoruesit.addKeyListener(new KeyAdapter() {
						@Override
						public void keyPressed(KeyEvent e) {
							
							if(e.getKeyCode()==KeyEvent.VK_TAB)
							{
								btnLargo.requestFocus();
							}
							}
					});
		
		tblPerdoruesit.setDefaultEditor(Object.class, null);
		tblPerdoruesit.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		scrollPane.setViewportView(tblPerdoruesit);
		tblPerdoruesit.setBackground(UIManager.getColor("Button.background"));
		tblPerdoruesit.setRowMargin(2);
		tblPerdoruesit.setRowHeight(20);
		tblPerdoruesit.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		tblPerdoruesit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				//kodi i cili e merr id e rreshtit te selektuar.
				
				DefaultTableModel model=(DefaultTableModel)tblPerdoruesit.getModel();
				id=(int)model.getValueAt(tblPerdoruesit.getSelectedRow(),0);
				
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
		
		btnLargo.addKeyListener(new KeyAdapter() {
							@Override
						public void keyPressed(KeyEvent e) {
							
								if(e.getKeyCode()==KeyEvent.VK_ENTER)
							{
							PreparedStatement statement = null;
								try 
						{
									String sqll="Delete from perdoruesit where pid='"+id+"'";
										pst=conn.prepareStatement(sqll);
								pst.execute();
										pst.close();
										updateTable();
		
									} 
									catch (Exception e1) 
								{
									JOptionPane.showMessageDialog(null, "Gabim! "+e1.getMessage());
								}
								}
						}
					});
					btnLargo.setIcon(null);
	
		btnLargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PreparedStatement statement = null;
				try 
				{
					String sqll="Delete from perdoruesit where pid='"+id+"'";
					pst=conn.prepareStatement(sqll);
					pst.execute();
					pst.close();
					updateTable();

				} 
				catch (Exception e1) 
				{
					JOptionPane.showMessageDialog(null, messages.getString("text13")+e1.getMessage());
				}
				
			}
		});
		btnLargo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLargo.setBounds(991, 718, 186, 52);
		panel.add(btnLargo);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(frmShitjet.class.getResource("/img2/search_60.png")));
		lblNewLabel.setBounds(950, 35, 66, 39);
		panel.add(lblNewLabel);
		
		JLabel lblListaVeturave = new JLabel(messages.getString("text30"));
		lblListaVeturave.setForeground(Color.WHITE);
		lblListaVeturave.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblListaVeturave.setBounds(24, 40, 173, 25);
		panel.add(lblListaVeturave);
		
		txtSearch = new JTextField();
		txtSearch.setBackground(UIManager.getColor("Button.background"));
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) 
			{
				try 
				{
					String query1="select * from perdoruesit where emri like '"+txtSearch.getText()+"%'";
					pst=conn.prepareStatement(query1);
					pst.execute();
					//pst.setString(1, String.valueOf(txtSearch.getText()));

					//objekti qe mundeson ekzekutimin e querit dhe vendosjen e rez ne objektin res.
					res=pst.executeQuery();
					tblPerdoruesit.setModel(DbUtils.resultSetToTableModel(res));
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
		
		JLabel lblKerko = new JLabel(messages.getString("text29"));
		lblKerko.setForeground(Color.WHITE);
		lblKerko.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblKerko.setBounds(560, 40, 152, 25);
		panel.add(lblKerko);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(frmShitjet.class.getResource("/img2/banner.jpg")));
		label_2.setBounds(0, -28, 1209, 838);
		panel.add(label_2);

		
	}
}
