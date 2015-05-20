package org.adastraeducation.sort;

import org.adastraeducation.util.SetArray;


public abstract class ArrayDisplayer implements ArrayObserver {
	public int[] data;
	protected int[] highlightvertex;// the main vertex
	
	public ArrayDisplayer(int[] data2){
			this.data=data2;
			
			highlightvertex=new int[data2.length];
			
			for(int i=0;i<highlightvertex.length;i++){
				highlightvertex[i]=0;
			}
			
	}

	public void swap(int i, int j) {
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}
	
	public int compare(int i, int j) {
		if (data[i] > data[j])
			return 1;
		if (data[i] == data[j])
			return 0;
		else 
			return -1;
	}

}
