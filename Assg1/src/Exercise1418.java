import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.Test;


public class Exercise1418 {
	
	public static int getLocalMinimum(int[] a){
		int lower=0;
		int upper=a.length-1;
		while(upper-lower>2){
			int middle=(lower+upper)/2;
			if(a[middle-1]<a[middle]){
				upper=middle;
			}else{
				lower=middle;
			}
		}
		int min=a[lower];
		if(a[upper]<min)
			min=a[upper];
		if(a[(lower+upper)/2]<min){
			min=a[(lower+upper)/2];
		}
		return min;
	}
	
	

	Random rand=new Random();
	
	
	@Test
	public void testGiven1() {
		int[] test={5, -4, 10, 16, 11, 20, 24, -10};
		List<Integer> answers=Arrays.asList(-4,11,-10);
		
		assertNotEquals(answers.indexOf(getLocalMinimum(test)),-1);
	}
	@Test
	public void testGiven2() {
		int[] test={-8, -6, 18, 8, 20, 4, 40};
		List<Integer> answers=Arrays.asList(-6,8,4);
		
		assertNotEquals(answers.indexOf(getLocalMinimum(test)),-1);
	}
	@Test
	public void testSize1() {
		int[] test={1};
		assertEquals(getLocalMinimum(test),1);
	}
	
	
	@Test
	public void testSize2L() {
		int[] test={1,2};
		assertEquals(getLocalMinimum(test),1);
	}
	@Test
	public void testSize2R() {
		int[] test={2,1};
		assertEquals(getLocalMinimum(test),1);
	}
	
	@Test
	public void testSize3R() {
		int[] test={3,2,1};
		assertEquals(getLocalMinimum(test),1);
	}
	@Test
	public void testSize3L() {
		int[] test={1,2,3};
		assertEquals(getLocalMinimum(test),1);
	}
	@Test
	public void testSize3M() {
		int[] test={2,1,3};
		assertEquals(getLocalMinimum(test),1);
	}
	@Test
	public void testAsc() {
		int[] test={1,2,3,4,5,6,7,8,9};
		assertEquals(getLocalMinimum(test),1);
	}
	@Test
	public void testDesc() {
		int[] test={9,8,7,6,5,4,3,2,1};
		assertEquals(getLocalMinimum(test),1);
	}
	@Test
	public void testMid() {
		int[] test={9,7,5,4,3,1,2,4,6,8,10};
		assertEquals(getLocalMinimum(test),1);
	}
	
	@Test
	public void testRandom() {
		for(int trial=0;trial<100;trial++){
			int[] test=new int[rand.nextInt(1000)+1];
			ArrayList<Integer> testList=new ArrayList<Integer>();
			for(int i=0;i<test.length;i++){
				testList.add(i);
			}
			Collections.shuffle(testList);
			for(int i=0;i<test.length;i++){
				test[i]=testList.get(i);
			}
			
			int min=getLocalMinimum(test);
			int index=testList.indexOf(min);
			boolean minimum=true;
			if(index>0){
				//System.out.print(test[index-1]+" > ");
				minimum&=min<test[index-1];
			}
			//System.out.print(test[index]);
			if(index<test.length-1){
				//System.out.print(" < "+test[index+1]);
				minimum&=min<test[index+1];
			}
			//System.out.println();
			assertTrue(minimum);
		}
	}
	
	
	@Test
	public void testRandomNegative() {
		for(int trial=0;trial<100;trial++){
			int[] test=new int[rand.nextInt(1000)+1];
			ArrayList<Integer> testList=new ArrayList<Integer>();
			for(int i=0;i<test.length;i++){
				testList.add(i-test.length/2);
			}
			Collections.shuffle(testList);
			for(int i=0;i<test.length;i++){
				test[i]=testList.get(i);
			}
			
			int min=getLocalMinimum(test);
			int index=testList.indexOf(min);
			boolean minimum=true;
			if(index>0){
				//System.out.print(test[index-1]+" > ");
				minimum&=min<test[index-1];
			}
			//System.out.print(test[index]);
			if(index<test.length-1){
				//System.out.print(" < "+test[index+1]);
				minimum&=min<test[index+1];
			}
			//System.out.println();
			assertTrue(minimum);
		}
	}
	
	
	
	
	

}
