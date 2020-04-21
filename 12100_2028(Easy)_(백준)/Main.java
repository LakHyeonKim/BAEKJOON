import java.util.Scanner;

public class Main {
	private static final int TRY = 5, DIR = 4;
	private static int N, max;
	private static int[][] map;
	private static int[][] tempMap;
	private static int[] numberOfCase;
	
	private static void moveBlock(int dir) {
		if(dir == 0) {
			for(int i = 0; i < N; i++) {
				int first = N - 1;
				int second = N - 2;
				while(second >= 0) {
					if(tempMap[i][second] == 0) {
						second --;
						continue;
					}
					if(tempMap[i][first] == 0 && tempMap[i][second] != 0) {
						tempMap[i][first] = tempMap[i][second];
						tempMap[i][second] = 0;
						second--;
						continue;
					}
					if(tempMap[i][first] != tempMap[i][second]) {
						first--;
						if(tempMap[i][first] != 0) {
							second--;							
						}
						continue;
					}
					if(tempMap[i][first] == tempMap[i][second]) {
						tempMap[i][first] += tempMap[i][second];
						tempMap[i][second] = 0;
						first--;
						second--;
						continue;
					}
				}
			}
		}else if(dir == 1) {
			for(int i = 0; i < N; i++) {
				int first = 0;
				int second = 1;
				while(second < N) {
					if(tempMap[i][second] == 0) {
						second ++;
						continue;
					}
					if(tempMap[i][first] == 0 && tempMap[i][second] != 0) {
						tempMap[i][first] = tempMap[i][second];
						tempMap[i][second] = 0;
						second++;
						continue;
					}
					if(tempMap[i][first] != tempMap[i][second]) {
						first++;
						if(tempMap[i][first] != 0) {
							second++;							
						}
						continue;
					}
					if(tempMap[i][first] == tempMap[i][second]) {
						tempMap[i][first] += tempMap[i][second];
						tempMap[i][second] = 0;
						first++;
						second++;
						continue;
					}
				}
			}
		}else if(dir == 2) {
			for(int i = 0; i < N; i++) {
				int first = N - 1;
				int second = N - 2;
				while(second >= 0) {
					if(tempMap[second][i] == 0) {
						second --;
						continue;
					}
					if(tempMap[first][i] == 0 && tempMap[second][i] != 0) {
						tempMap[first][i] = tempMap[second][i];
						tempMap[second][i] = 0;
						second--;
						continue;
					}
					if(tempMap[first][i] != tempMap[second][i]) {
						first--;
						if(tempMap[first][i] != 0) {
							second--;							
						}
						continue;
					}
					if(tempMap[first][i] == tempMap[second][i]) {
						tempMap[first][i] += tempMap[second][i];
						tempMap[second][i] = 0;
						first--;
						second--;
						continue;
					}
				}
			}
		}else if(dir == 3) {
			for(int i = 0; i < N; i++) {
				int first = 0;
				int second = 1;
				while(second < N) {
					if(tempMap[second][i] == 0) {
						second ++;
						continue;
					}
					if(tempMap[first][i] == 0 && tempMap[second][i] != 0) {
						tempMap[first][i] = tempMap[second][i];
						tempMap[second][i] = 0;
						second++;
						continue;
					}
					if(tempMap[first][i] != tempMap[second][i]) {
						first++;
						if(tempMap[first][i] != 0) {
							second++;							
						}
						continue;
					}
					if(tempMap[first][i] == tempMap[second][i]) {
						tempMap[first][i] += tempMap[second][i];
						tempMap[second][i] = 0;
						first++;
						second++;
						continue;
					}
				}
			}
		}
	}
	
	private static void permutation(int depth) {
		if(depth == TRY) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					tempMap[i][j] = map[i][j];
				}
			}
			for(int t = 0; t < TRY; t++) {
				int dir = numberOfCase[t];
				moveBlock(dir);
			}
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					max = Math.max(max, tempMap[i][j]);
				}
			}
			return;
		}
		for(int i = 0; i < DIR; i++) {
			numberOfCase[depth] = i;
			permutation(depth + 1);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		tempMap = new int[N][N];
		numberOfCase = new int[TRY];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		sc.close();
		permutation(0);
		System.out.println(max);
	}
}