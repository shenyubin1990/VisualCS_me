package org.adastraeducation.search.binary;

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
	private static final int START_RANGE = 0;
	private static final int END_RANGE = 20;
	private static final long serialVersionUID = 1L;
	private static final String required_algorithm = "BinarySearch";
	private static final int required_num = 1;
	private static final int delaytime = 1000;
	private static final int arrayLength = 20;
	private boolean interactive = true;
	private PositionDisplayer displayer;
	private SetArray randomArray;

	private void interact() {
		if (!interactive)
			return;
		else {
			redraw();
			delay(delaytime);
		}
	}

	public void setData(int[] data2, int keyValue) {
		displayer = new PositionDisplayer(data2, keyValue) {
			public void setHighlightVertex(int position, int color) {
				highlightvertex[position] = color;
			}

			public void display() {
				interact();
			}
		};
	}

	public void setup() {
		randomArray = new SetArray(arrayLength, START_RANGE, END_RANGE);
		randomArray.sort();
		setData(randomArray.data, Generate_random_number.RandomInteger(0, 9));
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

		for (int x_axis = 0; x_axis < arrayLength; x_axis++) {
			fill(255);
			rect(55 + 56 * x_axis, 200, 56, 56);
			textSize(32);

			if (displayer.highlightvertex[x_axis] == 1) {
				fill(100);
				text(displayer.data[x_axis], 45 + 56 * x_axis, 210);
			} else if (displayer.highlightvertex[x_axis] == 0) {
				fill(200);
				text(displayer.data[x_axis], 45 + 56 * x_axis, 210);
			} else if (displayer.highlightvertex[x_axis] == 2) {
				fill(204, 102, 0);
				text(displayer.data[x_axis], 45 + 56 * x_axis, 210);
			} else {
				fill(0, 204, 0);
				text(displayer.data[x_axis], 45 + 56 * x_axis, 210);
			}
		}
		
		fill(255);
		rect(55 + 56 * 0, 100, 56, 56);
		textSize(32);
		fill(0, 204, 0);
		text(displayer.keyValue, 45 + 56 * 0, 110);

		if (Visualize.start) {
			Visualize.start = false;
			switch (required_algorithm) {
			case "BinarySearch":
				save("..\\Image Question Folder\\BinarySearch_imagequestion_"
						+ Serial_number.serialno() + ".png");
				Thread Binary = new Thread() {
					public void run() {
						BinarySearch BinaryData = new BinarySearch(displayer);
						BinaryData.start();
						BinaryData.binary(displayer.keyValue);
						BinaryData.finish();
					}
				};
				Binary.start();
				break;
			default:
				break;
			}
		}

		if (Visualize.terminate) {
			switch (required_algorithm) {
			case "BinarySearch":
				save("..\\Image Solution Folder\\BinarySearch_imagesolution_"
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