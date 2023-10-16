# [Silver I] 전쟁 - 전투 - 1303 

[문제 링크](https://www.acmicpc.net/problem/1303) 

### 성능 요약

메모리: 12012 KB, 시간: 92 ms

### 분류

너비 우선 탐색, 깊이 우선 탐색, 그래프 이론, 그래프 탐색

### 제출 일자

2023년 10월 16일 23:50:58

### 문제 설명

<p>전쟁은 어느덧 전면전이 시작되었다. 결국 전투는 난전이 되었고, 우리 병사와 적국 병사가 섞여 싸우게 되었다. 그러나 당신의 병사들은 흰색 옷을 입고, 적국의 병사들은 파란색 옷을 입었기 때문에 서로가 적인지 아군인지는 구분할 수 있다. 문제는 같은 팀의 병사들은 모이면 모일수록 강해진다는 사실이다.</p>

<p>N명이 뭉쳐있을 때는 N<sup>2</sup>의 위력을 낼 수 있다. 과연 지금 난전의 상황에서는 누가 승리할 것인가? 단, 같은 팀의 병사들이 대각선으로만 인접한 경우는 뭉쳐 있다고 보지 않는다.</p>

### 입력 

 <p>첫째 줄에는 전쟁터의 가로 크기 N, 세로 크기 M(1 ≤ N, M ≤ 100)이 주어진다. 그 다음 두 번째 줄에서 M+1번째 줄에는 각각 (X, Y)에 있는 병사들의 옷색이 띄어쓰기 없이 주어진다. 모든 자리에는 병사가 한 명 있다. B는 파란색, W는 흰색이다. 당신의 병사와 적국의 병사는 한 명 이상 존재한다.</p>

### 출력 

 <p>첫 번째 줄에 당신의 병사의 위력의 합과 적국의 병사의 위력의 합을 출력한다.</p>

---

### 문제 요약

1. 병사들이 뭉쳐있는 그룹을 찾아야 한다. 
    - 같은 나라의 병사끼리 상하좌우로 이웃하는 경우 하나의 그룹으로 묶을 수 있다.
    - 하나의 그룹에 N명이 뭉쳐있을 때의 위력은 N^2이다
2. 각 나라의 위력의 합을 각각 출력한다.

### 접근 아이디어

1. 한 병사를 골랐을 때 해당 병사가 이웃한 병사들 중 같은 나라의 병사가 있는지 확인해야함 → bfs 방식을 이용해서 탐색
2. 탐색하면서 해당 그룹에 몇 명이 속해있는지 구하고 이를 각 나라의 위력을 계산하는데 더함

### 구현 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1303_전쟁전투 {

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
```
