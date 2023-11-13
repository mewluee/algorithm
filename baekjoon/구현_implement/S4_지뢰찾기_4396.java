package 구현_implement;

import java.io.*;
import java.util.ArrayList;

public class S4_지뢰찾기_4396 {
    //문제: 실버4 4396 지뢰찾기
    //알고리즘:구현
    //요약:
    //맵은 n x n
    //m개의 지뢰
    //지뢰가 없는 지점 선택 -> 인접한 8개의 칸에 지뢰가 몇개 있는지 알려준다. 0~8 숫자로
    //*은 지뢰가 있는 지점
    //.은 지뢰가 없는 지점
    //이미 열린칸은 소문자 영어 x
    //열리지 않은 칸은 .
    //풀이:
    //x로 폭탄을 건들일 경우 모든 폭탄들이 결과물에 표시되어야 함.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        char[][] answer = new char[N][N];
        char[][] result = new char[N][N];
        int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
        ArrayList<int[]> bombs = new ArrayList<>();
        boolean isFail = false;

        for (int n = 0; n < N; n++) {
            answer[n] = br.readLine().toCharArray();
            for (int m = 0; m < N; m++) {
                if (answer[n][m] == '*') bombs.add(new int[]{n, m});
            }
        }

        for (int n = 0; n < N; n++) {
            char[] inputs = br.readLine().toCharArray();
            for (int m = 0; m < N; m++) {
                if (inputs[m] == '.') {
                    result[n][m] = '.';
                } else {
                    if (answer[n][m] == '*') {
                        isFail = true;
                    } else {
                        int count = 0;
                        for (int d = 0; d < 8; d++) {
                            int x = n + dx[d];
                            int y = m + dy[d];
                            if (x < 0 || N <= x || y < 0 || N <= y) continue;
                            if (answer[x][y] == '*') count++;
                        }
                        result[n][m] = Character.forDigit(count, 10);
                    }
                }
            }
        }

        if (isFail) {
            for (int[] pos : bombs)
                result[pos[0]][pos[1]] = '*';
        }

        for (int n = 0; n < N; n++) {
            for (int m = 0; m < N; m++) {
                bw.append(result[n][m]);
            }
            bw.append("\n");
        }
        bw.flush();
        bw.close();

    }
}
