import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int W, H;
	static char[][] map;
	static boolean[][] visited;

	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new char[H][W];
		visited = new boolean[H][W];

		for (int i = 0; i < H; i++) {
			map[i] = br.readLine().toCharArray();
		}
		int white = 0, blue = 0;

		Queue<int[]> queue = new ArrayDeque<>();
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++) {
				if (visited[r][c])
					continue;
				int cnt = 1;
				queue.clear();
				queue.add(new int[] { r, c });
				visited[r][c] = true;
				while (!queue.isEmpty()) {
					int[] now = queue.poll();
					for (int d = 0; d < 4; d++) {
						int nr = now[0]+dir[d][0];
						int nc = now[1]+dir[d][1];
						if(isPossible(nr, nc) &&!visited[nr][nc]&& map[nr][nc] == map[now[0]][now[1]]) {
							cnt++;
							visited[nr][nc] = true;
							queue.add(new int[] {nr, nc});
						}
					}
				}
				if(map[r][c] == 'W') {
					white+=(cnt*cnt);
				}else {
					blue+=(cnt*cnt);
				}
				
			}
		}
		System.out.println(white+" "+blue);

	}

	public static boolean isPossible(int nr, int nc) {
		if (nr < 0 || nr >= H || nc < 0 || nc >= W) {
			return false;
		}
		return true;
	}
}