package 정렬_Sort;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 나이순정렬_10814 {

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T=Integer.parseInt(br.readLine());
        Member[] arr=new Member[T];
        for (int t = 0; t < T; t++) {
            st=new StringTokenizer(br.readLine());
            int age=Integer.parseInt(st.nextToken());
            String name=st.nextToken();
            arr[t]=new Member(age,name,t);
        }
        Arrays.sort(arr);
        for (int t = 0; t < T; t++) {
            bw.write(arr[t].toString()+"\n");
        }
        bw.flush();
        bw.close();
    }

    static class Member implements Comparable<Member>{
        int age;
        String name;
        int order;

        public Member(int age, String name, int order) {
            this.age = age;
            this.name = name;
            this.order = order;
        }

        @Override
        public int compareTo(Member o) {
            if(this.age==o.age){
                return this.order-o.order;
            }
            return this.age-o.age;
        }

        @Override
        public String toString() {
            return age +
                    " " + name;
        }
    }
}
