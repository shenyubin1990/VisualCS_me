package org.adastraeducation.util;

import java.util.Random;

public class Generate_random_number {
	
	private static Random random=new Random();
	
	public static int RandomInteger(int start, int end){
		if(start>end)
			throw new IllegalArgumentException("Start could not exceed End");
		int range=end-start+1;
		
		return start+(int)(range*random.nextDouble());
	}
	
	public static double RandomDouble(double start, double end, int digit){  //accuracy  
		
		double reserveNumber = Math.pow(10, digit);
		if(start>end)
			throw new IllegalArgumentException("Start could not exceed End");
		double range=end-start+1;
		return Math.round((start+range*random.nextDouble())*  (int)reserveNumber  )/ reserveNumber;
		
	}
	
	public static String RandomString(int length, char start, char end){
		if(start>end)
			throw new IllegalArgumentException("Start could not exceed End");
		int range=end-start+1;
		String result = "";
		for(int i = 0; i < length; i++){
			result = result + "" + (char)(start+(int)(range*random.nextDouble())) + "";
		}
		return result;
	}
	
	public static char[] RandomCharArray(int length, char start, char end){
		if(start>end)
			throw new IllegalArgumentException("Start could not exceed End");
		int range=end-start+1;
		char[] result = new char[length];
		for(int i = 0; i < length; i++){
			result[i] = (char)(start+(int)(range*random.nextDouble()));
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(RandomCharArray(10,'a','z'));
	}
	
}
