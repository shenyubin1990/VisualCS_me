package org.adastraeducation.search;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import org.adastraeducation.sort.ArrayDisplayer;
import org.adastraeducation.sort.HeapSort;
import org.adastraeducation.sort.TextArrayDisplayer;
import org.adastraeducation.util.Serial_number;
import org.adastraeducation.util.SetArray;
import org.adastraeducation.visualize.Visualize;

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
		GoldenMeanSearch gms = new GoldenMeanSearch(new TextPositionDisplayer());
		gms.start();
		System.out.println(gms.goldenMeanSearch(-2, 2, eps));
	}
	
	public double goldenMeanSearch(double left, double right, double eps) {
		double tmpRight = left + gr * (right - left), tmpLeft = right - gr * (right - left);
		double leftRes, rightRes;
		leftRes = x.func(tmpLeft);
		rightRes = x.func(tmpRight);
		x.left = tmpLeft;
		x.right = tmpRight;
		x.leftRes = leftRes;
		x.rightRes = rightRes;
		x.display();
//		System.out.println(x.left);
		while (Math.abs(tmpLeft - tmpRight) > eps) {
			if (leftRes < rightRes) {
				right = tmpRight;
				tmpRight = left + gr * (right - left);
				tmpLeft = right - gr * (right - left);
				leftRes = x.func(tmpLeft);
				rightRes = x.func(tmpRight);
				x.left = tmpLeft;
				x.right = tmpRight;
				x.leftRes = leftRes;
				x.rightRes = rightRes;
				System.out.printf("%.7f\n", x.left);
				System.out.printf("%.7f\n", x.leftRes);
				System.out.printf("%.7f\n", x.right);
				System.out.printf("%.7f\n", x.rightRes);
				x.display();
			} else {
				left = tmpLeft;
				tmpRight = left + gr * (right - left);
				tmpLeft = right - gr * (right - left);
				leftRes = x.func(tmpLeft);
				rightRes = x.func(tmpRight);
				x.left = tmpLeft;
				x.right = tmpRight;
				x.leftRes = leftRes;
				x.rightRes = rightRes;
				System.out.printf("%.7f\n", x.left);
				System.out.printf("%.7f\n", x.leftRes);
				System.out.printf("%.7f\n", x.right);
				System.out.printf("%.7f\n", x.rightRes);
				x.display();
			}
		}
		return tmpLeft;
	}
	
	public void textSolution() {
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("..\\GoldenMeanSearch_textsoulution_"+Serial_number.serialno()+".txt"), true));

			
			DecimalFormat format = new DecimalFormat("0.0000000");

			writer.write(format.format(x.left));
			writer.write(format.format(x.leftRes));
			writer.write(format.format(x.right));
			writer.write(format.format(x.rightRes));
			
			writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
	}
	
	
	public void start() {
		try {
			File file = new File("..\\GoldenMeanSearch_textsoulution_"+Serial_number.serialno()+".txt");
			if(!file.exists())
				file.createNewFile();
			else 
				file.delete();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public void finish() {
		Visualize.terminate=true;
	}
	
}	
