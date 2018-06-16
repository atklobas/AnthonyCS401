package edu.bellevuecollege.anthony.cache;
import java.io.IOException;
/**
 * this is an interface used by cache to retrieve files if the cache does not alreay contain it
 * 
 * 
 * @author aklobas
 *
 * @param <K> the type of the keys
 * @param <V> the type of the values
 */
public interface ResourceLoader<K,V> {

	/**
	 * loads the resource specified by the key, or throws an ioexception if its not retrievable
	 * @param location
	 * @return
	 * @throws IOException
	 */
	public V loadResource(K location) throws IOException;
	
}
