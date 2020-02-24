import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main {
	static class Point implements Comparable<Point>{
		int i;
		int j;
		
		Point(int i, int j){
			this.i = i;
			this.j = j;
		}
		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.j, o.j);
		}
	}
	private static int N,M,D,max;
	private static String[] line;
	private static int[][] map;
	private static int[][] tempMap;
	private static boolean[] visited;
	private static List<Point> list;
	private static List<Point> enemys;
	

	private static void combi(int index, int depth) {
		if(depth == 3) {
			for(int j = 0; j < M; j++) {
				if(visited[j]) {
					list.add(new Point(N, j));
				}
			}
			int killed = 0;
			while(true) {
				Collections.sort(enemys);
				for(int k = 0; k < 3; k++) {
					Point archer = list.get(k);
					Point target = null;
					int min = Integer.MAX_VALUE;
					for(Point e : enemys) {
						if(min > Math.abs(archer.i-e.i) + Math.abs(archer.j-e.j)){
							min = Math.abs(archer.i-e.i) + Math.abs(archer.j-e.j);
							target = new Point(e.i, e.j);
						}
					}
					if(D >= min) {
						if(map[target.i][target.j] == 1) {
							killed++;
						}
						map[target.i][target.j] = 0;
					}
				}
				enemys.clear();
				for(int i = 0; i < N; i++) {
					for(int j = 0 ; j < M; j++) {
						if(map[i][j] == 1) enemys.add(new Point(i, j));
					}
				}
				if(enemys.size() == 0) break;
				for(Point e:enemys) {
					map[e.i][e.j] = 0;
				}
				for(Point e:enemys) {
					if(e.i+1 == N) continue;
					map[++e.i][e.j] = 1;
					
				}
				enemys.clear();
				for(int i = 0; i < N; i++) {
					for(int j = 0 ; j < M; j++) {
						if(map[i][j] == 1) enemys.add(new Point(i, j));
					}
				}
			}
			max = Math.max(max, killed);
			list.clear();
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					map[i][j] = tempMap[i][j];
					if(map[i][j] == 1) enemys.add(new Point(i, j));
				}
			}
			return;
		}
		for(int i = index; i < M; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			combi(i, depth+1);
			visited[i] = false;
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		D = Integer.parseInt(line[2]);
		map = new int[N+1][M];
		tempMap = new int[N+1][M];
		visited = new boolean[M];
		list = new ArrayList<>();
		enemys = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			line = br.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(line[j]);
				tempMap[i][j] = map[i][j];
				if(map[i][j] == 1) {
					enemys.add(new Point(i, j));
				}
			}
		}
		combi(0, 0);
		System.out.println(max);
	}

}
