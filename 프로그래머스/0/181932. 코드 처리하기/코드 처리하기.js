function solution(code) {
    var ret = '';
    var mode = 0;
    
    for(idx = 0; idx<code.length; idx++){
        if(mode === 0){
            if(code[idx] !== '1'){
                if(idx % 2 === 0){
                    ret += code[idx];
                }
            }else{
                mode = 1;
            }
        }
        else{
            if(code[idx] !== '1'){
                if(idx % 2 === 1){
                    ret += code[idx];
                }
            }else{
                mode = 0;
            }
        }
    }
    
    if(!ret) {
        return "EMPTY";
    }
    return ret;
}