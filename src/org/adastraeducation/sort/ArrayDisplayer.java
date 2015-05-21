package org.adastraeducation.sort;

import org.adastraeducation.util.SetArray;


public abstract class ArrayDisplayer implements ArrayObserver {
	public int[] data;
	public int[] tempArr;
	public int left = -1, right = -1;	//show the position of a line which present the selected element 
	protected int[] highlightvertex;// the main vertex
	protected int[] highlightvertex2;// the sub vertex for sub array
	
	public ArrayDisplayer(int[] data2){
			this.data=data2;
			
			highlightvertex=new int[data2.length];
			highlightvertex2=new int[data2.length];
			
			for(int i=0;i<highlightvertex.length;i++){
				highlightvertex[i]=0;
				highlightvertex2[i]=0;
			}
			
	}

	//if dangerous with no delete[]?
	public void createTempArr(int length) {
		tempArr = new int[length];
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
