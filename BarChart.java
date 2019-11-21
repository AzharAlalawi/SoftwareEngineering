import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChart {
	
	public static void main(String[] args) {
		
		//Create & Configure Window
		JFrame window = new JFrame();
		window.setTitle("Know Social");
		window.setSize(720,480);
		window.setLayout(new BorderLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Create Drop-down Box & Place at Top of Window
		JComboBox<String> list = new JComboBox<String>();
		JButton runButton = new JButton("Run Analysis");
		JPanel topPanel = new JPanel();
		topPanel.add(list);
		topPanel.add(runButton);
		window.add(topPanel,BorderLayout.NORTH);
		
		//Get Chart Data
		DefaultCategoryDataset barChartData = new DefaultCategoryDataset();
		barChartData.setValue(74,"Positive Sentiment","Positive");
		barChartData.setValue(26,"Negative Sentiment","Negative");
		
		//Create Chart
		JFreeChart barChart = ChartFactory.createBarChart("Overall Sentiment for Keyword", "Sentiment", "Sentiment Percentage ( % )",barChartData);
		CategoryPlot barChrt = barChart.getCategoryPlot();
		barChrt.setRangeGridlinePaint(Color.white);
		
		
		//Place Percentages on Top of Bars
		CategoryItemRenderer renderers = ((CategoryPlot)barChart.getPlot()).getRenderer();
		renderers.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderers.setDefaultItemLabelsVisible(true);
		ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12,
		        TextAnchor.TOP_CENTER);
		renderers.setDefaultPositiveItemLabelPosition(position);
		
		CategoryPlot plot = barChrt.getChart().getCategoryPlot();
		BarRenderer renderer = (BarRenderer) plot.getRenderer();

		// set the color (r,g,b) or (r,g,b,a)
		Color color = new Color(79, 129, 189);
		renderer.setSeriesPaint(0, color.green);
		renderer.setSeriesPaint(1, color.red);
		
		//Add Bar Chart to JFrame
		ChartPanel barPanel = new ChartPanel(barChart);
		topPanel.removeAll();
		topPanel.add(barPanel,BorderLayout.CENTER);
		
		//Show The Window
		window.setVisible(true);
		
	}
	
}
