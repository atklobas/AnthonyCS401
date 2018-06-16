package recomender.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class UserGraph {
	HashMap<Integer,User> users=new HashMap<Integer,User>();
	HashMap<Integer,Artist> artists=new HashMap<Integer,Artist>();
	
	public UserGraph() {

	}
	public boolean hasUser(int uid) {
		return users.containsKey(uid);
	}
	public void addUser(User user) {
		if(!users.containsKey(user.id)) {
			users.put(user.id, user);
		}
	}
	public boolean hasArtist(int aid) {
		return artists.containsKey(aid);
	}
	public void addArtist(Artist artist) {
		if(!artists.containsKey(artist.id)) {
			artists.put(artist.id, artist);
		}
	}
	public void addFriendship(int u1, int u2) {
		User usr=users.get(u1);
		User friend=users.get(u2);
		if(usr!=null &&friend!=null) {
			usr.addFriend(friend);
		}else {
			System.err.printf("user(%d) or friend(%d) doesn't exist\n",u1,u2);
		}
	}
	public void addFan(int aid, int uid, int weight) {
		User usr=users.get(uid);
		Artist artist=artists.get(aid);
		if(usr!=null &&artists!=null) {
			usr.addArtist(artist,weight);
			artist.addFan(usr);
		}else {
			System.err.printf("user(%d) or friend(%d) doesn't exist\n",uid,aid);
		}
	}
	private Artist[] getTop10(List<User> users){
		Artist[] top=new Artist[10];
		HashMap<Artist, Integer> rank=new HashMap<Artist,Integer>();
		for(User u:users) {
			for(int i=0;i<u.artists.size();i++) {
				Artist a=u.artists.get(i);
				Integer weight=rank.getOrDefault(a, 0);
				rank.put(a, weight+u.artistsWeights.get(i));
			}
		}
		ArrayList<Artist>ranking=new ArrayList<Artist>();
		ranking.addAll(rank.keySet());
		ranking.sort((a1,a2)->rank.get(a2)-rank.get(a1));
		for(int i=0;i<10;i++) {
			top[i]=ranking.get(i);
		}
		return top;
		
	}
	public Artist[] getTop10() {
		ArrayList<User> users=new ArrayList<User>();
		users.addAll(this.users.values());
		return getTop10(users);
	}
	public Artist[] getTop10(int userID) {
		User user=users.get(userID);
		ArrayList<User> users=new ArrayList<User>();
		users.addAll(user.friends);
		users.add(user);
		return getTop10(users);
	}
	public User[] getFriends(int user) {
		return users.get(user).friends.toArray(new User[0]);
	}
	
	
	public User[] getCommonFriends(int user1, int user2) {
		HashSet<User> set=new HashSet<User>();
		set.addAll(users.get(user1).friends);
		set.retainAll(users.get(user2).friends);
		return set.toArray(new User[0]);
		
	}
	
	public Artist[] getCommonArtists(int user1, int user2) {
		HashSet<Artist> set=new HashSet<Artist>();
		set.addAll(users.get(user1).artists);
		set.retainAll(users.get(user2).artists);
		return set.toArray(new Artist[0]);
		
	}
	public User getUser(int userID) {
		return users.get(userID);
	}
	public Artist getArtist(int artistID) {
		return artists.get(artistID);
	}
	
	

}
