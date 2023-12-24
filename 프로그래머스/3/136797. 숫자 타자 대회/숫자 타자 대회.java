class Solution {
    static int[][] cost = {
        { 1, 7, 6, 7, 5, 4, 5, 3, 2, 3 },
        { 7, 1, 2, 4, 2, 3, 5, 4, 5, 6 },
        { 6, 2, 1, 2, 3, 2, 3, 5, 4, 5 },
        { 7, 4, 2, 1, 5, 3, 2, 6, 5, 4 },
        { 5, 2, 3, 5, 1, 2, 4, 2, 3, 5 },
        { 4, 3, 2, 3, 2, 1, 2, 3, 2, 3 },
        { 5, 5, 3, 2, 4, 2, 1, 5, 3, 2 },
        { 3, 4, 5, 6, 2, 3, 5, 1, 2, 4 },
        { 2, 5, 4, 5, 3, 2, 3, 2, 1, 2 },
        { 3, 6, 5, 4, 5, 3, 2, 4, 2, 1 }
    };
    
    static int len;
    static char[] number;
    static int[][][] dp;
    
    public int solution(String numbers) {
        int answer = 0;
        len = numbers.length();
        number = numbers.toCharArray();
        dp = new int[numbers.length()+1][10][10];
        
        answer = solve(0, 4, 6);
        
        return answer;
    }
    
    static int solve(int depth, int left, int right){
        if(depth == len){
            return 0;
        }
        
        if(dp[depth][left][right] != 0){
            return dp[depth][left][right];
        }
        
        int target = number[depth] - '0';
        if(left == target) {
            dp[depth][left][right] = solve(depth+1, left, right)+1;
            return dp[depth][left][right];
        }
        if(right == target){
            dp[depth][left][right] = solve(depth+1, left, right)+1;
            return dp[depth][left][right];
        }
        int leftValue = solve(depth+1, target, right) + cost[left][target];
        int rightValue = solve(depth+1, left, target) + cost[right][target];
        
        dp[depth][left][right] = Math.min(leftValue, rightValue);
        
        return dp[depth][left][right];
    }
}