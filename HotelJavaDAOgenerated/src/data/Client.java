	package data;
	import java.util.*;
	import java.sql.*;
	import java.sql.Date;
	
	public class Client extends Object{
	    // attributes
	    protected Vector reservations;
	    protected int clientNo ;
	    protected String firstName ;
	    protected String lastName ;
	    protected String phoneNo ;
	  
      // constructor
	  public Client(
	    Vector reservations,
	    int clientNo,
	    String firstName,
	    String lastName,
	    String phoneNo
	  ){
this.reservations = reservations;
this.clientNo = clientNo;
this.firstName = firstName;
this.lastName = lastName;
this.phoneNo = phoneNo;
	  }
	  
	  // getters and setters
public Vector getReservations() {
  return reservations;}
  
public int getClientNo() {
   return clientNo;}
   
public void setClientNo(int clientNo){
  this.clientNo = clientNo;}  
  
public String getFirstName() {
   return firstName;}
   
public void setFirstName(String firstName){
  this.firstName = firstName;}  
  
public String getLastName() {
   return lastName;}
   
public void setLastName(String lastName){
  this.lastName = lastName;}  
  
public String getPhoneNo() {
   return phoneNo;}
   
public void setPhoneNo(String phoneNo){
  this.phoneNo = phoneNo;}  
  
	  
	  // persist
	  public int persist(){
		Connection conn = null;        
		int pk = 0;
		try { conn = DriverManager.getConnection(global.ConnectionString.get());
	      Statement stmt = conn.createStatement();   
	      stmt.executeUpdate("INSERT INTO Client VALUES ("+
	        	        "NULL"+ ","+ 
	        	        "'"+firstName+"'"+  ","+ 
	        	        "'"+lastName+"'"+  ","+ 
	        	        "'"+phoneNo+"'"+ 
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
	    public static Client findByFirstNameLastName(String firstName, String lastName){
    	Client cl = null;
    	Connection conn = null;
try {conn =
    DriverManager.getConnection(global.ConnectionString.get());
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(
      "SELECT "+
"ClientNo ,  "+
"FirstName ,  "+
"LastName ,  "+
"PhoneNo  "+
	           "FROM Client "+
       "WHERE "+
"FirstName  = '"+firstName+"'"+" AND "+
	             "LastName  = '"+lastName+"'"
	             ); 		    
    if (rs.next()){
    	cl = new Client(
 null
, 
	              rs.getInt("ClientNo")
, 
	              rs.getString("FirstName")
, 
	              rs.getString("LastName")
, 
	              rs.getString("PhoneNo")
	              );
    	} 	    
    conn.close();}
catch(SQLException ex){ 
	System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());} 
	    return cl;
    }
    
    // other non-trivial methods in model
}
