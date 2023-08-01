import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] people = new int[N];
		int cnt = 0, l, r, max;
		for(int i = 0; i<N; i++) {
			people[i] = Integer.parseInt(br.readLine());		
		}
		
		long result = 0;
		Stack<int[]> s = new Stack<>();
		for(int i = 0; i<N; i++) {
			while(!s.isEmpty() && s.peek()[0] < people[i]) {
//				System.out.println("4"+s.peek()[0] +" "+ s.peek()[1]  +" "+ people[i]);
				result += s.pop()[1];
			}
			if(s.isEmpty()) {
				s.push(new int[] {people[i], 1});
			}
			else {
				if(s.peek()[0] > people[i]) {
//					System.out.println("3"+s.peek()[0] +" "+ s.peek()[1]  +" "+ people[i]);
					s.push(new int[] {people[i], 1});
					result++;
				} else if(s.peek()[0] == people[i]) {
//					System.out.println("2"+s.peek()[0] +" "+ s.peek()[1] +" "+ people[i]);
					result += s.peek()[1]++;
					if(s.size() > 1) {
						result++;
//						System.out.println("1"+s.peek()[0] +" "+ s.peek()[1] +" "+ people[i]);
					}
				}				
			}
		}
		
		System.out.println(result);
	}

}