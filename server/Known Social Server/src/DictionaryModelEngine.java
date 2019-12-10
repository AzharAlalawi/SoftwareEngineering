



import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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


public class DictionaryModelEngine implements java.io.Serializable{
	
	

	@SuppressWarnings("null")
	public DictionaryReport DictionaryModelEngine(String wordSearched, ArrayList<String> searchedTweets) {
		
	
		
		 MySQLAccessData dao = new MySQLAccessData();

			// Get words from database
			ArrayList<String> negativeWords = null;
			ArrayList<String> positiveWords = null;
			ArrayList<dictAnalysis> list = new ArrayList<>();
			try {
				negativeWords = dao.readNegative();
				positiveWords = dao.readPositive();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dictAnalysis analysis = new dictAnalysis();
			DictionaryReport dictReport = new DictionaryReport();
			
			// DATABASE ANALYSIS
			int posi = 0, negi = 0, total = 0, posT = 0, negT = 0;
			
			
			
			
			int rowCountAnalysis = 0;
			try {
				rowCountAnalysis  = dao.getLastID("DictionaryAnalysis");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int analysisID = 0;
			if (rowCountAnalysis > 0) {
				try {
					analysisID = dao.getLastAnalysisID("DictionaryAnalysis");
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else 
			{
				analysisID = 0;
			}
			
			dictReport.setDatabaseStartID(++analysisID);
			int idCount = analysisID;
			
			for (int i = 0; i < searchedTweets.size(); i++) 
			{
				// get tweets at i and save them as lowercase strings
		
				analysis.setAnalysisID(analysisID++);
				String text = searchedTweets.get(i).toLowerCase();
				text = text.replaceAll("[^a-zA-Z0-9\\s]", "");

				// split each tweet into separate words
				String[] words = text.split(" ");
				
				
				
				analysis.setTweetText(text);
				for (int h = 0; h < positiveWords.size(); h++) 
				{
					if (Arrays.asList(words).contains(positiveWords.get(h))) {
						posi++;
						posT++;
						
					}
				}
				for (int g = 0; g < negativeWords.size(); g++) 
				{
					if (Arrays.asList(words).contains(negativeWords.get(g))) {
						negi++;
						negT++;
						
					}
				}
				analysis.setPositiveCount(posi);
				analysis.setNegativeCount(negi);
				
				
				String result = null;
				if (posi > negi)
				{
					result = "Positive";
				}
				
				if (negi > posi)
				{
					result = "Negative";
				}
				
				if (negi == posi)
				{
					result = "Neutral";
				}
					
				
				
				
				analysis.setSentimentResult(result);
				posi = 0;
				negi = 0;
				try {
					dao.writeDictAnalysis(analysis);
					idCount++;
					list.add(analysis);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			int finalTotal =0;
			String finalResult = null;
			if (posT > negT)
				finalTotal++;
			
			if (negT > posT)
				finalTotal--;
			
			if (finalTotal > 0) {
				finalResult = "Positive";
			} else if (finalTotal < 0) {
				finalResult = "Negative";
			} else if (finalTotal == 0) {
				finalResult = "Neutral";
			}
			dictReport.setAnalysis(list);
			int rowCount = 0;
			try {
				rowCount = dao.getLastID("DictionaryReport");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int id = 0;
			if (rowCount>0) {
				try {
					id = dao.getLastReportID("DictionaryReport");
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else 
			{
				id = 0;
			}
			
			id++;
			dictReport.setReportID(id);
			dictReport.setSentimentalOutcome(finalResult);
			dictReport.setTotalPositive(posT);
			dictReport.setTotalNegative(negT);
			dictReport.setTotalTweets(searchedTweets.size());
			dictReport.setWordSearched(wordSearched);
			dictReport.setSentimentalOutcome(finalResult);
			dictReport.setDatabaseEndID(idCount-1);
			
			try {
				dao.writeDictionaryReport(dictReport);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return dictReport;
			
			
		 
		 
		 
		
	}
}