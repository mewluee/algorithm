package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 연구소_14502 {

    //범위 3~8
    static int N; //지도 세로 = 행 개수
    static int M; //지도 가로 = 열 개수
    static int[][] map;
    static boolean[][] visited;
    static int wall;
    static int result;
    static ArrayList<Point> virus;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        result=0;
        map=new int[N][M];
        visited=new boolean[N][M];
        virus=new ArrayList<>();

        for (int n = 0; n < N; n++) {
            int[] input=Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            for (int m = 0; m < M; m++) {
                map[n][m]=input[m];
                if(input[m]==1){
                    visited[n][m]=true;
                }else if (input[m]==2){
                    visited[n][m]=true;
                    virus.add(new Point(n, m));
                }
            }
        }
        //printMap();
        makeCombination(0);
        //makeCombination2(0,0,true);
        //virusSpread();
        System.out.println(result);
    }

    static void makeCombination(int row){
        if(wall>3) return;
        else if(wall==3){
            //System.out.println("wall:"+wall);
            //printMap(map);
            int count = getSafeZone(virusSpread());
            if(count>result) result=count;
            //System.out.println("------------------------");
        }
        for (int n = row; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if(visited[n][m]) continue;
                visited[n][m]=true;
                wall++;
                map[n][m]=1;
                makeCombination(n);
                map[n][m]=0;
                visited[n][m]=false;
                wall--;
            }
        }
    }

    static int[][] virusSpread(){

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int[][] copyMap=copyMap();
        Queue<Point> queue=new LinkedList<>();
        for (int i = 0; i < virus.size(); i++) {
            queue.add(virus.get(i));
        }

        while (!queue.isEmpty()){
            Point now=queue.poll();
            if(copyMap[now.x][now.y]!=2) continue;

            for (int i = 0; i < 4; i++) {
                int x=now.x+dx[i];
                int y=now.y+dy[i];
                if(x<0 || N<=x || y<0 || M<=y) continue;
                if(copyMap[x][y]!=0) continue;
                copyMap[x][y]=2;
                queue.add(new Point(x, y));
            }
        }
        //System.out.println("바이러스확산후");
        //printMap(copyMap);
        return copyMap;
    }

    static int getSafeZone(int[][] map){
        int count=0;
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if(map[n][m]==0) count++;
            }
        }
        //System.out.println("safe:"+count);
        return count;
    }

    static int[][] copyMap(){
        int[][] copy=new int[N][M];
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                copy[n][m]=map[n][m];
            }
        }
        return copy;
    }

    static void makeCombination2(int row, int col, boolean flag){
        if(row>=N) return;
        if(flag){
            printMap(map);
            System.out.println(wall);
            //count++;
        }
        for (int m = col; m < M; m++) {
            if(visited[row][m]) continue;
            makeCombination2(row+1, 0, false);

            visited[row][m]=true;
            wall++;
            map[row][m]=1;
            makeCombination2(row, m, true);
            map[row][m]=0;
            visited[row][m]=false;
            wall--;
        }
    }

    static void printMap(int[][] map){
        for (int n = 0; n < N; n++) {
            System.out.println(Arrays.toString(map[n]));
        }
    }

    static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
