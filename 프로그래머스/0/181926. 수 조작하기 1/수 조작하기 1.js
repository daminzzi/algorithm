function solution(n, control) {
    var answer = n;
    
    const controller = {
        'w': 1,
        's': -1,
        'd': 10,
        'a': -10,
    }
    
    for(const i of control){
        answer += controller[i];
    }
    
    return answer;
}