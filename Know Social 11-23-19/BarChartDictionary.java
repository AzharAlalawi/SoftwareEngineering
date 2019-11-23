import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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

public class BarChartDictionary {

	public static void main(String[] args) {

		// Create & Configure Window
		JFrame window = new JFrame();
		window.setTitle("Know Social");
		window.setSize(1100, 1280);
		window.setLayout(new BorderLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Get the screen size
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		// Calculate the frame location
		int x = (screenSize.width - window.getWidth()) / 2;
		int y = (screenSize.height - window.getHeight()) / 2;

		// Set the new frame location
		window.setLocation(x, y);

		// Create Drop-down Box & Place at Top of Window
		JPanel topPanel = new JPanel();
		window.add(topPanel, BorderLayout.CENTER);

		// Get Chart Data
		DefaultCategoryDataset barChartData = new DefaultCategoryDataset();
		barChartData.setValue(47, "Positive Sentiment", "Positive");
		barChartData.setValue(53, "Negative Sentiment", "Negative");

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
		renderer.setSeriesPaint(0, color.GREEN);
		renderer.setSeriesPaint(1, color.RED);

		// Add Bar Chart to JFrame
		ChartPanel barPanel = new ChartPanel(barChart);
		topPanel.removeAll();
		topPanel.add(barPanel, BorderLayout.CENTER);

		// Show The Window
		window.setVisible(true);

	}

}
