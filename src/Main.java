
import samsungMail.SamsungMail01;

import java.io.IOException;
import java.util.*;

public class Main {


    public static void main(String[] args) {
        SamsungMail01 ssm01=new SamsungMail01();
        try{
            ssm01.result2();
        }catch (IOException ioException){
            System.out.println("예외 발생 ㅠ");
        }


    }


}