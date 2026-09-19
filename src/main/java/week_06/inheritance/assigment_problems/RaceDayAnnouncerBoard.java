package week_06.inheritance.assigment_problems;

public class RaceDayAnnouncerBoard {
    static class RaceEntry {protected String bib;protected double balance;RaceEntry(String b,double f){bib=b;balance=f;}double getBalanceDue(){return balance;}String announce(){return "Race Entry | Bib: "+bib+" | Balance: "+balance;}}
    static class RunnerEntry extends RaceEntry {private String category;RunnerEntry(String b,double f,String c){super(b,f);category=c;}@Override String announce(){return "Runner Entry | Bib: "+bib+" | Category: "+category+" | Balance: "+balance;}}
    static class RelayTeamEntry extends RaceEntry {private int teamSize;RelayTeamEntry(String b,double f,int n){super(b,f);teamSize=n;}int getTeamSize(){return teamSize;}@Override String announce(){return "Relay Team | Bib: "+bib+" | Team Size: "+teamSize+" | Balance: "+balance;}}
    static String announceAll(RaceEntry[] entries){StringBuilder out=new StringBuilder();for(RaceEntry e:entries){out.append(e.announce());if(e instanceof RelayTeamEntry)out.append(" [Team size via downcast: ").append(((RelayTeamEntry)e).getTeamSize()).append(']');out.append(" | ");}return out.toString();}
    public static void main(String[] args){System.out.println(announceAll(new RaceEntry[]{new RunnerEntry("BIB2001",80,"Open 10K"),new RelayTeamEntry("BIB4001",300,4)}));}
}