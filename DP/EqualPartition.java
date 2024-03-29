package DP;

import java.io.*;

class EqualPartition {
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

    static int c;
    public static void main(String args[]) throws IOException {
        int n = inputInt();
        int[] a = new int[n];
        int sum = 0 ;
        for(int i = 0;i < n;i++){
            a[i] = inputInt();
            sum += a[i];
        }
        if(sum%2 != 0)
            println("False");
        println(solve(a,sum/2,0,0)+"");
        bw.flush();
        bw.close();
    }

    public static boolean solve(int[] a,int target,int sum,int i) {
        if (sum == target)
            return true;
        if (sum > target || i >= a.length)
            return false;
        boolean first = solve(a, target, sum, i + 1);
        boolean second = solve(a, target, sum + a[i], i + 1);
        return (first || second);
    }
    //DP solution.
    /*static boolean[][] dp;
    static int equalPartition(int N, int arr[])
    {
        int sum = 0;
        for(int i:arr){
            sum += i;
        }
        if(sum % 2 != 0)
            return 0;
        else{
            dp = new boolean[N+1][sum/2 + 1];
            for(int i = 0;i < N+1;i++){
                dp[i][0] = true;
            }
            for(int i = 1;i <= sum/2;i++){
                dp[0][i] = false;
            }
            for(int i = 1;i <= N;i++){
                for(int j = 1;j <= sum/2;j++){
                    if(j < arr[i-1])
                        dp[i][j] = dp[i-1][j];
                    else
                        dp[i][j] = dp[i-1][j] || dp[i-1][j - arr[i - 1]];
                }
            }
            if(dp[N][sum/2])
                return 1;
            return 0;
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