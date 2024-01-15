class Solution {
    static String[] mineral;
    
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        mineral = minerals;
        return dfs(0, 0, picks[0], picks[1], picks[2]);
    }
    
    static int dfs(int depth, int fatigue, int dia, int iron, int stone){
        if(dia == 0 && iron == 0 && stone == 0){
            return fatigue;
        }
        if(depth*5 >= mineral.length){
            return fatigue;
        }
        
        int d = Integer.MAX_VALUE; //현재 depth에서 다이아곡갱이를 쓰는 경우 피로도
        int i = Integer.MAX_VALUE; //현재 depth에서 철곡갱이를 쓰는 경우 피로도
        int s = Integer.MAX_VALUE; //현재 depth에서 돌곡갱이를 쓰는 경우 피로도
        
        int start = depth*5;
        int end = Math.min((depth+1)*5, mineral.length);
        System.out.println(start+" "+ end);
        if(dia >= 1){
            d = fatigue;
            for(int idx = start; idx < end; idx++){
                d += 1;
            }
            d = dfs(depth+1, d, dia-1, iron, stone);
        }
        if(iron >= 1){
            i = fatigue;
            for(int idx = start; idx < end; idx++){
                if(mineral[idx].equals("diamond")){
                    i += 5;                    
                    continue;
                }
                i += 1;
            }
            i = dfs(depth+1, i, dia, iron-1, stone);
        }
        if(stone >= 1){
            s = fatigue;
            for(int idx = start; idx < end; idx++){
                if(mineral[idx].equals("diamond")){
                    s += 25;                    
                    continue;
                }
                else if(mineral[idx].equals("iron")){
                    s += 5;
                    continue;
                }
                s += 1;
            }
            s = dfs(depth+1, s, dia, iron, stone-1);
        }
        
        return d <= i ? (d <= s? d:s):(i <= s? i:s);
    }
}