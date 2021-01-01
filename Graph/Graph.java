package Graph;
import java.awt.desktop.AboutEvent;
import java.util.*;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class Graph {
    private LinkedList<Integer> adj[];
    private int V;
    public Graph(int v){
        V = v;
        adj = new LinkedList[v];
        for(int i = 0;i < v;i++){
            adj[i] = new LinkedList<>();
        }
    }
    public void addEdge(int source,int destination){
        //For directed graph it will only be source -> destination.
        //For undirected it will be both source -> destination and destination -> source.
        adj[source].add(destination);
        adj[destination].add(source);
    }
    public void printGraph(){
    /*    for(int i=0;i<adj.length;i++){
            System.out.print("The vertex is " + " " + i +" and is connected to : ");
            for(int j =0;j<adj[i].size();j++){
                System.out.print(adj[i].get(j)+" ");
            }
            System.out.println();
        }*/
        System.out.println(Arrays.toString(adj));
    }
    public void BFS(int s){
        boolean[] vis = new boolean[V];
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        vis[s] = true;
        while(!q.isEmpty()){
            int cur = q.poll();
            System.out.print("The connected nodes with given source is "+ cur+" ");
            for(int neighbour : adj[cur]){
                if(!vis[neighbour]){
                    q.add(neighbour);
                    vis[neighbour] = true;
                }
            }
        }
    }
    public void DFS(int source,boolean[] vis){
        vis[source] = true;
        System.out.println("The path for dfs is "+ source+" ");
        for(int neighbor: adj[source]){
            if(!vis[neighbor]){
                vis[neighbor] = true;
                DFS(neighbor,vis);
            }
        }
    }

    public void DFS(int source){
        boolean[] vis = new boolean[V];
        //If we have an disconnected graph then DFS will also contain.
         for(int i=0;i<V;i++){
           if(!vis[i]){
               DFS(i,vis);
           }
         }
    }
    public int ComponentCount(){
        int cnt = 0;
        boolean[] vis = new boolean[V];
        for(int i=0;i<V;i++){
            if(!vis[i]){
                cnt++;
                DFS(i,vis);
            }
        }
        return cnt;
    }
    public boolean isBipartite(int source,boolean[] vis,int[] col,int c){
        vis[source] = true;
        col[source] = c;
        for(int neighbour:adj[source]){
            if(!vis[neighbour]){
                if(!isBipartite(neighbour, vis, col, c ^ 1))
                    return false;
            }
            if(col[neighbour] == col[source])
                return false;
        }
        return true;
    }
    public boolean isBipartite(int source){
        int c = 0;
        int[] col = new int[V];
        boolean[] vis = new boolean[V];
        if(isBipartite(source,vis,col,c))
            return true;
        else
            return false;
    }
    public static void main(String args[]){
        Scanner s = new Scanner(System.in);
        int v = s.nextInt();
        int e = s.nextInt();
        Graph graph = new Graph(v);
        for(int i=0;i<e;i++){
            int source = s.nextInt();
            int destination = s.nextInt();
            graph.addEdge(source,destination);
        }
        System.out.println(graph.isBipartite(0));
        /*graph.printGraph();*/
    }
}
