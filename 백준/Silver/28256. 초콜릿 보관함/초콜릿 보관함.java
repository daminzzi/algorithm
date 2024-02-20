import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] dir = {
        {0, 1}, {0, -1}, {1, 0}, {-1, 0}
    };

    static boolean isPossible(int r, int c){
        return r >= 0 && r < 3 && c >= 0 && c < 3;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
tc:         while(T-- > 0) {
            char[][] box = new char[3][3];
            boolean[][] visited = new boolean[3][3];
            int group = 0;
            PriorityQueue<Integer> choco = new PriorityQueue<>();

            for(int i = 0; i<3; i++){
                box[i] = br.readLine().toCharArray();
            }

            for(int i = 0; i<3; i++){
                for(int j = 0; j<3; j++){
                    if(box[i][j] == 'O' && !visited[i][j]){
                        int cnt = 1;
                        group++;
                        Queue<int[]> queue = new ArrayDeque<int[]>();

                        visited[i][j] = true;
                        queue.add(new int[] {i, j});

                        while(!queue.isEmpty()){
                            int[] now = queue.poll();
                            for(int d = 0; d<4; d++){
                                int nr = now[0]+dir[d][0];
                                int nc = now[1]+dir[d][1];
                                if(!isPossible(nr, nc)) continue;
                                if(visited[nr][nc] || box[nr][nc] != 'O') continue;

                                visited[nr][nc] = true;
                                queue.add(new int[] {nr, nc});
                                cnt++;
                            }
                        }
                        choco.add(cnt);
                    }
                }
            }
            st = new StringTokenizer(br.readLine());
            if(Integer.parseInt(st.nextToken()) == group){
                while(st.hasMoreTokens()){
                    if(!choco.isEmpty() && choco.peek() != Integer.parseInt(st.nextToken())){
                        System.out.println('0');
                        continue tc;
                    }
                    choco.poll();
                }
                System.out.println('1');
                continue tc;
            }
            System.out.println('0');
        }
    }
}