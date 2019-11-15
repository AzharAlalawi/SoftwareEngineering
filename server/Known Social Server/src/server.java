
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



 
public class server {
	
	
    //socket server port on which it will listen
   
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException
	{
		
		String user = "test"; 
		String pass = "test"; 
        //create the socket server object
		ServerSocket server = new ServerSocket(9876);
        //keep listens indefinitely until receives 'exit' call or program terminates
		while (true)  
        { 
			Socket socket = null;
        
			try {
            System.out.println("Waiting for the client request");
            //creating socket and waiting for client connection
            socket = server.accept();
            //read from socket to ObjectInputStream object
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            
            
           
            Thread t = new ClientHandler(socket, ois, oos); 
            
            // Invoking the start() method 
            t.start(); 
           
           
            //close resources
           
           
            //terminate the server if client sends exit request
			} catch (Exception e){ 
                socket.close(); 
                e.printStackTrace(); 
            }       
        }
	}
 static class ClientHandler extends Thread  
	{ 
	
	    final ObjectInputStream dis; 
	    final ObjectOutputStream dos; 
	    final Socket socket; 
	    public String user = "test";
	    public String pass = "test";
	    // Constructor 
	    public ClientHandler(Socket s, ObjectInputStream dis, ObjectOutputStream dos)  
	    { 
	        this.socket = s; 
	        this.dis = dis; 
	        this.dos = dos; 
	    } 
	  
	    @Override
	    public void run()  
	    { 
	    	MySQLAccess dao = new MySQLAccess();
	 	    try {
	 			dao.readDataBase();
	 		} catch (Exception e) {
	 			System.out.println("Error with database");
	 			e.printStackTrace();
	 		}
	        String received; 
	        String toreturn; 
	        while (true)  
	        { 
	        	
	            try 
	            {
	                dos.writeObject("doAuth");
	                userPass newUser = (userPass) dis.readObject();
	                String username = newUser.getUsername();
	                String password = newUser.getPassword();
	                ArrayList<userPass> users = dao.loginCheck();
	                boolean authenticated = false;
	                for (int i =0; i<users.size(); i++)
	                {
	                	userPass checkedUser = users.get(i);
	                	if(username.equals(checkedUser.getUsername()) && password.equals(checkedUser.getPassword()))
	                	{
	                		authenticated = true;
	                		break;
	                	}
	                }
	                if(authenticated)
	                {
	                dos.writeObject("authenticated");
	                }
	                else 
	                {
	                dos.writeObject("authFailed");	
	                socket.close();
	                }
	            } 
	            catch (IOException | ClassNotFoundException e) { 
	                //e.printStackTrace(); 
	                
	            }
	            catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
	         
	          
	        }
	        }
	    }
	}

	



