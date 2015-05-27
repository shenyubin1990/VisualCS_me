package org.adastraeducation.numerical;

import org.adastraeducation.util.Generate_random_number;
import org.adastraeducation.util.SetArray;


public abstract class AreaDisplayer implements AreaObserver {
	public double a, b, eps, area;      // Left and right and eps
	public int n = 0;         // Number of trapezoids
	
	public void setA_RandomBoundary(int al, int ar) {
		a = Generate_random_number.RandomInteger(al, ar);
		System.out.println(a);
	}
	
	public void setB_RandomBoundary(int bl, int br) {
		b = Generate_random_number.RandomInteger(bl, br);
	}
	
	public void set_eps(double eps) {
		this.eps = eps;
	}
	
	public double func(double x) {
		return (x) * (x);
	}
	
	public int getN() {
		return n;
	}
}
