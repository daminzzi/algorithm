import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[][] picture;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        picture = new int[N][N];

        for(int i = 0; i<N; i++){
            String str = br.readLine();
            for(int j = 0; j<N; j++){
                picture[i][j] = str.charAt(j)-'0';
            }
        }

        System.out.println(quad(0, 0, N));
    }

    static String quad(int r, int c, int size){
        int color = picture[r][c];
        StringBuilder sb = new StringBuilder();

        boolean flag = true;
        for(int i = r; i<r+size; i++){
            for(int j = c; j<c+size; j++){
                if(picture[i][j] != color){
                    flag = false;
                    break;
                }
            }
        }

        if(flag){
            return sb.append(color).toString();
        }

        sb.append('(').append(quad(r, c, size/2)).append(quad(r, c+size/2, size/2));
        sb.append(quad(r+size/2, c, size/2)).append(quad(r+size/2, c+size/2, size/2));
        sb.append(')');
        return sb.toString();
    }
}