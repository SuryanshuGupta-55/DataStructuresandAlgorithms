import java.io.*;

class EqualSumHalves {
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
        int n = 3;
        String output="";
        solve(n,output,0,0);
        bw.flush();
        bw.close();
    }
    private static void solve(int n,String output,int zero,int one) throws IOException {
        if (n == 0) {
            println(output + "");
            return;
        }
        if(n>zero && n>one) {
            String op1 = output;
            String op2 = output;
            op1 += "0";
            op2 += "1";
            solve(n - 1, op1, zero + 1, one);
            solve(n - 1, op2, zero, one + 1);
        }
        else{
            if(n!=1){
                if(zero == n){
                    String op = output;
                    op += "0";
                    solve(n-1,op,zero+1,one);
                }
                if(one == n){
                    String op = output;
                    op += "1";
                    solve(n-1,op,zero,one+1);
                }
            }
            else{
                if((zero&1)==1){
                    String op = output;
                    op += "0";
                    solve(n-1,op,zero+1,one);
                }
                if((one&1)==1){
                    String op = output;
                    op += "1";
                    solve(n-1,op,zero,one+1);
                }
            }
        }
       /* else if((zero&1)==1){
            String op = output;
            op += "0";
            solve(n-1,op,zero+1,one);
        }
        else if((one&1)==1){
            String op = output;
            op += "1";
            solve(n-1,op,zero,one+1);
        }*/

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
