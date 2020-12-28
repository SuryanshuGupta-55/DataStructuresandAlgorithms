import java.io.*;
import java.util.Stack;

class ReverseaString {
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
       /* Stack<Integer> s = new Stack<>();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);
        println(s.peek()+"");
        println("Before rev"+s.toString());
        Stackrev(s);
        println(s.toString());*/
      /* int n = 545;
       if(n == pallinnum(n))
           println("True");
       else
           println("FALSE");*/
      TOH(3,'S','D','H');
        bw.flush();
        bw.close();
    }
    private static String rev(String str){
        if(str.length()<1)
            return null;
        if(str.length() == 1){
            return str;
        }
        return str.charAt(str.length()-1)+rev(str.substring(0,str.length()-1));
    }
    private static void insert(Stack<Integer> stack,int ele){
        if(stack.isEmpty())
        {
            stack.push(ele);
            return;
        }
        else{
            int x = stack.peek();
            stack.pop();
            insert(stack,ele);
            stack.push(x);
            return;
        }
    }
    private static void Stackrev(Stack<Integer> stack){
        if(stack.size() == 1)
            return;
        int x = stack.peek();
        stack.pop();
        Stackrev(stack);
        insert(stack,x);
        return;
    }
    public static int pallinnum(int n){
        return (pallinnum(n,0));
    }
   private static int pallinnum(int n,int temp){
        if(n == 0)
            return temp;
        temp = (temp*10) + (n%10);
        return pallinnum(n/10,temp);
   }
   private static void natural(int n) throws IOException {
        if(n == 0) {

            return;
        }
        natural(n-1);
        printSp(n+"");

   }
   private static void TOH(int n,char source,char destination,char helper) throws IOException {
        if(n == 1)
        {
            println("MOVE DISK "+n+" FROM "+(source)+" TO DESTINATION "+(destination));
            return;
        }
        TOH(n-1,source,helper,destination);
        println("MOVE DISK "+n+" FROM "+(source)+" TO DESTINATION "+(destination));
        TOH(n-1,helper,destination,source);
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
