package 투포인터_TwoPointers;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 수열_2559 {


    public static void main2(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputStr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int N = inputStr[0];
        int K = inputStr[1];

        int[] inputDays = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int start = 0;
        int end = 0;
        int max_sum = Integer.MIN_VALUE;
        int sum = 0;
        while (start < N) {

            sum += inputDays[start]; // 0 1 2
            start++;

            if (start > K) {
                sum -= inputDays[end];
                end++;

                if (sum > max_sum) {
                    max_sum = sum;
                }
            }
        }

        System.out.println(max_sum);
    }

    public static void main3(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputStr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int N = inputStr[0];
        int K = inputStr[1];

        int[] inputDays = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int start = 0;
        int end = 0;
        int max_sum = Integer.MIN_VALUE;
        int sum = 0;
        while (start < N) {

            sum += inputDays[start]; // 0 1 2 3 4
            start++;                 // 1 2 3 4 5

            if (start >= K) {
                if (start - end > K) {
                    sum -= inputDays[end];
                    end++;
                }
                if (sum > max_sum) {
                    max_sum = sum;
                }
            }
        }

        System.out.println(max_sum);
    }

    public static void main4(String[] args) throws IOException {

        System.out.println(Integer.MIN_VALUE);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputStr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int N = inputStr[0];
        int K = inputStr[1];

        int[] inputDays = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int start = 0;
        int end = 0;
        int max_sum = Integer.MIN_VALUE;
        int sum = 0;
        int count = 0;
        while (start < N) {

            sum += inputDays[start]; // 0 1 2 3 4
            start++;                 // 1 2 3 4 5
            count++;

            if (start >= K) {
                if (count > K) {
                    sum -= inputDays[end];
                    end++;
                    count--;
                }
                if (sum > max_sum) {
                    max_sum = sum;
                }
            }
        }

        System.out.println(max_sum);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i <= n; i++) arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken()); // 누적합

        int max = -10000001;

        for(int i = k; i <= n; i++){
            int rangeSum = arr[i] - arr[i - k];
            if(max < rangeSum) max = rangeSum;
        }

        System.out.println(max);
    }

    public static void another2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N, K, i, j, temp, sum;
        int[] arr;

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        temp = 0;
        sum = 0;

        arr = new int[N];

        st = new StringTokenizer(br.readLine());


        for(i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(i=0; i<K; i++) {
            sum += arr[i];
        }

        temp = sum;

        for(i=K; i<N; i++) {
            temp = temp + arr[i] - arr[i - K];

            if(temp > sum) {
                sum = temp;
            }
        }

        bw.write(sum + "");
        bw.flush();
        bw.close();
    }
}
