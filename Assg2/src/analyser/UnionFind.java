package analyser;

public class UnionFind {
	
	private int[] data;
	private int[] size;
	private int count;
	
	public UnionFind(int count) {
		this.count=count;
		this.data=new int[count];
		this.size=new int[count];
		for(int i=0;i<count;i++) {
			data[i]=i;
			size[i]=1;
		}
	}
	public int find(int item) {
		if(data[item]!=item) {
			data[item]=find(data[item]);
		}
		return data[item];
	}
	public boolean connected(int a, int b) {
		return find(a)==find(b);
	}
	public int getCount() {
		return count;
	}
	public void union(int a,int b) {
		a=find(a);
		b=find(b);
		if(a!=b) {
			count--;
			if(size[a]>size[b]) {
				data[b]=a;
				size[a]+=size[b];
			}else {
				data[a]=b;
				size[b]+=size[a];
			}
		}	
	}
}
