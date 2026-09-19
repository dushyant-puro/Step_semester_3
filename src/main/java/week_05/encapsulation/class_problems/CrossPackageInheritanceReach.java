package week_05.encapsulation.class_problems;

public class CrossPackageInheritanceReach {
    static String classifyAccess(String modifier, String context) {
        switch (modifier) {
            case "public": return "ALLOWED";
            case "private": return "SAME_CLASS".equals(context) ? "ALLOWED" : "DENIED";
            case "default": return (context.equals("SAME_CLASS") || context.equals("SAME_PACKAGE")) ? "ALLOWED" : "DENIED";
            case "protected":
                if (context.equals("SAME_CLASS") || context.equals("SAME_PACKAGE") || context.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) return "ALLOWED";
                return "DENIED";
            default: return "DENIED";
        }
    }
    static String describeContext(String context) {
        StringBuilder out = new StringBuilder();
        for (String part : context.toLowerCase().split("_")) {
            if (!part.isEmpty()) out.append(Character.toUpperCase(part.charAt(0))).append(part.substring(1)).append(' ');
        }
        return out.toString().trim();
    }
    public static void main(String[] args) {
        System.out.println(classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));
        System.out.println(classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));
        System.out.println(describeContext("SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));
    }
}