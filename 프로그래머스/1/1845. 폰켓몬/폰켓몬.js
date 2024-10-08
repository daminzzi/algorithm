function solution(nums) {
    const poketmon = nums.reduce((acc, cur) => {
        acc[cur] = (acc[cur]? acc[cur] : 0) + 1;
        return acc;
    }, {});
        
    return Object.keys(poketmon).length > (nums.length)/2 ? nums.length/2 : Object.keys(poketmon).length;
}