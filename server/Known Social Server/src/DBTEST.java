

import java.util.ArrayList;

public class DBTEST {

	public static void main(String[] args) {
		 MySQLAccessData dao = new MySQLAccessData();
		 try {
			 Location l = dao.findLocation(62711);
		
			 System.out.println(l.getLatitude());
			 System.out.println(l.getLongitude());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
