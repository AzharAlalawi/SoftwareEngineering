

import java.util.ArrayList;

public class StanfordReport implements java.io.Serializable{

	
	private int databaseIdStart;
	private int databaseIdEnd;
	private String userSearched;
	private String wordSearched;
	private String sentimentalOutcome;
	private double veryNegative, negative, neutral, positive, veryPositive;  
	private ArrayList<Tweet> analysis;
	private int searchMode;
	private int reportID;
	
	
	

	
	public StanfordReport(int reportID, int databaseIdStart, int databaseIdEnd, String userSearched, String wordSearched,
			String sentimentalOutcome, double veryNegative, double negative, double neutral, double positive,
			double veryPositive, ArrayList<Tweet> analysis, int searchMode) {
		super();
		this.reportID = reportID;
		this.databaseIdStart = databaseIdStart;
		this.databaseIdEnd = databaseIdEnd;
		this.userSearched = userSearched;
		this.wordSearched = wordSearched;
		this.sentimentalOutcome = sentimentalOutcome;
		this.veryNegative = veryNegative;
		this.negative = negative;
		this.neutral = neutral;
		this.positive = positive;
		this.veryPositive = veryPositive;
		this.analysis = analysis;
		this.searchMode = searchMode;
	}

	public StanfordReport() {
		// TODO Auto-generated constructor stub
	}

	public int getReportID() {
		return reportID;
	}

	public void setReportID(int reportID) {
		this.reportID = reportID;
	}

	public int getSearchMode() {
		return searchMode;
	}
	public void setSearchMode(int searchMode) {
		this.searchMode = searchMode;
	}


	public int getDatabaseIdStart() {
		return databaseIdStart;
	}

	public void setDatabaseIdStart(int databaseIdStart) {
		this.databaseIdStart = databaseIdStart;
	}

	public int getDatabaseIdEnd() {
		return databaseIdEnd;
	}

	public void setDatabaseIdEnd(int databaseIdEnd) {
		this.databaseIdEnd = databaseIdEnd;
	}


	public String getUserSearched() {
		return userSearched;
	}


	public void setUserSearched(String userSearched) {
		this.userSearched = userSearched;
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




	public double getVeryNegative() {
		return veryNegative;
	}




	public void setVeryNegative(double veryNegative) {
		this.veryNegative = veryNegative;
	}




	public double getNegative() {
		return negative;
	}




	public void setNegative(double negative) {
		this.negative = negative;
	}




	public double getNeutral() {
		return neutral;
	}




	public void setNeutral(double neutral) {
		this.neutral = neutral;
	}




	public double getPositive() {
		return positive;
	}




	public void setPositive(double positive) {
		this.positive = positive;
	}




	public double getVeryPositive() {
		return veryPositive;
	}




	public void setVeryPositive(double veryPositive) {
		this.veryPositive = veryPositive;
	}




	public ArrayList<Tweet> getAnalysis() {
		return analysis;
	}




	public void setAnalysis(ArrayList<Tweet> analysis) {
		this.analysis = analysis;
	}
	
}
