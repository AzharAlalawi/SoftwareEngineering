import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class client {

	@SuppressWarnings("null")
	public static void main(String[] args)throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        //get the localhost IP address, if server is running on some other IP, you need to use that
        InetAddress host = InetAddress.getLocalHost();
        
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        
            //establish socket connection to server
            socket = new Socket(host.getHostName(), 9876);
            
            
            //write to socket using ObjectOutputStream
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
           try {
        	   String instruction = (String) ois.readObject();
               if (instruction.equals("doAuth"))
               {
                   Scanner in = new Scanner(System.in);
                   userPass newUser = new userPass();
                   System.out.println("Enter Username:");
                   newUser.setUsername(in.nextLine());
                   System.out.println("Enter Password:");
                   newUser.setPassword(in.nextLine());
                   oos.writeObject(newUser);
                   instruction = (String) ois.readObject();
                   if (instruction.equals("authenticated"))
                   {
                	   while(true) 
                	   {
                		   System.out.println("Connected");
                		   
                	   }
                   }
                   else if (instruction.equals("authFailed")) 
                   {
                	  
                	   System.out.println("Invalid username and password");
                	  
                   }
                
               
           }
           }
               catch(NullPointerException e) 
           { 
               System.out.print("NullPointerException Caught"); 
           } 
           
         
            
            
            //close resources
           
            socket.close();
            
        
    }
}

