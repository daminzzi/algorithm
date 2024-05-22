import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String origin = br.readLine();
        int N = Integer.parseInt(br.readLine());
        ArrayDeque<Character> front = new ArrayDeque<>();
        ArrayDeque<Character> back = new ArrayDeque<>();
        for (char c : origin.toCharArray()){
            front.addLast(c);
        }

        for(int i = 0; i<N; ++i){
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            switch (command) {
                case "P":
                    front.addLast(st.nextToken().charAt(0));
                    break;
                case "L":
                    if(!front.isEmpty()){
                        char c = front.pollLast();
                        back.addFirst(c);
                    }
                    break;
                case "D":
                    if(!back.isEmpty()){
                        char c = back.pollFirst();
                        front.addLast(c);
                    }
                    break;
                default:
                    if(!front.isEmpty()){
                        front.pollLast();
                    }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(char c : front){
            sb.append(c);
        }
        for(char c: back){
            sb.append(c);
        }

        System.out.println(sb);
    }
}