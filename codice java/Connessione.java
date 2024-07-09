

import java.sql.*;

class Connessione {
	
	
	public Connessione() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		String url= "jdbc:mysql://127.0.0.1:3306/?user=autoricambi";
		Connection con = DriverManager.getConnection(url, "autoricambi","autoricambi");
		
		Statement st = con.createStatement();
		
		System.out.println("Connessione andata a buon fine.");
		con.close();
	}
	
	public static void main(String[] args) throws Exception {
		try {
			Connessione con = new Connessione();
		} catch (ClassNotFoundException e) {
			System.out.println("Driver del DATABASE non trovato");
			System.out.println("Errore:" +e);
		} catch (Exception e){
			System.out.println("Connessione fallita. :(");
			System.out.println("Errore:" +e);
		}
	}
}
