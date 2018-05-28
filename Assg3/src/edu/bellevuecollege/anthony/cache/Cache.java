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
	
	public Cache(int size, ResourceLoader<K,V> loader) {
		this.loader=loader;
		this.size=size;
		map=new HashMap<K,Node>();
	}
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
	
	private V loadAndAppend(K location)throws IOException{
		return append(location,loader.loadResource(location));
	}
	
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
	
	public void put(K key, V value) {
		Node node=map.get(key);
		if(node==null) 
			append(key,value);
		else 
			modeToEnd(node,key);
		discardOld();
	}
	
	private void discardOld(){
		while(count>size) {
			map.remove(head.key);
			head=head.next;
			count--;
		}
	}
	public int getHits() {
		return hits;
	}
	public int getMisses() {
		return misses;
	}
	public void clear() {
		count=0;
		head=tail=null;
		map.clear();
	}
	public double getHitRatio() {
		return hits/(double)(hits+misses);
	}
	public double getMissRatio() {
		return misses/(double)(hits+misses);
	}
	public boolean containsKey(K key) {
		return map.containsKey(key);
	}
	public boolean containsValue(V value) {
		for(Node node:map.values()) {
			if(node.data.equals(value)) {
				return true;
			}
		}
		return false;
	}
	
	
	
	
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
