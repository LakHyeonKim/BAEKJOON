import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
	static class Point implements Comparable<Point>{
		int number;
		int count;
		Point(int number, int count){
			this.number = number;
			this.count = count;
		}

		@Override
		public String toString() {
			return "Point [number=" + number + ", count=" + count + "]";
		}

		@Override
		public int compareTo(Point o) {
			if(this.count > o.count) return 1;
			else if(this.count == o.count) {
				if(this.number > o.number) return 1;
			}
			return -1;
		}
	}
	private static int R,C,K;
	private static int[][] map;
	private static int cnt;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		K = sc.nextInt();
		map = new int[100][100];
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		int rSize = 3, cSize = 3;

		while(cnt <= 100) {
//			System.out.println("cSize:  " + cSize);
//			System.out.println("rSize:  " + rSize);
//			System.out.println("cnt:    " + cnt);
//			for(int ii = 0 ; ii < 10; ii++) {
//				for(int jj = 0; jj < 10; jj++) {
//					System.out.print(map[ii][jj] + " ");
//				}
//				System.out.println();
//			}
			if(map[R-1][C-1] == K) break;
			if(rSize >= cSize) {
//				System.out.println("insertRSIZE");
				int cSzieMax = 0;
				for(int i = 0; i < rSize; i++) {
					int j;
					int[] freqeuncy = new int[101];
					List<Point> sorted = new ArrayList<>();
					for(j = 0; j < cSize; j++) {
						freqeuncy[map[i][j]]++;
					}
					for(j = 1; j <= 100; j++) {
						if(freqeuncy[j] != 0) {
							sorted.add(new Point(j, freqeuncy[j]));
						}
					}
//					System.out.println(sorted);
					Collections.sort(sorted);
//					System.out.println(sorted);
					for(j = 0; j < 100; j++) {
						map[i][j] = 0;
					}
					int index = 0;
					for(j = 0; j < sorted.size(); j++) {
						map[i][index++] = sorted.get(j).number;
						map[i][index++] = sorted.get(j).count;
					}
					cSzieMax = Math.max(cSzieMax, index);
				}
				cSize = cSzieMax;
			}else {
//				System.out.println("insertCSIZE");
				int rSzieMax = 0;
				for(int i = 0; i < cSize; i++) {
					int j;
					int[] freqeuncy = new int[101];
					List<Point> sorted = new ArrayList<>();
					for(j = 0; j < rSize; j++) {
						freqeuncy[map[j][i]]++;
					}
					for(j = 1; j <= 100; j++) {
						if(freqeuncy[j] != 0) {
							sorted.add(new Point(j, freqeuncy[j]));
						}
					}
//					System.out.println(sorted);
					Collections.sort(sorted);
					
					for(j = 0; j < 100; j++) {
						map[j][i] = 0;
					}
					int index = 0;
					for(j = 0; j < sorted.size(); j++) {
						map[index++][i] = sorted.get(j).number;
						map[index++][i] = sorted.get(j).count;
					}
					rSzieMax = Math.max(rSzieMax, index);
				}
				rSize = rSzieMax;
			}
			cnt++;
		}
		if(cnt > 100) System.out.println(-1);
		else System.out.println(cnt);
	}

}
