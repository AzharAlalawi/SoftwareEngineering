

public class dictAnalysis implements java.io.Serializable{

	
	private int analysisID;
	private String TweetText;
	private int positiveCount;
	private int negativeCount;
	private String sentimentResult;
	

	public dictAnalysis(int analysisID, String tweetText, int positiveCount, int negativeCount, String sentimentResult) {
		super();
		this.analysisID = analysisID;
		this.TweetText = tweetText;
		this.positiveCount = positiveCount;
		this.negativeCount = negativeCount;
		this.sentimentResult = sentimentResult;
	}
	public dictAnalysis() {
		// TODO Auto-generated constructor stub
	}
	public int getAnalysisID() {
		return analysisID;
	}
	public void setAnalysisID(int analysisID) {
		this.analysisID = analysisID;
	}
	public String getTweetText() {
		return TweetText;
	}
	public void setTweetText(String tweetText) {
		TweetText = tweetText;
	}
	public int getPositiveCount() {
		return positiveCount;
	}
	public void setPositiveCount(int positiveCount) {
		this.positiveCount = positiveCount;
	}
	public int getNegativeCount() {
		return negativeCount;
	}
	public void setNegativeCount(int negativeCount) {
		this.negativeCount = negativeCount;
	}
	public String getSentimentResult() {
		return sentimentResult;
	}
	public void setSentimentResult(String sentimentResult) {
		this.sentimentResult = sentimentResult;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
