//Usually you will require both swing and awt packages
//https://www.guru99.com/java-swing-gui.html
// even if you are working with just swings.
import javax.swing.*;
import javax.swing.text.BadLocationException;


import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;
import java.util.Date;

public class logIn {
	
	
	public logIn(){
		//Get the screen size  
		Toolkit toolkit = Toolkit.getDefaultToolkit();  
		Dimension screenSize = toolkit.getScreenSize(); 
		String username;
		String password;
        //Creating the Frame
        JFrame frame = new JFrame("Know Social");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        
      //Calculate the frame location
        int x = (screenSize.width - frame.getWidth()) / 2;  
        int y = (screenSize.height - frame.getHeight()) / 2;  
          
        //Set the new frame location  
        frame.setLocation(x, y); 

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel user = new JLabel("Username");
        JLabel pwd = new JLabel("Password");
        JTextField tf = new JTextField(20); // accepts up to 10 characters
        JTextField tf2 = new JTextField(20); // accepts up to 10 characters
        JButton send = new JButton("Login");
        panel.add(user); // Components Added using Flow Layout
        panel.add(tf); // Components Added using Flow Layout
        panel.add(pwd);
        panel.add(tf2);
        panel.add(send);
        

        // Text Area at the Center
        JTextArea ta = new JTextArea();
        ta.setEditable(false);
        
        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        //frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);
    }
}
