package 구현_implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class S4_2578_빙고 {
    //문제: 실버4 2578 빙고
    //알고리즘:구현
    //요약:
    //5x5 빙고판, 1~25 숫자입력
    //가로줄/세로줄/대각선줄 빙고
    //빙고를 외치게 되는 몇번째 수
    //1~5째줄, 빙고판
    //6~10째줄, 사회자가 부르는 수가 5개씩 빈칸을 두고 한줄에.

    static int[][] map;
    static boolean alreadyCheckBL2TR;
    static boolean alreadyCheckTL2BR;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[5][5];
        int[] nums = new int[25];
        int bingoCount = 0;
        alreadyCheckBL2TR = false;
        alreadyCheckTL2BR = false;

        for (int n = 0; n < 5; n++) {
            map[n] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        for (int n = 0; n < 5; n++) {
            int[] inputs = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            System.arraycopy(inputs, 0, nums, 5 * n, 5);
        }

        //System.out.println(Arrays.toString(nums));

        for (int i = 0; i < 25; i++) {
            //System.out.println("i:"+i+", "+nums[i]);
            int[] position = getNumPosition(nums[i]);
            map[position[0]][position[1]] = -1; //x처리
            //print();
            if (isHorizontalBingo(position[0])) bingoCount++;
            if (isVerticalBingo(position[1])) bingoCount++;
            if (!alreadyCheckBL2TR && isBottomLeftToTopRightBingo()) bingoCount++;
            if (!alreadyCheckTL2BR && isTopLeftToBottomRightBingo()) bingoCount++;
            //System.out.println("bingo count:"+bingoCount);
            if (bingoCount >= 3) {
                System.out.println(i + 1);
                return;
            }
            //System.out.println("---------------");
        }

    }

    static void print() {
        for (int n = 0; n < 5; n++) {
            System.out.println(Arrays.toString(map[n]));
        }
    }

    static int[] getNumPosition(int num) {
        for (int n = 0; n < 5; n++) {
            for (int m = 0; m < 5; m++) {
                if (map[n][m] == num) {
                    return new int[]{n, m};
                }
            }
        }
        return new int[]{-1, -1};
    }

    static boolean isHorizontalBingo(int row) {
        for (int n = 0; n < 5; n++) {
            if (map[row][n] != -1) return false;
        }
        //System.out.println("수평 검사 true");
        return true;
    }

    static boolean isVerticalBingo(int col) {
        for (int n = 0; n < 5; n++) {
            if (map[n][col] != -1) return false;
        }
        //System.out.println("수직 검사 true");
        return true;
    }

    static boolean isBottomLeftToTopRightBingo() {
        for (int n = 0; n < 5; n++) {
            if (map[4 - n][n] != -1) return false;
        }
        //System.out.println("왼쪽하단에서 오른쪽상단 대각선 true");
        alreadyCheckBL2TR = true;
        return true;
    }

    static boolean isTopLeftToBottomRightBingo() {
        for (int n = 0; n < 5; n++) {
            if (map[n][n] != -1) return false;
        }
        //System.out.println("왼쪽상단에서 오른쪽하단 대각선 true");
        alreadyCheckTL2BR = true;
        return true;
    }
}
