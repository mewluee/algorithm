package 탐욕_Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class G4_단어수학_1339 {

    //문제: 골드4 단어수학
    //요약:
    //N개의 단어
    //알파벳 대문자로만
    //0~9까지의 숫자 중 하나로 바꿔서 N개의 수를 합하는 문제.
    //같은 알파벳=같은 숫자
    //두개 이상의 알파벳이 같은 숫자로는 x
    //N개의 단어가 주어졌을때, 그 수의 합을 최대로 만드는 프로그램

    //풀이
    //예제1
    //A가 9면 999+999=1998

    //예제2
    //GCF
    //ACDEB
    //자리수가 다르면 제일 앞자리수가 9여야한다.
    //AC는 98
    //GCF
    //DEB
    //똑같은 자리수면, 같은 자리수에 한해서 중복되는 수가 높은 수일수록 더 크다.

    //예제3
    //똑같은 자리수면 중복되는 수가 9인게 더 좋다.

    //풀이
    //각 자리수마다 가중치를 준다.
    //가중치로 내림차순 정렬해서
    //가중치에 순서대로 큰 숫자부터 곱해서 더한다.

    //정답 코드
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        HashMap<Character, Integer> map = new HashMap<>();
        for (int n = 0; n < N; n++) {
            String str = br.readLine();
            int length = str.length();
            for (int i = length - 1; i >= 0; i--) {
                char key = str.charAt(i); // 뒤부터, 1의 자리 수부터
                map.put(key, map.getOrDefault(key, 0) + (int) Math.pow(10, length - 1 - i));
            }
        }
        List<Integer> values = new ArrayList<>(map.values());
        Collections.sort(values, Collections.reverseOrder());
        int numIndex = 9;
        int result = 0;
        for (int i = 0; i < values.size(); i++) {
            result += values.get(i) * numIndex--;
        }
        System.out.println(result);
    }

    static int index;
    static boolean[] visited;
    static int[] arr;
    static String[] strs;
    static int N;
    static HashMap<Character, Integer> map;
    static int answer = 0;
    //조합짜서 테스트하기
    public static void main3(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[10]; //사용한 숫자 표현
        map = new HashMap<>();
        index = 0; // 입력된 문자들의 개수
        strs = new String[N];
        for (int n = 0; n < N; n++) {
            String str = br.readLine();
            strs[n] = str;
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (!map.containsKey(ch)) {
                    map.put(ch, index);
                    index++;
                }
            }
        }

        arr = new int[index];
        Arrays.fill(arr, -1);

        dfs(0, 0);
        System.out.println(answer);
    }

    static void dfs(int count, int next) {
        if (count >= index) {
            //System.out.println("검사");
            //System.out.println(Arrays.toString(arr));
            int result = 0;
            for (int n = 0; n < N; n++) {
                int number = 0;
                for (int i = 0; i < strs[n].length(); i++) {
                    int index = map.get(strs[n].charAt(i));
                    number += arr[index] * Math.pow(10, strs[n].length() - 1 - i);
                }
                result += number;
            }
            if (answer < result) answer = result;
            return;
        }
        for (int i = 0; i < 10; i++) { //조합만들 숫자들
            if (visited[i]) continue;
            arr[next] = i;
            visited[i] = true;
            dfs(count + 1, next + 1);

            visited[i] = false;
            arr[next] = -1;
        }
    }

    static void check() {
        int result = 0;
        for (int n = 0; n < N; n++) {
            int number = 0;
            for (int i = 0; i < strs[n].length(); i++) {
                int index = map.get(strs[n].charAt(i));
                number += arr[index] * Math.pow(10, strs[n].length() - 1 - i);
            }
            result += number;
        }
        if (answer < result) answer = result;
    }

    //틀림
    public static void main2(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] saved = new String[N];
        HashMap<Character, MathChar> map = new HashMap<>();
        int numberIndex = 9;
        for (int n = 0; n < N; n++) {
            String str = br.readLine();
            saved[n] = str;
            int startIndex = str.length() - 1;
            for (int i = startIndex; i >= 0; i--) {
                char nowKey = str.charAt(i);
                int index = startIndex - i;
                MathChar mc;
                if (map.containsKey(nowKey)) {
                    mc = map.get(nowKey);
                } else {
                    mc = new MathChar(nowKey);
                    map.put(nowKey, mc);
                }
                mc.compareMaxIndex(index);
                mc.addIndexCount(index);
            }
        }

        PriorityQueue<MathChar> queue = new PriorityQueue<>();
        for (char key : map.keySet()) {
            queue.add(map.get(key));
        }
        while (!queue.isEmpty()) {
            MathChar now = queue.poll();
            now.num = numberIndex;
            now.print();
            System.out.println(now.type + ":" + now.num);
            numberIndex--;
        }
        int result = 0;
        for (int n = 0; n < N; n++) {
            int length = saved[n].length();
            int number = 0;
            for (int i = 0; i < length; i++) {
                number += (int) (map.get(saved[n].charAt(i)).num * Math.pow(10, length - 1 - i));
            }
            result += number;
        }
        System.out.println(result);
    }

    static class MathChar implements Comparable<MathChar> {
        char type;
        int num;
        int maxIndex = 0;
        int[] indexCount = new int[8];

        MathChar(char type) {
            this.type = type;
        }

        void compareMaxIndex(int index) {
            if (index > maxIndex) maxIndex = index;
        }

        void addIndexCount(int index) {
            indexCount[index]++;
            if (indexCount[index] > 9) maxIndex++;
        }

        @Override
        public int compareTo(MathChar o) {
            if (this.maxIndex == o.maxIndex) {
                int count1 = this.indexCount[this.maxIndex];
                int count2 = o.indexCount[o.maxIndex];
                if (count1 == count2) {
                    int sum1 = Arrays.stream(this.indexCount).sum();
                    int sum2 = Arrays.stream(o.indexCount).sum();
                    return sum2 - sum1; //내림차순
                } else {
                    return count2 - count1; //내림차순
                }
            } else {
                return o.maxIndex - this.maxIndex; //내림차순
            }
        }

        public void print() {
            System.out.println("max:" + maxIndex + "," + Arrays.toString(indexCount));
        }
    }
}
