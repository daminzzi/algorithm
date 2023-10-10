# [Gold III] 욕심쟁이 판다 - 1937 

[문제 링크](https://www.acmicpc.net/problem/1937) 

### 성능 요약

메모리: 67500 KB, 시간: 476 ms

### 분류

깊이 우선 탐색, 다이나믹 프로그래밍, 그래프 이론, 그래프 탐색

### 제출 일자

2023년 10월 10일 12:44:24

### 문제 설명

<p>n × n의 크기의 대나무 숲이 있다. 욕심쟁이 판다는 어떤 지역에서 대나무를 먹기 시작한다. 그리고 그 곳의 대나무를 다 먹어 치우면 상, 하, 좌, 우 중 한 곳으로 이동을 한다. 그리고 또 그곳에서 대나무를 먹는다. 그런데 단 조건이 있다. 이 판다는 매우 욕심이 많아서 대나무를 먹고 자리를 옮기면 그 옮긴 지역에 그 전 지역보다 대나무가 많이 있어야 한다.</p>

<p>이 판다의 사육사는 이런 판다를 대나무 숲에 풀어 놓아야 하는데, 어떤 지점에 처음에 풀어 놓아야 하고, 어떤 곳으로 이동을 시켜야 판다가 최대한 많은 칸을 방문할 수 있는지 고민에 빠져 있다. 우리의 임무는 이 사육사를 도와주는 것이다. n × n 크기의 대나무 숲이 주어져 있을 때, 이 판다가 최대한 많은 칸을 이동하려면 어떤 경로를 통하여 움직여야 하는지 구하여라.</p>

### 입력 

 <p>첫째 줄에 대나무 숲의 크기 n(1 ≤ n ≤ 500)이 주어진다. 그리고 둘째 줄부터 n+1번째 줄까지 대나무 숲의 정보가 주어진다. 대나무 숲의 정보는 공백을 사이로 두고 각 지역의 대나무의 양이 정수 값으로 주어진다. 대나무의 양은 1,000,000보다 작거나 같은 자연수이다.</p>

### 출력 

 <p>첫째 줄에는 판다가 이동할 수 있는 칸의 수의 최댓값을 출력한다.</p>

---

### 문제 요약
<p> 문제를 읽으며 기억해야하는 것은 이동방향은 상,하,좌,우 네 방향이고, 현재 칸보다 이동하려는 칸의 대나무가 더 많을 때 움직일 수 있다는 점, 목표는 판다가 최대한 많은 칸을 방문할 수 있도록 하는 것
</p>

### 접근 아이디어
<p>
 이 문제를 풀면서 처음 생각한 풀이는 대나무가 적게 있는 칸부터 주변을 돌면서 이동할 수 있는 칸이 있는지 체크하는 것이었다.
 그래서 적게 있는 칸부터 탐색하기 위해서 각각의 위치를 prioirty queue에 대나무 수를 비교하면서 적은 순서대로 집어 넣고 하나씩 꺼내보면서 탐색하는 풀이를 생각했다.
 이렇게 탐색하면 각각의 칸에 대해서 최대 상하좌우 4번 즉 4*N*N 안에 탐색이 끝나므로 N이 최대 500일 때 여유롭게 문제를 통과할 수 있을 것이라고 생각했다.
 또한 대나무가 적게 있는 칸부터 탐색을 진행하기 때문에 i번째 칸을 탐색할 때 그 주변에 i번째 칸보다 적은 수의 대나무가 있었을 경우 해당 칸은 이미 최대의 경로를 통해서 한번은 방문을 한 상태임을 예상할 수 있다.
 (그리디한 식으로 문제를 해결했다고 생각할 수 있을 것 같음.)

 하지만 해당 첫번째 풀이보다 더 적은 시간이 걸린 다른 코드들이 많았고, 어떤 부분에서 시간이 오래 걸렸는지 다시 한 번 살펴봐야했다.
 다른 코드를 참고해봤을 때 dp를 사용한 풀이가 더 적은 시간이 걸림을 확인할 수 있었고, dfs와 dp를 합쳐서 적용하는 풀이를 적용해서 문제를 다시 풀어봤다.
 먼저 방문 여부를 체크하기 위해서 int 이차원 배열을 사용했는데, 이 int 배열은 각 칸이 최대 몇개의 칸을 방문할 수 있는지를 저장하고 있다.
 그래서 출발한 칸에서 접근할 수 있는 모든 칸에 대하여 몇 개의 칸을 방문할 수 있는지를 저장하고 있으므로 이미 방문을 한 칸의 경우 더 이상 탐색을 진행하지 않도록 한다.

 dp를 사용한 코드가 더 빠른 이유는, pq를 이용해서 문제를 해결할 때는 시간복잡도가 pq 삽입, 각 노드 탐색으로 O(N*N*logN)인데, dp의 경우 dfs는 이미 방문한 칸은 방문하지 않기 때문에 O(N*N)에서 끝낼 수 있다.
</p> 

### 작성 코드
- prioity queue를 이용한 코드
```java
package algorithm_study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1937_욕심쟁이판다 {
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
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new int[N][N];
		StringTokenizer st;
		PriorityQueue<Bamboo> queue = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				queue.add(new Bamboo(i, j, map[i][j]));
			}
		}
		int max = 0;
		int step = 0;
		while(!queue.isEmpty()) {
			Bamboo now = queue.poll();
			
			for(int d = 0; d<4; d++) {
				int nr = now.r+dir[d][0];
				int nc = now.c+dir[d][1];
//				System.out.println(nr+" "+nc+" "+(step++));
				if(!isPossible(nr, nc)) continue;
				if(map[nr][nc] <= map[now.r][now.c]) continue;
				if(dp[nr][nc] >= dp[now.r][now.c] +1) continue;
				dp[nr][nc] = dp[now.r][now.c] +1;
				if(max < dp[nr][nc]) {
					max = dp[nr][nc];
				}
			}
		}
		
		System.out.println(max+1);
	}

	static boolean isPossible(int nr, int nc) {
		if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
			return false;
		}
		return true;
	}
}
```

- dfs+dp를 이용한 코드
```java
package algorithm_study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1937_욕심쟁이판다_dp {
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
			return w - o.w;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return r + " " + c + " " + w;
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
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int max = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				max = Math.max(max, findMax(i, j));
			}
		}

		System.out.println(max);
	}

	static int step = 0;

	static int findMax(int i, int j) {
		if (visited[i][j] != 0) {
			return visited[i][j];
		}

		int max = 0;

		for (int d = 0; d < 4; d++) {
			int nr = i + dir[d][0];
			int nc = j + dir[d][1];
			if (isPossible(nr, nc) && map[nr][nc] > map[i][j]) {
				max = Math.max(max, findMax(nr, nc));
			}
		}

		return visited[i][j] = max + 1;
	}

	static boolean isPossible(int nr, int nc) {
		if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
			return false;
		}
		return true;
	}
}
```
