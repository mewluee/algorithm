package 구현_implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 마법사상어와토네이도_20057 {

    static int N;
    static int[][] map;
    static int result;
    static int[][][] sandSpread = {
            // ←
            {{0,-1}, {0, -2, 5}, {-2, 0, 2}, {2, 0, 2},
                    {-1, -1, 10}, {-1, 0, 7}, {-1, 1, 1},
                    {1, -1, 10}, {1, 0, 7}, {1, 1, 1}},
            // ↓
            {{1,0},{2, 0, 5}, {0, -2, 2}, {0, 2, 2},
                    {-1, 1, 1},{0, 1, 7},{1, 1, 10},
                    {-1, -1, 1}, {0, -1, 7}, {1, -1, 10}},
            // →
            {{0,1},{0, 2, 5}, {-2, 0, 2}, {2, 0, 2},
                    {-1, -1, 1}, {-1, 0, 7}, {-1, 1, 10},
                    {1, -1, 1}, {1, 0, 7}, {1, 1, 10}},
            // ↑
            {{-1,0},{-2, 0, 5}, {0, -2, 2}, {0, 2, 2},
                    {-1, 1, 10},{0, 1, 7},{1, 1, 1},
                    {-1, -1, 10}, {0, -1, 7}, {1, -1, 1}}
    };
    static int[][][] sandSpread2 = {
            // ←
            {{0, -2, 5}, {-2, 0, 2}, {2, 0, 2},
                    {-1, -1, 10}, {-1, 0, 7}, {-1, 1, 1},
                    {1, -1, 10}, {1, 0, 7}, {1, 1, 1},
                    {0, -1, 0}},
            // ↓
            {{2, 0, 5}, {0, -2, 2}, {0, 2, 2},
                    {-1, 1, 1}, {0, 1, 7}, {1, 1, 10},
                    {-1, -1, 1}, {0, -1, 7}, {1, -1, 10},
                    {1, 0, 0}},
            // →
            {{0, 2, 5}, {-2, 0, 2}, {2, 0, 2},
                    {-1, -1, 1}, {-1, 0, 7}, {-1, 1, 10},
                    {1, -1, 1}, {1, 0, 7}, {1, 1, 10},
                    {0, 1, 0}},
            // ↑
            {{-2, 0, 5}, {0, -2, 2}, {0, 2, 2},
                    {-1, 1, 10}, {0, 1, 7}, {1, 1, 1},
                    {-1, -1, 10}, {0, -1, 7}, {1, -1, 1},
                    {-1, 0, 0}}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        result = 0;
        for (int n = 0; n < N; n++) {
            map[n] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        moveTornado2(N / 2, N / 2);
        System.out.println(result);
    }

    static void moveTornado(int x, int y) {

        for (int i = 1; i < N; i = i + 2) {
            //N이 7인 경우 1, 3, 5
            //    5       1  3
            for (int left = 0; left < i; left++) {
                spreadSand(x, y, 0);
                y -= 1;
            }
            for (int down = 0; down < i; down++) {
                spreadSand(x, y, 1);
                x += 1;
            }
            for (int right = 0; right < i + 1; right++) {
                spreadSand(x, y, 2);
                y += 1;
            }
            for (int up = 0; up < i + 1; up++) {
                spreadSand(x, y, 3);
                x -= 1;
            }
        }
        for (int left = 1; left < N; left++) {
            spreadSand(x, y, 0);
            y -= 1;
        }

    }

    static void moveTornado2(int x, int y) {

        for (int i = 1; i < N; i = i + 2) {
            //N이 7인 경우 1, 3, 5
            //    5       1  3
            for (int left = 0; left < i; left++) {
                spreadSand2(x, y, 0);
                y -= 1;
            }
            for (int down = 0; down < i; down++) {
                spreadSand2(x, y, 1);
                x += 1;
            }
            for (int right = 0; right < i + 1; right++) {
                spreadSand2(x, y, 2);
                y += 1;
            }
            for (int up = 0; up < i + 1; up++) {
                spreadSand2(x, y, 3);
                x -= 1;
            }
        }
        for (int left = 1; left < N; left++) {
            spreadSand2(x, y, 0);
            y -= 1;
        }

    }
    //시작 지점 x(x, y)
    static void spreadSand(int x, int y, int direction) {
        //방향대로 이동 후 도착지점 y(dx,dy)
        //이동 지점의 모래가 흩뿌려진다.
        int dx=x+ sandSpread[direction][0][0];
        int dy=y+ sandSpread[direction][0][1];
        int big_sand=map[dx][dy];
        map[dx][dy]=0;
        int alpha=big_sand;
        for (int i = 1; i <=9 ; i++) {
            int sx=dx+sandSpread[direction][i][0];
            int sy=dy+sandSpread[direction][i][1];
            int small_sand=big_sand*sandSpread[direction][i][2]/100;
            alpha-=small_sand;
            if(0<=sx && sx<N && 0<=sy && sy<N) map[sx][sy]+=small_sand;
            else result+=small_sand;
        }
        int ax=dx+sandSpread[direction][0][0];
        int ay=dy+sandSpread[direction][0][1];
        if(0<=ax && ax<N && 0<=ay && ay<N) map[ax][ay]+=alpha;
        else result+=alpha;
    }

    //시작 지점 x(x, y)
    static void spreadSand2(int x, int y, int direction) {
        //방향대로 이동 후 도착지점 y(dx,dy)
        //이동 지점의 모래가 흩뿌려진다.
        int dx = x + sandSpread2[direction][9][0];
        int dy = y + sandSpread2[direction][9][1];
        int big_sand = map[dx][dy];
        map[dx][dy] = 0;
        int alpha = big_sand;
        for (int i = 0; i <= 9; i++) {
            int sx = dx + sandSpread2[direction][i][0];
            int sy = dy + sandSpread2[direction][i][1];
            int small_sand = big_sand * sandSpread2[direction][i][2] / 100;
            alpha -= small_sand;
            if (0 <= sx && sx < N && 0 <= sy && sy < N) {
                if (i == 9)
                    map[sx][sy] += alpha;
                else
                    map[sx][sy] += small_sand;
            } else {
                if (i == 9)
                    result += alpha;
                else
                    result += small_sand;
            }
        }
    }

    static void printMap() {
        System.out.println("map");
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println("---------------");
    }
}
