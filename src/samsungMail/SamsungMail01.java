package samsungMail;

import java.io.*;
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
            /*if(Integer.parseInt(N)<10){
                bw.write(String.format("#%d %d\n",TC-T,-1));
                T--;
                continue;
            }*/
            char n=N.charAt(0);

            char x=st.nextToken().charAt(0);
            char y=st.nextToken().charAt(0);

            // 3개의 분기
            // N의 length = 1일 경우 / 2일 경우 / 3이상일 경우
            switch (N.length()){
                case 1 : // 1일 경우
                    bw.write(String.format("#%d %d\n",TC-T,-1));
                    T--;
                    break;
                case 2: // 2일 경우

                    break;
                default: // 3이상일 경우
                    char[] arrStr=new char[N.length()];
                    for(int i=0; i<N.length(); i++){
                        if(N.charAt(i)<x){ //1
                            arrStr[i]='z';
                        }else if(y<=N.charAt(i)){ //3
                            arrStr[i]=y;
                        }else{ //2
                            arrStr[i]=x;
                        }
                    }
            }

            T--;
        }

        bw.close();


    }

    public String result3(){




        return "abc";
    }
}
