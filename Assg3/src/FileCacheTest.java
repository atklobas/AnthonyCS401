import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.IOException;
import java.util.Random;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import edu.bellevuecollege.anthony.cache.Cache;
import edu.bellevuecollege.anthony.cache.TextFileLoader;

class FileCacheTest {

	Random rand=new Random();
	@Test
	void loadSingleFile() throws IOException {
		Cache<String,String> cache=new Cache<String,String>(3,new TextFileLoader());
		String test=cache.get("file1.txt");
		assertTrue(test.equals("file 1\n"));
		assertTrue(cache.getMisses()==1);
	}
	
	@Test
	void testThrows() throws IOException {
		Cache<String,String> cache=new Cache<String,String>(3,new TextFileLoader());
		assertThrows(IOException.class,()->cache.get(""));
		
	}
	
	
	@Test
	void testNonCache() throws IOException {
		Cache<String,String> cache=new Cache<String,String>(0,new TextFileLoader());
		String test=cache.get("file1.txt");
		cache.get("file1.txt");
		assertTrue(test.equals("file 1\n"));
		assertTrue(cache.getMisses()==2);
	}
	
	@Test
	void cacheSingleFile() throws IOException {
		Cache<String,String> cache=new Cache<String,String>(3,new TextFileLoader());
		String test=cache.get("file1.txt");
		test=cache.get("file1.txt");
		test=cache.get("file1.txt");
		test=cache.get("file1.txt");
		test=cache.get("file1.txt");
		test=cache.get("file1.txt");
		assertTrue(test.equals("file 1\n"));
		assertTrue(cache.getMisses()==1);
		assertTrue(cache.getHits()==5);
	}
	
	@Test
	void load2Files() throws IOException{
		Cache<String,String> cache=new Cache<String,String>(3,new TextFileLoader());
		String test=cache.get("file1.txt");
		assertTrue(test.equals("file 1\n"));
		test=cache.get("file2.txt");
		assertTrue(test.equals("file 2\n"));
		assertTrue(cache.getMisses()==2);	
	}
	
	@Test
	void alternateFiles() throws IOException{
		Cache<String,String> cache=new Cache<String,String>(3,new TextFileLoader());
		String test;
		for(int i=0;i<10;i++) {
			test=cache.get("file1.txt");
			assertTrue(test.equals("file 1\n"));
			test=cache.get("file2.txt");
			assertTrue(test.equals("file 2\n"));

		}
		assertTrue(cache.getMisses()==2);	
	}
	
	void overFlowCache() throws IOException{
		Cache<String,String> cache=new Cache<String,String>(5,new TextFileLoader());
		for(int i=0;i<100;i++) {
			int fileNumber=i%5;
			String test=cache.get("file"+fileNumber+".txt");
			assertTrue(test.equals("file "+fileNumber+"\n"));
		}
	}
	
	void removeFromMiddle() throws IOException{
		
		Cache<String,String> cache=new Cache<String,String>(5,new TextFileLoader());
		for(int i=0;i<10;i++) {
			int fileNumber=i%5;
			String test=cache.get("file"+fileNumber+".txt");
			assertTrue(test.equals("file "+fileNumber+"\n"));
		}
		
		String test2=cache.get("file2.txt");
		assertTrue(test2.equals("file 2\n"));
		for(int i=0;i<10;i++) {
			int fileNumber=i%5;
			String test=cache.get("file"+fileNumber+".txt");
			assertTrue(test.equals("file "+fileNumber+"\n"));
		}
	}
	
	
	@Test
	void randomCached()throws IOException{
		Cache<String,String> cache=new Cache<String,String>(8,new TextFileLoader());
		for(int i=0;i<100;i++) {
			int fileNumber=rand.nextInt(8);
			String test=cache.get("file"+fileNumber+".txt");
			boolean isCorrectFile=test.equals("file "+fileNumber+"\n");
			assertTrue(isCorrectFile);
			assertTrue(test.equals("file "+fileNumber+"\n"));
		}
		assertTrue(cache.getMisses()==8);
	}
	
	@RepeatedTest(10)
	void random()throws IOException{
		Cache<String,String> cache=new Cache<String,String>(0,new TextFileLoader());
		for(int i=0;i<100;i++) {
			int fileNumber=rand.nextInt(8);
			String test=cache.get("file"+fileNumber+".txt");
			boolean isCorrectFile=test.equals("file "+fileNumber+"\n");
			assertTrue(isCorrectFile);
			assertTrue(test.equals("file "+fileNumber+"\n"));
		}
		assertTrue(cache.getHits()+cache.getMisses()==100);
	}
	
	
	
	@Test
	void findbug() throws IOException{
		Cache<String,String> cache=new Cache<String,String>(5,new TextFileLoader());
		cache.get("file"+0+".txt");
		cache.get("file"+2+".txt");
		cache.get("file"+7+".txt");
		cache.get("file"+2+".txt");
	}
	
