import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Bamboo implements Comparable<Bamboo> {
		int r, c, w;
		
		public Bamboo(int r, int c, int w) {
			super();
			this.r = r;
			this.c = c;
			this.w = w;
		}
		
		@Override
		public int compareTo(Bamboo o) {
			return w-o.w;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return r+" "+c+" "+w;
		}
		
	}
	
	static int[][] dir = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };
	static int N;
	static int[][] map;
	static int[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new int[N][N];
		StringTokenizer st;
		PriorityQueue<Bamboo> queue = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
//				queue.add(new Bamboo(i, j, map[i][j]));
			}
		}
		
		int max = 0;
	
//		while(!queue.isEmpty()) {
//			Bamboo now = queue.poll();
//			
//			for(int d = 0; d<4; d++) {
//				int nr = now.r+dir[d][0];
//				int nc = now.c+dir[d][1];
//				if(!isPossible(nr, nc)) continue;
//				if(map[nr][nc] <= map[now.r][now.c]) continue;
//				if(visited[nr][nc] >= visited[now.r][now.c] +1) continue;
//				visited[nr][nc] = visited[now.r][now.c] +1;
//				if(max < visited[nr][nc]) {
//					max = visited[nr][nc];
//				}
//			}
//		}
		
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				max = Math.max(max, findMax(i, j));
			}
		}

//		for(int i = 0; i<N; i++) {
//			for(int j = 0; j<N; j++) {
//				System.out.print(visited[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		System.out.println(max);
	}

	static int findMax(int i, int j) {
		if(visited[i][j] != 0) {
			return visited[i][j];
		}
		
		int max = 0;
		
		for(int d = 0; d<4; d++) {
			int nr = i+dir[d][0];
			int nc = j+dir[d][1];
			if(isPossible(nr, nc) && map[nr][nc] > map[i][j]) {
				max = Math.max(max, findMax(nr, nc));
			}
		}
		
		return visited[i][j] = max+1;
	}
	
	static boolean isPossible(int nr, int nc) {
		if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
			return false;
		}
		return true;
	}
}