package com.stanford_nlp.SentimentAnalyzer;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.*;

import com.stanford_nlp.model.SentimentResult;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


public class MainApp {
	
	
	public static void main(String[] args) throws IOException {
		String term = "";

		ArrayList<String> tweets2 = new ArrayList<String>();
		ArrayList<String> temp = new ArrayList<>();

		int count = 0;
		Scanner in = new Scanner(System.in);
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
        	term = in.nextLine();
            Query query = new Query(term + " -filter:retweets -filter:links -filter:replies -filter:images lang:en");
            QueryResult result;
            do {
            	
				result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                	if (tweet.getText().length() > 100) {
                	tweets2.add(tweet.getText());
                	count++;
                	}
                  }
            } while (((query = result.nextQuery()) != null) && count < 50);
           
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
		
		
        Scanner s = new Scanner(new File("C:\\Users\\Aditya Yadav\\Documents\\negative-words.txt"));
        ArrayList<String> negativeWords = new ArrayList<String>();
        while (s.hasNext()){
            negativeWords.add(s.next());
        }
        s.close();
        System.out.println(negativeWords );
        s = new Scanner(new File("C:\\Users\\Aditya Yadav\\Documents\\positive-words.txt"));
        ArrayList<String> positiveWords = new ArrayList<String>();
        while (s.hasNext()){
            positiveWords.add(s.next());
        }
        System.out.println( positiveWords);
        s.close();
		
        
        int pos = 0, neg = 0, total = 0;
//		double posTotal = 0.0, veryPosTotal = 0.0, negTotal = 0.0, veryNegTotal = 0.0, neutTotal = 0.0;
		for(int i=0; i<tweets2.size(); i++) {
		String text = tweets2.get(i).toLowerCase();
		text = text.replace('@', ' ').replace('#', ' ');
		System.out.println("Tweet: " + text);
		text.replace('@', ' ');
		text.replace('#', ' ');
			for(int j=0; j<text.length(); j++) {
				String[] words = text.split(" ");
				for(int h=0; h<words.length; h++) {
					if(positiveWords.contains(words[h])) {
						pos++;
					}
					if(negativeWords.contains(words[h])) {
						neg++;
					}
				}
				if(pos > neg)
					total += 1;
				if(neg > pos)
					total -= 1;
				
			}
			System.out.println("Positive count: " + pos);
			System.out.println("Negative count: " + neg);
			System.out.println("============================================");
			pos = 0;
			neg = 0;
		}
		if (total > 0) {
			System.out.println("Sentiment Type: Positive");
		}
		else if (total < 0) {
			System.out.println("Sentiment Type: Negative");
		}
		else if (total == 0) {
			System.out.println("Sentiment Type: Neutral");
		}
		System.out.println("Final points: " + total);
//		SentimentAnalyzer sentimentAnalyzer = new SentimentAnalyzer();
//		sentimentAnalyzer.initialize();
//		SentimentResult sentimentResult = sentimentAnalyzer.getSentimentResult(text);
//		
//		veryPosTotal = veryPosTotal + sentimentResult.getSentimentClass().getVeryPositive();
//		posTotal = posTotal + sentimentResult.getSentimentClass().getPositive();
//		neutTotal = neutTotal + sentimentResult.getSentimentClass().getNeutral();
//		veryNegTotal = veryNegTotal + sentimentResult.getSentimentClass().getVeryNegative();
//		negTotal = negTotal + sentimentResult.getSentimentClass().getNegative();
//		
//		System.out.println("==========================================================================");
//		System.out.println("Tweet: " + text);
//		System.out.println("Sentiment Score: " + sentimentResult.getSentimentScore());
//		System.out.println("Sentiment Type: " + sentimentResult.getSentimentType());
//		System.out.println("Very positive: " + sentimentResult.getSentimentClass().getVeryPositive()+"%");
//		System.out.println("Positive: " + sentimentResult.getSentimentClass().getPositive()+"%");
//		System.out.println("Neutral: " + sentimentResult.getSentimentClass().getNeutral()+"%");
//		System.out.println("Negative: " + sentimentResult.getSentimentClass().getNegative()+"%");
//		System.out.println("Very negative: " + sentimentResult.getSentimentClass().getVeryNegative()+"%");
//		System.out.println("==========================================================================");
//		
//		
//		
//		}
//		
//		veryPosTotal = veryPosTotal/tweets2.size();
//		posTotal = posTotal/tweets2.size();
//		neutTotal = neutTotal/tweets2.size();
//		veryNegTotal = veryNegTotal/tweets2.size();
//		negTotal = negTotal/tweets2.size();
//		double pos = 0.0, neg = 0.0, neut = 0.0;
//		pos = veryPosTotal + posTotal + (neutTotal * 0.1);
//		neg = veryNegTotal + negTotal;
//		neut = neutTotal * 0.9;
//		System.out.println("Word: " + term);
//		if (pos > neg) {
//			System.out.println("Sentiment Type: Positive");
//		}
//		else if (neg > pos) {
//			System.out.println("Sentiment Type: Negative");
//		}
//		else if (neut > pos && neut > neg) {
//			System.out.println("Sentiment Type: Neutral");
//		}
//		System.out.println("Very positive: " + Math.round(veryPosTotal)+"%");
//		System.out.println("Positive: " + Math.round(posTotal)+"%");
//		System.out.println("Neutral: " + Math.round(neutTotal)+"%");
//		System.out.println("Negative: " + Math.round(negTotal)+"%");
//		System.out.println("Very negative: " + Math.round(veryNegTotal)+"%");
//		
//
	}
	
}
	
