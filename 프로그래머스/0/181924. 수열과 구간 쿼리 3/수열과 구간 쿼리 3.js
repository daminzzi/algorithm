function solution(arr, queries) {
    function swap(i, j) {
        temp = answer[i];
        answer[i] = answer[j];
        answer[j] = temp;
    }
    return queries.reduce((acc, [i, j]) => {
        temp = acc[i];
        acc[i] = acc[j];
        acc[j] = temp;
        return acc;
    }, [...arr])
}