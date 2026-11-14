package week_08.oop.class_problems;
import java.time.*;import java.util.*;
public class HotelBookingSystem {
 static class Reservation {String room;LocalDate start,end,deadline;boolean cancelled;Reservation(String r,LocalDate s,LocalDate e,LocalDate d){room=r;start=s;end=e;deadline=d;}boolean overlaps(LocalDate s,LocalDate e){return !cancelled&&s.isBefore(end)&&e.isAfter(start);}}
 static class Manager {List<Reservation> rs=new ArrayList<>();Reservation book(String room,LocalDate s,LocalDate e,LocalDate deadline,double rate){if(!s.isBefore(e))throw new IllegalArgumentException("Invalid dates");for(Reservation r:rs)if(r.room.equals(room)&&r.overlaps(s,e))throw new IllegalStateException("Room unavailable");Reservation r=new Reservation(room,s,e,deadline);rs.add(r);System.out.printf("%s booked. Total price: $%.2f%n",room,rate*(e.toEpochDay()-s.toEpochDay()));return r;}boolean cancel(Reservation r,LocalDate today){if(r.cancelled||today.isAfter(r.deadline))return false;r.cancelled=true;return true;}}
 public static void main(String[]a){Manager m=new Manager();m.book("Deluxe Room 101",LocalDate.parse("2024-12-01"),LocalDate.parse("2024-12-05"),LocalDate.parse("2024-11-30"),200);}
}