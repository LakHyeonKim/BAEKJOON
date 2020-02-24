import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static class Point{
		int i;
		int j;
		int preDir;
		Point(int i, int j, int preDir){
			this.i = i;
			this.j = j;
			this.preDir = preDir;
		}
		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + ", preDir=" + preDir + "]";
		}
		
	}
	private static int N, result;
	private static int[][] map;
	private static int[][] dir = {{0,1},{1,0},{1,1}};
	
	private static void bfs() {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(0, 1, 0));
		while(!q.isEmpty()) {
			Point cur = q.poll();
			if(cur.i == N-1 && cur.j == N-1) {
				result++;
				continue;
			}
			for(int k = 0; k < 3; k++) {
				int nextI = cur.i + dir[k][0];
				int nextJ = cur.j + dir[k][1];
				if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= N) continue;
				if(map[nextI][nextJ] == 1) continue;
				if(cur.preDir == 0 && k == 1) continue;
				if(cur.preDir == 1 && k == 0) continue;
				if(k == 2) {
					if(map[cur.i + dir[0][0]][cur.j + dir[0][1]] == 1 || map[cur.i + dir[1][0]][cur.j + dir[1][1]] == 1)
						continue;
				}
				q.offer(new Point(nextI, nextJ, k));
			}
		}
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		for(int i = 0 ; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		bfs();
		System.out.println(result);
	}
}
