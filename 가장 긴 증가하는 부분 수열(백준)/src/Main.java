import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	private static int N, max, cnt;
	private static String[] line;
	private static int[] a;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		a = new int[N];
		line = br.readLine().split(" ");
		for(int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(line[i]);
		}
		for(int i = 0; i < N-1; i++) {
			cnt = 1;
			for(int j = i + 1; j < N; j++) {
				if(a[i] < a[j]) cnt++;
			}
			if(max < cnt) max = cnt;
		}
		System.out.println(max);
	}
}
