import java.io.BufferedReader;
import java.io.InputStreamReader;

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
	}

	private static int[][] map;
	private static int N,M;
	private static String[] line;
	private static Point robot;
	private static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		line = br.readLine().split(" ");
		robot = new Point(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]));
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			line = br.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}
		int count = 0;
		outer:while(true) {
			if(map[robot.i][robot.j] == 0) {
				count++;
				map[robot.i][robot.j]= 2;
			}
			for(int i = 0; i < 4; i++) {
				int nextI = robot.i + dir[(robot.d + 3)%4][0];
				int nextJ = robot.j + dir[(robot.d + 3)%4][1];
				if(map[nextI][nextJ] == 0) {
					robot.i = nextI;
					robot.j = nextJ;
					robot.d = (robot.d + 3) % 4;
					continue outer;
				}
				robot.d = (robot.d + 3) % 4;
			}
			int nextI = robot.i + dir[(robot.d + 2) % 4][0];
			int nextJ = robot.j + dir[(robot.d + 2) % 4][1];
			if(map[nextI][nextJ] == 1) break;
			else {
				robot.i = nextI;
				robot.j = nextJ;
			}
		}
		System.out.println(count);
	}
}
