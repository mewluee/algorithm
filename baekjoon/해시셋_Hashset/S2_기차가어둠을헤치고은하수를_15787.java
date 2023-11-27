package 해시셋_Hashset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class S2_기차가어둠을헤치고은하수를_15787 {

    //문제: 실버2 기차가 어둠을 헤치고 은하수를
    //요약:
    //은하수를 지나는 기차 모임
    //한 기차에는 20개의 좌석
    //명령 수행 후 은하수 지날 예정
    //명령 종류
    //1 i x -> i기차, x좌석, 승차, 이미 있으면 x
    //2 i x -> i기차, x좌석, 하차, 이미 없으면 x
    //3 i -> i기차, 모든 승객 좌석 한칸씩 뒤로, 20번째 사람 하차
    //4 i -> i기차, 모든 승객 좌석 한칸씩 앞으로, 1번째 사람 하차
    //은하수를 지날때마다 현재 좌석 정보를 저장
    //k번째 기차가 지나갈때 0~k-1번째 기차의 좌석정보와 같을 경우 지나가지 못함

    //풀이:구현, hashset

    static int N;
    static int M;
    static Train[] trains;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); //기차의 수
        M = Integer.parseInt(st.nextToken()); //명령의 수

        trains = new Train[N + 1];
        for (int n = 0; n <= N; n++) {
            trains[n] = new Train();
        }
        HashSet<Train> hashset = new HashSet<>();

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine(), " ");
            int command = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken());
            switch (command) {
                case 1:
                    int x1 = Integer.parseInt(st.nextToken());
                    trains[i].getOnTheTrain(x1);
                    break;
                case 2:
                    int x2 = Integer.parseInt(st.nextToken());
                    trains[i].getOffTheTrain(x2);
                    break;
                case 3:
                    trains[i].moveSeatBack();
                    break;
                case 4:
                    trains[i].moveSeatForward();
                    break;
            }
            //print();
            //System.out.println("==================");
        }

        int count = N;
        for (int n = 1; n <= N; n++) {
            if (hashset.contains(trains[n])) {
                count--;
                continue;
            }
            hashset.add(trains[n]);
        }
        System.out.println(count);
    }

    static class Train {
        boolean[] seats;

        Train() {
            seats = new boolean[21];
        }

        void getOnTheTrain(int x) {
            seats[x] = true;
        }

        void getOffTheTrain(int x) {
            seats[x] = false;
        }

        void moveSeatBack() {
            for (int x = 20; x >= 2; x--) {
                seats[x] = seats[x - 1];
            }
            seats[1] = false;
        }

        void moveSeatForward() {
            for (int x = 1; x <= 19; x++) {
                seats[x] = seats[x + 1];
            }
            seats[20] = false;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + Arrays.hashCode(seats);
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            Train other = (Train) obj;
            for (int x = 1; x <= 20; x++) {
                if (this.seats[x] != other.seats[x]) return false;
            }
            return true;
        }

    }

    static void print() {
        for (int i = 1; i <= N; i++) {
            System.out.println(i + "기차 ");
            for (int x = 0; x <= 20; x++) {
                if (trains[i].seats[x]) System.out.print("O ");
                else System.out.print("X ");
            }
            System.out.println();
        }
    }
}
