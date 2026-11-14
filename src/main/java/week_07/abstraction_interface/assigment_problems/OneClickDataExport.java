package week_07.abstraction_interface.assigment_problems;

interface Exportable { String exportData(); }

class ExportCounter {
    private static int totalExports = 0;
    static void recordExport() { totalExports++; }
    static int getTotalExports() { return totalExports; }
}

class ReportGenerator implements Exportable {
    private final String reportName;
    public ReportGenerator(String reportName) { this.reportName = reportName; }
    @Override public String exportData() {
        ExportCounter.recordExport();
        return "Exported report: " + reportName;
    }
}

class UserProfile implements Exportable {
    private final String username;
    public UserProfile(String username) { this.username = username; }
    @Override public String exportData() {
        ExportCounter.recordExport();
        return "Exported profile: " + username;
    }
}

public class OneClickDataExport {
    static int getTotalExports() { return ExportCounter.getTotalExports(); }
    static void exportAll(Exportable[] items) {
        for (Exportable item : items) System.out.println(item.exportData());
    }
    public static void main(String[] args) {
        ReportGenerator r = new ReportGenerator("Sales Q1");
        UserProfile u = new UserProfile("jane_doe");
        Exportable ref = r; // Upcasting to interface type.
        System.out.println(ref.exportData());
        exportAll(new Exportable[]{ref, u});
        System.out.println(getTotalExports());
    }
}