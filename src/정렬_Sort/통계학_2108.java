package 정렬_Sort;

import java.io.*;
import java.util.*;

public class 통계학_2108 {

    private String site="https://www.acmicpc.net/problem/2108";

    public void result() throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        int numberCount=Integer.parseInt(br.readLine());

        List<Integer> list=new ArrayList<Integer>();

        for(int i=0; i<numberCount; i++){
            list.add(Integer.parseInt(br.readLine()));
        }

        //System.out.println(Arrays.toString(list.toArray()));

        //1.평균
        //Stream<Double> sd=list.stream();
        //double average=sd.mapToDouble(x->x).average().getAsDouble();
        double average=list.stream().mapToDouble(x->x).average().getAsDouble();
        //System.out.println("**"+average);
        /*double test=1.76;
        double test2=1.35;
        System.out.println(String.format("%.0f  %.0f",test,test2));*/
        bw.write(Math.round(average)+"\n");
        //아오..정말..너무...래퍼클래스 싫다..우욱

        //2.중앙값
        Collections.sort(list);
        int mid=list.get((list.size()-1)/2);
        //System.out.println(Arrays.toString(list.toArray()));
        bw.write(Integer.toString(mid)+"\n");


        //3.최빈값
        HashMap<Integer,Integer> hashMap=new HashMap<>();
        for(int i=0; i<list.size(); i++){
            hashMap.put(list.get(i),0); // 초기화
        }
        for(int j=0; j<list.size(); j++){
            int val=hashMap.get(list.get(j));
            hashMap.put(list.get(j),val+1); // 카운트 증가
        }
        // Hashmap으로 중복값도 정리해서
        // 배열이나 리스트에 담아가지고
        // value값에 따라 내림차순 정리하고
        // max값을 찾아서 출력하기
        // 다음 값도 max값과 동일하면 두번째 값으로 출력하기
        int[][] arr=new int[hashMap.size()][2];
        // 값 / 빈도수
        Iterator<Map.Entry<Integer,Integer>> it=hashMap.entrySet().iterator();
        int index=0;
        while(it.hasNext()){
            Map.Entry<Integer,Integer> entryset=(Map.Entry<Integer, Integer>) it.next();
            arr[index][0]=entryset.getKey();
            arr[index][1]=entryset.getValue();
            index++;
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1]==o2[1]) return o1[0]-o2[0]; // y좌표가 같으면 x좌표 오름차순
                else return o2[1]-o1[1]; // y좌표 내림차순
            }
        });
        if(arr.length>1){
            if(arr[0][1]==arr[1][1])
                bw.write(Integer.toString(arr[1][0])+"\n"); //최빈값이 동일할때 0말고 1인덱스값 출력
            else
                bw.write(Integer.toString(arr[0][0])+"\n"); //최빈값이 동일하지 않다면 0인덱스값 출력
        }else if(arr.length==1){
            bw.write(Integer.toString(arr[0][0])+"\n"); //개수가 하나라면 0인덱스값 출력
        }




        //4.범위:최대-최소 차이
        int max=Collections.max(list);
        int min=Collections.min(list);
        int distance=max-min;
        bw.write(Integer.toString(distance)+"\n");

        bw.close();

    }
}
