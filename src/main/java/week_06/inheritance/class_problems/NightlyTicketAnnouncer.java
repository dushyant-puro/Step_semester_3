package week_06.inheritance.class_problems;

public class NightlyTicketAnnouncer {
    static class EventTicket {protected double balance;EventTicket(double p){balance=p;}double getBalanceDue(){return balance;}String printTicket(){return "Standard | Balance: "+balance;}}
    static class WorkshopTicket extends EventTicket {private String track;WorkshopTicket(double p,String t){super(p);track=t;}String getTrack(){return track;}@Override String printTicket(){return "Workshop | Track: "+track+" | Balance: "+balance;}}
    static String batchPrint(EventTicket[] tickets){StringBuilder b=new StringBuilder();for(EventTicket t:tickets){b.append(t.printTicket());if(t instanceof WorkshopTicket)b.append(" [Track via downcast: ").append(((WorkshopTicket)t).getTrack()).append(']');b.append(" | ");}return b.toString();}
    public static void main(String[] args){System.out.println(batchPrint(new EventTicket[]{new EventTicket(500),new WorkshopTicket(1200,"AI/ML")}));}
}