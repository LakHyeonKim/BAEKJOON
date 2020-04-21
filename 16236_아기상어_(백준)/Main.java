import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static class Shark{
		int i;
		int j;
		int count;
		int curSize;
		Shark(int i, int j, int count, int curSize) {
			this.i = i;
			this.j = j;
			this.count = count;
			this.curSize = curSize;
		}
	}
	static class Fish implements Comparable<Fish>{
		int i;
		int j;
		int d;
		int count;
		
		Fish(int i, int j, int d, int count) {
			this.i = i;
			this.j = j;
			this.d = d;
			this.count = count;
		}
		
		Fish(int i, int j, int d){
			this.i = i;
			this.j = j;
			this.d = d;
		}
		@Override
		public int compareTo(Fish o) {
			if(this.d > o.d) return 1;
			else if(this.d == o.d) {
				if(this.i > o.i) {
					return 1;
				}else if(this.i == o.i) {
					if(this.j > o.j) {
						return 1;
					}
				}
			}
			return -1;
		}
	}
	private static int N, time;
	private static int[][] map;
	private static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
	private static Shark start;
	
	private static List<Fish> bfs(Shark shark) {
		List<Fish> result = new ArrayList<>();
		Queue<Fish> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		q.offer(new Fish(shark.i, shark.j, 0));
		visited[shark.i][shark.j] = true;
		while(!q.isEmpty()) {
			Fish cur = q.poll();
			for(int k = 0; k < 4; k++) {
				int nextI = cur.i + dir[k][0];
				int nextJ = cur.j + dir[k][1];
				if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= N) continue;
				if(!visited[nextI][nextJ] && (map[nextI][nextJ] == shark.curSize || map[nextI][nextJ] == 0)) {
					visited[nextI][nextJ] = true;
					q.offer(new Fish(nextI, nextJ, cur.d + 1, 0));
				}
				if(cur.count < 1) {
					if(!visited[nextI][nextJ] && (map[nextI][nextJ] > 0 && map[nextI][nextJ] < shark.curSize)) {
						visited[nextI][nextJ] = true;
						q.offer(new Fish(nextI, nextJ, cur.d + 1, 1));
						result.add(new Fish(nextI, nextJ, cur.d + 1));
					}
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 9) {
					map[i][j] = 0;
					start = new Shark(i, j, 0, 2);
				}
			}
		}
		sc.close();
		while(true) {
			List<Fish> isGo = bfs(start);
			Collections.sort(isGo);
			if(isGo.size() == 0) break;
			Fish cur = isGo.get(0);
			map[cur.i][cur.j] = 0;
			start.i = cur.i;
			start.j = cur.j;
			time += cur.d;
			start.count++;
			if(start.count == start.curSize) {
				start.curSize++;
				start.count = 0;
			}
		}
		System.out.println(time);
	}
}