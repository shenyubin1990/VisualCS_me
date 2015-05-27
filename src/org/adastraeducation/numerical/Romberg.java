package org.adastraeducation.numerical;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import org.adastraeducation.search.GoldenMeanSearch;
import org.adastraeducation.search.TextPositionDisplayer;
import org.adastraeducation.util.Generate_random_number;
import org.adastraeducation.util.Serial_number;
import org.adastraeducation.visualize.Visualize;

public class Romberg {
	private AreaDisplayer displayer;
	
	public Romberg(AreaDisplayer x) {
		displayer = x;
	}
	
	public static void main(String args[]) {
		double   area;      // Store result in area
//		double   a, b;      // Left and right endpoints
//		int      n = Generate_random_number.RandomInteger(2, 4);;         // Number of trapezoids
//		double   h;         // Trapezoid base width
		
		Romberg rom = new Romberg(new TextAreaDisplayer());
		rom.start();
		
		rom.displayer.setA_RandomBoundary(0, 0);
		rom.displayer.setB_RandomBoundary(1, 1);
		rom.displayer.set_eps(1e-5);
		
		area = rom.romberg(rom.displayer.a, rom.displayer.b, rom.displayer.eps);
		
		System.out.println("area = " + area);
		System.out.println("times = " + rom.displayer.getN());      
	}  // main

	   // Method:       trap
	   // Purpose:      Estimate area using the trapezoidal rule
	   // Input args:   a: left endpoint
	   //               b: right endpoint
	   //               n: number of trapezoids
	   //               h: stepsize = length of base of trapezoids
	   // Return val:   Trapezoidal rule estimate of area between x-axis,
	   //               x = a, x = b, and graph of f(x)
	double romberg(double a, double b, double eps) {
		double area = 0, pre_area;   // Store result in area 
	    double x, h;
	    do {
	    	pre_area = area;
		    h = (b-a)/(++displayer.n);
		    area = (displayer.func(a) + displayer.func(b))/2.0;
		    for (int i = 1; i < displayer.n; i++) {
		    	x = a + i*h;
		    	area = area + displayer.func(x);
		    }	
		    area = area*h;
		    area = (4*area-pre_area)/3.0;
		    System.out.println(area);
//		    System.out.println(pre_area);
		    displayer.area = area;
		    
		    textSolution();
		    displayer.display();
	    } while (Math.abs(area - pre_area) >eps);
	    return area;
	}   

	public void textSolution() {
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("..\\Trapezoidal_textsoulution_"+Serial_number.serialno()+".txt"), true));

			
			DecimalFormat format = new DecimalFormat("0.0000000");
			
			writer.write(format.format(displayer.area) + "\n");
			
			writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
	}
	
	
	public void start() {
		try {
			File file = new File("..\\Trapezoidal_textsoulution_"+Serial_number.serialno()+".txt");
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
