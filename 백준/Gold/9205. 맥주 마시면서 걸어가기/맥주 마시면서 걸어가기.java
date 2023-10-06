import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static class Pair {
		int x;
		int y;

		Pair() {
		}

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		int distance(Pair p) {
			return Math.abs(x - p.x) + Math.abs(y - p.y);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			Pair[] location = new Pair[N + 2];
			for (int i = 0; i < N + 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				location[i] = new Pair(x, y);
			}
			int[][] dist = new int[N + 2][N + 2];
			for (int i = 0; i < N + 2; i++) {
				for (int j = 0; j < N + 2; j++) {
					dist[i][j] = 100_000_000;
					if (i == j) {
						dist[i][j] = 0;
						continue;
					}
					int d = location[i].distance(location[j]);
					if (d <= 50 * 20) {
						dist[i][j] = location[i].distance(location[j]);
						dist[j][i] = location[i].distance(location[j]);
					}
				}
			}
			
			for(int i = 0; i<N+2; i++) {
				for(int j = 0; j<N+2; j++) {
					for(int k = 0; k<N+2; k++) {
						dist[i][k] = Math.min(dist[i][k], dist[i][j]+dist[j][k]);
						dist[k][i] = dist[i][k];
					}
				}
			}
			
//			for(int i = 0; i<N+2; i++) {
//				for(int j = 0; j<N+2; j++) {
//					System.out.print(dist[i][j]+" ");
//				}
//				System.out.println();
//			}
			
			if(dist[0][N+1]<100_000_000) {
				sb.append("happy\n");
			}else {
				sb.append("sad\n");
			}
		}
		System.out.println(sb);
	}

}