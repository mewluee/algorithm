package 백트래킹_BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class G4_감시_15683 {

    //문제: 실버3 개발자 지망생 구름이의 취업 뽀개기
    //요약:
    //n x m 최대 8
    //k개 cctv 최대 8개
    //1번 1쪽 4방향
    //2번 2쪽 2방향
    //3번 2쪽 직각 4방향
    //4번 3쪽 4방향
    //5번 4쪽 1방향
    //벽 통과 x cctv 통과 o cctv로 못보는곳=사각지대
    //대각선 불가
    //빈칸 0
    //벽 6
    //cctv 번호 1~5
    //감시가능영역 #
    //출력:사각지대의 최소 크기
    //알고리즘:브루트포스


    static ArrayList<int[]>[] cctv_settings;
    static int N;
    static int M;
    static int[] dx = {-1, 1, 0, 0}; //위0 아래1 좌2 우3
    static int[] dy = {0, 0, -1, 1};
    static int[][] cctvs = new int[8][];
    static int index = 0;
    static int answer = 100;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); //행
        M = Integer.parseInt(st.nextToken()); //열

        char[][] input = new char[N][M];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int m = 0; m < M; m++) {
                input[n][m] = st.nextToken().charAt(0);
                if (input[n][m] != '0' && input[n][m] != '6') { //벽과 빈공간이 아닌 cctv라면
                    cctvs[index] = new int[]{input[n][m] - '0', n, m}; //cctv번호, 좌표
                    index++;
                }
            }
            //System.out.println(input[n]);
        }

        setting();
        dfs(input, 0);
        System.out.println(answer);
    }

    static void setting() {

        cctv_settings = new ArrayList[6];
        for (int n = 0; n < 6; n++) {
            cctv_settings[n] = new ArrayList<>();
        }
        for (int i = 0; i < 4; i++) {
            cctv_settings[1].add(new int[]{i});
        }
        cctv_settings[2].add(new int[]{0, 1});
        cctv_settings[2].add(new int[]{2, 3});

        cctv_settings[3].add(new int[]{0, 3}); //위우
        cctv_settings[3].add(new int[]{3, 1}); //우아래
        cctv_settings[3].add(new int[]{1, 2}); //아래좌
        cctv_settings[3].add(new int[]{2, 0}); //좌위

        cctv_settings[4].add(new int[]{2, 0, 3}); //좌위우
        cctv_settings[4].add(new int[]{0, 3, 1}); //위우아래
        cctv_settings[4].add(new int[]{3, 1, 2}); //우아래좌
        cctv_settings[4].add(new int[]{1, 2, 0}); //아래좌위

        cctv_settings[5].add(new int[]{0, 1, 2, 3});
    }

    static void dfs(char[][] map, int count) {
        //System.out.println("------------------count:"+count);
        //System.out.println("현재 맵");
//		for(int n=0; n<N; n++) {
//			System.out.println(Arrays.toString(map[n]));
//		}
        if (count >= index) {
            int zeroRoom = countRoom(map);
            if (zeroRoom < answer) answer = zeroRoom;
            return;
        }
        int[] now = cctvs[count];
        //System.out.println("now[]:"+Arrays.toString(now));
        int num = now[0];
        int x = now[1];
        int y = now[2];

        //System.out.println("size:"+cctv_settings[num].size());
        for (int i = 0; i < cctv_settings[num].size(); i++) {

            //맵 복사
            //System.out.println("맵 깊은 복사:주소값다름");
            char[][] copy = new char[N][M];
            for (int n = 0; n < N; n++) {
                copy[n] = map[n].clone();
                //System.out.println(Arrays.toString(copy[n]));
            }

            //System.out.println("i:"+i);
            //System.out.println("방향:"+Arrays.toString(directions));

            fillRoom(copy, x, y, cctv_settings[num].get(i));
            dfs(copy, count + 1);
        }
    }


    static int countRoom(char[][] map) {
        int result = 0;
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (map[n][m] == '0') result++;
            }
        }
        return result;
    }

    static void fillRoom(char[][] map, int sx, int sy, int[] directions) {
        boolean wall = false;
        for (int i = 0; i < directions.length; i++) {
            int d = directions[i];
            wall = false;
            for (int j = 1; j <= 8; j++) {
                int x = sx + dx[d] * j;
                int y = sy + dy[d] * j;
                if (x < 0 || N <= x || y < 0 || M <= y) continue;
                if (map[x][y] == '6') wall = true;
                if (wall) continue;
                if (map[x][y] != '0') continue;
                map[x][y] = '#';
            }
        }
    }
}
