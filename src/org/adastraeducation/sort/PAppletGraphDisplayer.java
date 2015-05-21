package org.adastraeducation.sort;

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
	private int required_num = 5;
	private int delaytime = 10;
	private int arrayLength = 10;
	private boolean interactive=true;
	private ArrayDisplayer displayer;
	private SetArray randomArray;
	private void interact(){
		if(!interactive)
			return;
		else{
			redraw();
			delay(delaytime);
		}	
	}
	public void setData(int[] data2){
		displayer=new ArrayDisplayer(data2){								    	
			public void setHighlightVertex(int start,int end, boolean flag){          
				if (flag) { 
					for (int v = start; v < end+1; v++)
						displayer.highlightvertex[v]=2;
				} else {
					for (int v = start; v < 10; v++)
						displayer.highlightvertex[v]=0;
					for (int v = start; v < end+1; v++)
						highlightvertex[v]=1;	
				}
				interact();
				//delay(1000);
			}
		};
	}

	public void setup() {
		randomArray = new SetArray(arrayLength);
		setData(randomArray.data);
		size(600,600);
    	//noStroke();
    	background(255);
    	//fill(0, 102, 153, 204);
    	fill(255);
    	//smooth();
    	//noLoop();
    }
    public void draw() {    
    	rectMode(CENTER);
    	int i = 0;
    	for (int x_axis = 0; x_axis < 10; x_axis++) {
    		fill(255);
    		rect(55+56*x_axis, 200, 56, 56);
    		textSize(32);
    		
    	
    		if (displayer.highlightvertex[x_axis] == 1) {
    			fill(100);
    			text(displayer.data[x_axis], 45+56*x_axis, 210);	
    		} else if (displayer.highlightvertex[x_axis] == 0){
    			fill(200);
    			text(displayer.data[x_axis], 45+56*x_axis, 210);
    		} else {
    			fill(204, 102, 0);
    			text(displayer.data[x_axis], 45+56*x_axis, 210);
    		}

    		
    		//delay(100);
    	}
        
		if(Visualize.start){
		 	
		 	Visualize.start=false;
		 	save("src\\Image Question Folder\\MergeSort_imagequestion_"+Serial_number.serialno()+".png");
	    	Thread t = new Thread() {
				public void run() {
					MergeSort mergedata = new MergeSort(displayer);
					mergedata.sort(0, arrayLength-1);
					mergedata.finish();
				}
			};
			
			t.start();
		}
 
		if(Visualize.terminate){
		     save("src\\Image Solution Folder\\MergeSort_imagesolution_"+Serial_number.serialno()+".png" );
		     Visualize.terminate=false;
		     
		     if(Serial_number.increment()>=required_num){
		     	exit();
		     }
		     else
		     {
		     	setup();
		     	Visualize.start=true;
		     }
		 	
		}
	}

}