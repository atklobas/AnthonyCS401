package recomender.csv;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import edu.bellevuecollege.anthony.cache.ResourceLoader;

public class CSVLoader{
	
	private char seperator;
	private char newline;
	private String dir;
	
	/**
	 * creates a loader that will try to read CSV files in a director
	 * @param dir directory where data files are stored
	 * @param seperator the character that specifies a new field (normally a comma or tab)
	 * @param newline the character that specifies a new record (almost always a newline)
	 */
	public CSVLoader(String dir,char seperator,char newline) {
		if(dir.charAt(dir.length()-1)!='/') {
			dir+='/';
		}
		this.dir=dir;
		this.seperator=seperator;
		this.newline=newline;
	}
	public CSVLoader(String dir) {
		this(dir,'\t','\n');
	}

	
	
	public CSV loadResource(String location) throws IOException {
		Scanner s=new Scanner(new File(dir+location));
		s.useDelimiter(""+newline);
		CSV data=new CSV(s.next().trim().split(""+seperator));
		while(s.hasNext()) {
			data.addTuple(s.next().trim().split(""+seperator));
		}
		s.close();
		return data;
	}

}
