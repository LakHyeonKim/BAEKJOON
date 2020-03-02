import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	private static int N, M;
	private static String[] line;
	private static int[][] map;
	private static int[][][] t = {
			{{0,0},{0,1},{0,2},{0,3}},
			{{0,0},{1,0},{2,0},{3,0}},
			{{0,0},{0,1},{1,0},{1,1}},
			{{0,0},{1,0},{2,0},{2,1}},
			{{0,0},{0,1},{0,2},{1,0}},
			{{0,0},{0,1},{1,1},{2,1}},
			{{0,0},{1,0},{1,-1},{1,-2}},
			{{0,0},{1,0},{1,1},{2,1}},
			{{0,0},{0,1},{1,0},{1,-1}},
			{{0,0},{0,1},{0,2},{1,1}},
			{{0,0},{1,0},{2,0},{1,-1}},
			{{0,0},{1,0},{1,-1},{1,1}},
			{{0,0},{1,0},{2,0},{1,1}},
			{{0,0},{1,0},{1,-1},{2,-1}},
			{{0,0},{0,1},{1,1},{1,2}},
			{{0,0},{1,0},{2,0},{2,-1}},
			{{0,0},{1,0},{1,1},{1,2}},
			{{0,0},{0,1},{1,0},{2,0}},
			{{0,0},{0,1},{0,2},{1,2}}};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//for(int test = 0; test < 19; test++) {
		line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		map = new int[N][M];

		for(int i = 0; i < N; i++) {
			line = br.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}
		int max = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				for(int k = 0; k < 19; k++) {
					int areaSum = 0;
					for(int l = 0; l < 4; l++) {
						int nextI = i + t[k][l][0];
						int nextJ = j + t[k][l][1];
						if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M) break;
						else {
							areaSum += map[nextI][nextJ];
						}
					}
					max = Math.max(max, areaSum);
				}
			}
		}
		System.out.println(max);	
	}
	//}
}
