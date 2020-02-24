import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.StringTokenizer;
//
//
//public class Main {
//	private static int[][] map;
//	private static int[][] dp;
//	private static int[][] direction = {{1,0},{-1,0},{0,1},{0,-1}};
//	private static int H, W;
//	public static void main(String[] args) throws Exception{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		H = Integer.parseInt(st.nextToken()); W = Integer.parseInt(st.nextToken());
//		map = new int[H][W]; dp = new int[H][W];
//		for(int i = 0; i < H; i++) {
//			st = new StringTokenizer(br.readLine());
//			for(int j = 0; j < W; j++) {
//				map[i][j] = Integer.parseInt(st.nextToken());
//			}
//		}
//		System.out.println(dfs(0,0));
//	}
//	private static int dfs(int i, int j) {
//		if(i == H-1 && j == W-1) return 1;
//		if(dp[i][j] == 0) {
//			for(int k = 0; k < 4; k++) {
//				int nextI = i + direction[k][0];
//				int nextJ = j + direction[k][1];
//				if(nextI < 0 || nextI >= H || nextJ < 0 || nextJ >= W) continue;
//				if(map[nextI][nextJ] < map[i][j]) {
//					dp[i][j] += dfs(nextI, nextJ);
//				}
//			}
//		}
//		return dp[i][j];
//	}
//}


public class Main {
 
    static int W; // ����(��)
    static int H; // ����(��)
    static int[][] map; // ����
    static int[][] dp; // �޸������̼�
    private static int[][] direction = {{1,0},{-1,0},{0,1},{0,-1}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        W = Integer.parseInt(st.nextToken()); 
        H = Integer.parseInt(st.nextToken());
        map = new int[W][H];
        dp = new int[W][H];
        
        for(int i=0; i<W; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<H; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        bw.write(String.valueOf(findWay(0,0)));
        bw.flush();
    
    }
	private static int findWay(int i, int j) {
		if(i == W-1 && j == H-1) return 1;
		if(dp[i][j] == 0) {
			for(int k = 0; k < 4; k++) {
				int nextI = i + direction[k][0];
				int nextJ = j + direction[k][1];
				if(nextI < 0 || nextI >= W || nextJ < 0 || nextJ >= H) continue;
				if(map[nextI][nextJ] < map[i][j]) {
					dp[i][j] += findWay(nextI, nextJ);
				}
			}
		}
		return dp[i][j];
	}
    
//    private static int findWay(int x, int y) {
//        
//        if(x == vertical-1 && y == horizontal-1) { // ���������� �����ϸ� ����� �� 1�� �����Ѵ�
//            return 1;
//        }
//        
//        if(memoization[x][y] == -1) { // ����� ���� ���� �� ����, �湮�� �� ���� ��츸 ���
//            memoization[x][y] = 0;
//            
//            // ���� �̵�
//            if(x > 0 && map[x][y] > map[x-1][y]) {
//                memoization[x][y] += findWay(x-1, y);
//            }
//            // �Ʒ��� �̵�
//            if(x < vertical-1 && map[x][y] > map[x+1][y]) {
//                memoization[x][y] += findWay(x+1, y);
//            }
//            // �������� �̵�
//            if(y > 0 && map[x][y] > map[x][y-1]) {
//                memoization[x][y] += findWay(x, y-1);
//            }
//            // ���������� �̵�
//            if(y < horizontal-1 && map[x][y] > map[x][y+1]) {
//                memoization[x][y] += findWay(x, y+1);
//            }
//        }
//        return memoization[x][y]; // �̹� �ͺô� ��ζ�� ���� ����� ���� return
//        
//    }    
}
