public class Report implements java.io.Serializable{

	
	volatile private StanfordReport stanford_Report;
	volatile private DictionaryReport dictionary_Report;
	volatile private int reportID;
	volatile private int stanfordID;
	volatile private int dictionaryID;
	
	/**
	 * @param stanford_Report
	 * @param dictionary_Report
	 */
	public Report(StanfordReport stanford_Report, DictionaryReport dictionary_Report, int reportID, int stanfordID, int dictionaryID)
	{
		super();
		this.stanford_Report = stanford_Report;
		this.dictionary_Report = dictionary_Report;
		this.reportID = reportID;
		this.stanfordID = stanfordID;
		this.dictionaryID = dictionaryID;
		
	}
	public int getStanfordID() {
		return stanfordID;
	}
	public void setStanfordID(int stanfordID) {
		this.stanfordID = stanfordID;
	}
	public int getDictionaryID() {
		return dictionaryID;
	}
	public void setDictionaryID(int dictionaryID) {
		this.dictionaryID = dictionaryID;
	}
	public Report() {
		// TODO Auto-generated constructor stub
	}
	public int getReportID() {
		return reportID;
	}
	public void setReportID(int reportID) {
		this.reportID = reportID;
	}
	public StanfordReport getStanford_Report() {
		return stanford_Report;
	}
	public void setStanford_Report(StanfordReport stanford_Report) {
		this.stanford_Report = stanford_Report;
	}
	public DictionaryReport getDictionary_Report() {
		return dictionary_Report;
	}
	public void setDictionary_Report(DictionaryReport dictionary_Report) {
		this.dictionary_Report = dictionary_Report;
	}
	
	
	
	
}
