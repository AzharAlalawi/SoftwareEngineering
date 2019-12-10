import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class client {
	
	@SuppressWarnings({ "null", "unchecked" })
	public static void main(String[] args)throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        //get the localhost IP address, if server is running on some other IP, you need to use that
        InetAddress host = InetAddress.getLocalHost();
        
        boolean authenticated = false;
        String firstName = "";
        String lastName = "";
        String username = "";
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        
            //establish socket connection to server
            socket = new Socket(host.getHostName(), 9876);
            //write to socket using ObjectOutputStream
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            try 
            {
            	
     
            		command instruction = (command) ois.readObject();
            		if (instruction.getCommand().equals("doAuth"))
            		{
            			
            				try {
            					login2 login2 = new login2();
            					login2.setUndecorated(true);
            					login2.setVisible(true);

            				
            			
         				
         				//LoginGUI frame = new LoginGUI();
         			
         				
         				//login login2 = new login();
    					
         
            			
            			while(!authenticated) 
                 	   	{
            				while(login2.loop);
            				
            				userPass user_pass2 = null;
            				user_pass2 = login2.user_pass;
            				oos.reset();
                			oos.writeObject(user_pass2);
                			
                			instruction = (command) ois.readObject();
                			if (instruction.getCommand().equals("authenticated"))
                			{
                				authenticated = true;
                				
                				login2.panel.remove(login2.error);
                				login2.panel.revalidate();
                				login2.panel.repaint();
                				login2.dispose();
                				ArrayList<String> names = new ArrayList<>();
                				names = (ArrayList<String>) ois.readObject();
                				
                				firstName = names.get(0);
                				lastName = names.get(1);
                				username = names.get(2);
                				System.out.println(firstName);
                				System.out.println(lastName);
                				System.out.println(username);
                				
                				
                			}
                			else if (instruction.getCommand().equals("authFailed"))
                				
                			{
                				login2.loop = true;
                				login2.error.setForeground(Color.RED);
                				login2.panel.add(login2.error);
                				login2.panel.revalidate();
                				login2.panel.repaint();
                			}
            			}
            				} catch (Exception e) {
            					e.printStackTrace();
            				}
            				
            		}
            if(authenticated) 
			{
            	
            	System.out.println("Connected");
            	
            	command none = new command(1, "None", null);
           		oos.reset();
    			oos.writeObject(none);
    			try {
    				TestMainGUI GUI = new TestMainGUI();
					GUI.setUndecorated(true);
					GUI.setVisible(true);

            	
    			Report justSearched = new Report();
    			
    			
    			while(true)
    			{
    			while(GUI.loop);
    			if (GUI.command.equals("Search"))
    			{
    				String searchWord = GUI.textField_1.getText();
    				int zipcode = Integer.parseInt(GUI.zipcode.getText());
    				int count = Integer.parseInt(GUI.Count.getText());
        			commandParameter searchParams = new commandParameter(searchWord, zipcode, count, firstName + " " + lastName);
        			command search = new command(1, "Search", searchParams);
        			oos.reset();
        			oos.writeObject(search);
        			
        			justSearched = (Report) ois.readObject();
        			GUI.justSearched = justSearched;
        			GUI.loop2 = false;
        			GUI.loop = true;
        			GUI.command = "";
        			
    			} else if (GUI.command.equals("History"))
    			{
    				GUI.loop2 = true;
        			System.out.println("calling for all history");
        			command history = new command(1, "getHistory", null);
        			oos.reset();
        			oos.writeObject(history);
        			
        			System.out.println("getting history");
        			ArrayList<Report> reports = new ArrayList<>();
        			reports = (ArrayList<Report>) ois.readObject();
        			GUI.History = reports;
        			GUI.loop2 = false;
        			GUI.loop = true;
        			GUI.loop2 = false;
        			GUI.loop2 = false;
        			GUI.command = "";
        			System.out.println("History got");
        			
    			}
    			
    			
    			}
    			} catch (Exception e) {
					e.printStackTrace();
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

