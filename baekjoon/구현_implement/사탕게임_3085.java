package 구현_implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 사탕게임_3085 {

    private String site = "https://www.acmicpc.net/problem/3085";


    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int N;

    public static void result() throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        char[][] board = new char[N][N];
        for(int n=0; n<N; n++){
            String one=br.readLine();
            for(int m=0; m<N; m++){
                board[n][m] = one.charAt(m);
            }
        }


        int count=0;
        int maxCount=countMax(board);
        // 위,아래,좌,우
        int x,y;
        for(int n=0; n<N; n++){
            for (int m = 0; m < N; m++) {
                for(int i=0; i<4; i++){
                    x=n+dx[i];
                    y=m+dy[i];

                    if(0<=x && x<N && 0<=y && y<N){
                        if(board[n][m]!=board[x][y]){
                            char[][] copyBoard = createCopyboard(board);

                            //스위칭
                            copyBoard[n][m]=board[x][y];
                            copyBoard[x][y]=board[n][m];

                            //스위칭한 보드의 max카운터세기
                            count=countMax(copyBoard);
                            if(count>maxCount){
                                maxCount=count;
                            }
                        }
                    }
                }
            }
        }

        System.out.println(maxCount);



    }

    public static char[][] createCopyboard(char[][] src){
        char[][] copyBoard = new char[N][N];
        for (int n = 0; n < N; n++) {
            copyBoard[n] = Arrays.copyOf(src[n], N);
        }
        return copyBoard;
    }

    public static int countMax(char[][] board){
        int maxCount=1;
        //여긴 행검사
        for (int n = 0; n < N; n++) {

            char before=board[n][0]; //제일 처음값을 뺀 나머지 쭉 행 검사
            int rowCount=1;

            for (int m = 1; m < N; m++) {

                if(before==board[n][m]){//같다면..
                    rowCount++;
                    if(rowCount>maxCount) maxCount=rowCount;
                }else{
                    rowCount=1;
                }
                before=board[n][m]; //before갱신 다음 또 검사하도록.

            }
        }

        //여긴 열검사
        for (int m = 0; m < N; m++) {

            char before=board[0][m]; //제일 처음값을 뺀 나머지 쭉 열 검사
            int colCount=1;

            for (int n = 1; n < N; n++) {

                if(before==board[n][m]){//같다면..
                    colCount++;
                    if(colCount>maxCount) maxCount=colCount;
                }else{
                    colCount=1;
                }
                before=board[n][m]; //before갱신 다음 또 검사하도록.

            }
        }
        return maxCount;
    }
}
