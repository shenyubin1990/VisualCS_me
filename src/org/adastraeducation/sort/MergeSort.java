package org.adastraeducation.sort;

import org.adastraeducation.util.Generate_random_number;
import processing.core.PApplet;

public class MergeSort {
	public int[] data;
	public SortDisplayer x;
	//public int from, to;
	PApplet parent; // The parent PApplet that we will render ourselves onto
	int size = 10;
	
	public MergeSort(int size) {
		data = new int[size];
		for (int i = 0; i < size; i++)
			data[i] = Generate_random_number.RandomInteger(0, 99);
	}
	public MergeSort(SortDisplayer a) {
		this.x = a; 
	}
	
	MergeSort(PApplet p) {
		parent = p;
		data = new int[size];
		for (int i = 0; i < size; i++)
			data[i] = Generate_random_number.RandomInteger(0, 99);
	}
	public static void main(String[] args) {
		//int[] data = new int[] { 5, 3, 6, 2, 1, 9, 4, 8, 7 };
		MergeSort a = new MergeSort(10);
		a.print(a.data);
		a.mergeSort(a.data);
		System.out.println("���������飺");
		a.print(a.data);
	}

//	public void mergeSort(SortDisplayer x) {
//		sort(0, x.data.length - 1);
//	}
	public void mergeSort() {
		sort(0, data.length - 1);
	}
	
	public void mergeSort(int[] data) {
		sort(0, data.length - 1);
	}
	
	// Draw 
	void display(int left, int right) {
//		parent.fill(255,100);
//		parent.noStroke();
		parent.textSize(32);
		for (int x_axis = 0; x_axis < 10; x_axis++) {
			parent.fill(255);
			parent.rect(55+56*x_axis, 200, 56, 56);
    		if (x_axis >= left &&x_axis <= right) {
    			parent.fill(100);
    			parent.text(data[x_axis], 45+56*x_axis, 210);	
    		} else {
    			parent.fill(200);
    			parent.text(data[x_axis], 45+56*x_axis, 210);	
    		}
    	}
		parent.redraw();
		parent.delay(2000);
	}
	void displayC(int left, int right) {
//		parent.fill(255,100);
//		parent.noStroke();
		parent.textSize(32);
		for (int x_axis = 0; x_axis < 10; x_axis++) {
			parent.fill(255);
			parent.rect(55+56*x_axis, 200, 56, 56);
    		if (x_axis >= left &&x_axis <= right) {
    			parent.fill(204, 102, 0);
    			parent.text(data[x_axis], 45+56*x_axis, 210);	
    		} else {
    			parent.fill(200);
    			parent.text(data[x_axis], 45+56*x_axis, 210);	
    		}
    	}
		parent.redraw();
		parent.delay(2000);
	}
	public void sort(int left, int right) {
		if (left >= right)
			return;
		// �ҳ��м�����
		int center = (left + right) / 2;
		// �����������еݹ�
		//x.setHighlightVertex(left, center, false);
		display(left, center);
		sort(left, center);
		//x.setHighlightVertex(center+1, right, false);
		// ���ұ�������еݹ�
		display(center+1, right);
		sort(center + 1, right);
		
		// �ϲ�
		merge(data, left, center, right);
		//x.setHighlightVertex(left, right, true);
		displayC(left, right);
		//print(x.data);
		print(data);
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
	
	public void merge(SortDisplayer x, int left, int center, int right) {
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

}
