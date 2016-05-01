	package data;
	import java.util.*;
	import java.sql.*;
	import java.sql.Date;
	
	public class Reservation extends Object{
	    // attributes
	    protected Date arrival ;
	    protected Date departure ;
	    protected Client client ;
	    protected Room room ;
	    protected int reservationNo ;
	  
      // constructor
	  public Reservation(
	    Date arrival,
	    Date departure,
	    Client client,
	    Room room,
	    int reservationNo
	  ){
this.arrival = arrival;
this.departure = departure;
this.client = client;
this.room = room;
this.reservationNo = reservationNo;
	  }
	  
	  // getters and setters
public Date getArrival() {
   return arrival;}
   
public void setArrival(Date arrival){
  this.arrival = arrival;}  
  
public Date getDeparture() {
   return departure;}
   
public void setDeparture(Date departure){
  this.departure = departure;}  
  
public Client getClient() {
   return client;}
   
public void setClient(Client client){
  this.client = client;}  
  
public Room getRoom() {
   return room;}
   
public void setRoom(Room room){
  this.room = room;}  
  
public int getReservationNo() {
   return reservationNo;}
   
public void setReservationNo(int reservationNo){
  this.reservationNo = reservationNo;}  
  
	  
	  // persist
	  public int persist(){
		Connection conn = null;        
		int pk = 0;
		try { conn = DriverManager.getConnection(global.ConnectionString.get());
	      Statement stmt = conn.createStatement();   
	      stmt.executeUpdate("INSERT INTO Reservation VALUES ("+
	        "'"+arrival+"'"+  ","+ 
	        	        "'"+departure+"'"+  ","+ 
	        	        "'"+client.getClientNo()+"'"+ ","+ 	
	        	        "'"+room.getRoomNo()+"'"+ ","+ 	
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
}
