import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
	int w;
	int cost;

	Edge(int w, int cost) {
		this.w = w;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}
}

public class Main {
	static int N, M;
	static List<Edge>[] graph;
	static int max = 0;
	static int total = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		total = 0;
		graph = new ArrayList[N + 1];
		for (int i = 0; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}

		int v, w, c;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			graph[v].add(new Edge(w, c));
			graph[w].add(new Edge(v, c));
			total += c;
		}

		System.out.println(prim(1, N));
	}

	static int prim(int start, int n) {
		boolean[] visited = new boolean[n + 1];

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(start, 0));

		int t = 0;
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			int v = edge.w;
			int cost = edge.cost;

			if (visited[v])
				continue;

			visited[v] = true;
			t += cost;

			for (Edge e : graph[v]) {
				if (!visited[e.w]) {
					pq.add(e);
				}
			}
		}

		return t;
	}

}