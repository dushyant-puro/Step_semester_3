package week_10.linked_lists.assigment_problems;
public class CampusPortalBrowserHistory {
 static class Node{String url;Node prev,next;Node(String u){url=u;}}
 private Node current;
 public CampusPortalBrowserHistory(String homepage){if(homepage==null||homepage.isEmpty())throw new IllegalArgumentException("Homepage required");current=new Node(homepage);}
 public String visit(String url){if(url==null||url.isEmpty())throw new IllegalArgumentException("URL required");current.next=null;Node n=new Node(url);n.prev=current;current.next=n;current=n;return current.url;}
 public String back(int steps){if(steps<0)throw new IllegalArgumentException("steps cannot be negative");while(steps-->0&&current.prev!=null)current=current.prev;return current.url;}
 public String forward(int steps){if(steps<0)throw new IllegalArgumentException("steps cannot be negative");while(steps-->0&&current.next!=null)current=current.next;return current.url;}
 public static void main(String[]args){CampusPortalBrowserHistory h=new CampusPortalBrowserHistory("srm.edu");h.visit("a.com");h.visit("b.com");h.visit("c.com");System.out.println(h.back(1));System.out.println(h.back(1));System.out.println(h.forward(1));h.visit("d.com");System.out.println(h.forward(2));System.out.println(h.back(2));System.out.println(h.back(7));}
}