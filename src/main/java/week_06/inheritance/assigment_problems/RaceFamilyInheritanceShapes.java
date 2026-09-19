package week_06.inheritance.assigment_problems;

public class RaceFamilyInheritanceShapes {
    static class RaceEntry {protected double balance;protected String bib;RaceEntry(String b,double f){if(b==null||b.trim().length()<4||f<=0)throw new IllegalArgumentException();bib=b;balance=f;}double getBalanceDue(){return balance;}String announce(){return "Race Entry | Bib: "+bib+" | Balance: "+balance;}}
    static class RunnerEntry extends RaceEntry {protected String category;RunnerEntry(String b,double f,String c){super(b,f);category=c;}@Override String announce(){return "Runner Entry | Bib: "+bib+" | Category: "+category+" | Balance: "+balance;}}
    static class EliteRunnerEntry extends RunnerEntry {private double bonus;EliteRunnerEntry(String b,double f,String c,double s){super(b,f,c);bonus=s;}@Override String announce(){return "Elite Runner | Bib: "+bib+" | Category: "+category+" | Sponsor Bonus: "+bonus+" | Balance: "+balance;}}
    static class RelayTeamEntry extends RaceEntry {private int teamSize;RelayTeamEntry(String b,double f,int n){super(b,f);if(n<=0)throw new IllegalArgumentException();teamSize=n;}@Override String announce(){return "Relay Team | Bib: "+bib+" | Team Size: "+teamSize+" | Balance: "+balance;}}
    static String classifyGeneration(RaceEntry e){if(e instanceof EliteRunnerEntry)return "Multilevel descendant (3 generations deep)";if(e instanceof RelayTeamEntry)return "Hierarchical sibling (independent branch)";return "Direct subclass";}
    static double getTotalBalanceDue(RaceEntry[] es){double total=0;for(RaceEntry e:es)total+=e.getBalanceDue();return total;}
    public static void main(String[] args){RaceEntry[] es={new RunnerEntry("BIB2",80,"Open"),new EliteRunnerEntry("BIB3",150,"Elite",500),new RelayTeamEntry("BIB4",300,4)};for(RaceEntry e:es)System.out.println(e.announce());System.out.println(getTotalBalanceDue(es));}
}