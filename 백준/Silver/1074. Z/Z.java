import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, r, c;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
	
		int size = (int)Math.pow(2, N);
		
		System.out.println(dq(0, 0, size));
	}
	
	static int dq(int sr, int sc, int size) {
		if(size == 1) return 0;
		int ret = 0;
		int s = size/2;
		
		if(sr+s > r && sc+s > c) {
			return ret += dq(sr, sc, s);
		}
		ret += s*s;
		if(sr+s > r && sc+s <= c) {
			return ret += dq(sr, sc+s, s);
		}
		ret += s*s;
		if(sr+s <= r && sc+s > c) {
			return ret += dq(sr+s, sc, s);
		}
		ret += s*s;
		return ret += dq(sr+s, sc+s, s);
	}

}