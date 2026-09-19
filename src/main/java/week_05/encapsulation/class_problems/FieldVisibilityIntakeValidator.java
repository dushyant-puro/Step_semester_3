package week_05.encapsulation.class_problems;

public class FieldVisibilityIntakeValidator {
    static String classifyAccess(String modifier, String context) {
        if ("public".equals(modifier)) return "ALLOWED";
        if ("private".equals(modifier)) return "SAME_CLASS".equals(context) ? "ALLOWED" : "DENIED";
        if ("default".equals(modifier) || "protected".equals(modifier))
            return ("SAME_CLASS".equals(context) || "SAME_PACKAGE".equals(context)) ? "ALLOWED" : "DENIED";
        return "DENIED";
    }
    static String summarizeBatch(String[][] attempts) {
        int allowed = 0, denied = 0;
        for (String[] a : attempts) if ("ALLOWED".equals(classifyAccess(a[0], a[1]))) allowed++; else denied++;
        return "Allowed: " + allowed + " | Denied: " + denied;
    }
    static class PatientRecord {
        private final String patientId;
        String wardCode;
        protected double vitalsScore;
        public String facilityName;
        PatientRecord(String id, String ward, double score, String facility) {
            if (id == null || id.trim().length() < 4) throw new IllegalArgumentException("Invalid patient ID");
            patientId=id.trim(); wardCode=ward; vitalsScore=score; facilityName=facility;
        }
        public String getPatientId() { return patientId; }
    }
    public static void main(String[] args) {
        System.out.println(classifyAccess("private", "SAME_CLASS"));
        System.out.println(summarizeBatch(new String[][]{{"protected","SAME_PACKAGE"},{"protected","DIFFERENT_PACKAGE"},{"public","DIFFERENT_PACKAGE"}}));
        try { new PatientRecord("MT9","W3",98.2,"MediTrack Central"); }
        catch (IllegalArgumentException e) { System.out.println("construction rejected"); }
    }
}