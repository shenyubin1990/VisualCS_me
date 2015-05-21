package org.adastraeducation.sort.quicksort;

import org.adastraeducation.util.Serial_number;
import org.adastraeducation.util.SetArray;
import org.adastraeducation.visualize.Visualize;

import processing.core.PApplet;

public class PAppletGraphDisplayer extends PApplet {   
	
	private static final long serialVersionUID = 1L;
	private SortDisplayer displayer;
    private QuickSort qs;
    private int delaytime=300;//300;			
    									
	private int highlight_num_v1;       //for main vertex
	private boolean interactive=true;
	private int required_num =10;
	private SetArray randomArray;
	private int arrayLength = 10;
	
	private void interact(){
		if(!interactive)
			return;
		else{
			repaint();
			delay(delaytime);
				
		}	
	}
	
	public void setQuickSort(QuickSort qs){
		displayer=new SortDisplayer(qs){								    //how to repaint();	
			public void setHighlightVertex(int v,int highlight){            //how to improve these to combine together
				highlightvertex[v]=highlight;
				highlight_num_v1=highlight;
				interact();
				//delay(1000);
			}
			public void display() {}
		};
	}

	public void setup() {
		qs = new QuickSort(arrayLength);
		setQuickSort(qs);
		size(600,600);
    	background(255);
    	fill(255);
    	noLoop();
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
    			text(displayer.highlightvertex[x_axis], 45+56*x_axis, 210);	
    		} else if (displayer.highlightvertex[x_axis] == 0){
    			fill(200);
    			text(displayer.highlightvertex[x_axis], 45+56*x_axis, 210);
    		} else {
    			fill(204, 102, 0);
    			text(displayer.highlightvertex[x_axis], 45+56*x_axis, 210);
    		}

    		
    		//delay(100);
    	}
        
		if(Visualize.start){
		 	
		 	Visualize.start=false;
//		 	MergeSort mergedata = new MergeSort(this);
//			interact();
//			mergedata.mergeSort();
		 	//save("Bellmanford_imagequestion_"+Serial_number.serialno()+".png");
	    	Thread t = new Thread() {
				public void run() {
					//delay(1000);
					QuickSort qsNew = new QuickSort(displayer);
					qsNew.sort(0, arrayLength-1);
//					MergeSort mergedata = new MergeSort(this);
//					interact();
//					mergedata.sort(0, 9);
//					mergedata.display(0, 9);
//					delay(2000);
//					mergedata.mergeSort();
					//repaint();
				}
			};
			
			t.start();
		}
 
		if(Visualize.terminate){
		     //save("Bellmanford_imagesolution_"+Serial_number.serialno()+".png" );
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
