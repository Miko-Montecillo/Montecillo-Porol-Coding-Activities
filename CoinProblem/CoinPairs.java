package CoinProblem;

// Montecillo & Porol CSCC 13 B
import java.util.Scanner;

public class Montecillo_and_Porol {
    public static void main(String[] args) {
        Scanner value = new Scanner(System.in);
        System.out.print("input: ");
        int coinValue = value.nextInt();

        System.out.println("\nOutput:");
        noOfCoins(coinValue);

        value.close();
    }

    public static void noOfCoins(int value) {
        int[] coins = { 20, 10, 5, 1 };
        int existingValue = value;

        for (int coin : coins) {
            if (existingValue >= coin){
                int count = existingValue / coin;
                System.out.println(count + " x " + coin + " coin/s");
                existingValue %= coin;
            }
        }
    }
}
