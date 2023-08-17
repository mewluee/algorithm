package 큪_Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class 경쟁적전염_18405 {

    public static void main(String[] args) throws IOException {

        // N x N 시험관
        // 모든 바이러스는 1번부터 K번까지의 바이러스 종류 중 하나에 속한다.
        // 1초마다 상하좌우 방향으로 증식
        // 번호가 낮은 종류의 바이러스부터 먼저 증식한다.
        // S초가 지난 후에 (x,y)에 존재하는 바이러스의 종류를 출력하는 프로그램.
        // 존재하지 않는다면 0 출력하는 프로그램

        // DFS(1.재귀방식/2.???방식)

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs=br.readLine().split(" ");
        int N = Integer.parseInt(strs[0]);
        int K = Integer.parseInt(strs[1]); //K이하 숫자의 바이러스
        int[][] map=new int[N][N];

        boolean[][] visited = new boolean[N][N];

        //[상 하 좌 우]
        //왼쪽 상단 (0,0)
        //오른쪽 하단 (N-1, N-1)
        int[] gx = new int[]{-1, 1, 0, 0};
        int[] gy = new int[]{0, 0, -1, 1};

        //우선순위큐로 만들고싶어
        //Queue<Point> queue=new LinkedList<>();
        PriorityQueue<Point> queue=new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.value-o2.value;
            }
        });

        for (int n = 0; n < N; n++) {
            strs = br.readLine().split(" ");
            for (int m = 0; m < N; m++) {
                map[n][m] = Integer.parseInt(strs[m]);
                if (map[n][m] != 0) {
                    queue.add(new Point(n, m, map[n][m]));
                    visited[n][m]=true;
                }
            }
        }

        //System.out.println(Arrays.deepToString(map));

        strs=br.readLine().split(" ");
        int S = Integer.parseInt(strs[0]);
        int X = Integer.parseInt(strs[1]);
        int Y = Integer.parseInt(strs[2]);


        while (S > 0) {

            //임시 큐 (큐의 범위는 중요!)
            //임시 큐에서 저장하고 나중에 진짜 큐에 옮길 것이다.
            Queue<Point> subQueue = new LinkedList<>();

            while (!queue.isEmpty()) {
                Point point = queue.poll();
                //System.out.println("큐에서 뽑은 값:"+point.toString());


                for (int i = 0; i < 4; i++) {
                    int x=point.x+gx[i];
                    int y=point.y+gy[i];

                    if (0 <= x && x < N && 0 <= y && y < N && !visited[x][y]) {
                        map[x][y]=point.value;
                        Point multiPoint= new Point(x, y, point.value);
                        subQueue.add(multiPoint);
                        visited[x][y]=true;
                        //System.out.println(multiPoint.toString());
                    }
                }
                //System.out.println("-".repeat(20));
            }

            while (!subQueue.isEmpty()) {
                queue.add(subQueue.poll());
            }

            //System.out.println("map:"+Arrays.deepToString(map));
            S--;
        }

        System.out.println(map[X-1][Y-1]);



    }

    static class Point{
        int x;
        int y;
        int value;

        Point(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        public String toString(){
            return "["+x+"]["+y+"]="+value;
        }
    }

}
