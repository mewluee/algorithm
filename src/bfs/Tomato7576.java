package bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

public class Tomato7576 {
    private String site="https://www.acmicpc.net/problem/7576";

    public void result() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] strarr = br.readLine().split(" ");
        int M = Integer.parseInt(strarr[0]);//가로 칸
        int N = Integer.parseInt(strarr[1]);//세로 칸

        boolean[][] visited = new boolean[N][M]; //false로 초기화됨.
        int[][] box = new int[N][M];

        // 1 익은 토마토
        // 0 익지 않은 토마토
        // -1 토마토 없음
        for (int n = 0; n < N; n++) {
            String[] strTomatos = br.readLine().split(" ");
            int[] tomatos = Stream.of(strTomatos).mapToInt(Integer::parseInt).toArray();
            box[n] = tomatos;
        }

        //System.out.println(Arrays.deepToString(box));

        //출력은 다 익는 최소날짜
        //다 익어있는 상태면 날짜 0 출력
        //다 못익는 상태라면 -1 출력

        //dfs라고 했는데, 탐색을 하는 그 조건이 뭐지?
        //하루 지날때마다 인접토마토가 익어야하고
        //dfs로 그걸 탐색한다고?
        //완전 탐색...음..
        //한칸씩 탐색할때마다 날짜 카운트는 증가해야하고
        //아! 깊이우선이 아니고 너비우선이로구만 ㅇ3ㅇ..
        //너비우선은 Queue아닌감?
        //아귀간지렁
        //놀러와서 호텔에서 코딩하고잇는게 레전드...ㅋ..ㅋㅋ..

        //그럼 Queue만들어서
        //add를 할때 한꺼번에 인접한 거 4개 다해야하고
        //그때마다 카운트 세기 > 그리고 boolean배열도 방문했다고 체크하기.
        //poll은..더이상 갈곳없으면 하는거지 뭐 음..재귀방식으론 어처피 못해서
        //peek과 poll에 따라서 불리안 변수 하나 추가되고(아마 dfs랑 비슷할듯?)

        Queue<int[]> queue = new LinkedList<>();
        //큐에는 .. 배열을 넣을거에용..그 배열은 즉, [n,m]인것이죠! 행렬!
        //시작 점은 1의 위치
        //1이 두개면 둘을 동시에 한다고???????이거마자???????
        //동시에 어떻게 셈..ㄷㄷ?머..머임..뭔데..
        //그냥 배열을 싹다 검사해서
        //1옆의 인접들을 싹다 1로 만ㄷ르고 카운트만 하면 됨.

        //시작점 큐에 저장하기.
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (box[n][m] == 1) { //1일때 스택에 넣고
                    queue.add(new int[]{n, m});
                    visited[n][m] = true; //방문도 체크해두기~

                }else if(box[n][m]==-1){ //-1일때 하단에 다 방문했는지 검사하기 때문에 -1도 방문으로 체크해둔다. 단, 스택엔 넣지는 않음.
                    visited[n][m]=true;
                }
            }
        }


        //익은 토마토가 박스에 없으면~ 못익는 상황이라서 -1 출력하기.
        if (queue.size() == 0) {
            bw.write(Integer.toString(-1));
            bw.close();
            return;
        }

        int day = 0;
        while (!queue.isEmpty()) {//중간에 비어있는 경우도 있어서 조건을 뭐로 할지는 고민해봐야함. >> 걍 큐에 없으면 ㅇㅅㅇ

            //인접 행렬들 뽑아내기.
            //바로 뺄까 아님 조건에 맞을때만 뺄까?
            //바로 빼고 어처피 조건문 따져야함. 그런데 직관적인건 바로 뺴는게 맞는 걱 ㅏㅌ아

            while (!queue.isEmpty()) { // 큐가 비어있지 않으면 이제 익어가는 바이러스~

                //bw.write("(2)while문실행\n");
                //bw.write("queue사이즈:"+queue.size()+"\n");
                //bw.write("visited배열 확인1:"+Arrays.deepToString(visited)+"\n");
                //동시에 익는거 처리하는 법 ㅇㅅㅇ...뭘까..?
                //큐의 사이즈만큼 for문 돌리기..? // 일단 입력예제 1번 처리해보고 // 3번 구현해보기
                int size=queue.size();
                for(int i=0; i<size; i++){

                    int[] one = queue.poll();
                    //bw.write("one:" + one[0] + ", " + one[1]+"\n");
                    //bw.write("---------------\n");

                    //인접 토마토 큐에 넣기 ㅇㅅㅇ!
                    int up = one[0] - 1;
                    int down = one[0] + 1;
                    int left = one[1] - 1;
                    int right = one[1] + 1;
                    if (up >= 0 && visited[up][one[1]] == false && box[up][one[1]]==0) {
                        // 조건문 개길쥬. (1) up이 일단 범위 안이어야하고 (2)해당 행렬이 방문하지 않으면서도 (3)안익은토마토가 있어야함.(즉, -1없거나, 1이미 익은건 카운트 세면 안됨)
                        //bw.write("up:" + up + ", " + one[1]+"\n");
                        queue.add(new int[]{up, one[1]});
                        visited[up][one[1]] = true;
                    }
                    if (down < N && visited[down][one[1]] == false && box[down][one[1]]==0) {
                        //bw.write("down:" + down + ", " + one[1]+"\n");
                        queue.add(new int[]{down, one[1]});
                        visited[down][one[1]] = true;
                    }
                    if (left >= 0 && visited[one[0]][left] == false && box[one[0]][left]==0) {
                        //bw.write("left:" + one[0] + ", " + left+"\n");
                        queue.add(new int[]{one[0], left});
                        visited[one[0]][left] = true;
                    }
                    if (right < M && visited[one[0]][right] == false && box[one[0]][right]==0) {
                        //bw.write("right:" + one[0] + ", " + right+"\n");
                        queue.add(new int[]{one[0], right});
                        visited[one[0]][right] = true;
                    }
                }

                //bw.write("visited배열 확인2:"+Arrays.deepToString(visited)+"\n");
                day++;


            }

            //visited배열 검사하기 >> 다 true면 day-1출력하고
            //하나라도 false있으면 -1출력하기(인접행렬이 싹다 -1이어서 익을 수 없는 상황일때)

            for(int n=0; n<N; n++) {
                for(int m=0; m<M; m++){
                    if(visited[n][m]==false) {
                        bw.write(Integer.toString(-1));
                        bw.close();
                        return;
                    }
                }
            }

            //-1빼야지 제대로 카운터가 됨.
            bw.write(Integer.toString(day-1));
            bw.close();

        }



    }
}
