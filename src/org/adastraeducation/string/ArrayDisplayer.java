package org.adastraeducation.string;


public abstract class ArrayDisplayer implements ArrayObserver {
	protected char[] text;
	protected char[] pat;
	protected int[] failure; 
	protected int[] highlightvertex;// the main vertex
	protected int[] highlightvertex2;// the sub vertex for sub array
	protected int[] highlightvertex3;
	protected int[] highlightvertex4;
	protected int mark;
	
	public ArrayDisplayer(char[] text, char[] pat){
			this.text = text;
			this.pat = pat;
			
			highlightvertex=new int[text.length];
			highlightvertex2=new int[pat.length];
			highlightvertex3=new int[pat.length];
			highlightvertex4=new int[pat.length];
			
			for(int i=0;i<highlightvertex.length;i++){
				highlightvertex[i]=1;
			}
			
			for(int i=0;i<highlightvertex2.length;i++){
				highlightvertex2[i]=1;
				highlightvertex3[i]=0;
				highlightvertex4[i]=1;
			}
			
	}
	

}
