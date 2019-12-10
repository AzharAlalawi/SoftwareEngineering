import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Button;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TestMainGUI extends JFrame {

	volatile public JPanel contentPane;
	volatile public JLayeredPane RightPanel5 = new JLayeredPane();
	volatile public JTextField textField;
	volatile public JTextField textField_1;
	volatile public JLabel button = new JLabel("");
	volatile userPass user_pass = new userPass();
	volatile boolean loop = true;
	volatile boolean loop2 = false;
	volatile JPanel panel = new JPanel();
	volatile JLabel error = new JLabel("Invalid Username and Password");
	volatile public JTextField zipcode = new JTextField();
	volatile public JTextField Count = new JTextField();
	volatile public JLabel ViewButton = new JLabel("");
	volatile public String command = "";
	volatile public Report justSearched = new Report();
	volatile public ArrayList<Report> History = new ArrayList<>();
	volatile public JLabel keyword = new JLabel("", SwingConstants.CENTER);
	volatile public JLabel Analysis = new JLabel("", SwingConstants.CENTER);
	volatile public JLabel TweetCount = new JLabel("", SwingConstants.CENTER);
	volatile public int timesClicked = 0;
	
	public int tweetSelected = 1;
	public int tweetTotal = 0;
	int xx, xy;

	/**
	 * Launch the application.
	 */

	// going to borrow code from a gist to move frame.

	/**
	 * Create the frame.
	 */
	public TestMainGUI() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Left GUI Panel Always Stays.

		JPanel LeftPanel = new JPanel();
		LeftPanel.setBackground(Color.DARK_GRAY);
		LeftPanel.setBounds(0, 0, 250, 768);

		LeftPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Know Social");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel.setForeground(new Color(240, 248, 255));
		lblNewLabel.setBounds(-25, 250, 300, 100);
		LeftPanel.add(lblNewLabel);

		JLabel searchLabel = new JLabel("Search");
		searchLabel.setHorizontalAlignment(SwingConstants.CENTER);
		searchLabel.setForeground(new Color(0, 150, 250));
		searchLabel.setFont(new Font("Tahoma", Font.PLAIN, 36));
		searchLabel.setBounds(0, 420, 300, 35);
		LeftPanel.add(searchLabel);

		JLabel HistoryLabel = new JLabel("History");
		HistoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		HistoryLabel.setFont(new Font("Tahoma", Font.PLAIN, 36));
		HistoryLabel.setForeground(new Color(255, 255, 255));
		HistoryLabel.setBounds(0, 472, 300, 35);
		LeftPanel.add(HistoryLabel);

		JLabel Signout = new JLabel("Sign out");
		Signout.setHorizontalAlignment(SwingConstants.CENTER);
		Signout.setFont(new Font("Tahoma", Font.PLAIN, 28));
		Signout.setForeground(new Color(255, 255, 255));
		Signout.setBounds(-25, 600, 300, 35);
		LeftPanel.add(Signout);

		JLabel Quit = new JLabel("Quit");
		Quit.setHorizontalAlignment(SwingConstants.CENTER);
		Quit.setFont(new Font("Tahoma", Font.PLAIN, 28));
		Quit.setForeground(new Color(255, 25, 25));
		Quit.setBounds(-25, 650, 300, 35);
		LeftPanel.add(Quit);

		JLabel label = new JLabel("");
		label.setBounds(-38, 0, 420, 275);
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setIcon(new ImageIcon(TestMainGUI.class.getResource("/images/bg.jpg")));
		LeftPanel.add(label);

		JLabel SearchIcon = new JLabel("");
		SearchIcon.setBounds(50, 420, 100, 50);
		SearchIcon.setVerticalAlignment(SwingConstants.TOP);
		SearchIcon.setIcon(new ImageIcon(TestMainGUI.class.getResource("/images/active-search.png")));
		LeftPanel.add(SearchIcon);

		JLabel HistoryIcon = new JLabel("");
		HistoryIcon.setBounds(50, 472, 100, 50);
		HistoryIcon.setVerticalAlignment(SwingConstants.TOP);
		HistoryIcon.setIcon(new ImageIcon(TestMainGUI.class.getResource("/images/Search history.png")));
		LeftPanel.add(HistoryIcon);

		contentPane.add(LeftPanel);

		Quit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				System.exit(0);
			}
		});

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
				TestMainGUI.this.setLocation(x - xx, y - xy);
			}
		});
		// GUI WHILE SEARCHING.
		
		RightPanel5.setBounds(250, 0, 774, 700);
		RightPanel5.setLayout(null);
		
		JLabel Background5 = new JLabel();
		Background5.setBounds(0, 0, 774, 700);
		Background5.setVerticalAlignment(SwingConstants.TOP);
		Background5.setIcon(new ImageIcon(TestMainGUI.class.getResource("/images/BlurredImage.png")));
		RightPanel5.add(Background5, JLayeredPane.DEFAULT_LAYER);

		JLabel Searching = new JLabel("Searching... Please Wait.");
		Searching.setFont(new Font("Tahoma", Font.PLAIN, 40));
		Searching.setBounds(287, 200, 774, 400);
		Searching.setForeground(Color.WHITE);
		RightPanel5.add(Searching, JLayeredPane.PALETTE_LAYER);

		
		// Right Panel SEARCH GUI.
		JLayeredPane RightPanel = new JLayeredPane();
		RightPanel.setBounds(250, 0, 774, 700);
		RightPanel.setLayout(null);

		JLabel Background = new JLabel();
		Background.setBounds(0, 0, 774, 700);
		Background.setVerticalAlignment(SwingConstants.TOP);
		Background.setIcon(new ImageIcon(TestMainGUI.class.getResource("/images/BlurredImage.png")));
		RightPanel.add(Background, JLayeredPane.DEFAULT_LAYER);

	
		button.setIcon(new ImageIcon(TestMainGUI.class.getResource("/images/Search Button.png")));
		button.setBounds(287, 400, 200, 75);
		RightPanel.add(button, JLayeredPane.PALETTE_LAYER);

		JLabel Search = new JLabel("Keyword");
		Search.setFont(new Font("Tahoma", Font.PLAIN, 30));
		Search.setBounds(287 + 40, 100, 774, 100);
		Search.setForeground(Color.WHITE);
		RightPanel.add(Search, JLayeredPane.PALETTE_LAYER);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(244, 200, 300, 36);
		RightPanel.add(textField_1, JLayeredPane.PALETTE_LAYER);

		JLabel ZipLabel = new JLabel("Zipcode:");
		ZipLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ZipLabel.setBounds(50, 500, 774, 25);
		ZipLabel.setForeground(Color.WHITE);
		RightPanel.add(ZipLabel, JLayeredPane.PALETTE_LAYER);
		contentPane.add(RightPanel);

		
		zipcode.setColumns(10);
		zipcode.setBounds(48, 526, 200, 36);
		RightPanel.add(zipcode, JLayeredPane.PALETTE_LAYER);

		JLabel CountLabel = new JLabel("Data Count:");
		CountLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		CountLabel.setBounds(50, 600, 774, 25);
		CountLabel.setForeground(Color.WHITE);
		RightPanel.add(CountLabel, JLayeredPane.PALETTE_LAYER);

		
		Count.setColumns(10);
		Count.setBounds(48, 626, 200, 36);
		RightPanel.add(Count, JLayeredPane.PALETTE_LAYER);

		contentPane.add(RightPanel);

		// Right Panel HISTORY.
		JLayeredPane RightPanel2 = new JLayeredPane();
		RightPanel2.setBounds(250, 0, 774, 700);
		RightPanel2.setLayout(null);

		JLabel Background2 = new JLabel();
		Background2.setBounds(0, 0, 774, 700);
		Background2.setVerticalAlignment(SwingConstants.TOP);
		Background2.setIcon(new ImageIcon(TestMainGUI.class.getResource("/images/BlurredImage.png")));
		RightPanel2.add(Background2, JLayeredPane.DEFAULT_LAYER);

		JLabel Search2 = new JLabel("Search History");
		Search2.setFont(new Font("Tahoma", Font.PLAIN, 40));
		Search2.setBounds(287, 25, 774, 40);
		Search2.setForeground(Color.WHITE);
		RightPanel2.add(Search2, JLayeredPane.PALETTE_LAYER);

		Border line = BorderFactory.createLineBorder(new Color(0, 150, 250), 2);
		
		
		
		

		
		
		// Right Panel GRAPH.
		JLayeredPane RightPanel3 = new JLayeredPane();
		RightPanel3.setBounds(250, 0, 774, 700);
		RightPanel3.setLayout(null);

		JLabel Background3 = new JLabel();
		Background3.setBounds(0, 0, 774, 700);
		Background3.setVerticalAlignment(SwingConstants.TOP);
		Background3.setIcon(new ImageIcon(TestMainGUI.class.getResource("/images/BlurredImage.png")));
		RightPanel3.add(Background3, JLayeredPane.DEFAULT_LAYER);

		JLabel AnalysisLabel = new JLabel("Sentimental Analysis");
		AnalysisLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		AnalysisLabel.setBounds(210, 25, 774, 40);
		AnalysisLabel.setForeground(Color.WHITE);
		RightPanel3.add(AnalysisLabel, JLayeredPane.PALETTE_LAYER);

		JPanel viewControls = new JPanel();
		viewControls.setBounds(0, 100, 774, 50);
		viewControls.setBorder(line);
		viewControls.setBackground(new Color(0, 50, 100));
		viewControls.setOpaque(true);
		viewControls.setLayout(null);
		RightPanel3.add(viewControls, JLayeredPane.PALETTE_LAYER);

		JLabel OverviewButton = new JLabel("Overview");
		OverviewButton.setFont(new Font("Tahoma", Font.PLAIN, 40));
		OverviewButton.setBounds(20, 3, 200, 40);
		OverviewButton.setForeground(new Color(0, 150, 250));
		viewControls.add(OverviewButton, JLayeredPane.PALETTE_LAYER);

		JLabel ModelButton = new JLabel("Model View");
		ModelButton.setFont(new Font("Tahoma", Font.PLAIN, 40));
		ModelButton.setBounds(250, 3, 250, 40);
		ModelButton.setForeground(Color.WHITE);
		viewControls.add(ModelButton, JLayeredPane.PALETTE_LAYER);

		JLabel DetailedButton = new JLabel("Detailed View");
		DetailedButton.setFont(new Font("Tahoma", Font.PLAIN, 40));
		DetailedButton.setBounds(500, 3, 250, 40);
		DetailedButton.setForeground(Color.WHITE);
		viewControls.add(DetailedButton, JLayeredPane.PALETTE_LAYER);

		// GRAPH PANEL
		JLayeredPane GraphPanel = new JLayeredPane();
		GraphPanel.setBounds(0, 150, 774, 550);
		GraphPanel.setLayout(null);
		GraphPanel.setOpaque(true);
		RightPanel3.add(GraphPanel, JLayeredPane.PALETTE_LAYER);

		JLabel Background4 = new JLabel();
		Background4.setBounds(0, 0, 774, 700);
		Background4.setVerticalAlignment(SwingConstants.TOP);
		Background4.setIcon(new ImageIcon(TestMainGUI.class.getResource("/images/BlurredImage.png")));
		GraphPanel.add(Background4, JLayeredPane.DEFAULT_LAYER);

		// OverView Panel
		JLayeredPane OverviewPanel = new JLayeredPane();
		OverviewPanel.setBounds(0, 0, 774, 550);
		OverviewPanel.setLayout(null);

		JLabel keywordLabel = new JLabel("Keyword:", SwingConstants.CENTER);
		keywordLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		keywordLabel.setBounds(0, 75, 774, 50);
		keywordLabel.setForeground(Color.WHITE);
		OverviewPanel.add(keywordLabel, JLayeredPane.PALETTE_LAYER);

		
		keyword.setFont(new Font("Tahoma", Font.PLAIN, 60));
		keyword.setBounds(0, 140, 774, 70);
		keyword.setForeground(Color.WHITE);
		OverviewPanel.add(keyword, JLayeredPane.PALETTE_LAYER);

		JLabel AnalysisLabel2 = new JLabel("Analysis Outcome:", SwingConstants.CENTER);
		AnalysisLabel2.setFont(new Font("Tahoma", Font.PLAIN, 40));
		AnalysisLabel2.setBounds(0, 260, 774, 50);
		AnalysisLabel2.setForeground(Color.WHITE);
		OverviewPanel.add(AnalysisLabel2, JLayeredPane.PALETTE_LAYER);

		
		Analysis.setFont(new Font("Tahoma", Font.PLAIN, 60));
		Analysis.setBounds(0, 330, 774, 70);
		Analysis.setForeground(Color.GREEN);
		OverviewPanel.add(Analysis, JLayeredPane.PALETTE_LAYER);

		GraphPanel.add(OverviewPanel, JLayeredPane.PALETTE_LAYER);

		// Models Panel
		JLayeredPane ModelPanel = new JLayeredPane();
		ModelPanel.setBounds(0, 0, 774, 550);
		ModelPanel.setLayout(null);

		// GraphPanel.add(OverviewPanel, JLayeredPane.PALETTE_LAYER);

		// Create Chart for stanford model
		JPanel topPanel = new JPanel();

		// Get Chart Data
		DefaultCategoryDataset barChartData = new DefaultCategoryDataset();
		
