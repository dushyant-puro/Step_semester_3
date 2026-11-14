package week_08.oop.class_problems;
import java.util.*;
public class FoodOrderFlexiblePayment {
 interface PaymentMethod{boolean pay(double amount);}
 static class CardPayment implements PaymentMethod{public boolean pay(double a){return a>=0;}}
 static class WalletPayment implements PaymentMethod{public boolean pay(double a){return a>=0;}}
 static class Order{Map<String,Integer> items=new LinkedHashMap<>();void add(String n,int q){if(q<1)throw new IllegalArgumentException();items.put(n,items.getOrDefault(n,0)+q);}boolean place(PaymentMethod p,double total){if(items.isEmpty())return false;return p.pay(total);}}
 public static void main(String[]x){Order o=new Order();o.add("Pizza",2);o.add("Soda",1);System.out.println(o.place(new CardPayment(),25)?"Order placed. Payment successful.":"Cannot place order.");}
}