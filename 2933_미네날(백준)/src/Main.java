import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
	
	static class Point{
		int i;
		int j;
		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + "]";
		}
		Point(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
	
	private static int R,C,cnt,totalCount;
	private static char[][] map;
	private static boolean[][] visited;
	private static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
	private static int[] high;
	private static String temp;
	private static List<Point> cluster;
	
	private static boolean edge(int nextJ) {
		if(nextJ < 0 || nextJ >= C) return false;
		else return true;
	}
	
	private static void doRod(int high, int dir) {
		int idx = R - high;
		if(dir == 0) { // 왼쪽부터 던질 때
			int j = 0;
			while(edge(j) && map[idx][j] != 'x') {
				j++;
			}
			if(edge(j)) {
				totalCount--;
				map[idx][j] = '.';
			}
		}else { // 오른쪽 부터 던질 때
			int j = C-1;
			while(edge(j) && map[idx][j] != 'x') {
				j--;
			}
			if(edge(j)) {
				totalCount--;
				map[idx][j] = '.';
			}
		}
	}
	
	private static boolean bfs(Point start) {
		Queue<Point> q = new LinkedList<>();
		q.offer(start);
		int searchX = 1;
		while(!q.isEmpty()) {
			Point cur = q.poll();
			visited[cur.i][cur.j] = true;
			for(int k = 0; k < 4; k++) {
				int nextI = cur.i + dir[k][0];
				int nextJ = cur.j + dir[k][1];
				if(nextI < 0 || nextI >= R+1 || nextJ < 0 || nextJ >= C) continue;
				if(visited[nextI][nextJ]) continue;
				if(map[nextI][nextJ] == 'x') {
					searchX++;
					visited[nextI][nextJ] = true;
					q.offer(new Point(nextI, nextJ));
				}
			}
		}
		if(searchX == totalCount) return true; // 떠있는 클러스터 없고
		else return false; // 떠있는 클러스터 있음
	}
	
	private static void searchCluster() {
		for(int i = R-1; i >= 0; i--) {
			for(int j = 0; j < C; j++) {
				if(!visited[i][j] && map[i][j] == 'x') {
					cluster.add(new Point(i, j));
				}
			}
		}
	}
	
	private static void moveCluster() {
		boolean flag = false;
		while(true) {
			for(int i = 0 ; i < cluster.size(); i++) {
				cluster.get(i).i++;
				if(cluster.get(i).i == R || (map[cluster.get(i).i][cluster.get(i).j] == 'x' && visited[cluster.get(i).i][cluster.get(i).j])) {
					flag = true;
				}
			}
			if(flag) {
				for(int i = 0 ; i < cluster.size(); i++) {
					cluster.get(i).i--;
				}
				break;
			}else {
				for(int i = 0 ; i < cluster.size(); i++) {
					map[--cluster.get(i).i][cluster.get(i).j] = '.';
				}
				for(int i = 0 ; i < cluster.size(); i++) {
					map[++cluster.get(i).i][cluster.get(i).j] = 'x';
				}
			}
		}
	}
	
	private static void play() {
		for(int i = 0; i < cnt; i++) {
			doRod(high[i], i%2);
			visited = new boolean[R+1][C];
			if(!bfs(new Point(R, 0))) {
				searchCluster();
				moveCluster();
				cluster.clear();
			}
		}
	}
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		R = Integer.parseInt(line[0]);
		C = Integer.parseInt(line[1]);
		map = new char[R+1][C];
		cluster = new ArrayList<>();
		for(int i = 0; i < R+1; i++) {
			if(i == R) {
				for(int j = 0; j < C; j++) {
					totalCount++;
					map[i][j] = 'x';
				}
			}
			else {
				temp = br.readLine();
				for(int j = 0; j < C; j++) {
					map[i][j] = temp.charAt(j);
					if(map[i][j] == 'x') totalCount++;
				}
			}
		}
		cnt = Integer.parseInt(br.readLine());
		high = new int[cnt];
		line = br.readLine().split(" ");
		for(int i = 0; i < cnt; i++) {
			high[i] = Integer.parseInt(line[i]);
		}
		play();
		print();
	}
	
	// print
	private static void print() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}