package 정렬_Sort;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class 단어정렬_1181 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        HashSet<String> hs=new HashSet();
        for (int n = 0; n < N; n++) {
            hs.add(br.readLine());
        }
        String[] arr=hs.toArray(String[]::new);
        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length()==o2.length()){
                    for (int i = 0; i < o1.length(); i++) {
                        if(o1.charAt(i)!=o2.charAt(i))
                            return o1.charAt(i)-o2.charAt(i);
                    }
                }
                return o1.length()-o2.length();
            }
        });

        for (int i = 0; i < hs.size(); i++) {
            bw.write(arr[i]+"\n");
        }
        bw.flush();
        bw.close();
    }

}
