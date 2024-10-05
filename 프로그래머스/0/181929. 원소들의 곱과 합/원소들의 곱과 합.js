function solution(num_list) {
    mul = num_list.reduce((acc, i) => acc*i);
    add = num_list.reduce((acc, i) => acc+i);
    
    if(mul < add*add) return 1;
    return 0;
}