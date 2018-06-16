package recomender.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class UserGraph {
	//a map from userID -> user instance, because userID's don't appear to always be sequential
	HashMap<Integer,User> users=new HashMap<Integer,User>();
	//a map of artistID -> artist instance
	HashMap<Integer,Artist> artists=new HashMap<Integer,Artist>();
	
	/**
	 * returns if a  user with this id exists
	 * @param uid
	 * @return
	 */
	public boolean hasUser(int uid) {
		return users.containsKey(uid);
	}
	/**
	 * adds a user to the graph
	 * @param user
	 */
	public void addUser(User user) {
		if(!users.containsKey(user.id)) {
			users.put(user.id, user);
		}
	}
	/**
	 * returns if an artist with this id exists
	 * @param aid
	 * @return
	 */
	public boolean hasArtist(int aid) {
		return artists.containsKey(aid);
	}
	/**
	 * adds and artist to the graph
	 * @param artist
	 */
	public void addArtist(Artist artist) {
		if(!artists.containsKey(artist.id)) {
			artists.put(artist.id, artist);
		}
	}
	/**
	 * conneccts adds user 2 as a friend of user1
	 * (note this means that users are a directed graph, you can have a friend that doesn't think of you as a friend)
	 * @param u1
	 * @param u2
	 */
	public void addFriendship(int u1, int u2) {
		User usr=users.get(u1);
		User friend=users.get(u2);
		if(usr!=null &&friend!=null) {
			usr.addFriend(friend);
		}else {
			System.err.printf("user(%d) or friend(%d) doesn't exist\n",u1,u2);
		}
	}
	/**
	 * specifies a user likes an artists with a weight
	 * @param aid artist id
	 * @param uid user id
	 * @param weight the number of times listened to
	 */
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
	/**
	 * gets top 10 artists by listen count from a list of users
	 * this does all the work for top10() and top10(userid)
	 * @param users
	 * @return
	 */
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
	/**
	 * returns the top 10 artists in order by listen count
	 * @return
	 */
	public Artist[] getTop10() {
		ArrayList<User> users=new ArrayList<User>();
		users.addAll(this.users.values());
		return getTop10(users);
	}
	/**
	 * returns the top 10 artists liked by a user and thier friends
	 * ordered by listen count
	 * @param userID
	 * @return
	 */
	public Artist[] getTop10(int userID) {
		User user=users.get(userID);
		ArrayList<User> users=new ArrayList<User>();
		users.addAll(user.friends);
		users.add(user);
		return getTop10(users);
	}
	/**
	 * returns useres that are a friend of the the user specified by id
	 * @param user
	 * @return
	 */
	public User[] getFriends(int user) {
		return users.get(user).friends.toArray(new User[0]);
	}
	
	/**
	 * gets the intersection of friends between the 2 specified users
	 * @param user1
	 * @param user2
	 * @return
	 */
	public User[] getCommonFriends(int user1, int user2) {
		HashSet<User> set=new HashSet<User>();
		set.addAll(users.get(user1).friends);
		set.retainAll(users.get(user2).friends);
		return set.toArray(new User[0]);
		
	}
	/**
	 * gets the artists liked by both specified users
	 * @param user1
	 * @param user2
	 * @return
	 */
	public Artist[] getCommonArtists(int user1, int user2) {
		HashSet<Artist> set=new HashSet<Artist>();
		set.addAll(users.get(user1).artists);
		set.retainAll(users.get(user2).artists);
		return set.toArray(new Artist[0]);
		
	}
	/**
	 * gets the user by their id
	 * @param userID
	 * @return
	 */
	public User getUser(int userID) {
		return users.get(userID);
	}
	/**
	 * gets the artist by their id
	 * @param artistID
	 * @return
	 */
	public Artist getArtist(int artistID) {
		return artists.get(artistID);
	}
	
	

}
