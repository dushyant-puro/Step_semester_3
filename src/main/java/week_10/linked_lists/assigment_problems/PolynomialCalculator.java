package week_10.linked_lists.assigment_problems;
public class PolynomialCalculator {
 static class Node{int coefficient,exponent;Node next;Node(int c,int e){coefficient=c;exponent=e;}}
 public static Node addPolynomials(Node p,Node q){Node dummy=new Node(0,0),tail=dummy;while(p!=null||q!=null){int c,e;if(q==null||(p!=null&&p.exponent>q.exponent)){c=p.coefficient;e=p.exponent;p=p.next;}else if(p==null||q.exponent>p.exponent){c=q.coefficient;e=q.exponent;q=q.next;}else{c=p.coefficient+q.coefficient;e=p.exponent;p=p.next;q=q.next;}if(c!=0){tail.next=new Node(c,e);tail=tail.next;}}return dummy.next;}
 static void print(Node n){if(n==null){System.out.println("0");return;}boolean first=true;while(n!=null){int c=n.coefficient,e=n.exponent;if(!first)System.out.print(c<0?" - ":" + ");else if(c<0)System.out.print("-");int abs=Math.abs(c);if(e==0||abs!=1)System.out.print(abs);if(e>0){System.out.print("x");if(e>1)System.out.print("^"+e);}first=false;n=n.next;}System.out.println();}
 public static void main(String[]args){Node p=new Node(5,3);p.next=new Node(4,2);p.next.next=new Node(2,0);Node q=new Node(5,2);q.next=new Node(-3,1);q.next.next=new Node(1,0);print(addPolynomials(p,q));}
}