import java.util.Scanner;

public class Main {
	private static int[][] map;
	private static int[][] dir = {{0,1},{-1,0},{0,-1},{1,0}};
	private static int r1, c1, r2, c2, val=1, cnt, len =1, startI, startJ, size, max;
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		r1 = sc.nextInt();
		c1 = sc.nextInt();
		r2 = sc.nextInt();
		c2 = sc.nextInt();
		map = new int[r2-r1+1][c2-c1+1];
		size = (r2-r1+1) * (c2-c1+1);
		outer:while(true) {
			for(int i = 0; i < 2; i++) {
				for(int j = 0; j < len; j++) {
					if(r1<= startI && startI <= r2 && c1 <= startJ && startJ <= c2) {
						map[startI-r1][startJ-c1] = val++;
						max = Math.max(max, map[startI-r1][startJ-c1]);
						cnt++;
						if(cnt == size) {
							break outer;
						}
					}
					else {
						val++;
					}
					startI += dir[i][0];
					startJ += dir[i][1];
				}
			}
			len++;
			for(int i = 2; i < 4; i++) {
				for(int j = 0; j < len; j++) {
					if(r1<= startI && startI <= r2 && c1 <= startJ && startJ <= c2) {
						map[startI-r1][startJ-c1] = val++;
						max = Math.max(max, map[startI-r1][startJ-c1]);
						cnt++;
						if(cnt == size) {
							break outer;
						}
					}
					else {
						val++;
					}
					startI += dir[i][0];
					startJ += dir[i][1];
				}
			}
			len++;
		}
		max = String.valueOf(max).length();
		for(int i = 0; i < r2-r1+1; i++) {
			for(int j = 0; j < c2-c1+1; j++) {
				System.out.format("%"+ max + "d ", map[i][j]);
			}
			System.out.println();
		}
	}
}