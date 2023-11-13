package 동적계획법_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class G5_1학년_5557 {

    //문제: 골드5 1학년 5557
    //요약:
    //마지막 두 숫자 사이에 =
    //나머지 숫자 사이에는 +, - 넣어서 등식 만들기
    //올바른 등식을 만들고자 한다.
    //음수 안배웠고, 20 초과는 모른다.
    //중간값은 늘 0이상 20이하
    //결과적으로 만들 수 있는 올바른 등식의 수 구하기.
    //풀이: dfs -> dp
    //dp 배열은 long타입이어야 한다.
    //2^63-1 이하의 개수가 출력된다고 명시됬다.

    static int N;
    static int[] nums;
    static int result;

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());

        nums= Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        long[][] dp=new long[N][21]; //경우의 수

        dp[0][nums[0]]=1; //입력:8 일때 가능한 경우의 수 -> [8]=1, 나머지 0

        for(int n=1; n<N-1; n++) { //입력은 제일 마지막 수를 제외하고 넣어준다.
            for(int m=0; m<=20; m++) { //만들 수 있는 숫자는 0~20
                if(dp[n-1][m]!=0) { //전 행에서 만들 수 있는 숫자일 경우.
                    int plus=m+nums[n]; //m(만들 수 있는 숫자)+nums[n](이번 행 입력되는 숫자) = 이번 입력으로 만들 수 있는 숫자
                    int minus=m-nums[n];

                    // plus, minus 는 이번에 만들 수 있는 경우의 수
                    if(0<=plus && plus<=20) dp[n][plus]+=dp[n-1][m]; // 전행의 경우의 수를 그대로 가져와야 한다.
                    if(0<=minus && minus<=20) dp[n][minus]+=dp[n-1][m];
                }
            }
        }
        System.out.println(dp[N-2][nums[N-1]]);
    }

    public static void main2(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());

        //숫자가 10개면, -2 한 값이 연산자 개수.
        nums=Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int sum=nums[0];
        result=0;

        dfs(1, false, 1, sum);
        dfs(1, true, 1, sum);

        System.out.println(result);
    }

    static void dfs(int index, boolean plus, int count, int sum) {
        //System.out.println("=================");
        //System.out.println("index:"+index+", "+plus+", count:"+count);
        //if(count>N-2) return;
        if(plus) {
            sum+=nums[index];
        }else {
            sum-=nums[index];
        }
        //System.out.println("sum:"+sum);
        if(count==N-2) {
            //System.out.println("count개수 도달!"+count+", sum:"+sum+", 제일마지막값:"+nums[N-1]);
            if(sum==nums[N-1]) result++;
            return;
        }
        if(sum<0 || 20<sum) return;


        dfs(index+1, true, count+1, sum);
        dfs(index+1, false, count+1, sum);

    }

}
