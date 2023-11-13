package 구현_implement;

import java.io.*;

public class S3_달팽이_1913 {
    //문제: 실버3 1913 달팽이
    //알고리즘:구현
    //요약:
    //홀수 자연수 N
    //1~N^2 까지의 자연수를 달팽이 모양으로 NxN 표에 채운다
    //NxN 달팽이집 출력
    //어떤 수를 입력 -> 달팽이 집의 위치 출력

    static int[][] map;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        int half = N / 2;
        int num = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int n = half; n >= 0; n--) {
            //System.out.println("n:"+n);
            //밑으로
            int start = (n * 2 + 1) * (n * 2 + 1);
            map[half - n][half - n] = start;
            //System.out.println("half-n:"+(half-n)+", "+map[half-n][half-n]);
            for (int down = 1; down <= n * 2; down++) {
                map[half - n + down][half - n] = --start;
            }
            for (int right = 1; right <= n * 2; right++) {
                map[half - n + n * 2][half - n + right] = --start;
            }
            for (int up = 1; up <= n * 2; up++) {
                map[half - n + n * 2 - up][half - n + n * 2] = --start;
            }
            for (int left = 1; left < n * 2; left++) {
                map[half - n][half - n + n * 2 - left] = --start;
            }
        }
        String pos = "";
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < N; m++) {
                bw.append(map[n][m] + " ");
                if (map[n][m] == num)
                    pos += (n + 1) + " " + (m + 1);
            }
            bw.append("\n");
        }
        bw.append(pos);
        bw.flush();
        bw.close();
    }
}
