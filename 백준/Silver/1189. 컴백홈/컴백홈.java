import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C, K;
    static boolean[][] map;
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

        boolean[][] visited = new boolean[R][C];
        visited[R-1][0] = true;
        System.out.println(dfs(R-1, 0, 1, visited));
    }

    static boolean isPossible(int r, int c){
        return r>=0 && r<R && c>=0 && c<C && map[r][c];
    }

    static int dfs(int r, int c, int d, boolean[][] visited){
        if(r == 0 && c == C-1){
            return d == K? 1:0;
        }

        int cnt = 0;
        boolean[][] nVisited = new boolean[R][C];
        for(int s = 0; s<R; s++){
            System.arraycopy(visited[s], 0, nVisited[s], 0, C);
        }
        for(int i = 0; i<4; i++){
            int nr = r + direction[i][0];
            int nc = c + direction[i][1];

            if(isPossible(nr, nc) && !visited[nr][nc]){
                nVisited[nr][nc] = true;
                cnt += dfs(nr, nc, d+1, nVisited);
                nVisited[nr][nc] = false;
            }
        }
        return cnt;
    }
}