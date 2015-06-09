package org.adastraeducation.string;

//1  how to repaint after finish one animation?  store that image and then give out certain solution.  
//2  how to output solution in txt file the, name
//3  what's next step?  build on the web server? save png     graph1 png  sloution1 txt  batch

import org.adastraeducation.util.Generate_random_number;
import org.adastraeducation.util.Serial_number;
import org.adastraeducation.visualize.Visualize;

import processing.core.PApplet;

public class PAppletGraphDisplayer extends PApplet {

	private static final int TEXT_LENGTH = 23;
	private static final int PAT_LENGTH = 7;
	private static final char START_CHAR = 'a';
	private static final char END_CHAR = 'c';
	private static final long serialVersionUID = 1L;
	private static final String required_algorithm = "Kmp";
	private static final int required_num = 1;
	private static final int delaytime = 1000;
	private boolean interactive = true;
	private ArrayDisplayer displayer;

	private void interact() {
		if (!interactive)
			return;
		else {
			redraw();
			delay(delaytime);
		}
	}

	public void setData(char[] text, char[] pat) {
		displayer = new ArrayDisplayer(text, pat) {
			public void setHighlightVertex(int position, int color) {
				highlightvertex[position] = color;
			}

			public void setHighlightVertex2(int position, int color) {
				highlightvertex2[position] = color;
			}

			public void setHighlightVertex3(int position, int color) {
				highlightvertex3[position] = color;
				
			}

			public void setHighlightVertex4(int position, int color) {
				highlightvertex4[position] = color;
			}

			public void setMark(int mark) {
				this.mark = mark;
			}
			
			public void display() {
				interact();
			}
		};
	}

	public void setup() {
		char[] a = {'A','B','C',' ','A','B','C','D','A','B',' ','A','B','C','D','A','B','C','D','A','B','D','E'};
		char[] b = {'A','B','C','D','A','B','D'};
		//setData(Generate_random_number.RandomCharArray(TEXT_LENGTH, START_CHAR, END_CHAR), Generate_random_number.RandomCharArray(PAT_LENGTH, START_CHAR, END_CHAR));
		setData(a,b);
		size(600, 600);
		// noStroke();
		background(255);
		// fill(0, 102, 153, 204);
		fill(255);
		// smooth();
		// noLoop();
	}

	public void draw() {
		rectMode(CENTER);
		clear();
		background(255);
		fill(255);

		for (int x_axis = 0; x_axis < PAT_LENGTH; x_axis++) {
			fill(255);
			rect(55 + 56 * x_axis, 100, 56, 56);
			textSize(32);

			if (displayer.highlightvertex2[x_axis] == 1) {
				fill(100);
				text(displayer.pat[x_axis], 45 + 56 * x_axis, 110);
			} else if (displayer.highlightvertex2[x_axis] == 0) {
				fill(200);
				text(displayer.pat[x_axis], 45 + 56 * x_axis, 110);
			} else if (displayer.highlightvertex2[x_axis] == 2) {
				fill(204, 102, 0);
				text(displayer.pat[x_axis], 45 + 56 * x_axis, 110);
			} else {
				fill(0, 204, 0);
				text(displayer.pat[x_axis], 45 + 56 * x_axis, 110);
			}

			if (displayer.highlightvertex3[x_axis] == 2) {
				fill(255);
				rect(55 + 56 * x_axis, 200, 56, 56);
				textSize(32);
				fill(204, 102, 0);
				text(displayer.failure[x_axis], 45 + 56 * x_axis, 210);
			}
		}
		
		for (int x_axis = 0; x_axis < TEXT_LENGTH; x_axis++) {
			fill(255);
			rect(55 + 56 * x_axis, 300, 56, 56);
			textSize(32);
			
			if (displayer.highlightvertex[x_axis] == 1) {
				fill(100);
				text(displayer.text[x_axis], 45 + 56 * x_axis, 310);
			} else if (displayer.highlightvertex[x_axis] == 0) {
				fill(200);
				text(displayer.text[x_axis], 45 + 56 * x_axis, 310);
			} else if (displayer.highlightvertex[x_axis] == 2) {
				fill(204, 102, 0);
				text(displayer.text[x_axis], 45 + 56 * x_axis, 310);
			} else {
				fill(0, 204, 0);
				text(displayer.text[x_axis], 45 + 56 * x_axis, 310);
			}
			
		}
		
		for (int x_axis2 = 0; x_axis2 < PAT_LENGTH; x_axis2++) {
			fill(255);
			rect(55 + 56 * (displayer.mark + x_axis2), 400, 56, 56);
			textSize(32);
			if (displayer.highlightvertex4[x_axis2] == 1) {
				fill(100);
				text(displayer.pat[x_axis2], 45 + 56 * (displayer.mark + x_axis2), 410);
			} else if (displayer.highlightvertex4[x_axis2] == 0) {
				fill(200);
				text(displayer.pat[x_axis2], 45 + 56 * (displayer.mark + x_axis2), 410);
			} else if (displayer.highlightvertex4[x_axis2] == 2) {
				fill(204, 102, 0);
				text(displayer.pat[x_axis2], 45 + 56 * (displayer.mark + x_axis2), 410);
			} else {
				fill(0, 204, 0);
				text(displayer.pat[x_axis2], 45 + 56 * (displayer.mark + x_axis2), 410);
			}
		}

		if (Visualize.start) {
			Visualize.start = false;
			switch (required_algorithm) {
			case "Kmp":
				save("..\\Image Question Folder\\KMP_imagequestion_"
						+ Serial_number.serialno() + ".png");
				Thread kmp = new Thread() {
					public void run() {
						Kmp kmpData = new Kmp(displayer);
						kmpData.start();
						kmpData.run();
						kmpData.finish();
					}
				};
				kmp.start();
				break;
			default:
				break;
			}
		}

		if (Visualize.terminate) {
			switch (required_algorithm) {
			case "Kmp":
				save("..\\Image Solution Folder\\KMP_imagesolution_"
						+ Serial_number.serialno() + ".png");
				Visualize.terminate = false;

				if (Serial_number.increment() >= required_num) {
					exit();
				} else {
					setup();
					Visualize.start = true;
				}
				break;
			default:
				break;
			}
		}
	}

}