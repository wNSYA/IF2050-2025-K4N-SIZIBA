package org.example.uisiziba;



import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WHODataLoader loader = new WHODataLoader();
        loader.loadWHOData();

        System.out.print("Jenis Kelamin (L/P): ");
        String gender = scanner.nextLine().trim().toUpperCase();

        System.out.print("Usia (bulan): ");
        int age = scanner.nextInt();

        System.out.print("Berat badan (kg): ");
        double weight = scanner.nextDouble();

        System.out.print("Tinggi badan (cm): ");
        double height = scanner.nextDouble();

        double bmi = BMICalculator.calculateBMI(weight, height);
        String category = BMICalculator.classifyBMI(bmi, gender, age, loader);

        System.out.printf("BMI: %.2f%n", bmi);
        System.out.println("Kategori: " + category);
    }
}
