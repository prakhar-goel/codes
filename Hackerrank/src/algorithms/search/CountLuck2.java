package algorithms.search;

import java.util.Arrays;
import java.util.Scanner;

public class CountLuck2 {

    static boolean visited[][];
    static int n,m,posi,posj,desti,destj;
    static String[] forest;
    static boolean dfs(int i,int j,int waves[]){
        if (i<0||j<0||i>=n||j>=m)return false;
        if (forest[i].charAt(j)=='X')return false;
        if (visited[i][j])return false;
        if (i==desti&&j==destj)return true;
        int w1[]={0},w2[]={0},w3[]={0},w4[]={0};
        
        if (check(i+1,j)+check(i-1,j)+check(i,j+1)+check(i,j-1)>1){
            waves[0]++;
        }
        visited[i][j]=true;
        boolean b=false;
        if (dfs(i+1,j,w1)){
            b=true;
            
            waves[0]+=w1[0];
        }else if (dfs(i-1,j,w2)){
            b=true;
            waves[0]+=w2[0];
            
        }
        else if (dfs(i,j+1,w3)){
            b=true;
            waves[0]+=w3[0];
        }
        else if (dfs(i,j-1,w4)){
            b=true;
            waves[0]+=w4[0];
        }
        
        return b;
    }
    
    private static int check(int i, int j) {
        if (i<0||j<0||i>=n||j>=m)return 0;
        if (forest[i].charAt(j)=='X')return 0;
        if (visited[i][j])return 0;
        return 1;
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while (t--!=0){
            
             n=sc.nextInt();
             m=sc.nextInt(); 
             visited=new boolean[n][m];
             
             for (int i=0;i<n;++i)Arrays.fill(visited[i],false);
             forest=new String[n];
             
             for (int i=0;i<n;++i){
                 forest[i]=sc.next();
                 int x;
                 if ((x=forest[i].indexOf("M"))!=-1){
                     posi=i;
                     posj=x;
                 }
                 if ((x=forest[i].indexOf("*"))!=-1){
                     desti=i;
                     destj=x;
                 }
             }
             int k=sc.nextInt();
             int rr[]={0};
             dfs(posi,posj,rr);
             if(rr[0]==k){
                 System.out.println("Impressed");
             }
             else{
                 System.out.println("Oops!");
             }
        }
    }


}
