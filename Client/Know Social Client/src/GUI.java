import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;






public class GUI extends JFrame
{
	
	
public GUI() {
	Home home = new Home();
	setTitle("Know Social");
	setSize(1100,700);
	setVisible(true);
	add(home);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	repaint();
}

}