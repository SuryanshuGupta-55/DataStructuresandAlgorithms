import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {

    static String max ="";
    public static void maxNum(char ar[], int k){
        if(k==0)
            return;
        int n = ar.length;
        for(int i=0; i<n-1; i++){
            for(int j=i+1; j<n; j++){
                if(ar[j]>ar[i]){
                    char temp = ar[i];
                    ar[i] = ar[j];
                    ar[j] = temp;
                    String st = new String(ar);
                    if(max.compareTo(st)<0){
                        max = st;
                    }
                    maxNum(ar,k-1);
                    temp = ar[i];
                    ar[i] = ar[j];
                    ar[j] = temp;
                }
            }
        }
    }
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-->0){
            int k = sc.nextInt();
            max = sc.next();
            maxNum(max.toCharArray(),k);
            System.out.println(max);
        }
    }
}

   /* public static void main(String args[]) throws IOException {
        int t = inputInt();
        while(t-->0) {
            int k = inputInt();
            String input = inputString();
            int[] a = new int[input.length()];
            for (int i = 0; i < input.length(); i++) {
                a[i] = input.charAt(i) - '0';
            }
            swaps(a, k, 0);
            input = "";
            for (int i : a) {
                input += i;
            }
            println(input);
        }
        bw.flush();
        bw.close();
    }
    private static void swaps(int[] a,int k,int start) throws IOException {
        if(k==0)
            return;
        int max = 0;
        int index = 0;
        for(int i =start;i<a.length;i++){
            if(max < a[i]){
                max = a[i];
                index = i;
            }
        }
        println("Value of start is "+start);
        println("index for "+max+" is"+index);
        if(index!=start){
            int temp = a[start];
            a[start] = a[index];
            a[index] = temp;
            swaps(a,k-1,start+1);
        }
        else{
            swaps(a,k,start+1);
        }
    }*/
