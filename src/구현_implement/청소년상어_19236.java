package 구현_implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 청소년상어_19236 {

    //4x4 공간(총 16개)
    //각 물고기 -> 번호(1~16) 방향(상하좌우+대각선=8개)
    //시작: (0,0)칸에 있는 물고기를 먹으면서 시작한다.
    //
    //1. 물고기 이동
    //물고기는 번호가 작은 물고기부터 순서대로 이동
    //이동:한칸 이동,
    //이동 가능-> 빈칸, 다른 물고기가 있는 칸(서로 위치를 바꾼다) / 이동 불가능 -> 상어가 있는 칸, 공간 경계를 넘는 칸
    //방향이 이동할 수 있는 칸을 향할 때까지 방향을 45도씩 반시계 회전
    //이동할 수 있는 칸이 없으면 이동 X
    //
    //2. 상어 이동
    //방향으로 이동
    //한 번에 여러개의 칸을 이동, 물고기가 있는 칸으로 이동하면 물고기를 먹고, 그 물고기의 방향을 갖는다.
    //물고기가 없는 칸으로 이동 X
    //이동할 수 있는 칸이 없으면 공간에서 벗어나 집으로 간다.

    //static Point[][] map;
    static ArrayList<Point> lists;
    static int[][] directions = {
            {0, 0}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}
            //O, ↑, ↖, ←, ↙, ↓, ↘, →, ↗
    };
    static int count;
    static int max_count;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Point[][] input_map = new Point[4][4];

        lists = new ArrayList<>();
        count = 0;
        max_count = 0;
        for (int i = 0; i < 4; i++) {
            int[] inputs = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int j = 0; j < inputs.length; j = j + 2) {
                //한줄 : 0 1 [0,0] / 2 3 [0,1] / 4 5 [0,2] / 6 7 [0,3]
                Point point = new Point(i, j / 2, inputs[j], inputs[j + 1]);
                input_map[i][j / 2] = point;
            }
        }

        Point start = input_map[0][0];
        count += start.fishNumber;
        start.fishNumber = 0;

        sharkEatFish(start, input_map);

        System.out.println(max_count);
    }

    public static Point[][] fishSwim(Point[][] map) {
        //배열 깊은 복사
        Point[][] map2 = new Point[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Point point = map[i][j];
                map2[i][j] = new Point(point.x, point.y, point.fishNumber, point.direction);
            }
        }
        mapToFishLists(map2);

        for (int n = 0; n < lists.size(); n++) {

            Point now = lists.get(n); //현재 물고기

            for (int m = 0; m < 9; m++) { //현재 방향부터 반시계로 45도씩 회전

                int direct_num = (now.direction + m) % 9; // 결과로 1~8가 나와야하니까 9의 나머지를 구한다
                if (direct_num == 0) continue; // 0 방향은 없으니까 패스

                int dx = now.x + directions[direct_num][0];
                int dy = now.y + directions[direct_num][1];

                if (0 <= dx && dx < 4 && 0 <= dy && dy < 4) { //공간 안
                    Point go = map2[dx][dy];

                    if (go.fishNumber != 0) { //물고기면 스위칭 (상어=0)
                        //깊은 복사한 map2에 저장된 go와 now를 스위칭한다.
                        go.x = now.x;
                        go.y = now.y;

                        now.x = dx;
                        now.y = dy;
                        now.direction = direct_num; //[중요] 방향이 바뀜..!

                        map2[go.x][go.y] = go;
                        map2[now.x][now.y] = now;

                        break; //스위칭하면 for문 종료시키기
                    }
                }
            }
        }

        return map2;
    }

    //조합짜기..
    public static void sharkEatFish(Point pshark, Point[][] map) {

        if (count > max_count) max_count = count;

        //물고기 이동 후에 맵 깊은 복사
        Point[][] nmap = fishSwim(map);

        //상어 이동 (최대 3칸)
        for (int i = 1; i <= 3; i++) {

            Point shark = nmap[pshark.x][pshark.y];
            int x = shark.x + directions[shark.direction][0] * i;
            int y = shark.y + directions[shark.direction][1] * i;

            if (0 <= x && x < 4 && 0 <= y && y < 4) { //범위 안에 있으면 먹으면 된다
                Point go = nmap[x][y];
                if (go.fishNumber != -1) { //물고기가 있으면 고
                    int goFishNum = go.fishNumber; //밑에서 값이 바껴서 임시로 값을 저장한다

                    shark.fishNumber = -1; //먹고 지나감
                    count += go.fishNumber;
                    go.fishNumber = 0; //현재 이동한 상어 위치

                    sharkEatFish(go, nmap);

                    go.fishNumber = goFishNum; //다시 물고기 원래대로 돌려놓기
                    count -= go.fishNumber;
                    shark.fishNumber = 0; //다시 상어 원래대로 돌려놓기
                }
            }
        }
    }

    public static void mapToFishLists(Point[][] map) {
        lists.clear();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (map[i][j].fishNumber == -1 || map[i][j].fishNumber == 0) continue;
                lists.add(map[i][j]);
            }
        }
        Collections.sort(lists);
    }

    static class Point implements Comparable<Point> {
        int x;
        int y;
        int fishNumber;
        int direction;

        public Point(int x, int y, int fishNumber, int direction) {
            this.x = x;
            this.y = y;
            this.fishNumber = fishNumber;
            this.direction = direction;
        }

        @Override
        public int compareTo(Point o) {
            return fishNumber - o.fishNumber;
        }
    }

}
