
package Knk_Package;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.Toolkit;
import java.awt.Cursor;




public class frmMain extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private int id;
	private double buxheti;
	static JLabel lblEmri = new JLabel("Endrin");
	String user;
	static JMenu mnAdministratori = new JMenu("Administrator");
	
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
					frmMain frame = new frmMain();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setSize(1200, 735);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public String merrUser()
	{
		return frmLogin.getUser();
	}
	
	public static void admin() 
	{
		lblEmri.setText("Admin");
		mnAdministratori.setEnabled(true);
	}
	
	UserPreferences userPreferences = new UserPreferences();
	/**
	 * Create the frame.
	 */
	public frmMain() {
		setResizable(false);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmMain.class.getResource("/img2/icons/1.png")));
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_B)
				{
					frmBlej.main(null);
					dispose();
				}
				else if(e.getKeyCode()==KeyEvent.VK_S)
				{
					frmShitjet.main(null);
					dispose();
				}
			}
		});
		
		
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
				
				try 
				{
					pst = conn.prepareStatement("SELECT emri FROM perdoruesit WHERE username=?");
					pst.setString(1, merrUser());
					res = pst.executeQuery();
						
					if(res.next())
					{
						String emri;
						emri = res.getString("emri");
						
						if(emri.equals("Nehar"))
						{
							admin();
						}
						else
						{
							lblEmri.setText(emri);
						}
						
					}
					
				} 
				catch (Exception e) 
				{
					JOptionPane.showMessageDialog(null, messages.getString("text13")+e.getMessage());
				}
				
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
			public void actionPerformed(ActionEvent arg0) {
				
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
		
		mnAdministratori.setEnabled(false);
		menuBar.add(mnAdministratori);
		
		JMenuItem mntmPuntort = new JMenuItem(messages.getString("Puntort"));
		mntmPuntort.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD4, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmPuntort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
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
		
		JLabel lblBlej = new JLabel("");
		lblBlej.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblBlej.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				frmBlej.main(null);
				dispose();
			}
		});
		lblBlej.setIcon(new ImageIcon(frmMain.class.getResource("/img2/100_100.png")));
		lblBlej.setBounds(426, 510, 122, 98);
		contentPane.add(lblBlej);
		
		JLabel lblShit = new JLabel("");
		lblShit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblShit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				frmShitjet.main(null);
				dispose();
			}
		});
		lblShit.setIcon(new ImageIcon(frmMain.class.getResource("/img2/125_125.png")));
		lblShit.setBounds(743, 493, 138, 125);
	
		contentPane.add(lblShit);
		//text26 me e bo SHIT
		JLabel lblShitt = new JLabel(messages.getString("text27"));
		lblShitt.setForeground(Color.WHITE);
							lblShitt.setFont(new Font("Tahoma", Font.PLAIN, 20));
							lblShitt.setBounds(789, 619, 222, 25);
							contentPane.add(lblShitt);
						
						JLabel lblBlejj = new JLabel(messages.getString("text26"));
						lblBlejj.setFont(new Font("Tahoma", Font.PLAIN, 20));
						lblBlejj.setForeground(Color.WHITE);
						lblBlejj.setBounds(465, 619, 122, 25);
					contentPane.add(lblBlejj);
		
		JLabel lblAutosalloniroyce = new JLabel(messages.getString("text25"));
		lblAutosalloniroyce.setFont(new Font("Sitka Banner", Font.BOLD, 60));
		lblAutosalloniroyce.setForeground(Color.WHITE);
		lblAutosalloniroyce.setBounds(352, 120, 507, 51);
		contentPane.add(lblAutosalloniroyce);
		
		JLabel lblMireSeVini = new JLabel(messages.getString("text24"));
		lblMireSeVini.setForeground(Color.WHITE);
		lblMireSeVini.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblMireSeVini.setBounds(479, 435, 232, 37);
		contentPane.add(lblMireSeVini);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(frmMain.class.getResource("/img2/banner1.png")));
		label.setBounds(228, 174, 757, 217);
		contentPane.add(label);
		
		
		lblEmri.setForeground(Color.CYAN);
		lblEmri.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblEmri.setBounds(661, 421, 103, 61);
		contentPane.add(lblEmri);
		JLabel lblklikoShkronjenB = new JLabel(messages.getString("text48"));
			lblklikoShkronjenB.setForeground(Color.WHITE);
				lblklikoShkronjenB.setBounds(415, 746, 109, 16);
					contentPane.add(lblklikoShkronjenB);
				
				JLabel lblklikoShkronjenS = new JLabel(messages.getString("text49"));
				lblklikoShkronjenS.setForeground(Color.WHITE);
					lblklikoShkronjenS.setBounds(710, 746, 110, 16);
					contentPane.add(lblklikoShkronjenS);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(frmMain.class.getResource("/img2/5amnf-desktop-56289262-1920x1080.jpg")));
		label_1.setBounds(-23, 0, 1249, 833);
		contentPane.add(label_1);

		
	}
}
