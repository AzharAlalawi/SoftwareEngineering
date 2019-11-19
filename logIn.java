//Usually you will require both swing and awt packages
//https://www.guru99.com/java-swing-gui.html
// even if you are working with just swings.
import javax.swing.*;
import javax.swing.text.BadLocationException;

import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;
import java.util.Date;

class logIn {
	private static PrintStream standardOut;
    public static void main(String args[]) {

        //Creating the Frame
        JFrame frame = new JFrame("Know Social");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("File");
        JMenu m2 = new JMenu("Settings");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m22 = new JMenuItem("About");
        JMenuItem m33 = new JMenuItem("Quit");
        m1.add(m33);
        m2.add(m22);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel user = new JLabel("Username",SwingConstants.CENTER);
        JLabel pwd = new JLabel("Password");
        JTextField tf = new JTextField(10); // accepts up to 10 characters
        JTextField tf2 = new JTextField(10); // accepts up to 10 characters
        JButton send = new JButton("Login");
        panel.add(user); // Components Added using Flow Layout
        panel.add(tf); // Components Added using Flow Layout
        panel.add(pwd);
        panel.add(tf2);
        panel.add(send);
        
        //Action for Quit Button
        m33.addActionListener(new ActionListener() {
        	  public void actionPerformed(ActionEvent event) {
        	    System.exit(0);
        	  }
        	});
        //Action for About Button
        m22.addActionListener(new ActionListener() {
        	  public void actionPerformed(ActionEvent event) {
        	    JOptionPane.showMessageDialog(frame, "Version 1.0");
        	  }
        	});

        // Text Area at the Center
        JTextArea ta = new JTextArea();
        ta.setEditable(false);
        
        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);
    }
}
