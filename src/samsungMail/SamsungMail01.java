package samsungMail;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class SamsungMail01 {
    public void result() throws IOException {

        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        int T=Integer.parseInt(bf.readLine());
        int TC=T+1;


        while(T>0){
            String str=bf.readLine();
            StringTokenizer st=new StringTokenizer(str);
            int max=0;

            int N=Integer.parseInt(st.nextToken());
            if(N<10){
                bw.write(String.format("#%d %d\n",TC-T,-1));
                T--;
                continue;
            }
            char x=st.nextToken().charAt(0);
            char y=st.nextToken().charAt(0);


            for(int i=10; i<=N; i++) { //모든 경우의 수 검색 시작
                String s=Integer.toString(i); // i를 String타입으로 만들기. 몇 자리수인지 검사하기 위해서.
                boolean flag=true;
                for(int j=0; j<s.length(); j++){ //s의 각 자리수 검사하기
                    if(s.charAt(j)!=x && s.charAt(j)!=y){ // x도 y도 아니면
                        flag=false;
                        break; //검사할 필요 없음. for문 나가기
                    }
                }

                if(flag){
                    max=i;
                }
            }

            if(max==0){
                bw.write(String.format("#%d %d\n",TC-T,-1));
            }else{
                bw.write(String.format("#%d %d\n",TC-T,max));
            }

            T--;
        }

        bw.close();
    }

    public void result2() throws IOException{

        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        int T=Integer.parseInt(bf.readLine());
        int TC=T+1;


        while(T>0){
            String str=bf.readLine();
            StringTokenizer st=new StringTokenizer(str);
            int max=0;

            String N=st.nextToken();
            char n=N.charAt(0);

            char x=st.nextToken().charAt(0);
            char y=st.nextToken().charAt(0);
            //bw.write("N:"+N);

            // 3개의 분기
            // N의 length(자리수) = 1일 경우 / 2일 경우 / 3이상일 경우
            switch (N.length()){
                case 1 : // 자리수가 1일 경우
                    //bw.write("자리수가 1일 경우\n");
                    bw.write("#"+(TC-T)+" -1\n");
                    break;
                case 2: // 자리수가 2일 경우
                    //bw.write("자리수가 2일 경우\n");
                    ArrayList<Integer> result=new ArrayList<>();
                    for(int i=0; i<N.length(); i++){
                        if(N.charAt(i)<x){ // 1: n < x
                            result.add(0, -1);
                            break;

                        }else if(N.charAt(i)==x){ // 2: n == x
                            result.add(i,Integer.valueOf(x-'0'));

                        }else if(y<=N.charAt(i)){ // 4, 5: y <= n (1번에서 처리됨)
                            result.add(i,Integer.valueOf(y-'0'));

                        }else{  // 3: x < n < y
                            result.add(i,Integer.valueOf(x-'0'));
                            if(x=='0') {
                                result.clear();
                                result.add(0, -1);
                            }
                            break;
                        }
                    }
                    String printResult="";
                    for(int abc:result){
                        printResult=printResult+Integer.toString(abc);
                    }

                    bw.write("#"+(TC-T)+" "+printResult+"\n");
                    break;

                default: // 자리수가 3이상일 경우
                    //bw.write("자리수가 3일 경우\n");
                    ArrayList<Integer> result2=new ArrayList<>();

                    // 또 분기.
                    // 1: n < x
                    // 2: n == x
                    // 3: x < n < y
                    // 4: y == n
                    // 5: y < n
                    for(int i=0; i<N.length(); i++){

                        if(N.charAt(i)<x){ // 1: n < x (2가지 또 분기)

                            if(i==0){ //맨 처음값일 때에는 바로 자리수 감소시키고 y로 채움

                                result2.clear();
                                for(int j=0; j<N.length()-1; j++){
                                    result2.add(j,Integer.valueOf(y-'0')); // 자리수 감소하고 y로 다 채움
                                }
                                break;

                            }else{ // 중간값부터 여기에서 분기됨.
                                for(int j=i-1; j>=0; j--){ // 그 전 값에 y값이 있으면 y값만 x값으로 바꾸고 나머지 y로 할당
                                    if(result2.get(j)==Integer.valueOf(y-'0')){
                                        //bw.write("**j:"+j+" "+result2.get(j)+"\n");
                                        result2.set(j,Integer.valueOf(x-'0'));
                                        //bw.write("**j22:"+j+" "+result2.get(j)+"\n");
                                        for(int k=j+1; k<N.length(); k++){
                                            result2.add(k,Integer.valueOf(y-'0'));
                                        }
                                        break;

                                    }else if(j==0){ // 그 전 값에 y값 없으면 자리수 감소해야함.
                                        result2.clear();
                                        for(int k=0; k<N.length()-1; k++){
                                            result2.add(k,Integer.valueOf(y-'0')); // 자리수 감소하고 y로 다 채움
                                        }
                                        break;
                                    }
                                }
                                break;


                            }


                        }else if(y<N.charAt(i)){ // 5: y < n (값을 다운시킨 경우 그 다음 자리 숫자들은 모두 y가 될 수 있다.)

                            for(int j=i; j<N.length(); j++){
                                result2.add(j,Integer.valueOf(y-'0'));
                            }
                            break;

                        }else if(y==N.charAt(i)){ // 4: y == n
                            result2.add(i,Integer.valueOf(y-'0'));

                        }else if(x==N.charAt(i)){ // 2: n == x
                            result2.add(i,Integer.valueOf(x-'0'));

                        }else{ // 3: x < n < y (값을 다운시킨 경우 그 다음 자리 숫자들은 모두 y가 될 수 있다)
                            if(x=='0'){ // 다운시킨 값이 0일 경우.. 자리수를 빼야함

                                for(int j=0; j<N.length()-1; j++){
                                    result2.add(j,Integer.valueOf(y-'0')); // 자리수 감소하고 y로 다 채움
                                }
                                break;
                            }

                            result2.add(i,Integer.valueOf(x-'0'));
                            for(int j=i+1; j<N.length(); j++){
                                result2.add(j,Integer.valueOf(y-'0'));
                            }
                            break; // y로 채우고 반복문 바로 탈출.
                        }
                    }

                    String printResult2="";
                    for(int abc:result2){
                        printResult2=printResult2+Integer.toString(abc);
                    }

                    bw.write("#"+(TC-T)+" "+printResult2+"\n");
                    break;
            }

            T--;
        }

        bw.close();


    }

}
