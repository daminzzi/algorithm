import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = -2;
        int len = queue1.length;
        int total = len*2;
        int limit = len*3-3;
        int[] queue = new int[total];
        System.arraycopy(queue1, 0, queue, 0, len);
        System.arraycopy(queue2, 0, queue, len, len);
        long sum = 0;
        long temp = 0;
        for(int i = 0; i<len; i++){
            sum += queue1[i]+queue2[i];
            temp += queue1[i];
        }
        if(sum % 2 == 1){
            return -1;
        }
        sum = sum/2;
        
        int idx1 = 0;
        int idx2 = len;
        
        answer = 0;
        while(answer <= limit){
            if(temp == sum) {
                return answer;
            }
            if(temp < sum) {
                temp += queue[idx2++];
                idx2 %= total;
                answer += 1;
            }
            else if(temp > sum){
                temp -= queue[idx1++];
                idx1 %= total;
                answer += 1;
            }
        }
        
        return -1;
    }
}