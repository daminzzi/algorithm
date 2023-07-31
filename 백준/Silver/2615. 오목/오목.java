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
		//////////////////////////////////////////////////////////////
		// 테스트 후 아래 파일 입력을 표준입력으로 처리하는 문장은 주석 처리해주세요!!!! ( System.setIn처리 코드 )
		//////////////////////////////////////////////////////////////
		//System.setIn(new FileInputStream("Test5.txt"));

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 1; i < 20; i++) {
			st = new StringTokenizer(bfr.readLine());
			for (int j = 1; j < 20; j++) {
				field[i][j] = Integer.parseInt(st.nextToken());
			}
		}

//		for (int i = 1; i < 20; i++) {
//			for (int j = 1; j < 20; j++) {
//				System.out.printf("%b ", visited[i][j]);
//			}
//			System.out.println();
//		}

		int flag = 0;
		for (int i = 1; i < 20; i++) {
			for (int j = 1; j < 20; j++) {
				if (field[i][j] == 0)
					continue;
				for (int d = 2; d <= 5; d++) {
					//visited[i][j] = true;
//					System.out.printf("now %d %d d%d\n", i, j, d);
//					System.out.printf("%d %d\n", dr[d], dc[d]);
//					System.out.printf("%d %d\n", dr[(d-4+8)%8], dc[(d-4+8)%8]);
					int br = i + dr[(d - 4 + 8) % 8];
					int bc = j + dc[(d - 4 + 8) % 8];
//					System.out.printf("%d %d\n", br, bc);
					
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
					
					if (flag != 0) {
						System.out.println(flag);
						System.out.printf("%d %d\n", i, j);
						return;
					}
				}
			}
		}
		if (flag == 0) {
			System.out.println(flag);
		}
	}

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
//			System.out.printf("dflag");
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
