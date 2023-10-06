# [Silver I] 효율적인 해킹 - 1325 

[문제 링크](https://www.acmicpc.net/problem/1325) 

### 성능 요약

메모리: 303172 KB, 시간: 6848 ms

### 분류

너비 우선 탐색, 깊이 우선 탐색, 그래프 이론, 그래프 탐색

### 문제 설명

<p>해커 김지민은 잘 알려진 어느 회사를 해킹하려고 한다. 이 회사는 N개의 컴퓨터로 이루어져 있다. 김지민은 귀찮기 때문에, 한 번의 해킹으로 여러 개의 컴퓨터를 해킹 할 수 있는 컴퓨터를 해킹하려고 한다.</p>

<p>이 회사의 컴퓨터는 신뢰하는 관계와, 신뢰하지 않는 관계로 이루어져 있는데, A가 B를 신뢰하는 경우에는 B를 해킹하면, A도 해킹할 수 있다는 소리다.</p>

<p>이 회사의 컴퓨터의 신뢰하는 관계가 주어졌을 때, 한 번에 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터의 번호를 출력하는 프로그램을 작성하시오.</p>

### 입력 

 <p>첫째 줄에, N과 M이 들어온다. N은 10,000보다 작거나 같은 자연수, M은 100,000보다 작거나 같은 자연수이다. 둘째 줄부터 M개의 줄에 신뢰하는 관계가 A B와 같은 형식으로 들어오며, "A가 B를 신뢰한다"를 의미한다. 컴퓨터는 1번부터 N번까지 번호가 하나씩 매겨져 있다.</p>

### 출력 

 <p>첫째 줄에, 김지민이 한 번에 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터의 번호를 오름차순으로 출력한다.</p>

 ---

 ### 문제 요약
 단방향 그래프에서 방문되는 정점이 가장 많은 점들을 찾는다.
 근데 진짜 이왜실? 문제가 이상한거같음.

 ### 작성 아이디어
 bfs를 통해서 한 그래프에서 방문할 수 있는 모든 정점들에 대해서 탐색을 진행하고
 방문할 수 있는 정점에 내가 방문했다고 알려주기

 ### 작성 코드
 ```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, max;
	static int[] result;
	static List<Integer>[] edge;
	static boolean[] visited;

	static class Node {
		int to;
		Node next;

		public Node(int to, Node next) {
			this.to = to;
			this.next = next;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		result = new int[N];
		edge = new ArrayList[N];
//		max = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			edge[i] = new ArrayList<Integer>();	
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			edge[a].add(b);
		}

		for (int i = 0; i < N; i++) {
			visited = new boolean[N];
			bfs(i);
		}
		for (int i = 0; i < N; i++) {
			max=Math.max(max, result[i]);
		}

		for (int i = 0; i < N; i++) {
			//System.out.println(result[i]+" ");
			if (result[i] == max) {
				sb.append(i + 1).append(" ");
			}
		}

		System.out.println(sb.toString());
	}

	static void bfs(int i) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(i);
		visited[i] = true;

		while (!queue.isEmpty()) {
			int now = queue.remove();

			for (int e : edge[now]) {
				if (!visited[e]) {
					queue.add(e);
					visited[e] = true;
					result[e]++;
				}
			}
		}
	}
}
```

