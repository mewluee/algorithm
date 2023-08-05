package 해시맵_Hashmap;

import java.io.*;
import java.util.HashMap;

public class 비밀번호찾기_17219 {

    private String site="https://www.acmicpc.net/problem/17219";

    public void result() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] strarr=br.readLine().split(" ");
        int N = Integer.parseInt(strarr[0]); // 저장된 사이트 주소
        int M = Integer.parseInt(strarr[1]); // 찾으려는 사이트 주소

        HashMap<String,String> hashMap=new HashMap<String, String>();

        //사이트랑 비밀번호 입력받기
        for(int n=0; n<N; n++){
            String[] sitepwd=br.readLine().split(" ");
            hashMap.put(sitepwd[0],sitepwd[1]);
        }

        for(int m=0; m<M; m++){
            String site=br.readLine();
            bw.write(hashMap.get(site)+"\n");
        }

        bw.close();

    }
}
