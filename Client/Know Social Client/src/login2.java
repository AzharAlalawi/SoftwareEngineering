import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Button;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;

public class login2  extends JFrame{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	volatile public JTextField passwordField = new JTextField();
	volatile public JTextField passwordField_1 = new JTextField();
	 volatile userPass user_pass = new userPass();
	 volatile boolean loop = true;
	 volatile JPanel panel = new JPanel();
	 volatile JLabel error = new JLabel("Invalid Username and Password");
	
	
	int xx,xy;

	/**
	 * Launch the application.
	 */
	
	
	
	// going to borrow code from a gist to move frame.
	

	/**
	 * Create the frame.
	 */
	public login2() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 476);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 346, 490);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("KnowSocial");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setForeground(new Color(240, 248, 255));
		lblNewLabel.setBounds(0, 305, 300, 150);
		panel.add(lblNewLabel);
		
		JLabel label = new JLabel("");
		
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				 xx = e.getX();
			     xy = e.getY();
			}
		});
		label.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				
				int x = arg0.getXOnScreen();
	            int y = arg0.getYOnScreen();
	            login2.this.setLocation(x - xx, y - xy);  
			}
		});
		label.setBounds(-38, 0, 420, 275);
		label.setVerticalAlignment(SwingConstants.TOP);
		
		label.setIcon(new ImageIcon(login2.class.getResource("/images/bg.jpg")));
		panel.add(label);
		
		
		
		Button button = new Button("Sign in");
		button.addActionListener(new loginButtonListener());
		button.setForeground(Color.RED);
		button.setBackground(new Color(255, 0, 0));
		button.setBounds(395, 363, 283, 36);
		contentPane.add(button);
		
		textField = new JTextField();
		textField.setBounds(395, 83, 283, 36);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setBounds(395, 58, 114, 14);
		contentPane.add(lblUsername);
		
		JLabel lblEmail = new JLabel("PASSWORD");
		lblEmail.setBounds(395, 132, 114, 14);
		contentPane.add(lblEmail);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(395, 157, 283, 36);
		contentPane.add(textField_1);
		
		
		JLabel lbl_close = new JLabel("X");
		lbl_close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				System.exit(0);
			}
		});
		lbl_close.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_close.setForeground(new Color(241, 57, 83));
		lbl_close.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_close.setBounds(691, 0, 37, 27);
		contentPane.add(lbl_close);
	
	
      

		// Action for login
		

		// Adding Components to the frame.
		
	}
	class loginButtonListener implements ActionListener
	{
	
		public void actionPerformed(ActionEvent e) 
	    {    
			System.out.println("HELLO");
	    	System.out.println(textField.getText());
	    	
	    	String username = textField.getText();
      	    String password = textField_1.getText();
      	    user_pass.setUsername(username);
      	    user_pass.setPassword(password);
      	    System.out.println(user_pass.getUsername());
      		System.out.println(user_pass.getPassword());
      		loop = false;
	    }
	}
	

}