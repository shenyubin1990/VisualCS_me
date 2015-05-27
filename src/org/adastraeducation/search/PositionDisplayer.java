package org.adastraeducation.search;

import org.adastraeducation.util.SetArray;


public abstract class PositionDisplayer implements PositionObserver {
	public double left = -1, right = -1, leftRes, rightRes;	//show the position of a line which present the selected element 
	
	public double func(double x) {
		return (x-1) * (x-1);
	}
}
