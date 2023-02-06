package sort;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

public class NumberGame1755 {

    private String site="https://www.acmicpc.net/problem/1755";

    private String[][] dic = new String[][]{
            {"0", "zero"}, {"1", "one"}, {"2", "two"}, {"3", "three"}, {"4", "four"},
            {"5", "five"}, {"6", "six"}, {"7", "seven"}, {"8", "eight"}, {"9", "nine"}};

    public void result(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        String[] str=s.nextLine().split(" ");
        int M = Integer.parseInt(str[0]);//start
        int N = Integer.parseInt(str[1]);//end

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));



        String[][] result = numberToString(M, N); //숫자를 문자로 바꿔서 배열로

        Arrays.sort(result, (o1, o2)->{
            return o1[0].compareTo(o2[0]); // o1보다 o2가 크면 음수값이 반환됨.
        });

        //System.out.println(Arrays.deepToString(result)); //정렬 확인

        for(int i=0; i<N-M+1; i++){
            if(i!=0 && i%10==0) bw.write("\n");
            bw.write(result[i][1]+" ");
        }

        bw.close();

    }

    public String[][] numberToString(int M, int N) {

        String[][] result = new String[N-M+1][2];
        //배열로 구현해서 정렬만들어보기
        int index=0;

        for (int x = M; x <= N; x++) {
            String number = Integer.toString(x);
            String str = "";

            for (int y = 0; y < number.length(); y++) {
                char oneCharacter = number.charAt(y); //string(index)->char
                int oneInteger = Character.getNumericValue(oneCharacter); //char->int
                str = str + dic[oneInteger][1] + " ";
            }

            str.substring(0, str.length() - 1); //마지막 " "제거
            result[index][0]=str;
            result[index][1]=number;

            index++;
        }

        return result;

    }
}
