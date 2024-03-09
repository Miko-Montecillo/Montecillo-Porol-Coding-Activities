// Montecillo & Porol CSCC 13 B
import java.util.Scanner;

public class CoinRowProblem {

    public static int[] coinRow(int[] coins) {
        int n = coins.length;
        int[] F = new int[n + 1];
        int[] pickedUp = new int[n + 1]; // Array to store if each coin is picked up or not

        // Base cases
        F[0] = 0;
        F[1] = coins[0];
        pickedUp[0] = 0;
        pickedUp[1] = 1; // The first coin is picked up

        // Determine which coins to pick up to maximize the value
        for (int i = 2; i <= n; i++) {
            if (coins[i - 1] + F[i - 2] > F[i - 1]) {
                F[i] = coins[i - 1] + F[i - 2];
                pickedUp[i] = 1; // Coin at index i is picked up
            } else {
                F[i] = F[i - 1];
                pickedUp[i] = 0; // Coin at index i is not picked up
            }
        }

        // Adjust pickedUp array based on the constraint
        for (int i = n; i >= 1; i--) {
            if (pickedUp[i] == 1 && i > 1 && pickedUp[i - 1] == 1) {
                // If current coin and previous coin are both picked up, skip the current coin
                pickedUp[i - 1] = 0;
            }
        }

        return pickedUp;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input number of coins
        int N = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Input coins
        int[] coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = scanner.nextInt();
        }

        // Calculate coins picked up
        int[] pickedUp = coinRow(coins);

        // Output coins and their status
        for (int i = 0; i < N; i++) {
            System.out.print(coins[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < N; i++) {
            System.out.print((pickedUp[i + 1] == 1 ? "X" : "-") + " ");
        }

        // Calculate and output the maximum amount of money that can be picked up
        int maxMoney = 0;
        for (int i = 1; i <= N; i++) {
            if (pickedUp[i] == 1) {
                maxMoney += coins[i - 1];
            }
        }
        System.out.println("\nMaximum amount of money that can be picked up: " + maxMoney);

        scanner.close();
    }
}
