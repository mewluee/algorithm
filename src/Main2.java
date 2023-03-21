
import java.io.IOException;
import java.util.*;

public class Main2 {

    public static void main(String[] args) throws IOException {

        System.out.println("경우1:"+solution("100-200*300-500+20"));
        System.out.println("경우2:"+solution("50*6-3*2"));
        System.out.println("경우3:"+solution("100-200*300*400"));


    }
    //연산자의 우선순위 자유롭게 재정의 > 가장 큰 숫자 만들기
    //동일한 순위는 안된다.
    //음수값 > 절대값 > 제일 큰 수
    //입력값은 3~100 문자열
    //최소 피연산자+연산자+피연산자
    //올바른 중위표기법

    //피연산자 0~999
    //음수 X 양수만
    //최종 결과값의 절대값은 2^63-1 (long타입~)

    //연산문자는 3가지 + - *
    //결국 6가지 조합

    //브루트포스?
    //어떤 자료구조를 써야 시간이 단축될까? 사실 입력값은 중위표기법이지만 스택으로 하면 후위표기법으로 컴퓨터가 계산하기 쉽지 않나?
    //연산자체에 시간이 오래걸리진 않잖아.
    //스택으로 녹여낼수있느냐?
    //가능한가..?
    //일단해봐 ㅇㅇ

    //일단 어제 눈대중으로 봐버린...
    static String[] operationPrioritys=new String[]{
      "+-*",
      "+*-",
      "*+-",
      "*-+",
      "-*+",
      "-+*"
    };
    public static long solution(String expression) {
        long answer = 0;

        //들어온 문자열을 잘라보자
        List<String> expressionList=getExpressionList(expression);
        answer=getAnswer(expressionList);
        //잘랐죠? 그럼 뭘해야겠어요!?
        //스택으로 구현해보자구용!!!!!!



        return answer;
    }


    //리스트? 배열? 아~ 리스트쓰자
    public static List<String> getExpressionList(String expression) {

        char[] chars = expression.toCharArray();
        //48(0) ~ 57(9)
        List<String> lists=new ArrayList<>();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {
            if (48 <= (int) chars[i] && (int) chars[i] <= 57) {
                sb.append(chars[i]);
            }else{
                //System.out.println("누적된 sb:"+sb.toString());
                lists.add(sb.toString());
                //리스트에 저장했으면 스트링빌더에서 없애버리고~
                sb.delete(0, sb.length());
                //연산자도 저장하자
                lists.add(Character.toString(chars[i]));

            }
        }
        lists.add(sb.toString());

        //System.out.println(lists.toString());
        return lists;

    }

    public static long getAnswer(List<String> expressionList){

        //중위 연산자니까 스택 두개로 해야할듯.

        long answer=Long.MIN_VALUE;
        System.out.println("min_value:"+answer);

        for (int j = 0; j < operationPrioritys.length; j++) {
            System.out.println("=".repeat(40));

            Stack<String> operations = new Stack<>(); //연산자(문자)
            Stack<Long> operands = new Stack<>(); //피연산자(숫자)
            String priority=operationPrioritys[j];
            System.out.println("pr:"+priority);

            for (int i = 0; i < expressionList.size(); i++) {
                try {
                    long number = Long.parseLong(expressionList.get(i));
                    operands.push(number);

                } catch (NumberFormatException ex) {
                    //예외발생 > 연산자
                    //System.out.println("number format exception");

                    if (!operations.isEmpty()) {
                        String topOperation = operations.peek(); //스택에 있는 연산자
                        String presentOperation = expressionList.get(i); //현재 연산자


                        //우선순위 비교하려고 인덱스값 가져온다. (매개변수:우선순위순열, 현재 연산자)
                        int presentIndex=getIndex(priority, presentOperation);
                        int topIndex=getIndex(priority, topOperation);

                        System.out.println("index p, t:"+presentIndex+", "+topIndex);

                        //현재 넣어야하는 연산자가 스택에 있는 연산자보다 우선순위가 높으면(인덱스값이 더 작으면)
                        if (presentIndex<topIndex) {
                            //피연산자에서 pop한 숫자랑 다음 인덱스의 숫자를 해당 연산자로 계산해서
                            //결과값을 피연산자에 넣고 해당 연산자는 넣지 않는다.
                            operands.push(calculate(presentOperation,operands.pop(),Long.parseLong(expressionList.get(++i))));
                        } else{
                            //현재 넣어야하는 연산자가 스택에 있는 연산자보다 우선순위가 동일하거나 우선순위가 낮으면(인덱스값이 더 크면)
                            //밑에 연산자로 계산해야한다. 피연산자 두번 pop하구, 연산자도 pop하나해서 계산해서 새로 넣는다.
                            long num2=operands.pop();
                            long num1=operands.pop();
                            operands.push(calculate(operations.pop(),num1, num2));
                            operations.push(expressionList.get(i));
                        }

                    } /*else { //텅 비어있을때
                        if(getIndex(priority, expressionList.get(i))==0){
                            //제일 첫번째 우선순위면 계산해서 넣도록 해용 ^^
                            operands.push(calculate(expressionList.get(i), operands.get(0), Long.parseLong(expressionList.get(++i))));
                        }
                        operations.push(expressionList.get(i));
                    }*/


                }

                System.out.println(Arrays.toString(operations.toArray()));
                System.out.println(Arrays.toString(operands.toArray()));

                System.out.println("-".repeat(20));
            }

            //스택에 남은 연산자들 처리하기. 이부분이 가능성이 있나...?
            while(!operations.isEmpty()){

                long num2=operands.pop();
                long num1=operands.pop();
                operands.push(calculate(operations.pop(), num1, num2));
            }

            System.out.println(operands.peek());
            long result = Math.abs(operands.pop());
            System.out.println("re:"+result);
            if (result > answer) {
                answer=result;
            }
            System.out.println("ans:"+answer);

        }

        return answer;
    }

    public static int getIndex(String priority, String operation){
        char[] arrs=priority.toCharArray();
        for (int i = 0; i < arrs.length; i++) {

            if (arrs[i] == Character.valueOf(operation.charAt(0))) {
                return i;
            }

        }

        return -1;
    }

    public static long calculate(String operation, long number1, long number2) {
        switch (operation) {
            case "+":
                return number1+number2;
            case "-":
                return number1-number2;
            case "*":
                return number1*number2;
            default:
                return -1;
        }
    }
}
