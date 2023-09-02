package 탐욕_Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 크게만들기_2812 {
    //k번 검사
    //인덱스 0번부터 n번까지, 오른쪽 수랑 비교해서 작을 경우 해당 인덱스를 제거한다.
    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine(), " ");
        int N=Integer.parseInt(st.nextToken());
        int K=Integer.parseInt(st.nextToken());

        String input=br.readLine();

        List<Integer> num=new ArrayList<>();


        for(int n=0; n<N; n++) {
            char ch=input.charAt(n);
            int number=ch-'0';
            num.add(number);
        }

        System.out.println("전:"+num);

        for(int k=0; k<K; k++) {
            int index=-1;
            for(int n=0; n<num.size()-1; n++) {
                int a=num.get(n);
                int b=num.get(n+1);
                System.out.println("a:"+a+", b:"+b);
                if(a>=b) continue;
                index=n;
                break;
            }
            num.remove(index);
            System.out.println(num);
        }
    }
}
