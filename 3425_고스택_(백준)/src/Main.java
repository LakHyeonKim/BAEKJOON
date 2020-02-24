import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
		
	private static boolean isNumberic(String s) {
		try {
			Integer.parseInt(s);
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Long> s = new Stack<>();
		List<Integer> numberList = new ArrayList<>();
		List<String> commandList = new ArrayList<>();
		String input;
		boolean flag = true;
		int N = 0;
	
		while(true) {
			input = br.readLine();
			// 종료 지점
			if(input.equals("QUIT")) {
				break;
			}
			// 숫자 
			if(isNumberic(input) == true) {
				if(flag) {
					N = Integer.parseInt(input);
					flag = false;
				}else {
					numberList.add(Integer.parseInt(input));
				}
			// 명령어
			}
			if(isNumberic(input) == false){
				commandList.add(input);
			// 수행
			}
			if(input.equals("")) {
				for(int i = 0; i < N; i++) {
					long targetNumber = numberList.get(i);
					s.push(targetNumber);
					for(String command : commandList) {
						if(command.contains("END")) {
							if(s.size() == 1) {
								System.out.println(s.pop());
							}else if(s.size() != 1){
								System.out.println("ERROR");
							}
							break;
						}
						if(s.size() == 0) {
							if(command.contains("NUM")) {
								String[] split = command.split(" ");
								s.push(Long.parseLong(split[1]));
							}else {
								System.out.println("ERROR");
								break;
							}
						}else if(command.contains("NUM")) {
							String[] split = command.split(" ");
							s.push(Long.parseLong(split[1]));
						}else if(command.contains("POP")) {
							if(!s.isEmpty()) {
								s.pop();
							}else {
								System.out.println("ERROR");
								break;
							}
						}else if(command.contains("INV")) {
							if(!s.isEmpty()) {
								s.push(-1 * s.pop());
							}else {
								System.out.println("ERROR");
								break;
							}
						}else if(command.contains("DUP")) {
							if(!s.isEmpty()) {
								s.push(s.peek());
							}else {
								System.out.println("ERROR");
								break;
							}
						}else if(command.contains("SWP")) {
							long firstNumber = 0;
							long secondNumber = 0;
							if(!s.isEmpty()) {
								firstNumber = s.pop();
							}else {
								System.out.println("ERROR");
								break;
							}
							if(!s.isEmpty()) {
								secondNumber = s.pop();
							}else {
								System.out.println("ERROR");
								break;
							}
							s.push(firstNumber);
							s.push(secondNumber);
						}else if(command.contains("ADD")) {
							long firstNumber = 0;
							long secondNumber = 0;
							if(!s.isEmpty()) {
								firstNumber = s.pop();
							}else {
								System.out.println("ERROR");
								break;
							}
							if(!s.isEmpty()) {
								secondNumber = s.pop();
							}else {
								System.out.println("ERROR");
								break;
							}
							if(Math.abs(firstNumber+secondNumber) > 1000000000) {
								System.out.println("ERROR");
								break;
							}
							s.push(firstNumber+secondNumber);
						}else if(command.contains("SUB")) {
							long firstNumber = 0;
							long secondNumber = 0;
							if(!s.isEmpty()) {
								firstNumber = s.pop();
							}else {
								System.out.println("ERROR");
								break;
							}
							if(!s.isEmpty()) {
								secondNumber = s.pop();
							}else {
								System.out.println("ERROR");
								break;
							}
							if(Math.abs(secondNumber-firstNumber) > 1000000000) {
								System.out.println("ERROR");
								break;
							}
							s.push(secondNumber-firstNumber);
						}else if(command.contains("MUL")) {
							long firstNumber = 0;
							long secondNumber = 0;
							if(!s.isEmpty()) {
								firstNumber = s.pop();
							}else {
								System.out.println("ERROR");
								break;
							}
							if(!s.isEmpty()) {
								secondNumber = s.pop();
							}else {
								System.out.println("ERROR");
								break;
							}
							if(Math.abs(firstNumber*secondNumber) > 1000000000) {
								System.out.println("ERROR");
								break;
							}
							s.push(firstNumber*secondNumber);
						}else if(command.contains("DIV")) {
							long firstNumber = 0;
							long secondNumber = 0;
							if(!s.isEmpty()) {
								firstNumber = s.pop();
							}else {
								System.out.println("ERROR");
								break;
							}
							if(!s.isEmpty()) {
								secondNumber = s.pop();
							}else {
								System.out.println("ERROR");
								break;
							}
							if(firstNumber == 0) {
								System.out.println("ERROR");
								break;
							}
							if(Math.abs(secondNumber / firstNumber) > 1000000000) {
								System.out.println("ERROR");
								break;
							}
							s.push(secondNumber / firstNumber);
						}else if(command.contains("MOD")) {
							long firstNumber = 0;
							long secondNumber = 0;
							if(!s.isEmpty()) {
								firstNumber = s.pop();
							}else {
								System.out.println("ERROR");
								break;
							}
							if(!s.isEmpty()) {
								secondNumber = s.pop();
							}else {
								System.out.println("ERROR");
								break;
							}
							if(firstNumber == 0) {
								System.out.println("ERROR");
								break;
							}
							if(Math.abs(secondNumber % firstNumber) > 1000000000) {
								System.out.println("ERROR");
								break;
							}
							s.push(secondNumber % firstNumber);
						}
						
					}
					
					s.clear();
				}
				System.out.println("");
				numberList.clear();
				commandList.clear();
				flag = true;
			}
		}
	}
}
