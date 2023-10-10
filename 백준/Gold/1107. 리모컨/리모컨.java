import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static String N, M;
	static int ans;
	static boolean[] isBroken = new boolean[10];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = st.nextToken();
		
		st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken());
		if(k != 0) {
			st = new StringTokenizer(br.readLine());
		
			for(int i = 0; i<k; i++) {
				isBroken[Integer.parseInt(st.nextToken())] = true;
			}
		}		
		
		int num = Integer.parseInt(N);
		ans = Math.abs(num-100); //+, -만 가지고 N까지 이동하는데 필요한 횟수
		
		if(k == 10) {
			System.out.println(ans);
			return;
		}
		
		int diff = 0; //n과 수 사이의 차이
		
		while(true) {
			int plus = num+diff;
			int minus = num-diff;
			String p = Integer.toString(plus);
			String m = Integer.toString(minus);
			int ptemp = Integer.MAX_VALUE; //해당 번호까지 이동에 필요한 횟수
			int mtemp = Integer.MAX_VALUE; //해당 번호까지 이동에 필요한 횟수
//			System.out.println(plus+" "+minus);
			
			if(isPossible(plus)) {
				ptemp = p.length()+diff;
			}
			
			if(minus >= 0 && isPossible(minus)) {
				mtemp = m.length()+diff;
			}
			
			int min = Math.min(ptemp, mtemp);
			if(min < Integer.MAX_VALUE) {
				if(min >= ans) break;
//				System.out.println(ans+" "+p+" "+m);
				System.out.println(min);
				return;
			}
			
			if(ans < diff+p.length()) {
//				System.out.println(p+" "+m);
				break;
			}
			
//			ans = Math.min(ptemp, mtemp);
			diff++;
		}
		
		System.out.println(ans);
		
	}
	
	static boolean isPossible(int num) {
		String str = Integer.toString(num);
		for(int i = 0; i<str.length(); i++) {
			if(isBroken[str.charAt(i)-'0']) {
				return false;
			}
		}
		return true;
	}

}