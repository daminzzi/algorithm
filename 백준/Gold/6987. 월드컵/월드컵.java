import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int [][] result;
	static boolean [][] visited;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		// 각 나라 승+무+패 = 5
		// 승무패 합이 30
		// 승(+)패(-) 합이 0
		// 무 cnt > 0 -> 다음 무가 들어오면 cnt-무
		// cnt <= 0 -> 다음 무가 들어오면 cnt+무
		// 입력이 끝났을 때 무 cnt 0 이어야 함
		// 한 국가의 승이 n이면 나를 제외하고 패가 1이상인 국가가 n개이상 있어야 함
		// A 국가에서부터 자신의 승 무 패 cnt가 0이 되는 순간까지 다른 국가랑 비교
		// 만약 b,c,d,e,f 모든 국가를 봤는데, cnt가 0이 아니면 틀림.
		int T = 4;
xx:		while(T-- > 0) {
			result = new int[6][3];
			visited = new boolean[6][6];
			st = new StringTokenizer(br.readLine());
			int w = 0, d = 0, l = 0;
			for(int i = 0; i<6; i++) {
				for(int j = 0; j<3; j++) {
					result[i][j] = Integer.parseInt(st.nextToken());
				}
				if(result[i][0]+result[i][1]+result[i][2] != 5) {
					System.out.print("0 ");
					continue xx;
				}
				w+=result[i][0];
				l+=result[i][2];
//				if(d <= 0) d += result[i][1];
//				else d -= result[i][1];
			}
			
			if(w != l) {
				System.out.print("0 ");
				continue;
			}
			
//			if(d != 0) {
//				System.out.print("0 ");
//				continue;
//			}
			
			for(int i = 0; i<6; i++) {
				for(int j = 0; j<6; j++) {
					visited[i][j] = false;
				}
			}
			
			boolean flag = dfs(0);
			if(!flag) {
				System.out.print("0 ");
			}
		}
		
	}
	
	static boolean dfs(int depth) {
		if(depth == 15) {
			System.out.print("1 ");
			return true;
		}
		boolean flag = false;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
                if(i == j) continue;
                if(visited[i][j]) continue;
				if(result[i][0] > 0 && result[j][2] > 0) {
					result[i][0]--;
					result[j][2]--;
					visited[i][j] = true;
					flag = dfs(depth+1);
					if(flag) return true;
					visited[i][j] = false;
					result[i][0]++;
					result[j][2]++;
				}
				if(result[i][1] > 0 && result[j][1] > 0) {
					result[i][1]--;
					result[j][1]--;
					visited[i][j] = true;
					flag = dfs(depth+1);
					if(flag) return true;
					visited[i][j] = false;
					result[i][1]++;
					result[j][1]++;
				}
				if(result[i][2] > 0 && result[j][0] > 0) {
					result[i][2]--;
					result[j][0]--;	
					visited[i][j] = true;
					flag = dfs(depth+1);
					if(flag) return true;
					visited[i][j] = false;
					result[i][2]++;
					result[j][0]++;
				}
			}
			if (result[i][0] != 0 || result[i][1] != 0 || result[i][2] != 0) {
				return false;
			}
		}
		
		return false;
	}
}