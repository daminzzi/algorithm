import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    
    public static class Edge implements Comparable<Edge>{
        int a;
        int dis;
        public Edge(int a, int dis){
            this.a = a;
            this.dis = dis;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dis - o.dis;
        }
    }
    public static int N, M;
    public static int[] distance, connect;
    public static ArrayList<Edge>[] list;
    public static int cnt;
    
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N+1];
        distance = new int[N+1];
        connect = new int[N+1];

        for(int i=0;i<N+1;i++){
            list[i] = new ArrayList<Edge>();
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());

            list[a].add(new Edge(b, dis));
            list[b].add(new Edge(a, dis));
        }

        Arrays.fill(distance, Integer.MAX_VALUE);
        
        distance[1] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1, 0));
        while (!pq.isEmpty()){
            Edge edge = pq.poll();

            if(edge.dis > distance[edge.a]) continue;

            for(Edge e:list[edge.a]){
                if(distance[e.a] > e.dis + edge.dis){
                    distance[e.a]  = e.dis + edge.dis;
                    connect[e.a] = edge.a;
                    pq.add(new Edge(e.a, distance[e.a]));
                }
            }
        }

        for(int i=2;i<=N;i++){
            if(connect[i] == 0) continue;
            cnt++;
            sb.append(i+ " " + connect[i]+"\n");
        }
        System.out.println(cnt);
        System.out.println(sb.toString());
    }

}