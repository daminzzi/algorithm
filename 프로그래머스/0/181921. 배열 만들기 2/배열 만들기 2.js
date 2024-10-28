function solution(l, r) {
    var answer = [];
    const pat = /^[^12346789]*$/;
    for(let i = l; i<=r; i++){
        if(pat.test(i)) answer.push(i);
    }
    
    if(answer.length===0) answer.push(-1);
    return answer;
}