import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	static class Point implements Comparable<Point> {
		int i;
		int j;
		int preDir;
		int count;

		Point(int i, int j, int preDir, int count) {
			this.i = i;
			this.j = j;
			this.preDir = preDir;
			this.count = count;
		}

		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + ", preDir=" + preDir + ", count=" + count + "]";
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.count, o.count);
		}
	}

	private static int N, result;
	private static String line;
	private static char[][] map;
	private static boolean[][][] visited;
	private static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	private static Point start, end;
	private static boolean flag;

	private static boolean edge(int nextI, int nextJ) {
		if (nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= N)
			return false;
		else
			return true;
	}

	private static void bfs(Point start) {
		PriorityQueue<Point> q = new PriorityQueue<>();

		for (int i = 0; i < 4; i++) {
			visited[i][start.i][start.j] = true;
		}

		q.offer(start);

		while (!q.isEmpty()) {
			Point cur = q.poll();

			if (cur.i == end.i && cur.j == end.j) {
				result = cur.count;
				break;
			}

			if (cur.i == start.i && cur.j == start.j) {
				for (int i = 0; i < 4; i++) {
					int nextI = cur.i + dir[i][0];
					int nextJ = cur.j + dir[i][1];
					if (edge(nextI, nextJ) && !visited[i][nextI][nextJ] && map[nextI][nextJ] != '*') {
						visited[i][nextI][nextJ] = true;
						q.offer(new Point(nextI, nextJ, i, 0));
					}
				}
				continue;
			}

			int nextI, nextJ;
			switch (map[cur.i][cur.j]) {
			case '.':
				nextI = cur.i + dir[cur.preDir][0];
				nextJ = cur.j + dir[cur.preDir][1];
				if (edge(nextI, nextJ) && !visited[cur.preDir][nextI][nextJ] && map[nextI][nextJ] != '*') {
					visited[cur.preDir][nextI][nextJ] = true;
					q.offer(new Point(nextI, nextJ, cur.preDir, cur.count));
				}
				break;
			case '!':
				switch (cur.preDir) {
				case 0: case 2:
					nextI = cur.i + dir[3][0];
					nextJ = cur.j + dir[3][1];
					if (edge(nextI, nextJ) && !visited[3][nextI][nextJ] && map[nextI][nextJ] != '*') {
						visited[3][nextI][nextJ] = true;
						q.offer(new Point(nextI, nextJ, 3, cur.count + 1));
					}
					nextI = cur.i + dir[1][0];
					nextJ = cur.j + dir[1][1];
					if (edge(nextI, nextJ) && !visited[1][nextI][nextJ] && map[nextI][nextJ] != '*') {
						visited[1][nextI][nextJ] = true;
						q.offer(new Point(nextI, nextJ, 1, cur.count + 1));
					}
					nextI = cur.i + dir[cur.preDir][0];
					nextJ = cur.j + dir[cur.preDir][1];
					if (edge(nextI, nextJ) && !visited[cur.preDir][nextI][nextJ] && map[nextI][nextJ] != '*') {
						visited[cur.preDir][nextI][nextJ] = true;
						q.offer(new Point(nextI, nextJ, cur.preDir, cur.count));
					}
					break;
				case 1: case 3:
					nextI = cur.i + dir[2][0];
					nextJ = cur.j + dir[2][1];
					if (edge(nextI, nextJ) && !visited[2][nextI][nextJ] && map[nextI][nextJ] != '*') {
						visited[2][nextI][nextJ] = true;
						q.offer(new Point(nextI, nextJ, 2, cur.count + 1));
					}
					nextI = cur.i + dir[0][0];
					nextJ = cur.j + dir[0][1];
					if (edge(nextI, nextJ) && !visited[0][nextI][nextJ] && map[nextI][nextJ] != '*') {
						visited[0][nextI][nextJ] = true;
						q.offer(new Point(nextI, nextJ, 0, cur.count + 1));
					}
					nextI = cur.i + dir[cur.preDir][0];
					nextJ = cur.j + dir[cur.preDir][1];
					if (edge(nextI, nextJ) && !visited[cur.preDir][nextI][nextJ] && map[nextI][nextJ] != '*') {
						visited[cur.preDir][nextI][nextJ] = true;
						q.offer(new Point(nextI, nextJ, cur.preDir, cur.count));
					}
					break;
				}
				break;
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new char[N][N];
		visited = new boolean[4][N][N];
		for (int i = 0; i < N; i++) {
			line = sc.next();
			for (int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == '#') {
					if (!flag) {
						start = new Point(i, j, 0, 0);
						flag = true;
					} else {
						end = new Point(i, j, 0, 0);
					}
				}
			}
		}
		bfs(start);
		System.out.println(result);
	}

}
