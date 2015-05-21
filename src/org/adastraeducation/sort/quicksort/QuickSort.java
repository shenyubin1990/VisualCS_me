package org.adastraeducation.sort.quicksort;

import org.adastraeducation.util.Generate_random_number;
import org.adastraeducation.util.SetArray;
import org.adastraeducation.visualize.Visualize;

public class QuickSort {
	
	private int[] arr;
	private int num; 
	private SortDisplayer sd;
	
	public QuickSort(int num) {
		arr = new int[num];
		this.num = num;
		for (int i = 0; i < num; i++)
			arr[i] = Generate_random_number.RandomInteger(0, 99);
	}
	
	public QuickSort(SortDisplayer sd) {
		this.sd = sd;
		this.arr = sd.highlightvertex;
		this.num = sd.highlightvertex.length;
	}

	public int[] getArr() {
		return arr;
	}

	public int getNum() {
		return num;
	}

	public static void main(String[] args) {
//		SetArray sa = new SetArray(10);
//		int[] arr = sa.data;
		QuickSort qs = new QuickSort(10);
		qs.sort(0, qs.num-1);
		for(int a:qs.arr){
			System.out.print(a + " ");
		}
	}

	public void sort(int low, int high){
		int l = low;
		int h = high;
		int povit = arr[low];
		
		while(l < h){
			while(l < h && arr[h] >= povit){
				if(Visualize.visualize){
					sd.setHighlightVertex(h, 0);
				}
				h--;
			}
			if(l < h){
				if(Visualize.visualize){
					sd.setHighlightVertex(h, 0);
				}
				int temp = arr[h];
				arr[h] = arr[l];
				if(Visualize.visualize){
					sd.setHighlightVertex(h, 0);
				}
				arr[l] = temp;
//				l++;
			}
			while(l < h && arr[l] <= povit){
				if(Visualize.visualize){
					sd.setHighlightVertex(h, 0);
				}
				l++;
			}
			if(l < h){
				if(Visualize.visualize){
					sd.setHighlightVertex(h, 0);
				}
				int temp = arr[h];
				arr[h] = arr[l];
				if(Visualize.visualize){
					sd.setHighlightVertex(h, 0);
				}
				arr[l] = temp;
//				h--;
			}
		}
		System.out.print("l="+(l+1)+"h="+(h+1)+"povit="+povit+"\n");
		if(l > low){
			sort(low, l-1);
		}
		if(h < high){
			sort(l+1, high);
		}
		
		Visualize.terminate=true;
	}
}
