import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, X;
    static List<List<int[]>> map;
    static List<List<int[]>> reverseMap;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        map = new ArrayList<>();
        reverseMap = new ArrayList<>();
        dp = new int[N+1][2];
        for(int i = 0; i<=N; i++){
            map.add(new ArrayList<>());
            reverseMap.add(new ArrayList<>());
            dp[i][0] = Integer.MAX_VALUE;
            dp[i][1] = Integer.MAX_VALUE;
        }

        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            map.get(v).add(new int[] {w, t});
            reverseMap.get(w).add(new int[] {v, t});
        }

        dijkstra(X);
        dijkstra2(X);

        int max = 0;
        for(int i = 1; i<=N; i++){
            int sum = dp[i][0] + dp[i][1];
            if(sum > max) max = sum;
        }

        System.out.println(max);
    }

    static void dijkstra(int start){
        dp[start][0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1], o2[1]);
            }
        });
        pq.add(new int[] {start, 0});

        while(!pq.isEmpty()){
            int cur = pq.peek()[0];
            int cost = -pq.peek()[1];
            pq.poll();

            if(dp[cur][0] < cost) continue;
            for(int i = 0; i<map.get(cur).size(); i++){
                int next = map.get(cur).get(i)[0];
                int nextCost = cost + map.get(cur).get(i)[1];

                if(nextCost < dp[next][0]) {
                    dp[next][0] = nextCost;
                    pq.add(new int[] {next, -nextCost});
                }
            }
        }
    }

    static void dijkstra2(int start){
        dp[start][1] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1], o2[1]);
            }
        });
        pq.add(new int[] {start, 0});

        while(!pq.isEmpty()){
            int cur = pq.peek()[0];
            int cost = -pq.peek()[1];
            pq.poll();

            if(dp[cur][1] < cost) continue;
            for(int i = 0; i<reverseMap.get(cur).size(); i++){
                int next = reverseMap.get(cur).get(i)[0];
                int nextCost = cost + reverseMap.get(cur).get(i)[1];

                if(nextCost < dp[next][1]) {
                    dp[next][1] = nextCost;
                    pq.add(new int[] {next, -nextCost});
                }
            }
        }
    }
}