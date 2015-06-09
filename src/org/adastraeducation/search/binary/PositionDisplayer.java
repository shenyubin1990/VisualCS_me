package org.adastraeducation.search.binary;

public abstract class PositionDisplayer implements PositionObserver {
	
	protected int keyValue;
	protected int[] data;
	protected int[] highlightvertex; 
	
	public PositionDisplayer(int[] data2, int keyValue){
		this.keyValue = keyValue;
		this.data=data2;
		highlightvertex=new int[data2.length];
		for(int i=0;i<highlightvertex.length;i++){
			highlightvertex[i]=0;
		}
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
