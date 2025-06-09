package org.example.uisiziba;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class WHODataLoader {
    private final Map<String, Map<Integer, WHOEntry>> whoData = new HashMap<>();

    public void loadWHOData() {
        whoData.put("L", loadFromCSV("BMIboys2yrs.csv"));
        whoData.put("P", loadFromCSV("BMIgirls2yrs.csv"));
    }

    private Map<Integer, WHOEntry> loadFromCSV(String fileName) {
        Map<Integer, WHOEntry> data = new HashMap<>();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        getClass().getClassLoader().getResourceAsStream(fileName)
                )
        )) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                int month = Integer.parseInt(tokens[0].trim());
                double p3 = Double.parseDouble(tokens[7].trim());
                double p85 = Double.parseDouble(tokens[14].trim());
                data.put(month, new WHOEntry(p3, p85));
                // Optional: Print loaded data
                System.out.printf("Loaded WHO data: Month %d | Persentil 3: %.2f | Persentil 85: %.2f%n", month, p3, p85);
            }
        } catch (Exception e) {
            System.out.println("Gagal membaca file " + fileName + ": " + e.getMessage());
        }
        return data;
    }

    public WHOEntry getWHOEntry(String gender, int age) {
        Map<Integer, WHOEntry> genderMap = whoData.get(gender);
        return (genderMap != null) ? genderMap.get(age) : null;
    }
}
