import java.util.*;

public class 신고결과받기 {

    public static void main(String[] args) {

        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report={"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        int k=2;

        System.out.println(Arrays.toString(solution(id_list, report, k)));
    }

        public static int[] solution(String[] id_list, String[] report, int k) {
            int[] answer = new int[id_list.length];
            List<ReportInfo> infoList=new ArrayList<>();
            // 중요한건 result값이 본인이 신고한 사람이 k번 넘을때 증가하는 값이다.
            Map<String, Integer> reportedCnt=new HashMap<>();
            // 카운트 증가하는 거니까 괜히 HashMap쓰고 싶네.

            // 오 중복신고는 카운트 안해주네.
            // 하나의 클래스의 멤버필드로 본인 이름, 신고자 명단
            // 클래스말고, 배열일때, 하나의 string 배열에 두개 넣고 > 이거 너무 비효율적.
            // 클래스 만들자


            for(String id:id_list){
                infoList.add(new ReportInfo(id)); // 각 사용자마다 이름, 신고명단을 갖고있는 객체 만들기
                reportedCnt.put(id, 0); // 모든 사용자들이 각자 신고'된' 횟수를 담는 해쉬맵 0으로 만들기
            }

            // 신고 목록 조회하면서
            for(String rep:report){
                String[] repList=rep.split(" ");
                int index=getIndex(id_list,repList[0]); // 신고자 확인 -> 인덱스 출력
                ReportInfo info=infoList.get(index); // 신고자의 info(이름, 신고명단) 가져오기
                //repList[0] 신고자 / repList[1] 신고대상

                if(info.add(repList[1])){ // 신고명단에 신고대상 입력하기 -> 중복되면 false라서 카운트가 안된다.
                    reportedCnt.put(repList[1],reportedCnt.get(repList[1])+1);
                    //System.out.println("***"+repList[1]+":"+reportedCnt.get(repList[1]));
                }

            }

            //자, answer 입력해야하니까.
            for (ReportInfo info:infoList){
                int index=getIndex(id_list, info.getMyName()); // 신고자 확인 -> 인덱스 출력

                // 해당 신고자가 갖고있는 신고명단을 순회하기
                Iterator iterator=info.getReportedName().iterator();
                while (iterator.hasNext()) {
                    String name=(String)iterator.next();
                    // 신고대상이 k번 이상 신고된 대상인게 확인되면 answer 값 증가
                    if(reportedCnt.get(name)>=k){
                        //System.out.println("-----name:"+name+" "+reportedCnt.get(name));
                        if(index>=0) answer[index]+=1;
                    }
                }
            }


            return answer;

        }

        static class ReportInfo{

            private String myName;
            private HashSet<String> reportedName=new HashSet<>();

            /* 생성자 */
            ReportInfo(String myName){
                this.myName=myName;
            }

            /* 해쉬셋에 값 넣는 함수 - 반환값 boolean */
            public boolean add(String name){
                if(reportedName.add(name)){
                    return true;
                }
                System.out.println(this.toString());
                return false;
            }

            /* getter */
            public String getMyName() {
                return myName;
            }

            public HashSet<String> getReportedName() {
                return reportedName;
            }

            public String toStringHashSet(){
                String str="";
                Iterator iterator = reportedName.iterator();
                while (iterator.hasNext()) {
                    str=str+iterator.next();
                }
                return str;
            }

            @Override
            public String toString() {
                return "ReportInfo{" +
                        "myName='" + myName + '\'' +
                        ", reportedName=" + toStringHashSet() +
                        '}';
            }
        }

        /* 문자열 배열에서 특정 문자열을 찾아서 반환 - 찾는 문자열이 없으면 -1 반환 */
        public static int getIndex(String[] list, String key){

            for(int i=0; i<list.length; i++){
                if(list[i].equals(key)){
                    return i;
                }
            }

            return -1;
        }



}
