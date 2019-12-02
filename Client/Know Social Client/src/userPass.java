
public class userPass implements java.io.Serializable{
	private String user;
	private String pass;
	private String first;
	private String last;
	
		public userPass(String username, String password)
		{
			String user = username;
			String pass = password;
			
		}
		public userPass()
		{
			
			
		}
		public void setUsername(String username) {
			user = username;
		}
		
		public void setPassword(String password) {
			pass = password;
		}
		
		public String getUsername() {
			return user;
		}
		public String getPassword() {
			return pass;
		}
	
	}
	


