import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		int M = Integer.parseInt(br.readLine());
		
		boolean[][] visited = new boolean[N][N];
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken())-1;
			int w = Integer.parseInt(st.nextToken())-1;
			visited[v][w] = true;
		}
		
		for(int j = 0; j<N; j++) {
			for(int i = 0; i<N; i++) {
				for(int k = 0; k<N; k++) {
					if(!visited[i][j] || !visited[j][k]) continue;
					visited[i][k] = visited[i][j] == visited[j][k]? visited[i][j]:visited[i][k];
				}
			}
		}
		
		//결과 출력
		int cnt;
		for(int i = 0; i<N; i++) {
			cnt = N-1;
			for(int j = 0; j<N; j++) {
				if(i==j)
					continue;
				if(visited[i][j] || visited[j][i])
					cnt--;
			}
			sb.append(cnt).append('\n');
		}
		System.out.println(sb);
	}

}