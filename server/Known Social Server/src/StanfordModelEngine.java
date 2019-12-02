

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.stanford_nlp.model.SentimentResult;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class StanfordModelEngine {

	public StanfordReport StanfordModelEngine(String wordSearched, ArrayList<String> searchedTweets) {

		MySQLAccessData dao = new MySQLAccessData();
		ArrayList<Tweet> tweets = new ArrayList<>();
		int pos = 0, neg = 0, neut = 0;
		double posTotal = 0, veryPosTotal = 0, negTotal = 0, veryNegTotal = 0, neutTotal = 0;

		int rowCountAnalysis = 0;
		try {
			rowCountAnalysis = dao.getLastID("Analysis");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int analysisID = 0;
		if (rowCountAnalysis > 0) {
			try {
				analysisID = dao.getLastAnalysisID("Analysis");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			analysisID = 0;
		}

		Tweet newTweet = new Tweet();
		StanfordReport newReport = new StanfordReport();
		newReport.setDatabaseIdStart(++analysisID);

		int idCount = analysisID;

		for (int i = 0; i < searchedTweets.size(); i++) 
		{

			
			newTweet.setID(analysisID++);
			String text = searchedTweets.get(i);
			text = text.replaceAll("[^a-zA-Z0-9\\s]", "");
			newTweet.setText(text);

			SentimentAnalyzer sentimentAnalyzer = new SentimentAnalyzer();
			sentimentAnalyzer.initialize();
			SentimentResult sentimentResult = sentimentAnalyzer.getSentimentResult(text);

			if (sentimentResult.getSentimentType().equals("Negative")
					|| sentimentResult.getSentimentType().equals("Very Negative")) {
				neg++;

			}
			if (sentimentResult.getSentimentType().equals("Positive")
					|| sentimentResult.getSentimentType().equals("Very Positive")) {
				pos++;

			}
			if (sentimentResult.getSentimentType().equals("Neutral")) {
				neut++;

			}
			newTweet.setVeryNegative(sentimentResult.getSentimentClass().getVeryNegative());

			newTweet.setNegative(sentimentResult.getSentimentClass().getNegative());

			newTweet.setNeutral(sentimentResult.getSentimentClass().getNeutral());

			newTweet.setPositive(sentimentResult.getSentimentClass().getPositive());
			newTweet.setVeryPositive(sentimentResult.getSentimentClass().getVeryPositive());

			veryPosTotal = veryPosTotal + sentimentResult.getSentimentClass().getVeryPositive();
			posTotal = posTotal + sentimentResult.getSentimentClass().getPositive();
			neutTotal = neutTotal + sentimentResult.getSentimentClass().getNeutral();
			veryNegTotal = veryNegTotal + sentimentResult.getSentimentClass().getVeryNegative();
			negTotal = negTotal + sentimentResult.getSentimentClass().getNegative();
			String outcome = sentimentResult.getSentimentType();
			newTweet.setResult(outcome);
			
			try {
				dao.writeTweet(newTweet);
				idCount++;
				tweets.add(newTweet);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		int rowCount = 0;
		try {
			rowCount = dao.getLastID("StanfordReport");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int id = 0;
		if (rowCount > 0) {
			try {
				id = dao.getLastReportID("StanfordReport");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			id = 0;
		}

		String outcome = null;
		veryPosTotal = veryPosTotal / searchedTweets.size();
		posTotal = posTotal / searchedTweets.size();
		neutTotal = neutTotal / searchedTweets.size();
		veryNegTotal = veryNegTotal / searchedTweets.size();
		negTotal = negTotal / searchedTweets.size();
		
		
		
		id++;
		newReport.setReportID(id);
		newReport.setDatabaseIdEnd(idCount-1);
		newReport.setAnalysis(tweets);
		newReport.setWordSearched(wordSearched);
		newReport.setVeryNegative(veryNegTotal);
		newReport.setNegative(negTotal);
		newReport.setNeutral(neutTotal);
		newReport.setPositive(posTotal);
		newReport.setVeryPositive(veryPosTotal);
		if (pos > neg && pos >= neut) {
			outcome = "Positive";
		}
		if (neut > pos && neut > neg) {
			outcome = "Neutral";
		}
		if (neg >= neut && neg > pos) {
			outcome = "Negative";
		}
		newReport.setSentimentalOutcome(outcome);

		try {
			dao.writeStanfordReport(newReport);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return newReport;

	}
}