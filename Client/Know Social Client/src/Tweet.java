

public class Tweet implements java.io.Serializable{

	
	private double veryNegative, negative, neutral, positive, veryPositive;  
	private String tweetText;
	private int tweetID;
	private String result;
	
	public Tweet(int id, String text, String res, double veryNeg, double neg, double neut, double pos, double veryPos)
    {
      
		tweetID = id;
		tweetText = text;
		result = res;
		veryNegative = veryNeg;
		negative = neg;
		neutral = neut;
		positive = pos;
		veryPositive = veryPos;
		
		
    }
	public Tweet() {
		// TODO Auto-generated constructor stub
	}
	public void setID(int id) {
		tweetID = id;
	}
	public void setText(String text) {
		tweetText = text;
	}
	public void setResult(String res) {
		result = res;
	}
	public void setVeryNegative(double veryNeg) {
		veryNegative = veryNeg;
	}
	public void setNegative(double neg) {
		negative = neg;
	}
	public void setNeutral(double neut) {
		neutral = neut;
	}
	public void setVeryPositive(double veryPos) {
		veryPositive = veryPos;
	}
	public void setPositive(double pos) {
		positive = pos;
	}
	
	public int getID() {
		return tweetID;
	}
	public String getText() {
		return tweetText;
	}
	public String getResult() {
		return result;
	}
	public double getVeryPositive() {
		return veryPositive;
	}
	public double getPositive() {
		return positive;
	}
	public double getNeutral() {
		return neutral;
	}
	public double getNegative() {
		return negative;
	}
	public double getVeryNegative() {
		return veryNegative;
	}
	
	
	
	
	
}
