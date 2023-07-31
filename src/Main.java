import queue.CompetitiveContagion18405;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

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

    static Point[][] map;
    static PriorityQueue<Point> queue;
    static int[][] directions = {
            {0, 0}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}
            //O, ↑, ↖, ←, ↙, ↓, ↘, →, ↗
    };

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new Point[4][4];
        queue = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.fishNumber - o2.fishNumber;
            }
        });

        for (int i = 0; i < 4; i++) {
            int[] inputs = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int j = 0; j < inputs.length; j = j + 2) {
                // 0 1 0,0
                // 2 3 0,1
                // 4 5 0,2
                // 6 7 0,3
                Point point = new Point(i, j / 2, inputs[j], inputs[j + 1]);
                //map[i][j / 2] = point;
                queue.add(point);
            }
        }
        //printMap();
        fishSwim();
    }

    public static void fishSwim() {

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            System.out.println(point.toString());
            int dx=point.x+directions[point.direction][0];
            int dy=point.y+directions[point.direction][1];
            if (0 <= dx && dx < 4 && 0 <= dy && dy < 4) {
                if()
            }
        }
    }

    public static void printMap() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(map[i][j].toString());
            }
            System.out.println();
        }
    }

    static class Point {
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
        public String toString() {
            return "[" + fishNumber +
                    "," + direction +
                    "] ";
        }
    }

}
