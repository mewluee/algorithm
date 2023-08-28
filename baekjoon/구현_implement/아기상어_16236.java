package 구현_implement;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 아기상어_16236 {

    //x축 위아래, y축 좌우
    static int[] dx={1,0,-1,0}; //아래, 좌. 위, 오른
    static int[] dy={0,-1,0,1};
    static int[][] map;
    static int N;
    static int shark_x;
    static int shark_y;
    static int shark_eat_count;
    static int shark_size;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        N=Integer.parseInt(br.readLine());
        map=new int[N][N];
        StringTokenizer st;
        for(int n=0; n<N; n++) {
            st=new StringTokenizer(br.readLine());
            for(int m=0; m<N; m++) {
                int num=Integer.parseInt(st.nextToken());
                if(num>0)
                    map[n][m]=num;
                if(num==9) {
                    shark_x=n;
                    shark_y=m;
                    shark_eat_count=0;
                    shark_size=2;
                }
            }
        }
        System.out.println(bfs());
    }

    static int bfs() {
        int second=0;
        PriorityQueue<Point> queue=new PriorityQueue<Point>(
                (p1, p2)->{
                    if(p1.distance==p2.distance)
                        if(p1.x==p2.x)
                            return p1.y-p2.y;
                        else
                            return p1.x-p2.x;
                    else
                        return p1.distance-p2.distance;});

        queue.add(new Point(shark_x, shark_y, 0));
        boolean[][] visited=new boolean[N][N];
        visited[shark_x][shark_y]=true;
        boolean checkEat=true;
        map[shark_x][shark_y]=0;

        while(checkEat) {
            //System.out.println("제일 처음라인");
            checkEat=false; //다시 바꿔주고

            while(!queue.isEmpty()) {
                Point now=queue.poll();
                //System.out.println("now ["+now.x+", "+now.y+"] d:"+now.distance+", fish:"+map[now.x][now.y]);
                //printShark();

                if(map[now.x][now.y]!=0 && map[now.x][now.y]<shark_size) {
                    //System.out.println("먹을래요~");
                    shark_x=now.x;
                    shark_y=now.y; //상어 이동
                    map[now.x][now.y]=0; //먹어부려
                    second+=now.distance;
                    //System.out.println("현재 이동시간:"+second);
                    shark_eat_count++;
                    if(shark_eat_count==shark_size) {
                        shark_eat_count=0;
                        shark_size++;
                    }
                    queue.clear();
                    queue.add(new Point(shark_x, shark_y, 0));
                    visited=new boolean[N][N];
                    visited[shark_x][shark_y]=true;
                    checkEat=true;
                    //System.out.println("겉 반복문 재시작");
                    break;
                }
                for(int i=0; i<4; i++) {
                    int tx=now.x+dx[i];
                    int ty=now.y+dy[i];

                    if(tx<0 || N<=tx || ty<0 || N<=ty) continue;
                    if(visited[tx][ty]) continue;
                    if(map[tx][ty]<=shark_size) {
                        queue.add(new Point(tx,ty,now.distance+1));
                        visited[tx][ty]=true;
                    }
                }
            }
        }

        return second;
    }

    static class Point{
        int x;
        int y;
        int distance;
        public Point(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    static void printShark() {
        System.out.println("현재 상어의 상태----");
        System.out.println("["+shark_x+", "+shark_y+"]");
        System.out.println("size:"+shark_size);
        System.out.println("eat count:"+shark_eat_count);
        System.out.println("-----------------");
    }
}
