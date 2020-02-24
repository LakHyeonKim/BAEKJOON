import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Main {
	static class Point{
		int i;
		int j;
		int level;
		boolean[] stuff;
		Point(int i, int j, int level, boolean[] stuff){
			this.i = i;
			this.j = j;
			this.level = level;
			this.stuff = stuff;
		}
		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + ", level=" + level + ", stuff=" + stuff + "]";
		}

	}
	private static int N,M,stuffCount;
	private static int[][] dir = {{0,1},{0,-1},{-1,0},{1,0}};
	private static String[] line;
	private static char[] mapLine;
	private static char[][] map;
	private static boolean[][][] visited;
	private static Point start;
	private static Map<String,Integer> stuffPosition;
	
	private static int toNumber(boolean[] keys) {
		int result = 0;
		for(int i = stuffCount-1; i >= 0; i--) {
			if(keys[i]) {
				result += Math.pow(2, stuffCount-1-i) * 1;
			}else {
				result += Math.pow(2, stuffCount-1-i) * 0;
			}
		}
		return result;
	}

	private static int bfs() {
		int result = 0;
		Queue<Point> q = new LinkedList<>();
		q.offer(start);
		visited[0][start.i][start.j] = true;
		outer:while(!q.isEmpty()) {
			Point cur = q.poll();
			//System.out.println(cur);
			//System.out.println(toNumber(cur.stuff));
			for(int k = 0; k < 4; k++) {
				int nextI = cur.i + dir[k][0];
				int nextJ = cur.j + dir[k][1];
				if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M) continue;
				if(map[nextI][nextJ] == '#') continue;
				if(map[nextI][nextJ] == 'E' && toNumber(cur.stuff) == Math.pow(2, stuffCount)-1) {
					result = cur.level+1;
					break outer;
				}
				boolean[] tempKeys = new boolean[5];
				for(int i = 0; i < stuffCount; i++) {
					tempKeys[i] = cur.stuff[i];
				}
				if(map[nextI][nextJ] == 'X') {
					tempKeys[stuffPosition.get(nextI+""+nextJ+"")] = true;
					if(!visited[toNumber(tempKeys)][nextI][nextJ]) {
						visited[toNumber(tempKeys)][nextI][nextJ] = true;
						q.offer(new Point(nextI, nextJ, cur.level + 1, tempKeys));
					}
				}else if(!visited[toNumber(tempKeys)][nextI][nextJ]){
					visited[toNumber(tempKeys)][nextI][nextJ] = true;
					q.offer(new Point(nextI, nextJ, cur.level + 1, tempKeys));
				}
			}
		}
		return result;
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		line = br.readLine().split(" ");
		M = Integer.parseInt(line[0]);
		N = Integer.parseInt(line[1]);
		map = new char[N][M];
		
		stuffPosition = new HashMap<String, Integer>();
		int p = 0;
		for(int i = 0; i < N; i++) {
			mapLine = br.readLine().toCharArray();
			for(int j = 0; j < M; j++) {
				map[i][j] = mapLine[j];
				if(map[i][j] == 'S') {
					start = new Point(i, j, 0, null);
				}
				if(map[i][j] == 'X') {
					stuffCount++;
					stuffPosition.put(i+""+j+"", p++);
				}
			}
		}
		start.stuff = new boolean[(int)Math.pow(2, stuffCount)];
		visited = new boolean[(int)Math.pow(2, stuffCount)][N][M];
		//System.out.println(stuffPosition);
		System.out.println(bfs());
	}

}
