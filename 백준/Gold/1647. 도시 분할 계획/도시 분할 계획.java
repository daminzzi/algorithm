import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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
	static Edge[] edge;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N];
		edge = new Edge[M];
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken());
			
			edge[i] = new Edge(a, b, c);
		}
		
		Arrays.sort(edge);
		
		initParent();
		
		int cnt = 1;
		int sum = 0;
		for(int i = 0; i<M; i++) {
			if(cnt == N-1) {
				break;
			}
			if(find(edge[i].v) != find(edge[i].w)) {
				union(edge[i].v, edge[i].w);
				sum += edge[i].cost;
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
		return parent[a] = find(parent[a]);
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
