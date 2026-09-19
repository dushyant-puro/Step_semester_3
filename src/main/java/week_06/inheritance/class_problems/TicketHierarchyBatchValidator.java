package week_06.inheritance.class_problems;

public class TicketHierarchyBatchValidator {
    static class EventTicket {
        private final String attendeeId; protected double balance;
        EventTicket(String id,double price){if(id==null||id.trim().length()<4||price<=0)throw new IllegalArgumentException();attendeeId=id.trim();balance=price;}
        void pay(double amount){if(amount>0)balance=Math.max(0,balance-amount);} double getBalanceDue(){return balance;}
        void applyLateFee(double amount){balance+=amount;}
        String printTicket(){return "Standard Event Ticket | Balance Due: "+balance;}
        static String registerBatch(String[] ids,double price){int ok=0,bad=0;for(String id:ids)try{new EventTicket(id,price);ok++;}catch(IllegalArgumentException ex){bad++;}return "Registered: "+ok+" | Rejected: "+bad;}
    }
    static class WorkshopTicket extends EventTicket {private final String track;WorkshopTicket(String id,double price,String track){super(id,price);this.track=track;}@Override String printTicket(){return "Workshop Ticket | Track: "+track+" | Balance Due: "+balance;}}
    public static void main(String[] args){System.out.println(EventTicket.registerBatch(new String[]{"STU1","ST1","STU2"," ","STU3"},500));}
}