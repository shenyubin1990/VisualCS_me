package org.adastraeducation.search;

//1  how to repaint after finish one animation?  store that image and then give out certain solution.  
//2  how to output solution in txt file the, name
//3  what's next step?  build on the web server? save png     graph1 png  sloution1 txt  batch


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
	private String required_algorithm = "HeapSort";
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
	public void setData(int[] data2){
		displayer=new PositionDisplayer(data2){								    	
			public void setHighlightVertex(int position, int color){          
				highlightvertex[position]=color;
//				interact();
			}
			public void display() {
				interact();
			}
		};
	}

	public void SetFunction (String x) {
		
	}
	public void setup() {
		SetFunction(func);
//		setData(randomArray.data);
		size(600,600);
    	//noStroke();
    	background(255);
    	//fill(0, 102, 153, 204);
    	fill(255);
    	smooth();
    	noLoop();
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
    	float left = -10, right = 10;	//real position * 10 = axis position
    	while (left <= right) {
    		float temp = (float) (left + 0.01);
//    		fill(100);
    		line(ox+10*left, oy-10*f(left), ox+10*temp, oy-10*f(temp));
//    		System.out.println(ox+left);
//    		System.out.println(oy-f(left));
//    		System.out.println(ox+temp);
//    		System.out.println(oy-f(temp));
    		left = temp;
    	}
    	
    	
		
	}

}