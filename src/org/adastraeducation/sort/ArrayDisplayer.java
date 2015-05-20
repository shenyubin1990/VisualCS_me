package org.adastraeducation.sort;

import org.adastraeducation.util.SetArray;


public abstract class ArrayDisplayer implements ArrayObserver {
	public int[] data;
	protected int[] highlightvertex;// the main vertex
	protected int[] highlightvertex2;  // the iterate according to main vertex
	
//	protected int[] highlightedge;
	
	public ArrayDisplayer(int[] data2){
			this.data=data2;
			
			highlightvertex=new int[data2.length];
			highlightvertex2=new int[data2.length];
			
			for(int i=0;i<highlightvertex.length;i++){
				highlightvertex[i]=0;
				highlightvertex2[i]=0;
			}
			
	}

	
	
//	public int getHighlightVertex2(int v){
//		return highlightvertex2[v];
//	}
//	
//	public int getHighlightVertex(int v){
//		
//		return highlightvertex[v];
//	}
	

}
