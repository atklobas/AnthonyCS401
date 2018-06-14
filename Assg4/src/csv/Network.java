package csv;
import graphs.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Network {
	public static void main(String[] args) throws IOException {
		CSVLoader loader=new CSVLoader("dataset");
		
		CSV artistCSV=loader.loadResource("artists.dat");
		CSV userArtists=loader.loadResource("user_artists.dat");
		CSV userFriends=loader.loadResource("user_friends.dat");
		
		UserGraph graph=new UserGraph();
		
		for(String[] tuple:artistCSV) {
			int id=Integer.parseInt(tuple[0]);
			if(!graph.hasArtist(id)) {
				graph.addArtist( new Artist(id,tuple[1],tuple[2],tuple[3]));
			}
		}
		
		for(String[] tuple:userArtists) {
			int id=Integer.parseInt(tuple[0]);
			if(!graph.hasUser(id)) {
				graph.addUser(new User(id));
			}
		}
		for(String[] tuple:userFriends) {
			int id=Integer.parseInt(tuple[0]);
			if(!graph.hasUser(id)) {
				graph.addUser(new User(id));
			}
		}
		
		
		
		for(String[] tuple:userFriends) {			
			int id=Integer.parseInt(tuple[0]);
			int fid=Integer.parseInt(tuple[1]);
			graph.addFriendship(id, fid);
			
		}
		for(String[] tuple:userArtists) {
			int uid=Integer.parseInt(tuple[0]);
			int aid=Integer.parseInt(tuple[1]);
			int weight=Integer.parseInt(tuple[2]);
			graph.addFan(aid, uid, weight);
		}
		System.out.println(Arrays.toString(graph.getTop10(29)));
		System.out.println(Arrays.toString(graph.getFriends(29)));
		System.out.println(Arrays.toString(graph.getCommonArtists(2, 4)));
		System.out.println(Arrays.toString(graph.getCommonFriends(2, 275)));
	}
}
