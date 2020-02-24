import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static class Boom{
		int i;
		int j;
		int time;
		Boom(int i, int j, int time){
			this.i = i;
			this.j = j;
			this.time = time;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int R = sc.nextInt();
		int C = sc.nextInt();
		int N = sc.nextInt();
		char[][] map = new char[R][C];
		Queue<Boom> booms = new LinkedList<Main.Boom>();
		Queue<Boom> emptySpace = new LinkedList<Main.Boom>();

		for(int i = 0; i < R; i++) {
			String line = sc.next();
			for(int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] == 'O') {
					booms.offer(new Boom(i, j, 3));
				}else {
					emptySpace.offer(new Boom(i, j, 0));
				}
			}
		}
		int curTime = 0;
		while(curTime != N) {
			while(curTime%2 == 0 && !emptySpace.isEmpty()) {
				Boom boom = emptySpace.poll();
				map[boom.i][boom.j] = 'O';
			}
			for(Boom boom : booms) {
				if(boom.time == 1) {
					map[boom.i][boom.j] = '.';
					emptySpace.offer(new Boom(boom.i, boom.j, 0));
					if(boom.j + 1 < C) {
						map[boom.i][boom.j + 1] = '.';
						emptySpace.offer(new Boom(boom.i, boom.j + 1, 0));
					}
					if(boom.j - 1 >= 0) {
						map[boom.i][boom.j - 1] = '.';
						emptySpace.offer(new Boom(boom.i, boom.j - 1, 0));
					}	
					if(boom.i + 1 < R) {
						map[boom.i + 1][boom.j] = '.';
						emptySpace.offer(new Boom(boom.i + 1, boom.j, 0));
					}						
					if(boom.i - 1 >= 0) {
						map[boom.i - 1][boom.j] = '.';
						emptySpace.offer(new Boom(boom.i - 1, boom.j, 0));
					}
					boom.time = -1; // 터진애들
				}
				else {
					boom.time--;
				}
			}
			while(booms.peek().time == -1) {
				booms.poll();
			}			
			curTime++;
		}
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				System.out.print(map[i][j] + "");
			}
			System.out.println();
		}
	}
}
