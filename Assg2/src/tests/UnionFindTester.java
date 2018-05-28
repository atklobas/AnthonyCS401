package tests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import analyser.UnionFind;

class UnionFindTester {

	@Test
	void test1() {
		UnionFind f=new UnionFind(10);
		assert(f.getCount()==10);
	}
	@Test
	void test2() {
		UnionFind f=new UnionFind(10);
		f.union(0, 1);
		assert(f.getCount()==9);
	}
	@Test
	void test3() {
		UnionFind f=new UnionFind(10);
		f.union(0, 1);
		f.union(0, 2);
		f.union(0, 3);
		f.union(0, 4);
		f.union(0, 5);
		f.union(0, 6);
		f.union(0, 7);
		f.union(0, 8);
		f.union(0, 9);
		assert(f.getCount()==1);
	}
	@Test
	void test4() {
		UnionFind f=new UnionFind(10);
		f.union(0, 1);
		f.union(0, 2);
		f.union(0, 3);
		f.union(0, 4);
		f.union(5, 6);
		f.union(5, 7);
		f.union(5, 8);
		f.union(5, 9);
		assert(f.getCount()==2);
	}
	@Test
	void test5() {
		UnionFind f=new UnionFind(10);
		f.union(0, 1);
		f.union(0, 2);
		f.union(0, 3);
		f.union(0, 4);
		f.union(5, 6);
		f.union(5, 7);
		f.union(5, 8);
		f.union(5, 9);
		f.union(9,2);
		assert(f.getCount()==1);
		assert(f.connected(1, 9));
	}
	@Test
	void test6() {
		UnionFind f=new UnionFind(10);
		assert(f.getCount()==10);
	}

}
