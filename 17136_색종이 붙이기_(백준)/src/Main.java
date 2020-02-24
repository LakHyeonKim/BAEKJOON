import java.util.Scanner;

public class Main {

	private static int[][] map;
	private static int[] paper = {0, 5, 5, 5, 5, 5};
	private static int min = Integer.MAX_VALUE, curSize, totalSize;

	private static boolean isAttach(int x, int y, int size) {
		boolean result = true;
		outer:for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(x + i >= 10 || y + j >= 10) {
					result = false;
					break outer;
				}
				if(map[x+i][y+j] == 0) {
					result = false;
					break outer;
				}
			}
		}
		return result;
	}
	
	private static void attach(int x, int y, int size) {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				map[x+i][y+j] = 0;
			}
		}
	}

	private static void detach(int x, int y, int size) {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				map[x+i][y+j] = 1;
			}
		}
	}
	
	private static void dfs(int num, int size, int paperCount) {
		if(min <= paperCount) return;
		if(paper[size] + 1 == 0) return;
		for(int i = 0; i < num; i++) {
			if(map[i/10][i%10] == 1) return;
		}
		if(curSize == totalSize) {
			min = Math.min(min, paperCount);
			return;
		}
		for(int i = num; i < 100; i++) {
			for(int j = 1; j <= 5; j++) {
				if(isAttach(i/10, i%10, j)) {
					paper[j]--;
					attach(i/10, i%10, j);
					curSize += j*j;
					dfs(i, j, paperCount + 1);
					paper[j]++;
					detach(i/10, i%10, j);
					curSize -= j*j;
				}else break;
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		map = new int[10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 1) totalSize++;
			}
		}
		dfs(0, 5, 0);
		if(min == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min);
	}

}
