package programmers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class 이모티콘할인행사 {

    public static void main(String[] args) throws IOException {
        solution(new int[][]{{40, 10000}, {25, 10000}}, new int[]{7000, 9000});
        solution(new int[][]{{40, 2900}, {23, 10000},{11,5200},{5,5900},{40,3100},{27,9200},{32,6900}}, new int[]{1300, 1500,1600,4900});
    }

    // 1 유저 - 4 이모티콘
    // 유저의 비율/돈 - 이모티콘 정가
    //
    // 목적
    // 1. 이모티콘 플러스 서비스 가입자 수 최대
    // 2. 이모티콘 판매액 최대

    // 이모티콘 플러스 서비스 가입자 수가 많으려면, 유저가 이모티콘을 많이 샀을 때 비용이 초과해야 한다.
    // 각 이모니콘마다 할인율을 다르게 해서 구매유저가 많아야한다.
    // 그렇다고 할인율을 올려서 다 사게했다고 치자. 비용이 초과를 안해서 서비스 가입자 수가 적을 수 있다.

    // 브루트 포스 알고리즘 > 각 이모티콘의 할인율에 따라 조합을 검사해서 1번 결과가 클 때만 답을 갱신하다.
    // 브루트 포스 범위를 줄일 수 있는가? 범위 사용자의 비율 범위내로 조정
    // 그럼 놓치는 값이 있는가? 모르겠음

    // 그리디 알고리즘
    // 그때그때 욕망에 따라.....음.....
    // 많이 구매해도 오케이 > 그럼 각 이모니콘마다 최대로 많이 사게 하면 됨. 무조건 할인율 크게 하면되는데 그러면 비용문제가 발생함.
    // 적절하지 않은 것 같다.

    // 놀랍게도.. 할인율은 1~40 40가지가 아니라..
    // 10 20 30 40 >> 4가지였다.....충격이네... 문제좀 여러번 읽어야겠다.

    // DFS BFS ?
    // 순열이래!!

    // 어떤 자료구조를 써야 효율적일까?

    static int[] solutionAnswer;
    static int[] fixedEmoticons;

    static int[][] fixedUsers;

    public static int[] solution(int[][] users, int[] emoticons) {
        solutionAnswer = new int[2];
        fixedEmoticons = emoticons;
        fixedUsers = users;

        //반복문을 돌면서
        //순열로 만든 비율 배열표로 모든 손님 배열을 순회해서 계산한다.
        //비율로 순열만들기!

        //DFS라는데 어느 부분에 DFS가 적용되야 하는가.
        //순열만들때? 순열은 그냥 만들어서 배열에 촤근촤근..?
        //만들때마다 검사하면 되는거아냐? 도대체 어디서 DFS가..?
        //일단 DFS없는 상황에서 만들어보자.>> ㅋㅋ불가능! 결과적으로 순열만들때 DFS써야한다.왜냐? 밑에있음.

        int[] fixedDiscountRate=new int[]{10, 20, 30, 40};
        getDiscountRates(fixedDiscountRate, emoticons.length, new int[0]);

        return solutionAnswer;
    }

    public static void getDiscountRates(int[] fixedDiscountRate, int count, int[] answerRate){

        //순열만들기

        int[] newAnswer;
        //System.out.println("count:"+count);

        if (count <= 0) {
            System.out.println("e:"+ Arrays.toString(answerRate));
            getAnswer(answerRate);

        }else{
            newAnswer = new int[answerRate.length + 1];
            //System.out.println(newAnswer.length);
            System.arraycopy(answerRate, 0, newAnswer, 0, answerRate.length);
            //System.out.println(newAnswer.length);

            for (int i = 0; i < fixedDiscountRate.length; i++) {


                newAnswer[newAnswer.length-1]=fixedDiscountRate[i];
                getDiscountRates(fixedDiscountRate, count - 1, newAnswer);
                //System.out.println("newAnswer:"+Arrays.toString(newAnswer));
            }
        }

    }

    public static void getAnswer(int[] discountRate){
        int[] subAnswer=new int[2];

        //모든 인원들을 가져온 비율로 검사한다.
        for (int i = 0; i < fixedUsers.length; i++) {
            int personalTotalMoney=0;
            //사람당 총 금액

            //이모티콘 개수만큼 검사 필요하다 = 이모티콘 개수 = 비율 개수
            for (int j = 0; j < fixedEmoticons.length; j++) {

                //각 개인의 비율 <= 할인율이 크다면 사야지! 그래서 할인율이 적용된 이모티콘 가격 계산
                if (fixedUsers[i][0] <= discountRate[j]) {
                    double personalEmoticonMoney = fixedEmoticons[j] * (1 - (discountRate[j] / (double)100));
                    personalTotalMoney+=personalEmoticonMoney;
                    //해당 이모티콘을 샀다고 가정 그래서 누적해서 저장한다.
                    //System.out.println("personaltotalmoney:"+personalTotalMoney);
                }
            }
            System.out.println(i+"번째 손님 이모티콘 총 구매비용:"+personalTotalMoney);

            //총 이모티콘 구매 내역이 해당 손님의 가격보다 클때
            if (personalTotalMoney >= fixedUsers[i][1]) {
                subAnswer[0]++;

            } else {
                subAnswer[1]+=personalTotalMoney;
            }


        }
        System.out.println("-".repeat(30));
        System.out.println("subAnswer:"+Arrays.toString(subAnswer));
        System.out.println(">>>>subAnswer:"+Arrays.toString(subAnswer));
//        if (subAnswer[0] >= solutionAnswer[0]) {
//            solutionAnswer[0]=subAnswer[0];
//            if (subAnswer[1] > solutionAnswer[1]) {
//                solutionAnswer[1]=subAnswer[1];
//            }
//        }else{
//            if (subAnswer[1] > solutionAnswer[1]) {
//                solutionAnswer[1]=subAnswer[1];
//            }
//        }

        if (subAnswer[0] > solutionAnswer[0]) {
            solutionAnswer[0] = subAnswer[0];
            solutionAnswer[1] = subAnswer[1];
        }
        else if(subAnswer[0] == solutionAnswer[0]) {
            if(subAnswer[1] > solutionAnswer[1]) {
                solutionAnswer[1] = subAnswer[1];
            }
        }

        System.out.println(">>>>solutionAnswer:"+Arrays.toString(solutionAnswer));



    }

    //-------------------------------이럼 공간 많이 차지하지 않나?
    //거진 뭐 속도랑 공간 또이또이한데?
    public static int[] solution2(int[][] users, int[] emoticons) {
        int[] percent = {10,20,30,40};
        int[] arr = new int[emoticons.length];
        List<Service> serviceList = new ArrayList<>();
        dfs(percent,emoticons,arr,users,serviceList,0);
        serviceList.sort(Comparator.reverseOrder());
        Service service = serviceList.get(0);

        return new int[]{service.getNumber(),service.getPrice()};
    }

    static void dfs(int[] percent,int[] emoticons,int[] arr,int[][] users,List<Service> serviceList,int depth){
        if(depth==emoticons.length){
            int price = 0;
            int number = 0;
            for (int[] user : users) {
                int salePercent = user[0];
                int maxPrice = user[1];
                int money = 0;
                for (int q = 0; q < arr.length; q++) {
                    if (arr[q] >= salePercent) {
                        money += emoticons[q] * (1 - ((double) arr[q] / 100));
                    }
                }
                if (money >= maxPrice) {
                    number++;
                } else {
                    price += money;
                }
            }
            serviceList.add(new Service(number,price));
            return;
        }
        for (int p : percent) {
            arr[depth] = p;
            dfs(percent, emoticons, arr, users, serviceList,depth + 1);
        }
    }

    static class Service implements Comparable<Service>{
        int number;

        int price;

        Service(int number,int price){
            this.number = number;
            this.price = price;
        }

        public int getNumber() {
            return number;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public int compareTo(Service o) {
            return this.number==o.number? this.price-o.price:this.number-o.number;
        }
    }

}
