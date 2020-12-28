/*package whatever //do not write package name here */

import java.util.*;
import java.lang.*;
import java.io.*;

class JosephusProblem {
    static int ans = 0;
    public static void main (String[] args) {
        //code
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        while(t-->0){
            int n = s.nextInt();
            int k = s.nextInt();
            ArrayList<Integer> al = new ArrayList<Integer>();
            for(int i=1;i<=n;i++){
                al.add(i);
            }
            k = k-1;
            solve(al,k,0);
            System.out.println(ans);
            /*ans = 0;*/
        }
    }
    public static void solve(ArrayList<Integer> al,int k,int index){

        if(al.size()==1){
            ans = al.get(0);
            return;
        }
        index = (index+k)%al.size();
        al.remove(index);
        solve(al,k,index);
    }
}