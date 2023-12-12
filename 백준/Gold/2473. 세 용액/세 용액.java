import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        Long[] solution = new Long[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            solution[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(solution);

        if (N == 3) {
            for (int i = 0; i < N; i++) {
                sb.append(solution[i]).append(' ');
            }
            System.out.println(sb);
            return;
        }

        Long comp = Long.MAX_VALUE;
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
            for (int i = 0; i < N - 2; i++) { //시작 수
                int lo = i + 1;
                int hi = N - 1;
                Long temp;
                while (lo < hi) {
                    temp = solution[i] + solution[lo] + solution[hi];

                    if (temp == 0) {
                        System.out.println(solution[i] + " " + solution[lo] + " " + solution[hi]);
                        return;
                    }

                    if (Math.abs(temp) < comp) {
                        ans1 = i;
                        ans2 = lo;
                        ans3 = hi;
                        comp = Math.abs(Math.abs(temp));
                    }

                    if (temp < 0) {
                        lo++;
                    } else {
                        hi--;
                    }
                }
            }
        }

        System.out.println(solution[ans1] + " " + solution[ans2] + " " + solution[ans3]);
    }
}