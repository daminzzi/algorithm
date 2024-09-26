class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        VideoInfo input = new VideoInfo(video_len, op_start, op_end);
        int posTime = timeToInt(pos);
        
        if(posTime >= input.opStart && posTime < input.opEnd){
            posTime = input.opEnd;
        }
        
        for(String command : commands){
            if(command.equals("next")) {
                posTime += 10;
                if(posTime > input.videoLen) {
                    posTime = input.videoLen;
                }
            } else {
                posTime -= 10;
                if(posTime < 0) {
                    posTime = 0;
                }
            }
            
            if(posTime >= input.opStart && posTime < input.opEnd) {
                posTime = input.opEnd;
            }   
        }
        
        return String.format("%02d:%02d", posTime/60, posTime%60);
    }
    
    static int timeToInt(String time) {
        return Integer.parseInt(time.substring(0, 2))*60 + Integer.parseInt(time.substring(3));
    } 
    
    static class VideoInfo {
        int videoLen;
        int opStart;
        int opEnd;
        
        VideoInfo(String video_len, String op_start, String op_end) {
            this.videoLen = timeToInt(video_len);
            this.opStart = timeToInt(op_start);
            this.opEnd = timeToInt(op_end);
        }
    }
}