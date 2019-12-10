

public class commandParameter  implements java.io.Serializable{

	
	
	volatile private String searchString;
	volatile private int zipcode;
	volatile private int returnCount;
	volatile private String userSearched;
	
	


	
	
	

	public commandParameter(String searchString, int zipcode, int returnCount, String userSearched)
	{
		super();
		this.searchString = searchString;
		this.zipcode = zipcode;
		this.returnCount = returnCount;
		this.userSearched = userSearched;
		
	}



	public String getUserSearched() {
		return userSearched;
	}


	public void setUserSearched(String userSearched) {
		this.userSearched = userSearched;
	}


	public String getSearchString() {
		return searchString;
	}




	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}




	public int getZipcode() {
		return zipcode;
	}




	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}




	public int getReturnCount() {
		return returnCount;
	}




	public void setReturnCount(int returnCount) {
		this.returnCount = returnCount;
	}
	
	
}
