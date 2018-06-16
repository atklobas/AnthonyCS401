package edu.bellevuecollege.anthony.cache;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class TextFileLoader implements ResourceLoader<String, String> {
	@Override
	public String loadResource(String location) throws IOException {
		File f=new File(location);
		//this is the fastest way to read files that i've found
		byte[] chars = Files.readAllBytes(f.toPath());

		
		return new String(chars);
	}

}
