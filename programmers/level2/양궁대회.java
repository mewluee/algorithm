package level2;

import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;

public class 양궁대회 {

//문제: 프로그래머스 level2 양궁대회
    //요약:
    //화살 개수
    //해당 점수 과녁에 화살개수가 더 많이 맞춘 사람이 점수를 획득
    //인덱스 : 0 1 2 3 4 5 6 7 8 9 10
    //점수(점):10 9 8 7 6 5 4 3 2 1 0
    //

    public static void main(String[] args) throws IOException {

        Solution s = new Solution();
        int[] result = s.solution(5, new int[]{2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0});
        System.out.println("결과:" + Arrays.toString(result));
//		count=0;
//		lion=new int[11];
//		dfs2222(1, 10);
//		System.out.println(count);

    }

    static int count;
    static int[] apeach = {5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    static int[] lion;

    //은정님이 작성하신 소스코드
    static void dfs2222(int round, int n) {
        count++;
        //System.out.println("-------round:"+round);
        if (round == n + 1) {
            //System.out.println("끝!!");
            return;
        }
        //lion[1,0,0] dfs(round=2, 3);
        //lion[2,0,0] dfs(round=2, 3); // dfs(round=3, 3);
        for (int j = 0; j <= 10 && lion[j] <= apeach[j]; j++) {
            //System.out.println("for****");
            lion[j]++;
            //System.out.println("lion:"+Arrays.toString(lion));
            //System.out.println("round:"+(round+1));
            dfs2222(round + 1, n);
            lion[j]--;
        }
    }

    static class Solution {
        //라이언vs어피치
        //라이언에게 불리한 규칙
        //어피치 n발 -> 라이언 n발
        //점수 계산
        //각 점수마다 맞춘 화살의 개수를 비교해서 많이 맞춘 사람한테 k점
        //화살의 개수가 동일하면 어피치가 가져간다
        //둘다 못맞춘 점수면 0점
        //어피치 n발 다 쏜후
        //라이언이 어피치를 가장 큰 점수 차이로 이기기 위해서 n발의 화살을 어떤 과녁 점수에 맞춰야하는지 구하기
        //화살 개수 n
        //어피치가 맞춘 점수 배열 info (내림차순 10점->0점)
        //라이언이 맞춰야하는 점수 배열 answer (내림차순 10점->0점)
        //답이 여러개일 경우, 가장 낮은 점수를 더 많이 맞힌 경우
        //인덱스 0->10 기준으로 큰수부터 비교하면 된다.
        //라이언이 우승할 방법이 없으면 [-1]

        //풀이:백트래킹
        //인덱스 : 0 1 2 3 4 5 6 7 8 9 10
        //점수(점):10 9 8 7 6 5 4 3 2 1 0

        int[] info;
        int N;
        int[] arr;
        PriorityQueue<Score> queue = new PriorityQueue<>();

        public int[] solution(int n, int[] info) {
            int[] answer = {-1};
            this.N = n;
            this.info = info;
            arr = new int[11];
            System.out.println(Arrays.toString(info));
            dfs(n, 0);
            if (queue.size() != 0) {

                System.out.println("결과차이:" + queue.peek().score);
                answer = queue.poll().arr;
            }
            return answer;
        }

        public void dfs(int count, int index) {
            //System.out.println("d");

            if (count <= 0) {
                checkScore();
            }
            for (int i = index; i <= 10; i++) {
                for (int j = 1; j < info[i] + 2; j++) {
                    if (j > count) break;
                    arr[i] += j;
                    count -= j;
                    dfs(count, i + 1);
                    arr[i] -= j;
                    count += j;
                }
                //arr[i]=0;
                //count=temp;
            }
        }

        public void checkScore() {
            //System.out.println("체크값:"+Arrays.toString(arr));
            int A = 0; //라이언
            int B = 0; //어피치
            for (int n = 0; n < 11; n++) {
                if (arr[n] == 0 && info[n] == 0) continue;
                if (arr[n] <= info[n]) {
                    B += (10 - n);
                } else {
                    A += (10 - n);
                }
            }
            //System.out.println("라이언:"+A+", 어피치:"+B);
            if (A > B) {
                int[] copy = arr.clone();
                queue.add(new Score(copy, A - B));
                System.out.println("A:" + A + ", B:" + B + ",차이:" + (A - B) + ", 구한값:" + Arrays.toString(arr));
                //System.out.println("라이언:"+A+", 어피치:"+B);
            }
        }

        class Score implements Comparable<Score> {
            int[] arr;
            int score;

            public Score(int[] arr, int score) {
                this.arr = arr;
                this.score = score;
            }

            @Override
            public int compareTo(Score o) {
                if (o.score == this.score) {
                    int index = 10;
                    for (int n = 10; n >= 0; n--) {
                        if (this.arr[n] != o.arr[n]) {
                            index = n;
                            break;
                        }
                    }
                    return o.arr[index] - this.arr[index];
                } else {
                    return o.score - this.score;
                }
            }
        }


    }
}
