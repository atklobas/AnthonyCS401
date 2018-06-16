package recomender.csv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
/**
 * this class essentially holds data from a csv file, though really it could be used for any relational data 
 *
 */
public class CSV implements Iterable<String[]>{
	private String[] titles;
	private ArrayList<String[]> tuples;
	public CSV(String[] titles){
		this.titles=titles;
		tuples=new ArrayList<String[]>();
	}
	
	
	
	public String getColumnTitle(int index) {
		return titles[index];
	}
	public int getColumns() {
		return titles.length;
	}
	/**
	 * gets the string array assosiated with row i
	 * @param i
	 * @return
	 */
	public String[] getTuple(int i) {
		return tuples.get(i);
	}
	
	
	public String toString() {
		String buffer=Arrays.toString(titles);
		for(int i=0;i<tuples.size()&&i<5;i++) {
			buffer+="\n"+Arrays.toString(tuples.get(i));
		}
		if(tuples.size()>=5) {
			buffer+="\n...";
			buffer+="\n"+Arrays.toString(tuples.get(tuples.size()-1));
		}
		return buffer;
	}
	public void addTuple(String[] tuple) {
		//if there are not as many fields as titles they will be empty. if there are too many fields it's an invalid file
		String[] toAdd=new String[titles.length];
		int index=0;
		for(String s:tuple) {
			toAdd[index++]=s;
		}
		
		tuples.add(toAdd);
	}



	@Override
	public Iterator<String[]> iterator() {
		return tuples.iterator();
	}
}
