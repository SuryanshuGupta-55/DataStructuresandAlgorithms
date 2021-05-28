package DP;

import java.io.*;
import java.nio.file.LinkPermission;
import java.util.Scanner;

class PerfectSumProblem {
    static int[][] dp;

    public static int perfectSum(int arr[],int n, int sum)
    {
        int mod = 1000000007;
        dp = new int[n+1][sum+1];
        for(int i = 0;i <= n;i++){
            dp[i][0] = 1;
        }
        for(int i = 1;i <= sum;i++){
            dp[0][i] = 0;
        }

        for(int i = 1;i <= n;i++){
            for(int j = 1;j <= sum;j++){

                if(j < arr[i - 1])
                    dp[i][j] = dp[i - 1][j];

                else{
                    dp[i][j] = ((dp[i - 1][j] % mod) + (dp[i - 1][j - arr[i - 1]] % mod)) % mod;
                }
            }
        }
        return dp[n][sum];
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int target = s.nextInt();
        int[] a = new int[n];
        for(int i = 0;i < n;i++){
            a[i] = s.nextInt();
        }
        System.out.println(perfectSum(a,n,target));
    }

    /*
    * Recursive approach
    * static int c = 0;
    * solve(int[] a,int n,int sum,int i){
    *   if(sum == 0){
    *       c++;
    *       return;
    *   }
    *   if(sum < 0 || i >= n)
    *       return;
    *   solve(a,n,sum,i+1);
    *   solve(a,n,sum - a[i],i+1);
    * }
    * */
}