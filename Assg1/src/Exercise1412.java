import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

import org.junit.Test;


public class Exercise1412 {
	
	public static int[] getIntersection(int[] a, int[] b){
		ArrayList<Integer>inter=new ArrayList<Integer>();
		int ia=0,ib=0;
		while((ia<a.length)&&(ib<b.length)){
			if(a[ia]==b[ib]){
				if(inter.isEmpty()||inter.get(inter.size()-1)!=a[ia])
					inter.add(a[ia]);
				ia++;
				ib++;
			}else if(a[ia]<b[ib]){
				ia++;
			}else{
				ib++;
			}
		}
		int[] toReturn=new int[inter.size()];
		for(int i=0;i<inter.size();i++){
			toReturn[i]=inter.get(i);
		}
		return toReturn;
	}
	
	
	
	Random rand=new Random();
	@Test
	public void BasicTest() {
		int[] a={1,2,3,4,5,6,7,8,9};
		int[] b={6,7,8,9,10,11,12,13};
		int[] x=getIntersection(a, b);
		
		assertTrue(Arrays.equals(x, new int[]{6,7,8,9}));
	}
	
	@Test
	public void emptyTest1() {
		int[] a={1,2,3,4,5,6,7,8,9};
		int[] b={};
		int[] x=getIntersection(a, b);
		assertTrue(Arrays.equals(x, new int[]{}));
	}
	@Test
	public void emptyTest2() {
		int[] a={1,2,3,4,5,6,7,8,9};
		int[] b={};
		int[] x=getIntersection(a, b);
		assertTrue(Arrays.equals(x, new int[]{}));
	}
	@Test
	public void EmptyIntersection() {
		int[] a={1,3,5,7,9};
		int[] b={2,4,6,8,10};
		int[] x=getIntersection(a, b);
		assertTrue(Arrays.equals(x, new int[]{}));
	}
	
	@Test
	public void knownFailure() {
		int[] a={0, 1, 1, 1, 1, 1, 3, 3, 3, 3, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6};
		int[] b={ 0, 2, 2, 3, 4, 4, 6, 6, 6};
		int[] x=getIntersection(a, b);
		assertTrue(Arrays.equals(x, new int[]{0, 3, 4, 6}));
	}
	
	
	private void fillList(ArrayList<Integer> list,int count, int bound){
		for(int i=0;i<count;i++){
			list.add(rand.nextInt(bound));
		}
		list.sort((a,b)->Integer.compare(a, b));
	}
	private int[] toIntArray(ArrayList<Integer> list){
		int[] array=new int[list.size()];
		for(int i=0;i<list.size();i++){
			array[i]=list.get(i);
		}
		return array;
	}
	@Test
	public void randomTest(){

		
		for(int i=0;i<1000;i++){
			ArrayList<Integer> alist=new ArrayList<Integer>();
			ArrayList<Integer> blist=new ArrayList<Integer>();
			int max=rand.nextInt(1000)+1;
			fillList(alist,rand.nextInt(1000),max);
			fillList(blist,rand.nextInt(1000),max);
			
			HashSet<Integer> set=new HashSet<Integer>();
			set.addAll(alist);
			set.addAll(blist);
			set.retainAll(alist);
			set.retainAll(blist);
			ArrayList<Integer> intersection=new ArrayList<Integer>();
			intersection.addAll(set);
			intersection.sort((a,b)->Integer.compare(a, b));
			int[] valid=toIntArray(intersection);
			int[] test=getIntersection(toIntArray(alist), toIntArray(blist));
			assertTrue(Arrays.equals(valid, test));
		}
	}

	
	

}
