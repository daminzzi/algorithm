import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	static Map<Long, Long> map = new HashMap<>();
	static long P, Q;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long n = Long.parseLong(st.nextToken());
		P = Long.parseLong(st.nextToken());
		Q = Long.parseLong(st.nextToken());
		
		System.out.println(findSeq(n));
			
	}
	
	static long findSeq(long num) {
		if(num == 0) return 1;
		if(map.containsKey(num)) return map.get(num);
		
		long a = (long)Math.floor(num/P);
		long b = (long)Math.floor(num/Q);
		
		map.put(num, findSeq(a)+findSeq(b));
		return map.get(num);
	}

}