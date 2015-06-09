package org.adastraeducation.sort;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.adastraeducation.util.Serial_number;
import org.adastraeducation.util.SetArray;
import org.adastraeducation.visualize.Visualize;


//does the text solution contain the original question?
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
		a.start();
		a.sort(0, arrayLength-1);
	}
	
	public void sort(int left, int right) {
		if (left >= right)
			return;
		for (int i = left; i <= right; i++)
			x.setHighlightVertex(i, 0);
		// �ҳ��м�����
		int center = (left + right) / 2;
		// �����������еݹ�
		for (int i = left; i <= center; i++)
			x.setHighlightVertex(i, 1);
		x.display();
		sort(left, center);

		for (int i = center+1; i <= right; i++)
			x.setHighlightVertex(i, 1);
		// ���ұ�������еݹ�
		x.display();
		sort(center + 1, right);
		
		// �ϲ�
		merge(x, left, center, right);
		for (int i = left; i <= right; i++ )
			x.setHighlightVertex(i, 2);	//2 = red
		x.display();
		print(x.data);
		textSolution();

	}
	
	public void textSolution() {
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
	public void merge(ArrayDisplayer x, int left, int center, int right) {
		// ��ʱ����
		x.tempArr = null;
		x.createTempArr(x.data.length);
		//int[] tmpArr = new int[x.data.length];
		// �������һ��Ԫ������
		x.right = center + 1;
		// third ��¼��ʱ���������
		int third = left;
		// �����������һ��Ԫ�ص�����
		x.left = left;
		int tmp = left;
		
		for (int i = 0; i < x.data.length; i++)
			x.highlightvertex2[i] = 0;
		for (int i = left; i <= right; i++)
			x.highlightvertex2[i] = 1;
		print(x.highlightvertex2);
		
		while (x.left <= center && x.right <= right) {
			// ������������ȡ����С�ķ�����ʱ����
			if (x.compare(x.left, x.right) <= 0) {
				x.tempArr[third++] = x.data[x.left];
				x.display();
				x.left++;
			} else {
				x.tempArr[third++] = x.data[x.right];
				x.display();	//keep original x.right for display
				x.right++;
			}
		}
		// ʣ�ಿ�����η�����ʱ���飨ʵ��������whileֻ��ִ������һ����
		while (x.right <= right) {
			x.left = -1;
			x.tempArr[third++] = x.data[x.right];
			x.display();
			x.right++;
		}
		while (x.left <= center && x.left != -1) {	//remove the influence by previous while loop
			x.right = -1;
			x.tempArr[third++] = x.data[x.left];
			x.display();
			x.left++;
		}
		x.left = -1;
		x.right = -1;
		// ����ʱ�����е����ݿ�����ԭ������
		// ��ԭleft-right��Χ�����ݱ����ƻ�ԭ���飩
		while (tmp <= right) {
			x.data[tmp] = x.tempArr[tmp++];
		}
	}

	public void print(int[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + "\t");
		}
		System.out.println();
	}
	public void start() {
		try {
			File file = new File("MergeSort_textsoulution_"+Serial_number.serialno()+".txt");
			if(!file.exists())
				file.createNewFile();
			else 
				file.delete();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public void finish() {
		Visualize.terminate=true;
	}

}
