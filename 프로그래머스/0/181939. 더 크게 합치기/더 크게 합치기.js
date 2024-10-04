function solution(a, b) {
    var answer = a+''+b > b+''+a ? a+''+b : b+''+a;
    return Number(answer);
}