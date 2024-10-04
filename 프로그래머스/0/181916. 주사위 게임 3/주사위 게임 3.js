function solution(a, b, c, d) {
    const diceMap = [a,b,c,d].reduce((acc, cur) => {
        acc[cur] = (acc[cur] || 0) + 1;
        return acc;
    }, {})
    
    const diceValue = Object.keys(diceMap).sort((a, b) => diceMap[b] - diceMap[a]);
    
    if(diceMap[diceValue[0]] === 4) return 1111*Number(diceValue[0]);
    
    if(diceMap[diceValue[0]] === 3) return Math.pow(10 * Number(diceValue[0]) + Number(diceValue[1]), 2);
    if(diceMap[diceValue[0]] === 2 && diceValue.length === 2) 
        return (Number(diceValue[0]) + Number(diceValue[1]))*Math.abs(Number(diceValue[0]) - Number(diceValue[1]));
    
    if(diceMap[diceValue[0]] === 2 && diceValue.length === 3)
        return Number(diceValue[1]) * Number(diceValue[2]);
    
    return Number(diceValue[0]);
}