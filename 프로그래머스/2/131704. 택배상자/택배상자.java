import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        
        Queue<Integer> mainQueue = new ArrayDeque<>();
        ArrayDeque<Integer> subStack = new ArrayDeque<>();
        
        for(int i = 1; i<=order.length; i++){
            mainQueue.add(i);
        }
        
        for(int i = 0; i<order.length; i++){
            int target = order[i];
            if(!mainQueue.isEmpty()){
                if(target >= mainQueue.peek()){
                    while(mainQueue.peek() != target){
                    subStack.push(mainQueue.poll());
                }
                mainQueue.remove();
                answer++;
                continue;
                }
            }
            if(subStack.peek() != target){
                break;
            }
            subStack.pop();
            answer++;
        }
        
        
        return answer;
    }
}