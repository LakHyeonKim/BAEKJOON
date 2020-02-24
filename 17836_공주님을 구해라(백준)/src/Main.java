import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static class Point{
		int i;
		int j;
		int level;
		int isHased;
		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + ", level=" + level + ", isHased=" + isHased + "]";
		}
		Point(int i, int j, int level, int isHased){
			this.i = i;
			this.j = j;
			this.level = level;
			this.isHased = isHased;
		}
	}
	
	private static int N, M, T;
	private static int[][] map;
	private static boolean[][][] visited;
	private static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
	
	private static int bfs(Point start) {
		int result = -1;
		Queue<Point> q = new LinkedList<Main.Point>();
		q.offer(start);
		visited[start.isHased][start.i][start.j] = true;
		while(!q.isEmpty()) {
			Point cur = q.poll();
			//System.out.println(cur);
			if(cur.level > T) {
				break;
			}
			if(cur.i == N-1 && cur.j == M-1) {
				result = cur.level;
				break;
			} 
			for(int k = 0; k < 4; k++) {
				int nextI = cur.i + dir[k][0];
				int nextJ = cur.j + dir[k][1];
				if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M) continue;
				if(visited[cur.isHased][nextI][nextJ]) continue;
				if(map[nextI][nextJ] == 1 && cur.isHased == 1) {
					visited[1][nextI][nextJ] = true;
					q.offer(new Point(nextI, nextJ, cur.level+1, 1));
				}
				if(map[nextI][nextJ] == 2) {
					visited[1][nextI][nextJ] = true;
					q.offer(new Point(nextI, nextJ, cur.level+1, 1));
				}
				if(map[nextI][nextJ] == 0) {
					if(cur.isHased == 0) {
						visited[0][nextI][nextJ] = true;
						q.offer(new Point(nextI, nextJ, cur.level+1, 0));
					}else {
						visited[1][nextI][nextJ] = true;
						q.offer(new Point(nextI, nextJ, cur.level+1, 1));
					}
				}
			}
			
		}
		return result;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		T = sc.nextInt();
		map = new int[N][M];
		visited = new boolean[2][N][M];
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0 ; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		int result = bfs(new Point(0, 0, 0, 0));
		if(result == -1) System.out.println("Fail");
		else System.out.println(result);
//		for(int i = 0; i < N; i++) {
//			for(int j = 0 ; j < M; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
	}

}
