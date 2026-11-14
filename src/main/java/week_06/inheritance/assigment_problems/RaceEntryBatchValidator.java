package week_06.inheritance.assigment_problems;

public class RaceEntryBatchValidator {
    static class RaceEntry {private final String bibNumber;protected double balance;RaceEntry(String bib,double fee){if(bib==null||bib.trim().length()<4||fee<=0)throw new IllegalArgumentException();bibNumber=bib.trim();balance=fee;}void pay(double a){if(a>0)balance=Math.max(0,balance-a);}double getBalanceDue(){return balance;}String announce(){return "Race Entry | Bib: "+bibNumber+" | Balance: "+balance;}static String registerBatch(String[] bibs,double fee){int ok=0,rejected=0;for(String bib:bibs)try{new RaceEntry(bib,fee);ok++;}catch(IllegalArgumentException e){rejected++;}return "Registered: "+ok+" | Rejected: "+rejected;}}
    static class RunnerEntry extends RaceEntry {protected String category;RunnerEntry(String bib,double fee,String c){super(bib,fee);category=c;}@Override String announce(){return "Runner Entry | Category: "+category+" | Balance: "+balance;}}
    public static void main(String[] args){System.out.println(RaceEntry.registerBatch(new String[]{"BIB1","B1","BIB2"},80));}
}