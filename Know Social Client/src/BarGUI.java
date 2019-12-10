

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
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

public class BarGUI extends JLayeredPane{

	public JLayeredPane BarGUI() {

	

		// Create Drop-down Box & Place at Top of Window
		JLayeredPane topPanel = new JLayeredPane();
		topPanel.setLayout(null);
			
		// Get Chart Data
		DefaultCategoryDataset barChartData = new DefaultCategoryDataset();
		barChartData.setValue(50, "VeryNegative", "VeryNegative");
		barChartData.setValue(55, "VeryNegative", "VeryNegative");
		barChartData.setValue(60, "VeryNegative", "VeryNegative");
		barChartData.setValue(65, "VeryNegative", "VeryNegative");
		barChartData.setValue(70, "VeryNegative", "VeryNegative");
//		barChartData.setValue(report.getStanford_Report().getVeryNegative(), "VeryNegative", "VeryNegative");
//		barChartData.setValue(report.getStanford_Report().getNegative(), "Negative", "Negative");
//		barChartData.setValue(report.getStanford_Report().getNeutral(), "Neutral", "Neutral");
//		barChartData.setValue(report.getStanford_Report().getPositive(), "Positive", "Positive");
//		barChartData.setValue(report.getStanford_Report().getVeryPositive(), "VeryPositive", "VeryPositive");

		// Create Chart
		JFreeChart barChart = ChartFactory.createBarChart(
				"Overall Sentiment for Keyword is Negative w/Dictionary", "Sentiment",
				"Sentiment Percentage ( % )", barChartData);
		CategoryPlot barChrt = barChart.getCategoryPlot();
		barChrt.setRangeGridlinePaint(Color.WHITE);

		// Pull Image and Set Background
		BufferedImage image = null;
		File url = new File("link to bg image");
		try {
			image = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		barChart.setBackgroundImage(image);
		barChrt.setBackgroundImage(image);

		// Place Percentages on Top of Bars
		CategoryItemRenderer renderers = ((CategoryPlot) barChart.getPlot()).getRenderer();
		renderers.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderers.setDefaultItemLabelsVisible(true);
		ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.TOP_CENTER);
		renderers.setDefaultPositiveItemLabelPosition(position);

		CategoryPlot plot = barChrt.getChart().getCategoryPlot();
		BarRenderer renderer = (BarRenderer) plot.getRenderer();

		// Set the color (r,g,b) or (r,g,b,a)
		Color color = new Color(79, 129, 189);
		renderer.setSeriesPaint(0, color.RED);
		renderer.setSeriesPaint(1, color.RED);
		renderer.setSeriesPaint(2, color.WHITE);
		renderer.setSeriesPaint(3, color.GREEN);
		renderer.setSeriesPaint(4, color.GREEN);
		

		// Add Bar Chart to JFrame
		ChartPanel barPanel = new ChartPanel(barChart);
		topPanel.removeAll();
		topPanel.setBounds(0, 0, 774, 550);
		topPanel.add(barPanel);
		topPanel.add(barPanel, BorderLayout.CENTER);

		return topPanel;

	}

}