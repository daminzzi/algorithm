import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
    static class Point {
        int r, c;
        Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    //시작 통나무 위치, 종료 통나무 위치
    static List<int[]> start, end;
    //시작 통나무 방향, 종료 통나무 방향
    //0이면 가로, 1이면 세로
    static int N, sp, ep;
    static int[][] map;
    static int[][] dir = {
            {0, 1}, {0, -1}, {1, 0}, {-1, 0}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        start = new ArrayList<>(3);
        end = new ArrayList<>(3);

        //입력 처리
        for(int i = 0; i<N; i++){
            String str = br.readLine();
            for(int j = 0; j<N; j++){
                char c = str.charAt(j);
                if(c == 'B'){
                    start.add(new int[] {i, j});
                }
                else if(c == 'E'){
                    end.add(new int[] {i, j});
                }
                else {
                    map[i][j] = c-'0';
                }
            }
        }

        //현재 통나무 방향 체크
        if(start.get(0)[0] != start.get(1)[0]) sp = 1;
        if(end.get(0)[0] != end.get(1)[0]) ep = 1;

        boolean[][][] visited = new boolean[N][N][2];
        //bfs로 이동 진행(현재 중심 위치, 방향을 기준으로 탐색)
        //Queue에는 {현재 중심 row, 현재 중심 col, 현재 방향, depth}가 들어감
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {start.get(1)[0], start.get(1)[1], sp, 0});

        while(!queue.isEmpty()){
            int[] now = queue.poll();

            //일치하는지 체크하기
            if (now[0] == end.get(1)[0] && now[1] == end.get(1)[1] && now[2] == ep) {
                System.out.println(now[3]);
                return;
            }

            //이동하기
            for(int i = 0; i<4; i++){
                int nr = now[0]+dir[i][0];
                int nc = now[1]+dir[i][1];
                int np = now[2];
                int nd = now[3]+1;

                if(!isPossible(nr, nc, np)) continue;
                if(visited[nr][nc][np]) continue;
                if(isTree(nr, nc, np)) continue;

                visited[nr][nc][np] = true;
                queue.offer(new int[] {nr, nc, np, nd});
            }

            //회전하기
            if(canTurn(now[0], now[1])){
                int np = now[2] == 1? 0:1;
                if(!visited[now[0]][now[1]][np]){
                    visited[now[0]][now[1]][np] = true;
                    queue.offer(new int[] {now[0], now[1], np, now[3]+1});
                }
            }
        }

        System.out.println(0);
    }

    static boolean isPossible(int r, int c, int p){
        if(p == 0) { //세로
            if(r < 0 || r >= N || c <= 0 || c >= N-1){
                return false;
            }
        }else{
            if(r <= 0 || r>= N-1 || c < 0 || c>=N){
                return false;
            }
        }
        return true;
    }

    static boolean isTree(int r, int c, int p){
        for(int i = -1; i<= 1; i++){
            if(p == 0){
                if(map[r][c+i] == 1) return true;
            }
            else{
                if(map[r+i][c] == 1) return true;
            }
        }
        return false;
    }

    static boolean canTurn(int r, int c){
        for(int i = -1; i<=1; i++){
            for(int j = -1; j<=1; j++){
                if(r+i < 0 || r+i >= N || c+j < 0 || c+j >= N){
                    return false;
                }

                if(map[r+i][c+j] == 1){
                    return false;
                }
            }
        }

        return true;
    }
}