function solution(num_list) {
    odd = '';
    even = '';
    
    for(const num of num_list){
        if(num % 2 === 0){
            even += num;
        }
        else {
            odd += num;
        }
    }
    
    return Number(odd) + Number(even);
}