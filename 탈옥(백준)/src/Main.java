import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static class Point{
		int i;
		int j;
		int door;
		Point(int i, int j, int door){
			this.i = i;
			this.j = j;
			this.door = door;
		}
	}
	private static int testCase, N, M, menCnt, min = Integer.MAX_VALUE;
	private static char[][] map;
	private static int[][] cntMap;
	private static String[] line;
	private static String mapLine;
	private static Point men1, men2;
	
	
	private static void bfs(Point start) {
		Deque<Point> q = new LinkedList<>();
		boolean[][] check = new boolean[N+2][M+2];
		int[][] direction = {{1,0},{-1,0},{0,1},{0,-1}};
		
		q.offer(start);
		while(!q.isEmpty()) {
			Point cur = q.poll();
			check[cur.i][cur.j] = true;
			cntMap[cur.i][cur.j] += cur.door;
			for(int k = 0; k < 4; k++) {
				int nextI = cur.i + direction[k][0];
				int nextJ = cur.j + direction[k][1];
				if(nextI < 0 || nextI >= N+2 || nextJ < 0 || nextJ >= M+2) continue;
				if(check[nextI][nextJ]) continue;
				if(map[nextI][nextJ] == '*') continue;
				if(map[nextI][nextJ] == '#') {
					check[nextI][nextJ] = true;
					q.offer(new Point(nextI, nextJ, cur.door+1));
				}else {
					check[nextI][nextJ] = true;
					q.addFirst(new Point(nextI, nextJ, cur.door));
				}
			}
		}
	}
	
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		testCase = Integer.parseInt(br.readLine());
		for(int count = 0; count < testCase; count++) {
			line = br.readLine().split(" ");
			N = Integer.parseInt(line[0]);
			M = Integer.parseInt(line[1]);
			map = new char[N+2][M+2];
			cntMap = new int[N+2][M+2];
			menCnt = 2;
			for(int i = 1; i <= N; i++) {
				mapLine = br.readLine();
				for(int j = 1; j <= M; j++) {
					map[i][j] = mapLine.charAt(j-1);
					if(map[i][j] == '$') {
						if(menCnt == 2) {
							men1 = new Point(i, j, 0);
							menCnt--;
						}else {
							men2 = new Point(i, j, 0);
						}
					}
				}
			}
			bfs(new Point(0, 0, 0));
			bfs(new Point(men1.i, men1.j, 0));
			bfs(new Point(men2.i, men2.j, 0));
			//System.out.println();
			for(int i = 0; i < N+2; i++) {
				for(int j = 0; j < M+2; j++) {
					if(map[i][j] == '#') cntMap[i][j] -= 2;
					//System.out.print(cntMap[i][j] + "\t");
					if(map[i][j] == '*') continue;
					if(min > cntMap[i][j]) min = cntMap[i][j];
				}
				//System.out.println();
			}
			System.out.println(min);
			min = Integer.MAX_VALUE;
		}
	}

}
