package programmers;

import java.io.IOException;

public class 택배배달과수거하기 {

    public static void main(String[] args) throws IOException {

        System.out.println(solution(4, 5, new int[]{1, 0, 3, 1, 2}, new int[]{0, 3, 0, 4, 0}));
        System.out.println(solution(2, 7, new int[]{1,0,2,0,1,0,2}, new int[]{0,2,0,1,0,2,0}));

    }

    public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        //먼집부터 배달하면서, 돌아오는 길에 최대한 많이 수거하는게...
        //그런데 이 수거도 먼집부터 한다.
        //배달,수거할게 없으면 멀리갈 이유가 없거둔..

        //인덱스+1 가야하는 거리

        //그런데 더 좋은건 멀리가는 건 좋지만,
        //오고 갈때 공간을 꽉꽉 채워서 가기!


        //BFS써야하나여..?
        //안쓰고 가능..?


        //일단 안쓰고 해봐

        //long m=n;
        //드라마틱한 변화는 없네..?
        while (!isDone2(deliveries, pickups,n)) {

            //배달 거리 계산
            //개선 전
            //n=(int)Math.max(getDeliveryLoad(cap, n, deliveries), getPickupLoad(cap, n, pickups));
            //개선 후
            n = (int) Math.max(getLoad(cap, n, deliveries), getLoad(cap, n, pickups));
            answer=answer+n*2;


        }


        return answer;
    }

    public static boolean isDone2(int[] deliveries, int[] pickups, int n){
        for (int i = 0; i < n; i++) {
            if (deliveries[i] != 0) {
                return false;
            }
        }

        for (int i = 0; i < n; i++) {
            if (pickups[i] != 0) {
                return false;
            }
        }

        return true;
    }

    //이 부분이 좋지 않다.
    //다른사람들은 포인터로 굳이 안돌아가게 처리했는데
    //나는 매번 반복문마다 다시 다 처음부터 검사해서 시간이 더 소요된다.
    //이부분도 나는 n으로 처리해보자.
    public static boolean isDone(int[] deliveries, int[] pickups){
        for (int i = 0; i < deliveries.length; i++) {
            if (deliveries[i] != 0) {
                return false;
            }
        }

        for (int i = 0; i < pickups.length; i++) {
            if (pickups[i] != 0) {
                return false;
            }
        }

        return true;
    }

    public static long getLoad(int cap, int n, int[] arrs) {
        long load=0;

        for (int i = n-1; i >= 0; i--) {
            if(arrs[i]!=0 && cap>0){
                load=Math.max(load,i+1);
                if(cap>=arrs[i]){
                    cap=cap-arrs[i];
                    arrs[i]=0;
                }else{
                    arrs[i]=arrs[i]-cap;
                    cap=0;
                }
                if(cap==0) break;
            }
        }
        return load;
    }


    //와 이부분 어처피 겹쳐서 하나의 메서드로 처리해버리네.
    public static long getDeliveryLoad(int cap, int n, int[] deliveries){
        long load=0;

        for (int i = n-1; i >= 0; i--) {
            if(deliveries[i]!=0 && cap>0){
                load=Math.max(load,i+1);
                if(cap>=deliveries[i]){
                    cap=cap-deliveries[i];
                    deliveries[i]=0;
                }else{
                    deliveries[i]=deliveries[i]-cap;
                    cap=0;
                }
                if(cap==0) break;
            }
        }
        //System.out.println("d:"+load);
        return load;
    }

    public static long getPickupLoad(int cap, int n, int[] pickups) {
        long load=0;
        for (int i = n - 1; i >= 0; i--) {
            if(pickups[i]!=0 && cap>0){
                load=Math.max(load,i+1);
                if(cap>=pickups[i]){
                    cap=cap-pickups[i];
                    pickups[i]=0;
                }else{
                    pickups[i]=pickups[i]-cap;
                    cap=0;
                }
                if(cap==0) break;
            }
        }
        //System.out.println("p:"+load);
        return load;
    }

    //다른 사람 풀이
    public long solution2(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        int dPointer = n-1; //인덱스
        int pPointer = n-1;



        while(dPointer >= 0 || pPointer >= 0) {

            //배열값을 조회하긴 해. deliveries[dPointer] 이부분.
            while(dPointer>=0 && deliveries[dPointer] == 0) dPointer--; //인덱스만 바꾼다.
            while(pPointer>=0 && pickups[pPointer] == 0) pPointer--;

            int sum = 0;

            answer += (Math.max(dPointer,pPointer)+1)*2;

            // dPointer 조정
            while(dPointer >= 0 && sum < cap) {
                sum += deliveries[dPointer]; //담고
                deliveries[dPointer--] = 0; //박스 소거
            }//sum이 용량과 같아지는 순간 나가고
            if(sum > cap) {//만약 초과한다면 다시 인덱스 증가시켜서 다시 박스 남겨놔야함.
                deliveries[++dPointer] = sum-cap;
            }

            sum = 0;
            // pPointer 조정
            while(pPointer >= 0 && sum < cap) {
                sum += pickups[pPointer];
                pickups[pPointer--] = 0;
            }
            if(sum > cap) {
                pickups[++pPointer] = sum-cap;
            }
        }


        return answer;
    }
}
