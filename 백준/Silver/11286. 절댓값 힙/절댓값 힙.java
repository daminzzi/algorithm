import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer a, Integer b) {
				int A = Math.abs(a);
				int B = Math.abs(b);
				if (A == B) {
					if (a > b)
						return 1;
					else
						return -1;
				} else
					return A - B;
			}
		});

		int t;
		while (N-- > 0) {
			t = Integer.parseInt(br.readLine());
			if (t == 0) {
				if (!pq.isEmpty()) {
					sb.append(pq.poll()).append('\n');
				} else {
					sb.append("0\n");
				}
			} else {
				pq.offer(t);
			}
		}

		System.out.println(sb);
	}

}