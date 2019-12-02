
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class MySQLAccessData {

	private Connection connect = null;
	private Statement statement = null;
	public PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	final private String host = "127.0.0.1";
	final private String user = "knowsocial";
	final private String passwd = "knowsocial";

	public ArrayList<Report> getReports() throws Exception {

		try {
			// This will load the MySQL driver, each DB has its own driver
			ArrayList<Report> reports = new ArrayList<>();
			Report report2 = new Report();
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://" + host + "/knowSocial?" + "user=" + user + "&password=" + passwd);

			statement = connect.createStatement();
			String sql = "SELECT * FROM knowSocial.reports";
			ResultSet rs = statement.executeQuery(sql);
			MySQLAccessData dao = new MySQLAccessData();
			while (rs.next()) {

				report2.setReportID(rs.getInt("ReportID"));
				report2.setStanfordID(rs.getInt("StanfordID"));
				report2.setDictionaryID(rs.getInt("DictionaryID"));

				StanfordReport stanReport = new StanfordReport();
				stanReport = dao.getStanfordReports(report2.getStanfordID());
				DictionaryReport dictReport = new DictionaryReport();
				dictReport = dao.getDictionaryReports(report2.getDictionaryID());

				report2.setStanford_Report(stanReport);
				report2.setDictionary_Report(dictReport);
				report2.setUserSearched(rs.getString("userSearched"));
				System.out.println(report2.getReportID());
				reports.add(report2);
			}
			for (int i = 0; i < reports.size(); i++) {
				System.out.println(reports.get(i).getReportID());
			}

			return reports;
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	public Report getReport(int id) throws Exception {

		try {
			// This will load the MySQL driver, each DB has its own driver

			Report report = new Report();
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://" + host + "/knowSocial?" + "user=" + user + "&password=" + passwd);

			statement = connect.createStatement();
			String sql = "SELECT * FROM knowSocial.reports";
			ResultSet rs = statement.executeQuery(sql);
			MySQLAccessData dao = new MySQLAccessData();
			while (rs.next()) {
				if (rs.getInt("ReportID") == id) {
					report.setReportID(rs.getInt("ReportID"));
					report.setStanfordID(rs.getInt("StanfordID"));
					report.setDictionaryID(rs.getInt("DictionaryID"));
					StanfordReport stanReport = new StanfordReport();
					stanReport = dao.getStanfordReports(report.getStanfordID());
					DictionaryReport dictReport = new DictionaryReport();
					dictReport = dao.getDictionaryReports(report.getDictionaryID());
					report.setStanford_Report(stanReport);
					report.setDictionary_Report(dictReport);
					report.setUserSearched(rs.getString("userSearched"));
					break;
				}
			}

			return report;
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	public StanfordReport getStanfordReports(int id) throws Exception {

		try {
			// This will load the MySQL driver, each DB has its own driver

			StanfordReport stanReport = new StanfordReport();
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://" + host + "/knowSocial?" + "user=" + user + "&password=" + passwd);

			statement = connect.createStatement();
			String sql = "SELECT * FROM knowSocial.StanfordReport";
			ResultSet rs = statement.executeQuery(sql);
			MySQLAccessData dao = new MySQLAccessData();
			ArrayList<Tweet> tweets = new ArrayList<>();
			while (rs.next()) {
				if (rs.getInt("ReportID") == id) {
					stanReport.setReportID(rs.getInt("ReportID"));
					stanReport.setDatabaseIdStart(rs.getInt("DatabaseStartID"));
					stanReport.setDatabaseIdEnd(rs.getInt("DatabaseEndID"));
					stanReport.setDatabaseIdStart(rs.getInt("DatabaseStartID"));
					stanReport.setWordSearched(rs.getString("WordSearched"));
					stanReport.setSentimentalOutcome(rs.getString("sentimentalOutcome"));
					stanReport.setVeryNegative(rs.getDouble("veryNegative"));
					stanReport.setNegative(rs.getDouble("negative"));
					stanReport.setNeutral(rs.getDouble("neutral"));
					stanReport.setPositive(rs.getDouble("positive"));
					stanReport.setVeryPositive(rs.getDouble("veryPositive"));
					
				
					
					 	int start = rs.getInt("DatabaseStartID");
					 	int end = rs.getInt("DatabaseEndID");
		    		
		    		 for (int i = start; i <= end; i++)
		    		 {
		    			
		    			tweets.add(dao.getStanfordAnalysis(i));
		   
		    			
		    		 }

			

				}

			}
			stanReport.setAnalysis(tweets);

			return stanReport;
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	public DictionaryReport getDictionaryReports(int id) throws Exception {
	    
	  try {
	      // This will load the MySQL driver, each DB has its own driver
		  ArrayList<dictAnalysis> tweets = new ArrayList<>();
		  DictionaryReport report = new DictionaryReport();
	      // Setup the connection with the DB
	      connect = DriverManager
	          .getConnection("jdbc:mysql://" + host + "/knowSocial?"
	              + "user=" + user + "&password=" + passwd );

	      statement = connect.createStatement();
	      String sql = "SELECT * FROM knowSocial.DictionaryReport";
	      ResultSet rs = statement.executeQuery(sql);
	      
	      MySQLAccessData dao = new MySQLAccessData();
	      while(rs.next())
	      {
	    	  if (rs.getInt("ReportID") == id) 
	    	  {
	    		  report.setReportID(rs.getInt("ReportID")); 
	    		  report.setTotalPositive(rs.getInt("TotalPositive"));
	    		  report.setTotalNegative(rs.getInt("TotalNegative"));
	    		  report.setWordSearched(rs.getString("wordSearched"));
	    		  report.setTotalTweets(rs.getInt("TotalCount"));
	    		  report.setSentimentalOutcome(rs.getString("SentimentalOutcome"));
	    		  report.setDatabaseStartID(rs.getInt("DatabaseStartID"));
	    		  report.setDatabaseEndID(rs.getInt("DatabaseEndID"));
	    		  
	    		  int start = rs.getInt("DatabaseStartID");
	    		  int end = rs.getInt("DatabaseEndID");
	    		
	    		 for (int i = start; i <= end; i++)
	    		 {
	    			
	    			tweets.add(dao.getDictionaryAnalysis(i));
	    			
	    		 }
	    		 
	    		 break;
	    	  }
	    	
	      }
	      
	      report.setAnalysis(tweets);
	      return report;
	  } catch (Exception e) {
	      throw e;
	    } finally {
	      close();
	    }

	  }

	public dictAnalysis getDictionaryAnalysis(int id) throws Exception {

		try {
			// This will load the MySQL driver, each DB has its own driver
			dictAnalysis analysis = new dictAnalysis();
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://" + host + "/knowSocial?" + "user=" + user + "&password=" + passwd);

			statement = connect.createStatement();
			String sql = "SELECT * FROM knowSocial.DictionaryAnalysis";
			ResultSet rs = statement.executeQuery(sql);

			MySQLAccessData dao = new MySQLAccessData();
			while (rs.next()) {
				if (rs.getInt("AnalysisID") == id) {
					analysis.setAnalysisID(rs.getInt("AnalysisID"));
					analysis.setTweetText(rs.getString("TweetText"));
					analysis.setPositiveCount(rs.getInt("PositiveCount"));
					analysis.setNegativeCount(rs.getInt("NegativeCount"));
					analysis.setSentimentResult(rs.getString("SentimentResult"));

				}

			}

			return analysis;
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	public Tweet getStanfordAnalysis(int id) throws Exception {

		try {
			// This will load the MySQL driver, each DB has its own driver
			Tweet analysis = new Tweet();
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://" + host + "/knowSocial?" + "user=" + user + "&password=" + passwd);

			statement = connect.createStatement();
			String sql = "SELECT * FROM knowSocial.analysis";
			ResultSet rs = statement.executeQuery(sql);

			MySQLAccessData dao = new MySQLAccessData();
			while (rs.next()) {
				if (rs.getInt("AnalysisID") == id) {
					analysis.setID(rs.getInt("AnalysisID"));
					analysis.setText(rs.getString("TweetText"));
					analysis.setVeryPositive(rs.getDouble("VeryPositivePercentage"));
					analysis.setPositive(rs.getDouble("PositivePercentage"));
					analysis.setNeutral(rs.getDouble("NeutralPercentage"));
					analysis.setNegative(rs.getDouble("NegativePercentage"));
					analysis.setVeryNegative(rs.getDouble("VeryNegativePercentage"));
					analysis.setResult(rs.getString("SentimentResult"));

				}

			}

			return analysis;
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	public int getLastReportID(String tableName) throws Exception {

		try {
			// This will load the MySQL driver, each DB has its own driver

			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://" + host + "/knowSocial?" + "user=" + user + "&password=" + passwd);

			statement = connect.createStatement();
			String sql = "SELECT * FROM knowSocial." + tableName;
			ResultSet rs = statement.executeQuery(sql);
			rs.last();
			int id = rs.getInt("ReportID");
			return id;
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	public int getLastAnalysisID(String tableName) throws Exception {

		try {
			// This will load the MySQL driver, each DB has its own driver

			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://" + host + "/knowSocial?" + "user=" + user + "&password=" + passwd);

			statement = connect.createStatement();
			String sql = "SELECT * FROM knowSocial." + tableName;
			ResultSet rs = statement.executeQuery(sql);
			rs.last();
			int id = rs.getInt("AnalysisID");
			return id;
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	public void writeReport(Report report) throws Exception {

		try {
			// This will load the MySQL driver, each DB has its own driver

			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://" + host + "/knowSocial?" + "user=" + user + "&password=" + passwd);

			// PreparedStatements can use variables and are more efficient

			String insertSQL = "INSERT INTO reports (ReportID, StanfordID, " + "DictionaryID, userSearched) " + "VALUES (?, ?, ?,?)";
			PreparedStatement preparedStatement = connect.prepareStatement(insertSQL);

			preparedStatement.setInt(1, report.getReportID());
			preparedStatement.setInt(2, report.getStanfordID());
			preparedStatement.setInt(3, report.getDictionaryID());
			preparedStatement.setNString(4, report.getUserSearched());

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	public void writeStanfordReport(StanfordReport report) throws Exception {

		try {
			// This will load the MySQL driver, each DB has its own driver

			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://" + host + "/knowSocial?" + "user=" + user + "&password=" + passwd);

			// PreparedStatements can use variables and are more efficient

			String insertSQL = "INSERT INTO StanfordReport (ReportID, DatabaseStartID, "
					+ "DatabaseEndID, WordSearched, " + "sentimentalOutcome, veryNegative, "
					+ "negative, neutral, positive, veryPositive) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connect.prepareStatement(insertSQL);

			preparedStatement.setInt(1, report.getReportID());
			preparedStatement.setInt(2, report.getDatabaseIdStart());
			preparedStatement.setInt(3, report.getDatabaseIdEnd());
			preparedStatement.setString(4, report.getWordSearched());
			preparedStatement.setString(5, report.getSentimentalOutcome());
			preparedStatement.setDouble(6, report.getVeryNegative());
			preparedStatement.setDouble(7, report.getNegative());
			preparedStatement.setDouble(8, report.getNeutral());
			preparedStatement.setDouble(9, report.getPositive());
			preparedStatement.setDouble(10, report.getVeryPositive());

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	public void writeDictionaryReport(DictionaryReport report) throws Exception {

		try {
			// This will load the MySQL driver, each DB has its own driver

			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://" + host + "/knowSocial?" + "user=" + user + "&password=" + passwd);

			// PreparedStatements can use variables and are more efficient

			String insertSQL = "INSERT INTO DictionaryReport (ReportID, TotalPositive, "
					+ "TotalNegative, wordSearched, " + "SentimentalOutcome, DatabaseStartID, "
					+ "DatabaseEndID, TotalCount) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connect.prepareStatement(insertSQL);

			preparedStatement.setInt(1, report.getReportID());
			preparedStatement.setInt(2, report.getTotalPositive());
			preparedStatement.setInt(3, report.getTotalNegative());
			preparedStatement.setString(4, report.getWordSearched());
			preparedStatement.setString(5, report.getSentimentalOutcome());
			preparedStatement.setInt(6, report.getDatabaseStartID());
			preparedStatement.setInt(7, report.getDatabaseEndID());
			preparedStatement.setInt(8, report.getTotalTweets());
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	public void writeTweet(Tweet tweet) throws Exception {

		try {
			// This will load the MySQL driver, each DB has its own driver

			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://" + host + "/knowSocial?" + "user=" + user + "&password=" + passwd);

			// PreparedStatements can use variables and are more efficient

			String insertSQL = "INSERT INTO Analysis (TweetText, VeryPositivePercentage, "
					+ "PositivePercentage, NeutralPercentage, " + "NegativePercentage, VeryNegativePercentage, "
					+ "SentimentResult, AnalysisID) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connect.prepareStatement(insertSQL);

			preparedStatement.setString(1, tweet.getText());
			preparedStatement.setDouble(2, tweet.getVeryPositive());
			preparedStatement.setDouble(3, tweet.getPositive());
			preparedStatement.setDouble(4, tweet.getNeutral());
			preparedStatement.setDouble(5, tweet.getNegative());
			preparedStatement.setDouble(6, tweet.getVeryNegative());
			preparedStatement.setString(7, tweet.getResult());
			preparedStatement.setInt(8, tweet.getID());
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	public void writeDictAnalysis(dictAnalysis analysis) throws Exception {

		try {
			// This will load the MySQL driver, each DB has its own driver

			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://" + host + "/knowSocial?" + "user=" + user + "&password=" + passwd);

			// PreparedStatements can use variables and are more efficient

			String insertSQL = "INSERT INTO DictionaryAnalysis (TweetText, PositiveCount, "
					+ "NegativeCount, SentimentResult, AnalysisID) " + "VALUES (?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connect.prepareStatement(insertSQL);

			preparedStatement.setString(1, analysis.getTweetText());
			preparedStatement.setInt(2, analysis.getPositiveCount());
			preparedStatement.setInt(3, analysis.getNegativeCount());
			preparedStatement.setString(4, analysis.getSentimentResult());
			preparedStatement.setInt(5, analysis.getAnalysisID());

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	public int getLastID(String tableName) throws Exception {

		try {
			// This will load the MySQL driver, each DB has its own driver

			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://" + host + "/knowSocial?" + "user=" + user + "&password=" + passwd);

			statement = connect.createStatement();
			String sql = "SELECT * FROM knowSocial." + tableName;
			ResultSet rs = statement.executeQuery(sql);
			rs.last();
			int row = rs.getRow();
			return row;
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	public void readTweet() throws Exception {

		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");

			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://" + host + "/knowSocial?" + "user=" + user + "&password=" + passwd);

			statement = connect.createStatement();
			String sql = "SELECT * FROM knowSocial.Tweets";
			ResultSet rs = statement.executeQuery(sql);
			System.out.println(rs.toString());

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	public Location findLocation(int zip) throws Exception {
		Location newLocation = new Location();
		try {
			// This will load the MySQL driver, each DB has its own driver

			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://" + host + "/knowSocial?" + "user=" + user + "&password=" + passwd);

			statement = connect.createStatement();
			String sql = "SELECT * FROM knowSocial.zipcodes";
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {

				int zipcode = rs.getInt("zipcode");
				if (zipcode == zip) 
				{
					newLocation.setLatitude(rs.getDouble("lat"));
					newLocation.setLongitude(rs.getDouble("lng"));
					break;
				}

			}

			return newLocation;

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	public ArrayList<String> readNegative() throws Exception {

		try {
			// This will load the MySQL driver, each DB has its own driver

			ArrayList<String> words = new ArrayList<>();
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://" + host + "/knowSocial?" + "user=" + user + "&password=" + passwd);

			statement = connect.createStatement();
			String sql = "SELECT * FROM knowSocial.negativewords";
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {

				String word = rs.getString("NEGATIVE");
				words.add(word);

			}

			return words;

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	public ArrayList<String> readPositive() throws Exception {

		try {

			ArrayList<String> words = new ArrayList<>();
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://" + host + "/knowSocial?" + "user=" + user + "&password=" + passwd);

			statement = connect.createStatement();
			String sql = "SELECT * FROM knowSocial.positivewords";
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {

				String word = rs.getString("POSITIVE");
				words.add(word);

			}

			return words;

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	public void readDataBase() throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");

			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://" + host + "/knowSocial?" + "user=" + user + "&password=" + passwd);

			// Statements allow to issue SQL queries to the database
//      statement = connect.createStatement();
//      // Result set get the result of the SQL query
//      resultSet = statement.executeQuery("select * from knowSocial.comments");
//      writeResultSet(resultSet);
//
//      // PreparedStatements can use variables and are more efficient
//      preparedStatement = connect
//          .prepareStatement("insert into  feedback.comments values (default, ?, ?, ?, ? , ?, ?)");
//      // "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
//      // Parameters start with 1
//      preparedStatement.setString(1, "Test");
//      preparedStatement.setString(2, "TestEmail");
//      preparedStatement.setString(3, "TestWebpage");
//      preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
//      preparedStatement.setString(5, "TestSummary");
//      preparedStatement.setString(6, "TestComment");
//      preparedStatement.executeUpdate();
//
//      preparedStatement = connect
//          .prepareStatement("SELECT myuser, webpage, datum, summary, COMMENTS from feedback.comments");
//      resultSet = preparedStatement.executeQuery();
//      writeResultSet(resultSet);
//
//      // Remove again the insert comment
//      preparedStatement = connect
//      .prepareStatement("delete from feedback.comments where myuser= ? ; ");
//      preparedStatement.setString(1, "Test");
//      preparedStatement.executeUpdate();
//      
//      resultSet = statement
//      .executeQuery("select * from feedback.comments");
//      writeMetaData(resultSet);

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	private void writeMetaData(ResultSet resultSet) throws SQLException {
		// Now get some metadata from the database
		// Result set get the result of the SQL query

		System.out.println("The columns in the table are: ");

		System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
		for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
			System.out.println("Column " + i + " " + resultSet.getMetaData().getColumnName(i));
		}
	}

	private void writeResultSet(ResultSet resultSet) throws SQLException {
		// ResultSet is initially before the first data set
		while (resultSet.next()) {
			// It is possible to get the columns via name
			// also possible to get the columns via the column number
			// which starts at 1
			// e.g. resultSet.getSTring(2);
			String user = resultSet.getString("myuser");
			String website = resultSet.getString("webpage");
			String summary = resultSet.getString("summary");
			Date date = resultSet.getDate("datum");
			String comment = resultSet.getString("comments");
			System.out.println("User: " + user);
			System.out.println("Website: " + website);
			System.out.println("Summary: " + summary);
			System.out.println("Date: " + date);
			System.out.println("Comment: " + comment);
		}
	}

	// You need to close the resultSet
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

}