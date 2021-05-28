/*
* The question can be reduced to Count of Subsets with given Sum.
*
* As we have difference given.
*And we want to divide array into two subsets i.e S1 - S2 = difference.
*We also know that S1 + S2 = Sum of array.
* On adding both we get.
* S1 = (diff + sum)/2;
* So if we able to find count of S1 in subset then it will be the answer.
* */


package DP;

import java.io.*;

class NumberOfSubsetsWithGivenDiffernece {
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

    public static void main(String args[]) throws IOException {

        int n = inputInt();
        int[] a = new int[n];
        int total = 0;
        for(int i = 0;i < n;i++){
            a[i] = inputInt();
            total += a[i];
        }
        int diff = inputInt();
        int sum = (total + diff) / 2;
        println(perfectSum(a,n,sum)+"");
        bw.flush();
        bw.close();
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