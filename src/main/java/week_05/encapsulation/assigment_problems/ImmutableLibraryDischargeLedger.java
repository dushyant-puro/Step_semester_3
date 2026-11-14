package week_05.encapsulation.assigment_problems;

public class ImmutableLibraryDischargeLedger {
    static class DischargeSummary {
        private final String memberId; private final String[] bookIds;
        DischargeSummary(String id,String[] ids){if(id==null||id.trim().isEmpty()||ids==null)throw new IllegalArgumentException();for(String s:ids)if(s==null||!s.matches("BOOK-[A-Z]"))throw new IllegalArgumentException("Invalid book code");memberId=id;bookIds=ids.clone();}
        String[] getBookIds(){return bookIds.clone();}
        DischargeSummary withCorrectedBook(int i,String code){String[] copy=bookIds.clone();copy[i]=code;return new DischargeSummary(memberId,copy);}
    }
    static class PriorityDischargeSummary extends DischargeSummary {private final int priority;PriorityDischargeSummary(String id,String[] codes,int p){super(id,codes);priority=p;}int getPriority(){return priority;}}
    static String processBatch(DischargeSummary[] entries){int done=0,nulls=0,priority=0,regular=0;for(DischargeSummary d:entries){if(d==null){nulls++;continue;}done++;if(d instanceof PriorityDischargeSummary)priority++;else regular++;}return done+" processed | "+nulls+" null skipped | "+priority+" priority | "+regular+" routine";}
    public static void main(String[] args){System.out.println(processBatch(new DischargeSummary[]{new PriorityDischargeSummary("MB01",new String[]{"BOOK-A"},2),null,new DischargeSummary("MB02",new String[]{"BOOK-B"})}));}
}