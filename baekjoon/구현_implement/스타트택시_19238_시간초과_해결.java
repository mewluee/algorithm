package 구현_implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 스타트택시_19238_시간초과_해결 {

    //문제: 골드2 스타트 택시 19238

    //요약:
    //도착지로 데려다줄때마다 연료 충전
    //연료 바닥나면 업무 종료
    //M명의 승객 태우기 목표
    //맵은 N x N
    //특정 위치로 이동할 때 항상 최단 경로로만 이동
    //택시는 항상 현재 위치에서 최단거리가 가장 짧은 승객 고르기
    //거리가 같으면 행 번호, 열 번호 순으로 작은 승객 먼저 고르기
    //택시와 승객이 같은 위치면 최단거리는 0
    //한칸 이동할때마다 연료 1만큼 소모
    //성공적으로 목적지로 이동하면 소모한 연료 양의 두배 충전
    //이동 도중에 연료 바닥시 실패, 업무 끝
    //목적지 도착과 연료 바닥시 성공
    //맵의 1은 벽이다. 못간다.
    //각 손님의 출발지와 목적지는 서로 다르다.

    //알고리즘:구현, 최단거리니까 bfs
    //풀이1(틀렸음, 시간초과):
    //


    static int N;
    static int M;
    static int[][] map;

    static int[] dx= {-1, 0, 0, 1};
    static int[] dy= {0,-1,1,0};
    static int result=-1;

    static int[][] passengerStart;
    static int[][] passengerEnd;
    static boolean[] done;
    static int[] taxiStart;

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine(), " ");
        N=Integer.parseInt(st.nextToken()); //맵
        M=Integer.parseInt(st.nextToken()); //승ㄱ
        int fuel=Integer.parseInt(st.nextToken()); //연료

        map=new int[N][N];
        for(int n=0; n<N; n++) {
            map[n]=Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .map(e->e==1?-1:e)
                    .toArray();
            //System.out.println(Arrays.toString(map[n]));
        }

        st=new StringTokenizer(br.readLine()," ");
        taxiStart= new int[]{Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1};
        //System.out.println(Arrays.toString(taxiStart));
        passengerStart=new int[M+1][2];
        passengerEnd=new int[M+1][2];
        done=new boolean[M+1];
        int count=0;
        for(int m=0; m<M; m++) {
            st=new StringTokenizer(br.readLine()," ");
            int sx = Integer.parseInt(st.nextToken())-1;
            int sy = Integer.parseInt(st.nextToken())-1;
            int ex = Integer.parseInt(st.nextToken())-1;
            int ey = Integer.parseInt(st.nextToken())-1;
            passengerStart[m+1]=new int[]{sx, sy};
            passengerEnd[m+1]=new int[]{ex, ey};
            map[sx][sy]=m+1; //손님 인덱스, 1부터 시작(0은 빈공간 표현)
            //System.out.println(Arrays.toString(passengerStart[m+1])+"->"+Arrays.toString(passengerEnd[m+1]));
        }

