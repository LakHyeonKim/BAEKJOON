
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeSet;

public class Main {
	private static int N,M;
	private static int[] arr;
	private static boolean[] visited;
	private static Stack<Integer> stack;
	private static TreeSet<String> treeSet;
	
	private static void permuatation(int depth) {
		if(depth == M) {
			String temp = "";
			for(int i = 0; i < M; i++) {
				temp += stack.get(i) + " ";
			}
			treeSet.add(temp.trim());
			return;
		}
		for(int i = 0; i < N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			stack.push(arr[i]);
			permuatation(depth+1);
			stack.pop();
			visited[i] = false;
		}
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N];
		visited = new boolean[N];
		stack = new Stack<>();
		treeSet = new TreeSet<>();
		
		for(int i = 0; i < N; i++) arr[i] = sc.nextInt();
		
		permuatation(0);
		for(String s : treeSet) {
			System.out.println(s);
		}
	}

}
