package org.example.uisiziba;

public class BMICalculator {
    public static double calculateBMI(double weight, double heightCm) {
        double heightM = heightCm / 100;
        return weight / (heightM * heightM);
    }

    public static String classifyBMI(double bmi, String gender, int age, WHODataLoader loader) {
        WHOEntry entry = loader.getWHOEntry(gender, age);
        if (entry == null) {
            return "Data WHO tidak ditemukan untuk usia " + age + " bulan";
        }

        System.out.printf("Referensi WHO untuk usia %d bulan: Persentil 3 = %.2f, Persentil 85 = %.2f%n", age, entry.getP3(), entry.getP85());

        if (bmi < entry.getP3()) {
            return "Underweight (di bawah P3)";
        } else if (bmi <= entry.getP85()) {
            return "Normal (antara P3 dan P85)";
        } else {
            return "Overweight/Obese (di atas P85)";
        }
    }
}
