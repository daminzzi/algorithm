import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, C, W;
	static int arr[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		int max = 0;
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, arr[i]);
		}

		long res = 0;
		for (int i = 1; i <= 10000; i++) {
			long sum = 0;
			for (int j = 0; j < N; j++) {
				int cut = arr[j] / i;
				int cost = (int) ((arr[j] - 0.5) / i);
				if (i*cut*W - C*cost > 0)
					sum += i*cut*W - C*cost;
			}
			res = Math.max(sum, res);
		}
		System.out.println(res);
	}
}