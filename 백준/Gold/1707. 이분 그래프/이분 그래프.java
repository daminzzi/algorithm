import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static List<List<Integer>> graph;
    private static int[] colors;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        while(T-- > 0){
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            colors = new int[V+1];

            for(int v = 0; v<=V; v++){
                graph.add(new ArrayList<>());
            }

            for(int e = 0; e<W; e++){
                st = new StringTokenizer(br.readLine());
                int f = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());

                graph.get(f).add(t);
                graph.get(t).add(f);
            }

            boolean res = false;
            for(int v = 1; v <= V; v++){
                if(colors[v] == 0){
                    res = isBipartiteGraph(v, 1);
                }
                if(!res) break;
            }

            if(res) sb.append("YES\n");
            else sb.append("NO\n");
        }

        System.out.println(sb);
    }

    private static boolean isBipartiteGraph(int start, int color){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        colors[start] = color;

        while(!queue.isEmpty()) {
            int now = queue.poll();
            for(int next : graph.get(now)) {
                if(colors[now] == colors[next]) return false;

                if(colors[next] == 0) {
                    colors[next] = colors[now] * -1;
                    queue.add(next);
                }
            }
        }
        return true;
    }
}