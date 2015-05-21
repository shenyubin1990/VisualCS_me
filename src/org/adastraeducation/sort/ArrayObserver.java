package org.adastraeducation.sort;



public interface ArrayObserver{
	
	public void display();   //once loaded it would graphically show the image
		
	public void setHighlightVertex(int position, int color);
	public void swap(int i, int j);
	public int compare(int i, int j);
	 
    
}
