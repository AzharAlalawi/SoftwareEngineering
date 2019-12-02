//Usually you will require both swing and awt packages
//https://www.guru99.com/java-swing-gui.html
// even if you are working with just swings.
import javax.swing.*;
import javax.swing.text.BadLocationException;

import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;
import java.util.Date;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JPasswordField;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;

import org.jfree.chart.*;
import org.jfree.chart.plot.PiePlot;
import org.jfree.*;
import org.jfree.data.*;
import org.jfree.data.general.DefaultPieDataset;


class mainGUI {
	private static PrintStream standardOut;

	  private static JTextField textField;
	  private static JPasswordField textField_1;
	  private static JTextField txtEnterAKey;
	  public String username = "ahavard";
	  
	 
 public mainGUI() {



	
	   
	 //Starting the frame window.
	  JFrame frame = new JFrame("Sentiment Analysis");
	  frame.setSize(600, 600);
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  frame.getContentPane().setLayout(null);
	  frame.setBackground(Color.DARK_GRAY);
	  frame.setForeground(Color.DARK_GRAY);
	  
	  //Font
	  Font font_1 = new Font("Tahoma", Font.BOLD, 20);
	  Font font_2 = new Font("Comic Sans Ms", Font.BOLD, 15);
	  
	  //Components
	  //JPanel panel_1 = new JPanel();
	  JPanel panel_2 = new JPanel();
	  JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
	  frame.getContentPane().add(tabbedPane, BorderLayout.NORTH);
	  tabbedPane.setFont(font_2);
	  tabbedPane.setBounds(30, 30, 550, 500);
	  
	  //tabbedPane.add("HomeView", panel_1);
	  
	  //Panel_1
	  //group and organize.
	  
	  //panel_1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(15),"Sign-in/Log-in Window"));
	  //panel_1.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
	
	    
	 
	  
	  /*groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
	    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(lblName).addComponent(lblPassword))
	    .addGap(20)
	    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	      .addComponent(textField, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
	      .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
	      
	      .addGroup(groupLayout.createSequentialGroup()
	        .addComponent(btnRegister, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	        .addGap(2)
	        .addComponent(btnSignin, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	      .addGroup(groupLayout.createSequentialGroup().addComponent(ForgetPW, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
	        )
	      )
	    );
	  
	  groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
	    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	      .addComponent(lblName)
	      .addComponent(textField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
	      )
	    .addGap(15)
	    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	      .addComponent(lblPassword)
	      .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
	      )
	    .addGap(15)
	    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	      
	      .addComponent(btnRegister, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
	      .addComponent(btnSignin, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
	      )
	    .addGap(15)
	    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	      .addComponent(ForgetPW, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
	      )
	    );*/
	  
	  
	  
	  
	  //Panel_2
	  tabbedPane.add("Search", panel_2);
	  
	  panel_2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED),"Searching Window", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, font_2, Color.white));
	  
	  JLabel welcome = new JLabel();
	  welcome.setText("Welcome: " + username);
	  
	  
	  String userSAWord = "";
	  String userZipCode = "";
	  
	  txtEnterAKey = new JTextField();
	  txtEnterAKey.setBackground(new Color(255, 255, 255));
	  txtEnterAKey.setText("Enter a Keyword");
	  
	  panel_2.add(txtEnterAKey);
	  txtEnterAKey.setColumns(15);
	  
	  JTextField zipCode = new JTextField("Enter a Zipcode");
	  zipCode.setBackground(new Color(255, 255, 255));
	  panel_2.add(zipCode);
	  zipCode.setColumns(15);
	 
	  
	  JLabel labelResult = new JLabel();
	  JSlider TweetSlide = new JSlider (JSlider.HORIZONTAL, 20, 1000, 490);
	  JLabel tweetLabel = new JLabel("Selected Tweets number");
	  JTextField tweetNum = new JTextField();
	  tweetNum.setHorizontalAlignment(SwingConstants.CENTER);
	  
	  tweetNum.setText(String.valueOf(TweetSlide.getValue()));
	  
	  //showing lines and values in the slider.
	  TweetSlide.setMinorTickSpacing(50);
	  TweetSlide.setMajorTickSpacing(100);
	  TweetSlide.setPaintTicks(true);
	  TweetSlide.setPaintLabels(true);
	  
	  panel_2.add(TweetSlide);
	  panel_2.add(tweetLabel);
	  panel_2.add(tweetNum);
	  
	  //An action when we change the value of the slider
	  /*TweetSlide.addChangeListener(changeEvent e){
	   @Override
	   public void stateChanged(ChangeEvent e) {
	    tweetNum.setText(String.valueOf(TweetSlide.getValue()));
	   }
	  };*/

	  
	  JButton btnSystemAnalysis = new JButton("System Analysis");
	  btnSystemAnalysis.setBackground(new Color(255, 255, 255));
	  btnSystemAnalysis.setFont(new Font("Arial", Font.BOLD, 15));
	  
	  //Actions.
	  btnSystemAnalysis.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent e) {
	   }
	  });
	  panel_2.add(btnSystemAnalysis);
	  
	  JButton btnClear = new JButton("Clear");
	  btnClear.setBackground(new Color(255, 255, 255));
	  btnClear.setFont(font_2);
	  
	  btnClear.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent e) {
	   }
	  });
	  panel_2.add(btnClear);
	  
	  userSAWord = txtEnterAKey.getText();
	  userZipCode = zipCode.getText();
	  
	  //group and organize.
	  
	    GroupLayout groupLayout1 = new GroupLayout(panel_2);
	    panel_2.setLayout(groupLayout1);
	    
	    groupLayout1.setAutoCreateContainerGaps(true);
	    groupLayout1.setAutoCreateContainerGaps(true);
	    groupLayout1.setHorizontalGroup(groupLayout1.createSequentialGroup()
	      .addGroup(groupLayout1.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addComponent(txtEnterAKey)
	        .addComponent(zipCode)
	      .addGap(10)

	        
	        .addGroup(groupLayout1.createSequentialGroup()
	          .addComponent(btnSystemAnalysis, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
	          .addGap(10)
	          .addComponent(btnClear, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	          )
	        .addGap(10)
	        .addGroup(groupLayout1.createParallelGroup(GroupLayout.Alignment.LEADING)
	          .addComponent(TweetSlide)
	          .addComponent(tweetLabel)
	          )
	        .addGap(10)
	        .addGroup(groupLayout1.createSequentialGroup()
	          .addComponent(tweetNum, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	          )
	        )
	      );
	    
	    groupLayout1.setVerticalGroup(groupLayout1.createSequentialGroup()
	      .addGroup(groupLayout1.createParallelGroup(GroupLayout.Alignment.BASELINE)
	        .addComponent(txtEnterAKey, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
	        )
	      .addGap(15)
	      .addGroup(groupLayout1.createParallelGroup(GroupLayout.Alignment.BASELINE)
	        .addComponent(zipCode, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
	        )
	      .addGap(15)
	      .addGroup(groupLayout1.createParallelGroup(GroupLayout.Alignment.BASELINE)
	        
	        .addComponent(btnSystemAnalysis, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
	        .addComponent(btnClear, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
	        )
	      .addGap(15)
	      .addGroup(groupLayout1.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(TweetSlide, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
	        )
	      .addGap(15)
	      .addGroup(groupLayout1.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(tweetLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
	        )
	      .addGap(15)
	      .addGroup(groupLayout1.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(tweetNum, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
	        )
	      );
	    
	    
	    
	    
	  /*zipCode.addKeyListener(new KeyListener() {
	    @Override
	    public void keyTyped(KeyEvent e) {
	     
	    }
	    @Override
	    public voud keyPressed(KeyEvent e) {
	     if(e.getKeyCode() = KeyEvent.VK_ENTER)
	      labelResult.setText(zipCode.getText());
	    }
	    @Override
	    public void keyReleased(KeyEvent e) {}
	  });*/
	  
	  
	  
	  //Panel_3
	  
	  JPanel panel_3 = new JPanel();
	  tabbedPane.addTab("Graphs", panel_3);
	  
	  //Components
	  JButton btnData = new JButton("Loaded Data");
	  btnData.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent argO) {
	    
	   }
	  });
	  JScrollPane scrollPane = new JScrollPane();
	  JTable table = new JTable();
	  scrollPane.setViewportView(table);
	  
	  
	  
	  panel_3.add(btnData);
	  panel_3.add(scrollPane);
	  //Font Editing
	  
	  //Color Editing
	  //panel_1.setBackground(Color.DARK_GRAY);
	  panel_2.setBackground(Color.DARK_GRAY);
	  panel_3.setBackground(Color.DARK_GRAY);
	  
	  
	  //MenuBar.
	  JMenuBar menuBar = new JMenuBar();
	  frame.setJMenuBar(menuBar);
	  
	  JMenu mnNewMenu = new JMenu("File");
	  menuBar.add(mnNewMenu);
	  
	  JMenuItem mntmNewMenuItem = new JMenuItem("Quit");
	  mnNewMenu.add(mntmNewMenuItem);
	  
	  JMenu mnNewMenu_1 = new JMenu("Settings");
	  menuBar.add(mnNewMenu_1);
	  
	  JMenuItem mntmNewMenuItem_2 = new JMenuItem("About");
	  mnNewMenu_1.add(mntmNewMenuItem_2);
	  
	  JMenuItem mntmNewMenuItem_1 = new JMenuItem("Edit API Key");
	  mnNewMenu_1.add(mntmNewMenuItem_1);
	  frame.setVisible(true);
	  
	 }
	 
	}