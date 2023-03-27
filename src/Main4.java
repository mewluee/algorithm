import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Main4 {

    public static void main(String[] args) {
        int[] s=solution(new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
                new String[]{"young", "john", "tod", "emily", "mary"},
                new int[]{12, 4, 2, 5, 10});

        System.out.println(Arrays.toString(s));
    }

    HashMap<String, Integer> hashEnroll=new HashMap<>();
    HashMap<String, Integer> hashReferral=new HashMap<>();

    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        //루트 트리는 뺀다!
        //각 판매원의 이름을 담은 배열 enroll : 조직에 참여한 순서를 따른다.
        //각 판매원을 다단계 조직에 참여시킨 다른 판매원의 이름을 담은 배열 referral
        // *** enroll 해당 위치의 판매원을 참여시킨 부모 판매원의 이름이 referral
        //판매량 집계 데이터의 판매원 이름을 나열한 배열 seller
        //판매량 집계 데이터의 판매 수량을 나열한 배열 amount

        //seller입력값 순서대로하면되고
        //근데 배열이 트리 순서대로가 아니야

        //예시보니까 자식에서 부모로 이익이 올라가는 구조
        //자식->부모
        //사실 자식의 해당하는 부모값은 referral배열에 있어.

        for(int i=0; i<seller.length; i++){
            String sellerName=seller[i];
            int personalMoney=amount[i]*100;
            int index = getIndex(enroll, sellerName);
            //지금 인덱스에 셀러의 인덱스가 들어가있는 상태이다.
            System.out.println("index:"+index);

            while(index>=0){ //getindex함수에서 -1이 반환되면 끝나는 거지!
                System.out.println("index:"+index);
                int[] dividedMoneys=getMineAndMomsMoney(enroll, referral, index, personalMoney);
                //내돈은 그냥 answer에 바로 넣으면 되는데 엄마돈은 다시 계산해야함!
                answer[index]+=dividedMoneys[1];
                index=dividedMoneys[0]; //엄마의 인덱스를 넣어준다! 그럼 다시 자식이 되고 다시 또 올라가는거지.
                personalMoney=dividedMoneys[2];

            }


        }

        return answer;
    }

    //내 이익과 엄마 이익 나눠서 반환하는 메서드
    public static int[] getMineAndMomsMoney(String[] enroll, String[] referral, int index, int money){

        int[] answer=new int[3]; //현재 인덱스 반환하는게 빠를려나
        //현재 인덱스, 내돈, 엄마돈!

        String momName = referral[index];
        int momIndex = getIndex(enroll, momName);

        //10퍼센트 돈부터 계산해야한다.
        int momMoney = (int)(money * 0.1); // 소수점 자리수를 잘라버린다.
        int myMoney = money - momMoney;

        //내가 원래 생각했던건 인덱스값을 갖고 비교하는 거였는데~
        //아냐 일단 구현해보자.

        answer[0]=momIndex;
        answer[1]=myMoney;
        answer[2]=momMoney;

        return answer;

    }

    //이름에 해당하는 인덱스 가져오는 메서드
    public static int getIndex(String[] enroll, String name){
        for (int i = 0; i < enroll.length; i++) {
            if(enroll[i].equals(name))
                return i;
        }
        return -1;
    }

    //이름에 해당하는 인덱스 가져오는 메서드
    public static HashMap<String, Integer> makeHash(String[] str){
        HashMap<String ,Integer> hashMap=new HashMap<>();
        for (int i = 0; i < str.length; i++) {
            hashMap.put(str[i],i);
        }
        return hashMap;
    }
}
