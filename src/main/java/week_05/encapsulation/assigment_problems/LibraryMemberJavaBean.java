package week_05.encapsulation.assigment_problems;

public class LibraryMemberJavaBean {
    static class LibraryMember {
        private String membershipId,name,securityAnswerHash; private boolean premiumMember;
        public LibraryMember(){this(null,null);} public LibraryMember(String name){this(null,name);}
        public LibraryMember(String id,String name){this.name=name;setMembershipId(id);}
        public String getMembershipId(){return membershipId;} public void setMembershipId(String id){if(membershipId==null&&id!=null&&!id.trim().isEmpty())membershipId=id.trim();}
        public String getName(){return name;} public void setName(String name){this.name=name;}
        public boolean isPremiumMember(){return premiumMember;} public void setPremiumMember(boolean premium){premiumMember=premium;}
        public void setSecurityAnswer(String answer){if(answer!=null)securityAnswerHash=Integer.toHexString(answer.hashCode());}
    }
    public static void main(String[] args){LibraryMember m=new LibraryMember("Priya Nair");System.out.println(m.getMembershipId());}
}