//		for(int n=0; n<N; n++) {
//			System.out.println(Arrays.toString(map[n]));
//		}
        //System.out.println(getDistance(new int[] {1, 2}, new int[] {1,4}));



        while(count<M) {
            //System.out.println("-------------");
            //현재 택시 기준으로 제일 가까운 승객 구하기
            //System.out.println("현재택시:"+Arrays.toString(taxiStart));

            int[] now=getPassenger();
            //System.out.println("태울 승객:"+Arrays.toString(now));
            if(now[0]!=M-count ||now[0]==-1) {
                System.out.println("-1");
                return;
            }

            //System.out.println("fuel:"+fuel+", 승객까지 가야하는 거리:"+now[2]);
            if(fuel<now[2]) break; //지금 연료로는 승객까지 못감.
            fuel-=now[2];

            //System.out.println("운전");
            int drivingFuel=getDistance(passengerStart[now[1]], passengerEnd[now[1]]);
            //System.out.println("운전거리:"+drivingFuel);
            if(fuel<drivingFuel || drivingFuel==-1) {
                fuel=-1;
                break;
            }

            fuel+=drivingFuel; //성공적으로 도착하면 두배 충전
            taxiStart=passengerEnd[now[1]];
            done[now[1]]=true;
            count++;
        }

        System.out.println(fuel);
    }

    static int[] getPassenger() {
        Queue<int[]> queue=new LinkedList<>();
        PriorityQueue<int[]> pqueue=new PriorityQueue<>((a1, a2)->{
            if(a1[3]==a2[3])
                if(a1[1]==a2[1])
                    return a1[2]-a2[2];
                else
                    return a1[1]-a2[1];
            else
                return a1[3]-a2[3];
        });
        queue.add(new int[] {taxiStart[0], taxiStart[1], 0});//{x,y,거리}
        boolean[][] visited=new boolean[N][N];
        visited[taxiStart[0]][taxiStart[1]]=true;

        while(!queue.isEmpty()) {
            int[] now=queue.poll();
            //System.out.println(Arrays.toString(now));
            if(map[now[0]][now[1]]!=0) {
                int pIndex=map[now[0]][now[1]];
                if(!done[pIndex]) {
                    int[] input=new int[] {pIndex, passengerStart[pIndex][0], passengerStart[pIndex][1], now[2]};
                    pqueue.add(input); //{승객 인덱스, x, y, 거리}
                    //System.out.println("큐에 들어간 승객:"+Arrays.toString(input));
                }
            }

            for(int d=0; d<4; d++) {
                int x=now[0]+dx[d];
                int y=now[1]+dy[d];

                if(x<0 || N<=x || y<0 || N<=y) continue;
                if(visited[x][y] || map[x][y]==-1) continue;
                visited[x][y]=true;
                queue.add(new int[] {x, y, now[2]+1});
            }
        }
        int[] result=pqueue.poll();
        if(result==null)
            return new int[] {-1};
        else
            return new int[] {pqueue.size()+1, result[0], result[3]}; //{큐에 들어간 요소의 개수, 승객 인덱스, 거리}
    }

    static int getDistance(int[] start, int[] end) {
        //System.out.println("getD:"+Arrays.toString(start)+"->"+Arrays.toString(end));
        int result=-1;
        Queue<int[]> queue=new LinkedList<>();
        boolean[][] visited=new boolean[N][N];
        queue.add(new int[] {start[0], start[1], 0});
        visited[start[0]][start[1]]=true;

        while(!queue.isEmpty()) {
            int[] now=queue.poll();
            //System.out.println("\n현재:"+Arrays.toString(now));
            if(now[0]==end[0] && now[1]==end[1]) {
                result=now[2];
                break;
            }

            for(int d=0; d<4; d++) {
                int x=now[0]+dx[d];
                int y=now[1]+dy[d];
                //System.out.print("\n["+x+", "+y+"]");
                if(x<0 || y<0|| N<=x || N<=y) continue;
                if(visited[x][y] || map[x][y]==-1) continue;
                //System.out.print("큐에 입력");
                queue.add(new int[] {x,y,now[2]+1});
                visited[x][y]=true;
            }
        }

        return result;
    }

    // 틀렸던 코드
