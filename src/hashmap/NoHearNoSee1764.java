package hashmap;

import java.util.*;

public class NoHearNoSee1764 {

    public void result(){


        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int m=input.nextInt();
        input.nextLine();

        HashSet<String> set=new HashSet<String>();
        ArrayList<String> arr=new ArrayList<String>();

        while(n>0){
            set.add(input.nextLine());
            n--;
        }

        while (m > 0) {
            String str=input.nextLine();
            if(!set.add(str)){
                arr.add(str);
            }
            m--;
        }

        Collections.sort(arr);

        System.out.println(arr.size());

        Iterator iterator=arr.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }


    }
}
