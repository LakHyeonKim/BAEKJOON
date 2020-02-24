import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static class Point{
		int x;
		int level;
		Point(int x, int level){
			this.x = x;
			this.level = level;
		}
	}
	
	private static int N,K;
	private static boolean[] visited;
	private static int[] dir = {1, -1, 2};
	
	private static int bfs(Point start) {
		if(start.x == K) return 0; 
		int result = 0;
		Queue<Point> q = new LinkedList<>();
		q.offer(start);
		visited[start.x] = true;
		outer:while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for(int k = 0; k < 3; k++) {
				int nextX = cur.x;
				if(k == 2) {
					nextX *= dir[k];
				}else {
					nextX += dir[k];
				}
				if(nextX < 0 || nextX > 100000) continue;
				if(nextX == K) {
					result = cur.level+1;
					break outer;
				}
				if(visited[nextX]) continue;
				visited[nextX] = true;
				q.offer(new Point(nextX, cur.level+1));
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); K = sc.nextInt();
		visited = new boolean[100001];
		
		System.out.println(bfs(new Point(N, 0)));
	}

}
