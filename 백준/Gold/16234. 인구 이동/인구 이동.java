import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, L, R;
	static int[][] A;
	static int[][] united;
	
	static int[][] direction = {
			{0, 1}, {1, 0}, {-1, 0}, {0, -1}
	};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		A = new int[N][N];
		united = new int[N][N];
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				united[i][j] = -1;
			}
		}
		
		
		int day = 0;
		while(true) {						
			if(!simulation()) break;
			day++;
		}
		
		System.out.println(day);
	}
	
	static boolean simulation() {
		int[] uNum = new int[N*N];
		int uCnt = -1; //해당 날짜의 simulation에서 생성되는 연합의 수
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				if(united[i][j] == -1) {
					int c = bfs(i, j, ++uCnt);
					uNum[uCnt] = c;
				}
			}
		}
		
		if(uCnt == (N*N-1)) {
			return false;
		}
				
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				A[i][j] = uNum[united[i][j]];
				united[i][j] = -1;
			}
		}
		
		return true;
	}
	
	static int bfs(int r, int c, int uCnt) { //하나의 연합에 속해있는 나라를 찾고 sum을 계산
		int cnt = 0; //연합 안에 있는 나라 수
		int sum = 0; //연합에 속해 있는 모든 인구 수
		Queue<int[]> queue = new LinkedList<>();
		
		queue.offer(new int[] {r,c});
		united[r][c] = uCnt;
		cnt++;
		sum += A[r][c];
		
		int nr, nc;
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			for(int i = 0; i<4; i++) {
				nr = now[0] + direction[i][0];
				nc = now[1] + direction[i][1];
				if(nr < 0 || nr >= N || nc < 0 || nc >= N || united[nr][nc] != -1) {
					continue;
				}
				int differ = Math.abs(A[nr][nc] - A[now[0]][now[1]]);
				if(differ>=L && differ<=R) {
					queue.offer(new int[] {nr, nc});
					united[nr][nc] = uCnt;
					cnt++;
					sum += A[nr][nc];
				}
			}
		}
		
		return sum/cnt;
	}
}