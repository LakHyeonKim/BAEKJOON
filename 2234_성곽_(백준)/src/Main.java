import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Result{
		int roomCount;
		int maxArea;
		int removeOneMaxArea;
		Result(int roomCount, int maxArea, int removeOneMaxArea){
			this.roomCount = roomCount;
			this.maxArea = maxArea;
			this.removeOneMaxArea = removeOneMaxArea;
		}
		@Override
		public String toString() {
			return roomCount + "\n" + maxArea + "\n" + removeOneMaxArea;
		}
	}
	static class Point{
		int i;
		int j;
		Point(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
	private static int N,M;
	private static int[][] map;
	private static int[][] numbering;
	private static boolean[][] visited;
	private static Map<Integer,Integer> hashMap;
	private static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
	private static String[] castleInfo = {"0000",
			"0001",
			"0010",
			"0011",
			"0100",
			"0101",
			"0110",
			"0111",
			"1000",
			"1001",
			"1010",
			"1011",
			"1100",
			"1101",
			"1110",
			"1111"
	};
	
	private static Result numberOfRooms() {
		visited = new boolean[N][M];
		Queue<Point> q = new LinkedList<>();
		int roomCount = 0;
		int maxArea = 0;
		int removeOneMaxArea = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(visited[i][j]) continue;
				
				q.offer(new Point(i, j));
				int cnt = 0;
				while(!q.isEmpty()) {
					Point cur = q.poll();
					cnt++;
					numbering[cur.i][cur.j] = roomCount;
					visited[cur.i][cur.j] = true;
					for(int k = 0; k < 4; k++) {
						int nextI = cur.i + dir[k][0];
						int nextJ = cur.j + dir[k][1];
						if(castleInfo[map[cur.i][cur.j]].charAt(k) == '0' && !visited[nextI][nextJ]) {
							visited[nextI][nextJ] = true;
							q.offer(new Point(nextI, nextJ));
						}
					}
				}
				maxArea = Math.max(maxArea, cnt);
				hashMap.put(roomCount++, cnt);
			}
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				for(int k = 0; k < 4; k++) {
					int nextI = i + dir[k][0];
					int nextJ = j + dir[k][1];
					if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M) continue;
					if(numbering[i][j] == numbering[nextI][nextJ]) {
						removeOneMaxArea = Math.max(removeOneMaxArea, hashMap.get(numbering[i][j]));
					}else {
						removeOneMaxArea = Math.max(removeOneMaxArea, hashMap.get(numbering[i][j]) + hashMap.get(numbering[nextI][nextJ]));
					}
				}
			}
		}
		return new Result(roomCount, maxArea, removeOneMaxArea);
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		numbering = new int[N][M];
		hashMap = new HashMap<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.print(numberOfRooms());
	}
}
