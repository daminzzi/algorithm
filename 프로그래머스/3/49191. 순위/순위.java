class Solution {
    public int solution(int n, int[][] results) {
      
        int[][] graph = new int[n+1][n+1];
        for(int[] result: results){
            graph[result[0]][result[1]] = 1;
            graph[result[1]][result[0]] = -1;
        }
        
        for(int j = 1; j<=n; j++){
            for(int i = 1; i<=n; i++){
                for(int k = 1; k<=n; k++){
                    if(graph[i][j] ==0 || graph[j][k] == 0) 
                        continue;
                    if(graph[i][j] == graph[j][k]){
                        graph[i][k] = graph[i][j];
                    }
                }
            }
        }
        
        int ans = 0;
        for(int i = 1; i<=n; i++){
            int win = 0, lose = 0;
            for(int j = 1; j<=n; j++){
                if(graph[i][j] == 1) win++;
                else if(graph[i][j] == -1) lose++;
            }
            if(win + lose == n-1){
                ans++;
            }
        }
        
        return ans;
    }
}