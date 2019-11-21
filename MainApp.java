package com.stanford_nlp.SentimentAnalyzer;

import java.io.File;
import java.io.FileNotFoundException;
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
import twitter4j.GeoLocation;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class MainApp {

	public static void main(String[] args) throws IOException {
		String term = "", ZIP = "", lonS = "", latS = "", resUnit = "mi";
		double lon = 0, lat = 0, res = 20;

		ArrayList<String> tweets2 = new ArrayList<String>();
		ArrayList<String> temp = new ArrayList<>();

		int count = 0, howMany = 0;
		Scanner in = new Scanner(System.in);
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey("bu6aoV7hyuA5Xn9aFTLPaFRLJ")
				.setOAuthConsumerSecret("ugGNbis73pcARLFzuoTUgQB0N4TLP4OwgS8yWKxnbGYLyn9zh5")
				.setOAuthAccessToken("349635887-bvPtoSPg3DXA9EtEp60G2LVpdezwJEEyDS0vuKcz")
				.setOAuthAccessTokenSecret("GmtKX4MwbPTydgjZYRBrDmpXW9TnfhegV9NAWjIfl8taC");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		try {
			System.out.println("Enter a search word: ");
			term = in.nextLine();
			System.out.println("Enter the number of results you want: ");
			howMany = in.nextInt();
			System.out.println("Enter a ZIP code for search location(or leave this field blank): ");
			ZIP = in.next();
			// TEST ZIP CODE
			int zipInt = Integer.parseInt(ZIP);
			if (ZIP != "" && zipInt > 0) {
				try {

					CsvReader products = new CsvReader("C:\\Users\\Aditya Yadav\\Downloads\\zips.csv");

					products.readHeaders();
					int numOfHeaders = products.getHeaderCount();
					//System.out.println("Number of headers" + numOfHeaders);
					try {
						while (products.readRecord()) {

							String lookedupZip = products.get(products.getHeader(0));
							if (lookedupZip.equals(ZIP)) {
								latS = products.get(products.getHeader(2));
								lonS = products.get(products.getHeader(3));
							}

						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
			
			lat = Double.parseDouble(latS);
			lon = Double.parseDouble(lonS);
			// END OF ZIP CODE
			// GET QUERIES 
			Query query = new Query().geoCode(new GeoLocation(lat, lon), res, resUnit);
			query = new Query(term + " -filter:retweets -filter:links -filter:replies -filter:images lang:en");
			QueryResult result = null;
			do {
				result = twitter.search(query);
				List<Status> tweets = result.getTweets();
				for (Status tweet : tweets) {
					if (tweet.getText().length() > 150) {
						tweets2.add(tweet.getText());
						count++;
					}
				}
			} while ((query = result.nextQuery()) != null && count < howMany);

		} catch (

		TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to search tweets: " + te.getMessage());
			System.exit(-1);
		}
		// READ FILES
		Scanner s = new Scanner(new File("C:\\Users\\Aditya Yadav\\Documents\\negative-words.txt"));
		ArrayList<String> negativeWords = new ArrayList<String>();
		while (s.hasNext()) {
			negativeWords.add(s.next());
		}
		s.close();
		// System.out.println(negativeWords );
		s = new Scanner(new File("C:\\Users\\Aditya Yadav\\Documents\\positive-words.txt"));
		ArrayList<String> positiveWords = new ArrayList<String>();
		while (s.hasNext()) {
			positiveWords.add(s.next());
		}
		// System.out.println(positiveWords);
		s.close();
		// DATABASE ANALYSIS
		int posi = 0, negi = 0, total = 0, posT = 0, negT = 0;
		for (int i = 0; i < tweets2.size(); i++) {
			// get tweets at i and save them as lowercase strings
			System.out.println("Tweet: " + tweets2.get(i));
			String text = tweets2.get(i).toLowerCase();
			text = text.replace('@', ' ').replace('#', ' ');

			// split each tweet into separate words
			String[] words = text.split(" ");
			for (int h = 0; h < positiveWords.size(); h++) {
				if (Arrays.asList(words).contains(positiveWords.get(h))) {
					posi++;
					posT++;
				}
			}
			for (int g = 0; g < negativeWords.size(); g++) {
				if (Arrays.asList(words).contains(negativeWords.get(g))) {
					negi++;
					negT++;
				}
			}
			if (posi > negi)
				total++;
			if (negi > posi)
				total--;
			posi = 0;
			negi = 0;

			System.out.println("Positive count: " + posT);
			System.out.println("Negative count: " + negT);
			System.out.println("============================================");
			posT = 0;
			negT = 0;
		}

		if (total > 0) {
			System.out.println("Sentiment Type: Positive");
		} else if (total < 0) {
			System.out.println("Sentiment Type: Negative");
		} else if (total == 0) {
			System.out.println("Sentiment Type: Neutral");
		}
		System.out.println("Final points: " + total);
		System.out.println(count);

		// STANFORD NLP IMPLEMENTATION
		double veryPosTotal = 0.0, posTotal = 0.0, neutTotal = 0.0, negTotal = 0.0, veryNegTotal = 0.0;
		for (int i = 0; i < tweets2.size(); i++) {
			String text = tweets2.get(i).toLowerCase();
			text = text.replace('@', ' ').replace('#', ' ');
			SentimentAnalyzer sentimentAnalyzer = new SentimentAnalyzer();
			sentimentAnalyzer.initialize();
			SentimentResult sentimentResult = sentimentAnalyzer.getSentimentResult(text);

			veryPosTotal = veryPosTotal + sentimentResult.getSentimentClass().getVeryPositive();
			posTotal = posTotal + sentimentResult.getSentimentClass().getPositive();
			neutTotal = neutTotal + sentimentResult.getSentimentClass().getNeutral();
			veryNegTotal = veryNegTotal + sentimentResult.getSentimentClass().getVeryNegative();
			negTotal = negTotal + sentimentResult.getSentimentClass().getNegative();

			System.out.println("==========================================================================");
			System.out.println("Tweet: " + text);
			System.out.println("Sentiment Score: " + sentimentResult.getSentimentScore());
			System.out.println("Sentiment Type: " + sentimentResult.getSentimentType());
			System.out.println("Very positive: " + sentimentResult.getSentimentClass().getVeryPositive() + "%");
			System.out.println("Positive: " + sentimentResult.getSentimentClass().getPositive() + "%");
			System.out.println("Neutral: " + sentimentResult.getSentimentClass().getNeutral() + "%");
			System.out.println("Negative: " + sentimentResult.getSentimentClass().getNegative() + "%");
			System.out.println("Very negative: " + sentimentResult.getSentimentClass().getVeryNegative() + "%");
			System.out.println("==========================================================================");

		}

		veryPosTotal = veryPosTotal / tweets2.size();
		posTotal = posTotal / tweets2.size();
		neutTotal = neutTotal / tweets2.size();
		veryNegTotal = veryNegTotal / tweets2.size();
		negTotal = negTotal / tweets2.size();
		double pos = 0.0, neg = 0.0, neut = 0.0;
		pos = veryPosTotal + posTotal + (neutTotal * 0.1);
		neg = veryNegTotal + negTotal;
		neut = neutTotal * 0.9;
		System.out.println("Word: " + term);
		if (pos > neg) {
			System.out.println("Sentiment Type: Positive");
		} else if (neg > pos) {
			System.out.println("Sentiment Type: Negative");
		} else if (neut > pos && neut > neg) {
			System.out.println("Sentiment Type: Neutral");
		}
		System.out.println("Very positive: " + Math.round(veryPosTotal) + "%");
		System.out.println("Positive: " + Math.round(posTotal) + "%");
		System.out.println("Neutral: " + Math.round(neutTotal) + "%");
		System.out.println("Negative: " + Math.round(negTotal) + "%");
		System.out.println("Very negative: " + Math.round(veryNegTotal) + "%");

	}
}
