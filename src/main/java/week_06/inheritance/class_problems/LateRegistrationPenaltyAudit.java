package week_06.inheritance.class_problems;

import java.util.Arrays;

public class LateRegistrationPenaltyAudit {
    static class EventTicket {protected double balance;private double[] history=new double[0];EventTicket(double p){balance=p;}void pay(double a){if(a>0)balance=Math.max(0,balance-a);}double getBalanceDue(){return balance;}protected void applyLateFee(double a){balance+=a;history=Arrays.copyOf(history,history.length+1);history[history.length-1]=a;}double[] getLateFeeHistory(){return history.clone();}}
    static class WorkshopTicket extends EventTicket {WorkshopTicket(double p){super(p);}@Override protected void applyLateFee(double a){super.applyLateFee(a*2);}}
    public static void main(String[] args){WorkshopTicket w=new WorkshopTicket(1200);w.pay(1200);w.applyLateFee(100);System.out.println(w.getBalanceDue());System.out.println(Arrays.toString(w.getLateFeeHistory()));}
}