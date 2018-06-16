package edu.bellevuecollege.anthony.cache;
import java.io.IOException;
import java.util.HashMap;

public class Cache<K,V> {
	private ResourceLoader<K,V> loader;
	private int size;
	private int count=0;
	private HashMap<K,Node> map;
	private Node head=null;
	private Node tail=null;

	private int hits=0;
	private int misses=0;
	/**
	 * creates a cache
	 * @param size the max number of items you want stored
	 * @param loader how it should try to load things it doesn't alreay contain
	 */
	public Cache(int size, ResourceLoader<K,V> loader) {
		this.loader=loader;
		this.size=size;
		map=new HashMap<K,Node>();
	}
	/**
	 * gets item specified by key from cache or loads it if its not found
	 * @param location
	 * @return
	 * @throws IOException
	 */
	public V get(K location) throws IOException {
		Node node=map.get(location);
		V data;
		if(node==null) 
			data= loadAndAppend(location);
		else 
			data= modeToEnd(node,location);
		discardOld();
		return data;
	}
	/**
	 * a sub method that loads a resource and appends it to the end of the age queue
	 * @param location
	 * @return
	 * @throws IOException
	 */
	private V loadAndAppend(K location)throws IOException{
		return append(location,loader.loadResource(location));
	}
	/**
	 * appends an item to the age queue
	 * @param location, 
	 * @param value
	 * @return
	 */
	private V append(K location,V value)  {
		misses++;
		count++;
		Node toAppend=new Node(location,value);
		if(count==1) 
			head=toAppend;
		else
			tail.next=toAppend;
		tail=toAppend;
		map.put(location, toAppend);	
		return value;
	}
	/**
	 * removes node from age queue and appends it to the end
	 * this is so one item is never double represented which 
	 * could cause other items to be removed prematurely
	 * @param node
	 * @param location
	 * @return
	 */
	private V modeToEnd(Node node,K location) {
		hits++;
		Node toAppend=new Node(location,node.data);
		if(node!=tail) {
			node.delete();
			map.put(node.key, node);
			if(node.next==null) 
				tail=node;
			tail.next=toAppend;
			tail=toAppend;
			map.put(location, toAppend);
		}
		
		return toAppend.data;
	}
	
	/**
	 * manually inserts a key and value to cache
	 * @param key
	 * @param value
	 */
	public void put(K key, V value) {
		Node node=map.get(key);
		if(node==null) 
			append(key,value);
		else 
			modeToEnd(node,key);
		discardOld();
	}
	/**
	 * used internally to bring cache down to size if it grows too large
	 */
	private void discardOld(){
		while(count>size) {
			map.remove(head.key);
			head=head.next;
			count--;
		}
	}
	/**
	 * gets the number of hits since instantiation/clearing
	 * @return
	 */
	public int getHits() {
		return hits;
	}
	/**
	 * gets number of misses sincce instantition/clearing
	 * @return
	 */
	public int getMisses() {
		return misses;
	}
	/**
	 * removes all items from cache and resets hit and miss
	 */
	public void clear() {
		hits=0;
		misses=0;
		count=0;
		head=tail=null;
		map.clear();
	}
	/**
	 * returns hit ratio (hits/total)
	 * @return
	 */
	public double getHitRatio() {
		return hits/(double)(hits+misses);
	}
	/**
	 * returns miss ration (misses/total)
	 * @return
	 */
	public double getMissRatio() {
		return misses/(double)(hits+misses);
	}
	/**
	 * returns if the item assosiated with this key is currently cached
	 * @param key
	 * @return
	 */
	public boolean containsKey(K key) {
		return map.containsKey(key);
	}
	/**
	 * returns if the item itself exists anywhere in the cache (maybe you don't know the key)
	 * @param value
	 * @return
	 */
	public boolean containsValue(V value) {
		for(Node node:map.values()) {
			if(node.data.equals(value)) {
				return true;
			}
		}
		return false;
	}
	
	
	
	/**
	 * this is an internal singly linked list/queue, every time something is used or added it
	 * shoudl be appeneded to the end so the fist item in the list should be the oldest item 
	 * if one needs to be removed
	 * @author aklobas
	 *
	 */
	private class Node{
		private Node next;
		private K key;
		private V data;
		private Node(K key,V data) {
			this.key=key;
			this.data=data;
		}
		public void delete() {
			this.data=next.data;
			this.key=next.key;
			this.next=next.next;
		}
	}
}
