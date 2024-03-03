class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = {};
        int row = 0;
        int col = 0;
        for(int i = 1; i*i <= yellow; i++){
            if(yellow % i == 0){
                int j = yellow /i;
                int total = (i+2)*(j+2);
                if(total == yellow + brown){
                    row = j+2;
                    col = i+2;
                }
            }
        }
        answer = new int[] {row, col};
        return answer;
    }
}