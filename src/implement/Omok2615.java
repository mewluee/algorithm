package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Omok2615 {


    static int[][] map=new int[19][19];

    //           ↗  →  ↘  ↓
    static int[] dx = {-1, 0, 1, 1};
    static int[] dy = {1, 1, 1, 0};
    //반대방향   곱하기 -1하면된다.

    static Queue<Point> baduks=new LinkedList<>();

    static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "["+x+"]["+y+"]="+map[x][y];
        }

        void printInfo(){
            System.out.println("["+x+"]["+y+"]="+map[x][y]);
        }

        int getStone(){
            return map[x][y];
        }

    }

    public static void result() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0; i<19; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i2 = 0; i2 < 19; i2++) {
                map[i][i2] = Integer.parseInt(st.nextToken());
                if(map[i][i2]!=0){
                    baduks.add(new Point(i, i2));
                }
            }
        }

        //System.out.println(Arrays.deepToString(map));
        //바둑알이 놓였을 경우만 검사하면 되니까
        //입력받을때 0이 아닌 입력값들은 큐에 담아서 순차적으로 검사하자(호제님 말 주워듣기1)

        //출력은 세가지 경우
        //1.검은알이 이기거나, 하얀알이 이긴다. (그 경우 1,2를 각각 적고, 다음 줄에 5목 달성시 가장 왼쪽상단에 있는 바둑알 위치 출력한다)
        //2.아직 승부가 결정되지 않았을 경우는 0이다. (즉 5목이 단 한개도 없을 때)

        //로직(호제님 말 주워듣기2)
        //4방향으로 쭉쭉 검사하는데
        //검사방향의 반대방향으로 -1칸을 검사한다.
        //육목을 확인하기 위해서다.

        while (baduks.size()>0) {
            Point point=baduks.poll(); //큐의 첫번째 값을 반환한다.(큐에서 아예 꺼낸다)
            System.out.println(point.toString());
            for (int d = 0; d < 4; d++) {

                //한 방향씩 검사한다.
                if (!checkBeforeStone(point, d)) {

                    int before=point.getStone(); // 현재 돌 기억하고
                    int count=1; // 현재 돌 1로 센다.

                    int x = point.x;
                    int y = point.y;

                    //5번 같은 방향(d)으로 검사해야한다.(4번까진 카운트가 증가하고 5번은 6목 검사)
                    for(int i=0; i<5; i++){
                        x=x+dx[d];
                        y=y+dy[d];

                        if(0 <= x && x < 19 && 0 <= y && y < 19 && before == map[x][y]){
                            before=map[x][y];
                            //System.out.println("**["+x+"]["+y+"]="+map[x][y]);
                            count++;

                        }else{
                            break;
                            //아니면 검사 끝낸다.
                        }
                    }

                    if(count==5){
                        //결과값 출력
                        System.out.println(point.getStone());
                        System.out.println((point.x+1) +" "+ (point.y+1));
                        return;
                    }

                }

            }
        }

        System.out.println("0");


    }

    static public boolean checkBeforeStone(Point point, int direction) {

        //반대 방향 -1칸 계산
        int x=point.x+dx[direction]*-1;
        int y=point.y+dy[direction]*-1;

        if (0 <= x && x < 19 && 0 <= y && y < 19 && map[x][y] == point.getStone()) {
            //-1칸에 위치하는 스톤이 지금 현재 스톤과 같은 색상 있으면 true반환
            return true;
        }
        return false;

    }


}
