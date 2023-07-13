package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 사다리조작_15684 {

    static int N;
    static int M;
    static int H;

    static boolean[][] map;

    static int result;
    static int min;
    static boolean isImposible = true;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        result = 0;
        min = Integer.MAX_VALUE;
        map = new boolean[H + 1][N + 2];

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = true;
            //map[a][b+1]=true;
        }
        for (int h = 0; h < H + 1; h++) {
            map[h][N + 1] = true;
        }

        //System.out.println(ladderClimbing(1, 1));
        makeCombination(1, 1, true);

        //체크가 단한번도 true가 안나올 경우는 어떻게 표현하는가. 흠..
        if (min > 3 || isImposible) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }

    public static void makeCombination2(int row) {
        if (row > H) return;
        for (int h = row; h <= H; h++) {
            for (int n = 1; n < N; n++) {
                makeCombination2(h);
            }
        }
    }

    public static void makeCombination(int row, int col, boolean flag) {
        if (flag) {
            if (check()) {
                isImposible = false;
                if (result < min) min = result;
            }
        }
        if (row > H || result >= 3) return;

        for (int n = col; n <= N; n++) {
            if (map[row][n] || map[row][n + 1] || map[row][n - 1]) {
                //행 증가
                if (n == N) makeCombination(row + 1, 1, false);
                continue;
            }
            map[row][n] = true;
            result++;
            //기본적으로 열? 증가
            makeCombination(row, n + 1, true);
            map[row][n] = false;
            result--;
        }

    }

    public static int ladderClimbing(int row, int col) {
        int result = col;
        for (int h = row; h <= H; h++) {
            if (!map[h][col] && !map[h][col - 1]) continue;
            if (map[h][col - 1])
                result = ladderClimbing(h + 1, col - 1);
            else
                result = ladderClimbing(h + 1, col + 1);
            break;
        }
        return result;
    }

    public static boolean check() {
        for (int n = 1; n <= N; n++) {
            if (n != ladderClimbing(1, n)) return false;
        }
        return true;
    }

    public static void printArr(boolean[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
    }

//
//    public static void makeCombination(int row, int col, boolean flag) {
//        if (flag) {
//            //System.out.println("=======================");
//            //System.out.println("row:" + row + ", col:" + col);
//            //printArr(map);
//            if (check()) {
//                isImposible=false;
//                //printArr(map);
//                //System.out.println(result);
//                if (result < min) {
//                    min = result;
//                }
//            }
//            //System.out.println("-----------------------------------");
//        }
//        if (row > H || result>=3) return;
//
//        for (int n = col; n <= N; n++) {
//            //System.out.println("n:" + n);
//            if (map[row][n] || map[row][n + 1] || map[row][n - 1]) {
//                if (n == N) {
//                    //System.out.println("다음 줄(행)로 가야함");
//                    makeCombination(row + 1, 1, false);
//                }
//                continue;
//            }
//            map[row][n] = true;
//            result++;
//            //map[row][n+1]=true;
//            //System.out.println("다음 칸으로 가야함");
//            makeCombination(row, n + 1, true);
//            map[row][n] = false;
//            result--;
//            //map[row][n+1]=false;
//        }
//
//    }
//
//
//    public static int ladderClimbing(int row, int col) {
//        //System.out.println("row:"+row+", col:"+col);
//        int result = col;
//        for (int h = row; h <= H; h++) {
//            //System.out.println("h:"+h);
//            if (!map[h][col] && !map[h][col - 1]) continue;
//            if (map[h][col - 1]) {
//                //System.out.println("이동 col:"+(col-1));
//                result = ladderClimbing(h + 1, col - 1);
//            } else {
//                //System.out.println("이동 col:"+(col+1));
//                result = ladderClimbing(h + 1, col + 1);
//            }
//            break;
//        }
//        //System.out.println("dd");
//        return result;
//    }
}
