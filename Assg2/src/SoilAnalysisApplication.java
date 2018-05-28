import java.io.File;
import java.io.IOException;

import analyser.SoilLoader;
import analyser.SoilSample;
import edu.bellevuecollege.anthony.cache.*;

public class SoilAnalysisApplication {
	
	public static void main(String[] args) throws IOException {
		Cache<File, SoilSample> cache=new Cache<File, SoilSample>(10,new SoilLoader());
		
		for(String s: args) {
			File f=new File(s);
			if(f.exists()) {
				SoilSample soil=cache.get(f);
				System.out.printf("The soil named %s can%s drain\n", soil.getName(),soil.canDrain()?"":"'t");
				
			}else {
				System.err.printf("%s does not exist!\n");
			}
		}
	}

}
