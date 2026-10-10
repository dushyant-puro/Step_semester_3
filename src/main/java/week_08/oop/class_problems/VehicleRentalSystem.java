package week_08.oop.class_problems;
import java.util.*;
public class VehicleRentalSystem {
 static abstract class Vehicle {String name;boolean available=true;Vehicle(String n){name=n;}abstract double dailyRate();}
 static class StandardCar extends Vehicle{StandardCar(String n){super(n);}double dailyRate(){return 50;}}
 static class LuxuryCar extends Vehicle{LuxuryCar(String n){super(n);}double dailyRate(){return 100;}}
 static class RentalService {double rent(Vehicle v,int days){if(!v.available||days<1)throw new IllegalStateException("Vehicle unavailable or invalid duration");v.available=false;return v.dailyRate()*days;}void returned(Vehicle v){v.available=true;}}
 public static void main(String[]x){RentalService s=new RentalService();Vehicle v=new LuxuryCar("Luxury Car A");System.out.printf("%s rented for 3 days. Total charge: $%.2f%n",v.name,s.rent(v,3));s.returned(v);System.out.println(v.name+" returned. Now available.");}
}