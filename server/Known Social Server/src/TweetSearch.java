
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TweetSearch implements java.io.Serializable{

	@SuppressWarnings("deprecation")
	public ArrayList<String> TweetSearch(String searchWord, int zipcode, int tweetCount) throws IOException {
			 MySQLAccessData dao = new MySQLAccessData();
			    try {
					dao.readDataBase();
				} catch (Exception e) {
					System.out.println("Error with database");
					e.printStackTrace();
				}
			ArrayList<String> tweets2 = new ArrayList<String>();

			int count = 0;
			
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true)
			  .setOAuthConsumerKey("rWwbNiLYcAUSsUur1dcFVMl9A")
			  .setOAuthConsumerSecret("W5bG3q6Q9I7esiaVq7T0410hwuFDRDjqADTSJIg6JEv4bPxRqy")
			  .setOAuthAccessToken("423687694-PFAPIfKZnwFyXA4tkuEfFvtrizbyWgKWE0l2swAP")
			  .setOAuthAccessTokenSecret("FFBHwAqz7vlmqVUC6CVlcBr98wENmlLo9U5tynAg4fs8c");
			TwitterFactory tf = new TwitterFactory(cb.build());
			Twitter twitter = tf.getInstance();

			
	        try {
	        	System.out.println("Enter a search word: ");
	        	String term = searchWord;
	            Query query = new Query(term + " -filter:retweets -filter:links -filter:replies -filter:images lang:en");
	            Location loc = new Location();
	            if (zipcode > 0) {
	            	
	            	try {
						loc = dao.findLocation(zipcode);
						 double lat = loc.getLatitude();
						 double log = loc.getLongitude();
						query.geoCode(new GeoLocation(lat, log), 50, "mi");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	
	            
	            }
	           
	            QueryResult result;
	            do {
	            	
					result = twitter.search(query);
	                List<Status> tweets = result.getTweets();
	                for (Status tweet : tweets) {
	                   // System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
	                	tweets2.add(tweet.getText());         	
	                    count++;
	                }
	            } while (((query = result.nextQuery()) != null) && count < tweetCount);
	           
	        } catch (TwitterException te) {
	            te.printStackTrace();
	            System.out.println("Failed to search tweets: " + te.getMessage());
	            System.exit(-1);
	        }
		
		
		
		return tweets2;
		 
		
		
		
		
		
		
	}
}
