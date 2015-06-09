package org.adastraeducation.string;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.adastraeducation.string.TextArrayDisplayer;
import org.adastraeducation.string.ArrayDisplayer;
import org.adastraeducation.util.Generate_random_number;
import org.adastraeducation.util.Serial_number;
import org.adastraeducation.visualize.Visualize;
 
public class Kmp
{
	private static final int TEXT_LENGTH = 15;
	private static final int PAT_LENGTH = 3;
	private static final char START_CHAR = 'a';
	private static final char END_CHAR = 'c';
	
    public ArrayDisplayer sd;
	
	public Kmp() {
		super();
	}

	public Kmp(ArrayDisplayer sd) {
		this.sd = sd;
	}

	/** Main Function **/
    public static void main(String[] args) throws IOException
    {    
        Kmp kmp = new Kmp(new TextArrayDisplayer(Generate_random_number.RandomCharArray(TEXT_LENGTH, START_CHAR, END_CHAR), Generate_random_number.RandomCharArray(PAT_LENGTH, START_CHAR, END_CHAR)));
        kmp.run();
    }
    
    /** Failure function for a pattern **/
    private void fail(char[] pat)
    {
        int n = pat.length;
        sd.failure[0] = -1;
        if(Visualize.visualize){
        	sd.setHighlightVertex3(0, 2);
            sd.display();
        }
        for (int j = 1; j < n; j++)
        {
            int i = sd.failure[j - 1];
            while ((pat[j] != pat[i + 1]) && i >= 0)
                i = sd.failure[i];
            if (pat[j] == pat[i + 1])
            	sd.failure[j] = i + 1;
            else
            	sd.failure[j] = -1;
            if(Visualize.visualize){
            	sd.setHighlightVertex3(j, 2);
                sd.display();
            }
        }
        printChar(pat);
        printInt(sd.failure);
        textSolutionChar(pat);
        textSolutionInt(sd.failure);
        
    }
    
    /** Function to find match for a pattern 
     * @throws IOException **/
    private int posMatch(char[] text, char[] pat) 
    {
        int i = 0, j = 0;
        int lens = text.length;
        int lenp = pat.length;
        printInitData(); //For print and text solution
        if(Visualize.visualize){
        	sd.setHighlightVertex(0, 2);
            sd.setHighlightVertex4(0, 2);
            sd.display();
        }
		while (i < lens && j < lenp)
		{
		    if (text[i] == pat[j]){
		    	if(Visualize.visualize){
		    		sd.setHighlightVertex(i, 2);
			    	sd.setHighlightVertex4(j, 2);
			    	sd.display();
		    	}
		        i++;
		        j++;
		    }
		    else if (j == 0){
		    	if(Visualize.visualize){
		    		for(int k = 0; k <= i; k++){
			    		sd.setHighlightVertex(k, 1);
			    	}
			    	for(int k = 0; k < lenp; k++){
			    		sd.setHighlightVertex4(k, 1);
			    	}
		    	}
		    	i++;
		    	if(Visualize.visualize){
		    		if(i < lens){
			    		sd.setHighlightVertex(i, 2);
			    	}
			    	sd.setMark(i);
			    	sd.setHighlightVertex4(j, 2);
			    	sd.display();
		    	}
		    }
		    else{
		    	j = sd.failure[j - 1] + 1;
				if(Visualize.visualize){
					for(int k = 0; k < i-j; k++){
			    		sd.setHighlightVertex(k, 1);
			    	}
			    	for(int k = j; k < lenp; k++){
			    		sd.setHighlightVertex4(k, 1);
			    	}
			    	sd.setHighlightVertex(i, 2);
			    	sd.setHighlightVertex4(j, 2);
			    	sd.setMark(i - j);
			    	sd.display();
				}
		    }
		    for (int s = 0; s < i - j; s++) {
				System.out.print(" " + "\t");
				textSolutionString(" " + "\t");
			}
			printChar(pat);
			textSolutionChar(pat);
		}
        return ((j == lenp) ? (i - lenp) : -1);
    }
    
    public void printInitData(){
    	int[] position = new int[sd.text.length];
        for(int p = 0; p < sd.text.length; p++){
        	position[p] = p;
        }
        printInt(position);
        printChar(sd.text);
        printChar(sd.pat);
        textSolutionInt(position);
        textSolutionChar(sd.text);
        textSolutionChar(sd.pat);
        for(int i = 0; i < sd.text.length -1; i++){
        	System.out.print("========");
        	textSolutionString("========");
        }
        System.out.println();
        textSolutionString("\r\n");
    }
    
    public void run(){
    	 /** pre construct failure array for a pattern **/
    	sd.failure = new int[sd.pat.length];
        fail(sd.pat);
        /** find match **/
        int pos = posMatch(sd.text, sd.pat);
        System.out.println(sd.text);
        System.out.println(sd.pat);
        if (pos == -1)
            System.out.println("\nNo match found");
        else
            System.out.println("\nMatch found at index "+ pos);
    }
    
    public void start() {
		try {
			File file = new File("Kmp_textsoulution_"+Serial_number.serialno()+".txt");
			if(!file.exists())
				file.createNewFile();
			else 
				file.delete();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void finish() {
		Visualize.terminate=true;
	}
	
	public void textSolutionString(String data) {
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("Kmp_textsoulution_"+Serial_number.serialno()+".txt"), true));
			writer.write(data);
			writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}    
	}
	
	public void textSolutionInt(int[] data) {
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("Kmp_textsoulution_"+Serial_number.serialno()+".txt"), true));
			for (int i = 0; i < data.length; i++) {
				writer.write(data[i] + "\t");
			}
			writer.write("\r\n");
			writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}    
	}
	
	public void textSolutionChar(char[] data) {
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("Kmp_textsoulution_"+Serial_number.serialno()+".txt"), true));
			for (int i = 0; i < data.length; i++) {
				writer.write(data[i] + "\t");
			}
			writer.write("\r\n");
			writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}    
	}
	
	public void printInt(int[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + "\t");
		}
		System.out.println();
	}
	
	public void printChar(char[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + "\t");
		}
		System.out.println();
	}
}