//    static int[][] map;
//    static int N;
//    static int M;
//    static int fuel;
//    static boolean goal = false;
//    static Queue<Passenger> list = new LinkedList();
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());
//        fuel = Integer.parseInt(st.nextToken());
//        map = new int[N + 1][N + 1];
//        for (int n = 1; n <= N; n++) {
//            st = new StringTokenizer(br.readLine(), " ");
//            for (int n2 = 1; n2 <= N; n2++) {
//                if (Integer.parseInt(st.nextToken()) == 1)
//                    map[n][n2] = 1; // 1은 벽
//            }
//        }
//        st = new StringTokenizer(br.readLine(), " ");
//        int start_x = Integer.parseInt(st.nextToken());
//        int start_y = Integer.parseInt(st.nextToken());
//
//        for (int m = 0; m < M; m++) {
//            st = new StringTokenizer(br.readLine(), " ");
//            int sx = Integer.parseInt(st.nextToken());
//            int sy = Integer.parseInt(st.nextToken());
//            int ex = Integer.parseInt(st.nextToken());
//            int ey = Integer.parseInt(st.nextToken());
//            Passenger p = new Passenger(new int[] { sx, sy }, new int[] { ex, ey });
//            list.add(p);
//        }
//        move(start_x, start_y);
//
//        if (goal)
//            System.out.println(fuel);
//        else
//            System.out.println(-1);
//    }
//
//    static void move(int x, int y) {
//        PriorityQueue<Passenger> queue = new PriorityQueue<>((o1, o2) -> {
//            if (o1.distance == o2.distance)
//                if (o1.start[0] == o2.start[0])
//                    return o1.start[1] - o2.start[1];
//                else
//                    return o1.start[0] - o2.start[0];
//            else
//                return o1.distance - o2.distance;
//        });
//
//        while (!list.isEmpty() || fuel <= 0) {
//            int listSize=list.size();
//            for(int i=0; i<listSize; i++) {
//                Passenger p=list.poll();
//                int d=getDistance(x, y, p.start[0], p.start[1]);
//                if(d>=0) {
//                    p.distance = d;
//                    queue.add(p);
//                }
//            }
//            //System.out.println("남은 list size:"+list.size());
//            //System.out.println("남은 queue size:"+queue.size());
//            //for (Passenger p2 : queue) {
//            //	p2.print();
//            //}
//            //System.out.println("이동 시작!");
//            //System.out.println("이동 전 연료:"+fuel);
//            if(queue.isEmpty()) return;
//            Passenger p = queue.poll();
//            // 승객 출발지에 도착해서 태움!
//            fuel -= p.distance;
//            if (fuel <= 0) {
//                return;
//            }
//            x = p.start[0];
//            y = p.start[1];
//            //System.out.println("손님 태운 후 연료:"+fuel);
//            int go = getDistance(x, y, p.end[0], p.end[1]);
//            if(fuel <go) {
//                return;
//            }
//            x=p.end[0];
//            y=p.end[1];
//            fuel+=go;
//            //System.out.println("도착지:["+x+", "+y+"] 남은 연료:"+fuel);
//            list.addAll(queue);
//            queue.clear();
//        }
//
//        goal = true;
//    }
//
//    static int getDistance(int sx, int sy, int ex, int ey) {
//        //System.out.println("getD [" + sx + ", " + sy + "] -> [" + ex + ", " + ey + "]");
//        int distance = -1;
//        Queue<int[]> q = new LinkedList();
//        int[] dx = { -1, 1, 0, 0 };
//        int[] dy = { 0, 0, -1, 1 };
//        q.add(new int[] { sx, sy, 0 });
//        boolean[][] visited=new boolean[N+1][N+1];
//        visited[sx][sy]=true;
//        while (!q.isEmpty()) {
//            int[] now = q.poll();
//            if (now[0] == ex && now[1] == ey) {
//                distance = now[2];
//                break;
//            }
//            for (int i = 0; i < 4; i++) {
//                int tx = now[0] + dx[i];
//                int ty = now[1] + dy[i];
//                if (tx < 1 || N < tx || ty < 1 || N < ty)
//                    continue;
//                if (map[tx][ty] == 1 || visited[tx][ty])
//                    continue;
//                visited[tx][ty]=true;
//                q.add(new int[] { tx, ty, now[2] + 1 });
//            }
//        }
//        //System.out.println("getD:" + distance);
//        return distance;
//    }
//
//    static void printMap(int[][] map) {
//        for (int i = 0; i <= N; i++) {
//            System.out.println(Arrays.toString(map[i]));
//        }
//    }
//
//    static class Passenger {
//        int[] start;
//        int[] end;
//        int distance;
//
//        Passenger(int[] start, int[] end) {
//            this.start = start;
//            this.end = end;
//            distance = 0;
//        }
//
//        void print() {
//            System.out.println("----passenger----");
//            System.out.println("start:" + Arrays.toString(start));
//            System.out.println("end:" + Arrays.toString(end));
//            System.out.println("distance:" + distance);
//        }
//    }
}
