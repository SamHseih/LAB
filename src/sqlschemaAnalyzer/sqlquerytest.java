package sqlschemaAnalyzer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.sql.Statement; 

public class sqlquerytest {
    // Connect to your database.
    // Replace server name, username, and password with your credentials
    public static void main(String[] args) {
    	String a = new String();
    	
    	try { 
    		Class.forName("com.mysql.cj.jdbc.Driver"); 
    		//���Udriver 
    		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/DB1?useUnicode=true&characterEncoding=Big5&serverTimezone=UTC","root","balloon"); 
    		Statement stmt = null;
    		
    		//���oconnection
    		//jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=Big5
    		//localhost�O�D���W,test�Odatabase�W
    		//useUnicode=true&characterEncoding=Big5�ϥΪ��s�X 
    		
    		System.out.println("hi fren"); 
    		
    		stmt = conn.createStatement();
    		 
    		String query = "SELECT * FROM person";
    		ResultSet rs = stmt.executeQuery(query);
    		int pid;
    		int age;
    		
    		while(rs.next()) {
    			pid = rs.getInt("pid");
    			age = rs.getInt("age");
    			
    			System.out.println("pid : " + pid + " age : " + age);
    		}
    		
    		query = "SELECT * FROM car";
    		rs = stmt.executeQuery(query);
    		int cid;
    		int carid;
    		while(rs.next()) {
    			cid = rs.getInt("cid");
    			carid = rs.getInt("carid");
    			
    			System.out.println("cid : " + cid + " carid : " + carid);
    		}
    		
    		/*
    		String update = "INSERT INTO car VALUES (2, 3);";
    		stmt.executeUpdate(update);
    		rs = stmt.executeQuery(query);
    		
    		stmt = conn.createStatement();*/
    		
    		
    		int qpid = -1;
    		Integer qage = -10;
    		
    		System.out.println("[Query] INSERT INTO person VALUES (" + qpid + ", " + qage +  ")");
    		
    		String update = "INSERT INTO person VALUES (" + qpid + ", " + qage + ");";
    		stmt.executeUpdate(update);
    		rs = stmt.executeQuery(query);
    		
    		stmt = conn.createStatement();
   		 
    		query = "SELECT * FROM person";
    		rs = stmt.executeQuery(query);
    		while(rs.next()) {
    			pid = rs.getInt("pid");
    			age = rs.getInt("age");
    			
    			System.out.println("pid : " + pid + " age : " + age);
    		}
    		
    		
    		
    		
    		rs.close();
    		System.out.println("bye"); 
    	} 
    	catch(ClassNotFoundException e) 
    	{ 
    		System.out.println("DriverClassNotFound :"+e.toString()); 
    	}//���i��|����sqlexception 
    	catch(SQLException x) { 
    		System.out.println("Exception :"+x.toString()); 
    		a = x.toString();
    	} 
    	System.out.println("Message a :"+a); 
    }
}