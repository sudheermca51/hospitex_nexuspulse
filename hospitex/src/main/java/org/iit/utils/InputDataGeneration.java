package org.iit.utils;

import java.util.Random;

public class InputDataGeneration {


	public static void main(String[] args) 
	{

		InputDataGeneration inputDataGeneration = new InputDataGeneration();
		String result = inputDataGeneration.generateRandomString(4, 4, 4);
		System.out.println(result);
		
		result = inputDataGeneration.generateRandomString(8, 8, 8);
		System.out.println(result);

		result = inputDataGeneration.generateRandomString(4, 0, 0);
		System.out.println(result);

	}

	public  String generateRandomString( int upperCaseCount, int lowerCaseCount, int digitCount)
	{
		//Create an instance of Random class
		Random random = new Random();

		//Generate random integers in range 0-99
		int i = random.nextInt(100);
		System.out.println("Random Integer: " + i);
		//-----------------------------
		StringBuilder randomString = new StringBuilder();
		String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for(int j=0; j<upperCaseCount; j++)
		{
			char c = upperCaseLetters.charAt(random.nextInt(upperCaseLetters.length()));
			randomString.append(c);
		}
		//--------------------------
		String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
		for(int j=0; j<lowerCaseCount; j++)
		{
			char c = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
			randomString.append(c);
		}

		//-----------

		String digits = "0123456789";
		for(int j=0; j<digitCount; j++)
		{
			char c = digits.charAt(random.nextInt(digits.length()));
			randomString.append(c);
		}
		System.out.println("Random String: " + randomString.toString());

		return  randomString.toString();
	}
}