function solution(arr, queries) {
    return queries.reduce((acc, [s, e, k]) => {
        min = 10000001;
        arr.slice(s, e+1).forEach((element) => {
            if(element > k && min > element){
                min = element;
            }
        })
        
        min = min === 10000001? -1:min;
        
        return [...acc, min];
    }, []);
}