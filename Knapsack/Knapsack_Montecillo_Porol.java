package Knapsack;
// Montecillo-Porol CSCC 13 CCB 

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Knapsack_Montecillo_Porol {
    static int maxValue = 0;
    static List<Integer> selectedItems = new ArrayList<>();
    static List<Integer> selectedWeights = new ArrayList<>();

    static void generateSubsets(int[] valuesList, int[] weightsList, int maxWeight, int totalValue, int totalWeight,
            int index,
            List<Integer> selected, List<Integer> weights) {

        if (index == valuesList.length) {
            if (totalWeight <= maxWeight && totalValue > maxValue) {
                maxValue = totalValue;
                selectedItems = new ArrayList<>(selected);
                selectedWeights = new ArrayList<>(weights);
            }
            return;
        }

        if (totalWeight + weightsList[index] <= maxWeight) {
            selected.add(index);
            weights.add(weightsList[index]);
            generateSubsets(valuesList, weightsList, maxWeight, totalValue + valuesList[index],
                    totalWeight + weightsList[index], index + 1, selected, weights);
            selected.remove(selected.size() - 1);
            weights.remove(weights.size() - 1);
        }

        generateSubsets(valuesList, weightsList, maxWeight, totalValue, totalWeight, index + 1, selected, weights);
    }

    public static void main(String[] args) {
        Scanner scanInt = new Scanner(System.in);

        int maxWeight = scanInt.nextInt();
        int itemNums = scanInt.nextInt();
        int[] valuesList = new int[itemNums];
        int[] weightsList = new int[itemNums];

        for (int i = 0; i < itemNums; i++) {
            weightsList[i] = scanInt.nextInt();
            valuesList[i] = scanInt.nextInt();
        }

        generateSubsets(valuesList, weightsList, maxWeight, 0, 0, 0, new ArrayList<>(), new ArrayList<>());

        int totalWeight = 0;
        for (int weight : selectedWeights) {
            totalWeight += weight;
        }

        System.out.println("weight: " + totalWeight);
        System.out.println("value: " + maxValue);
        System.out.print("items: ");
        for (int i = 0; i < selectedItems.size(); i++) {
            System.out.print((selectedItems.get(i) + 1) + " ");
        }
    }
}