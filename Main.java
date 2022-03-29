package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int n,arr[];
         Scanner sc=new Scanner(System.in);
         System.out.println("Enter number of elements in array");
         n=sc.nextInt();
         arr=new int[n];
         System.out.println("Enter array elements");
         for(int i=0;i<n;i++){
             arr[i]=sc.nextInt();
         }
         /*int count0=0,count1=0,count2=0;
         for(int i=0;i<n;i++){
             if(arr[i]==0)
                 count0++;
             else if(arr[i]==1)
                 count1++;
             else
                 count2++;
         }

         int sort[]=new int[n];
         for(int i=0;i<count0;i++)
             sort[i]=0;
         for(int i=0;i<count1;i++)
             sort[i+count0]=1;
         for(int i=0;i<count2;i++)
             sort[i+count0+count1]=2;*/

        /*int inner,temp;
        for(int outer=1;outer<n;outer++){
            temp=arr[outer];
            inner=outer;
            while(inner>0 && arr[inner-1]>temp){
                arr[inner]=arr[inner-1];
                --inner;
            }
            arr[inner]=temp;
        }*/

       /* int start=0,mid=0,end=n-1;
        int temp;
        while(mid<=end){
            if(arr[mid]==0){
                temp=arr[start];
                arr[start]=arr[mid];
                arr[mid]=temp;
                start++;
                mid++;
            }else if(arr[mid]==1){
                mid++;
            }else{
                temp=arr[mid];
                arr[mid]=arr[end];
                arr[end]=temp;
                end--;
            }
        }*/
        //1 1 0 2 1 0 2 1 0 1 2

        int start=0,end=n-1,temp;
        while(start<=end) {
            if (arr[start] == 0) {

            }else if(arr[start]==2){
                temp=arr[end];
                arr[end]=arr[start];
                arr[start]=temp;
                start++;
                end--;
                //System.out.println("this is inside 2");
            }
        }

         System.out.println("Sorted array:");
         for(int i=0;i<n;i++){
             System.out.print(arr[i]+" ");
         }
    }
}
