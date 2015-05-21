package org.adastraeducation.sort;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.adastraeducation.util.Generate_random_number;
import org.adastraeducation.util.Serial_number;
import org.adastraeducation.util.SetArray;
import org.adastraeducation.visualize.Visualize;

public class MergeSort {
	public int[] data;
	public ArrayDisplayer x;
	public static int arrayLength = 10;
	
	public MergeSort(ArrayDisplayer a) {
		this.x = a; 
	}

	public static void main(String[] args) {
		//int[] data = new int[] { 5, 3, 6, 2, 1, 9, 4, 8, 7 };
		SetArray randomArray = new SetArray(arrayLength); 
		MergeSort a = new MergeSort(new TextArrayDisplayer(randomArray.data));
		a.sort(0, arrayLength-1);
	}
	
	public void sort(int left, int right) {
		if (left >= right)
			return;
		// �ҳ��м�����
		int center = (left + right) / 2;
		// �����������еݹ�
		x.setHighlightVertex(left, center, false);
		sort(left, center);
		x.setHighlightVertex(center+1, right, false);
		// ���ұ�������еݹ�
		sort(center + 1, right);
		
		// �ϲ�
		merge(x, left, center, right);
		x.setHighlightVertex(left, right, true);
		print(x.data);
		
		try{
//			PrintStream out = new PrintStream(new FileOutputStream("MergeSort_textsoulution_"+Serial_number.serialno()+".txt"));
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("MergeSort_textsoulution_"+Serial_number.serialno()+".txt"), true));

			for (int i = 0; i < x.data.length; i++) {
				writer.write(x.data[i] + "\t");
			}
			writer.write("\r\n");
		     
			writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
	}

	public void merge(int[] data, int left, int center, int right) {
		// ��ʱ����
		int[] tmpArr = new int[data.length];
		// �������һ��Ԫ������
		int mid = center + 1;
		// third ��¼��ʱ���������
		int third = left;
		// �����������һ��Ԫ�ص�����
		int tmp = left;
		while (left <= center && mid <= right) {
			// ������������ȡ����С�ķ�����ʱ����
			if (data[left] <= data[mid]) {
				tmpArr[third++] = data[left++];
			} else {
				tmpArr[third++] = data[mid++];
			}
		}
		// ʣ�ಿ�����η�����ʱ���飨ʵ��������whileֻ��ִ������һ����
		while (mid <= right) {
			tmpArr[third++] = data[mid++];
		}
		while (left <= center) {
			tmpArr[third++] = data[left++];
		}
		// ����ʱ�����е����ݿ�����ԭ������
		// ��ԭleft-right��Χ�����ݱ����ƻ�ԭ���飩
		while (tmp <= right) {
			data[tmp] = tmpArr[tmp++];
		}
	}
	
	public void merge(ArrayDisplayer x, int left, int center, int right) {
		// ��ʱ����
		int[] tmpArr = new int[x.data.length];
		// �������һ��Ԫ������
		int mid = center + 1;
		// third ��¼��ʱ���������
		int third = left;
		// �����������һ��Ԫ�ص�����
		int tmp = left;
		while (left <= center && mid <= right) {
			// ������������ȡ����С�ķ�����ʱ����
			if (x.data[left] <= x.data[mid]) {
				tmpArr[third++] = x.data[left++];
			} else {
				tmpArr[third++] = x.data[mid++];
			}
		}
		// ʣ�ಿ�����η�����ʱ���飨ʵ��������whileֻ��ִ������һ����
		while (mid <= right) {
			tmpArr[third++] = x.data[mid++];
		}
		while (left <= center) {
			tmpArr[third++] = x.data[left++];
		}
		// ����ʱ�����е����ݿ�����ԭ������
		// ��ԭleft-right��Χ�����ݱ����ƻ�ԭ���飩
		while (tmp <= right) {
			x.data[tmp] = tmpArr[tmp++];
		}
	}

	public void print(int[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + "\t");
		}
		System.out.println();
	}
	
	public void finish() {
		Visualize.terminate=true;
	}

}
