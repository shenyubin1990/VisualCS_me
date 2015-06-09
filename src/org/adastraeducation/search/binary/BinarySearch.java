package org.adastraeducation.search.binary;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.adastraeducation.util.Generate_random_number;
import org.adastraeducation.util.Serial_number;
import org.adastraeducation.util.SetArray;
import org.adastraeducation.visualize.Visualize;

public class BinarySearch {

	private static final int START_RANGE = 0;
	private static final int END_RANGE = 9;
	private static final int ARRAY_LENGTH = 10;
	public PositionDisplayer sd;
	
	public BinarySearch(PositionDisplayer sd) {
		this.sd = sd;
	}
	
	public static void main(String[] args) {
		SetArray randomArray = new SetArray(ARRAY_LENGTH, START_RANGE, END_RANGE);
		randomArray.sort();
		BinarySearch bs = new BinarySearch(new TextPositionDisplayer(randomArray.data, Generate_random_number.RandomInteger(0, 9)));
		bs.start();
		int position = bs.binary(bs.sd.keyValue);
		for(int i : bs.sd.data){
			System.out.print(i);
		}
		System.out.println();
		System.out.println(bs.sd.keyValue);
		System.out.println(position);
	}
	
	public int binary(int value) {
		int low = 0;
		int high = sd.data.length - 1;
		printInitData();
		while (low <= high) {
			int middle = (low + high) / 2;
			if(Visualize.visualize){
				sd.setHighlightVertex(middle, 1);
				sd.display();
			}
			for (int s = 0; s < low; s++) {
				System.out.print(" " + "\t");
				textSolutionString(" " + "\t");
			}
			print(sd.data, low, high+1);
			textSolutionInt(sd.data, low, high+1);
			if (value == sd.data[middle]) {
				if(Visualize.visualize){
					sd.setHighlightVertex(middle, 3);
					sd.display();
				}
				return middle;
			}
			if (value > sd.data[middle]) {
				low = middle + 1;
				if(Visualize.visualize){
					for(int i = low; i <= high; i++){
						sd.setHighlightVertex(i, 2);
					}
					for(int i = 0; i < low; i++){
						sd.setHighlightVertex(i, 0);
					}
					for(int i = high + 1; i < sd.data.length; i++){
						sd.setHighlightVertex(i, 0);
					}
					sd.display();
				}
			}
			if (value < sd.data[middle]) {
				high = middle - 1;
				if(Visualize.visualize){
					for(int i = 0; i <= high; i++){
						sd.setHighlightVertex(i, 2);
					}
					for(int i = 0; i < low; i++){
						sd.setHighlightVertex(i, 0);
					}
					for(int i = high + 1; i < sd.data.length; i++){
						sd.setHighlightVertex(i, 0);
					}
					sd.display();
				}
			}
		}
		return -1;
	}

	public void start() {
		try {
			File file = new File("BinarySearch_textsoulution_"+Serial_number.serialno()+".txt");
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
	
	public void printInitData(){
		print(sd.data, 0, sd.data.length);
		System.out.println(sd.keyValue);
		textSolutionInt(sd.data, 0, sd.data.length);
		textSolutionString(sd.keyValue+"\r\n");
		for(int i = 0; i < sd.data.length - 1; i++){
        	System.out.print("========");
        	textSolutionString("========");
        }
        System.out.println();
        textSolutionString("\r\n");	
	}
	
	public void textSolutionString(String data) {
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("BinarySearch_textsoulution_"+Serial_number.serialno()+".txt"), true));
			writer.write(data);
			writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}    
	}
	
	public void textSolutionInt(int[] data, int begin, int end) {
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("BinarySearch_textsoulution_"+Serial_number.serialno()+".txt"), true));
			for (int i = begin; i < end; i++) {
				writer.write(data[i] + "\t");
			}
			writer.write("\r\n");
			writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}    
	}
	
	public void print(int[] data, int begin, int end) {
		for (int i = begin; i < end; i++) {
			System.out.print(data[i] + "\t");
		}
		System.out.println();
	}
}
