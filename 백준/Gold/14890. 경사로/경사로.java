import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] rows;
    static int[][] cols;
    static int N, L;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        rows = new int[N][N];
        cols = new int[N][N];

        for(int r = 0; r < N; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++){
                int temp = Integer.parseInt(st.nextToken());
                rows[r][c] = temp;
                cols[c][r] = temp;
            }
        }

        int cnt = 0;
        for(int i = 0; i<N; i++){
            if(isPossible(rows[i]))
                cnt++;
            if (isPossible(cols[i]))
                cnt++;
        }

        System.out.println(cnt);
    }

    private static boolean isPossible(int[] road){
        int beforeHeight = road[0];
        int count = 1;
        for (int j = 1; j < N; j++) {
            // 같은 높이
            if (beforeHeight == road[j]) {
                count++;
            }

            // 이전 칸이 한 칸 낮음
            else if (beforeHeight + 1 == road[j]) {
                if(count < L) return false;
                beforeHeight++;
                count = 1;
            }

            // 이전 칸이 한 칸 높음
            else if (beforeHeight - 1 == road[j]) {
                // 앞으로 평지의 수를 세어야 한다.
                int remain = 0;
                for(int k = j; k < N; k++) {
                    if(beforeHeight-1 != road[k]) break;
                    remain++;
                }
                if(remain < L) return false;

                // 설치 가능
                j += L-1;
                count = 0;
                beforeHeight--;
            }

            // 높이 차이 2이상
            else {
                return false;
            }
        }
        return true;
    }
}