package DP;

import java.io.*;

class LPS {
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
        String str = inputString();
        int n = str.length();

        println(lps(str,0,n-1)+"");
        bw.flush();
        bw.close();
    }


    /*Approach used LCS and palindrome property if last and first character are same then ans will be +2 else check for removing 1st character and removing last character
    * such as
    *                       abc
    *                   ab       bc
    *                 a    b    b    c
    *
    *                       pooe
    *
    *                   poo         ooe
    *
    *               po      oo      oo  oe
    *
    *           p       o   2       2   o   e
    *
    *       You can also see the overlapping Subproblems.
    *
    * */
    public static int lps(String s,int i,int j) throws IOException {
        //println(s.substring(i,j+1));
        if(i > j)
            return 0;
        if(i == j)
            return 1;
        if(s.charAt(i) == s.charAt(j))
            return 2 + lps(s,i+1,j-1);
        else{
            return   Math.max(lps(s,i,j-1),lps(s,i+1,j));
        }
    }

    /*
    * 2nd Approach Observing that LPS == LCS(a,reverse(a));
    * Just use some test cases it would be clear.
    *
    *  public int longestPalinSubseq(String s)
    {
        int n = s.length();
        dp = new int[n+1][n+1];

        for(int i = 0;i < dp.length;i++){
            Arrays.fill(dp[i],-1);
        }

        String rev = "";
        for(char ch:s.toCharArray())
            rev = ch + rev;

        return LCS(s,rev,n,n);
    }

    public int LCS(String a,String b,int n,int m){

        if(n == 0|| m == 0)
            return 0;

        if(dp[n][m] != -1)
            return dp[n][m];

        if(a.charAt(n-1) == b.charAt(m-1)){
            return dp[n][m] = 1 + LCS(a,b,n-1,m-1);
        }
        else{
            return dp[n][m] = Math.max(LCS(a,b,n,m-1),LCS(a,b,n-1,m));
        }
    }
    * */




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