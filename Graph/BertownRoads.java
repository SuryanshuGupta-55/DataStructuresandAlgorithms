package Graph;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class BertownRoads {
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
    static class Pair{
        int a;
        int b;
        public Pair(int a,int b){
            this.a = a;
            this.b = b;
        }
    }

    static Reader sc = new Reader();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static ArrayList<Integer> adj[];
    static boolean[] vis;
    static int[] in;
    static int[] low;
    static int timer = 0;
    static boolean flag = false;
    static Queue<Pair> q = new LinkedList<>();
    public static void IsBridge(int cur,int par){
        vis[cur] = true;
        in[cur] = low[cur] = timer++;
        for(int child:adj[cur]){
            if(child == par)
                continue;
            else if(vis[child]){
                //This condition mainly checks for back edges in the graph.
                low[cur] = Math.min(low[cur],in[child]);
                //As we know back edge remain un traversed therefore we are finding its closet ancestor having minimum in[] value.
                if(in[cur] > in[child])
                    //This condition is for marking down the path from child to ancestor since back edge remains un traversed it will be used as the edge for child to return back to parent or node.
                    q.add(new Pair(cur,child));
            }
            else{
                IsBridge(child,cur);
                if(low[child] > in[cur]){
                    //Condition to check for the bridge if  a node is having only one edge and its closet ancestor is greater than the in time of parent it means it is the bridge.
                    flag = true;
                    return;
                }
                //Since forward edge moves from root to node adding it.
                q.add(new Pair(cur,child));
                low[cur] = Math.min(low[cur],low[child]);
            }
        }
    }

    public static void main(String args[]) throws IOException {
        int n = inputInt();
        int m = inputInt();
        adj = new ArrayList[n];
        vis = new boolean[n];
        in = new int[n];
        low = new int[n];
        timer = 0;
        flag = false;
        for(int i=0;i<n;i++){
            adj[i] = new ArrayList<>();
        }
        for(int i=0;i<m;i++){
            int source = inputInt() -1;
            int des = inputInt() -1;
            adj[source].add(des);
            adj[des].add(source);
        }
        q.clear();
        IsBridge(0,-1);
        if(flag)
            println("0");
        else{
            for(Pair i:q){
                println((i.a + 1)+" "+(i.b + 1));
            }
        }

       /* println(flag+"");*/

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
