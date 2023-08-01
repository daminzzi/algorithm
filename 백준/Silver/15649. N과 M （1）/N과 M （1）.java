import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static boolean[] used;
	static int[] ans;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		used = new boolean[N];
		ans = new int[M];
		
		choose(0);
	}
	
	static void choose(int level) {
		if(level == M) {
			for(int i = 0; i<M; i++) {
				sb.append(ans[i]).append(' ');
			}
			System.out.println(sb.toString());
			sb.delete(0, sb.length());
			return;
		}
		
		for(int i =0; i<N; i++) {
			if(used[i] == true) continue;
			used[i] = true;
			ans[level] = i + 1;
			choose(level+1);
			used[i] = false;
		}
	}

}
