import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		int M = Integer.parseInt(br.readLine());
		
		int[][] visited = new int[N+1][N+1];
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			visited[v][w] = 1;
			visited[w][v] = -1;
		}
		
		for(int j = 1; j<=N; j++) {
			for(int i = 1; i<=N; i++) {
				for(int k = 1; k<=N; k++) {
					if(visited[i][j]== 0 || visited[j][k] == 0) continue;
					visited[i][k] = visited[i][j] == visited[j][k]? visited[i][j]:visited[i][k];
				}
			}
		}
		
		//결과 출력
		for(int i = 1; i<=N; i++) {
			int cnt = 0;
			for(int j = 1; j<=N; j++) {
				if(i==j)
					continue;
				if(visited[i][j] == 0)
					cnt++;
			}
			sb.append(cnt).append('\n');
		}
		System.out.println(sb);
	}

}