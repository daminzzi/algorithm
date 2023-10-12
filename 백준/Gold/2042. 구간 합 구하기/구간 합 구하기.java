import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static StringBuilder sb = new StringBuilder();

    private static int N, M, K;
    private static long[] list;
    private static FenWick fenWick;

    static class FenWick {
        private long[] data;

        public FenWick(int size) {
            this.data = new long[size];
        }

        private void update(int idx, long num) {
            while (idx < this.data.length) {
                this.data[idx] += num;
                idx += (idx & -idx);
            }
        }

        private long sum(int idx) {
            long allSum = 0;
            while (idx > 0) {
                allSum += this.data[idx];
                idx -= (idx & -idx);
            }
            return allSum;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 초기화
        list = new long[N + 1];
        fenWick = new FenWick(N + 1);

        // N개의 수가 주어짐
        for (int i = 1; i <= N; i++) {
            long input = Long.parseLong(br.readLine());

            fenWick.update(i, input);
            list[i] = input;
        }

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());

            // a가 1인 경우 b번째 수를 c로 바꾸고
            if (Integer.parseInt(st.nextToken()) == 1) {
                int idx = Integer.parseInt(st.nextToken());
                long input = Long.parseLong(st.nextToken());

                fenWick.update(idx, input - list[idx]);

                list[idx] = input;
            }
            // a가 2인 경우에는 b번째 수부터 c번째 수까지의 합을 구하여 출력
            else {
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());

                sb.append((fenWick.sum(right) - fenWick.sum(left - 1)) + "\n");
            }
        }

        System.out.println(sb.toString());
    }
}