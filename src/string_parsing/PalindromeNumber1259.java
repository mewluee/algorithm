package string_parsing;

import java.util.Scanner;

public class PalindromeNumber1259 {
    public void result(){
        Scanner input=new Scanner(System.in);
        boolean flag=true;
        String str=input.nextLine();

        do{
            flag=true;

            for(int i=0; i<str.length()/2;++i){
                if(str.charAt(i)!=str.charAt(str.length()-1-i)){
                    flag=false;
                }
            }
            System.out.println(flag? "yes":"no");

            str=input.nextLine();

        }while(!str.equals("0"));

    }
}
