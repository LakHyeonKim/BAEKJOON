import java.util.*;
/**
 * 
 * @author LakHyeon Kim
 * @since 2020-01-04
 * @category bfs search _ 백준 1194 _ 달이 차오른다 가자
 * 
 * 1. toNumber() 함수 :: 경우의 수를 10진수로 표현해줌
 * 2. bfs() :: 탈출하려는 최소 이동 수 (이동 못 할 경우 -1 리턴, 이동가능 할 경우 이동 횟 수 리턴)
 * 3. 풀이 :: a, b, c, d, e, f 총 6가지의 키를 각각 가지고 이동 할 때 와 가지지 않고 이동 할 때 경우의 수가 다름 따라서,
 * 			방문체크를 단지 6개의 키의 종류 만큼만 한다면 만약 a키를 가지고 다른 키를 가질 때와 가지지 않을 때 방문체크를 할 수 없다 그러므로,
 * 			6개의 키를 가지고, 가지지 않을 때의 총 경우의 수 2^6 = 64 가지의 서로다른 키를 가지고 갈 때의 방문체크를 다 해주어야 함,
 * 			문이 있을 때는 그냥 키랑 같은지만 확인하여 갈 수 있는지 확인해주면 되고 핵심은 6가지의 키를 가지고 이동한다는 점에 초점을 맞추어 풀었음 
 */
class Main {
	static class Point{
		int i;
		int j;
		int level;
		boolean[] keys;
		Point(int i, int j, int level, boolean[] keys){
			this.i = i;
			this.j = j;
			this.level = level;
			this.keys = keys;
		}
	}

	private static int N,M;
	private static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
	private static char[][] map;
	private static boolean[][][] visited;

	private static Point start;
	
	private static int toNumber(boolean[] keys) {
		int result = 0;
		for(int i = 5; i >= 0; i--) {
			if(keys[i]) {
				result += Math.pow(2, 5-i) * 1;
			}else {
				result += Math.pow(2, 5-i) * 0;
			}
		}
		return result;
	}

	private static int bfs(){
		int result = -1;
		Queue<Point> q = new LinkedList<>();
		q.offer(start);
		outer:while(!q.isEmpty()){
			Point cur = q.poll();
			for(int k = 0; k < 4; k++){
				int nextI = cur.i + dir[k][0];
				int nextJ = cur.j + dir[k][1];
				if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M) continue;
				if(map[nextI][nextJ] == '#') continue;
				if(map[nextI][nextJ] == '1') {
					result = cur.level + 1;
					break outer;
				}
				boolean[] tempKeys = new boolean[6];
				for(int i = 0; i < 6; i++) {
					tempKeys[i] = cur.keys[i];
				}
				if((map[nextI][nextJ] == '.' || map[nextI][nextJ] == '0') && !visited[toNumber(tempKeys)][nextI][nextJ]) {
					visited[toNumber(cur.keys)][nextI][nextJ] = true;
					q.offer(new Point(nextI, nextJ, cur.level + 1, tempKeys));
				}
				if(map[nextI][nextJ] >= 'a' && map[nextI][nextJ] <= 'f') {
					tempKeys[map[nextI][nextJ] - 'a'] = true;
					int number = toNumber(tempKeys);
					if(visited[number][nextI][nextJ]) continue;
					visited[number][nextI][nextJ] = true;
					q.offer(new Point(nextI, nextJ, cur.level + 1, tempKeys));
				}
				if(map[nextI][nextJ] >= 'A' && map[nextI][nextJ] <= 'F') {
					int number = toNumber(tempKeys);
					if(tempKeys[map[nextI][nextJ] - 'A'] && !visited[number][nextI][nextJ]) {
						visited[number][nextI][nextJ] = true;
						q.offer(new Point(nextI, nextJ, cur.level + 1, tempKeys));
					}
				}
			}
		}
		return result;
	}


	public static void main (String[] args) throws java.lang.Exception {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new char[N][M];
		visited = new boolean[64][N][M];

		for(int i = 0; i < N; i++){
			char[] temp = sc.next().toCharArray();
			for(int j = 0; j < M; j++){
				map[i][j] = temp[j];
				if(map[i][j] == '0'){
					start = new Point(i,j,0,new boolean[6]);
				}
			}
		}
		
		System.out.println(bfs());
	}
}