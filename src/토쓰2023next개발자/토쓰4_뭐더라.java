package 토쓰2023next개발자;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class 토쓰4_뭐더라 {
    public static void main(String[] args) {
        String[] actions={"1","3","2","B","4","F"};
        String[] actions2={"B","F"};
        String[] actions3={"1","2","B","F"};
        String[] answer=solution(3, actions3);
        System.out.println(Arrays.toString(answer));
    }

    static ArrayList<String> result;
    static Stack<String> backList;
    static Stack<String> frontList;
    static int inputMaxSize;

    static String[] solution(int maxSize, String[] actions) {
        result=new ArrayList<>();
        backList=new Stack<>();
        frontList=new Stack<>();
        inputMaxSize=maxSize;

        for (int i = 0; i < actions.length; i++) {

            if(actions[i].equals("B")){
                moveBack();
                continue;
            }
            if(actions[i].equals("F")){
                moveFront();
                continue;
            }
            movePage(actions[i]);
        }

        String[] answer={};
        if(result.size()>0){
            answer=new String[inputMaxSize];
            for (int i = 0; i < inputMaxSize; i++) {
                answer[i]=result.get(inputMaxSize-i-1);
            }
        }
        return answer;
    }

    static void movePage(String newPage){

        int index=result.indexOf(newPage);
        if(index>=0){ //중복된 값일 경우
            result.remove(index);//예전 걸 지우고 나서
        }

        if(result.size()>0){
            //추가하기 전에 최신 페이지를 백에 옮겨두고
            String currentPage=result.get(result.size()-1);
            backList.push(currentPage);
        }

        result.add(newPage);//추가하기

        frontList.clear();

        if(result.size()>inputMaxSize){
            result.remove(0);
        }

    }

    static void moveBack(){

        if(backList.size()==0) return;

        if(result.size()>0) {
            String currentPage = result.get(result.size() - 1);
            frontList.push(currentPage);
        }

        String backPage=backList.pop();

        int index=result.indexOf(backPage);
        if(index>=0){ //중복된 값일 경우
            result.remove(index);//예전 걸 지우고 나서
        }

        result.add(backPage);

        if(result.size()>inputMaxSize){
            result.remove(0);
        }

    }

    static void moveFront(){

        if(frontList.size()==0) return;
        if(result.size()>0) {
            String currentPage = result.get(result.size() - 1);
            backList.push(currentPage);
        }

        String frontPage=frontList.pop();

        int index=result.indexOf(frontPage);
        if(index>=0){ //중복된 값일 경우
            result.remove(index);//예전 걸 지우고 나서
        }

        result.add(frontPage);

        if(result.size()>inputMaxSize){
            result.remove(0);
        }

    }

}
