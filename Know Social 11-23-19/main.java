//Usually you will require both swing and awt packages
//https://www.guru99.com/java-swing-gui.html
// even if you are working with just swings.
import javax.swing.*;
import javax.swing.text.BadLocationException;

import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;
import java.util.Date;

class main {
	private static PrintStream standardOut;
    public static void main(String args[]) {

        //Creating the Frame
        JFrame frame = new JFrame("Know Social");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        
        //Calculate the frame location
        Toolkit toolkit = Toolkit.getDefaultToolkit(); 
        Dimension screenSize = toolkit.getScreenSize(); 
        int x = (screenSize.width - frame.getWidth()) / 2;  
        int y = (screenSize.height - frame.getHeight()) / 2;
        
        //Set the new frame location
        frame.setLocation(x, y);
        
        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("File");
        JMenu m2 = new JMenu("Settings");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Edit API Key");
        JMenuItem m22 = new JMenuItem("About");
        JMenuItem m33 = new JMenuItem("Quit");
        m1.add(m33);
        m2.add(m11);
        m2.add(m22);
        
        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Enter Keyword");
        JTextField tf = new JTextField(10); // accepts up to 10 characters
        JCheckBox twtr = new JCheckBox("Facebook");
        JCheckBox fb = new JCheckBox("Twitter");
        JButton send = new JButton("Run Analysis");
        panel.add(label); // Components Added using Flow Layout
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(twtr);
        panel.add(fb);
        panel.add(send);
        
        //Action for Quit Button
        m33.addActionListener(new ActionListener() {
        	  public void actionPerformed(ActionEvent event) {
        	    System.exit(0);
        	  }
        	});
        
        //Action for Edit API Key Button
        m11.addActionListener(new ActionListener() {
        	  public void actionPerformed(ActionEvent event) {
        	    JOptionPane.showInputDialog("Enter API Key");
        	  }
        	});
        
        //Action for About Button
        m22.addActionListener(new ActionListener() {
        	  public void actionPerformed(ActionEvent event) {
        	    JOptionPane.showMessageDialog(frame, "Version 1.0");
        	  }
        	});
        
        //Adding Components to the frame.
        frame.setBackground(Color.blue);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.setVisible(true);
    }
}
