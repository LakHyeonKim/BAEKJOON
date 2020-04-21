import java.util.Scanner;

public class Main {
	private static int N, M;
	private static int[][] map;
	private static boolean[] visited;
	private static String[] line;
	private static int max;
	
	private static void dfs(int depth) {
		if(depth >= N*M) {
			int allSum = 0;
			int temp = 0;
			for(int i = 0; i < N; i++) {
				temp = 0;
				for(int j = 0; j < M; j++) {
					if(visited[i * M + j]) {
						temp *= 10;
						temp += map[i][j];
					}else {
						allSum += temp;
						temp = 0;
					}
				}
				allSum += temp;
			}
			for(int j = 0; j < M; j++) {
				temp = 0;
				for(int i = 0; i < N; i++) {
					if(!visited[i * M + j]) {
						temp *= 10;
						temp += map[i][j];
					}else {
						allSum += temp;
						temp = 0;
					}
				}
				allSum += temp;
			}
			max = Math.max(max, allSum);
			return;
		}
		visited[depth] = true;
		dfs(depth + 1);
		visited[depth] = false;
		dfs(depth + 1);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		visited = new boolean[N*M];
		for(int i = 0; i < N; i++) {
			line = sc.next().split("");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}
		dfs(0);
		System.out.println(max);
	}
}