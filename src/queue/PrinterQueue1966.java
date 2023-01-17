package queue;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class PrinterQueue1966 {

    private String site="https://www.acmicpc.net/problem/1966";

    public void result() throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase=Integer.parseInt(br.readLine());


        //테스트케이스 입력값
        //1 0 (1개의 데이터, 0번째 있는 데이터의 출력 순서 알려달라)
        //5 (문서 중요도)


        while(testCase>0) {
            //bw.write("\nwhile문 실행\n");
            String[] strarr = br.readLine().split(" ");
            int N = Integer.parseInt(strarr[0]);
            int M = Integer.parseInt(strarr[1]);
            //int getCount=Integer.parseInt(strarr[M]);

            Queue<Integer> queue = new LinkedList<Integer>();

            String[] strs = br.readLine().split(" "); // 중요도 받아옴.
            for (int i = 0; i < N; i++) {
                queue.add(Integer.parseInt(strs[i])); // 큐에 차근차근 중요도 추가함.
            }
            //bw.write(Arrays.toString(queue.toArray())+"\n");
            //System.out.println("####"+Arrays.toString(queue.toArray()));
            int count = 0;
            int index = 0;
            while (!queue.isEmpty()) {
                //늘 맨앞을 기준으로 맨앞보다 작으면 뒤로 보낸다?
                //그런데말이죵 4 1 2 5 면 1일때 2출력해버리면 5가 제일 중요도가 크니까 이런 알고리즘은 안됨.음..
                //max값을 기준으로 음..LinkedList를 스트림으로 보내서 max값 뽑기 가능.

                //일단 제일 앞에있는 문서를 pop해서 뽑고
                //나머지 큐에서 max값 출력함.
                //max값과 문서의 중요도를 비교해서 max값보다 작으면 인쇄안하고 다시 큐에 입력함.

                //count를 도대체 어떻게 세야하지..?
                //arraylist에다가 0번 인덱스였던게 뒤로가면서 인덱스값이 변하는데
                //그 인덱스를 추적하는 방법.........

                //index를 기준으로
                int num = queue.poll(); //제일 왼쪽값(출력) 뽑아옴.
                //stream으로 max값 뽑는 방법 찾아보기 ㅠ
//                List<Integer> list=new ArrayList<Integer>((LinkedList<Integer>)queue);
//                int max=list.stream().mapToInt(x-> (int) x).max().getAsInt();
                int max = 0;
                for (Integer i : queue) {
                    if (max < i) max = i;
                }
                //int max=Collections.max(queue.to);
                //bw.write("max:" + max+"\n");

                if (num < max) { //중요도가 높은 문서가 뒤에 있으니까.
                    queue.add(num); //다시 큐에 들어가~
                    if(index==M){// 뒤로가는 값이 내가 찾는 index값이면 index랑 M의 값이 바껴야함.
                        M=queue.size(); // (제일 뒤로가니까)
                        index=0; // 다시 해당 M까지 새야함.
                    }
                } else {
                    //num>=max일 경우
                    count++;
                    //poll은 위에서 했으니까 따로 처리할 필요 X
                    if (index == M) { // 출력했는데 해당 index값이 내가 찾아야하는 index값인 경우
                        bw.write(count + "\n");
                    }
                }
                //뒤로가면..?
                index++;
                // 1(*) 1 9 1    1    1     뒤로 입력             0    0   (M) (index)
                // 1    9 1 1    1    1(*)  뒤로 입력             5    1
                // 9    1 1 1    1(*) 1     여기서는 출력됨.       5    2
                // 1    1 1 1(*) 1          여기서부터 또 출력     5     3

            }

            testCase--;
        }



        bw.close();
    }


}
