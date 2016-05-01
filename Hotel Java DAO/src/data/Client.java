package data;
import java.sql.*;

public class Client {
	int clientNo;
	String firstName;
	String lastName;
	String phoneNo;
	
	public Client(int cno, String fn, String ln, String pno){
		clientNo = cno;
		firstName = fn;
		lastName = ln;
		phoneNo = pno;
	}
	
	public int getClientNo(){
		return clientNo;
	}
	public int persist(){
		Connection conn = null;        
		int cno = 0;
		try { conn = DriverManager.getConnection(Room.connectionString);
        	Statement stmt = conn.createStatement();   
        	stmt.executeUpdate("INSERT INTO Client VALUES (NULL,"+ 
                           "'"+firstName+"','"+lastName+"','"+phoneNo+"')",
                           Statement.RETURN_GENERATED_KEYS);
        	ResultSet rs = stmt.getGeneratedKeys();
        	if (rs.next()) cno = rs.getInt(1);
        	System.out.println("new client "+cno+" inserted into DB");
        	conn.close();}
     	catch(SQLException ex){ 
     		System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());} 
        return cno;
	}
    public static Client findByFirstNameLastName(String firstName, String lastName){
    	Client cl = null;
    	Connection conn = null;
 		try {conn =
 		    DriverManager.getConnection(Room.connectionString);
 		    Statement stmt = conn.createStatement();
 		    ResultSet rs = stmt.executeQuery(
 		    		"SELECT ClientNo, FirstName, LastName, PhoneNo " +
 		    		"FROM Client " +
 		    		"WHERE FirstName = '"+firstName+"' " +
 		    	    "      AND LastName = '"+lastName+"'"); 		    
 		    if (rs.next()){
 		    	cl = new Client(rs.getInt("ClientNo"),rs.getString("FirstName"),
 		    			        rs.getString("LastName"),rs.getString("PhoneNo"));
 		    	} 	    
 		    conn.close();}
 		catch(SQLException ex){ 
 			System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());} 
	    return cl;
    }
    ;
}
