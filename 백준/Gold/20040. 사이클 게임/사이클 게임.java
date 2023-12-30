import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] parents;
    static int find(int a){
        if(parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA == rootB) return false;
        parents[rootB] = rootA;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parents = new int[N];
        for(int i = 0; i<N; i++){
            parents[i] = i;
        }

        for(int i = 1; i<=M; i++){
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            if(!union(v,w)){
                System.out.println(i);
                return;
            }
        }
        System.out.println(0);
    }
}