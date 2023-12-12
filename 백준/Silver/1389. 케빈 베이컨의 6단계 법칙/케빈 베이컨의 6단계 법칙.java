import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] users = new int[N+1][N+1];

        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=N; j++){
                if(i==j) continue;
                users[i][j] = 10000000;
            }
        }

        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            users[v][w] = 1;
            users[w][v] = 1;
        }

        for(int j = 1; j<=N; j++){
            for(int i = 1; i<=N; i++){
                int first = users[i][j];
                if(first == 10000000) continue;
                for(int k = 1; k<=N; k++){
                    if(first + users[j][k] < users[i][k]){
                        users[i][k] = first + users[j][k];
                    }
                }
            }
        }

        int min = 1000000;
        int kevin_min = -1;
        for(int i = 1; i<=N; i++){
            int temp = 0;
            for(int j = 1; j<=N; j++){
                temp += users[i][j];
            }
            if(temp < min){
                min = temp;
                kevin_min = i;
            }
        }

        System.out.println(kevin_min);
    }
}