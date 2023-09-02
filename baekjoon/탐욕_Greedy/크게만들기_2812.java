package 탐욕_Greedy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class 크게만들기_2812 {
    //k번 검사
    //인덱스 0번부터 n번까지, 오른쪽 수랑 비교해서 작을 경우 해당 인덱스를 제거한다.
    public static void main2(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        String input = br.readLine();

        List<Integer> num = new ArrayList<>();


        for (int n = 0; n < N; n++) {
            char ch = input.charAt(n);
            int number = ch - '0';
            num.add(number);
        }

        //System.out.println("전:"+num);

        for (int k = 0; k < K; k++) {
            int index = -1;
            for (int n = 0; n < num.size() - 1; n++) {
                int a = num.get(n);
                int b = num.get(n + 1);
                //System.out.println("a:"+a+", b:"+b);
                if (a >= b) {
                    if (n == num.size() - 2) {
                        index = n + 1;
                        break;
                    } else {
                        continue;
                    }
                }
                index = n;
                break;
            }
            num.remove(index);
            //System.out.println(num);
        }

        for (int u = 0; u < num.size(); u++) {
            bw.append(String.valueOf(num.get(u)));
        }
        bw.flush();
        bw.close();
    }

    public static void main3(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String input = br.readLine();
        List<Integer> num = new ArrayList<>();

        for (int n = 0; n < N; n++) {
            num.add(input.charAt(n) - '0');
        }

        Stack<Integer> stack = new Stack<>();
        while (K > 0) {
            stack.push(num.get(0));
            boolean flag = false;
            for (int n = 1; n < num.size(); n++) {
                int num_now = num.get(n);
                if (K <= 0) {
                    stack.push(num_now);
                } else {
                    int stack_now = stack.peek();
                    if (!flag && stack_now < num_now) {
                        stack.pop();
                        flag = true;
                        K--;
                    }
                    if (!flag && n == num.size() - 1) {
                        K--;
                        continue;
                    }
                    stack.push(num_now);
                }
            }
            if (K == 0) break;
            num = new ArrayList<>(stack);
            stack.clear();
        }

        for (int u = 0; u < stack.size(); u++) {
            bw.append(String.valueOf(stack.get(u)));
        }
        bw.flush();
        bw.close();
    }

    public static void main4(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String input = br.readLine();
        List<Integer> num = new ArrayList<>();

        for (int n = 0; n < N; n++) {
            num.add(input.charAt(n) - '0');
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(num.get(0));
        boolean flag = false;
        for (int n = 1; n < num.size(); n++) {
            //System.out.println("n:"+n);
            int num_now = num.get(n);
            if (K <= 0) {
                stack.push(num_now);
            } else {
                int stack_now = stack.peek();
                //System.out.println("sn:"+stack_now+" nn:"+num_now);
                while (K > 0 && stack_now < num_now) {
                    stack.pop();
                    flag = true;
                    K--;
                    if (!stack.isEmpty()) stack_now = stack.peek();
                    else break;
                }
                if (!flag && n == num.size() - 1) {
                    K--;
                    continue;
                }
                stack.push(num_now);
            }
            System.out.println(stack);
        }
        System.out.println("end:" + stack);
        for (int u = 0; u < stack.size(); u++) {
            bw.append(String.valueOf(stack.get(u)));
        }
        bw.flush();
        bw.close();
    }

    //맞는 답
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        char[] input = br.readLine().toCharArray();

        Stack<Character> stack = new Stack<>();
        stack.push(input[0]);
        for (int n = 1; n < N; n++) {
            //System.out.println("n:"+n+", K:"+K);
            char num_now = input[n];
            if (K <= 0) {
                stack.push(num_now);
            } else {
                char stack_now = stack.peek();
                //System.out.println("sn:"+stack_now+" nn:"+num_now);
                while (K > 0 && stack_now < num_now) {
                    stack.pop();
                    K--;
                    if (!stack.isEmpty()) stack_now = stack.peek();
                    else break;
                }
//                if (!flag && n == N - 1) {
//                    System.out.println("yeah1");
//                    K--;
//                    continue;
//                }
                if (n == N - 1) {
                    stack.push(num_now);
                    //System.out.println("yeah2");
                    while (K > 0) {
                        stack.pop();
                        K--;
                    }
                    break;
                }
                stack.push(num_now);
            }
            //System.out.println(stack);
        }
        //System.out.println("end:"+stack);
        for (int u = 0; u < stack.size(); u++) {
            bw.append(String.valueOf(stack.get(u)));
        }
        bw.flush();
        bw.close();
    }
}
