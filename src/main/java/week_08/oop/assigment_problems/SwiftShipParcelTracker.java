package week_08.oop.assigment_problems;
import java.util.*;
public class SwiftShipParcelTracker {
 enum Status{BOOKED,PICKED_UP,IN_TRANSIT,OUT_FOR_DELIVERY,DELIVERED,CANCELLED}
 interface Shipping{double charge(double kg);} interface Channel{void notify(String id,Status s);}
 static class Standard implements Shipping{public double charge(double kg){return 40+10*kg;}} static class Express implements Shipping{public double charge(double kg){return 80+15*kg;}} static class Fragile implements Shipping{public double charge(double kg){return 40+10*kg+50;}}
 static class Sms implements Channel{public void notify(String id,Status s){System.out.println("[SMS] "+id+" is now "+s);}} static class Email implements Channel{public void notify(String id,Status s){System.out.println("[Email] "+id+" is now "+s);}}
 static class Parcel{String id;double kg;Status status=Status.BOOKED;Shipping shipping;List<Channel> channels;Parcel(String i,double w,Shipping s,List<Channel> c){id=i;kg=w;shipping=s;channels=c;}void transition(Status next){if(next==Status.CANCELLED&&status!=Status.BOOKED)throw new IllegalStateException("Can cancel only while BOOKED");if(next!=Status.CANCELLED&&next.ordinal()!=status.ordinal()+1)throw new IllegalArgumentException("Invalid transition");status=next;for(Channel c:channels)c.notify(id,status);}}
 public static void main(String[]a){Parcel p=new Parcel("P101",2,new Express(),Arrays.asList(new Sms(),new Email()));System.out.printf("Charge: ₹%.2f%n",p.shipping.charge(p.kg));p.transition(Status.PICKED_UP);p.transition(Status.IN_TRANSIT);}
}