	@Test
	void testEmptyMissRatio() {
		Cache<String,String> cache=new Cache<String,String>(3,new TextFileLoader());
		assertTrue(Double.isNaN(cache.getMissRatio()));
	}
	@Test
	void testEmptyHitRatio() {
		Cache<String,String> cache=new Cache<String,String>(3,new TextFileLoader());
		assertTrue(Double.isNaN(cache.getHitRatio()));
	}
	
	
	@Test
	void testpartiallyFilledMissRatio() throws IOException {
		
		Cache<String,String> cache=new Cache<String,String>(30,new TextFileLoader());
		cache.get("file"+0+".txt");
		cache.get("file"+2+".txt");
		cache.get("file"+7+".txt");
		cache.get("file"+2+".txt");
		assertTrue(cache.getMissRatio()==3/4.);
		cache.get("file"+0+".txt");
		cache.get("file"+2+".txt");
		cache.get("file"+7+".txt");
		cache.get("file"+2+".txt");
		assertTrue(cache.getMissRatio()==3/8.);
	}
	@Test
	void testpartiallyFilledHitRatio() throws IOException {
		Cache<String,String> cache=new Cache<String,String>(30,new TextFileLoader());
		cache.get("file"+0+".txt");
		cache.get("file"+2+".txt");
		cache.get("file"+7+".txt");
		cache.get("file"+2+".txt");
		assertTrue(cache.getHitRatio()==1/4.);
		cache.get("file"+0+".txt");
		cache.get("file"+2+".txt");
		cache.get("file"+7+".txt");
		cache.get("file"+2+".txt");
		assertTrue(cache.getHitRatio()==5/8.);
	}
	@Test
	void testFilledMissRatio() throws IOException{
		
		Cache<String,String> cache=new Cache<String,String>(3,new TextFileLoader());
		cache.get("file"+0+".txt");
		cache.get("file"+2+".txt");
		cache.get("file"+7+".txt");
		cache.get("file"+2+".txt");
		assertTrue(cache.getMissRatio()==3/4.);
	}
	
	@Test
	void testFilledHitRatio() throws IOException{
		Cache<String,String> cache=new Cache<String,String>(3,new TextFileLoader());
		cache.get("file"+0+".txt");
		cache.get("file"+2+".txt");
		cache.get("file"+7+".txt");
		cache.get("file"+2+".txt");
		assertTrue(cache.getHitRatio()==1/4.);
	}
	
	@Test
	void testHitMissRatio() throws IOException {
		Cache<String,String> cache=new Cache<String,String>(3,new TextFileLoader());
		cache.get("file"+0+".txt");
		cache.get("file"+2+".txt");
		cache.get("file"+7+".txt");
		cache.get("file"+2+".txt");
		assertTrue(cache.getHitRatio()==1/4.);
		assertTrue(cache.getMissRatio()==3/4.);
		cache.get("file"+0+".txt");
		cache.get("file"+2+".txt");
		cache.get("file"+7+".txt");
		cache.get("file"+2+".txt");
		assertTrue(cache.getHitRatio()==5/8.);
		assertTrue(cache.getMissRatio()==3/8.);
		cache.get("file"+1+".txt");
		cache.get("file"+3+".txt");
		cache.get("file"+4+".txt");
		assertTrue(cache.getHitRatio()==5/11.);
		assertTrue(cache.getMissRatio()==6/11.);
		cache.get("file"+0+".txt");
		cache.get("file"+2+".txt");
		cache.get("file"+7+".txt");
		cache.get("file"+2+".txt");
		assertTrue(cache.getHitRatio()==6/15.);
		assertTrue(cache.getMissRatio()==9/15.);
		
	}
	
	@Test
	void testHitMissRatioZero() throws IOException {
		Cache<String,String> cache=new Cache<String,String>(3,new TextFileLoader());
		
		assertTrue(Double.isNaN(cache.getHitRatio()));
		assertTrue(Double.isNaN(cache.getMissRatio()));
		
	}
	@Test
	void testContains() throws IOException {
		Cache<String,String> cache=new Cache<String,String>(3,new TextFileLoader());
		cache.get("file"+0+".txt");
		cache.get("file"+1+".txt");
		cache.get("file"+2+".txt");
		assertAll("testing keyes", 
			()->assertTrue(cache.containsKey("file"+0+".txt")),
			()->assertTrue(cache.containsKey("file"+1+".txt")),
			()->assertTrue(cache.containsKey("file"+2+".txt")),
			()->assertFalse(cache.containsKey("file"+3+".txt")),
			()->assertFalse(cache.containsKey("file"+4+".txt")),
			()->assertFalse(cache.containsKey("file"+5+".txt"))
		);
		
		
		
		assertTrue(cache.containsValue("file "+0+"\n"));
		assertTrue(cache.containsValue("file "+1+"\n"));
		assertTrue(cache.containsValue("file "+2+"\n"));
		assertFalse(cache.containsValue("file "+3+"\n"));
		assertFalse(cache.containsValue("file "+4+"\n"));
		assertFalse(cache.containsValue("file "+5+"\n"));
		
	}
	
	
	
	
	
	
}
