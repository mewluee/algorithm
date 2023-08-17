package 탐욕_Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 보석도둑_1202 {

    static int N;
    static int K;

    //내가 한 풀이 -> 시간초과 잉잉
    public static void main2(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());


        PriorityQueue<Jewel> queue=new PriorityQueue<>(new Comparator<Jewel>() {
            @Override
            public int compare(Jewel o1, Jewel o2) {
                if(o1.price==o2.price)
                    return o2.weight-o1.weight;
                else
                    return o2.price-o1.price;
            }
        });

        for (int n = 0; n < N; n++) {
            st=new StringTokenizer(br.readLine());
            int weight=Integer.parseInt(st.nextToken());
            int price=Integer.parseInt(st.nextToken());
            queue.add(new Jewel(weight, price));
        }



        int result=0;
        for (int k = 0; k < K; k++) {
            int bag=Integer.parseInt(br.readLine());

            int size=queue.size();
            ArrayList<Jewel> list=new ArrayList<>();
            for (int s = 0; s < size; s++) {
                Jewel jewel=queue.poll();

                if(bag>=jewel.weight){
                    result+=jewel.price;
                    break;
                }
                list.add(jewel);
            }

            for (int i = 0; i < list.size(); i++) {
                queue.add(list.get(i));
            }

        }

        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());

        ArrayList<Jewel> jewelList=new ArrayList<>();
        for (int n = 0; n < N; n++) {
            st=new StringTokenizer(br.readLine());
            int weight=Integer.parseInt(st.nextToken());
            int price=Integer.parseInt(st.nextToken());
            jewelList.add(new Jewel(weight, price));
        }
        //보석 무게 오름차순 정렬
        Collections.sort(jewelList, Comparator.comparingInt(o -> o.weight));

        ArrayList<Integer> bagList=new ArrayList<>();
        for (int k = 0; k < K; k++) {
            bagList.add(Integer.parseInt(br.readLine()));
        }
        //가방 무게 오름차순 정렬
        Collections.sort(bagList, Comparator.comparingInt(o -> o));

        //보석 가격 내림차순 정렬
        PriorityQueue<Jewel> queue=new PriorityQueue<>((o1, o2) -> o2.price-o1.price);

        int jewel_index=0;
        long result=0;
        for (int k = 0; k < K; k++) {
            while(jewel_index<N && bagList.get(k) >= jewelList.get(jewel_index).weight ){
                Jewel now=jewelList.get(jewel_index);
                queue.add(now);
                jewel_index++;
            }
            if(!queue.isEmpty()) result+=queue.poll().price;
        }
        System.out.println(result);
    }

    static class Jewel{
        int weight;
        int price;

        public Jewel(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }

    }
}
