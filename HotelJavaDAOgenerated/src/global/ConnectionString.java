package global;

public class ConnectionString {
	private static String value = "jdbc:mysql://localhost/hotel2?" +
			                      "user=me&password=mysecret";
	public ConnectionString(){}
	
	public static String get(){return value;
	}
	
}
