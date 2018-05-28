package analyser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import edu.bellevuecollege.anthony.cache.ResourceLoader;

public class SoilLoader implements ResourceLoader<File, SoilSample>{
	
	@Override
	public SoilSample loadResource(File location) throws IOException {
		byte[] chars = Files.readAllBytes(location.toPath());
		return new SoilSample(location.getName() ,new String(chars));
	}

}
