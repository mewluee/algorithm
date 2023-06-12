package pigeonHouse;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class 가장가까운세사람의심리적거리_20529 {

    static List<String> output;
    static String[] output2;
    static int result;
    static int N;
    //비둘기 집 원리.
    //n개의 집이 있고 n+1마리의 비둘기가 있으면, 적어도 하나의 집엔 2마리의 비둘기가 있다.
    //3개의 집이 있고 input개의 비둘기가 있다. 적어도 하나의 집엔 2마리요?ㄴ [ㅔ?
    //그러니까 input을 해쉬셋에 걸러서 input보다 적은 해쉬셋의 사이즈가 나오면 중복이 있다는 말인데.
    //그 중복값을 어떻게 처리할 건데.
    //5개 중 2개가 다르게 중복 3 -> 다른 값으로 중복하면 중복값과 사이가 최소인 값을 찾아서 곱2..아니 음...
    //5개 중 2개가 같은 값으로 중복 3 -> 같은 값으로 중복하면 0 나와야하고
    //16개의 mbti집, 입력값은 N
    //33개 이상의 입력값이 들어오면 적어도 1개는 3번 중복한다 따라서 무조건 0이다.
    //16개의 입력이 다 중복하지 않는다고 치면, 16+16=32

    //1.브루트포스 -> 2.해쉬셋 -> 비둘기집

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());


        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());

            String[] brInput = br.readLine().split(" ");
            HashSet<String> hashInput = new HashSet<>(List.of(brInput));
            String[] input = hashInput.toArray(new String[0]);

            result = Integer.MAX_VALUE;
            output = new ArrayList<>();

            if (input.length == 1 || brInput.length>32) {
                result = 0;
            } else if (input.length == 2) {
                result = getDistance(input[0], input[1]) * 2;
            } else {
                makeCombination2(brInput);
            }
            System.out.println(result);
        }

    }

    public static void makeCombination(String[] input, boolean[] visited, int start, int count) {

        if (count == 0) {

            output = IntStream.range(0, visited.length)
                    .filter(e -> visited[e])
                    .mapToObj(e -> input[e])
                    .collect(Collectors.toList());

            int sum = getMindDistance();
            if (sum < result) {
                result = sum;
            }

        }

        for (int i = start; i < input.length; i++) {
            visited[i] = true;
            makeCombination(input, visited, i + 1, count - 1);
            visited[i] = false;
        }
    }

    public static void makeCombination2(String[] input) {

        output2 = new String[3];
        for (int i = 0; i < N; i++) {
            output2[0] = input[i];
            for (int j = i + 1; j < N; j++) {
                output2[1] = input[j];
                for (int k = j + 1; k < N; k++) {
                    output2[2] = input[k];
                    int sum = getMindDistance2();
                    if (sum < result) {
                        result = sum;
                    }
                }
            }
        }
    }

    public static int getMindDistance() {
        return getDistance(output.get(0), output.get(1))
                + getDistance(output.get(1), output.get(2))
                + getDistance(output.get(2), output.get(0));
    }

    public static int getMindDistance2() {
        return getDistance(output2[0], output2[1])
                + getDistance(output2[1], output2[2])
                + getDistance(output2[2], output2[0]);
    }

    public static int getDistance(String a, String b) {
        return (int) IntStream.range(0, 4)
                .filter(e -> a.charAt(e) != b.charAt(e))
                .count();
    }


}
