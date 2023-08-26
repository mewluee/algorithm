package 구현_implement;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.StringTokenizer;

public class 드래곤커브_15685 {

    static int[] dx={1,0,-1,0};
    static int[] dy={0,-1,0,1};
    static boolean[][] map=new boolean[101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int N=Integer.parseInt(br.readLine());
        StringTokenizer st;
        HashSet<Point> result_point=new HashSet();
        for(int n=0; n<N; n++) {
            st=new StringTokenizer(br.readLine(), " ");
            int x=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());
            int d=Integer.parseInt(st.nextToken()); //방향(0오/1위/2좌/3아)
            int g=Integer.parseInt(st.nextToken()); //세대
            Dragon dragon=new Dragon(x,y,d);
            while(dragon.g<g) {
                dragon.curv();
            }
            for(Point p : dragon.list) {
                result_point.add(p);
                map[p.x][p.y]=true;
            }
        }
        int result=0;
        for(Point pp:result_point) {
            if(pp.checkSquare()) result++;
        }
        System.out.println(result);
    }

    static class Dragon{
        int sx; //시작점
        int sy;
        int x; //기준점
        int y;
        int g;
        HashSet<Point> list=new HashSet();

        Dragon(int x, int y, int d){
            sx=x;
            sy=y;
            g=0;
            list.add(new Point(x, y));
            list.add(new Point(x+dx[d], y+dy[d]));
            this.x=x+dx[d];
            this.y=y+dy[d];
        }

        void curv() {
            // A -(기준점 기준 회전)-> B
            // A(list) 기준점(x,y) B(tx, ty)
            ArrayList<Point> move=new ArrayList();
            int next_x=x;
            int next_y=y;
            for(Point p:list) {
                int tx=x+y-p.y;
                int ty=y-x+p.x;
                if(tx<0 || 100<tx || ty<0 || 100<ty) continue;
                if(p.x==sx && p.y==sy) { //시작점에서 커브한 점이 다시 기준점이 된다.
                    next_x=tx;
                    next_y=ty;
                }
                move.add(new Point(tx,ty));
            }
            list.addAll(move);
            g++;
            x=next_x;
            y=next_y;
        }
    }

    static class Point{
        int x;
        int y;

        Point(int x, int y) {
            this.x=x;
            this.y=y;
        }

        @Override
        public boolean equals(Object obj) {
            return ((Point)obj).x==this.x && ((Point)obj).y==this.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x,y);
        }

        boolean checkSquare() {
            //현재 확인, 오른쪽 확인, 오른쪽 아래 확인, 아래 확인
            if(x+1<=100 && y+1<=100) {
                if(map[x][y] && map[x+1][y] && map[x+1][y+1] && map[x][y+1]) return true;
                else return false;
            }else {
                return false;
            }
        }
    }
}
