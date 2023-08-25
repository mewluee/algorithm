package level0;

import java.io.IOException;
import java.util.Arrays;

public class 배열의평균값 {

    //방법1 : stream의 average() 사용
    public static void main2(String[] args) throws IOException {
        int[] numbers= {1,2,3,4,5,6,7,8,9,10};
        double answer= Arrays.stream(numbers).average().orElse(0);
        System.out.println(answer);
    }

    //방법2 : for문 사용
    public static void main(String[] args) throws IOException{
        int[] numbers= {1,2,3,4,5,6,7,8,9,10};
        int sum=0;
        for(int n:numbers) {
            sum+=n;
        }
        double answer=sum/(double)numbers.length;
        System.out.println(answer);
    }
}
