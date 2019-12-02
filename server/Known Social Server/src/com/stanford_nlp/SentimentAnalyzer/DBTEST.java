package com.stanford_nlp.SentimentAnalyzer;

import java.util.ArrayList;

import MySQLAccessData;

public class DBTEST {

	public static void main(String[] args) {
		 MySQLAccessData dao = new MySQLAccessData();
		 try {
			 int row = dao.getStartID("analysis");
		
			 System.out.println(row);
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
