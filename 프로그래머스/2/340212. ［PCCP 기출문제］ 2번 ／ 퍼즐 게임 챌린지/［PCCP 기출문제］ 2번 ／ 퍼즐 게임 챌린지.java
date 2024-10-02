class Solution {
    static int length;
    static int[] puzzleDiffs;
    static int[] puzzleTimes;
    
    public int solution(int[] diffs, int[] times, long limit) {
        length = diffs.length;
        puzzleDiffs = diffs;
        puzzleTimes = times;
        
        return findLevel(1, 100000, limit);
    }
    
    int findLevel(int start, int end, long limit){        
        int mid = (start+end)/2;
        while(start < end){
            mid = (start+end)/2;
            
            long time = puzzleTimes[0];
        
            for(int i = 1; i<length; i++){
                if(puzzleDiffs[i] > mid){
                    time += (puzzleDiffs[i] - mid)*(puzzleTimes[i-1] + puzzleTimes[i]);
                }
                time += puzzleTimes[i];
            
                if(time > limit) break;
            }
        
            if(time > limit){
                start = mid + 1;
            }else{
                end = mid;
            }
        }
        
        return end;
    }
}