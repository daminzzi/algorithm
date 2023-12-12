import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        long[] solution = new long[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++)
            solution[i] = Long.parseLong(st.nextToken());

        Arrays.sort(solution);

        if (N == 3) {
            System.out.println(solution[0] + " " + solution[1] + " " + solution[2]);
            return;
        }

        long comp = Long.MAX_VALUE;
        int ans1 = 0, ans2 = 0, ans3 = 0;
        if (solution[0] >= 0) {
            ans1 = 0;
            ans2 = 1;
            ans3 = 2;
        } else if (solution[N - 1] <= 0) {
            ans1 = N - 3;
            ans2 = N - 2;
            ans3 = N - 1;
        } else {
            long temp;
            for (int i = 0; i < N - 2; i++) { //시작 수
                int lo = i + 1;
                int hi = N - 1;
                while (lo < hi) {
                    temp = solution[i] + solution[lo] + solution[hi];

                    if (Math.abs(temp) < comp) {
                        ans1 = i;
                        ans2 = lo;
                        ans3 = hi;
                        comp = Math.abs(Math.abs(temp));
                    }

                    if (temp < 0) {
                        lo++;
                    } else if (temp > 0) {
                        hi--;
                    } else {
                        System.out.println(solution[i] + " " + solution[lo] + " " + solution[hi]);
                        return;
                    }
                }
            }
        }

        System.out.println(solution[ans1] + " " + solution[ans2] + " " + solution[ans3]);
    }
}