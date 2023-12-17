import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] lis;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] line = new int[N];
        lis = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++) {
            line[i] = Integer.parseInt(st.nextToken());
        }

        int len = 0; // 최장증가 부분 수열 길이
        int idx = 0; // 삽입 위치
        for(int i = 0; i<N; i++) {
            if(line[i] > lis[len]) {
                len++;
                lis[len] = line[i];
                continue;
            }

            idx = lower_bound(-1, len, line[i]);
            lis[idx] = line[i];
        }

        System.out.println(N - len);
    }

    static int lower_bound(int left, int right, int key) {
        while(left+1<right) {
            int mid = (left+right)/2;
            if(lis[mid]> key) {
                right = mid;
            }else {
                left = mid;
            }
        }
        return right;
    }
}