package org.adastraeducation.sort;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.adastraeducation.util.Serial_number;
import org.adastraeducation.util.SetArray;
import org.adastraeducation.visualize.Visualize;

public class HeapSort {
	public int[] data;
	public ArrayDisplayer x;
	public static int arrayLength = 10;
	
	public HeapSort(ArrayDisplayer a) {
		this.x = a; 
	}
	
	public static void main(String[] args) {
		SetArray randomArray = new SetArray(arrayLength); 
		HeapSort a = new HeapSort(new TextArrayDisplayer(randomArray.data));
		a.start();
		a.heapSort();
	}

	public static void swap(int[] data, int i, int j) {
		if (i == j) {
			return;
		}
		data[i] = data[i] + data[j];
		data[j] = data[i] - data[j];
		data[i] = data[i] - data[j];
	}

	public void heapSort() {
		for (int i = 0; i < x.data.length; i++) {
			createMaxdHeap(x.data.length - 1 - i);
			x.setHighlightVertex(x.data.length - 1 - i, 1);
			x.setHighlightVertex(0, 1);
			x.display();
			x.setHighlightVertex(0, 0);
			x.swap(0, x.data.length - 1 - i);
			x.setHighlightVertex(x.data.length - 1 - i, 2);
			print(x.data);
			textSolution();
			x.display();
		}
	}

	public void createMaxdHeap(int lastIndex) {
		for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
			// ���浱ǰ�����жϵĽڵ�
			int k = i;
			x.setHighlightVertex(k, 1);
			// ����ǰ�ڵ���ӽڵ����
			while (2 * k + 1 <= lastIndex) {
				// biggerIndex���Ǽ�¼�ϴ�ڵ��ֵ,�ȸ�ֵΪ��ǰ�жϽڵ�����ӽڵ�
				x.setHighlightVertex(k, 1);
				
				int biggerIndex = 2 * k + 1;
				if (biggerIndex < lastIndex) {
					// �����ӽڵ���ڣ������ʱbiggerIndexӦ�õ��� lastIndex
					x.setHighlightVertex(biggerIndex, 1);
					x.setHighlightVertex(biggerIndex+1, 1);
					x.display();
					if (x.compare(biggerIndex, biggerIndex + 1) == -1) {
						// �����ӽڵ�ֵ�����ӽڵ�ֵ����biggerIndex��¼�������ӽڵ��ֵ
						x.setHighlightVertex(biggerIndex, 0);
						biggerIndex++;
						x.display();
					} else {
						x.setHighlightVertex(biggerIndex+1, 0);
						x.display();
					}
				} else {
					x.setHighlightVertex(biggerIndex, 1);
					x.display();
				}
				if (x.compare(k, biggerIndex) == -1) {
					// ����ǰ�ڵ�ֵ���ӽڵ����ֵС���򽻻�2�ߵ�ֵ��������biggerIndexֵ��ֵ��k
					x.swap(k, biggerIndex);
					int tempk = k;
					k = biggerIndex;
					x.display();
					x.setHighlightVertex(tempk, 0);
					x.setHighlightVertex(biggerIndex, 0);
				} else {
					x.display();
					x.setHighlightVertex(k, 0);
					x.setHighlightVertex(biggerIndex, 0);
					break;
				}
			}
		}
	}

	public void textSolution() {
		try{
//			PrintStream out = new PrintStream(new FileOutputStream("MergeSort_textsoulution_"+Serial_number.serialno()+".txt"));
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("..\\HeapSort_textsoulution_"+Serial_number.serialno()+".txt"), true));

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
	
	public void print(int[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + "\t");
		}
		System.out.println();
	}
	
	public void start() {
		try {
			File file = new File("..\\HeapSort_textsoulution_"+Serial_number.serialno()+".txt");
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