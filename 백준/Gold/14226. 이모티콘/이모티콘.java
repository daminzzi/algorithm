import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static boolean[][] visited = new boolean[1001][1001]; //[클립보드][모니터]
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int S = Integer.parseInt(br.readLine());

        Queue<Emoticon> queue = new ArrayDeque<>();
        queue.add(new Emoticon(0,1, 0));

        while (!queue.isEmpty()){
            Emoticon start = queue.poll();

            if(start.monitor == S){
                sb.append(start.time);
                break;
            }

            //1. 클립보드에 복사하기
            if(!visited[start.monitor][start.monitor]){
                visited[start.monitor][start.monitor] = true;
                queue.add(new Emoticon(start.monitor, start.monitor, start.time+1));
            }

            //2. 클립보드꺼 화면에 붙여넣기
            if(start.clipboard != 0 && start.monitor + start.clipboard <= S && !visited[start.clipboard][start.monitor + start.clipboard]){
                queue.add(new Emoticon(start.clipboard, start.monitor+ start.clipboard, start.time+1));
                visited[start.clipboard][start.monitor+ start.clipboard] = true;
            }

            //3. 이모티콘 하나 삭제
            if(start.monitor > 1 && !visited[start.clipboard][start.monitor-1]){
                queue.add(new Emoticon(start.clipboard, start.monitor-1, start.time+1));
                visited[start.clipboard][start.monitor-1] = true;
            }
        }
        System.out.println(sb);
    }

    static class Emoticon{
        int clipboard;
        int monitor;
        int time;

        public Emoticon(int clipboard, int monitor, int time) {
            this.clipboard = clipboard;
            this.monitor = monitor;
            this.time = time;
        }
    }
}