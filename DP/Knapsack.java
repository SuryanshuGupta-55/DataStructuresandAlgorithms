package DP;

import java.io.*;
import java.util.Arrays;

class Knapsack {
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

    static int[][] dp;
    public static void main(String args[]) throws IOException {
        int n = inputInt();
        int[] mass = new int[n];
        for(int i = 0;i < n;i++){
            mass[i] = inputInt();
        }
        int[] val = new int[n];
        for(int i = 0;i < n;i++){
            val[i] = inputInt();
        }
        int target = inputInt();
        dp = new int[n+1][target + 1];
        for(int i = 0;i < n+1;i++){
            for(int j = 0;j < target+1;j++){
                dp[i][j] =  -1;
            }
        }
        println(profit(mass,val,target,n)+"");
        view(dp);
        bw.flush();
        bw.close();
    }
    //Recursive code - O(2^n) time complexity.
   /* public static int profit(int[] mass,int[] val,int target,int n){
        if(n == 0 || target == 0)
            return 0;
        if(mass[n-1] <= target){
            return Math.max(val[n-1] + profit(mass,val,target - mass[n-1],n-1),profit(mass,val,target,n-1));
        }
        else
            return profit(mass,val,target,n-1);
    }*/

    //Memoize code - O(n^2),space - O(n*m);
    public static int profit(int[] mass,int[] val,int target,int n){
        if(n == 0 || target == 0)
            return 0;
        if(dp[n][target] != -1)
            return dp[n][target];
        if(mass[n-1] <= target){
            return dp[n][target] = Math.max(val[n-1] + profit(mass,val,target - mass[n-1],n-1),profit(mass,val,target,n-1));
        }
        else
            return dp[n][target] = profit(mass,val,target,n-1);
    }
    public static void view(int[][] a){
        for(int i = 0;i < a.length;i++){
            System.out.println(Arrays.toString(a[i]));
        }
    }

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