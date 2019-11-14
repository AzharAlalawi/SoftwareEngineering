//Usually you will require both swing and awt packages
//https://www.guru99.com/java-swing-gui.html
// even if you are working with just swings.
//You must have the CustomOutputStream.java in the same package
import javax.swing.*;
import javax.swing.text.BadLocationException;

import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;
import java.util.Date;

class GUI {
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
        JButton clear = new JButton("Clear");
        panel.add(label); // Components Added using Flow Layout
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(twtr);
        panel.add(fb);
        panel.add(send);
        panel.add(clear);
        
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

        // Text Area at the Center
        JTextArea ta = new JTextArea();
        ta.setEditable(false);
        PrintStream printStream = new PrintStream(new CustomOutputStream(ta));
        
        // keeps reference of standard output stream
        standardOut = System.out;
        
        // re-assigns standard output stream and error output stream
        System.setOut(printStream);
        System.setErr(printStream);
        
        //Output Console Into Window
        // adds event handler for button Start
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // re-assigns standard output stream and error output stream
                printLog();
            }
        });
        // adds event handler for button Clear
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // clears the text area
                try {
                    ta.getDocument().remove(0,
                            ta.getDocument().getLength());
                    System.out.println("Text area cleared");
                    ta.getDocument().remove(0,0);
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);
    }
    /**
     * Prints log statements for testing in a thread
     */
    private static void printLog() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("Time now is " + (new Date()));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }
}
