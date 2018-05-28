import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.bellevuecollege.anthony.cache.ResourceLoader;
public class ImageFileLoader implements ResourceLoader<String, Image> {

	@Override
	public Image loadResource(String location) throws IOException {
		return ImageIO.read(new File(location));
	}
	
	

}
