package week_06.inheritance.class_problems;

public class ThreeShapesTicketFamily {
    static class EventTicket {protected double balance;EventTicket(double price){if(price<=0)throw new IllegalArgumentException();balance=price;}void pay(double a){if(a>0)balance=Math.max(0,balance-a);}double getBalanceDue(){return balance;}String printTicket(){return "Standard Event Ticket | Balance Due: "+balance;}}
    static class WorkshopTicket extends EventTicket {protected String track;WorkshopTicket(double p,String t){super(p);track=t;}@Override String printTicket(){return "Workshop Ticket | Track: "+track+" | Balance Due: "+balance;}}
    static class PremiumWorkshopTicket extends WorkshopTicket {private double kitFee;PremiumWorkshopTicket(double p,String t,double k){super(p,t);kitFee=k;}@Override String printTicket(){return "Premium Workshop Ticket | Track: "+track+" | Kit Fee: "+kitFee+" | Balance Due: "+balance;}}
    static class HackathonTicket extends EventTicket {private String team;HackathonTicket(double p,String t){super(p);team=t;}@Override String printTicket(){return "Hackathon Ticket | Team: "+team+" | Balance Due: "+balance;}}
    static String classifyGeneration(EventTicket t){if(t instanceof PremiumWorkshopTicket)return "Multilevel descendant (3 generations deep)";if(t instanceof HackathonTicket)return "Hierarchical sibling (independent branch)";if(t instanceof WorkshopTicket)return "Direct subclass";return "Base ticket";}
    static double getTotalBalanceDue(EventTicket[] ts){double sum=0;for(EventTicket t:ts)if(t!=null)sum+=t.getBalanceDue();return sum;}
    public static void main(String[] args){EventTicket[] ts={new EventTicket(500),new WorkshopTicket(1200,"AI/ML"),new PremiumWorkshopTicket(2000,"Cloud Native",300),new HackathonTicket(800,"Byte Force")};for(EventTicket t:ts)System.out.println(t.printTicket());System.out.println(getTotalBalanceDue(ts));}
}