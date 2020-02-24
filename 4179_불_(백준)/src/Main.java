import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static class Point{
		int i;
		int j;
		int level;
		Point(int i, int j, int level){
			this.i = i;
			this.j = j;
			this.level = level;
		}
	}

	private static int N,M;
	private static char[][] map;
	private static int[][] fire;
	private static boolean[][][] visited;
	private static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
	private static Point J;
	private static Queue<Point> fireQ;
	private static Queue<Point> jQ;

	private static void fireBfs() {
		while(!fireQ.isEmpty()) {
			Point cur = fireQ.poll();
			fire[cur.i][cur.j] = cur.level;
			for(int k = 0; k < 4; k++) {
				int nextI = cur.i + dir[k][0];
				int nextJ = cur.j + dir[k][1];
				if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M) continue;
				if(map[nextI][nextJ] == '#') continue;
				if((map[nextI][nextJ] == '.' || map[nextI][nextJ] == 'J') && !visited[0][nextI][nextJ]) {
					visited[0][nextI][nextJ] = true;
					fireQ.offer(new Point(nextI, nextJ, cur.level + 1));
				}
			}
		}
	}

	private static void jBfs() {
		fireBfs();
		int result = 0;
		outer:while(!jQ.isEmpty()) {
			Point cur = jQ.poll();
			for(int k = 0; k < 4; k++) {
				int nextI = cur.i + dir[k][0];
				int nextJ = cur.j + dir[k][1];
				if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M) {
					result = cur.level + 1;
					break outer;
				}
				if(map[nextI][nextJ] == '#') continue;
				if(!visited[1][nextI][nextJ] && map[nextI][nextJ] == '.') {
					if((fire[nextI][nextJ] == 0 || cur.level + 1 < fire[nextI][nextJ])) {
						visited[1][nextI][nextJ] = true;
						jQ.offer(new Point(nextI, nextJ, cur.level + 1));
					}
				}
			}
		}

		if(result == 0) System.out.println("IMPOSSIBLE");
		else System.out.println(result);
	}
	
	private static void print(int[][] arr) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				System.out.print(arr[i][j] + "\t");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		map = new char[N][M];
		fire = new int[N][M];
		visited = new boolean[2][N][M];
		fireQ = new LinkedList<>();
		jQ = new LinkedList<>();

		for(int i = 0; i < N; i++) {
			String temp = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = temp.charAt(j); 
				if(map[i][j] == 'J') {
					jQ.offer(new Point(i, j, 0));
					visited[1][i][j] = true;
				}
				if(map[i][j] == 'F') {
					fireQ.offer(new Point(i, j, 0));
					visited[0][i][j] = true;
				}
			}
		}
		jBfs();
	}
}
