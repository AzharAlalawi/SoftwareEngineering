

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class MySQLAccess {
	
  private Connection connect = null;
  private Statement statement = null;
  public PreparedStatement preparedStatement = null;
  private ResultSet resultSet = null;

  final private String host = "127.0.0.1";
  final private String user = "knowsocial";
  final private String passwd = "knowsocial";
  
  public ArrayList<userPass> loginCheck() throws Exception {
	    try {
	      // This will load the MySQL driver, each DB has its own driver
	     
	      
	      // Setup the connection with the DB
	      connect = DriverManager.getConnection("jdbc:mysql://" + host + "/knowSocial?"
	      + "user=" + user + "&password=" + passwd );

	      statement = connect.createStatement();
	      String sql = "SELECT * FROM knowSocial.users;";
	      ResultSet rs = statement.executeQuery(sql);
	      ArrayList<userPass> users = new ArrayList<>();
	     
	      while (rs.next())
	      {
	    	  userPass newUser= new userPass();
	    	  newUser.setUsername(rs.getString(4));
	    	  newUser.setPassword(rs.getString(5));
	    	  users.add(newUser);
	    	 
	      }
	 
	      return users;
	      
	    } catch (Exception e) {
	      throw e;
	    } finally {
	      close();
	    }

	  }

  
  public void readDataBase() throws Exception {
    try {
      // This will load the MySQL driver, each DB has its own driver
     
      
      // Setup the connection with the DB
      connect = DriverManager.getConnection("jdbc:mysql://" + host + "/knowSocial?"
              + "user=" + user + "&password=" + passwd );

     
      
    } catch (Exception e) {
      throw e;
    } finally {
      close();
    }

  }

 


  // You need to close the resultSet
  private void close() {
    try {
      if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connect != null) {
        connect.close();
      }
    } catch (Exception e) {

    }
  }

}