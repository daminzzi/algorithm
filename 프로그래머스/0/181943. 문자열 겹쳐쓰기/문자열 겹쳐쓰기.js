function solution(my_string, overwrite_string, s) {
    var myStr = [...my_string];
    myStr.splice(s, overwrite_string.length, overwrite_string);
    return myStr.join('');
}