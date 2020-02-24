import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static class Point{
		int i;
		int j;
		int k;
		int level;
		Point(int i, int j, int k, int level){
			this.i = i;
			this.j = j;
			this.k = k;
			this.level = level;
		}
	}
	private static int L, R, C;
	private static String line;
	private static char[][][] map;
	private static boolean[][][] visited;
	private static int[][] dir = {{1,0,0},{-1,0,0},{0,1,0},{0,-1,0},{0,0,1},{0,0,-1}};
	private static Point start; 
	
	private static String bfs() {
		int result = 0;
		Queue<Point> q = new LinkedList<>();
		q.offer(start);
		outer:while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for(int k = 0; k < 6; k++) {
				int nextI = cur.i + dir[k][0];
				int nextJ = cur.j + dir[k][1];
				int nextK = cur.k + dir[k][2];
				if(nextI < 0 || nextI >= L || nextJ < 0 || nextJ >= R || nextK < 0 || nextK >= C) continue;
				if(map[nextI][nextJ][nextK] == '#') continue;
				if(map[nextI][nextJ][nextK] == 'E') {
					result = cur.level + 1;
					break outer;
				}
				if(map[nextI][nextJ][nextK] == '.' && !visited[nextI][nextJ][nextK]) {
					visited[nextI][nextJ][nextK] = true;
					q.offer(new Point(nextI, nextJ, nextK, cur.level + 1));
				}
			}
		}
		if(result == 0) return "Trapped!";
		else return "Escaped in " + result +  " minute(s).";
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			L = sc.nextInt(); R = sc.nextInt(); C = sc.nextInt();
			if(L == 0 && R == 0 && C == 0) break;
			map = new char[L][R][C];
			visited = new boolean[L][R][C];
			
			for(int i = 0; i < L; i++) {
				for(int j = 0; j < R; j++) {
					line = sc.next();
					for(int k = 0; k < C; k++) {
						map[i][j][k] = line.charAt(k);
						if(map[i][j][k] == 'S') start = new Point(i, j, k, 0);
					}
				}
			}
			System.out.println(bfs());
		}
	}
}
