package 백트래킹_BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G5_넴모넴모easy_14712 {
    //문제: 골드5 넴모넴모 easy
    //요약:
    //비어있는 칸 -> 넴모 올려놓기
    //넴모가 올라간 칸 네개가 2x2 정사각형 -> 넴모들 모두 없애기
    //모든 넴모가 없어질때까지 반복
    //오래하긴 싫어서, 넴모를 없애고 싶은데 격자판 위에 없앨 수 있는 넴모가 없으면 게임 그만두기
    //게임을 그만둘대 나올 수 있는 넴모의 배치의 가짓수 구하기
    //N 행 25 M 열 25
    //알고리즘:백트래킹
    //풀이:
    //2x2인 사각형만 이루지않으면 된다.
    //메서드가 호출될때마다 2x2사각형인지 검사하고 사각형이면 x
    static int[][] map;
    static int N;
    static int M;
    static int count = 0;
    static int count2 = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        //map = new int[N+1][M];
        map = new int[N][M];
        dfs2(0, -1);
        //System.out.println(count/M);
        System.out.println(count2);
    }

    //시간 초과
    static void dfs(int r, int c){
        //System.out.println("=====================================");
        //System.out.println("[" + r + ", " + c + "]");
        //printMap();
        if (r >= N) {
            //System.out.println("끝, 증가");
            count++;
            return;
        }
        if (checkSquare(r, c)) {
            //System.out.println("넴모 완성, 쳐내기");
            return;
        }
        for(int m=c+1; m<=M; m++){
            if(m==M) {
                if(r==N) break;
                r++;
                m=0;
            }
            map[r][m]=1;
            dfs(r, m);
            map[r][m]=0;
        }
    }

    //성공
    static void dfs2(int r, int c) { //열값
        System.out.println("=====================================");
        System.out.println("[" + r + ", " + c + "]");
        printMap();
        count2++;
        //System.out.println("현재 count2:" + count2);

        //if문 가지치기
        if (checkSquare(r, c)) {
            //System.out.println("넴모 완성");
            count2--;
            return;
        }

        //모든 경우 검색하기
        for (int m = c + 1; m <= M; m++) { //열값
            //System.out.println("m:" + m + ", r:" + r);
            if (m == M) { //행값
                r++;
                m = 0;
            }
            if (r == N) break;
            map[r][m] = 1;
            dfs2(r, m);
            map[r][m] = 0;
        }
    }

    static boolean checkSquare(int x, int y) {
        if (x <= 0 || y <= 0) return false;
        if (map[x - 1][y - 1] == 1 && map[x - 1][y] == 1 && map[x][y - 1] == 1 && map[x][y] == 1) return true;
        else return false;
    }

    static void printMap() {
        System.out.println("-----------");
        for (int n = 0; n < N; n++) {
            System.out.println(Arrays.toString(map[n]));
        }
        System.out.println("-----------");
    }
}
