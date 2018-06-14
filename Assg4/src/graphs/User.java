package graphs;

import java.util.ArrayList;

public class User {
	int id;
	ArrayList<User> friends=new ArrayList<User>();
	ArrayList<Artist> artists=new ArrayList<Artist>();
	ArrayList<Integer> artistsWeights=new ArrayList<Integer>();
	public User(int id) {
		this.id=id;
	}

	public void addFriend(User friend) {
		friends.add(friend);
		
	}

	public void addArtist(Artist artist, int weight) {
		artists.add(artist);
		artistsWeights.add(weight);
	}
	public String toString() {
		return "User"+id;
	}

}
