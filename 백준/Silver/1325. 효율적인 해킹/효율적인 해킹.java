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