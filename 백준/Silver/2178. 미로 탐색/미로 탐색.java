import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static char[][] field;
	static int[][] visited;
	static int[][] direction = {
			{0, 1}, {0, -1}, {1, 0}, {-1, 0}
	};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		field = new char[N][M];
		visited = new int[N][M];
		
		for(int i =0; i<N; i++) {
			String s = br.readLine();
			for(int j = 0; j<M; j++) {
				field[i][j] = s.charAt(j);
				visited[i][j] = -1;
			}
		}
		
		bfs(0, 0);
		System.out.println(visited[N-1][M-1]);
	}
	
	static boolean isPossible(int nr, int nc) {
		if(nr < 0  || nr >= N || nc <0 || nc >= M ) {
			return false;
		}
		if(field[nr][nc] == '0') {
			return false;
		}
		return true;
	}
	
	static void bfs(int r, int c) {
		
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {r, c});
		visited[r][c] = 1;
		int nr, nc;
		int[] now;
		
		while(!queue.isEmpty()) {
			now = queue.poll();
			r = now[0];
			c = now[1];
			if(r == N-1 && c == M-1) {
				break;
			}
			
			for(int i = 0; i<4; i++) {
				nr = r + direction[i][0];
				nc = c + direction[i][1];
			
				if(!isPossible(nr, nc)) continue;
				if(visited[nr][nc] != -1) continue;
				if(nr == N-1 && nc == M-1) {
					visited[nr][nc] = visited[r][c]+1;
					return;
				}
				queue.offer(new int[] {nr, nc});
				visited[nr][nc] = visited[r][c]+1;			
			}
		}
	}

}