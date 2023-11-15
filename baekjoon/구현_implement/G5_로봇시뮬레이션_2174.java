package 구현_implement;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class G5_로봇시뮬레이션_2174 {

    //문제: 골드5 2174 로봇 시뮬레이션
    //알고리즘:구현
    //요약:
    //가로 A 1~100
    //세로 B 1~100
    //로봇 N 1~100 -> 초기위치 x, y 좌표, 방향 있음
    //왼쪽하단부터 1,1 ~ 오른쪽상단 B,A
    //명령 M 1~100
    //동시에 여러 로봇이 명령 실행하지 않고, 로봇 하나씩 순차로 실행된다.
    //3개의 명령
    //L : 로봇이 향하고있는 방향 기준으로 왼쪽으로 90도 회전
    //R : // 오른쪽으로 90도 회전
    //F : // 앞으로 한칸 이동
    //잘못된 명령
    //맵 밖으로는 안간다.
    //x번 로봇이 움직이다가 y번 로봇에 충돌
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken()); //가로=열의 개수
        int B = Integer.parseInt(st.nextToken()); //세로=행의 개수
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //로봇의 초기위치와 방향
        int M = Integer.parseInt(st.nextToken()); //명령
        //위0 오1 아2 왼3
        List<int[]> robots = new ArrayList<>();
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int[][] map = new int[B][A];

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            char d = st.nextToken().charAt(0);
            switch (d) {
                case 'N':
                    robots.add(new int[]{B - y, x - 1, 0});
                    break;
                case 'E':
                    robots.add(new int[]{B - y, x - 1, 1});
                    break;
                case 'S':
                    robots.add(new int[]{B - y, x - 1, 2});
                    break;
                default:
                    robots.add(new int[]{B - y, x - 1, 3});
                    break;
            }
            map[B - y][x - 1] = n + 1;
            //System.out.println(Arrays.toString(robots.get(n)));
        }

        //print(B, map);

        for (int m = 0; m < M; m++) {
            //System.out.println("----------------");
            //System.out.println("m:"+m);
            st = new StringTokenizer(br.readLine());
            int robotNum = Integer.parseInt(st.nextToken());
            char orderType = st.nextToken().charAt(0);
            int repeat = Integer.parseInt(st.nextToken());
            //System.out.println("robotNum:"+robotNum+", order type:"+orderType+", repeat:"+repeat);
            int[] robot = robots.get(robotNum - 1);
            //print(B, map);
            for (int r = 0; r < repeat; r++) {
                //System.out.println("r:"+r);
                switch (orderType) {
                    case 'L':
                        //System.out.print("왼쪽으로 회전 로봇방향:"+robot[2]);
                        robot[2] = robot[2] - 1 < 0 ? 3 : robot[2] - 1;
                        //System.out.println("->"+robot[2]);
                        break;
                    case 'R':
                        //System.out.print("오른쪽으로 회전 로봇방향:"+robot[2]);
                        robot[2] = robot[2] + 1 > 3 ? 0 : robot[2] + 1;
                        //System.out.println("->"+robot[2]);
                        break;
                    default:
                        //System.out.println("로봇방향으로 한칸 전진:"+robot[2]);
                        //System.out.print("["+robot[0]+", "+robot[1]+"]");
                        int mx = robot[0] + dx[robot[2]];
                        int my = robot[1] + dy[robot[2]];
                        //System.out.println("->["+mx+", "+my+"]");
                        if (mx < 0 || B <= mx || my < 0 || A <= my) {
                            System.out.println("Robot " + robotNum + " crashes into the wall");
                            return;
                        }
                        if (map[mx][my] != 0) {
                            System.out.println("Robot " + robotNum + " crashes into robot " + map[mx][my]);
                            return;
                        }
                        map[robot[0]][robot[1]] = 0;
                        robot[0] = mx;
                        robot[1] = my;
                        map[mx][my] = robotNum;
                        //print(B, map);

                }
            }

        }
        System.out.println("OK");
        return;
    }

    static void print(int B, int[][] map) {
        for (int n = 0; n < B; n++) {
            System.out.println(Arrays.toString(map[n]));
        }
    }
}
