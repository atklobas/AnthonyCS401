package recomender;
import java.io.IOException;
import java.util.Arrays;

import recomender.csv.CSV;
import recomender.csv.CSVLoader;
import recomender.graphs.Artist;
import recomender.graphs.User;
import recomender.graphs.UserGraph;

public class Recomender {
	private UserGraph graph=new UserGraph();
	
	public Recomender(String dataDir) throws IOException {
		CSVLoader loader=new CSVLoader(dataDir);
		CSV artistCSV=loader.loadResource("artists.dat");
		CSV userArtists=loader.loadResource("user_artists.dat");
		CSV userFriends=loader.loadResource("user_friends.dat");
		
		//populate artist list
		addArtists(artistCSV);
		//populate user list (do for both files in case a user has friends but not artist or likes an artist but has no friends)
		addUsers(userArtists);
		addUsers(userFriends);
		
		addFriends(userFriends);
		
		addArtistWeights(userArtists);
	}
	public void listFriends(int userID) {
		User user=graph.getUser(userID);
		System.out.printf("Friends of %s:\n",user.toString());
		for(User u:user.getFriends()) {
			System.out.println(u);
		}
	}
	
	public void commonFriends(int user1, int user2) {
		User[] common= graph.getCommonFriends(user1, user2);
		System.out.printf("Friends of both %s and %s:\n",graph.getUser(user1).toString(),graph.getUser(user2).toString());
		for(User u:common) {
			System.out.println(u);
		}
		
	}
	public void	listArtists(int user1, int user2){
		Artist[] common= graph.getCommonArtists(user1, user2);
		System.out.printf("Artists liked by both %s and %s:\n",graph.getUser(user1).toString(),graph.getUser(user2).toString());
		for(Artist a:common) {
			System.out.println(a);
		}
	}
	public void listTop10() {
		Artist[] common= graph.getTop10();
		System.out.printf("Top 10 listened to artists:\n");
		for(Artist a:common) {
			System.out.println(a);
		}
		
	}
	public void recommend10(int user) {
		Artist[] common= graph.getTop10(user);
		System.out.printf("Top 10 artists listened to by friends:\n");
		for(Artist a:common) {
			System.out.println(a);
		}
	}
	
	
	
	
	
	
	
	
	
	private void addArtists(CSV artistCSV) {
		for(String[] tuple:artistCSV) {
			int id=Integer.parseInt(tuple[0]);
			if(!graph.hasArtist(id)) {
				graph.addArtist( new Artist(id,tuple[1],tuple[2],tuple[3]));
			}
		}
	}
	private void addUsers(CSV data) {
		for(String[] tuple:data) {
			int id=Integer.parseInt(tuple[0]);
			if(!graph.hasUser(id)) {
				graph.addUser(new User(id));
			}
		}
	}
	private void addFriends(CSV userFriends) {
		for(String[] tuple:userFriends) {			
			int id=Integer.parseInt(tuple[0]);
			int fid=Integer.parseInt(tuple[1]);
			graph.addFriendship(id, fid);
			
		}
	}
	private void addArtistWeights(CSV userArtists) {
		for(String[] tuple:userArtists) {
			int uid=Integer.parseInt(tuple[0]);
			int aid=Integer.parseInt(tuple[1]);
			int weight=Integer.parseInt(tuple[2]);
			graph.addFan(aid, uid, weight);
		}
	}
}
