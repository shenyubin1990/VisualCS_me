package org.adastraeducation.sort;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.adastraeducation.util.Serial_number;
import org.adastraeducation.util.SetArray;
import org.adastraeducation.visualize.Visualize;

public class QuickSort {
	
	public static final int arrayLength = 10;
	public ArrayDisplayer sd;

	public QuickSort(ArrayDisplayer sd) {
		this.sd = sd;
	}

	public static void main(String[] args) {
		SetArray randomArray = new SetArray(arrayLength); 
		QuickSort qs = new QuickSort(new TextArrayDisplayer(randomArray.data));
		qs.start();
		qs.sort(0, arrayLength-1);
	}

	public void sort(int low, int high){
		int l = low;
		int h = high;
		int povit = sd.data[low];
		if(Visualize.visualize){
			for (int i = low; i <= high; i++)
				sd.setHighlightVertex(i, 0);
			sd.setHighlightVertex(l, 3);
		}
		while(l < h){
			while(l < h && sd.data[h] >= povit){
				if(Visualize.visualize){
					sd.setHighlightVertex(h, 1);
					sd.display();
					sd.setHighlightVertex(h, 0);
					sd.display();
				}
				h--;
			}
			if(l < h){
				if(Visualize.visualize){
					sd.setHighlightVertex(h, 1);
					sd.display();
					sd.setHighlightVertex(h, 2);
					sd.display();
				}
				sd.swap(l, h);
				if(Visualize.visualize){
					sd.setHighlightVertex(l, 2);
					sd.display();
					sd.setHighlightVertex(h, 3);
					sd.display();
				}
//				l++;
				print(sd.data);
				textSolution();
			}
			while(l < h && sd.data[l] <= povit){
				if(Visualize.visualize){
					sd.setHighlightVertex(l, 1);
					sd.display();
					sd.setHighlightVertex(l, 0);
					sd.display();
				}
				l++;
			}
			if(l < h){
				if(Visualize.visualize){
					sd.setHighlightVertex(l, 1);
					sd.display();
					sd.setHighlightVertex(l, 2);
					sd.display();
				}
				sd.swap(l, h);
				if(Visualize.visualize){
					sd.setHighlightVertex(h, 2);
					sd.display();
					sd.setHighlightVertex(l, 3);
					sd.display();
				}
//				h--;
				print(sd.data);
				textSolution();
			}
		}
		if(l > low){
			sd.display();
			sort(low, l-1);
		}
		if(h < high){
			sd.display();
			sort(l+1, high);
		}
	}
	
	public void start() {
		try {
			File file = new File("QuickSort_textsoulution_"+Serial_number.serialno()+".txt");
			if(!file.exists())
				file.createNewFile();
			else 
				file.delete();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void finish() {
		Visualize.terminate=true;
	}
	
	public void textSolution() {
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("QuickSort_textsoulution_"+Serial_number.serialno()+".txt"), true));
			for (int i = 0; i < sd.data.length; i++) {
				writer.write(sd.data[i] + "\t");
			}
			writer.write("\r\n");
			writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}    
	}
	
	public void print(int[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + "\t");
		}
		System.out.println();
	}
}
