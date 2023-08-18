import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
//	static boolean [][] visited;
    static int[] gx = {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4};
    static int[] gy = {1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5};
    static int[] win, lose, draw;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		// 각 나라 승+무+패 = 5
		// 승무패 합이 30
		// 승(+)패(-) 합이 0
		int T = 4;
xx:		while(T-- > 0) {
			win = new int[6];
			lose = new int[6];
			draw = new int[6];
			f = false;
			st = new StringTokenizer(br.readLine());
			int w = 0, d = 0, l = 0;
			for(int i = 0; i<6; i++) {
				win[i] = Integer.parseInt(st.nextToken());
				draw[i] = Integer.parseInt(st.nextToken());
				lose[i] = Integer.parseInt(st.nextToken());
				if(win[i]+lose[i]+draw[i] != 5) {
					sb.append("0 ");
					continue xx;
				}
				w+=win[i];
				l+=lose[i];
				d+=draw[i];
			}
			
			if(w != l) {
				sb.append("0 ");
				continue;
			}
			if(w+l+d != 30) {
				sb.append("0 ");
				continue;
			}
			
			dfs(0);
			
			if(!f) {
				sb.append("0 ");
			}
			else {
				sb.append("1 ");
			}
		}
		System.out.println(sb);
	}
	static boolean f = false;
	static void dfs(int depth) {
		if(depth == 15) {
			f = true;
			return;
		}
		
		int i = gx[depth];
		int j = gy[depth];
		
		if(win[i] > 0 && lose[j] > 0) {
			win[i]--;
			lose[j]--;
			dfs(depth+1);
			if(f) return;
			win[i]++;
			lose[j]++;
		}
		if(draw[i] > 0 && draw[j] > 0) {
			draw[i]--;
			draw[j]--;
			dfs(depth+1);
			if(f) return;
			draw[i]++;
			draw[j]++;
		}
		if(lose[i] > 0 && win[j] > 0) {
			lose[i]--;
			win[j]--;	
			dfs(depth+1);
			if(f) return;
			lose[i]++;
			win[j]++;
		}
	
		return;
	}
}