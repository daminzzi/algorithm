import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	static class Pair{
		int height;
		int index;
		
		public Pair(int h, int i) {
			height = h;
			index = i;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		Stack<Pair> stack = new Stack<>();
		
		for(int i = 1; i<=N; i++) {
					
			int idx = i;
			int h = Integer.parseInt(st.nextToken());
			
			while(!stack.isEmpty()) {
				if(stack.peek().height < h) {
					stack.pop();
				}
				else {
					sb.append(stack.peek().index).append(' ');
					break;
				}
			}
			
			if(stack.isEmpty()) {
				sb.append("0 ");
			}
			
			stack.push(new Pair(h, idx));
		}
		
		System.out.println(sb);
	}

}