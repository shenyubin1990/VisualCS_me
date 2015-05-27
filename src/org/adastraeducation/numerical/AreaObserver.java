package org.adastraeducation.numerical;

import org.adastraeducation.util.Generate_random_number;



public interface AreaObserver{
	
	public void display();   //once loaded it would graphically show the image
		
	public void setA_RandomBoundary(int al, int ar); 
	public void setB_RandomBoundary(int bl, int br);
	public void set_eps(double eps);
	
	public double func(double x);
    
}
