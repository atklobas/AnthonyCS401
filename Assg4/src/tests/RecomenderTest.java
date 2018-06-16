package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.nio.channels.Pipe;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class RecomenderTest {
	static recomender.Recomender rec;
	static PipedInputStream pipe;
	static BufferedReader output;
	@BeforeAll
	static void init() {
		try {
			rec=new recomender.Recomender("dataset");
			PipedOutputStream pipeOut = new PipedOutputStream();
			pipe = new PipedInputStream(pipeOut);
			output=new BufferedReader(new InputStreamReader(pipe));
			System.setOut(new PrintStream(pipeOut));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	// 4 random users, known results generated from "select friendID from user_friends where userID=2";
	@Test
	void testListFriends2() throws IOException {
		int[] friends= {275,428,515,761,831,909,1209,1210,1230,1327,1585,1625,1869};
		rec.listFriends(2);
		int[] ids=new int[friends.length];
		//ignore heading
		String head=output.readLine();
		int i=0;
		while(output.ready()) {
			String line=output.readLine();
			int id=Integer.parseInt(line.substring(5));
			ids[i++]=id;
		}
		Arrays.sort(ids);
		assertArrayEquals(friends,ids);
		
	}
	@Test
	void testListFriends29() throws IOException {
		int[] friends= {946};
		rec.listFriends(29);
		int[] ids=new int[friends.length];
		//ignore heading
		String head=output.readLine();
		int i=0;
		while(output.ready()) {
			String line=output.readLine();
			int id=Integer.parseInt(line.substring(5));
			ids[i++]=id;
		}
		Arrays.sort(ids);
		assertArrayEquals(friends,ids);
	}
	@Test
	void testListFriends500()  throws IOException {
		int[] friends= {440,782,1204,1801,1888,1975};
		rec.listFriends(500);
		int[] ids=new int[friends.length];
		//ignore heading
		String head=output.readLine();
		int i=0;
		while(output.ready()) {
			String line=output.readLine();
			int id=Integer.parseInt(line.substring(5));
			ids[i++]=id;
		}
		Arrays.sort(ids);
		assertArrayEquals(friends,ids);
	}
	@Test
	void testListFriends2100()  throws IOException {
		int[] friends= {586,607};
		rec.listFriends(2100);
		int[] ids=new int[friends.length];
		//ignore heading
		String head=output.readLine();
		int i=0;
		while(output.ready()) {
			String line=output.readLine();
			int id=Integer.parseInt(line.substring(5));
			ids[i++]=id;
		}
		Arrays.sort(ids);
		assertArrayEquals(friends,ids);
	}
	
	//test from "select name from user_artist join artist on id=artistID where userID in (2, 29) group by name having count(*)>=2 ;"
	@Test 
	void testListArtist29_500() throws IOException{
		String[] artists= {};
		rec.listArtists(29, 500);
		String[] results=new String[artists.length];
		//ignore heading
		String head=output.readLine();
		int i=0;
		while(output.ready()) {
			String line=output.readLine();
			results[i++]=line;
		}
		if(results.length>1) {
			Arrays.sort(results);
		}
		assertArrayEquals(results,artists);
	}
	@Test 
	void testListArtis2_4() throws IOException{
		String[] artists= {"Air","Coldplay","Depeche Mode","Duran Duran","George Michael","Moby","Röyksopp"};
		rec.listArtists(2, 4);
		String[] results=new String[artists.length];
		//ignore heading
		String head=output.readLine();
		int i=0;
		while(output.ready()) {
			String line=output.readLine();
			results[i++]=line;
		}
		if(results.length>1) {
			Arrays.sort(results);
		}
		assertArrayEquals(results,artists);
	}
	//from select friendID from user_friends where userID in (2,275) group by friendID having count(*)>=2
	@Test
	void testFriendIntersection2_275()  throws IOException {
		int[] friends= {831};
		rec.commonFriends(2, 275);
		int[] ids=new int[friends.length];
		//ignore heading
		String head=output.readLine();
		int i=0;
		while(output.ready()) {
			String line=output.readLine();
			int id=Integer.parseInt(line.substring(5));
			ids[i++]=id;
		}
		Arrays.sort(ids);
		assertArrayEquals(friends,ids);
	}

	@Test
	void testFriendIntersection3_78()  throws IOException {
		int[] friends= {255,837,1740,1801};
		rec.commonFriends(3, 78);
		int[] ids=new int[friends.length];
		//ignore heading
		String head=output.readLine();
		int i=0;
		while(output.ready()) {
			String line=output.readLine();
			int id=Integer.parseInt(line.substring(5));
			ids[i++]=id;
		}
		Arrays.sort(ids);
		assertArrayEquals(friends,ids);
	}
	//select name from user_artist join artist on artistID=id group by name order by sum(weight)desc limit 10;
	@Test
	void testTop10() throws IOException{
		String[] artists= {"Britney Spears","Depeche Mode","Lady Gaga","Christina Aguilera","Paramore","Madonna","Rihanna","Shakira","The Beatles","Katy Perry"};
		rec.listTop10();
		String[] results=new String[artists.length];
		//ignore heading
		String head=output.readLine();
		int i=0;
		while(output.ready()) {
			String line=output.readLine();
			results[i++]=line;
		}
		
		assertArrayEquals(results,artists);
	}
	
	//select name from user_artist join artist on artistID=id where userID=2 or userID in (select friendID from user_friends where userID=2) group by name order by sum(weight)desc limit 10;
	@Test
	void testRecomend10_2() throws IOException{
		String[] artists= {"Duran Duran","Depeche Mode","Madonna","Panic! At the Disco","Rammstein","U2","The Cure","Pet Shop Boys","Simple Minds","Kylie Minogue"};
		rec.recommend10(2);
		String[] results=new String[artists.length];
		//ignore heading
		String head=output.readLine();
		int i=0;
		while(output.ready()) {
			String line=output.readLine();
			results[i++]=line;
		}
		
		assertArrayEquals(results,artists);
	}
	@Test
	void testRecomend10_29() throws IOException{
		String[] artists= {"All Time Low","Fresno","Metro Station","DOYOULIKE?","Forever the Sickest Kids","Boys Like Girls","You Me At Six","A Rocket to the Moon","Green Day","Beeshop"};
		rec.recommend10(29);
		String[] results=new String[artists.length];
		//ignore heading
		String head=output.readLine();
		int i=0;
		while(output.ready()) {
			String line=output.readLine();
			results[i++]=line;
		}
		
		assertArrayEquals(results,artists);
	}
	@Test
	void testRecomend10_2100() throws IOException{
		String[] artists= {"Burzum","Behemoth","Immortal","Nahash","Marduk","Last Days of Humanity","Obtest","Amžius","Yann Tiersen","Gallhammer"};
		rec.recommend10(2100);
		String[] results=new String[artists.length];
		//ignore heading
		String head=output.readLine();
		int i=0;
		while(output.ready()) {
			String line=output.readLine();
			results[i++]=line;
		}
		
		assertArrayEquals(results,artists);
	}
	@Test
	void testFailedRecomend(){
		assertThrows(Exception.class,()->rec.recommend10(1));
	}
	@Test
	void testFailedCommonFriends(){
		assertThrows(Exception.class,()->rec.commonFriends(1, 2));
	}
	@Test
	void testFailedListFriends(){
		assertThrows(Exception.class,()->rec.listFriends(1));
	}
	@Test
	void testFailedListArtists(){
		assertThrows(Exception.class,()->rec.listArtists(1,2));
	}
	@Test
	void testFailedRecomend10(){
		assertThrows(Exception.class,()->rec.recommend10(1));
	}
	
	
	
	
}
