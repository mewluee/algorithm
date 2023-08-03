package 구현_implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 로봇청소기_14503 {

    private String site = "https://www.acmicpc.net/problem/14503";


    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    //90도 회전방향=인덱스

    static int N;
    static int M;

    static int[][] rooms;

    public static void result() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken());

        int count = 0;

        //하나로 처리 못하는 이유(벽인지/청소된 룸인지 구분하기 위해서) 다 1로 처리해버리면 안됨! 그럼 다른 숫자를 넣을까..?
        //int[][] cleaned = new int[N][M];
        rooms = new int[N][M];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int m = 0; m < M; m++) {
                rooms[n][m] = Integer.parseInt(st.nextToken());
            }
        }

        //System.out.println(Arrays.deepToString(rooms));

        //         북(0)
        //    서(3) ㅁ 동(1)
        //         남(2)
        //반시계방향으로 90도씩 회전

        //청소기가 종료되는 조건
        //>>바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다<<
        //후진가능하면 후진하고 청소여부 검사하기.

        //순서
        //1.현재칸 청소여부 확인 > 청소
        //2.4칸 중 청소되지않은 빈칸이 있는 경우.!!!! 중요한건 바라보는 방향부터 검사하는게 아님.
        // >> 빈칸있따! 그럼 (1) 90도 회전하고 (2) 정면에 공간잇으면 들어감.
        // >> 빈칸없다! 그럼 뒤로감!
        //순서 중요함!!!!

        //일단 조건문 어케할지...생각이안나니까 일단..이케하고
        while (true) {
            //System.out.println("-".repeat(10));
            //System.out.println("x,y:"+x+","+y+", direction:"+direction+", rooms:"+rooms[x][y]);
            //System.out.println("count:"+count);
            if (rooms[x][y] == 0) {//청소안한방이라면.
                //cleaned[x][y]=1; //청소시켜놓고.
                rooms[x][y] = 2; //청소표시는 숫자2로 해보자.
                count++;
            }

            //주변 검색해야함.
            if (noCleanRoomExist(x, y)) {
                //방향 회전시키자.
                direction = (direction + 3) % 4;
                int gx = x + dx[direction];
                int gy = y + dy[direction];

                if (1 <= gx && gx < N - 1 && 1 <= gy && gy < M - 1 && rooms[gx][gy] == 0) { //direction방향으로 앞쪽칸이 청소되지 않았다면
                    x = gx;
                    y = gy;
                    //갱신하고 while문 돌도록 한다.
                }

            } else {
                //System.out.println("후진!!!");
                //후진방향 설정해주자.
                //direction은 유지한 채 후진해야함.
                int backdirection = (direction + 2) % 4;
                int gx = x + dx[backdirection];
                int gy = y + dy[backdirection];
                //System.out.println("gx,gy:"+gx+","+gy+", rooms:"+rooms[gx][gy]);

                if (0 <= gx && gx < N && 0 <= gy && gy < M && rooms[gx][gy] == 1) { //후진방향이 벽일경우 <-> 0이나 2일 경우는 이동하도록함.
                    //System.out.println("작동멈춰!");
                    break; // 작동 멈추기
                } else {
                    //System.out.println("벽이 아니니까 이동해!");
                    x = gx;
                    y = gy;
                    //갱신하고 while문 돌도록 한다.
                }
            }

        }

        System.out.println(count);


    }

    public static boolean noCleanRoomExist(int x, int y) {
        boolean flag = false;

        for (int i = 0; i < 4; i++) {
            int gx = x + dx[i];
            int gy = y + dy[i];

            if (1 <= x && x < N - 1 && 1 <= y && y < M - 1) { //제일 외곽 벽을 제외한 공간.
                if (rooms[gx][gy] == 0) { //x,y를 기준으로 4방향중 하나에 공간이 있다!
                    flag = true;
                    break;
                }
            }
        }
        //System.out.println("flag:"+flag);
        return flag;
    }

}
