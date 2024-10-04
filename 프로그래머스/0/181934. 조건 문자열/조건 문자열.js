function solution(ineq, eq, n, m) {
    var answer = 1;
    if(n > m){
        if(ineq === '<')
            answer = 0;
    }
    
    else if(n < m){
        if(ineq === '>') 
            answer = 0;
    }
    
    else {
        if(eq === '!') answer = 0;
    }
    
    
    return answer;
}