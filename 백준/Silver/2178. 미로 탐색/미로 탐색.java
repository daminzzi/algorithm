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
		
		field = new char[N][];
		visited = new int[N][M];
		
		for(int i =0; i<N; i++) {
			field[i] = br.readLine().toCharArray();
		}
		
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				visited[i][j] = 0;
			}
		}
		
		System.out.println(bfs(0, 0));
	}
	
	static boolean isPossible(int nr, int nc) {
		if(nr < 0  || nr >=N || nc <0 || nc >= M ) {
			return false;
		}
		if(field[nr][nc] == '0') {
			return false;
		}
		return true;
	}
	
	static int bfs(int r, int c) {
		
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {r, c});
		visited[r][c] = 1;
		int nr, nc;

		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			
			if(now[0] == N-1 && now[1] == M-1) {
				break;
			}
			
			for(int i = 0; i<4; i++) {
				nr = now[0] + direction[i][0];
				nc = now[1] + direction[i][1];
			
				if(!isPossible(nr, nc)) continue;
				if(visited[nr][nc] != 0) continue;
				
				queue.offer(new int[] {nr, nc});
				visited[nr][nc] = visited[now[0]][now[1]]+1;			
			}
		}
		
		
		return visited[N-1][M-1];
	}

}