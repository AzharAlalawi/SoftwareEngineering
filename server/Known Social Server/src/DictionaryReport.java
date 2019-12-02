

import java.util.ArrayList;

public class DictionaryReport implements java.io.Serializable{

	
	volatile private int reportID;
	volatile private int totalPositive;
	volatile private int totalNegative;
	volatile private String wordSearched;
	volatile private String sentimentalOutcome;
	volatile private int databaseStartID;
	volatile private int databaseEndID;
	volatile private ArrayList<dictAnalysis> analysis;
	volatile private int totalTweets;

	public DictionaryReport(int reportID, int totalPositive, int totalNegative, String wordSearched, String sentimentalOutcome,
			int databaseStartID, int databaseEndID, ArrayList<dictAnalysis> analysis, int totalTweets) {
		super();
		this.reportID = reportID;
		this.totalPositive = totalPositive;
		this.totalNegative = totalNegative;
		this.wordSearched = wordSearched;
		this.sentimentalOutcome = sentimentalOutcome;
		this.databaseStartID = databaseStartID;
		this.databaseEndID = databaseEndID;
		this.analysis = analysis;
		this.totalTweets = totalTweets;
	}
	
	public int getReportID() {
		return reportID;
	}

	public void setReportID(int reportID) {
		this.reportID = reportID;
	}

	public DictionaryReport() {
		// TODO Auto-generated constructor stub
	}
	public int getTotalPositive() {
		return totalPositive;
	}
	public void setTotalPositive(int totalPositive) {
		this.totalPositive = totalPositive;
	}
	public int getTotalNegative() {
		return totalNegative;
	}
	public void setTotalNegative(int totalNegative) {
		this.totalNegative = totalNegative;
	}
	public String getWordSearched() {
		return wordSearched;
	}
	public void setWordSearched(String wordSearched) {
		this.wordSearched = wordSearched;
	}
	public String getSentimentalOutcome() {
		return sentimentalOutcome;
	}
	public void setSentimentalOutcome(String sentimentalOutcome) {
		this.sentimentalOutcome = sentimentalOutcome;
	}
	public int getDatabaseStartID() {
		return databaseStartID;
	}
	public void setDatabaseStartID(int databaseStartID) {
		this.databaseStartID = databaseStartID;
	}
	public int getDatabaseEndID() {
		return databaseEndID;
	}
	public void setDatabaseEndID(int databaseEndID) {
		this.databaseEndID = databaseEndID;
	}
	public ArrayList<dictAnalysis> getAnalysis() {
		return analysis;
	}
	public void setAnalysis(ArrayList<dictAnalysis> analysis) {
		this.analysis = analysis;
	}
	public int getTotalTweets() {
		return totalTweets;
	}
	public void setTotalTweets(int totalTweets) {
		this.totalTweets = totalTweets;
	}

}
