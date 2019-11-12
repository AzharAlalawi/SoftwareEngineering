import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class client {

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
            System.out.println("Sending request to Socket Server");
            
            
           
          
            ois = new ObjectInputStream(socket.getInputStream());
            String username = (String) ois.readObject();
            System.out.println(username);
            
            Scanner in = new Scanner(System.in);
            oos.writeObject(in.nextLine());
            
            String password = (String) ois.readObject();
            System.out.println(password);
            oos.writeObject(in.nextLine());
            
            String message = (String) ois.readObject();
            System.out.println(message);
            
            //close resources
            
            Thread.sleep(5000);
        
    }
}