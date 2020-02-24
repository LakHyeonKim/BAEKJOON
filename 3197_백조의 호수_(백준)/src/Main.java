import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
	static class Point implements Comparable<Point>{
		int i;
		int j;
		int level;
		Point(int i, int j, int level){
			this.i = i;
			this.j = j;
			this.level = level;
		}
		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + ", level=" + level + "]";
		}
		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.level, o.level);
		}

	}
	private static char[][] map;
	private static int[][] dayMap;
	private static boolean flag;
	private static boolean[][][] visited;
	private static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
	private static int N, M, max;
	private static String[] line;
	private static String mapInfo;
	private static Point swanStart, swanEnd; 
	
	private static void iceBfs(Queue<Point> q) {
		while(!q.isEmpty()) {
			Point cur = q.poll();
			map[cur.i][cur.j] = '.';
			dayMap[cur.i][cur.j] = cur.level;
			for(int k = 0; k < 4; k++) {
				int nextI = cur.i + dir[k][0];
				int nextJ = cur.j + dir[k][1];
				if(edge(nextI, nextJ)) {
					if(map[nextI][nextJ] == 'X' && !visited[0][nextI][nextJ]) {
						visited[0][nextI][nextJ] = true;
						q.offer(new Point(nextI, nextJ, cur.level + 1));
					}
				}
			}
		}
	}
	
	private static void removeIce() {
		Queue<Point> q = new LinkedList<>();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 'X') {
					int cnt = 0;
					for(int k = 0; k < 4; k++) {
						int nextI = i + dir[k][0];
						int nextJ = j + dir[k][1];
						if(edge(nextI, nextJ) && (map[nextI][nextJ] == '.' || map[nextI][nextJ] == 'L')) {
							cnt++;
						}
					}
					if(cnt > 0) {
						visited[0][i][j] = true;
						q.offer(new Point(i, j, 1));
					}
				}
			}
		}
		iceBfs(q);
	}

	private static int swanBfs() {
		removeIce();
		int result = 0;
		PriorityQueue<Point> q = new PriorityQueue<>();
		q.offer(new Point(swanStart.i, swanStart.j, dayMap[swanStart.i][swanStart.j]));
		visited[1][swanStart.i][swanStart.j] = true;
		outer:while(!q.isEmpty()) {
			Point cur = q.poll();
			max = Math.max(max, cur.level);
			for(int k = 0; k < 4; k++) {
				int nextI = cur.i + dir[k][0];
				int nextJ = cur.j + dir[k][1];
				if(edge(nextI, nextJ)) {
					if(map[nextI][nextJ] == 'L' && !visited[1][nextI][nextJ]) {
						result = max;
						break outer;
					}
					if(map[nextI][nextJ] == '.'&& !visited[1][nextI][nextJ]) {
						visited[1][nextI][nextJ] = true;
						q.offer(new Point(nextI, nextJ, dayMap[nextI][nextJ]));
					}
					
				}
			}
		}
		return result;
	}

	private static boolean edge(int nextI, int nextJ) {
		if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M) return false;
		else return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		map = new char[N][M];
		dayMap = new int[N][M];
		visited = new boolean[2][N][M];

		for(int i = 0; i < N; i++) {
			mapInfo = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = mapInfo.charAt(j);
				if(!flag && map[i][j] == 'L') {
					swanStart = new Point(i, j, 0);
					flag = true;
				}else if(flag && map[i][j] == 'L') {
					swanEnd = new Point(i, j, 0);
				}
			}
		}
		
		System.out.println(swanBfs());
	}

}
