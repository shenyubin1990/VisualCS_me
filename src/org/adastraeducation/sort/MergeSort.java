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


//does the text solution contain the original question?
public class MergeSort {
	public int[] data;
	public static ArrayDisplayer x;
	public static int arrayLength = 10;
	
	public MergeSort(ArrayDisplayer a) {
		this.x = a; 
	}

	public static void main(String[] args) {
		//int[] data = new int[] { 5, 3, 6, 2, 1, 9, 4, 8, 7 };
		SetArray randomArray = new SetArray(arrayLength); 
		MergeSort a = new MergeSort(new TextArrayDisplayer(randomArray.data));
		a.print(x.data);
		a.start();
		a.sort();
		
	}
	
	public void sort() {
		for (int width = 1; width < arrayLength; width = width * 2) {
//			System.out.println(width);
			for (int i = 0; i < arrayLength; i += width * 2) {
				merge(i, Math.min(i+width, arrayLength), Math.min(i+2*width, arrayLength));
			}
			print(x.data);
			textSolution();
		}
	}
	
	public void textSolution() {
		try{
//			PrintStream out = new PrintStream(new FileOutputStream("MergeSort_textsoulution_"+Serial_number.serialno()+".txt"));
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("..\\MergeSort_textsoulution_"+Serial_number.serialno()+".txt"), true));

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
	
	//what we merge is [left to center-1] and [center to right-1]
	public void merge(int left, int center, int right) {
		// temp array for record merge result
		if (center == right)
			return;
		x.tempArr = null;
		x.createTempArr(x.data.length);
		x.right = center;	//the iterator of the right array 
		int third = left;	//the iterator of the temp array
		x.left = left;	//the iterator of the left array
		int tmp = left;		//the head of temp array for copy back 
		

		for (int i = left; i < right; i++) {
			x.setHighlightVertex2(i, 1);
			x.setHighlightVertex(i, 1);
		}
		x.display();
		
		while (x.left < center && x.right < right) {
			// choose the min one
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
		//now one iterator has gone to the end, the following two while will deal with the other iterator
		while (x.right < right) {
			x.left = -1;
			x.tempArr[third++] = x.data[x.right];
			x.display();
			x.right++;
		}
		while (x.left < center && x.left != -1) {	//remove the influence by previous while loop
			x.right = -1;
			x.tempArr[third++] = x.data[x.left];
			x.display();
			x.left++;
		}
		x.left = -1;
		x.right = -1;
		x.display();

		// copy the temp array back to the original one
		while (tmp < right) {
			x.setHighlightVertex(tmp, 2);
			x.data[tmp] = x.tempArr[tmp++];
		}
		x.display();

//		print(x.data);

		for (int i = 0; i < x.data.length; i++)
			x.setHighlightVertex2(i, 0);
	}

	public void print(int[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + "\t");
		}
		System.out.println();
	}
	public void start() {
		try {
			File file = new File("..\\MergeSort_textsoulution_"+Serial_number.serialno()+".txt");
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
