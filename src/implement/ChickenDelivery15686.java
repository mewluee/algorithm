package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class ChickenDelivery15686 {

    private String site="https://www.acmicpc.net/problem/15686";

    static int N,M;
    static int[][] streets;
    static ArrayList<Point> person;
    static ArrayList<Point> chicken;
    static int ans;

    //방법1
    static boolean[] open; // 방문여부확인 visited인듯! >> 응 아님 선택임.
    //방법2
    static Stack<Point> selected;

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    //이건 boolean 배열을 공부하고 내가 안!보!고! 직접 스택으로(다른방법) 구현한 것.
    public static void result2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        streets = new int[N][N];
        person = new ArrayList<>();
        chicken = new ArrayList<>();
        selected=new Stack<>();

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int n2 = 0; n2 < N; n2++) {
                streets[n][n2] = Integer.parseInt(st.nextToken());

                if(streets[n][n2]==1){
                    person.add(new Point(n, n2)); // 일단 person 리스트에 담는다.
                } else if (streets[n][n2] == 2) {
                    chicken.add(new Point(n, n2)); // 일단 chicken 리스트에 담는다.
                }
            }
        }

        ans=Integer.MAX_VALUE;

        DFS2(0,0);

        System.out.println(ans);


    }

    public static void DFS2(int start, int count){
        if(count==M){
            //여기서 내가 해야할 작업은 카운트랑 M이랑 개수가 똑같으니까 스택에 있는 치킨집들만 꺼내서 거리 계산하고 저장하면됨.
            //while문으로 스택에서 하나씩 꺼내서 할려고했는데 이걸 꺼내버리면
            //재귀함수 호출후 돌아갔을 때 없어져버림ㅋㅋ큐ㅠ안댐...!!!

            int res=0;
            for(int j=0; j<person.size(); j++){

                int distance=Integer.MAX_VALUE;

                //지금 집을 기준으로 모든 치킨집간의 거리를 잰다음에 최소값만 비교해서 distance에 집어넣는다.
                for(int i=0; i<selected.size(); i++){

                    distance=Math.min((Math.abs(person.get(j).x-selected.get(i).x)+
                            Math.abs(person.get(j).y-selected.get(i).y)),distance);

                }
                //모든 치킨집을 다 순회하고 결과적으로 최솟값만 남은 distance를 누적해서 저장한다!
                res=res+distance;
            }

            ans=Math.min(res,ans);
            //구해진 도시의 치킨거리 중 값이 작을때마다 갱신시켜준다.

        }

        //백트래킹(큭큭..사실 정말 백트래킹이라고 하는거 마음에 안들어 큭큭..)
        for (int t = start; t < chicken.size(); t++) {
            selected.push(chicken.get(t));
            DFS2(t + 1, count + 1);
            selected.pop();
        }
    }

    //이건 boolean배열을 조건으로 만듬.
    public static void result() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        streets = new int[N][N];
        person = new ArrayList<>();
        chicken = new ArrayList<>();
        //이렇게 두개의 리스트를 나누는 이유가 뭘까? >> 모든 맵을 계속 다 검사하지 않으려구...

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int n2 = 0; n2 < N; n2++) {
                streets[n][n2] = Integer.parseInt(st.nextToken());

                if(streets[n][n2]==1){
                    person.add(new Point(n, n2)); // 일단 person 리스트에 담는다.
                } else if (streets[n][n2] == 2) {
                    chicken.add(new Point(n, n2)); // 일단 chicken 리스트에 담는다.
                }
            }
        }

        ans = Integer.MAX_VALUE; // 최종적으로 출력할 답
        open = new boolean[chicken.size()]; // 이걸하는이유가 가늠이 안돼...조합용인거같긴한데
        //모두다 false로 초기화된다. new생성자

        DFS(0, 0);
        //DFS(0,0) 호출
        //if(0==0)이니까
        //결과=0, 0부터 모든 치킨집을 검사함.
        //그래서 결가적으로 해당 집의 치킨거리(최소값) =temp이고
        //res에는 모든 집의 치킨거리를 더한값이 된다.


        System.out.println(ans);
        br.close();


        //솔루션 코드
        //1.치킨집이 오픈한 개수가 M과 같다면 모든 집에 대해 M개의 치킨집 중 최단거리를 계산한다.
        //2.탐색을 시작하는 지점이 치킨집 list의 사이즈가 벗어나게 되면 탐색을 종료한다.

        //사실 이해안돼...흑흑..



    }

    public static void DFS(int start, int cnt) {
        if (cnt == M) { //카운트 = M일때만.
            //아니 바보야!!!!! 카운트 M이잖아!!!!!!!!!헐!!! 최대 M개일때만 돌아가!!!!!!!잖!!!아!!!!!!!악!
            int res = 0;

            //집을 기준으로..
            for (int i = 0; i < person.size(); i++) {
                int temp = Integer.MAX_VALUE; //임시값을 integer중 최고값으로 설정함. 최소값 뽑아내려고

                //치킨집을 하나씩 검사한다.
                //치킨집이 true이면..
                for (int j = 0; j < chicken.size(); j++) {
                    if (open[j]) {
                        int distance = Math.abs(person.get(i).x - chicken.get(j).x)
                                + Math.abs(person.get(i).y - chicken.get(j).y);
                        //해당 치킨집과 거리를 잰다음에
                        temp = Math.min(temp, distance);
                        //템프에는 해당집의 치킨거리들 중에 최소값만 남게된다.
                    }
                }
                res += temp;
                //모든 집의 치킨거리를 다 더한다.
            }
            ans = Math.min(ans, res); //현재 갖고있는 답과 비교해서 작은 값을 저장한다.
            return;
        }

        //백트래킹
        //이 함수가 호출될때마다 입력값기준으로 모든 치킨집을.. open을 트루로만든다구...그리고 재귀함수호출해서 1증가시킨다..으음...
        for (int i = start; i < chicken.size(); i++) {

            //인덱스 start부터 끝까지 치킨집을 순차적으로 접근을 하는데

            //일단 트루로 만들고,
            open[i] = true; //어떤사람은 여기가 스택으로 구현되서 push

            DFS(i + 1, cnt + 1);
            //카운트가 3개가 되는 순간 치킨거리를 검사하고..
            //그리고 false로 만들어버림
            //그런데 false로 만들어버린다고 해도 카운트 값은 계속 따로 증가하는 형태인데 아니 왜 자꾸 증가하게 냅두지..?
            //start를 포함하지 않기 때문에 조합 상태가 됨..?
            //(0,0) > {(open+0/(1,1)/-0),(open+1/(2,1)/-1),(open+2/(3,1)/-2),(open+3/(4,1)/-3), ... ,(open+(치킨집수-1)/(치킨집수,1)/-(치킨집수-1))} //결과적으로 여길보면 계속 0은 기본으로 추가하고
            // 그런데 이 구간은 카운터가 1이라서 계속 백트래킹으로 넘어갈 거임.
            //                    ㄴ>  (1,1)이 실행되는 시점은 0이미 true인 상태이고 카운터도 1증가한 상태(카운터는 open의 true개수를 나타나게 된다...)
            //                         (1,1)에서는 1을 open에 추가하고 또 카운터 증가시킨다. 그럼 0,1이 추가된 상태겟지? 하지만 카운트는 2라서 계속 백트래킹으로 넘어갈거임.. 그래서 1추가되면 (2,2) 수행되겠지
            //                                       open에 2추가하고 카운터도 증가하고 그럼 (3,3) 수행되겠지. > 이제 최단거리 검사함. open이 true인 애만 검사함. 검사하고 결과값 갱신되고
            //                                       나오잖아..? 근데 이부분이 피크인게 0,1,2 추가되고 (3,3)검사했지? >> 나온다.false만들고 0,1,3 추가되고 (4,3) 검사함.
            //                                      0,1,(2,3) / 0,1,(3,3) / 0,1,(4,3) / ... 0,1,(m,3) 여기까지 다 검사하고 다시 맨 마지막은 false로 만든다.
            //                                           호출될때 카운트가 3이되는 순간 치킨거리 검사한다고 생각하면 됨.
            //                                            ㄴ> 봐바 이제 재귀가 끝나는 시점을 살펴보자.
            //                                                0,1,2,{(3,4),(4,4),(5,4),..(m,4)} 자 여기서부터는 싹다 count가 3보다 커서 치킨거리는 계산하지 않을거야.
            //                                                대신 백트래킹은 계속하겠지. 0,1,2,3,{(4,5),(5,5),...(m,5)}
            //                                                                                   ㄴ> 0,1,2,3,4,{(m,m+1)} i가 m이 되는순간 더이상 재귀호출이 되지않고 끝남. 반환값이 void라서 그냥 끝!
            //
            //                                          ㄴ> 0,(2,1) 실행시 0,2,3 / 0,2,4 / 0,2,5 ... 0,2,m


            open[i] = false; //스택으로 구현되면 pop
        }

        //시작값이 0일때 모든 치킨집을 두고 일단 open을 모두 다 true로 만든다음에.
        //0을 뺀 (1,1)을 검사한다..?ㅋㅋ ㅠㅠ???
        //0번 집을 빼고 카운트도 증가하고..
        //그리고 false로 만든다.
    }

    public static void badThinking() throws IOException {

        //입력 도시의 크기는 N x N
        //폐업시키지 않을 치킨집을 최대 M개 골랐을 때, 도시의 치킨 거리의 최솟값을 출력.

        //도시에 있는 치킨집 중에서 최대 M개를 고르고, 나머지 치킨집은 모두 폐업시켜야한다.
        //어떻게 고르면 도시의 치킨거리가 가장 작게 될지..
        //어떤 치킨집을 폐업해야 치킨거리가 가장 작게되는가!

        //(출력) 도시의 치킨거리 = 모든 집의 치킨거리의 합.
        // >> 치킨거리=집과 가장 가까운 치킨집 사이의 거리(두개 치킨집이 있다고 했을때, 각각의 거리중 가까운 거리가 그 집의 치킨거리가 된다)
        //빈집(0) 집(1) 치킨집(2)

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        streets = new int[N][N];

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int n2 = 0; n2 < N; n2++) {
                streets[n][n2] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(Arrays.deepToString(streets));

        //백트래킹..?어케한다는거지..?왜하는거지..?브루트포스인데..?
        //치킨집 조합에 따른 치킨거리를 계산해야하나..?
        //조합짜는것도 문젠뎅..
        //이 조합짜는 부분에서 백트래킹이 들어가는 건가
        //백트래킹 > 해를 찾는 도중 해가 아니어서 막히면, 되돌아가서 다시 해를 찾아간다.
        //가지치기! 불필요한 경로를 조기에 차단한다. 즉, 백트래킹은 모든 가능한 경우의 수 중에서 특정한 조건을 만족하는 경우만 살펴본다라는 것이다.

        //그렇다면,,특정한 조건이란..지금 현재 구해진 도시의 치킨거리보다 작을때만 검사..?
        //집은 폐업시킬수없으니까..선택은 치킨집인데..
        //기준점을 뭐로해야할까..?
        //애초에 r이든 c이든 거리가 멀어지면 가능성 없음.
        //아~ 그 치킨집(2) 주변에 집(1)이 많을수록 선택될 가능성 높음.
        //그런데 주변에 1이 적다..? 그럼 일단 재껴.
        //주변에 1이 많다는 조건을 만들려면 기준은 N/2?
        //생각해봐...치킨집은 적어도 1부터 시작하고.. 1일땐 좀 경우를 다르게 세야겠지만.
        //2이라고 뒀을때 정말 끝(1,1)과 끝(N,N)에 치킨집이 있다고 해보자. M이 1일때 두 치킨집중 골라야하는 건
        //그럼 그 치킨집을 기준으로 주변에 1이 있냐없냐니까, 그 거리는 끝과끝을 기준으로 반으로 잡으면 되지! got it!?!?
        //이제 순회하면서 치킨집일때마다 주변의 1개수를 검사하고..>> 이건 입력시 저장가능..아니 못함ㅋㅋㅋ헉스;;
        //해당하는 값 저장해둬야할까..?
        //순회할때마다 주변의 1개수 검사해서 noClosed에 있는 가게들보다 1의 개수가 적으면 애초에 검사하지 않는다.어때!? (1의 개수가 같은건 ㄱㅊ..)

        //주변의 1을 검사하는 방법은..?
        //치킨집을 골라놓고, 순회해서 1일때마다 해당 치킨집과의 거리가 N보다 크면 카운트세지 않는다.!

        //부모로 돌아갈게 있나..?백트래킹..?난왜..흑흑...

        //일단 구현 고

        int[][] noClosed=new int[M][2];
        //{ {1,2}, {1,2}, ... }
        //int maxCountHouses=0;

        for (int n = 0; n < N; n++) {
            for (int n2 = 0; n2 < N; n2++) {
                if(streets[n][n2]==2){ //치킨집일떄!
                    int countOnes=countHouses(n,n2);
                    for (int m = 0; m < M; m++) {
                        if(countHouses(noClosed[m][0], noClosed[m][1])>countOnes){
                            //사실 반례가 있을거같아...흑흑.....흑..
                        }
                    }
                }
            }
        }


    }

    //N거리내의 1의 개수 세기 (매개변수로 치킨집의 위치가 필요하다)
    public static int countHouses(int x, int y) {
        int count=0;

        for (int n = 0; n < N; n++) {
            for (int n2 = 0; n2 < N; n2++) {
                if(streets[n][n2]==1){
                    int distance=Math.abs(x-n)+Math.abs(y-n2);
                    if(distance<=N) count++;
                }
            }
        }

        return count;
    }
}
