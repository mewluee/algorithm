package 해시셋_Hashset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class 문자열집합_14425 {

    private String site="https://www.acmicpc.net/problem/14425";

    public void result() throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st=new StringTokenizer(br.readLine(), " ");
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        HashSet<String> hashSet=new HashSet<>();
        int count=0;
        String one;

        for(int n=0; n<N; n++){
            one=br.readLine();
            hashSet.add(one);
        }

        for(int m=0; m<M; m++){
            one=br.readLine();
            if(hashSet.contains(one)) count++;
        }

        System.out.println(count);


    }
}
