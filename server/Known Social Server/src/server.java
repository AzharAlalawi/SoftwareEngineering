
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
 
public class server {
	
	private static ServerSocket server;
    //socket server port on which it will listen
    private static int port = 9876;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{
		String user = "test"; 
		String pass = "test"; 
        //create the socket server object
        server = new ServerSocket(port);
        //keep listens indefinitely until receives 'exit' call or program terminates
        
            System.out.println("Waiting for the client request");
            //creating socket and waiting for client connection
            Socket socket = server.accept();
            //read from socket to ObjectInputStream object
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject("Please enter username: ");
            //convert ObjectInputStream object to String
            String username = (String) ois.readObject();
            oos.writeObject("Please enter password: ");
            String password = (String) ois.readObject();
            //write object to Socket
            
            if(user.equals(username) && pass.equals(password))
            {
            	oos.writeObject("You are now logged in.");
            }
            else 
            {
            	oos.writeObject("Access Denied - Connection Terminated.");
            	Thread.sleep(1000);
            	socket.close();
            }
            //close resources
           
           
            //terminate the server if client sends exit request
                
}
	
}


