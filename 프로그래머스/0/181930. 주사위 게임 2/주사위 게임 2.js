function solution(a, b, c) {
    if(a===b && b===c){
        return (3*a)*(3*a*a)*(3*a*a*a);
    }
    else if(a !==b && b!==c && c!==a){
        return (a+b+c);
    }
    
    return (a+b+c)*(a*a+b*b+c*c);
}