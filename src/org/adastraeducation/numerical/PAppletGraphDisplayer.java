package org.adastraeducation.numerical;

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
	private String required_algorithm = "Romberg";
	private int required_num = 1;
	private int delaytime = 2000;
	private boolean interactive=true;
	private AreaDisplayer displayer;
//	private String func = "(x-1)*(x-1)";
	private void interact(){
		if(!interactive)
			return;
		else{
			redraw();
			delay(delaytime);
		}	
	}
	public void setData() {
		displayer=new AreaDisplayer(){								    	
			public void display() {
				interact();
			}
		};
	}

	public void setup() {
		setData();
		size(600,600);
    	//noStroke();
    	background(255);
    	//fill(0, 102, 153, 204);
    	fill(255);
    	smooth();
    	
    	displayer.setA_RandomBoundary(0, 0);
		displayer.setB_RandomBoundary(1, 1);
		
    	//noLoop();
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
    		line(ox+50*left, (float)(oy-50*displayer.func(left)), ox+50*temp, (float)(oy-50*displayer.func(temp)));
    		left = temp;
    	}
    	
    	DecimalFormat format = new DecimalFormat("0.0000000");
    	
    	textSize(28);
    	text("f(x) = x*x", 20, 350);
    	text("a = ", 50, 400);
    	text(format.format(displayer.a), 100, 400);
    	text("b = ", 50, 450);
    	text(format.format(displayer.b), 100, 450);
    	text("n = ", 50, 500);
    	text(displayer.n, 100, 500);
    	text("area = ", 10, 550);
    	text(format.format(displayer.area), 100, 550);
    	
    	double h, x;
    	h = (displayer.b-displayer.a)/displayer.n;
    	line((float)(ox+50*displayer.a), (float)(oy-50*displayer.func(displayer.a)), (float)(ox+50*displayer.a), (float)(300));
	    for (int i = 1; i <= displayer.n; i++) {
	    	x = displayer.a + i*h;
	    	line((float)(ox+50*x), (float)(oy-50*displayer.func(x)), (float)(ox+50*x), (float)(300));
	    	line((float)(ox+50*x), (float)(oy-50*displayer.func(x)), (float)(ox+50*(x-h)), (float)(oy-50*displayer.func(x-h)));
	    }	

    	if(Visualize.start){
			Visualize.start=false;
			switch (required_algorithm) {
		 	case "Trapezoidal":
		 		save("..\\Image Question Folder\\Trapezoidal_imagequestion_"+Serial_number.serialno()+".png");
		    	Thread m = new Thread() {
					public void run() {
						Trapezoidal trap = new Trapezoidal(displayer);
						delay(delaytime);
						trap.start();
						trap.trap(displayer.a, displayer.b, 1e-5);
						trap.finish();
					}
				};
				m.start();
		 		break;
	    	case "Romberg":
		 		save("..\\Image Question Folder\\Romberg_imagequestion_"+Serial_number.serialno()+".png");
		    	Thread m1 = new Thread() {
					public void run() {
						Romberg rom = new Romberg(displayer);
						delay(delaytime);
						rom.start();
						rom.romberg(displayer.a, displayer.b, 1e-5);
						rom.finish();
					}
				};
				m1.start();
		 		break;
			}
    	}
    	
    	if(Visualize.terminate){
			switch (required_algorithm) {
			case "Trapezoidal":
				save("..\\Image Solution Folder\\Trapezoidal_imagesolution_"+Serial_number.serialno()+".png" );
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
			case "Romberg":
				save("..\\Image Solution Folder\\Romberg_imagesolution_"+Serial_number.serialno()+".png" );
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