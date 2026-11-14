package week_05.encapsulation.assigment_problems;

public class MembershipFieldReachChecker {
    static String classifyAccess(String modifier,String context) {
        if("public".equals(modifier)) return "ALLOWED";
        if("private".equals(modifier)) return context.equals("SAME_CLASS")?"ALLOWED":"DENIED";
        if("default".equals(modifier)||"protected".equals(modifier)) return (context.equals("SAME_CLASS")||context.equals("SAME_PACKAGE"))?"ALLOWED":"DENIED";
        return "DENIED";
    }
    static String summarizeByModifier(String[][] attempts) {
        String[] mods={"private","default","protected","public"}; StringBuilder s=new StringBuilder();
        for(String m:mods){int yes=0,no=0; for(String[] a:attempts)if(m.equals(a[0])){if(classifyAccess(a[0],a[1]).equals("ALLOWED"))yes++;else no++;}
            if(s.length()>0)s.append(" | "); s.append(m).append(": ").append(yes).append(" allowed / ").append(no).append(" denied");}
        return s.toString();
    }
    static class LibraryMember {
        private final String membershipId; String branchCode; protected double finesOwed; public String displayName;
        LibraryMember(String id,String branch,double fines,String name){if(id==null||id.trim().length()<4)throw new IllegalArgumentException("Invalid membership ID");membershipId=id.trim();branchCode=branch;finesOwed=fines;displayName=name;}
        String getMembershipId(){return membershipId;}
    }
    public static void main(String[] args){System.out.println(classifyAccess("protected","DIFFERENT_PACKAGE"));}
}