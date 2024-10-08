function solution(num_list) {
    var answer = [...num_list];
    const length = num_list.length-1;
    if(num_list[length] > num_list[length-1]){
        answer.push(num_list[length]-num_list[length-1]);
    } else {
        answer.push(num_list[length]*2);
    }
    return answer;
}