import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int max = -1;
	static int[][] field;
	static boolean[][] visited;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		field = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) {
				field[i][j] = Integer.parseInt(st.nextToken());
				visited[i][j] = false;
			}
		}
		
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				visited[i][j] = true;
				dfs(i, j, field[i][j], 1);
				visited[i][j] = false;
			}
		}
		
		System.out.println(max);
		
	}
	
	static void dfs(int r, int c, int sum, int depth) {
		if(depth == 4) {
			max = Math.max(sum, max);
			return;
		}
		int nr, nc;
		for(int i = 0; i<4; i++) {
			nr = r+dr[i];
			nc = c+dc[i];
			
			if(nr < 0 || nr >=N || nc < 0 || nc >= M || visited[nr][nc]) {
				continue;
			}
			
			if(depth == 2) {
				visited[nr][nc] = true;
				dfs(r, c, sum+field[nr][nc], depth+1);
				visited[nr][nc] = false;
			}
			
			visited[nr][nc] = true;
			dfs(nr, nc, sum+field[nr][nc], depth+1);
			visited[nr][nc] = false;
		}
	}

}