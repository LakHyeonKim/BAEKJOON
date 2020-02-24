import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class Main {
	private static int N, width;
	private static char[] code;
	private static Map<String,Integer> map;
	private static String result = "";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		code = br.readLine().toCharArray();
		width = N/5;
		map = new HashMap<>();
		map.put("####.##.##.####", 0);
		map.put("###..#####..###", 2);
		map.put("###..####..####", 3);
		map.put("#.##.####..#..#", 4);
		map.put("####..###..####", 5);
		map.put("####..####.####", 6);
		map.put("###..#..#..#..#", 7);
		map.put("####.#####.####", 8);
		map.put("####.####..####", 9);
		
		for(int i = 0; i < width; i++) {
			int cnt1 = 0;
			for(int j = 0; j < 5; j++) {
				if(code[(width * j) + i] == '.') cnt1++;
			}
			if(cnt1 == 5) continue; 
			else {
				int cnt2 = 0, cnt3 = 0;
				for(int j = 0; j < 5; j++) {
					if(code[(width * j) + i] == '#') cnt2++;
					if(i+1 < width &&code[(width * j) + i+1] == '.') cnt3++;
				}
				if(cnt2 == 5 && cnt3 == 5) {
					result += "1";
					i++;
				}else if(cnt2 == 5 && cnt3 == 0) {
					result += "1";
				}
				else {
					String temp2 = "";
					for(int k = 0; k < 5; k++) {
						for(int l = i; l < i + 3; l++) {
							temp2 += String.valueOf(code[(width * k) + l]);
						}
					}
					result += map.get(temp2) + "";
					i+=3;
				}
			}
		}
		
		System.out.print(result);
	}

}
