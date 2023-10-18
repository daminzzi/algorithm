import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[] x = new int[3];
		int[] y = new int[3];
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}

		int a = y[0] - y[2];
		int b = y[0] * (x[0] - x[2]) - a * x[0];
		int tX = a * x[1] + b;
		int tY = y[1] * (x[0] - x[2]);
		// System.out.printf("%d*y=%d*x+%d\n", x[0]-x[2], a, b);
		// System.out.println(tY+" "+tX);

		if (tY == tX) { //일직선
			System.out.println(0);
			return;
		} else if (tY < tX) { // 직선 값이 실제 y값보다 큼
			System.out.println(-1);
			return;
		} else { //직선 값이 실제 y값보다 작음
			System.out.println(1);
			return;
		}

	}

}
