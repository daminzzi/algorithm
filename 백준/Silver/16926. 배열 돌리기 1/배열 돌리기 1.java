import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] field;
	static int[][] after;
	static int N, M, R; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		field = new int[N][M];
		after = new int[N][M];
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) {
				field[i][j] = Integer.parseInt(st.nextToken());
				after[i][j] = field[i][j];
			}
		}
		
		int min = Math.min(N, M);
		int rotate, sr, er, sc, ec;
		for(int i = 0; i<min/2; i++) {
			rotate = R % ((N-1-i*2+M-1-i*2)*2);
			sr = i; sc = i;
			er = N-i-1; ec = M-i-1;
			
			while(rotate-- > 0) {
				fieldRotate(sr, er, sc, ec);
			}
		}
		
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				sb.append(field[i][j]).append(' ');
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
	}
	
	static void fieldRotate(int sr, int er, int sc, int ec) {
		for(int i = sr; i < er; i++) {
			after[i+1][sc] = field[i][sc]; 
		}
		
		for(int i = sc; i<ec; i++) {
			after[er][i+1] = field[er][i];
		}
		
		for(int i = er; i>sr; i--) {
			after[i-1][ec] = field[i][ec]; 
		}
		
		for(int i = ec; i> sc; i--) {
			after[sr][i-1] = field[sr][i];
		}
			
		
		for(int j = 0; j<N; j++) {
			for(int k = 0; k<M; k++) {
				field[j][k] = after[j][k];
			}
		}	
	}

}