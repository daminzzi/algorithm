import java.util.*;

public class Solution {
    static class FileName implements Comparable<FileName> {
        String fileName;
        String head;
        int number;
        int idx;

        public FileName(String fileName, String head, int number, int idx) {
            this.fileName = fileName;
            this.head = head;
            this.number = number;
            this.idx = idx;
        }

        @Override
        public int compareTo(FileName o) {
            if(this.head.compareTo(o.head) != 0){
                return this.head.compareTo(o.head);
            }
            else if(Integer.compare(this.number, o.number) != 0){
                return Integer.compare(this.number, o.number);
            }
            return Integer.compare(this.idx, o.idx);
        }
    }
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        FileName[] fileNames = new FileName[files.length];

        for(int i = 0; i<files.length; i++){
            int isNumber = -1;
            StringBuilder buf = new StringBuilder();
            String head = "";
            int number = 0;
            int idx = 0;
            while(true){
                if(idx >= files[i].length()) break;
                char t = files[i].charAt(idx++);
                if(isNumber == -1) {
                    if(t >= '0' && t <= '9') {
                        isNumber = 0;
                        head = buf.toString().toLowerCase();
                        buf.setLength(0);
                        buf.append(t);
                        continue;
                    }
                    buf.append(t);
                    continue;
                }
                else if(isNumber == 0){
                    if(t < '0' || t > '9') {
                        number = Integer.parseInt(buf.toString());
                        break;
                    }
                    buf.append(t);
                }
            }
            if(idx == files[i].length()){
                number = Integer.parseInt(buf.toString());
            }
            fileNames[i] = new FileName(files[i], head, number, i);
            System.out.println(head+" "+number);
        }

        Arrays.sort(fileNames);
        for(int i = 0; i<fileNames.length; i++){
            answer[i]=fileNames[i].fileName;
        }
            
        return answer;
    }
}
