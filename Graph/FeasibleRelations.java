package Graph;

import java.io.*;
import java.util.*;

class FeasibleRelations {
    static class InputReader {

        private final InputStream stream;
        private final byte[] buf = new byte[8192];
        private int curChar, snumChars;

        public InputReader(InputStream st) {
            this.stream = st;
        }

        public int read() {
            if (snumChars == -1)
                throw new InputMismatchException();
            if (curChar >= snumChars) {
                curChar = 0;
                try {
                    snumChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (snumChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int nextInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public long nextLong() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public int[] nextIntArray(int n) {
            int a[] = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = nextInt();
            }
            return a;
        }

        public String readString() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public String nextLine() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isEndOfLine(c));
            return res.toString();
        }

        public boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        private boolean isEndOfLine(int c) {
            return c == '\n' || c == '\r' || c == -1;
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
    static ArrayList<Integer> adj[];
    static boolean[] vis;
    public static boolean dfs(int source,int destination){
        Queue<Integer> q = new LinkedList<>();
        q.add(source);
        vis[source] = true;
        while(!q.isEmpty()){
            int cur = q.poll();
            if(cur == destination)
                return true;
            for(int child:adj[cur]){
                if(!vis[child]){
                    vis[child] = true;
                    q.add(child);
                }
            }
        }
        return false;
    }

    public static void main(String args[]) throws IOException {
        InputReader  sc=new InputReader(System.in);
        int t = sc.nextInt();
        while(t-->0){
            int n = sc.nextInt();
            int m = sc.nextInt();
            adj = new ArrayList[n+1];
            vis = new boolean[n+1];
            Queue<Pair> q = new LinkedList<>();
            for(int i=1;i<=n;i++){
                adj[i] = new ArrayList<>();
            }
            for(int i=1;i<=m;i++){
                int x = sc.nextInt();
                String r = sc.readString().trim();
                int y = sc.nextInt();
                if(r.equals("=")){
                    adj[x].add(y);
                    adj[y].add(x);
                }
                else{
                    q.add(new Pair(x,y));
                }
            }
            boolean flag = false;
            while(!q.isEmpty()){
                Pair cur = q.poll();
                flag = dfs(cur.a,cur.b);
                if(flag)
                    break;

            }
            if(flag)
                System.out.println("NO");
            else
                System.out.println("YES");
        }
    }

}

