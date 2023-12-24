import java.util.*;
import java.io.*;

class Solution {
    static int[] infos;
    static List<List<Integer>> graph;
    
    public int solution(int[] info, int[][] edges) {
        infos = info;
        graph = new ArrayList<>();
        
        for(int i = 0; i<=info.length; i++){
            graph.add(new ArrayList<>());
        }
        
        for(int[] e: edges){
            graph.get(e[0]).add(e[1]);
        }
        
        int max = 0;
        List<Integer> nextNodes = new ArrayList<>(graph.get(0));
        max = dfs(0, 0, 0, nextNodes);
        return max;
    }
    
    static int dfs(int now, int sheep, int wolf, List<Integer> nodes){
        //방문 칸이 양
        if(infos[now] == 0) sheep++;
        else { //방문 칸이 늑대
            if(sheep == wolf+1){ //양 다 잡아먹힘
                return sheep;
            }
            wolf++;
        }
        
        int ret = sheep;
        for(int i = 0; i<nodes.size(); i++){
            int next = nodes.get(i);
            List<Integer> nextNodes = new ArrayList<>(nodes);
            nextNodes.remove((Integer)next);
            nextNodes.addAll(graph.get(next));
            ret = Math.max(ret, dfs(next, sheep, wolf, nextNodes));
        }
        
        return ret;
    }
}