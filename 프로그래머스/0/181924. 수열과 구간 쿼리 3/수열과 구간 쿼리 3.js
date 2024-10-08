function solution(arr, queries) {
    var answer = [...arr];
    function swap(i, j) {
        temp = answer[i];
        answer[i] = answer[j];
        answer[j] = temp;
    }
    queries.reduce((acc, cur, idx) => {
        swap(cur[0], cur[1]);
    }, 0)
    return answer;
}