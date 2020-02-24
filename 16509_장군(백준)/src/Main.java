import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static class Point{
		int i;
		int j;
		int level;
		Point(int i, int j, int level){
			this.i = i;
			this.j = j;
			this.level = level;
		}
	}
	static int N = 10, M = 9;
	static Point king;
	static Point sang;
	static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
	static int[][] map;
	static boolean[][] check;

	static int bfs(Point start) {
		Queue<Point> q = new LinkedList<Main.Point>();
		int result = -1;
		q.offer(start);
		while(!q.isEmpty()) {
			Point cur = q.poll();
			if(cur.i == king.i && cur.j == king.j) {
				result = cur.level;
				break;
			}
			check[cur.i][cur.j] = true;
			for(int k = 0; k < 4; k++) {
				if(k == 0) {
					int nextI = cur.i + dir[k][0];
					int nextJ = cur.j + dir[k][1];
					if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M) continue;
					boolean flag1 = true;
					for(int l = 0; l < 2; l++) {
						nextI -= 1;
						nextJ += 1;
						if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M || map[nextI][nextJ] == 7777) {
							flag1 = false;
							break;
						}
					}
					if(flag1) {
						if(!check[nextI][nextJ]) {
							check[nextI][nextJ] = true;
							q.offer(new Point(nextI, nextJ, cur.level + 1));
						}
					}
					nextI = cur.i + dir[k][0];
					nextJ = cur.j + dir[k][1];
					if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M) continue;
					boolean flag2 = true;
					for(int l = 0; l < 2; l++) {
						nextI += 1;
						nextJ += 1;
						if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M || map[nextI][nextJ] == 7777) {
							flag2 = false;
							break;
						}
					}
					if(flag2) {
						if(!check[nextI][nextJ]) {
							check[nextI][nextJ] = true;
							q.offer(new Point(nextI, nextJ, cur.level + 1));
						}
					}
				}
				if(k == 1) {
					int nextI = cur.i + dir[k][0];
					int nextJ = cur.j + dir[k][1];
					if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M) continue;
					boolean flag1 = true;
					for(int l = 0; l < 2; l++) {
						nextI += 1;
						nextJ += 1;
						if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M || map[nextI][nextJ] == 7777) {
							flag1 = false;
							break;
						}
					}
					if(flag1) {
						if(!check[nextI][nextJ]) {
							check[nextI][nextJ] = true;
							q.offer(new Point(nextI, nextJ, cur.level + 1));
						}
					}
					nextI = cur.i + dir[k][0];
					nextJ = cur.j + dir[k][1];
					if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M) continue;
					boolean flag2 = true;
					for(int l = 0; l < 2; l++) {
						nextI += 1;
						nextJ -= 1;
						if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M || map[nextI][nextJ] == 7777) {
							flag2 = false;
							break;
						}
					}
					if(flag2) {
						if(!check[nextI][nextJ]) {
							check[nextI][nextJ] = true;
							q.offer(new Point(nextI, nextJ, cur.level + 1));
						}
					}
				}
				if(k == 2) {
					int nextI = cur.i + dir[k][0];
					int nextJ = cur.j + dir[k][1];
					if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M) continue;
					boolean flag1 = true;
					for(int l = 0; l < 2; l++) {
						nextI += 1;
						nextJ -= 1;
						if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M || map[nextI][nextJ] == 7777) {
							flag1 = false;
							break;
						}
					}
					if(flag1) {
						if(!check[nextI][nextJ]) {
							check[nextI][nextJ] = true;
							q.offer(new Point(nextI, nextJ, cur.level + 1));
						}
					}
					nextI = cur.i + dir[k][0];
					nextJ = cur.j + dir[k][1];
					if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M) continue;
					boolean flag2 = true;
					for(int l = 0; l < 2; l++) {
						nextI -= 1;
						nextJ -= 1;
						if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M || map[nextI][nextJ] == 7777) {
							flag2 = false;
							break;
						}
					}
					if(flag2) {
						if(!check[nextI][nextJ]) {
							check[nextI][nextJ] = true;
							q.offer(new Point(nextI, nextJ, cur.level + 1));
						}
					}
				}
				if(k == 3) {
					int nextI = cur.i + dir[k][0];
					int nextJ = cur.j + dir[k][1];
					if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M) continue;
					boolean flag1 = true;
					for(int l = 0; l < 2; l++) {
						nextI -= 1;
						nextJ -= 1;
						if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M || map[nextI][nextJ] == 7777) {
							flag1 = false;
							break;
						}
					}
					if(flag1) {
						if(!check[nextI][nextJ]) {
							check[nextI][nextJ] = true;
							q.offer(new Point(nextI, nextJ, cur.level + 1));
						}
					}
					nextI = cur.i + dir[k][0];
					nextJ = cur.j + dir[k][1];
					if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M) continue;
					boolean flag2 = true;
					for(int l = 0; l < 2; l++) {
						nextI -= 1;
						nextJ += 1;
						if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M || map[nextI][nextJ] == 7777) {
							flag2 = false;
							break;
						}
					}
					if(flag2) {
						if(!check[nextI][nextJ]) {
							check[nextI][nextJ] = true;
							q.offer(new Point(nextI, nextJ, cur.level + 1));
						}
					}
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sang = new Point(sc.nextInt(), sc.nextInt(), 0);
		king = new Point(sc.nextInt(), sc.nextInt(), 0);
		//sc.close();
		map = new int[N][M];
		check = new boolean[N][M];
		map[king.i][king.j] = 7777;
		System.out.println(bfs(sang));
	}

}
