package programmers;

import java.util.Arrays;
import java.util.stream.IntStream;

public class 공원산책 {

    public static void main(String[] args){

        String[] park = {"SOO", "OOO", "OOO"};
        String[] routes = {"E 2", "S 2", "W 1"};

        int[] re = solution(park, routes);
        System.out.println(Arrays.toString(re));

    }

    public static int[] solution(String[] park, String[] routes) {
        int[] answer = new int[2];

        for(int i=0; i<park.length; i++){
            char[] chars=park[i].toCharArray();
            for(int j=0; j<chars.length; j++){
                if(chars[j]=='S'){
                    answer[0]=i;
                    answer[1]=j;
                }
            }
        }

        for(int n=0; n<routes.length; n++){
            System.out.println("route:"+n);
            String[] strs=routes[n].split(" ");

            //장애물에 막혀 되돌아올 경우 기존값을 보존하기 위해서
            int[] rollback=new int[2];
            rollback[0]=answer[0];
            rollback[1]=answer[1];

            boolean doRollback=false;

            for(int m=0; m<Integer.parseInt(strs[1]); m++){
                int[] go= IntStream.range(0,2)
                        .map(x->answer[x]+getDirection(strs[0])[x])
                        .toArray();
                // 여기 사실 단순해서 스트림안써도 되긴해................. 바보야 정말...
                System.out.println("answer:"+Arrays.toString(answer));
                System.out.println("go:"+Arrays.toString(go));
                if(0<=go[0] && go[0]<park.length
                        && 0<=go[1] && go[1]<park[0].length()
                        && ( park[go[0]].charAt(go[1])=='O' || park[go[0]].charAt(go[1])=='S')){
                    //answer의 값 갱신

                    answer[0]=go[0];
                    answer[1]=go[1];
                }else{
                    System.out.println("rollback해요");
                    doRollback=true;
                    break; //반복문 종료
                }
            }

            if(doRollback){
                answer[0]=rollback[0];
                answer[1]=rollback[1];
            }

        }

        System.out.println(Arrays.toString(answer));


        return answer;
    }

    public static int[] getDirection(String str){
        switch(str){
            case "E":
                return new int[]{0, 1};
            case "W":
                return new int[]{0, -1};
            case "S":
                return new int[]{1, 0};
            case "N":
                return new int[]{-1, 0};
            default:
                return new int[]{0, 0};
        }
    }
}
