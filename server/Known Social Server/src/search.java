import java.io.IOException;
import java.util.ArrayList;


public class search implements java.io.Serializable{

	
	public Report search(String searchWord, int zipcode, int count, String userSearched) throws IOException {
		
		
		TweetSearch twitterSearch = new TweetSearch();
		@SuppressWarnings("unused")
		
		ArrayList<String> foundTweets = twitterSearch.TweetSearch(searchWord, zipcode, count);
		
		StanfordModelEngine stanford = new StanfordModelEngine();
		
		DictionaryModelEngine dictionary = new DictionaryModelEngine();
		
		StanfordReport stanford_Report = stanford.StanfordModelEngine(searchWord, foundTweets);
		
		DictionaryReport dictionary_Report = dictionary.DictionaryModelEngine(searchWord, foundTweets);
		
		MySQLAccessData dao = new MySQLAccessData();
		
		Report report = new Report();
		int id =0;
		try {
			id = dao.getLastReportID("reports");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		id++;
		report.setReportID(id);
		report.setStanfordID(stanford_Report.getReportID());
		report.setDictionaryID(dictionary_Report.getReportID());
		report.setStanford_Report(stanford_Report);
		report.setDictionary_Report(dictionary_Report);
		report.setUserSearched(userSearched);
		
		
		try {
			dao.writeReport(report);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		return report;
		

	}
}
