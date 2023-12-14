import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static Map<Integer, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int K = Integer.parseInt(br.readLine());

            Queue<Integer> minHeap = new PriorityQueue<>();
            Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            map = new HashMap<>();

            while (K-- > 0) {
                st = new StringTokenizer(br.readLine());
                char action = st.nextToken().charAt(0);
                int num = Integer.parseInt(st.nextToken());

                if (action == 'I') {
                    maxHeap.add(num);
                    minHeap.add(num);
                    map.put(num, map.getOrDefault(num, 0)+1);
                }else{
                    if(map.size()==0) continue;
                    if(num == 1){
                        delete(maxHeap);
                    }else{
                        delete(minHeap);
                    }
                }
            }
            if(map.size()==0){
                sb.append("EMPTY\n");
            }else{
                int res = delete(maxHeap);
                sb.append(res+" ");
                if(map.size() > 0){
                    res = delete(minHeap);
                }
                sb.append(res+"\n");
            }
        }
        System.out.println(sb);
    }

    static int delete(Queue<Integer> q) {
        int res = 0;
        while (true) {
            res = q.poll();

            int cnt = map.getOrDefault(res, 0);
            if (cnt == 0) continue;

            if (cnt == 1) map.remove(res);
            else map.put(res, cnt - 1);
            break;
        }

        return res;
    }
}