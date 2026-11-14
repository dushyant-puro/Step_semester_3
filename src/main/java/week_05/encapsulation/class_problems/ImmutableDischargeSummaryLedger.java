package week_05.encapsulation.class_problems;

import java.util.Arrays;

public class ImmutableDischargeSummaryLedger {
    static { System.setProperty("meditrack.ledger.ready", "true"); }
    // The prompt requests a final base class and a subclass simultaneously; Java forbids that combination.
    // Keep the base extensible here so the required critical-care subtype and instanceof processing can work.
    static class DischargeSummary {
        private final String patientId;
        private final String[] medicationCodes;
        DischargeSummary(String id, String[] codes) {
            if (id == null || id.trim().isEmpty() || codes == null) throw new IllegalArgumentException("Invalid summary");
            for (String code : codes) if (code == null || !code.matches("MED-[A-Z]")) throw new IllegalArgumentException("Invalid medication code");
            patientId=id; medicationCodes=codes.clone();
        }
        String getPatientId() { return patientId; }
        String[] getMedicationCodes() { return medicationCodes.clone(); }
        DischargeSummary withCorrectedMedication(int index, String code) {
            String[] updated=medicationCodes.clone(); updated[index]=code; return new DischargeSummary(patientId, updated);
        }
    }
    static final class CriticalCareDischargeSummary extends DischargeSummary {
        private final int icuDays;
        CriticalCareDischargeSummary(String id, String[] codes, int days) { super(id,codes); if(days<0) throw new IllegalArgumentException(); icuDays=days; }
        int getIcuDays() { return icuDays; }
    }
    static String processNightlyBatch(DischargeSummary[] batch) {
        int processed=0, skipped=0, critical=0, routine=0;
        for (DischargeSummary d: batch) { if(d==null) {skipped++; continue;} processed++; if(d instanceof CriticalCareDischargeSummary) critical++; else routine++; }
        return processed+" processed | "+skipped+" null skipped | "+critical+" critical-care | "+routine+" routine";
    }
    public static void main(String[] args) {
        DischargeSummary[] records={new CriticalCareDischargeSummary("MT001",new String[]{"MED-X"},4),null,new DischargeSummary("MT002",new String[]{"MED-Y"})};
        System.out.println(processNightlyBatch(records));
    }
}