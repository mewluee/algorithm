package 구현_implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 스타트택시_19238_시간초과 {
    static int[][] map;
    static int N;
    static int M;
    static int fuel;
    static boolean goal = false;
    static Queue<Passenger> list = new LinkedList();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int n2 = 1; n2 <= N; n2++) {
                if (Integer.parseInt(st.nextToken()) == 1)
                    map[n][n2] = 1; // 1은 벽
            }
        }
        st = new StringTokenizer(br.readLine(), " ");
        int start_x = Integer.parseInt(st.nextToken());
        int start_y = Integer.parseInt(st.nextToken());

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine(), " ");
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            int ex = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());
            Passenger p = new Passenger(new int[] { sx, sy }, new int[] { ex, ey });
            list.add(p);
        }
        move(start_x, start_y);

        if (goal)
            System.out.println(fuel);
        else
            System.out.println(-1);
    }

    static void move(int x, int y) {
        PriorityQueue<Passenger> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1.distance == o2.distance)
                if (o1.start[0] == o2.start[0])
                    return o1.start[1] - o2.start[1];
                else
                    return o1.start[0] - o2.start[0];
            else
                return o1.distance - o2.distance;
        });

        while (!list.isEmpty() || fuel <= 0) {
            int listSize=list.size();
            for(int i=0; i<listSize; i++) {
                Passenger p=list.poll();
                int d=getDistance(x, y, p.start[0], p.start[1]);
                if(d>=0) {
                    p.distance = d;
                    queue.add(p);
                }
            }
            //System.out.println("남은 list size:"+list.size());
            //System.out.println("남은 queue size:"+queue.size());
            //for (Passenger p2 : queue) {
            //	p2.print();
            //}
            //System.out.println("이동 시작!");
            //System.out.println("이동 전 연료:"+fuel);
            if(queue.isEmpty()) return;
            Passenger p = queue.poll();
            // 승객 출발지에 도착해서 태움!
            fuel -= p.distance;
            if (fuel <= 0) {
                return;
            }
            x = p.start[0];
            y = p.start[1];
            //System.out.println("손님 태운 후 연료:"+fuel);
            int go = getDistance(x, y, p.end[0], p.end[1]);
            if(fuel <go) {
                return;
            }
            x=p.end[0];
            y=p.end[1];
            fuel+=go;
            //System.out.println("도착지:["+x+", "+y+"] 남은 연료:"+fuel);
            list.addAll(queue);
            queue.clear();
        }

        goal = true;
    }

    static int getDistance(int sx, int sy, int ex, int ey) {
        //System.out.println("getD [" + sx + ", " + sy + "] -> [" + ex + ", " + ey + "]");
        int distance = -1;
        Queue<int[]> q = new LinkedList();
        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };
        q.add(new int[] { sx, sy, 0 });
        boolean[][] visited=new boolean[N+1][N+1];
        visited[sx][sy]=true;
        while (!q.isEmpty()) {
            int[] now = q.poll();
            if (now[0] == ex && now[1] == ey) {
                distance = now[2];
                break;
            }
            for (int i = 0; i < 4; i++) {
                int tx = now[0] + dx[i];
                int ty = now[1] + dy[i];
                if (tx < 1 || N < tx || ty < 1 || N < ty)
                    continue;
                if (map[tx][ty] == 1 || visited[tx][ty])
                    continue;
                visited[tx][ty]=true;
                q.add(new int[] { tx, ty, now[2] + 1 });
            }
        }
        //System.out.println("getD:" + distance);
        return distance;
    }

    static void printMap(int[][] map) {
        for (int i = 0; i <= N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }

    static class Passenger {
        int[] start;
        int[] end;
        int distance;

        Passenger(int[] start, int[] end) {
            this.start = start;
            this.end = end;
            distance = 0;
        }

        void print() {
            System.out.println("----passenger----");
            System.out.println("start:" + Arrays.toString(start));
            System.out.println("end:" + Arrays.toString(end));
            System.out.println("distance:" + distance);
        }
    }
}
