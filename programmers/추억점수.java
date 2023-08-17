import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class 추억점수 {

    public static void main(String[] args) {
        String[] name = {"may", "kein", "kain", "radi"};
        int[] yearning = {5, 10, 1, 3};
        String[][] photo = {
                {"may", "kein", "kain", "radi"},
                {"may", "kein", "brin", "deny"},
                {"kon", "kain", "may", "coni"}
        };
        int[] result = solution2(name, yearning, photo);
        System.out.println(Arrays.toString(result));
    }

    public static int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];

        Map<String, Integer> hashmap=new HashMap<>();

        for(int i=0; i<name.length; i++){
            hashmap.put(name[i], yearning[i]); //int에 integer가능? 에러가능성

        }

        for(int n=0; n<photo.length; n++){

            answer[n]=0;
            for(int m=0; m<photo[n].length; m++){
                if(hashmap.containsKey(photo[n][m])){
                    answer[n]+=hashmap.get(photo[n][m]);

                }

            }
        }

        return answer;
    }

    //스트림으로 푸는 방법 연구..
    public static int[] solution2(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];

        Map<String, Integer> hashmap = IntStream.range(0, name.length)
                .boxed()
                .collect(Collectors.toMap(
                        i->name[i], i->yearning[i]
                ));

        Iterator<Map.Entry<String,Integer>> iterator=hashmap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String,Integer> el=iterator.next();
            System.out.println(el.toString());
        }

        for(int n=0; n<photo.length; n++){

            answer[n]=0;
            for(int m=0; m<photo[n].length; m++){
                answer[n] += hashmap.getOrDefault(photo[n][m], 0);

            }
        }

        return answer;
    }
}

