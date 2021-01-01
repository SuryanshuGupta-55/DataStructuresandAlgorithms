package Graph;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

class GERALD07 {
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
    static class Pair{
        int a;
        int b;
        public Pair(int a,int b){
            this.a = a;
            this.b = b;
        }
    }
    static ArrayList<Integer> adj[];
    static int count;
    static boolean[] vis;
    static Pair[] temp;
    public static void dfs(int source){
        vis[source] = true;
        for(int child:adj[source]){
            if(!vis[child])
                dfs(child);
        }
    }
    static void printGraph(ArrayList<Integer> adj[])
    {
        for (int i = 1; i < adj.length; i++) {
            System.out.println("\nAdjacency list of vertex" + i);
            System.out.print("head");
            for (int j = 1; j < adj[i].size(); j++) {
                System.out.print(" -> "+adj[i].get(j));
            }
            System.out.println();
        }
    }

    public static void main(String args[]) throws IOException {
        int t = inputInt();
        while(t-->0){
            int n = inputInt();
            int m = inputInt();
            int query= inputInt();
            adj = new ArrayList[n+1];
            vis = new boolean[n+1];
            temp = new Pair[m+1];
            for(int i=1;i<=m;i++){
                int source = inputInt();
                int destination = inputInt();
                temp[i] = new Pair(source,destination);
            }
           /* for(int cur=1;cur<temp.length;cur++){
                Pair var = temp[cur];
                println(var.a+" "+var.b);
            }*/
            for(int i=1;i<=query;i++){
                int x = inputInt();
                int y = inputInt();
                for(int k=1;k<=n;k++){
                    adj[k] = new ArrayList<>();
                }
                Arrays.fill(vis,false);
                count = 0;
                for(int j=x;j<=y;j++){
                    int p = temp[j].a;
                    int q = temp[j].b;
                    adj[p].add(q);
                    adj[q].add(p);
                }
              /*  println("The graph is");
                printGraph(adj);*/
                for(int l = 1;l<=n;l++){
                    if(!vis[l]){
                        count++;
                        dfs(l);
                    }
                }
                println(count+"");
            }

        }
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
