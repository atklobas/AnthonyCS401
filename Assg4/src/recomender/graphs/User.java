package recomender.graphs;

import java.util.ArrayList;

public class User {
	//these are package protected on purpose
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
	//if users had names we'd use that instead
	public String toString() {
		return "User "+id;
	}

	public User[] getFriends() {
		return friends.toArray(new User[0]);
	}

}
