package analyser;

import java.util.Arrays;

public class SoilSample {

	private int[][] drainage;
	private String name;
	
	public SoilSample(String name,String drainage) {
		this.name=name;
		try {
			this.drainage=makeArray(drainage);
		}catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid soil sample");
		}
	}
	private int[][] makeArray(String input){
		String[] rows=input.split("\n");
		int[][] made=new int[rows.length][];
		for(int i=0;i<rows.length;i++) {
			made[i]=Arrays.stream(rows[i].split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
		}
		return made;
	}
	
	public boolean canDrain() {
		int width=drainage[0].length;
		int height=drainage.length;
		int total=width*height+2;
		UnionFind uf=new UnionFind(total);
		
		for(int y=0;y<height;y++) {
			for(int x=0;x<width;x++) {
				if(drainage[y][x]==0) {
					continue;
				}
				if(y==0) {
					uf.union(x,total-2);
				}
				if(y==height-1) {
					uf.union(total-1, y*width+x);
				}
				if(x<width-1 && drainage[y][x+1]==1) {
					uf.union(y*width+x+1, y*width+x);
				}
				if(y<height-1&& drainage[y+1][x]==1) {
					uf.union(y*width+x, (y+1)*width+x);
				}
			}
			
		}
		
		return uf.connected(total-1, total-2);
	}
	public String getName() {
		return name;
	}
}
