package 구현_implement;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 상어초등학교_21608 {


    //문제
    //+ 인접
    //1. 비어있는 칸 중 -> 인접한 칸에 좋아하는 학생이 많을 수록 선탸ㅐㄱ
    //1번 만족 -> 2. 주변에 비어있는 칸이 많은 수록 선택
    //2번 만족 -> 3. 행 번호가 가작 작은 칸 선책.
    //3번 만족 -> 4. 열 번호가 가장 작은 칸 선탹

    static int N;
    static int[][] map;
    static ArrayList<Integer>[] list;
    static int[] dx= {-1,1,0,0};
    static int[] dy= {0,0,-1,1};
    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        map=new int[N][N];
        list=new ArrayList[N*N+1];
        ArrayList<Integer> studentList=new ArrayList<>();

        for(int n=0; n<N*N; n++) {
            int[] input= Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            list[input[0]]=new ArrayList<Integer>();
            for(int i=1; i<=4; i++) {
                list[input[0]].add(input[i]);
            }
            //System.out.println(list[input[0]].toString());
            studentList.add(input[0]);
        }

        for(int m=0; m<N*N; m++) {
            check(studentList.get(m));
        }

        for(int n=0; n<N; n++) {
            //System.out.println(Arrays.toString(map[n]));
        }

        int sum=0;
        int[] score={0, 1, 10, 100, 1000};

        for(int n=0; n<N; n++) {
            for(int m=0; m<N; m++) {
                int student=map[n][m];
                int like=0;
                for(int i=0; i<4; i++) {
                    int tx=n+dx[i];
                    int ty=m+dy[i];
                    if(tx<0 || N<=tx || ty<0 || N<=ty) continue;

                    for(int num:list[student]) {
                        if(num==map[tx][ty]) like++;
                    }

                }
                sum+=score[like];
            }
        }
        System.out.println(sum);

    }

    static void check(int student) {
        PriorityQueue<Point> queue=new PriorityQueue<>();
        for(int n=0; n<N; n++) {
            for(int m=0; m<N; m++) {
                if(map[n][m]==0) {
                    //빈자리일 경우 주변을 탐색한다.
                    int like=0;
                    int blank=0;
                    for(int i=0; i<4; i++) {
                        int tx=n+dx[i];
                        int ty=m+dy[i];

                        if(tx<0 || N<=tx || ty<0 || N<=ty) continue;
                        if(map[tx][ty]==0) blank++;
                        else {
                            for(int num:list[student]) {
                                if(num==map[tx][ty]) like++;
                            }
                        }
                    }
                    //System.out.println("["+n+", "+m+"]: like("+like+"), blank("+blank+")");
                    queue.add(new Point(n,m,like, blank));
                }
            }
        }
        Point p=queue.poll();
        //System.out.println("결정된 P ["+p.x+", "+p.y+"]: like("+p.like+"), blank("+p.blank+")");
        map[p.x][p.y]=student;
    }

    static class Point implements Comparable<Point>{
        int x;
        int y;
        int like;
        int blank;


        public Point(int x, int y, int like, int blank) {
            super();
            this.x = x;
            this.y = y;
            this.like = like;
            this.blank = blank;
        }


        @Override
        public int compareTo(Point o) {
            // TODO Auto-generated method stub
            if(this.like==o.like) {

                if(this.blank==o.blank) {

                    if(this.x==o.x) {
                        return this.y-o.y;
                    }else {
                        return this.x-o.x;
                    }
                }else {
                    return o.blank-this.blank;
                }
            }else {
                return o.like-this.like;
            }
        }

    }
}
