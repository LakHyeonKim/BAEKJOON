import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static class Point{
		int x;
		int y;
		int d;
		int g;
		Point(int x, int y, int d, int g){
			this.x = x;
			this.y = y;
			this.d = d;
			this.g = g;
		}
	}
	private static int N;
	private static int[][] map;
	private static int[][] dir = {{0,1},{-1,0},{0,-1},{1,0}};
	private static List<Point> startPoint;
	private static List<Point> generation;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[101][101];
		startPoint = new ArrayList<>();
		generation = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			startPoint.add(new Point(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt()));
		}
		
		for(int i = 0; i < N; i++) {
			Point point = startPoint.get(i);
			int x = point.x;
			int y = point.y;
			int d = point.d;
			int g = point.g;
			generation.add(point);
			map[y][x] = i+1;
			for(int j = 0; j <= g; j++) {
				if(j == 0) {
					int nextY = y + dir[d][0];
					int nextX = x + dir[d][1];
					map[nextY][nextX] = i+1;
					generation.add(new Point(nextX, nextY, -1, -1));
					continue;
				}
				int size = generation.size();
				Point standard = generation.get(size-1);
				
				for(int k = size-2; k >= 0; k--) {
					int transX = (-1 * (generation.get(k).y - standard.y)) + standard.x;
					int transY = (generation.get(k).x - standard.x) + standard.y;
					map[transY][transX] = i+1;
					generation.add(new Point(transX, transY, -1, -1));
				}
			}
			generation.clear();
//			System.out.println();
//			for(int ii = 0; ii < 10; ii++) {
//				for(int jj = 0; jj < 10; jj++) {
//					System.out.print(map[ii][jj]);
//				}
//				System.out.println();
//			}
		}
		
		int cnt = 0;
		for(int y = 0; y < 100; y++) {
			for(int x = 0; x < 100; x++) {
				int check = 0;
				if(map[y][x] > 0) check++;	
				if(map[y][x+1] > 0) check++;
				if(map[y+1][x] > 0) check++;
				if(map[y+1][x+1] > 0) check++;
				if(check == 4) cnt++;
			}
		}
		System.out.println(cnt);
	}

}
