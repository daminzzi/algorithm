class Solution {
    static char[][] squares = new char[3][3];

    public int solution(String[] board) {
        int cntO = 0, cntX = 0;
        for(int i = 0; i<3; i++){
            squares[i] = board[i].toCharArray();
            for(int j = 0; j<3; j++){
                if(squares[i][j]== 'O') cntO++;
                else if(squares[i][j]=='X') cntX++;
            }
        }
        
        //O의 개수가 X보다 2개 이상 많거나
        //X의 개수가 더 많을 때
        int diff = cntO-cntX;
        if(diff >= 2 || diff < 0) 
            return 0;
        
        //O가 한줄 만들었는데 O, X 개수가 같을 때(diff == 0)
        if(isWin('O') && diff == 0) 
            return 0;
        //X가 한줄 만들었는데 cntO > cntX 일때(diff > 0)
        if(isWin('X') && diff > 0)
            return 0;
        
        return 1;
    }
    
    static boolean isWin(char c){
        int[] putCnt = new int[8];
        
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                if(squares[i][j] == c){
                    putCnt[i]++; //가로
                    putCnt[j+3]++; //세로
                    if(i == j) putCnt[6]++;
                    if(i+j == 2) putCnt[7]++;
                }
            }
        }
        
        for(int i:putCnt){
            if(i == 3) return true;
        }
        
        return false;
    }
}