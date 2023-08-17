package 조합_Combination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 블랙잭_2798 {

    static int N;
    static int M;
    static int[] cards;
    static int result;
    static int answer;
    //깊이가 3으로 고정되어있으니까 for문 3번으로 됨.
    //혹시하고 재귀 방법으로 하니까 시간초과뜸.
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        cards= Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                for (int k = j+1; k < N; k++) {
                    int sum=cards[i]+cards[j]+cards[k];
                    if(sum<=M && answer<sum){
                        answer=sum;
                    }
                }
            }
        }
        System.out.println(answer);
    }
    
    public static void main2(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        cards= Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        result=0;
        answer=0;
        combi(0, 0);
        System.out.println(answer);
    }

    static void combi(int count, int input){
        if(count==3){
            if(result<=M && answer<result){
                answer=result;
            }
        }
        for (int i = input; i < N; i++) {
            result+=cards[i];
            combi(count+1, i+1);
            result-=cards[i];
        }
    }
}
