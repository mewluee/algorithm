package programmers;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.LongStream;

public class 로또의최고순위와최저순위 {


    public static void main(String[] args) throws IOException {

        System.out.println(Arrays.toString(solution(new int[]{44, 1, 0, 0, 31, 25}, new int[]{31, 10, 45, 1, 6, 19})));
        System.out.println(Arrays.toString(solution(new int[]{0, 0, 0, 0, 0, 0}, new int[]{38, 19, 20, 40, 15, 25})));
        System.out.println(Arrays.toString(solution(new int[]{45, 4, 35, 20, 3, 9}, new int[]{20, 9, 3, 45, 4, 35})));

    }

    //..? 이게 더 시간이 오래걸리는데..?
    //범위가 작아서 그런가?
    public static int[] solution(int[] lottos, int[] win_nums) {
        int zeroCount=0;
        //lottos만 순회해서 0의 개수 세고, 해쉬셋에 저장한다.

        HashSet<Integer> hashSet=new HashSet<>();
        for (int i = 0; i < lottos.length; i++) {
            if (lottos[i] == 0) {
                zeroCount++;
            } else {
                hashSet.add(lottos[i]); //내부에 값이 존재하면 false를 반환한다.
            }
        }

        int sameNumbersCount=0;
        for (int j = 0; j < win_nums.length; j++) {
            if (!hashSet.add(win_nums[j])) {
                sameNumbersCount++;
            }
        }
        return new int[]{
                7-(sameNumbersCount+zeroCount)<6?7-(sameNumbersCount+zeroCount):6
                , 7-sameNumbersCount<6?7-sameNumbersCount:6};
    }

    //스트림사용 시간은 느리지만..대박..
    public static int[] solution2(int[] lottos, int[] winNums) {
        return LongStream.of(
                        (lottos.length + 1) - Arrays.stream(lottos).filter(l -> Arrays.stream(winNums).anyMatch(w -> w == l) || l == 0).count(),
                        (lottos.length + 1) - Arrays.stream(lottos).filter(l -> Arrays.stream(winNums).anyMatch(w -> w == l)).count()
                )
                .mapToInt(op -> (int) (op > 6 ? op - 1 : op))
                .toArray();
    }

    //내 속도랑 비슷
    public int[] solution3(int[] lottos, int[] win_nums) {
        Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
        int zeroCount = 0;

        for(int lotto : lottos) {
            if(lotto == 0) {
                zeroCount++;
                continue;
            }
            map.put(lotto, true);
        }


        int sameCount = 0;
        for(int winNum : win_nums) {
            if(map.containsKey(winNum)) sameCount++;
        }

        int maxRank = 7 - (sameCount + zeroCount);
        int minRank = 7 - sameCount;
        if(maxRank > 6) maxRank = 6;
        if(minRank > 6) minRank = 6;

        return new int[] {maxRank, minRank};
    }

    //속도 제일 빠름
    public int[] solution4(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];

        int cnt1 = 0;
        int cnt2 = 0;
        for(int i : lottos) {
            if(i == 0) {
                cnt1++;
                continue;
            }
            for(int j : win_nums) {
                if(i == j) cnt2++;
            }
        }


        answer[0] = getGrade(cnt1+cnt2);
        answer[1] = getGrade(cnt2);

        return answer;
    }

    public int getGrade(int n) {
        switch(n) {
            case 6 :
                return 1;
            case 5 :
                return 2;
            case 4 :
                return 3;
            case 3 :
                return 4;
            case 2 :
                return 5;
            default :
                return 6;
        }
    }

    //랭크부분 표현한거 멋지당. 속도는 느림.
    public int[] solution5(int[] lottos, int[] win_nums) {
        int[] rank = {6, 6, 5, 4, 3, 2, 1};
        int answer = 0;
        int hidden = 0;

        Arrays.sort(win_nums);
        for (int i = 0; i < lottos.length; i++)
            if (Arrays.binarySearch(win_nums, lottos[i]) > -1)
                answer++;
            else if (lottos[i] == 0)
                hidden++;

        return new int[] {rank[answer + hidden], rank[answer]};
    }
}
