import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int R, C, cnt;
	static char[][] field;
	
	static int[] direction = {-1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		field = new char[R][C];
		
		for(int i = 0; i<R; i++) {
			field[i] = br.readLine().toCharArray();
		}
		cnt = 0;
		
		for(int i = 0; i<R; i++) {
			dfs(i, 0);
		}
		
		System.out.println(cnt);
	}
	
	static boolean dfs(int r, int c) {		
		if(c == C-1) {
			cnt++;
			return true;
		}
		
		int nr, nc;
		
		for(int i = 0; i<3; i++) {
			nr = r+direction[i];
			nc = c+1;
			if(nr < 0 || nr>= R || nc <0 || nc>=C || field[nr][nc] != '.') {
				continue;
			}
			field[nr][nc] = '-';
			if(dfs(nr, nc)) {
				return true;
			}
		}
		
		return false;
	}
}