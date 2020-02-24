import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	private static final int INF = 9999999;
	private static int N, M, first, second, index, min = Integer.MAX_VALUE;
	private static String[] line;
	private static int[][] map;
	private static int[] sum;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		sum = new int[N+1];
		map = new int[N+1][N+1];
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(i == j) map[i][j] = 0;
				else map[i][j] = INF;
			}
		}
		for(int i = 0; i < M; i++) {
			line = br.readLine().split(" ");
			first = Integer.parseInt(line[0]);
			second = Integer.parseInt(line[1]);
			map[first][second] = map[second][first] = 1;
		}
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				sum[i] += map[i][j];
			}
			if(min > sum[i]) {
				min = sum[i];
				index = i;
			}
		}
		System.out.println(index);
	}
}
