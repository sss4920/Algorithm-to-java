package Hw9_1;

import java.util.Scanner;

public class Main_201914009 {
    public static void main(String[] args){
        System.out.println("hw9_1:김수현");
        Scanner sc = new Scanner(System.in);
        int possibleWeight = sc.nextInt(); //가능한 무게
        int itemCount=sc.nextInt();   //총 물건의 개수
        int[] weight=new int[itemCount+1]; //무게 배열
        int[] value =new int[itemCount+1]; //가치 배열
        int [][] napsack = new int [itemCount+1][possibleWeight+1]; //무게와 아이템 개수로 이루어진 2차원 배열 만들기
        for (int i=0;i<itemCount;i++){ //물건 개수만큼 입력받기
            int a = sc.nextInt();
            int b = sc.nextInt();
            weight[i]=a;
            value[i]=b;
        }
        for (int i=0;i<itemCount+1;i++){ //이제 2차원 그래프를 for문으로 돌건데
            for (int j=0;j<possibleWeight+1;j++){
                if (i==0||j==0){ //일단 가로 세로 첫줄은 0으로 채운다.
                    napsack[i][j]=0;
                }
                else { //가로 세로 첫줄이 0이 아니면
                    napsack[i][j] = napsack[i - 1][j]; // 내가 현재 보고있는 물건의 무게보다 작으면 넣을 수 없으니 그냥 바로 윗칸 넣기
                    if (weight[i-1] <= j) {// 근데 내가 현재 보고있는 물건의 무게보다 크거나 같다면
                        napsack[i][j] = Math.max(napsack[i - 1][j], napsack[i - 1][j - weight[i-1]] + value[i-1]); // 내 물건 넣기전의 최대 가치와 넣었을 때 최대가치를 비교해서 큰거 고름
                    }
                }
            }
        }
        System.out.println("최대가치 = "+napsack[itemCount][possibleWeight]); //마지막 인덱스에 내 최대가치가 위치.


    }
}
