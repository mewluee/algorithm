package 해시맵_Hashmap;

import java.io.*;
import java.util.*;

public class 영단어암기는괴로워_20920 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st=new StringTokenizer(br.readLine(), " ");

        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());

        HashMap<String, Integer> hashmap=new HashMap();

        for(int n=0; n<N; n++) {
            String input=br.readLine();
            if(input.length()<M) continue; //M이상의 단어만 암기
            hashmap.put(input, hashmap.getOrDefault(input, 0)+1);
        }

        List<Map.Entry<String, Integer>> values = new ArrayList<>(hashmap.entrySet());
        Comparator<Map.Entry<String, Integer>> comparator =
                Comparator
                        //빈도높으면 앞에
                        .comparing(Map.Entry<String, Integer>::getValue, Comparator.reverseOrder())
                        //길이가 길면 앞에
                        .thenComparing((Map.Entry<String, Integer> entry) -> entry.getKey().length(), Comparator.reverseOrder())
                        //알파벳 순으로 앞에
                        .thenComparing(Map.Entry<String, Integer>::getKey);

        values.sort(comparator);
        for(Map.Entry<String,Integer> v:values) {
            bw.append(v.getKey()+"\n");
        }
        bw.flush();
        bw.close();
    }
}
