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
        			
    			}
    			
    			
    			}
    			} catch (Exception e) {
					e.printStackTrace();
				}
				
//    			System.out.println("+++Report START++++");
//    			System.out.println(justSearched.getReportID());
//    			System.out.println("+++Report START++++");
//    			System.out.println("+++Report START++++");
//    			System.out.println("+++Report START++++");
//    			
//    			
//    			none = new command(1, "None", null);
//            	oos.reset();
//    			oos.writeObject(none);
//    			
//    			System.out.println("calling for all history");
//    			command history = new command(1, "getHistory", null);
//    			oos.reset();
//    			oos.writeObject(history);
//    			
//    			System.out.println("getting history");
//    			ArrayList<Report> reports = new ArrayList<>();
//    			reports = (ArrayList<Report>) ois.readObject();
//    			for(int i = 0; i< (reports.size()); i++) 
//    			{
//    				System.out.println("+++Report START++++");
//    				System.out.println(reports.get(i).getReportID());
//    				System.out.println(reports.get(i).getStanfordID());
//    				System.out.println(reports.get(i).getDictionaryID());
//    				System.out.println(reports.get(i).getUserSearched());
//    				System.out.println("+++Report STANFORD++++");
//    				System.out.println(reports.get(i).getStanford_Report().getReportID());
//    				System.out.println(reports.get(i).getStanford_Report().getVeryPositive());
//    				System.out.println(reports.get(i).getStanford_Report().getPositive());
//    				System.out.println(reports.get(i).getStanford_Report().getNeutral());
//    				System.out.println(reports.get(i).getStanford_Report().getNegative());
//    				System.out.println(reports.get(i).getStanford_Report().getVeryNegative());
//    				System.out.println(reports.get(i).getStanford_Report().getSentimentalOutcome());
//    				System.out.println(reports.get(i).getStanford_Report().getDatabaseIdStart());
//    				System.out.println(reports.get(i).getStanford_Report().getDatabaseIdEnd());
//    				System.out.println("+++Report DICTIONARY++++");
//    				System.out.println(reports.get(i).getDictionary_Report().getReportID());
//    				System.out.println(reports.get(i).getDictionary_Report().getTotalPositive());
//    				System.out.println(reports.get(i).getDictionary_Report().getTotalNegative());
//    				System.out.println(reports.get(i).getDictionary_Report().getSentimentalOutcome());
//    				System.out.println(reports.get(i).getDictionary_Report().getTotalTweets());
//    				System.out.println(reports.get(i).getDictionary_Report().getWordSearched());
//    				System.out.println(reports.get(i).getDictionary_Report().getDatabaseStartID());
//    				System.out.println(reports.get(i).getDictionary_Report().getDatabaseEndID());
//    				
//    				
//    			}
//    		
    			
    			
    			
//    			System.out.println("one history");
//    			command historyID = new command(1, "getReport", null);
//    			commandParameter Params = new commandParameter("Puppies", 1, 20);
//    			oos.reset();
//    			oos.writeObject(historyID);
//    			
//    			System.out.println("getting one history");
//    			Report newR = new Report();
//    			newR = (Report) ois.readObject();
//    			System.out.println(newR.getStanford_Report().getNegative());
//    			
//    			
    			
    			
    			
            	
            	//call knowSocial Class
            	
            	//Main GUI starts here.
            	
            	
            	// Analysis arguments:
            	// MODE - Facebook/Twitter/both (use int 1=Facebook, 2 = Twitter, 3 = Both)
            	// data return count - int for facebook and int for twitter.
            	// Search String
            	
            	
            	
            	
            
			
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

