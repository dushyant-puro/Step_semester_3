package week_05.encapsulation.assigment_problems;

public class ReferenceDeskSubclassReach {
    static String classifyAccess(String m,String c){if(m.equals("public"))return "ALLOWED";if(m.equals("private"))return c.equals("SAME_CLASS")?"ALLOWED":"DENIED";if(m.equals("default"))return c.equals("SAME_CLASS")||c.equals("SAME_PACKAGE")?"ALLOWED":"DENIED";if(m.equals("protected"))return c.equals("SAME_CLASS")||c.equals("SAME_PACKAGE")||c.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")?"ALLOWED":"DENIED";return "DENIED";}
    static String describeContext(String c){StringBuilder b=new StringBuilder();for(String p:c.toLowerCase().split("_"))if(!p.isEmpty())b.append(Character.toUpperCase(p.charAt(0))).append(p.substring(1)).append(' ');return b.toString().trim();}
    public static void main(String[] a){System.out.println(classifyAccess("protected","SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));System.out.println(describeContext("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));}
}