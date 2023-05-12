package java_practice.java_algorithm_dp.knapsack;

import java.util.Scanner;

public class knapsack {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int itemCount=sc.nextInt();
        int possibleWeight = sc.nextInt();
        int[] weight=new int[itemCount+1];
        int[] value =new int[itemCount+1];
        int [][] napsack = new int [itemCount+1][possibleWeight+1];
        for (int i=0;i<itemCount;i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            weight[i]=a;
            value[i]=b;
        }
        for (int i=0;i<itemCount+1;i++){
            for (int j=0;j<possibleWeight+1;j++){
                if (i==0||j==0){
                    napsack[i][j]=0;
                }
                else {
                    napsack[i][j] = napsack[i - 1][j];
                    if (weight[i-1] <= j) {//내 현재애의 무게보다 크거나 같으면
                        napsack[i][j] = Math.max(napsack[i - 1][j], napsack[i - 1][j - weight[i-1]] + value[i-1]);
                    }
                }
            }
        }
        System.out.println(napsack[itemCount][possibleWeight]);


    }
}
