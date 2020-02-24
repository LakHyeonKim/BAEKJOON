import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
/**
 * 
 * @author LakHyeon Kim
 * @since 2020-01-04
 * @category bfs search _ 백준 1600 _ 말이 되고픈 원숭이
 * 
 * 1. bfs() :: 원숭이가 이동하는 최소 동작수 (이동 못 할 경우 -1 리턴, 이동가능 할 경우 동작 횟 수 리턴)
 * 2. 풀이 :: Queue에 들어가는 Point pair클래스 안에는 좌표, 해당지점 이동level == 동작수, k 말처럼 이동하는 횟 수 제한,
 * 			처음에는 단순하게 총 12방향의 이전 방향에 따라 갈 수 있는 경우를 visited를 확인하면서 했음 그러나 오답,
 * 			생각해보니 이전 방향에 따라 갈 수 있는 방향은 항상 똑같음 12방향 다가봐야함 항상.. 파이프 문제랑 다름,
 * 			근데 원숭이가 말처럼 갈 수 있는 방법이 제한이 있는데 그럼 말처럼 갈 때랑 그냥 인접방향으로 갈때랑 visited 구분 어떻게 할까 고민,
 * 			그래서 말처럼 갈 수 있는 방법 만큼 visited로 다 구분함 이러면 각 말처럼 이동 한 횟 수가 다르면서 위치가 겹치도 구분해서 갈 수 있음,
 * 			그리고 동작수가 그냥 최단거리 찾는 문제인데.. 처음 문제 잘 못 읽고 가중치가 1이 아닌 다익스트라 인줄 알고 PriorityQueue 썼음 
 */
public class Main {
	static class Point{
		int i;
		int j;
		int level;
		int k;
		Point(int i, int j, int level, int k){
			this.i = i;
			this.j = j;
			this.level = level;
			this.k = k;
		}
	}
	private static int K,N,M;
	private static int[][] map;
	private static String[] line;
	private static boolean[][][] visited;
	private static int[][] dir = {{-2,1}, {-1,2}, {1,2}, {2,1}, {2,-1}, {1,-2}, {-1,-2}, {-2,-1},
								  {0,1}, {1,0}, {0,-1}, {-1,0}};
	
	private static int bfs() {
		int result = -1;
		Queue<Point> q = new LinkedList<>();
		visited[0][0][0] = true;
		q.offer(new Point(0, 0, 0, 0));
		while(!q.isEmpty()) {
			Point cur = q.poll();
			if(cur.i == N-1 && cur.j == M-1) {
				result = cur.level;
				break;
			}
			if(cur.k < K) {
				for(int i = 0; i <= 7; i++) {
					int nextI = cur.i + dir[i][0];
					int nextJ = cur.j + dir[i][1];
					if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M) continue;
					if(map[nextI][nextJ] == 1) continue;
					if(!visited[cur.k+1][nextI][nextJ]) {
						visited[cur.k+1][nextI][nextJ] = true;
						q.offer(new Point(nextI, nextJ, cur.level+1, cur.k+1));
					}
				}
			}
			for(int i = 8; i < 12; i++) {
				int nextI = cur.i + dir[i][0];
				int nextJ = cur.j + dir[i][1];
				if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M) continue;
				if(map[nextI][nextJ] == 1) continue;
				if(!visited[cur.k][nextI][nextJ]) {
					visited[cur.k][nextI][nextJ] = true;
					q.offer(new Point(nextI, nextJ, cur.level+1, cur.k));
				}
			}
			
		}
		
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		line = br.readLine().split(" ");
		M = Integer.parseInt(line[0]);
		N = Integer.parseInt(line[1]);
		map = new int[N][M];
		visited = new boolean[31][N][M];
		for(int i = 0; i < N; i++) {
			line = br.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}
		System.out.println(bfs());
	}

}
