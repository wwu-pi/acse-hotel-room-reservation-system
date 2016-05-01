	package data;
	import java.util.*;
	import java.sql.*;
	import java.sql.Date;
	
	public class Room extends Object{
	    // attributes
	    protected Vector reservations;
	    protected String size ;
	    protected int price ;
	    protected int roomNo ;
	  
      // constructor
	  public Room(
	    Vector reservations,
	    String size,
	    int price,
	    int roomNo
	  ){
this.reservations = reservations;
this.size = size;
this.price = price;
this.roomNo = roomNo;
	  }
	  
	  // getters and setters
public Vector getReservations() {
  return reservations;}
  
public String getSize() {
   return size;}
   
public void setSize(String size){
  this.size = size;}  
  
public int getPrice() {
   return price;}
   
public void setPrice(int price){
  this.price = price;}  
  
public int getRoomNo() {
   return roomNo;}
   
public void setRoomNo(int roomNo){
  this.roomNo = roomNo;}  
  
	  
	  // persist
	  public int persist(){
		Connection conn = null;        
		int pk = 0;
		try { conn = DriverManager.getConnection(global.ConnectionString.get());
	      Statement stmt = conn.createStatement();   
	      stmt.executeUpdate("INSERT INTO Room VALUES ("+
	        	        "'"+size+"'"+  ","+ 
	        	        "'"+price+"'"+  ","+ 
	        	        "NULL"+
	        ")",
        	stmt.RETURN_GENERATED_KEYS);
          ResultSet rs = stmt.getGeneratedKeys();
          if (rs.next()) pk = rs.getInt(1);
          System.out.println("new tuple with primary key "+pk+" inserted into DB");
          conn.close();}
     	catch(SQLException ex){
     	  System.out.println("SQLException: " + ex.getMessage());
		  System.out.println("SQLState: " + ex.getSQLState());
		  System.out.println("VendorError: " + ex.getErrorCode());} 
        return pk;
	}
	
	// finders
    
    // other non-trivial methods in model
		public static int findRoom(
				  Date  arrival,
				  	  Date  departure
				  ){ 
			// *************************
			// begin: protected region 1
					int rno;
					Connection conn = null;
				    try {conn = DriverManager.getConnection(global.ConnectionString.get());
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
			// end: protected region 1
			// ***********************
			    }	
}
