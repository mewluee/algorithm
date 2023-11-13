package 구현_implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class S5_20546_기적의매매법 {
    //문제: 실버5 20546 (개미)기적의 매매법
    //알고리즘:구현
    //요약:
    //개미 준현이 - BNP : 주식을 살 수 있다면 무조건 최대한 많이 산다.(가능한 만큼 즉시 매수)
    //성민이 - TIMING : 타이밍, 33매매법.
    //                모든 거래는 전량매수/전량매도, 빚 X
    // 				  3일 연속 가격이 전일 대비 상성시->하락 가정, 갖고있는 주식이 이러면 전량 매도한다. (상승만, 동일한 주가는 상승으로 간주안함.)
    //                3일 연속 가격이 전일 대비 하락시->상승 가정, 전량 매수한다. (하락만, 동일한 주가는 하락으로 안본다)
    //MachineDuck이라는 기업의 주식만 거래 가능
    //1~14일까지
    //주어진 현금 동일
    //매일 주식 거래 가능
    //14일에 더 많은 자산을 보유한 사람이 승리
    //현금+14일의 주가x주식 수로 계산
    //더 높은 수익률을 낼지 맞춰보기

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int jh = N;
        int sm = N;
        int jhStockCount = 0;
        int smStockCount = 0;
        int[] nums = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        for (int day = 1; day <= 14; day++) {
            int stock = nums[day - 1];
            //System.out.println("day:"+day+", stock:"+stock+", jh:"+jh+", sm:"+sm);
            if (stock <= jh) {
                //System.out.println("지훈의 전량매수!");
                jhStockCount += jh / stock;
                jh = jh % stock;
                //System.out.println("jh:"+jh+", jhStockCount:"+jhStockCount);
            }
            if (day > 3) {
                //System.out.println("타이밍:"+nums[day-3]+", "+nums[day-2]+", "+nums[day-1]);
                if (nums[day - 4] < nums[day - 3] && nums[day - 3] < nums[day - 2]) {
                    //전량 매도
                    //System.out.println("전일대비 계속 상승:성민의 전량매도!");
                    sm += stock * smStockCount;
                    smStockCount = 0;
                    //System.out.println("sm:"+sm+", smStockCount:"+smStockCount);
                }
                if (nums[day - 4] > nums[day - 3] && nums[day - 3] > nums[day - 2]) {
                    //전량 매수
                    //System.out.println("전일대비 계속 하락:성민의 전량매수!");
                    smStockCount += sm / stock;
                    sm = sm % stock;
                    //System.out.println("sm:"+sm+", smStockCount:"+smStockCount);
                }
            }
            //System.out.println("---------------");
        }

        jh += jhStockCount * nums[13];
        sm += smStockCount * nums[13];
        if (jh > sm)
            System.out.println("BNP");
        else if (jh < sm)
            System.out.println("TIMING");
        else
            System.out.println("SAMESAME");

    }
}
