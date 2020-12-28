import java.io.*;

class KthnodefromLL {
    private static class Node{
        Node next;
        int data;

        public Node(int data) {
            this.data = data;
        }
    }
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
        Node node = null;
        node = insert(node,9);
        node = insert(node,8);
        node = insert(node,3);
        node = insert(node,5);
        node = insert(node,2);
        node = insert(node,1);
        /*display(node);*/
        Node temp = remove(node,4);
        display(temp);
        bw.flush();
        bw.close();
    }
    private static Node insert(Node head,int data){
        Node node = new Node(data);
        if(head == null){
            return node;
        }
        else
            head.next = insert(head.next,data);
        return head;
    }
    private static void display(Node node)throws IOException{
        if(node == null)
            return;
        println(node.data+"");
        display(node.next);
    }
    private static Node remove(Node head,int k){
        if(head == null)
            return null;
        if(k==1){
            return head.next;
        }
        head.next = remove(head.next,k-1);
        return head;
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
