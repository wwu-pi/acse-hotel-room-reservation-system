package businessLogic;
import java.sql.*;
import data.*;

public class RoomManagement {
	public static int findRoom(Date arrival, Date departure){
		System.out.print("Arrival: "+arrival+", ");
		System.out.println("Departure: "+departure);
		return Room.findRoom(arrival, departure);
	}
	
	public static void reserveRoom(int roomNo, String firstName, 
			                       String lastName, String phoneNo, 
			                       Date arrival, Date departure){
	    System.out.println("RoomNo: "+roomNo+","+firstName+","+lastName+","+phoneNo);
		Client cl = Client.findByFirstNameLastName(firstName,lastName);
		int cno;
		if (cl != null) { cno = cl.getClientNo();}
		else { cl = new Client(null,0,firstName,lastName,phoneNo);
		       cno = cl.persist();
		       cl.setClientNo(cno);
		       System.out.println("Client "+lastName+" inserted into DB");
		       }
		Room room = new Room(null,"Double",0,roomNo); // dummy room with correct number
		Reservation res = new Reservation(arrival,departure,cl,room,0);
		res.persist();	    		  	
	}
}

