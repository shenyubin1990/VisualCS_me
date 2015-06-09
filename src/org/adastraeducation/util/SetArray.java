package org.adastraeducation.util;

import java.util.Arrays;


public class SetArray {
	public int[] data;
	public int len;
	
	public SetArray(int length) {
		data = new int[length];
		len = length;
		for (int i = 0; i < length; i++)
			data[i] = Generate_random_number.RandomInteger(0, 99);
	}
	
	public SetArray(int length, int start, int end) {
		data = new int[length];
		len = length;
		for (int i = 0; i < length; i++)
			data[i] = Generate_random_number.RandomInteger(start, end);
	}
	
	public void sort(){
		Arrays.sort(data);
	}
	
}
