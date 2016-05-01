package data;
import java.sql.*;

public class Room {
	int roomNo;
	String size; // single or double
	int price; // in Euro
		
	public static String connectionString = "jdbc:mysql://localhost/hotel?" +
			                                  "user=me&password=mysecret";
	
	public static int findRoom(Date arrival, Date departure){
		int rno;
		Connection conn = null;
	    try {conn = DriverManager.getConnection(connectionString);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(
			    		"SELECT R.RoomNo FROM Room R " +
			    		"WHERE (SELECT COUNT(*) FROM Reservation V "+
			            "       WHERE V.RoomNo = R.RoomNo AND "+
	                    "             V.Departure > '"+arrival+"' AND "+
			            "             V.Arrival < '"+departure+"') = 0");
	        if (rs.next()) rno = rs.getInt("RoomNo");
	        else rno = 42; // the secret room 42 can accommodate an infinite number of guests		   
	        conn.close();
		} catch (SQLException ex) {
	     	System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			rno = 42; // use secret room
		}  
	    return rno;
	}
}
