package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class WizardSharkAndRainStorm21610 {

    private String site = "https://www.acmicpc.net/problem/21610";


    static int[][] map;
    static int N;
    static int M;
    static boolean[][] visited;

    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        String printPoint(){
            return "["+x+"]["+y+"]="+map[x][y];
        }
    }

    static ArrayList<Point> clouds;

    public static void result() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        clouds = new ArrayList<>();
        clouds.add(new Point(N-1, 0));
        clouds.add(new Point(N-1, 1));
        clouds.add(new Point(N-2, 0));
        clouds.add(new Point(N-2, 1));
        //넣을때 정상 인덱스 값으로 넣었음.

        map = new int[N][N];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int n2 = 0; n2 < N; n2++) {
                map[n][n2] = Integer.parseInt(st.nextToken());
            }
        }
//        System.out.println(Arrays.deepToString(map));
//        printCloud();
//        System.out.println("-".repeat(30));

        visited=new boolean[N][N];

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine(), " ");
            int D = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());

            rainAfterMoving(D,S);
            //printCloud(); //이동후 구름 위치 뽑아내기
            waterCopyBug();
            //System.out.println(Arrays.deepToString(map));
            waterDecrease();
            //System.out.println(Arrays.deepToString(map));
        }

        int result=0;
        int sub=0;
        for (int n = 0; n < N; n++) {
            sub= Arrays.stream(map[n]).sum();
            result=result+sub;
        }
        System.out.println(result);

        //마법사 상어 : 비바라기 > 하늘에 비구름 만듬!
        //격자의 각 칸에는 바구니가 있다. 바구니에 저장할 수 있는 물의 양에는 제한이 없다.
        //비바라기 시전하면 (N,1)(N,2),(N-1,1),(N-1,2)
        //    0 0 0
        //    1 1 0
        //    1 1 0

        //    2 3 4
        //    1 ㅁ 5
        //    8 7 6
        //인덱스라서 입력값에서 -1해야함.


    }

    //이동해서 비내리기
    static void rainAfterMoving(int D, int S) {
        //System.out.println("D:"+D+",S:"+S);
        //D방향으로 S만큼 이동하기
        for(int i=0; i<clouds.size(); i++){
            //담고
            int x=clouds.get(i).x;
            int y=clouds.get(i).y;
            //System.out.println("*전1:x,y="+x+","+y);
            //S만큼 이동하고
            x = x + S*dx[D - 1];
            y = y + S*dy[D - 1];
            //System.out.println("*전2:x,y="+x+","+y);

            //이럴줄알았다~~~s가 50만큼이네~~?흠.........
            //이동만큼 더해야하는데!
            //예를 들어 S가 30이라고 치면 총 -30인데..
            //4,0을 기준으로 -10이면 N 2번..습..
            //음수일경우 양수가될떄까지 N을 반복해서 더해야한다..

            while(x<0){
                x=x+N;
            }
            while(y<0){
                y=y+N;
            }
            //System.out.println("*후:x,y="+x+","+y);
            x=x%N;
            y=y%N;

            clouds.get(i).x=x;
            clouds.get(i).y=y;
            map[x][y]++;
            //이동하고 비내렸음!
            //이동한 위치 제외해야해서 visited 처리해주기
            visited[x][y]=true;


        }


    }

    //물복사버그
    static void waterCopyBug(){
        //대각선 방향 검사

        for(int i=0; i<clouds.size(); i++){

            Point point=clouds.get(i);

            int count=0;
            for(int t=1; t<8; t=t+2){
                int gx=point.x+dx[t];
                int gy=point.y+dy[t];

                //이번엔 면이 연결되지 않는다. 범위를 벗어나면 검사 할 필요가 없다.
                if(0<=gx && gx<N && 0<=gy && gy<N){
                    if(map[gx][gy]!=0){
                        count++;
                    }
                }
            }
            map[point.x][point.y]=map[point.x][point.y]+count;
            //System.out.println("물복사버그!");
            //System.out.println("물복사버그:"+point.printPoint());
        }

    }

    //물감소
    static void waterDecrease(){
        clouds.clear();
        for (int n = 0; n < N; n++) {
            for (int n2 = 0; n2 < N; n2++) {
                if(map[n][n2]>=2 && !visited[n][n2]){
                    map[n][n2]=map[n][n2]-2;
                    //감소시킨다.
                    clouds.add(new Point(n,n2));
                    //다시 새구름~~!
                }
            }
        }

//        System.out.println("---------------+\n새구름들:");
//        printCloud();
//        System.out.println("-----------------------");

        //감소시킨후에 다시 다 false로 만들어버려야한다!
        for (int n = 0; n < N; n++) {
            Arrays.fill(visited[n],false);
        }
        //System.out.println(Arrays.deepToString(visited));
    }

    //출력용
    static void printCloud(){
        for(int i=0; i<clouds.size(); i++){
            System.out.println(clouds.get(i).printPoint());
        }
    }
}
