package data;
import java.sql.*;

public class Reservation {
		Date arrival;
		Date departure;
		int clientNo;
		int roomNo;

	public Reservation(Date ar, Date dep, int cno, int rno){
		arrival = ar;
		departure = dep;
		clientNo = cno;
		roomNo = rno;
	}
		
	public void persist(){
		Connection conn = null;        
		try { conn = DriverManager.getConnection(Room.connectionString);
	    	Statement stmt = conn.createStatement();  
			stmt.executeUpdate("INSERT INTO Reservation VALUES ('"+arrival+"','"+
			    	            departure+"','"+clientNo+"','"+roomNo+"')");
			System.out.println("reservation completed");        	
	        conn.close();}
	     catch(SQLException ex){ 
	     	System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());} 
		}
}
