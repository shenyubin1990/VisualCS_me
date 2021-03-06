package org.adastraeducation.search;

//1  how to repaint after finish one animation?  store that image and then give out certain solution.  
//2  how to output solution in txt file the, name
//3  what's next step?  build on the web server? save png     graph1 png  sloution1 txt  batch


import java.text.DecimalFormat;

import org.adastraeducation.sort.MergeSort;
import org.adastraeducation.util.Generate_random_number;
import org.adastraeducation.util.Serial_number;
import org.adastraeducation.util.SetArray;
import org.adastraeducation.visualize.Visualize;

import processing.core.PApplet;


public class PAppletGraphDisplayer extends PApplet {   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private MergeSort mergedata;
	private String required_algorithm = "GoldenMeanSearch";
	private int required_num = 1;
	private int delaytime = 2000;
	private boolean interactive=true;
	private PositionDisplayer displayer;
	private String func = "(x-1)*(x-1)";
	private void interact(){
		if(!interactive)
			return;
		else{
			redraw();
			delay(delaytime);
		}	
	}
	public void setData() {
		displayer=new PositionDisplayer(){								    	
			public void display() {
				interact();
			}
		};
	}

//	public void SetFunction (String x) {
//		for (in)
//	}
	public void setup() {
//		SetFunction(func);
		setData();
		size(600,600);
    	//noStroke();
    	background(255);
    	//fill(0, 102, 153, 204);
    	fill(255);
    	smooth();
    	//noLoop();
    }
	public float f(double x) {
		return (float) ((x-1)*(x-1));
		
	}
    public void draw() {    
    	clear();
    	background(255);
    	fill(100);
    	line(0, 300, 600, 300);
    	line(300, 0, 300, 600);
    	//o = (300, 300)
    	
    	float ox = 300, oy = 300;
    	float left = -2, right = 2;	//real position * 10 = axis position
    	while (left <= right) {
    		float temp = (float) (left + 0.01);
//    		fill(100);
    		line(ox+50*left, (float)(oy-50*displayer.func(left)), ox+50*temp, (float)(oy-50*displayer.func(temp)));
//    		System.out.println(ox+left);
//    		System.out.println(oy-f(left));
//    		System.out.println(ox+temp);
//    		System.out.println(oy-f(temp));
    		left = temp;
    	}
    	
    	DecimalFormat format = new DecimalFormat("0.0000000");
    	
    	textSize(28);
    	text("f(x) = x*x - 1", 20, 350);
    	text("a = ", 50, 400);
    	text(format.format(displayer.left), 100, 400);
    	text("f(a) = ", 20, 450);
    	text(format.format(displayer.leftRes), 100, 450);
    	text("b = ", 50, 500);
    	text(format.format(displayer.right), 100, 500);
    	text("f(b) = ", 20, 550);
    	text(format.format(displayer.rightRes), 100, 550);
    	
    	if (displayer.left != -1) {
    		fill(100);
    		line( (float)(ox+50*displayer.left),  (float)(600),  (float)(ox+50*displayer.left), (float)(oy-50*displayer.func(displayer.left)));
    	} 
    	
    	if (displayer.right != -1) {
    		fill(100);
    		line( (float)(ox+50*displayer.right),  (float)(600),  (float)(ox+50*displayer.right), (float)(oy-50*displayer.func(displayer.right)));
    	}
    	if(Visualize.start){
			Visualize.start=false;
			switch (required_algorithm) {
		 	case "GoldenMeanSearch":
		 		save("..\\Image Question Folder\\MergeSort_imagequestion_"+Serial_number.serialno()+".png");
		    	Thread m = new Thread() {
					public void run() {
						GoldenMeanSearch gms = new GoldenMeanSearch(displayer);
						delay(delaytime);
						gms.start();
						gms.goldenMeanSearch(-2, 2, 1e-5);
						gms.finish();
					}
				};
				m.start();
		 		break;
			}
		}
    	
    	if(Visualize.terminate){
			switch (required_algorithm) {
			case "GoldenMeanSearch":
				save("..\\Image Solution Folder\\MergeSort_imagesolution_"+Serial_number.serialno()+".png" );
			    Visualize.terminate=false;
			     
			    if(Serial_number.increment()>=required_num){
			     	exit();
			    }
			    else
			    {
			    	setup();
			    	Visualize.start=true;
			    }
				break;
			}
    	}
		
	}

}