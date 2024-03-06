import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C, K;
    static int res;
    static boolean[][] map;
    static boolean[][] visited;
    static int[][] direction = new int[][] {
            {0, 1}, {0, -1}, {1, 0}, {-1, 0},
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new boolean[R][C];
        for(int i = 0; i<R; i++){
            char[] str = br.readLine().toCharArray();
            for(int j = 0; j<C; j++){
                if(str[j] == '.')
                    map[i][j] = true;
            }
        }

        visited = new boolean[R][C];
        visited[R-1][0] = true;
        dfs(R-1, 0, 1);
        System.out.println(res);
    }

    static boolean isPossible(int r, int c){
        return r>=0 && r<R && c>=0 && c<C && map[r][c];
    }

    static void dfs(int r, int c, int d){
        if(r == 0 && c == C-1){
            if(d == K) res++;
            return;
        }

        for(int i = 0; i<4; i++){
            int nr = r + direction[i][0];
            int nc = c + direction[i][1];

            if(isPossible(nr, nc) && !visited[nr][nc]){
                visited[nr][nc] = true;
                dfs(nr, nc, d+1);
                visited[nr][nc] = false;
            }
        }
    }
}