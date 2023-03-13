package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class StandInLine1138 {

    String site = "https://www.acmicpc.net/problem/1138";

    public static void result() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::new).toArray();
        int[] ans = new int[N];

        //로직
        //입력 배열에서 인덱스 i로 배열값을 가져와서
        //정답 배열에서 인덱스 j에서 0의 개수만큼 비교해서 차감한다.

        //나보다 큰 사람이 왼쪽에 input만큼 있어야 한다는 건
        //정답 배열에서 왼쪽부터 비교해서 0의 개수가 input만큼 있어야 한다는 것이다.


        //n > inputs
        //m > ans
        for (int n=0; n<N; n++) {
            int input=inputs[n];
            for (int m = 0; m < N; m++) {
                if(ans[m]==0&&ans[m]==input){ //0일때 input도 차감되다가 0인 순간이 그 인덱스가 들어갈 자리이다.
                    ans[m]=n+1;
                    break;
                }else if(ans[m]==0){ //0일때만 input이 차감되어야 한다. //내가 틀렸던 이유는 0이 아닌 1일때도 차감되서 문제였다.
                    input--;
                }
            }
        }

        for (int n = 0; n < N; n++) {

            System.out.print(ans[n]+" ");
        }


    }

    public static void solution()  throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arrs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::new).toArray();
        int[] ans = new int[N];
        //ans는 인덱스만큼 왼쪽에 있을 수 있는 사람 수



        //i > arrs
        //j > ans
        for (int i = arrs.length - 1,j=0; i >= 0; i--,j++) {

            int count=arrs[i]; //arr에서 하나씩 꺼내온다.
            if (count == j) {
                ans[j]=i+1;
            }else{
                for (int k = j; k > count; k--) {
                    ans[k]=ans[k-1];
                }
                ans[count]=i+1;
            }

        }

        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i]+" ");
        }
    }
}
