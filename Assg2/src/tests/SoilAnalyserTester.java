package tests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import analyser.SoilSample;

class SoilAnalyserTester {

	@Test
	void test1() {
		String input =
		"1 0 1 0 1\n" + 
		"1 1 0 1 0\n" + 
		"0 1 1 0 1\n" + 
		"1 0 1 0 1\n" + 
		"1 0 1 1 1";
		assert(new SoilSample("test",input).canDrain());
	}
	@Test
	void test2() {
		String input =
		"1 0 0 1 1\n" + 
		"0 1 1 1 0\n" + 
		"1 0 0 0 1\n" + 
		"1 0 0 0 1\n" + 
		"1 1 0 1 1";
		assert(!new SoilSample("test",input).canDrain());
	}
	@Test
	void test3() {
		String input =
		"1 0 0 1 1\n" + 
		"0 1 1 1 0\n" + 
		"1 0 1 1 1\n" + 
		"0 0 0 1 1\n" + 
		"1 1 0 0 0";
		assert(!new SoilSample("test",input).canDrain());
	}
	@Test
	void test4() {
		String input =
		"1 0 0 1 1 1 1 0 0 0\n" +
		"0 1 1 1 0 0 0 1 1 0\n" +
		"1 0 0 1 1 0 0 1 0 0\n" +
		"1 0 0 0 1 1 1 0 0 0\n" +
		"1 1 0 1 1 1 1 0 0 0\n" +
		"1 0 1 1 0 1 1 1 1 0\n" +
		"0 0 0 0 1 1 0 0 0 0\n" +
		"1 0 1 1 1 1 1 0 0 0\n" +
		"0 1 0 1 1 0 1 0 1 0\n" +
		"1 1 0 1 0 1 1 0 0 0";
		assert(new SoilSample("test",input).canDrain());
	}
	
	@Test
	void testVerticalZigZag(){
		String input =
		"1 0 0 0 0 0 0 0 0 0 0\n" +
		"1 0 1 1 1 1 1 0 1 1 1\n" +
		"1 0 1 0 1 0 1 0 1 0 1\n" +
		"1 0 1 0 1 0 1 0 1 0 1\n" +
		"1 0 1 0 1 0 1 0 1 0 1\n" +
		"1 0 1 0 1 0 1 0 1 0 1\n" +
		"1 0 1 0 1 0 1 0 1 0 1\n" +
		"1 0 1 0 1 0 1 0 1 0 1\n" +
		"1 1 1 1 1 0 1 1 1 0 1\n" +
		"0 0 0 0 0 0 0 0 0 0 1";
		assert(new SoilSample("test",input).canDrain());
	}
	@Test
	void testNonConnectedZigZag() {
		String input =
		"1 0 0 0 0 0 0 0 0 0\n" +
		"1 0 1 1 1 1 1 0 1 0\n" +
		"1 0 1 0 1 0 1 0 1 0\n" +
		"1 0 1 0 1 0 1 0 1 0\n" +
		"1 0 1 0 1 0 1 0 1 0\n" +
		"1 0 1 0 1 0 1 0 1 0\n" +
		"1 0 1 0 1 0 1 0 1 0\n" +
		"1 0 1 0 1 0 1 0 1 0\n" +
		"1 1 1 1 1 0 1 0 1 1\n" +
		"0 0 0 0 0 0 0 0 0 1";
		assert(!new SoilSample("test",input).canDrain());
	}
	@Test
	void testTall() {
		String input =
		"0 0 0 0 0 1\n"+
		"1 1 1 1 1 1\n" +
		"1 0 0 0 0 0\n" +
		"1 1 1 1 1 1\n" +
		"0 0 0 0 0 1\n" +
		"1 1 1 1 1 1\n" +
		"1 0 0 0 0 0\n" +
		"1 1 1 1 1 1\n" +
		"0 0 0 0 0 1\n" +
		"1 1 1 1 1 1\n" +
		"1 0 0 0 0 0\n" +
		"1 1 1 1 1 1\n" +
		"0 0 0 0 0 1\n" +
		"1 1 1 1 1 1\n" +
		"1 0 0 0 0 0\n" +
		"1 1 1 1 1 1\n" +
		"0 0 0 0 0 1\n" +
		"1 1 1 1 1 1\n" +
		"1 0 0 0 0 0\n" +
		"1 1 1 1 1 1\n";
		assert(new SoilSample("test",input).canDrain());
	}
	@Test
	void testThin() {
		String input =
		"1\n" +
		"1\n" +
		"1\n" +
		"1\n" +
		"1\n" +
		"1\n" +
		"1\n" +
		"1\n" +
		"1\n" +
		"1";
		assert(new SoilSample("test",input).canDrain());
	}
	@Test
	void testUnitSize() {
		String input =
		"1";
		assert(new SoilSample("test",input).canDrain());
	}
	@Test
	void testEmpty() {
		String input =
		"";
		try {
			assert(!new SoilSample("test",input).canDrain());
		}catch(IllegalArgumentException E) {	
			return;
		}
		fail("This sample was supposed to be invalid");
		
	}
	
	

}
