# [Gold IV] 도시 분할 계획 - 1647 

[문제 링크](https://www.acmicpc.net/problem/1647) 

### 성능 요약

메모리: 331592 KB, 시간: 2200 ms

### 분류

그래프 이론, 최소 스패닝 트리
<details>
 <summary>문제 설명</summary>
 
### 문제

<p>동물원에서 막 탈출한 원숭이 한 마리가 세상구경을 하고 있다. 그러다가 평화로운 마을에 가게 되었는데, 그곳에서는 알 수 없는 일이 벌어지고 있었다.</p>

<p>마을은 N개의 집과 그 집들을 연결하는 M개의 길로 이루어져 있다. 길은 어느 방향으로든지 다닐 수 있는 편리한 길이다. 그리고 각 길마다 길을 유지하는데 드는 유지비가 있다. 임의의 두 집 사이에 경로가 항상 존재한다.</p>

<p>마을의 이장은 마을을 두 개의 분리된 마을로 분할할 계획을 가지고 있다. 마을이 너무 커서 혼자서는 관리할 수 없기 때문이다. 마을을 분할할 때는 각 분리된 마을 안에 집들이 서로 연결되도록 분할해야 한다. 각 분리된 마을 안에 있는 임의의 두 집 사이에 경로가 항상 존재해야 한다는 뜻이다. 마을에는 집이 하나 이상 있어야 한다.</p>

<p>그렇게 마을의 이장은 계획을 세우다가 마을 안에 길이 너무 많다는 생각을 하게 되었다. 일단 분리된 두 마을 사이에 있는 길들은 필요가 없으므로 없앨 수 있다. 그리고 각 분리된 마을 안에서도 임의의 두 집 사이에 경로가 항상 존재하게 하면서 길을 더 없앨 수 있다. 마을의 이장은 위 조건을 만족하도록 길들을 모두 없애고 나머지 길의 유지비의 합을 최소로 하고 싶다. 이것을 구하는 프로그램을 작성하시오.</p>

### 입력 

 <p>첫째 줄에 집의 개수 N, 길의 개수 M이 주어진다. N은 2이상 100,000이하인 정수이고, M은 1이상 1,000,000이하인 정수이다. 그 다음 줄부터 M줄에 걸쳐 길의 정보가 A B C 세 개의 정수로 주어지는데 A번 집과 B번 집을 연결하는 길의 유지비가 C (1 ≤ C ≤ 1,000)라는 뜻이다.</p>

<p>임의의 두 집 사이에 경로가 항상 존재하는 입력만 주어진다.</p>

### 출력 

 <p>첫째 줄에 없애고 남은 길 유지비의 합의 최솟값을 출력한다.</p>
</details>

---

### 접근 아이디어
<p>
 해당 문제에서 결국 해야하는 일은 마을의 길을 최소의 cost만 남겨두고 다 끊어내는 MST를 만드는 것이다.
 마을을 두 구역으로 나누는데에 있어서 한 구역에는 하나 이상의 집만 있으면 되기 때문에 MST를 만드는 도중 연결된 집이 N-1개면 MST를 만드는 것을 종료하고 탈출하는 방식으로 코드를 구현하였다.
</p>

### 작성 코드
```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Edge implements Comparable<Edge>{
		int v;
		int w;
		int cost;
		
		public Edge(int v, int w, int cost) {
			this.v = v;
			this.w = w;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return cost - o.cost;
		}
	}
	
	static int N, M;
	static int[] parent;
	static PriorityQueue<Edge> edge;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N];
		edge = new PriorityQueue<>();
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken());
			
			edge.add(new Edge(a, b, c));
		}
		
		initParent();
		
		int cnt = 1;
		int sum = 0;
		
		while(cnt < N-1) {
			Edge e = edge.poll();
			if(find(e.v) != find(e.w)) {
				union(e.v, e.w);
				sum += e.cost;
				cnt++;
			}
		}
		System.out.println(sum);
	}
	
	static void initParent() {
		for(int i = 0; i<N; i++) {
			parent[i] = i;
		}
	}
	
	static int find(int a) {
		int pa = parent[a];
		if(pa == a) {
			return pa;
		}
		return find(pa);
	}
	
	static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if(pa > pb) {
			parent[pa] = pb;
		}else {
			parent[pb] = pa;
		}
	}

}
```
