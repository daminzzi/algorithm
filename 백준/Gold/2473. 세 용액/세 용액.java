import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer> solution = new ArrayList<Integer>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i<N; i++){
            solution.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(solution);

        if(N == 3){
            for(int i = 0; i<N; i++){
                sb.append(solution.get(i)).append(' ');
            }
            System.out.println(sb);
            return;
        }

        Long comp = Long.MAX_VALUE;
        int ans1 = 0;
        int ans2 = 0;
        int ans3 = 0;
        for(int i = 0; i<N-2; i++){ //시작 수
            int lo = i+1;
            int hi = N-1;
            while(lo < hi){
                Long temp = getSum(i, lo, hi);
                if(temp == 0){
                    System.out.println(solution.get(i)+" "+solution.get(lo)+" "+solution.get(hi));
                    return;
                }
                if(Math.abs(temp) < comp){
                    ans1 = i;
                    ans2 = lo;
                    ans3 = hi;
                    comp = Math.abs(getSum(i, lo, hi));
                }

                if(temp < 0){
                    lo++;
                } else {
                    hi--;
                }
            }
        }

        System.out.println(solution.get(ans1)+" "+solution.get(ans2)+" "+solution.get(ans3));
    }

    static Long getSum(int i, int j, int k){
        return  Long.valueOf(solution.get(i)) + solution.get(j) + solution.get(k);
    }
}