package Knk_Package;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.*;


public class sqlConnect
{
	Connection conn=null;
	public static Connection connectDB()
	{
		try 
		{
			//mundeson marrjen e driver-it per lidhje
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn= null;
			conn = DriverManager.getConnection("jdbc:mysql://localhost/AutosalloniRoyce?characterEncoding=UTF-8&useSSL=false","root", "iamendrin");
			return conn;
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Gabim gjate lidhjes me DB. "+e.getMessage());
			return null;
		}
	}
}
