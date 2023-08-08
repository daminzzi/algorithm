import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static class Egg {
		int s;
		int w;
		
		public Egg(int s, int w) {
			this.s = s;
			this.w = w;
		}
		
		boolean isBreak() {
			if(s <= 0) {
				return true;
			}
			
			return false;
		}
	}
	
	static int N;
	static Egg[] eggList;
	static int max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		eggList = new Egg[N];
		
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			eggList[i] = new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		test(0, 0);
		
		System.out.println(max);
	}
	
	static void test(int depth, int cnt) {
		if(depth == N) {
			return;
		}
		
		if(eggList[depth].isBreak()) {
			test(depth+1, cnt);
			return;
		}
		
		for(int i = 0; i<N; i++) {
			int temp = 0;
			if(depth == i) continue;
			if(eggList[i].isBreak()) continue;
			
			eggBreak(eggList[depth], eggList[i]);
			if(eggList[depth].isBreak()) temp++;
			if(eggList[i].isBreak()) temp++;
			
			max = Math.max(cnt+temp, max);
			test(depth+1, cnt+temp);
			eggRecovery(eggList[depth], eggList[i]);
		}
	}
	
	static void eggBreak(Egg e1, Egg e2) {
		e1.s -= e2.w;
		e2.s -= e1.w;
	}
	
	static void eggRecovery(Egg e1, Egg e2) {
		e1.s += e2.w;
		e2.s += e1.w;
	}
}