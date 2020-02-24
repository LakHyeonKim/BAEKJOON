import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	static class Horse{
		int i;
		int j;
		int d;
		Horse(int i, int j, int d){
			this.i = i;
			this.j = j;
			this.d = d;
		}
		@Override
		public String toString() {
			return "Horse [i=" + i + ", j=" + j + ", d=" + d + "]";
		}
	}
	private static int N, K, turn;
	private static int[][] map;
	private static String[] line;
	private static List<Integer>[][] tempMap;
	private static List<Horse> holls;
	private static int[][] dir = {{0,1},{0,-1},{-1,0},{1,0}};

	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		K = Integer.parseInt(line[1]);
		map = new int[N][N];
		tempMap = new ArrayList[N][N];
		holls = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				tempMap[i][j] = new ArrayList<>();
			}
		}
		for(int i = 0; i < N; i++) {
			line = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}
		for(int i = 0; i < K; i++) {
			line = br.readLine().split(" ");
			holls.add(new Horse(Integer.parseInt(line[0])-1, Integer.parseInt(line[1])-1, Integer.parseInt(line[2])-1));
		}
		//System.out.println(holls);
		for(int i = 0; i < K; i++) {
			tempMap[holls.get(i).i][holls.get(i).j].add(i);
		}
		
		outer:while(1000 > turn) {
			turn++;
			for(int i = 0; i < K; i++) {
				//print();
				//System.out.println(holls);
				if(!isBottom(holls.get(i).i, holls.get(i).j, i)) continue;
				//System.out.println("ddd");
				int curI = holls.get(i).i;
				int curJ = holls.get(i).j;
				int curD = holls.get(i).d;
				int nextI = curI + dir[curD][0];
				int nextJ = curJ + dir[curD][1];
				if(edge(nextI, nextJ)) { // 안나간경우
					if(map[nextI][nextJ] == 0) {
						tempMap[nextI][nextJ].addAll(tempMap[curI][curJ]);
						if(tempMap[nextI][nextJ].size() >= 4) {
							break outer;
						}
						for(int horseNumber : tempMap[curI][curJ]) {
							holls.get(horseNumber).i = nextI;
							holls.get(horseNumber).j = nextJ;
						}
						tempMap[curI][curJ].clear();
					}else if(map[nextI][nextJ] == 1) {
						Collections.reverse(tempMap[curI][curJ]);
						tempMap[nextI][nextJ].addAll(tempMap[curI][curJ]);
						if(tempMap[nextI][nextJ].size() >= 4) {
							break outer;
						}
						for(int horseNumber : tempMap[curI][curJ]) {
							holls.get(horseNumber).i = nextI;
							holls.get(horseNumber).j = nextJ;
						}
						tempMap[curI][curJ].clear();
					}else if(map[nextI][nextJ] == 2) {
						chageDir(i);
						int cI = holls.get(i).i;
						int cJ = holls.get(i).j;
						int cD = holls.get(i).d;
						int nI = cI + dir[cD][0];
						int nJ = cJ + dir[cD][1];
						if(edge(nI, nJ)) {
							if(map[nI][nJ] == 0) {
								tempMap[nI][nJ].addAll(tempMap[cI][cJ]);
								if(tempMap[nI][nJ].size() >= 4) {
									break outer;
								}
								for(int horseNumber : tempMap[cI][cJ]) {
									holls.get(horseNumber).i = nI;
									holls.get(horseNumber).j = nJ;
								}
								tempMap[cI][cJ].clear();
							}else if(map[nI][nJ] == 1) {
								Collections.reverse(tempMap[cI][cJ]);
								tempMap[nI][nJ].addAll(tempMap[cI][cJ]);
								if(tempMap[nI][nJ].size() >= 4) {
									break outer;
								}
								for(int horseNumber : tempMap[cI][cJ]) {
									holls.get(horseNumber).i = nI;
									holls.get(horseNumber).j = nJ;
								}
								tempMap[cI][cJ].clear();
							}
						}
					}
				}else { //나간경우
					chageDir(i);
					int cI = holls.get(i).i;
					int cJ = holls.get(i).j;
					int cD = holls.get(i).d;
					int nI = cI + dir[cD][0];
					int nJ = cJ + dir[cD][1];
					if(edge(nI, nJ)) {
						if(map[nI][nJ] == 0) {
							tempMap[nI][nJ].addAll(tempMap[cI][cJ]);
							if(tempMap[nI][nJ].size() >= 4) {
								break outer;
							}
							for(int horseNumber : tempMap[cI][cJ]) {
								holls.get(horseNumber).i = nI;
								holls.get(horseNumber).j = nJ;
							}
							tempMap[cI][cJ].clear();
						}else if(map[nI][nJ] == 1) {
							Collections.reverse(tempMap[cI][cJ]);
							tempMap[nI][nJ].addAll(tempMap[cI][cJ]);
							if(tempMap[nI][nJ].size() >= 4) {
								break outer;
							}
							for(int horseNumber : tempMap[cI][cJ]) {
								holls.get(horseNumber).i = nI;
								holls.get(horseNumber).j = nJ;
							}
							tempMap[cI][cJ].clear();
						}
					}
				}
			}
		}
		if(turn == 1000) System.out.println(-1);
		else System.out.println(turn);
		
	}
	
	private static void print() {
		System.out.println();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(tempMap[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	private static boolean isBottom(int i, int j, int horseNumber) {
		int top = -1;
		for(int f = 0; f < tempMap[i][j].size(); f++) {
			if(tempMap[i][j].get(f) == horseNumber) {
				top = f;
				break;
			}
		}
		if(top == 0) return true;
		else return false;
	}
	
	private static void chageDir(int horseNumber) {
		if(holls.get(horseNumber).d == 0) {
			holls.get(horseNumber).d = 1;
		}else if(holls.get(horseNumber).d == 1) {
			holls.get(horseNumber).d = 0;
		}else if(holls.get(horseNumber).d == 2) {
			holls.get(horseNumber).d = 3;
		}else if(holls.get(horseNumber).d == 3) {
			holls.get(horseNumber).d = 2;
		}
	}
	
	private static boolean edge(int nextI, int nextJ) {
		if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= N) return false;
		else return true;
	}

}
