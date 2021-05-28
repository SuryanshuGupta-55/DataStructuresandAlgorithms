package DP;

import java.io.*;
import java.util.Arrays;

class MaximumSumPartition {
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

    static boolean[][] dp;
    public static void main(String args[]) throws IOException {
        int n = inputInt();
        int[] a = new int[n];
        int total = 0;
        for(int i = 0;i < n;i++){
            a[i] = inputInt();
            total += a[i];
        }
        dp =  new boolean[n + 1][total + 1];
        for(int i = 0;i <= n;i++){
            dp[i][0] = true;
        }
        for(int i = 1;i <= total;i++){
            dp[0][i] = false;
        }
        for(int i =1;i <= n;i++){
            for(int j = 1;j <= total;j++){
                if(j < a[i-1]){
                    dp[i][j] = dp[i-1][j];
                }
                else{
                    dp[i][j] = dp[i-1][j] || dp[i-1][j - a[i - 1]];
                }
            }
        }
        int diff = Integer.MAX_VALUE;
        for(int i=  total/2;i >= 0;i--){
            if(dp[n][i]){
                diff = Math.min(diff,total - (2*i));
            }
        }
        println(diff+"");
        bw.flush();
        bw.close();
    }
    public static void view(int[][] a){
        for(int i = 0;i < a.length;i++){
            System.out.println(Arrays.toString(a[i]));
        }
    }
    //Memoization.
    /*public static int solve(int[] a,int sum,int total,int i,int[][] dp){
        if(i == a.length - 1){
            if(sum != total)
                return dp[i+1][sum] = Math.abs(sum - (total - sum));
            else
                return dp[i+1][sum] = Integer.MAX_VALUE;
        }
        if(dp[i+1][sum] != Integer.MAX_VALUE)
            return dp[i+1][sum];

        return dp[i+1][sum] = Math.min(solve(a,sum,total,i+1,dp),solve(a,sum + a[i],total,i+1,dp));
    }*/
    //Recursive Solution.
    /* public static int solve(int[] a,int sum,int total,int i){
        if(i >= a.length){
            if(sum != total)
                return Math.abs(sum - (total - sum));
            else
                return Integer.MAX_VALUE;
        }

        return Math.min(solve(a,sum,total,i+1),solve(a,sum + a[i],total,i+1));
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