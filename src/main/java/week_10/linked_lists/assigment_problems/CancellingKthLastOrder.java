package week_10.linked_lists.assigment_problems;
public class CancellingKthLastOrder {
 static class Node {int data;Node next;Node(int d){data=d;}}
 public static Node removeKthFromEnd(Node head,int k){if(k<1)throw new IllegalArgumentException("k must be positive");Node dummy=new Node(0);dummy.next=head;Node lead=dummy,trail=dummy;for(int i=0;i<k+1;i++){if(lead==null)throw new IllegalArgumentException("k exceeds list length");lead=lead.next;}while(lead!=null){lead=lead.next;trail=trail.next;}trail.next=trail.next.next;return dummy.next;}
 static Node of(int...values){Node d=new Node(0),t=d;for(int v:values){t.next=new Node(v);t=t.next;}return d.next;}static void print(Node n){while(n!=null){System.out.print(n.data+(n.next==null?"":" -> "));n=n.next;}System.out.println();}
 public static void main(String[]args){print(removeKthFromEnd(of(10,20,30,40,50),2));print(removeKthFromEnd(of(10,20,30,40,50),5));}
}