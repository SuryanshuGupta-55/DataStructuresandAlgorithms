package DP;

import java.util.*;
import java.lang.*;
import java.io.*;
class LongestRepeatingSubsequence
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while(T-->0)
        {
            String str = br.readLine().trim();
            Solution ob = new Solution();
            int ans = ob.LongestRepeatingSubsequence(str);
            System.out.println(ans);
        }
    }
}

class Solution
{
    int[][] dp;
    public int LongestRepeatingSubsequence(String str)
    {
        dp = new int[str.length()+1][str.length()+1];

        for(int i = 0;i < dp.length;i++){
            Arrays.fill(dp[i],-1);
        }
        return LCS(str,str,str.length(),str.length());
    }

    public int LCS(String x,String y,int n,int m){
        if(n == 0 || m == 0)
            return 0;

        if(dp[n][m] != -1)
            return dp[n][m];

        if(x.charAt(n-1) == y.charAt(m-1) && n != m){
            return dp[n][m] = 1 + LCS(x,y,n-1,m-1);
        }
        else{
            return dp[n][m] = Math.max(LCS(x,y,n-1,m),LCS(x,y,n,m-1));
        }
    }
}