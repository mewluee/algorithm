package hashing;

import java.io.*;
import java.math.BigInteger;

public class Hashing15829 {

    public void result() throws IOException {

        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        int input=Integer.parseInt(bf.readLine());
        String str=bf.readLine();
        BigInteger result=new BigInteger("0");

        for(int i=0; i<str.length(); i++){
            //System.out.println(str[i]-'0'-48);
            BigInteger one=new BigInteger(Integer.toString(str.charAt(i)-'0'-48));
            BigInteger r=new BigInteger("31");
            r=r.pow(i);
            one=one.multiply(r);
            result=result.add(one);
        }

        bw.write(result.toString());
        bw.close();
    }

}
