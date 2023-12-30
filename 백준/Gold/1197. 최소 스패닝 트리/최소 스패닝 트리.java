import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int w, c;
        Edge(int w, int c) {
            this.w = w;
            this.c = c;
        }
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(c, o.c);
        }
    }

    static List<List<Edge>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>(V);
        for(int i = 0; i<V; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken())-1;
            int w = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken());
            graph.get(v).add(new Edge(w, c));
            graph.get(w).add(new Edge(v, c));
        }

        System.out.println(prim(0, V));
    }

    static int prim(int start, int n){
        boolean[] visited = new boolean[n+1];

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        int total = 0;
        while(!pq.isEmpty()){
            Edge edge = pq.poll();
            int w = edge.w;
            int c = edge.c;
            if(visited[w]) continue;

            visited[w] = true;
            total += c;

            for(Edge e:graph.get(w)){
                if(!visited[e.w]){
                    pq.add(e);
                }
            }
        }

        return total;
    }
}