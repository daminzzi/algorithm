import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while(T-- > 0){
            int K = Integer.parseInt(br.readLine());

            TreeMap<Integer, Integer> map = new TreeMap<>();
            while(K-- > 0){
                st = new StringTokenizer(br.readLine());
                char action = st.nextToken().charAt(0);
                int num = Integer.parseInt(st.nextToken());

                if(action == 'I'){
                    map.put(num, map.getOrDefault(num, 0)+1);
                }else{
                    if(map.size()==0) continue;
                    int deleteNum;
                    if(num == 1){
                        deleteNum = map.lastKey();
                    }else{
                        deleteNum = map.firstKey();
                    }
                    if(map.put(deleteNum, map.get(deleteNum)-1) == 1){
                        map.remove(deleteNum);
                    }
                }
            }
            if(map.size()==0){
                sb.append("EMPTY\n");
            }else{
                sb.append(map.lastKey()+" "+map.firstKey()+"\n");
            }
        }
        System.out.println(sb);
    }
}