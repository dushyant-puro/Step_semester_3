package week_05.encapsulation.class_problems;

import java.util.Arrays;

public class VitalsMonitoringEncapsulationGuard {
    static class PatientVitals {
        private double[] readings = new double[0];
        PatientVitals(double[] initialReadings) {
            if (initialReadings != null) for (double value : initialReadings) recordReading(value);
        }
        void recordReading(double reading) {
            if (reading <= 0 || reading > 45 || Double.isNaN(reading)) return;
            readings = Arrays.copyOf(readings, readings.length + 1);
            readings[readings.length - 1] = reading;
        }
        double getAverage() {
            if (readings.length == 0) return 0;
            double sum = 0; for (double r : readings) sum += r;
            return sum / readings.length;
        }
        double[] getAllReadings() { return readings.clone(); }
    }
    public static void main(String[] args) {
        PatientVitals v = new PatientVitals(new double[]{36.5, -2, 37.1});
        System.out.println(Arrays.toString(v.getAllReadings()));
        double[] copy = v.getAllReadings(); copy[0] = 999;
        System.out.println(v.getAllReadings()[0]);
    }
}