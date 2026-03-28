package org.iit.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FutureDate {

	
	public static void main(String[] args) {
		
		 
		String result = getFutureDate(11,"MMMM dd YYYY");
		System.out.println(result);
		String month = result.split(" ")[0];
		String day = result.split(" ")[1];
		String year = result.split(" ")[2];
		 
		System.out.println("Month: " + month);
		System.out.println("Day: " + day);
		System.out.println("Year: " + year);
		
	}
	
	public static String getFutureDate(int days,String dateFormat)
	{
		LocalDate currentDate = LocalDate.now();
		currentDate = currentDate.plusDays(days);
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
		String date = currentDate.format(dateTimeFormatter);
		return date;
	}
	
	
}
