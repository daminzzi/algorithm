import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int R, C, T, dust;
	static int r1=-1, c1=-1, r2=-1, c2=-1; //공기청정기 위치
	static int[][] room;
	
    //위쪽 공기청정기 탐색 방향 3(북)->2(동)->1(남)->0(서)으로 돌아감
	static int[] dr1 = {0, 1, 0, -1};
	static int[] dc1 = {-1, 0, 1, 0};
    //아래쪽 공기청정기 탐색 방향 3(남)->2(동)->1(북)->0(서)으로 돌아감
	static int[] dr2 = {0, -1, 0, 1};
	static int[] dc2 = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		room = new int[R][C];
		
		for(int r = 0; r<R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c <C; c++) {
				room[r][c] = Integer.parseInt(st.nextToken());
				if(room[r][c] == -1) {
					if(r1 == -1) {
						r1 = r;
						c1 = c;
					}
					else {
						r2 = r;
						c2 = c;
					}
				}
			}
		}
		
		
		for(int t = 0; t<T; t++) {
			simulation();
		}
		
		for(int r = 0; r<R; r++) {
			for(int c = 0; c<C; c++) {
				if(room[r][c] != -1) {
					dust+=room[r][c];					
				}
			}
		}
		
		System.out.println(dust);
	}
    
	//먼지 확산, 공기청정기 작동 시 범위 체크 함수
	static boolean checkRange(int r, int c) {
		if(r>=R || r<0 || c>=C || c<0) {
			return false;
		}
		return true;
	}
	
    //시뮬레이션 함수, 함수 내부에서 먼지확산을 하고
    //pushDust함수를 불러서 공기청정기 작동을 시뮬레이션함.
	static void simulation() {
		int nr, nc;
		int[][] nextRoom = new int[R][C];
		
		//dust diffusion
		for(int r = 0; r<R; r++) {
			for(int c = 0; c<C; c++) {
				if(room[r][c] == 0 ||room[r][c] == -1) continue;
				//상하좌우 모든 방향에 대하여 먼지를 확산시킴		
				for(int i = 0; i<4; i++) {
					nr = r+dr1[i];
					nc = c+dc1[i];
					if(checkRange(nr, nc) && room[nr][nc] != -1) {
						nextRoom[nr][nc] += room[r][c]/5;
						nextRoom[r][c] -= room[r][c]/5;
					}
				}
			}
		}
        
		//먼지 확산을 현재 방에 적용함
		for(int r = 0; r<R; r++) {
			for(int c = 0; c<C; c++) {
				room[r][c] += nextRoom[r][c];
			}
		}
		
		pushDust();		
	}
	
	static void pushDust() {
		int uidx = 3; //위쪽 공기청정 인덱스는 3-2-1-0
		int didx = 3; //아래쪽 공기청정 인덱스는 3-2-1-0
		int nr, nc; //새로운 위치
		
        //위쪽 공기청정기에서 시작
		int r=r1, c=c1;
		while(true){
			nr = r+dr1[uidx];
			nc = c+dc1[uidx];
            //다 돌고나서 공기청정기에 다시 도착하면 공기청정기 바로 오른쪽 칸은 0으로 만들고 break
			if(nr == r1 && nc == c1) { 
				room[r][c] = 0;
				break;
			}
            //범위를 벗어나면 index를 감소시켜 방향을 전환함
			if(!checkRange(nr, nc)) {
				uidx--;
				nr = r+dr1[uidx];
				nc = c+dc1[uidx];
			}
			//당겨오기
			if(r == r1 && c== c1) { //시작점일 때
				room[nr][nc] = 0;
			}
			else { //나머지 경우
				room[r][c] = room[nr][nc];				
			}
			
            //아래쪽으로 내려가고 있을 때 공기청정기가 있는 줄에 오면 방향 전환
			if(uidx == 1 && nr==r1) {
				uidx--;
			}
            
            //다음 칸 탐색을 위해서 현재 위치 수정
			r = nr;
			c = nc;
		}
		
        //아래쪽 공기청정기 탐색. 위랑 방식은 똑같음
		r=r2; c=c2;
		while(true){
			nr = r+dr2[didx];
			nc = c+dc2[didx];
			if(nr == r2 && nc == c2) {
				room[r][c] = 0;
				break;
			}
			if(!checkRange(nr, nc)) {
				didx--;
				nr = r+dr2[didx];
				nc = c+dc2[didx];
			}
			
			//당겨오기
			if(r == r2 && c== c2) {
				room[nr][nc] = 0;
			}
			else {
				room[r][c] = room[nr][nc];				
			}

			if(didx == 1 && nr==r2) {
				didx--;
			}
			r = nr;
			c = nc;
		}
	}
}