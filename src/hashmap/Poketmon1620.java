package hashmap;

import java.io.*;
import java.util.HashMap;

public class Poketmon1620 {

    public void result() throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input=br.readLine().split(" ");
        int N=Integer.parseInt(input[0]); // 포켓몬 입력수
        int M=Integer.parseInt(input[1]); // 내가 맞춰야하는 개수
        String[] strs=new String[N];
        HashMap<String,Integer> hashMap=new HashMap<>();

        for(int i=0; i<N; i++){
            String str=br.readLine();
            strs[i]=str;
            hashMap.put(str,i);
        }


        for(int j=0; j<M; j++){
            String str=br.readLine();
            boolean isNum=str.chars().allMatch(Character::isDigit);
            if(isNum) bw.write(strs[Integer.parseInt(str)-1]+"\n");
            else bw.write(Integer.toString(hashMap.get(str)+1)+"\n");
        }



        bw.close();
    }
}
