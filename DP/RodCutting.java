package DP;

import java.io.*;
/*Problem Link
 * https://practice.geeksforgeeks.org/problems/rod-cutting0840/1
 * The problem is based on unbounded knapsack tried using both recursion and dp.*/
class RodCutting {
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length 
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }
            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }

    static Reader sc = new Reader();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String args[]) throws IOException {
        int n = inputInt();
        int[] a = new int[n];
        for(int i= 0;i < n;i++){
            a[i] = inputInt();
        }

        bw.flush();
        bw.close();
    }
    

    /*
    Recursive Solution.
    public static int solve(int[] a,int n,int i){
        if(n <= 0 || i <= 0)
            return 0;
        if(n >= i){
            return Math.max(a[i-1] + solve(a,n - i,i),solve(a,n,i-1));
        }
        else
            return solve(a,n,i-1);
    }*/



    /*
    Memoized Solution submitted on gfg.
    class Solution{
    static int[][] dp;
    public int cutRod(int price[], int n) {

        dp = new int[n+1][n+1];
        for(int i = 0;i <= n;i++){
            for(int j = 0;j <= n;j++){
                dp[i][j] = -1;
            }
        }
        return solve(price,n,n);
    }

    public static int solve(int[] a,int n,int i){
        if(n <= 0 || i <= 0)
            return 0;

        if(dp[n][i] != -1)
            return dp[n][i];

        if(n >= i){
            return dp[n][i] = Math.max(a[i-1] + solve(a,n - i,i),solve(a,n,i-1));
        }
        else
            return dp[n][i] = solve(a,n,i-1);
        }
    }*/

    public static int inputInt() throws IOException {
        return sc.nextInt();
    }

    public static long inputLong() throws IOException {
        return sc.nextLong();
    }

    public static double inputDouble() throws IOException {
        return sc.nextDouble();
    }

    public static String inputString() throws IOException {
        return sc.readLine();
    }

    public static void print(String a) throws IOException {
        bw.write(a);
    }

    public static void printSp(String a) throws IOException {
        bw.write(a + " ");
    }

    public static void println(String a) throws IOException {
        bw.write(a + "\n");
    }
}