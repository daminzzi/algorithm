import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final int MOD = 10_007;
    static int N;
    static int[][] dp;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        int sum = combi(10+N-1, N);

        System.out.println(sum);
    }

    static int combi(int n, int r) {
        dp = new int[n+1][r+1];
        for(int i = 0; i<=n; i++) {
            for(int j = 0; j<=i && j<=r;j++) {
                if(j == 0 || j == i) {
                    dp[i][j] = 1;
                    continue;
                }
                dp[i][j] = (dp[i-1][j-1]%MOD+dp[i-1][j]%MOD)%MOD;
            }
        }
        return dp[n][r];
    }
}