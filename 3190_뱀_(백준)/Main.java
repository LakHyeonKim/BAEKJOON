import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Main {
	static class Point{
		int i;
		int j;
		int d;
		Point(int i, int j, int d){
			this.i = i;
			this.j = j;
			this.d = d;
		}
		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + ", d=" + d + "]";
		}
	}
	private static int N,K,L;
	private static int[][] map;
	private static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
	private static Deque<Point> snake;
	private static Map<Integer, String> commands;
	private static int time = 0;
	
	private static boolean isSnakeBody(int nextI, int nextJ) {
		if(map[nextI][nextJ] == 8) return true;
		return false;
	}
	
	private static boolean check(int nextI, int nextJ) {
		if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= N) return true;
		else if(isSnakeBody(nextI, nextJ)) return true;
		else return false;
	}
	
	private static boolean move(int nextDir) {
		int nextI = snake.peek().i + dir[nextDir][0];
		int nextJ = snake.peek().j + dir[nextDir][1];
		if(check(nextI, nextJ)) return true;
		if(map[nextI][nextJ] == 1) {
			map[nextI][nextJ] = 8;
			snake.offerFirst(new Point(nextI, nextJ, nextDir));
		}else {
			Point cur = snake.pollLast();
			map[cur.i][cur.j] = 0;
			map[nextI][nextJ] = 8;
			snake.offerFirst(new Point(nextI, nextJ, nextDir));
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		snake = new LinkedList<>();
		commands = new HashMap<>();
		K = Integer.parseInt(br.readLine());
		for(int i = 0; i < K; i++) {
			String[] line = br.readLine().split(" ");
			map[Integer.parseInt(line[0])-1][Integer.parseInt(line[1])-1] = 1;
		}
		L = Integer.parseInt(br.readLine());
		for(int i = 0; i < L; i++) {
			String[] line = br.readLine().split(" ");
			commands.put(Integer.parseInt(line[0])+1, line[1]);
		}
		snake.offer(new Point(0, 0, 1));
		while(true) {
			time++;
			if(commands.containsKey(time)) {
				if(commands.get(time).equals("D")) {
					if(move((snake.peek().d+1) % 4)) break;
				}else {
					if(move((snake.peek().d+3) % 4)) break;
				}
			}else {
				if(move(snake.peek().d)) break;
			}
		}
		System.out.println(time);
	}
}