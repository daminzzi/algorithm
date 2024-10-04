function solution(order) {
    var answer = 0;
    const menu = ['americano', 'cafelatte', 'anything'];
    const menuPrice = {
        'americano' : 4500,
        'cafelatte' : 5000,
        'anything' : 4500,
    };
    
    order.map((i) => {
        menu.map((m) => {
            if(i.includes(m)) answer += menuPrice[m]
        })
    })
    
    return answer;
}