//			barChartData.setValue(report.getStanford_Report().getVeryNegative(), "VeryNegative", "VeryNegative");
//			barChartData.setValue(report.getStanford_Report().getNegative(), "Negative", "Negative");
//			barChartData.setValue(report.getStanford_Report().getNeutral(), "Neutral", "Neutral");
//			barChartData.setValue(report.getStanford_Report().getPositive(), "Positive", "Positive");
//			barChartData.setValue(report.getStanford_Report().getVeryPositive(), "VeryPositive", "VeryPositive");

		JFreeChart barChart = ChartFactory.createBarChart("Stanford Model", "Sentiment Category",
				"Sentiment Percentage", barChartData);

		CategoryPlot barChrt = barChart.getCategoryPlot();
		barChrt.setRangeGridlinePaint(Color.WHITE);
		barChrt.setOutlinePaint(Color.WHITE);
		barChrt.setRangeGridlinePaint(Color.WHITE);

		barChart.getTitle().setPaint(Color.WHITE);

		BufferedImage image = null;

		try {
			image = ImageIO.read(TestMainGUI.class.getResource("/images/BlurredImage.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		barChart.setBackgroundImage(image);
		barChrt.setBackgroundImage(image);

		CategoryItemRenderer renderers = ((CategoryPlot) barChart.getPlot()).getRenderer();
		renderers.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderers.setDefaultItemLabelsVisible(true);
		ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.TOP_CENTER);
		renderers.setDefaultPositiveItemLabelPosition(position);

		CategoryPlot plot = barChrt.getChart().getCategoryPlot();
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setDefaultFillPaint(Color.WHITE);
		ValueAxis AxisY = plot.getRangeAxis();
		AxisY.setAxisLinePaint(Color.WHITE);
		AxisY.setLabelPaint(Color.WHITE);
		AxisY.setTickMarkPaint(Color.WHITE);
		AxisY.setTickLabelPaint(Color.WHITE);
		CategoryAxis AxisX = plot.getDomainAxis();
		AxisX.setAxisLinePaint(Color.WHITE);
		AxisX.setLabelPaint(Color.WHITE);
		AxisX.setTickMarkPaint(Color.WHITE);
		AxisX.setTickLabelPaint(Color.WHITE);
		renderers.setDefaultItemLabelPaint(Color.DARK_GRAY);

		// Set the color (r,g,b) or (r,g,b,a)
		Color color = new Color(0, 0, 0);
		renderer.setSeriesPaint(0, color.RED);
		renderer.setSeriesPaint(1, color.RED);
		renderer.setSeriesPaint(2, color.WHITE);
		renderer.setSeriesPaint(3, color.GREEN);
		renderer.setSeriesPaint(4, color.GREEN);

		// Add Bar Chart to JFrame
		ChartPanel barPanel = new ChartPanel(barChart);

		// barPanel.setMaximumDrawWidth(300);

		barPanel.setPreferredSize(new Dimension(550, 250));

		topPanel.setSize(new Dimension(550, 250));
		// topPanel.setBounds(0, 0, 744, 550);

		topPanel.add(barPanel);
		topPanel.validate();
		topPanel.repaint();

		ModelPanel.add(topPanel, JLayeredPane.PALETTE_LAYER);

		// Create Chart for Dictionary model
		JPanel topPanel2 = new JPanel();

		// Get Chart Data
		DefaultCategoryDataset barChartData2 = new DefaultCategoryDataset();

	
//				barChartData.setValue(report.getStanford_Report().getVeryNegative(), "VeryNegative", "VeryNegative");
//				barChartData.setValue(report.getStanford_Report().getNegative(), "Negative", "Negative");
//				barChartData.setValue(report.getStanford_Report().getNeutral(), "Neutral", "Neutral");
//				barChartData.setValue(report.getStanford_Report().getPositive(), "Positive", "Positive");
//				barChartData.setValue(report.getStanford_Report().getVeryPositive(), "VeryPositive", "VeryPositive");

		JFreeChart barChart2 = ChartFactory.createBarChart("Dictionary Model", "Sentiment Category",
				"Sentiment Percentage", barChartData2);

		CategoryPlot barChrt2 = barChart2.getCategoryPlot();
		barChrt2.setRangeGridlinePaint(Color.WHITE);
		barChrt2.setOutlinePaint(Color.WHITE);
		barChrt2.setRangeGridlinePaint(Color.WHITE);

		barChart2.getTitle().setPaint(Color.WHITE);

		BufferedImage image2 = null;

		try {
			image2 = ImageIO.read(TestMainGUI.class.getResource("/images/BlurredImage.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		barChart2.setBackgroundImage(image2);
		barChrt2.setBackgroundImage(image2);

		CategoryItemRenderer renderers2 = ((CategoryPlot) barChart2.getPlot()).getRenderer();
		renderers2.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderers2.setDefaultItemLabelsVisible(true);
		ItemLabelPosition position2 = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.TOP_CENTER);
		renderers2.setDefaultPositiveItemLabelPosition(position2);

		CategoryPlot plot2 = barChrt2.getChart().getCategoryPlot();
		BarRenderer renderer2 = (BarRenderer) plot2.getRenderer();
		renderer2.setDefaultFillPaint(Color.WHITE);
		ValueAxis AxisY2 = plot2.getRangeAxis();
		AxisY2.setAxisLinePaint(Color.WHITE);
		AxisY2.setLabelPaint(Color.WHITE);
		AxisY2.setTickMarkPaint(Color.WHITE);
		AxisY2.setTickLabelPaint(Color.WHITE);
		CategoryAxis AxisX2 = plot2.getDomainAxis();
		AxisX2.setAxisLinePaint(Color.WHITE);
		AxisX2.setLabelPaint(Color.WHITE);
		AxisX2.setTickMarkPaint(Color.WHITE);
		AxisX2.setTickLabelPaint(Color.WHITE);
		renderers2.setDefaultItemLabelPaint(Color.DARK_GRAY);

		// Set the color (r,g,b) or (r,g,b,a)
		Color color2 = new Color(0, 0, 0);
		renderer2.setSeriesPaint(0, color2.RED);
		renderer2.setSeriesPaint(1, color2.GREEN);

		// Add Bar Chart to JFrame
		ChartPanel barPanel2 = new ChartPanel(barChart2);

		// barPanel.setMaximumDrawWidth(300);

		barPanel2.setPreferredSize(new Dimension(550, 250));

		topPanel2.setSize(new Dimension(550, 250));
		// topPanel.setBounds(0, 0, 744, 550);

		topPanel2.add(barPanel2);
		topPanel2.validate();
		topPanel2.repaint();
		topPanel2.setBounds(0, 250, 550, 250);
		ModelPanel.add(topPanel2, JLayeredPane.PALETTE_LAYER);

		JLabel ResultLabel1 = new JLabel("Model Outcome:");
		ResultLabel1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ResultLabel1.setBounds(600, 50, 175, 40);
		ResultLabel1.setForeground(Color.WHITE);
		ModelPanel.add(ResultLabel1, JLayeredPane.PALETTE_LAYER);

		JLabel Result = new JLabel("Positive");
		Result.setFont(new Font("Tahoma", Font.PLAIN, 28));
		Result.setBounds(600, 80, 175, 40);
		Result.setForeground(Color.GREEN);
		ModelPanel.add(Result, JLayeredPane.PALETTE_LAYER);

		JLabel ResultLabel2 = new JLabel("Model Outcome:");
		ResultLabel2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ResultLabel2.setBounds(600, 300, 175, 40);
		ResultLabel2.setForeground(Color.WHITE);
		ModelPanel.add(ResultLabel2, JLayeredPane.PALETTE_LAYER);

		JLabel Result2 = new JLabel("Positive");
		Result2.setFont(new Font("Tahoma", Font.PLAIN, 28));
		Result2.setBounds(600, 330, 175, 40);
		Result2.setForeground(Color.GREEN);
		ModelPanel.add(Result2, JLayeredPane.PALETTE_LAYER);

		// Detailed Panel
		JLayeredPane DetailedPanel = new JLayeredPane();
		DetailedPanel.setBounds(0, 0, 774, 550);
		DetailedPanel.setLayout(null);

		JTextArea sampleTextArea = new JTextArea(10, 15);
		sampleTextArea.setEditable(false);
		sampleTextArea.setFont(new Font("Tahoma", Font.PLAIN, 18));
		sampleTextArea.setOpaque(true);
		sampleTextArea.setBackground(Color.DARK_GRAY);
		sampleTextArea.setForeground(Color.WHITE);
		JScrollPane ScrollPane = new JScrollPane(sampleTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		ScrollPane.setBounds(10, 25, 360, 125);
		ScrollPane.setBackground(Color.darkGray);
		
		DetailedPanel.add(ScrollPane, JLayeredPane.PALETTE_LAYER);

		
		
		
		
		
		
		
		
		
		// Stanford Model
		// Create Chart for stanford model
		JPanel topPanel3 = new JPanel();

		// Get Chart Data
		DefaultCategoryDataset barChartData3 = new DefaultCategoryDataset();
		
//			barChartData.setValue(report.getStanford_Report().getVeryNegative(), "VeryNegative", "VeryNegative");
//			barChartData.setValue(report.getStanford_Report().getNegative(), "Negative", "Negative");
//			barChartData.setValue(report.getStanford_Report().getNeutral(), "Neutral", "Neutral");
//			barChartData.setValue(report.getStanford_Report().getPositive(), "Positive", "Positive");
//			barChartData.setValue(report.getStanford_Report().getVeryPositive(), "VeryPositive", "VeryPositive");

		JFreeChart barChart3 = ChartFactory.createBarChart("Stanford Model", "Sentiment Category",
				"Sentiment Percentage", barChartData3);

		CategoryPlot barChrt3 = barChart3.getCategoryPlot();
		barChrt3.setRangeGridlinePaint(Color.WHITE);
		barChrt3.setOutlinePaint(Color.WHITE);
		barChrt3.setRangeGridlinePaint(Color.WHITE);

		barChart3.getTitle().setPaint(Color.WHITE);

		BufferedImage image3 = null;

		try {
			image3 = ImageIO.read(TestMainGUI.class.getResource("/images/BlurredImage.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		barChart3.setBackgroundImage(image3);
		barChrt3.setBackgroundImage(image3);

		CategoryItemRenderer renderers3 = ((CategoryPlot) barChart3.getPlot()).getRenderer();
		renderers3.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderers3.setDefaultItemLabelsVisible(true);
		ItemLabelPosition position3 = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.TOP_CENTER);
		renderers3.setDefaultPositiveItemLabelPosition(position3);

		CategoryPlot plot3 = barChrt3.getChart().getCategoryPlot();
		BarRenderer renderer3 = (BarRenderer) plot3.getRenderer();
		renderer3.setDefaultFillPaint(Color.WHITE);
		ValueAxis AxisY3 = plot3.getRangeAxis();
		AxisY3.setAxisLinePaint(Color.WHITE);
		AxisY3.setLabelPaint(Color.WHITE);
		AxisY3.setTickMarkPaint(Color.WHITE);
		AxisY3.setTickLabelPaint(Color.WHITE);
		CategoryAxis AxisX3 = plot3.getDomainAxis();
		AxisX3.setAxisLinePaint(Color.WHITE);
		AxisX3.setLabelPaint(Color.WHITE);
		AxisX3.setTickMarkPaint(Color.WHITE);
		AxisX3.setTickLabelPaint(Color.WHITE);
		renderers3.setDefaultItemLabelPaint(Color.DARK_GRAY);

		// Set the color (r,g,b) or (r,g,b,a)
		Color color3 = new Color(0, 0, 0);
		renderer3.setSeriesPaint(0, color3.RED);
		renderer3.setSeriesPaint(1, color3.RED);
		renderer3.setSeriesPaint(2, color3.WHITE);
		renderer3.setSeriesPaint(3, color3.GREEN);
		renderer3.setSeriesPaint(4, color3.GREEN);

		// Add Bar Chart to JFrame
		ChartPanel barPanel3 = new ChartPanel(barChart3);

		// barPanel.setMaximumDrawWidth(300);

		barPanel3.setPreferredSize(new Dimension(372, 225));

		topPanel3.setSize(new Dimension(372, 225));
		topPanel3.setBounds(0, 155, 372, 225);

		topPanel3.add(barPanel3);
		topPanel3.validate();
		topPanel3.repaint();

		DetailedPanel.add(topPanel3, JLayeredPane.PALETTE_LAYER);

		// Dictionary Model

		JPanel topPanel4 = new JPanel();

		// Get Chart Data
		DefaultCategoryDataset barChartData4 = new DefaultCategoryDataset();

		
		
//					barChartData.setValue(report.getStanford_Report().getVeryNegative(), "VeryNegative", "VeryNegative");
//					barChartData.setValue(report.getStanford_Report().getNegative(), "Negative", "Negative");
//					barChartData.setValue(report.getStanford_Report().getNeutral(), "Neutral", "Neutral");
//					barChartData.setValue(report.getStanford_Report().getPositive(), "Positive", "Positive");
//					barChartData.setValue(report.getStanford_Report().getVeryPositive(), "VeryPositive", "VeryPositive");

		JFreeChart barChart4 = ChartFactory.createBarChart("Dictionary Model", "Sentiment Category",
				"Sentiment Percentage", barChartData4);

		CategoryPlot barChrt4 = barChart4.getCategoryPlot();
		barChrt4.setRangeGridlinePaint(Color.WHITE);
		barChrt4.setOutlinePaint(Color.WHITE);
		barChrt4.setRangeGridlinePaint(Color.WHITE);

		barChart4.getTitle().setPaint(Color.WHITE);

		BufferedImage image4 = null;

		try {
			image4 = ImageIO.read(TestMainGUI.class.getResource("/images/BlurredImage.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		barChart4.setBackgroundImage(image4);
		barChrt4.setBackgroundImage(image4);

		CategoryItemRenderer renderers4 = ((CategoryPlot) barChart4.getPlot()).getRenderer();
		renderers4.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderers4.setDefaultItemLabelsVisible(true);
		ItemLabelPosition position4 = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.TOP_CENTER);
		renderers4.setDefaultPositiveItemLabelPosition(position4);

		CategoryPlot plot4 = barChrt4.getChart().getCategoryPlot();
		BarRenderer renderer4 = (BarRenderer) plot4.getRenderer();
		renderer4.setDefaultFillPaint(Color.WHITE);
		ValueAxis AxisY4 = plot4.getRangeAxis();
		AxisY4.setAxisLinePaint(Color.WHITE);
		AxisY4.setLabelPaint(Color.WHITE);
		AxisY4.setTickMarkPaint(Color.WHITE);
		AxisY4.setTickLabelPaint(Color.WHITE);
		CategoryAxis AxisX4 = plot4.getDomainAxis();
		AxisX4.setAxisLinePaint(Color.WHITE);
		AxisX4.setLabelPaint(Color.WHITE);
		AxisX4.setTickMarkPaint(Color.WHITE);
		AxisX4.setTickLabelPaint(Color.WHITE);
		renderers4.setDefaultItemLabelPaint(Color.DARK_GRAY);

		// Set the color (r,g,b) or (r,g,b,a)
		Color color4 = new Color(0, 0, 0);
		renderer4.setSeriesPaint(0, color4.RED);
		renderer4.setSeriesPaint(1, color4.GREEN);

		// Add Bar Chart to JFrame
		ChartPanel barPanel4 = new ChartPanel(barChart4);

		// barPanel.setMaximumDrawWidth(300);

		barPanel4.setPreferredSize(new Dimension(372, 225));

		topPanel4.setSize(new Dimension(372, 225));
		topPanel4.setBounds(385, 155, 372, 225);

		topPanel4.add(barPanel4);
		topPanel4.validate();
		topPanel4.repaint();
		DetailedPanel.add(topPanel4, JLayeredPane.PALETTE_LAYER);

		
		
		
		
		JPanel TweetControls = new JPanel();
		TweetControls.setBounds(390, 25, 360, 125);
		TweetControls.setBorder(line);
		TweetControls.setBackground(new Color(0, 50, 100));
		TweetControls.setOpaque(false);
		TweetControls.setLayout(null);
		DetailedPanel.add(TweetControls, JLayeredPane.PALETTE_LAYER);
		
		JLabel NextButton = new JLabel("");
		NextButton.setIcon(new ImageIcon(TestMainGUI.class.getResource("/images/Next Button.png")));
		NextButton.setBounds(275, 10, 100, 75);
		TweetControls.add(NextButton, JLayeredPane.PALETTE_LAYER);
		
		JLabel NextLabel = new JLabel("Next", SwingConstants.CENTER);
		NextLabel.setBounds(260, 60, 100, 75);
		NextLabel.setForeground(Color.WHITE);
		NextLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		TweetControls.add(NextLabel, JLayeredPane.PALETTE_LAYER);
		
		
		JLabel PrevButton = new JLabel("");
		PrevButton.setIcon(new ImageIcon(TestMainGUI.class.getResource("/images/Prev Button.png")));
		PrevButton.setBounds(10, 10, 100, 75);
		TweetControls.add(PrevButton, JLayeredPane.PALETTE_LAYER);
		
		JLabel PrevLabel = new JLabel("Prev", SwingConstants.CENTER);
		PrevLabel.setBounds(-5, 60, 100, 75);
		PrevLabel.setForeground(Color.WHITE);
		PrevLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		TweetControls.add(PrevLabel, JLayeredPane.PALETTE_LAYER);
		
		JLabel TweetLabel = new JLabel("Tweet Selector", SwingConstants.CENTER);
		TweetLabel.setBounds(100, -10, 150, 75);
		TweetLabel.setForeground(Color.WHITE);
		TweetLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		TweetControls.add(TweetLabel, JLayeredPane.PALETTE_LAYER);
		
		
		TweetCount.setBounds(100,30, 150, 75);
		TweetCount.setForeground(Color.WHITE);
		TweetCount.setFont(new Font("Tahoma", Font.PLAIN, 24));
		TweetControls.add(TweetCount, JLayeredPane.PALETTE_LAYER);
		
		
		
		
		JLabel ResultLabel4 = new JLabel("Model Outcome:");
		ResultLabel4.setFont(new Font("Tahoma", Font.PLAIN, 26));
		ResultLabel4.setBounds(125, 385, 200, 40);
		ResultLabel4.setForeground(Color.WHITE);
		DetailedPanel.add(ResultLabel4, JLayeredPane.PALETTE_LAYER);

		JLabel Result4 = new JLabel("Positive");
		Result4.setFont(new Font("Tahoma", Font.PLAIN, 32));
		Result4.setBounds(125, 425, 175, 40);
		Result4.setForeground(Color.GREEN);
		DetailedPanel.add(Result4, JLayeredPane.PALETTE_LAYER);

		JLabel ResultLabel3 = new JLabel("Model Outcome:");
		ResultLabel3.setFont(new Font("Tahoma", Font.PLAIN, 26));
		ResultLabel3.setBounds(500, 385, 200, 40);
		ResultLabel3.setForeground(Color.WHITE);
		DetailedPanel.add(ResultLabel3, JLayeredPane.PALETTE_LAYER);

		JLabel Result3 = new JLabel("Positive");
		Result3.setFont(new Font("Tahoma", Font.PLAIN, 32));
		Result3.setBounds(500, 425, 175, 40);
		Result3.setForeground(Color.GREEN);
		DetailedPanel.add(Result3, JLayeredPane.PALETTE_LAYER);
		
		
		
		
		
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
				
				
				contentPane.removeAll();
				contentPane.add(LeftPanel);
				contentPane.add(RightPanel5);
				contentPane.validate();
				
				
				contentPane.repaint();
			
				
				
				
				timesClicked = 0;
				
				
				
				
				command = "Search";
				
				loop = false;
				
				loop2 = true;
				while(loop2);
				
				keyword.setText(justSearched.getDictionary_Report().getWordSearched());
				String outcome = justSearched.getStanford_Report().getSentimentalOutcome();
				Analysis.setText(outcome);
				if(outcome.equalsIgnoreCase("Negative") || outcome.equalsIgnoreCase("Very Negative"))
				{
				Analysis.setForeground(Color.RED);
				}
				
				
				barChartData.addValue(justSearched.getStanford_Report().getVeryNegative(),  "Very Negative","");
				barChartData.addValue(justSearched.getStanford_Report().getNegative(),  "Negative", "");
				barChartData.addValue(justSearched.getStanford_Report().getNeutral(),  "Neutral", "");
				barChartData.addValue(justSearched.getStanford_Report().getPositive(),  "Positive", "");
				barChartData.addValue(justSearched.getStanford_Report().getVeryPositive(),  "Very Positive", "");
				Result.setForeground(Color.GREEN);
				outcome = justSearched.getStanford_Report().getSentimentalOutcome();
				if(outcome.equals("Negative") || outcome.equals("Very Negative"))
				{
					Result.setForeground(Color.RED);
				}
				Result.setText(outcome);
				
				barChartData2.addValue(justSearched.getDictionary_Report().getTotalNegative(),  "Negative", "");
				barChartData2.addValue(justSearched.getDictionary_Report().getTotalPositive(),  "Positive", "");
				Result2.setForeground(Color.GREEN);
				String outcome2 = justSearched.getDictionary_Report().getSentimentalOutcome();
				if(outcome2.equals("Negative"))
				{
					Result2.setForeground(Color.RED);
				}
				Result2.setText(outcome2);
				
				
				
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				contentPane.removeAll();
				contentPane.add(LeftPanel);
				contentPane.add(RightPanel3);
				
				contentPane.validate();
				contentPane.repaint();
				
			}
		});
		
		
		NextButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if(tweetSelected < tweetTotal)
				{
				tweetSelected++;
				barChartData3.removeValue("Very Negative", "");
				barChartData3.removeValue("Negative", "");
				barChartData3.removeValue("Neutral", "");
				barChartData3.removeValue("Positive", "");
				barChartData3.removeValue("Very Positive", "");
				barChartData3.addValue(justSearched.getStanford_Report().getAnalysis().get(tweetSelected-1).getVeryNegative(),  "Very Negative","");
				barChartData3.addValue(justSearched.getStanford_Report().getAnalysis().get(tweetSelected-1).getNegative(),  "Negative","");
				barChartData3.addValue(justSearched.getStanford_Report().getAnalysis().get(tweetSelected-1).getNeutral(),  "Neutral","");
				barChartData3.addValue(justSearched.getStanford_Report().getAnalysis().get(tweetSelected-1).getPositive(),  "Positive","");
				barChartData3.addValue(justSearched.getStanford_Report().getAnalysis().get(tweetSelected-1).getVeryPositive(),  "Very Positive","");
				Result4.setForeground(Color.GREEN);
				String outcome = justSearched.getStanford_Report().getAnalysis().get(tweetSelected-1).getResult();
				if(outcome.equals("Negative") || outcome.equals("Very Negative"))
				{
					Result4.setForeground(Color.RED);
				}
				if(outcome.equals("Neutral"))
				{
					Result4.setForeground(Color.WHITE);
				}
				Result4.setText(outcome);
				
				barChartData4.removeValue("Negative", "");
				barChartData4.removeValue("Positive", "");
				barChartData4.addValue(justSearched.getDictionary_Report().getAnalysis().get(tweetSelected-1).getNegativeCount(),  "Negative", "");
				barChartData4.addValue(justSearched.getDictionary_Report().getAnalysis().get(tweetSelected-1).getPositiveCount(),  "Positive", "");
				Result3.setForeground(Color.GREEN);
				String outcome2 = justSearched.getDictionary_Report().getAnalysis().get(tweetSelected-1).getSentimentResult();
				if(outcome2.equals("Negative"))
				{
					Result3.setForeground(Color.RED);
				}
				if(outcome2.equals("Neutral"))
				{
					Result3.setForeground(Color.WHITE);
				}
				Result3.setText(outcome2);
				
				sampleTextArea.setText(justSearched.getStanford_Report().getAnalysis().get(tweetSelected-1).getText());
				TweetCount.setText(tweetSelected +" / " + tweetTotal);
				
				contentPane.validate();
				contentPane.repaint();
				}
				else {
					
				}

			}
		});
		PrevButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if(tweetSelected > 1)
				{
				tweetSelected--;
				barChartData3.removeValue("Very Negative", "");
				barChartData3.removeValue("Negative", "");
				barChartData3.removeValue("Neutral", "");
				barChartData3.removeValue("Positive", "");
				barChartData3.removeValue("Very Positive", "");
				barChartData3.addValue(justSearched.getStanford_Report().getAnalysis().get(tweetSelected-1).getVeryNegative(),  "Very Negative","");
				barChartData3.addValue(justSearched.getStanford_Report().getAnalysis().get(tweetSelected-1).getNegative(),  "Negative","");
				barChartData3.addValue(justSearched.getStanford_Report().getAnalysis().get(tweetSelected-1).getNeutral(),  "Neutral","");
				barChartData3.addValue(justSearched.getStanford_Report().getAnalysis().get(tweetSelected-1).getPositive(),  "Positive","");
				barChartData3.addValue(justSearched.getStanford_Report().getAnalysis().get(tweetSelected-1).getVeryPositive(),  "Very Positive","");
				Result4.setForeground(Color.GREEN);
				
				String outcome = justSearched.getStanford_Report().getAnalysis().get(tweetSelected-1).getResult();
				if(outcome.equals("Negative") || outcome.equals("Very Negative"))
				{
					Result4.setForeground(Color.RED);
				}
				if(outcome.equals("Neutral"))
				{
					Result4.setForeground(Color.WHITE);
				}
				Result4.setText(outcome);
				
				barChartData4.removeValue("Negative", "");
				barChartData4.removeValue("Positive", "");
				barChartData4.addValue(justSearched.getDictionary_Report().getAnalysis().get(tweetSelected-1).getNegativeCount(),  "Negative", "");
				barChartData4.addValue(justSearched.getDictionary_Report().getAnalysis().get(tweetSelected-1).getPositiveCount(),  "Positive", "");
				Result3.setForeground(Color.GREEN);
				
				String outcome2 = justSearched.getDictionary_Report().getAnalysis().get(tweetSelected-1).getSentimentResult();
				if(outcome2.equals("Negative"))
				{
					Result3.setForeground(Color.RED);
				}
				if(outcome2.equals("Neutral"))
				{
					Result3.setForeground(Color.WHITE);
				}
				Result3.setText(outcome2);
				
				sampleTextArea.setText(justSearched.getStanford_Report().getAnalysis().get(tweetSelected-1).getText());
				TweetCount.setText(tweetSelected +" / " + tweetTotal);
				GraphPanel.validate();
				GraphPanel.repaint();
				
			
				contentPane.validate();
				contentPane.repaint();
				}
				else 
				{
					
				}

			}
		});
		
		OverviewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				GraphPanel.remove(ModelPanel);
				GraphPanel.remove(DetailedPanel);
				GraphPanel.add(OverviewPanel, JLayeredPane.PALETTE_LAYER);
				GraphPanel.validate();
				GraphPanel.repaint();
				contentPane.validate();
				contentPane.repaint();
				ModelButton.setForeground(Color.WHITE);
				DetailedButton.setForeground(Color.WHITE);
				OverviewButton.setForeground(new Color(0, 150, 250));
			}
		});
		
		DetailedButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(timesClicked == 0) {
					
					timesClicked++;
				tweetSelected = 1;
				tweetTotal = justSearched.getDictionary_Report().getTotalTweets();
				barChartData3.removeValue("Very Negative", "");
				barChartData3.removeValue("Negative", "");
				barChartData3.removeValue("Neutral", "");
				barChartData3.removeValue("Positive", "");
				barChartData3.removeValue("Very Positive", "");
				barChartData3.addValue(justSearched.getStanford_Report().getAnalysis().get(tweetSelected-1).getVeryNegative(),  "Very Negative","");
				barChartData3.addValue(justSearched.getStanford_Report().getAnalysis().get(tweetSelected-1).getNegative(),  "Negative","");
				barChartData3.addValue(justSearched.getStanford_Report().getAnalysis().get(tweetSelected-1).getNeutral(),  "Neutral","");
				barChartData3.addValue(justSearched.getStanford_Report().getAnalysis().get(tweetSelected-1).getPositive(),  "Positive","");
				barChartData3.addValue(justSearched.getStanford_Report().getAnalysis().get(tweetSelected-1).getVeryPositive(),  "Very Positive","");
				Result4.setForeground(Color.GREEN);
				String outcome = justSearched.getStanford_Report().getAnalysis().get(tweetSelected-1).getResult();
				if(outcome.equals("Negative") || outcome.equals("Very Negative"))
				{
					Result4.setForeground(Color.RED);
				}
				Result4.setText(outcome);
				
				barChartData4.removeValue("Negative", "");
				barChartData4.removeValue("Positive", "");
				barChartData4.addValue(justSearched.getDictionary_Report().getAnalysis().get(tweetSelected-1).getNegativeCount(),  "Negative", "");
				barChartData4.addValue(justSearched.getDictionary_Report().getAnalysis().get(tweetSelected-1).getPositiveCount(),  "Positive", "");
				Result3.setForeground(Color.GREEN);
				String outcome2 = justSearched.getDictionary_Report().getAnalysis().get(tweetSelected-1).getSentimentResult();
				if(outcome2.equals("Negative"))
				{
					Result3.setForeground(Color.RED);
				}
				Result3.setText(outcome2);
				
				sampleTextArea.setText(justSearched.getStanford_Report().getAnalysis().get(tweetSelected-1).getText());
				TweetCount.setText(tweetSelected +" / " + tweetTotal);
				GraphPanel.remove(ModelPanel);
				GraphPanel.remove(OverviewPanel);
				GraphPanel.add(DetailedPanel, JLayeredPane.PALETTE_LAYER);

				GraphPanel.validate();
				GraphPanel.repaint();
				contentPane.validate();
				contentPane.repaint();
				ModelButton.setForeground(Color.WHITE);
				OverviewButton.setForeground(Color.WHITE);
				DetailedButton.setForeground(new Color(0, 150, 250));
				}
				else
				{
					tweetTotal = justSearched.getDictionary_Report().getTotalTweets();
					barChartData3.removeValue("Very Negative", "");
					barChartData3.removeValue("Negative", "");
					barChartData3.removeValue("Neutral", "");
					barChartData3.removeValue("Positive", "");
					barChartData3.removeValue("Very Positive", "");
					barChartData3.addValue(justSearched.getStanford_Report().getAnalysis().get(tweetSelected-1).getVeryNegative(),  "Very Negative","");
					barChartData3.addValue(justSearched.getStanford_Report().getAnalysis().get(tweetSelected-1).getNegative(),  "Negative","");
					barChartData3.addValue(justSearched.getStanford_Report().getAnalysis().get(tweetSelected-1).getNeutral(),  "Neutral","");
					barChartData3.addValue(justSearched.getStanford_Report().getAnalysis().get(tweetSelected-1).getPositive(),  "Positive","");
					barChartData3.addValue(justSearched.getStanford_Report().getAnalysis().get(tweetSelected-1).getVeryPositive(),  "Very Positive","");
					Result4.setForeground(Color.GREEN);
					String outcome = justSearched.getStanford_Report().getAnalysis().get(tweetSelected-1).getResult();
					if(outcome.equals("Negative") || outcome.equals("Very Negative"))
					{
						Result4.setForeground(Color.RED);
					}
					if(outcome.equals("Neutral"))
					{
						Result4.setForeground(Color.WHITE);
					}
				
					Result4.setText(outcome);
					barChartData4.removeValue("Negative", "");
					barChartData4.removeValue("Positive", "");
					barChartData4.addValue(justSearched.getDictionary_Report().getAnalysis().get(tweetSelected-1).getNegativeCount(),  "Negative", "");
					barChartData4.addValue(justSearched.getDictionary_Report().getAnalysis().get(tweetSelected-1).getPositiveCount(),  "Positive", "");
					Result3.setForeground(Color.GREEN);
					String outcome2 = justSearched.getDictionary_Report().getAnalysis().get(tweetSelected-1).getSentimentResult();
					if(outcome2.equals("Negative"))
					{
						Result3.setForeground(Color.RED);
					}
					if(outcome2.equals("Neutral"))
					{
						Result3.setForeground(Color.WHITE);
					}
					Result3.setText(outcome2);
					
					sampleTextArea.setText(justSearched.getStanford_Report().getAnalysis().get(tweetSelected-1).getText());
					TweetCount.setText(tweetSelected +" / " + tweetTotal);
					GraphPanel.remove(ModelPanel);
					GraphPanel.remove(OverviewPanel);
					GraphPanel.add(DetailedPanel, JLayeredPane.PALETTE_LAYER);

					GraphPanel.validate();
					GraphPanel.repaint();
					contentPane.validate();
					contentPane.repaint();
					ModelButton.setForeground(Color.WHITE);
					OverviewButton.setForeground(Color.WHITE);
					DetailedButton.setForeground(new Color(0, 150, 250));	
				}
			}
		});

		ModelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				GraphPanel.remove(DetailedPanel);
				GraphPanel.remove(OverviewPanel);
				GraphPanel.add(ModelPanel, JLayeredPane.PALETTE_LAYER);

				contentPane.validate();
				contentPane.repaint();
				DetailedButton.setForeground(Color.WHITE);
				OverviewButton.setForeground(Color.WHITE);
				ModelButton.setForeground(new Color(0, 150, 250));
			}
		});

		searchLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				contentPane.remove(RightPanel2);
				contentPane.remove(RightPanel3);
				contentPane.remove(RightPanel);
				contentPane.add(RightPanel);
				HistoryLabel.setForeground(Color.WHITE);
				searchLabel.setForeground(new Color(0, 150, 250));
				contentPane.validate();
				contentPane.repaint();
			}
		});

		HistoryLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
		
				timesClicked = 0;
				command = "History";
				loop = false;
				
				loop2 = true;
				
				while(loop2);
				
				for (int i = 1; i < 6; i++) 
				{
					
					JLabel keywordDATA = new JLabel("");
					JLabel OutcomeDATA = new JLabel("");
					JLabel countDATA = new JLabel("");
					JLabel ZipDATA = new JLabel("");
					JLabel UserDATA = new JLabel("");
					
					
					
					
					
					// Background Bar
					Report justSearched2  = History.get(History.size()-i);
					JPanel history1 = new JPanel();
					history1.setBounds(0, (100 * i) + (15 * i), 774, 75);
					history1.setBorder(line);
					history1.setBackground(new Color(0, 50, 100));
					history1.setOpaque(true);
					history1.setLayout(null);
					RightPanel2.add(history1, JLayeredPane.PALETTE_LAYER);

					// keyword Label
					JLabel keywordLabel = new JLabel("Keyword:");
					keywordLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
					keywordLabel.setBounds(50, 10, 100, 25);
					keywordLabel.setForeground(Color.WHITE);
					history1.add(keywordLabel, JLayeredPane.PALETTE_LAYER);
					
					
					keywordDATA.setText(justSearched2.getDictionary_Report().getWordSearched());
					keywordDATA.setFont(new Font("Tahoma", Font.PLAIN, 22));
					keywordDATA.setBounds(48, 40, 100, 25);
					keywordDATA.setForeground(Color.WHITE);
					history1.add(keywordDATA, JLayeredPane.PALETTE_LAYER);

					// outcome label
					JLabel OutcomeLabel = new JLabel("Outcome:");
					OutcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
					OutcomeLabel.setBounds(175, 10, 100, 25);
					OutcomeLabel.setForeground(Color.WHITE);
					history1.add(OutcomeLabel, JLayeredPane.PALETTE_LAYER);
					

					OutcomeDATA.setText(justSearched2.getStanford_Report().getSentimentalOutcome());
					
					OutcomeDATA.setFont(new Font("Tahoma", Font.PLAIN, 22));
					OutcomeDATA.setBounds(173, 40, 100, 25);
					OutcomeDATA.setForeground(Color.WHITE);
					history1.add(OutcomeDATA, JLayeredPane.PALETTE_LAYER);

					// Count Fields
					JLabel countLabel2 = new JLabel("Data Count:");
					countLabel2.setFont(new Font("Tahoma", Font.PLAIN, 18));
					countLabel2.setBounds(300, 10, 100, 25);
					countLabel2.setForeground(Color.WHITE);
					history1.add(countLabel2, JLayeredPane.PALETTE_LAYER);

					countDATA.setText(Integer.toString(justSearched2.getDictionary_Report().getTotalTweets()));
					countDATA.setFont(new Font("Tahoma", Font.PLAIN, 22));
					countDATA.setBounds(320, 40, 100, 25);
					countDATA.setForeground(Color.WHITE);
					history1.add(countDATA, JLayeredPane.PALETTE_LAYER);

//					// Zipcode Fields
//					JLabel ZipLabel2 = new JLabel("Zipcode:");
//					ZipLabel2.setFont(new Font("Tahoma", Font.PLAIN, 18));
//					ZipLabel2.setBounds(445, 10, 100, 25);
//					ZipLabel2.setForeground(Color.WHITE);
//					history1.add(ZipLabel2, JLayeredPane.PALETTE_LAYER);
//
//				
//					ZipDATA.setFont(new Font("Tahoma", Font.PLAIN, 22));
//					ZipDATA.setBounds(445, 40, 100, 25);
//					ZipDATA.setForeground(Color.WHITE);
//					history1.add(ZipDATA, JLayeredPane.PALETTE_LAYER);

					// User Fields
					JLabel UserLabel = new JLabel("User Searched:");
					UserLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
					UserLabel.setBounds(445, 10, 125, 25);
					UserLabel.setForeground(Color.WHITE);
					history1.add(UserLabel, JLayeredPane.PALETTE_LAYER);

					UserDATA.setText(justSearched2.getUserSearched());
					UserDATA.setFont(new Font("Tahoma", Font.PLAIN, 22));
					UserDATA.setBounds(445, 40, 150, 25);
					UserDATA.setForeground(Color.WHITE);
					history1.add(UserDATA, JLayeredPane.PALETTE_LAYER);

					// View Button
					
					
//					ViewButton.setIcon(new ImageIcon(TestMainGUI.class.getResource("/images/View Button.png")));
//					ViewButton.setBounds(675, 0, 100, 75);
//					history1.add(ViewButton, JLayeredPane.PALETTE_LAYER);
					
//					ViewButton.addMouseListener(new MouseAdapter() {
//						@Override
//						public void mouseClicked(MouseEvent arg0) {
//							contentPane.remove(RightPanel2);
//							contentPane.remove(RightPanel3);
//							contentPane.remove(RightPanel);
//							contentPane.add(RightPanel);
//							HistoryLabel.setForeground(Color.WHITE);
//							searchLabel.setForeground(new Color(0, 150, 250));
//							contentPane.validate();
//							contentPane.repaint();
//						}
//					});

				}
				
				
				

				
				
				
				
				
				RightPanel2.validate();
				RightPanel2.repaint();
				

				contentPane.remove(RightPanel);
				contentPane.remove(RightPanel2);
				contentPane.remove(RightPanel3);
				contentPane.add(RightPanel2);
				
				HistoryLabel.setForeground(new Color(0, 150, 250));
				searchLabel.setForeground(Color.WHITE);
				contentPane.validate();
				contentPane.repaint();
			}
		});

//		JLabel lbl_close = new JLabel("X");
//		
//		
//		lbl_close.setHorizontalAlignment(SwingConstants.CENTER);
//		lbl_close.setForeground(new Color(241, 57, 83));
//		lbl_close.setFont(new Font("Tahoma", Font.PLAIN, 18));
//		lbl_close.setBounds(1020, 0, 37, 27);
//		contentPane.add(lbl_close);

		// Action for login

		// Adding Components to the frame.

	}

	class loginButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
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

	class ImagePanel extends JPanel {

		private Image img;

		public ImagePanel(String img) {
			this(new ImageIcon(img).getImage());
		}

		public ImagePanel(Image img) {
			this.img = img;
			Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
			setPreferredSize(size);
			setMinimumSize(size);
			setMaximumSize(size);
			setSize(size);
			setLayout(null);
		}

		public void paintComponent(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}

	}

}