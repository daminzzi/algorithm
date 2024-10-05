function solution(a, d, included) {
    var answer = 0;
 
    included.map((bool, idx) => {
        if(bool) answer += (a + d*idx);
    })
        
    return answer;
}