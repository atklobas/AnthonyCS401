package edu.bellevuecollege.anthony.cache;
import java.io.IOException;

public interface ResourceLoader<K,V> {

	public V loadResource(K location) throws IOException;
	
}
