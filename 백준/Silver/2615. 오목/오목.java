import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] field = new int[20][20];
	static boolean[][] visited = new boolean[20][20];
	static int[] dr = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dc = { -1, 0, 1, 1, 1, 0, -1, -1 };
	static boolean dflag = false;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("Test5.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int i = 1; i < 20; i++) {
			st = new StringTokenizer(bfr.readLine());
			for (int j = 1; j < 20; j++) {
				field[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int flag = 0;
		for (int i = 1; i < 20; i++) {
			for (int j = 1; j < 20; j++) {
				if (field[i][j] == 0)
					continue;
				for (int d = 2; d <= 5; d++) {
					//6목 체크
					int br = i + dr[(d - 4 + 8) % 8];
					int bc = j + dc[(d - 4 + 8) % 8];
					//지금 가려는 방향 반대쪽이 나랑 똑같으면
					//육목일수도 있음..! 주의..!
					if (!invaild(br, bc)) {
						//System.out.printf("before %d %d %d => ", br, bc, field[br][bc]);
						if (field[br][bc] == field[i][j]) {
							
							dflag = true;
							flag = check(i, j, d, 1);
							dflag = false;
						}
						else {
							flag = check(i, j, d, 0);
						}
					} 
					else {
						flag = check(i, j, d, 0);						
					}
					
					//오목을 찾은 경우
					if (flag != 0) {
						System.out.println(flag);
						System.out.printf("%d %d\n", i, j);
						return;
					}
				}
			}
		}
		//끝까지 오목을 못찾으면 0 출력
		if (flag == 0) {
			System.out.println(flag);
		}
	}

	//범위 안에 있는지 확인하는 함수
	static boolean invaild(int r, int c) {
		if (r <= 0 || r >= 20 || c <= 0 || c >= 20) {
			return true;
		}
		return false;
	}

	static int check(int r, int c, int dir, int depth) {
//		System.out.println(depth);
		if (!dflag && depth == 5) {
//			System.out.println("depth = 5");
			int br = r + dr[(dir - 4 + 8) % 8];
			int bc = c + dc[(dir - 4 + 8) % 8];
			if (!invaild(r, c) && field[br][bc] != field[r][c])
				return field[br][bc];
			else if(invaild(r, c))
				return field[br][bc];
			else
				return 0;
		}
		
		if((dflag && depth == 4) ) {
//			System.out.println("dflag");
			int br = r + dr[(dir - 4 + 8) % 8];
			int bc = c + dc[(dir - 4 + 8) % 8];
			if (field[br][bc] != field[r][c])
				return field[br][bc];
			else
				return 0;		
		}
		

//		System.out.printf("%d %d %d\n", r, c, depth);
		visited[r][c] = true;
		int nr, nc;
		nr = r + dr[dir];
		nc = c + dc[dir];

		if(invaild(nr, nc) && depth == 4) {
			return field[r][c];
		}
		else if (invaild(nr, nc))
			return 0;

//		System.out.printf("new %d %d %d\n", nr, nc, field[nr][nc]);
		if (field[nr][nc] == field[r][c] || depth == 4) {
			visited[nr][nc] = true;
			int flag = check(nr, nc, dir, depth + 1);
			if (flag != 0)
				return flag;
			visited[nr][nc] = false;
		}
		
		visited[r][c] = false;

		return 0;
	}
}
