import java.util.*;

class Solution {
    static class Pair implements Comparable<Pair> {
        String genre;
        int plays;
        
        Pair(String genre, int plays){
            this.genre = genre;
            this.plays = plays;
        }
        
        public int compareTo(Pair o){
            return Integer.compare(o.plays, this.plays);
        }
    }
    
    //String은 장르명, int[]에는 차례대로 총 플레이 수, 상위 두곡이 들어간다.
    static HashMap<String, int[]> playList;
    
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();
        playList = new HashMap<>();
        
        for(int i = 0; i < genres.length; i++){
            int[] temp = playList.get(genres[i]);
            //HashMap에 genre가 없는 경우
            if(temp == null){
                playList.put(genres[i], new int[] {plays[i], i, -1});
                continue;
            }
            //HashMap에 genre가 있는 경우
            temp[0] += plays[i];
            if(plays[temp[1]] < plays[i]){
                temp[2] = temp[1];
                temp[1] = i;
                continue;
            }
            if(temp[2] == -1 || plays[temp[2]] < plays[i]){
                temp[2] = i;
            }
        }
        
        //key랑 value[0]를 새로운 리스트에 정렬하면서 넣기(Priority Queue 쓰기?)
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for(String genre: playList.keySet()){
            pq.offer(new Pair(genre, playList.get(genre)[0]));
        }
        
        //HashMap에서 pq에서 하나씩 뽑아가면서 1,2 순위 노래 출력하기
        int idx = 0;
        while(!pq.isEmpty()){
            Pair temp = pq.poll();
            
            int[] song = playList.get(temp.genre);
            if(song == null) continue;
            
            answer.add(song[1]);
            if(song[2] != -1)
                answer.add(song[2]);
        }
        
        int[] ret = new int[answer.size()];
        for (int i = 0 ; i < answer.size() ; i++) 
            ret[i] = answer.get(i);
        
        return ret;
    }
}