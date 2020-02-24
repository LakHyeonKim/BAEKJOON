import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static class Point{
		int i;
		int j;
		Point(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
	private static int R,C,T;
	private static String[] line;
	private static int[][] map;
	private static int[][] tempMap;
	private static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
	private static Point cleanerUp, cleanerUnder;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		line = br.readLine().split(" ");
		R = Integer.parseInt(line[0]);
		C = Integer.parseInt(line[1]);
		T = Integer.parseInt(line[2]);
		map = new int[R][C];
		tempMap = new int[R][C];
		boolean flag = true;
		for(int i = 0; i < R; i++) {
			line = br.readLine().split(" ");
			for(int j = 0 ; j < C; j++) {
				map[i][j] = Integer.parseInt(line[j]);
				if(map[i][j] == -1) {
					if(flag) {
						cleanerUp = new Point(i, j);
						flag = false;
					}else {
						cleanerUnder = new Point(i, j);
					}
				}
			}
		}
		int time = 0;
		while(time < T) {
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					if(map[i][j] == 0 || map[i][j] == -1) continue;
					int cnt = 0;
					for(int k = 0; k < 4; k++) {
						int nextI = i + dir[k][0];
						int nextJ = j + dir[k][1];
						if(nextI < 0 || nextI >= R || nextJ < 0 || nextJ >= C) continue;
						if(map[nextI][nextJ] == -1) continue;
						tempMap[nextI][nextJ] += map[i][j]/5;
						cnt++;
					}
					map[i][j] -= (map[i][j]/5) * cnt;
				}
			}
			
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					map[i][j] += tempMap[i][j];
					tempMap[i][j] = 0;
				}
			}
			
			int upTemp1 = map[cleanerUp.i][C-1];
			for(int j = C-1; j > cleanerUp.j+1; j--) {
				map[cleanerUp.i][j] = map[cleanerUp.i][j-1];
			}
			map[cleanerUp.i][cleanerUp.j+1] = 0;
			int upTemp2 = map[0][C-1];
			for(int i = 0; i < cleanerUp.i-1; i++) {
				map[i][C-1] = map[i+1][C-1];
			}
			map[cleanerUp.i-1][C-1] = upTemp1;
			int upTemp3 = map[0][0];
			for(int j = 0; j < C-2; j++) {
				map[0][j] = map[0][j+1];
			}
			map[0][C-2] = upTemp2;
			for(int i = cleanerUp.i; i > 1; i--) {
				if(map[i][0] == -1) continue;
				map[i][0] = map[i-1][0];
			}
			map[1][0] = upTemp3;
			
			int underTemp1 = map[cleanerUnder.i][C-1];
			for(int j = C-1; j > 1; j--) {
				map[cleanerUnder.i][j] = map[cleanerUnder.i][j-1];
			}
			map[cleanerUnder.i][1] = 0;
			int underTemp2 = map[R-1][C-1];
			for(int i = R-1; i > cleanerUnder.i+1; i--) {
				map[i][C-1] = map[i-1][C-1];
			}
			map[cleanerUnder.i+1][C-1] = underTemp1;
			int underTemp3 = map[R-1][0];
			for(int j = 0; j < C-2; j++) {
				map[R-1][j] = map[R-1][j+1];
			}
			map[R-1][C-2] = underTemp2;
			for(int i = cleanerUnder.i; i < R-2; i++) {
				if(map[i][0] == -1) continue;
				map[i][0] = map[i+1][0];
			}
			map[R-2][0] = underTemp3;
			time++;
		}
		int sum = 0;
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] > 0) {
					sum += map[i][j];
				}
			}
		}
		System.out.println(sum);
	}

}
