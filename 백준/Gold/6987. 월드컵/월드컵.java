import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int [][] result;
//	static boolean [][] visited;
    static int[] gx = {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4};
    static int[] gy = {1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		// 각 나라 승+무+패 = 5
		// 승무패 합이 30
		// 승(+)패(-) 합이 0
		int T = 4;
xx:		while(T-- > 0) {
			result = new int[6][3];
			st = new StringTokenizer(br.readLine());
			int w = 0, d = 0, l = 0;
			for(int i = 0; i<6; i++) {
				for(int j = 0; j<3; j++) {
					result[i][j] = Integer.parseInt(st.nextToken());
				}
				if(result[i][0]+result[i][1]+result[i][2] != 5) {
					sb.append("0 ");
					continue xx;
				}
				w+=result[i][0];
				l+=result[i][2];
				d+=result[i][1];
			}
			
			if(w != l) {
				sb.append("0 ");
				continue;
			}
			if(w+l+d != 30) {
				sb.append("0 ");
				continue;
			}
			
			boolean flag = dfs(0);
			if(!flag) {
				sb.append("0 ");
			}
			else {
				sb.append("1 ");
			}
		}
		System.out.println(sb);
	}
	
	static boolean dfs(int depth) {
		if(depth == 15) {
			return true;
		}
		boolean flag = false;
		
		int i = gx[depth];
		int j = gy[depth];
		
		if(result[i][0] > 0 && result[j][2] > 0) {
			result[i][0]--;
			result[j][2]--;
			flag = dfs(depth+1);
			if(flag) return true;
			result[i][0]++;
			result[j][2]++;
		}
		if(result[i][1] > 0 && result[j][1] > 0) {
			result[i][1]--;
			result[j][1]--;
			flag = dfs(depth+1);
			if(flag) return true;
			result[i][1]++;
			result[j][1]++;
		}
		if(result[i][2] > 0 && result[j][0] > 0) {
			result[i][2]--;
			result[j][0]--;	
			flag = dfs(depth+1);
			if(flag) return true;
			result[i][2]++;
			result[j][0]++;
		}
	
		return false;
	}
}