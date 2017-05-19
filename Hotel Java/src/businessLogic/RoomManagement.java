package businessLogic;
import java.sql.*;

public class RoomManagement {
	private static String connectionString =
		"jdbc:mysql://localhost/hotel?user=root&password=acse";
	
	public static int findRoom(Date arrival, Date departure){
		System.out.print("Arrival: "+arrival+", ");
		System.out.println("Departure: "+departure);
		
		Connection conn = null;
		try {
		    conn =
		       DriverManager.getConnection(connectionString);
		    Statement stmt = conn.createStatement();
		    ResultSet rs = stmt.executeQuery(
		    		"SELECT R.RoomNo FROM Room R " +
		    		"WHERE (SELECT COUNT(*) FROM Reservation V "+
		                   "WHERE V.RoomNo = R.RoomNo AND "+
                		   "      V.Departure > '"+arrival+"' AND "+
		                   "      V.Arrival < '"+departure+"') = 0");
                if (rs.next()) {
                	int rno = rs.getInt("RoomNo");
                	conn.close();
                	return rno;
                }
		    conn.close();
		    throw new Exception("no free room found");		    		   
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    return 42; // the solution to all problems (see: THGttU)
		} catch (Exception ex){
		    System.out.println("no free room found");
		    return 42; // the secret room 42 can take any number of guests
		}
	}
	
	public static void reserveRoom(int room, String firstName, 
			 String lastName, String phoneNo, Date arrival, Date departure){
	    System.out.println("Room: "+room+","+firstName+","+lastName+","+phoneNo);
	    Connection conn = null;
		try {
		    conn =
		       DriverManager.getConnection(connectionString);
		    Statement stmt = conn.createStatement();
		    ResultSet rs = stmt.executeQuery(
		    		"SELECT ClientNo " +
		    		"FROM Client " +
		    		"WHERE FirstName = '"+firstName+"' " +
		    	    "      AND LastName = '"+lastName+"'");
		    int cno = 9999;
		    if (rs.next()){ cno = rs.getInt("ClientNo");
		      System.out.println("ClientNo: "+cno);}
		    else {
		      stmt = conn.createStatement();
		      stmt.executeUpdate("INSERT INTO Client VALUES (NULL,"+ 
		      		"'"+firstName+"','"+lastName+"','"+phoneNo+"')",
		            Statement.RETURN_GENERATED_KEYS);
		      rs = stmt.getGeneratedKeys();
		      if (rs.next()) cno = rs.getInt(1);
		      System.out.println("new client "+cno+" inserted into DB");
		    }
		    stmt = conn.createStatement();
		    stmt.executeUpdate("INSERT INTO Reservation VALUES ('"+arrival+"','"+
		    		departure+"','"+cno+"','"+room+"')");
		    System.out.println("reservation completed");
		    conn.close();
		    		   
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
}

