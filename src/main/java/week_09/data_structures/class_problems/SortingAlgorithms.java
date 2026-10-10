package week_09.data_structures.class_problems;
import java.util.Arrays;
public class SortingAlgorithms {
static void swap(int[]a,int i,int j){int t=a[i];a[i]=a[j];a[j]=t;}
public static void bubbleSort(int[]a){for(int i=0;i<a.length-1;i++){boolean s=false;for(int j=0;j<a.length-1-i;j++)if(a[j]>a[j+1]){swap(a,j,j+1);s=true;}if(!s)break;}}
public static void selectionSort(int[]a){for(int i=0;i<a.length-1;i++){int m=i;for(int j=i+1;j<a.length;j++)if(a[j]<a[m])m=j;swap(a,i,m);}}
public static void insertionSort(int[]a){for(int i=1;i<a.length;i++){int k=a[i],j=i-1;while(j>=0&&a[j]>k){a[j+1]=a[j];j--;}a[j+1]=k;}}
public static void mergeSort(int[]a){if(a.length<2)return;sort(a,new int[a.length],0,a.length-1);}static void sort(int[]a,int[]t,int l,int r){if(l>=r)return;int m=(l+r)/2;sort(a,t,l,m);sort(a,t,m+1,r);int i=l,j=m+1,k=l;while(i<=m&&j<=r)t[k++]=a[i]<=a[j]?a[i++]:a[j++];while(i<=m)t[k++]=a[i++];while(j<=r)t[k++]=a[j++];System.arraycopy(t,l,a,l,r-l+1);}
public static void quickSort(int[]a){quick(a,0,a.length-1);}static void quick(int[]a,int l,int r){if(l>=r)return;int p=a[l+(r-l)/2],i=l,j=r;while(i<=j){while(a[i]<p)i++;while(a[j]>p)j--;if(i<=j)swap(a,i++,j--);}if(l<j)quick(a,l,j);if(i<r)quick(a,i,r);}
public static void heapSort(int[]a){for(int i=a.length/2-1;i>=0;i--)heap(a,a.length,i);for(int e=a.length-1;e>0;e--){swap(a,0,e);heap(a,e,0);}}static void heap(int[]a,int n,int i){while(true){int m=i,l=2*i+1,r=l+1;if(l<n&&a[l]>a[m])m=l;if(r<n&&a[r]>a[m])m=r;if(m==i)return;swap(a,i,m);i=m;}}
public static void countingSort(int[]a){if(a.length<2)return;int min=a[0],max=a[0];for(int v:a){min=Math.min(min,v);max=Math.max(max,v);}long range=(long)max-min+1;if(range>1000000)throw new IllegalArgumentException("Range too large");int[]c=new int[(int)range];for(int v:a)c[v-min]++;int k=0;for(int i=0;i<c.length;i++)while(c[i]-->0)a[k++]=i+min;}
public static void main(String[]x){int[]a={7,3,9,1,5};bubbleSort(a);System.out.println(Arrays.toString(a));}
}