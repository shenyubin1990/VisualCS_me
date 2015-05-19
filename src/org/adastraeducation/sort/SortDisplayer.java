package org.adastraeducation.sort;


public abstract class SortDisplayer implements SortObserver {
	public int[] data;
	protected int[] highlightvertex;// the main vertex
	protected int[] highlightvertex2;  // the iterate according to main vertex
	
	protected int[] highlightedge;
	
	public SortDisplayer(int[] data){
			this.data=data;
			
			highlightvertex=new int[data.length];
			highlightvertex2=new int[data.length];
			
			for(int i=0;i<highlightvertex.length;i++){
				highlightvertex[i]=0;
				highlightvertex2[i]=0;
			}
			
	}
	
	
	public int getHighlightVertex2(int v){
		return highlightvertex2[v];
	}
	
	public int getHighlightVertex(int v){
		
		return highlightvertex[v];
	}
	

}
