import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] tree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		tree = new int[N];
		int result = 0;
		int start = 0;
		int end = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
			end = Math.max(end, tree[i]);
		}
		
		while(start <= end) {
			long total = 0;
			int mid = (start+end)/2;
			for (int i : tree) {
				if (i>mid) {
					total += i-mid;	
				}
			}
			
			if (total < M) {
				end = mid-1;
			}
			else {
				result = mid;
				start = mid+1;
			}
		}
		
		System.out.println(result);

	}

}