function solution(arr, queries) {
    queries.map((cur) => {
        for(let i = cur[0]; i<=cur[1]; i++){
            if(i % cur[2] == 0) arr[i] += 1;
        }
    });
    return arr;
}