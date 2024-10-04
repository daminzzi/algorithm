function solution(n) {
    var answer;
    
    function oddAnswer() {
        answer = 0;
        for(i=1; i<=n; i+=2){
            answer += i;
        }
        return answer;
    }
    
    function evenAnswer() {
        answer = 0;
        for(i=2; i<=n; i+=2){
            answer += i*i;
        }
        return answer;
    }
    
    return n%2? oddAnswer():evenAnswer();
}