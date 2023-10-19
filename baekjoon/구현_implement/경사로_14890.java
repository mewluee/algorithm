package 구현_implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 경사로_14890 {


    //문제
    //경사로 길이 L
    //맵은 N * N

    static int N;
    static int L;
    public static void main(String[] args)throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st=new StringTokenizer(br.readLine(), " ");
        N=Integer.parseInt(st.nextToken());
        L=Integer.parseInt(st.nextToken());
        int count=0;
        int[][] map=new int[N][N];

        for(int n=0; n<N; n++) {
            map[n]= Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            //System.out.println(check(map[n]));
            //System.out.println(Arrays.toString(map[n]));
            if(check(map[n])) count++;
        }

        //System.out.println("=============");

        int[][] rotation=new int[N][N];
        for(int n=0; n<N; n++) {
            for(int m=0; m<N; m++) {
                rotation[n][m]=map[m][n];
            }
        }

        for(int n=0; n<N; n++) {
            //System.out.println(Arrays.toString(rotation[n]));
            if(check(rotation[n])) count++;
        }

        System.out.println(count);
    }

    public static boolean check(int[] arr) {

        int[] copy=new int[N];
        System.arraycopy(arr, 0, copy, 0, arr.length);
        //System.out.println(Arrays.toString(copy));

        int same=0;
        boolean result=true;
        for(int m=0; m<N-1; m++) {
            //System.out.println("m:"+m);
            int now=copy[m];
            int after=copy[m+1];
            if(now-after==1) {
                //System.out.println("내려가는 경우");
                //내려가는 경우 -> 오른쪽 검사
                //System.out.println("now:"+now+", after:"+after);
                int count=1; //after 포함
                int h=after;
                for(int l=0; l<L-1; l++) {
                    //after(m+1)은 검사했다고 치기 때문에 1개적은 수만큼 검사한다.
                    int index=l+m+2;
                    //System.out.println("**index:"+index);
                    if(index>=N) { //더 이상 갈 곳이없음. 검사할 수 없다.
                        result=false;
                        break;
                    }
                    int check=copy[index];
                    //System.out.println("index:"+index+", check:"+check);
                    if(check==h) {
                        //System.out.println("count 증가");
                        count++;
                    }
                    else {
                        result=false;
                        break;
                    }
                }
                if(count<L) {
                    result=false;
                    break;
                }
                m=m+L-1;
                same=-1; //0이 아니라 -1인 이유: 다음에 검사하는 now는 이미 경사로가 놓아져있기 때문
            }else if(after-now==1) {
                //System.out.println("올라가는 경우");
                //올라가는 경우 -> 왼쪽 검사
                if(same+1<L) { //경사로를 두지 못한다.
                    result=false;
                    break;
                }
                same=0;
            }else if(after==now) {
                //똑같은 경우
                //System.out.println("똑같은 경우");
                same++;
            }else {
                //높이 차가 1이상인 경우
                //System.out.println("높이 차가 1이상인 경우");
                result=false;
                break;
            }
        }

        //System.out.println(">>>>>>>>>"+result);
        return result;
    }

}
