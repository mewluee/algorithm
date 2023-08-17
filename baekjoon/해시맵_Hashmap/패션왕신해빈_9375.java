package 해시맵_Hashmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 패션왕신해빈_9375 {


    static String[] arr;
    static boolean[] visited;
    //static HashMap<String, ArrayList<String>> hashMap;
    static HashMap<String, Integer> hashMap;
    static int answer;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            //키-값 : 키(의상 종류) 값(의상 이름)
            hashMap=new HashMap<>();
            int answer2=1;
            int costume=Integer.parseInt(br.readLine());
            for (int c = 0; c < costume; c++) {
                StringTokenizer st=new StringTokenizer(br.readLine(), " ");
                String name=st.nextToken();
                String type=st.nextToken();
                if(hashMap.containsKey(type)){
                    hashMap.put(type, hashMap.get(type)+1);
                }else{
                    hashMap.put(type, 1);
                }
            }
            Integer[] values=hashMap.values().toArray(Integer[]::new);
            for(Integer num:values){
                answer2*=num+1;
            }
            System.out.println(answer2-1);
        }

    }
    public static void main2(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            //키-값 : 키(의상 종류) 값(의상 이름)
            hashMap=new HashMap<>();
            answer=0;
            int costume=Integer.parseInt(br.readLine());
            for (int c = 0; c < costume; c++) {
                StringTokenizer st=new StringTokenizer(br.readLine(), " ");
                String name=st.nextToken();
                String type=st.nextToken();
                if(hashMap.containsKey(type)){
                    hashMap.put(type, hashMap.get(type)+1);
                }else{
                    hashMap.put(type, 1);
                }
            }
            arr = hashMap.keySet().toArray(String[]::new);
            //hashMap.keySet()는 Set 객체를 반환하고, toArray() 메서드는 Object[] 타입을 반환
            //배열로 변환하려는 타입(String[]로)을 명시
            visited=new boolean[arr.length];

            for (int i = 1; i <=arr.length ; i++) {
                combi(i,0,0);
            }
            System.out.println(answer);
        }
        
    }

    static void combi(int depth, int num, int count){
        if(count==depth) {
            print();
            return;
        }
        for (int i = num; i < arr.length; i++) {
            visited[i]=true;
            combi(depth, i+1, count+1);
            visited[i]=false;
        }
    }

    static void print(){
        int result=1;
        for (int i = 0; i < visited.length; i++) {
            if(visited[i]) {
                result=result*hashMap.get(arr[i]);
            }
        }
        answer+=result;
    }
}
