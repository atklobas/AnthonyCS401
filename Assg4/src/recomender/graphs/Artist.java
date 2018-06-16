package recomender.graphs;

import java.util.ArrayList;

public class Artist {
	//these are package protected on purpose
	int id;
	String name;
	String url;
    String picture;
    ArrayList<User> fans =new ArrayList<User>();
	public Artist(int id, String name, String url, String picture) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.picture = picture;
	}
	public void addFan(User usr) {
		fans.add(usr);
	}
	public String toString() {
		return name;
	}

}
