package org.adastraeducation.util;


public class SetArray {
	public int[] data;
	public int len;
	
	public SetArray(int length) {
		data = new int[length];
		len = length;
		for (int i = 0; i < length; i++)
			data[i] = Generate_random_number.RandomInteger(0, 99);
	}
}
