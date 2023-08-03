package 구현_implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 컨베이어벨트위의로봇_20055 {
    String site = "https://www.acmicpc.net/problem/20055";

    static int N;
    static int K;
    static int c;

    static int[] beltsDurability;
    static boolean[] beltsBoxs;

    public static void result() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        beltsDurability= Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::new).toArray();
        //System.out.println(Arrays.toString(belts));
        beltsBoxs = new boolean[N * 2];




        //벨트위에 로봇이 함께 한칸 회전한다.
        //박스=로봇
        //1번에서만 올릴수있다
        //N번에서는 내린다.
        //로봇은 벨트 위에서 스스로 이동가능하다
        //이동 조건 : 한칸 앞에 로봇이 없어야하고 그 칸의 내구도 1이상 남아있어야한다.
        //올리거나 이동한 칸의 내구도가 1감소된다.
        //올리는 위치에서 해당 칸의 내구도가 0이면 올리지 않는다.
        //내구도가 0인 칸의 개수가 k개 이상이면 과정 종료
        //종료시 몇번째 단계인지 구하기.

        long count=0;
        c=0;

        while (count < K) {
            //System.out.println("답:"+c);
            beltRotation();
            print();

            //벨트 회전한번 했으니까 N칸에 도달하면 로봇 하차 /인덱스는 (N-1)
            beltsBoxs[N-1]=false;

            moveNextCell();
            print();

            //로봇 이동 했으니까 또 N칸에 도달하면 로봇 하차 /인덱스는 (N-1)
            beltsBoxs[N-1]=false;

            robotBoarding();
            print();

            count=Arrays.stream(beltsDurability).filter(o->o<=0).count();
            System.out.println(count);
            c++;
        }

        System.out.println("답:"+c);

    }

    public static void beltRotation(){
        int durability=beltsDurability[2*N-1];
        boolean onbox=beltsBoxs[2*N-1];

        System.arraycopy(beltsDurability, 0, beltsDurability, 1, 2 * N - 1);
        beltsDurability[0]=durability;
        System.arraycopy(beltsBoxs, 0, beltsBoxs, 1, 2 * N - 1);
        beltsBoxs[0]=onbox;

        //c++;
    }

    public static void moveNextCell(){
        //가장 먼저 올라간 로봇부터.....
        //회전하다보면 그걸 어케 알아..?..으잉..?

        /*//내코드 주석처리
        for (int n = 0; n < 2 * N; n++) {

            // 현재 칸에 박스가 있고, 다음 칸에 공간이 있고(false), 내구도가 1이상일떄
            if (beltsBoxs[n] && !beltsBoxs[(n + 1) % (2 * N)] && beltsDurability[(n + 1) % (2 * N)]>=1) {
                //한칸 이동
                beltsBoxs[n]=false;
                //beltsBoxs[(n+1)%(2*N)]=true;

                //내코드 주석처리
                if((n+1)%(2*N)==N){
                    beltsBoxs[(n+1)%(2*N)]=false;
                }else{
                    beltsBoxs[(n+1)%(2*N)]=true;
                }
                //난 여기서 이동하고나서 N칸 일 경우 내리게 했다.

                //이동한 칸 내구도 감소
                beltsDurability[(n+1)%(2*N)]--;
            }

        }*/

        // 0 1 2 N이 3이니까 1
        for (int n = N - 2; n >= 0; n--) {
            //어처피 N칸에서 다 하차하니까 그 밑에는 로봇이 존재하지 않는다. 범위 축소 가능하다.
            //그리고 난 0부터 시작했지만, 문제에서 먼저 올라간 로봇부터이기 때문에
            //N칸 전부터 검사하면 된다.

            // 현재 칸에 박스가 있고, 다음 칸에 공간이 있고(false), 내구도가 1이상일떄
            if (beltsBoxs[n] && !beltsBoxs[n+1] && beltsDurability[n+1]>=1) {
                //한칸 이동
                beltsBoxs[n]=false;
                beltsBoxs[n+1]=true;

                //이동한 칸 내구도 감소
                beltsDurability[n+1]--;
            }

        }
        //c++;
    }

    public static void robotBoarding(){

        if (beltsDurability[0]>0 && !beltsBoxs[0]) { //박스 올려
            beltsDurability[0]--;
            beltsBoxs[0]=true;
        }
        //c++;

    }

    public static void print(){
        System.out.println(Arrays.toString(beltsDurability));
        System.out.println(Arrays.toString(beltsBoxs));
        System.out.println("-".repeat(30));
    }







    public static void moveNextCell2(){

        //벨트 이동 하면서..! 올리고! 내리고! 한꺼번에 되나?
        for (int n = 0; n < 2 * N; n++) {

            if (n == 0 && beltsDurability[n]>0 && !beltsBoxs[n]) { //박스 올려
                beltsDurability[n]--;
                beltsBoxs[n]=true;
            }
            // 현재 칸에 박스가 있고, 다음 칸에 공간이 있고(false), 내구도가 1이상일떄
            if (beltsBoxs[n] && !beltsBoxs[(n + 1) % (2 * N)] && beltsDurability[(n + 1) % (2 * N)]>=1) {
                //한칸 이동
                beltsBoxs[n]=false;
                beltsBoxs[(n+1)%(2*N)]=true;
                //이동한 칸 내구도 감소
                beltsDurability[(n+1)%(2*N)]--;
            }

            if (n == N && beltsBoxs[n]) {
                beltsBoxs[n]=false;
            }
        }


    }
}
