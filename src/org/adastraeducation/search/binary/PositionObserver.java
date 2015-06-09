package org.adastraeducation.search.binary;



public interface PositionObserver{
	
	public void display();   //once loaded it would graphically show the image
	
	public void setHighlightVertex(int position, int color);
	public int compare(int i, int j);
    
}
