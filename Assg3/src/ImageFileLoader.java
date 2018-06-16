import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.bellevuecollege.anthony.cache.ResourceLoader;
/**
 * this is just an exmaple to show other types of loaders are easlily made
 * @author aklobas
 *
 */
public class ImageFileLoader implements ResourceLoader<String, Image> {

	@Override
	public Image loadResource(String location) throws IOException {
		return ImageIO.read(new File(location));
	}
	
	

}
