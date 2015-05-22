package org.adastraeducation.search;

import org.adastraeducation.sort.ArrayDisplayer;
import org.adastraeducation.sort.HeapSort;
import org.adastraeducation.sort.TextArrayDisplayer;
import org.adastraeducation.util.SetArray;

public class GoldenMeanSearch {
	public static double eps = 1e-5;
	public PositionDisplayer x;
	public static double gr = (Math.sqrt(5) - 1) / 2;	//golden ratio
	public static double function(double x) {
		return (x+1)*(x+1);
	}
	
	public GoldenMeanSearch(PositionDisplayer a) {
		this.x = a; 
	}
	
	public static void main(String[] args) {
//		SetArray randomArray = new SetArray(arrayLength); 
//		HeapSort a = new HeapSort(new TextArrayDisplayer(randomArray.data));
		GoldenMeanSearch gms = new GoldenMeanSearch();//(new TextPositionDisplayer(left, right, func));
//		a.start();
//		a.heapSort();(x-1)*(x-1)
		System.out.println(gms.goldenMeanSearch(-2, 2, eps));
	}
	
	public double goldenMeanSearch(double left, double right, double eps) {
		double tmpRight = left + gr * (right - left), tmpLeft = right - gr * (right - left);
		double leftRes, rightRes;
		leftRes = function(tmpLeft);
		rightRes = function(tmpRight);
		x.left = tmpLeft;
		x.right = tmpRight;
		x.leftRes = leftRes;
		x.rightRes = rightRes;
		x.display();
		
		while (Math.abs(tmpLeft - tmpRight) > eps) {
			if (leftRes < rightRes) {
				right = tmpRight;
				tmpRight = left + gr * (right - left);
				tmpLeft = right - gr * (right - left);
				leftRes = function(tmpLeft);
				rightRes = function(tmpRight);
				x.left = tmpLeft;
				x.right = tmpRight;
				x.leftRes = leftRes;
				x.rightRes = rightRes;
				x.display();
			} else {
				left = tmpLeft;
				tmpRight = left + gr * (right - left);
				tmpLeft = right - gr * (right - left);
				leftRes = function(tmpLeft);
				rightRes = function(tmpRight);
				x.left = tmpLeft;
				x.right = tmpRight;
				x.leftRes = leftRes;
				x.rightRes = rightRes;
				x.display();
			}
		}
		return tmpLeft;
	}
}
