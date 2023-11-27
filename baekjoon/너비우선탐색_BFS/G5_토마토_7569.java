package 너비우선탐색_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class G5_토마토_7569 {
    //문제: 골드5 토마토 7569
    //요약:
    //3차원 맵
    //토마토는 밑의 상자부터 위의 상자로 입력된다.
    //대각선으로는 영향을 주지 않는다.
    //며칠이 지나야 다 익는지 최소 일수 구하기
    //1은 익은토마토
    //0은 안익은 토마토
    //-1 빈공간

    //풀이:BFS

    static int N;
    static int M;
    static int H;

    static int[] dx = {0, 0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, 0, -1, 1};
    static int[] dh = {1, -1, 0, 0, 0, 0};
    static int[][][] map;

    //앞,뒤,위,아래,왼쪽,오른쪽
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        M = Integer.parseInt(st.nextToken()); //맵 열 2~100
        N = Integer.parseInt(st.nextToken()); //맵 행 2~100

    }
}
