package week_06.inheritance.assigment_problems;

import java.util.Arrays;

public class LateWithdrawalPenaltyAudit {
    static class RaceEntry {protected double balance;private double[] fees=new double[0];RaceEntry(double f){balance=f;}void pay(double a){if(a>0)balance=Math.max(0,balance-a);}protected void applyLateFee(double a){balance+=a;fees=Arrays.copyOf(fees,fees.length+1);fees[fees.length-1]=a;}double[] getLateFeeHistory(){return fees.clone();}double getBalanceDue(){return balance;}}
    static class RunnerEntry extends RaceEntry {RunnerEntry(double f){super(f);}@Override protected void applyLateFee(double a){super.applyLateFee(a*2);}}
    public static void main(String[] args){RunnerEntry r=new RunnerEntry(80);r.pay(30);r.applyLateFee(20);System.out.println(r.getBalanceDue());System.out.println(Arrays.toString(r.getLateFeeHistory()));}
}