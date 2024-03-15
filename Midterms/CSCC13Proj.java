package Midterms;

import java.util.Scanner;

public class CSCC13Proj {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // test case indicates the rounds
        System.out.println("Enter number of test cases:");
        int testCases = sc.nextInt();

        System.out.println("Enter Name, Health, and Power for combatant");
        for (int testCase = 0; testCase < testCases; testCase++) {
            // name, health and power of the pirate
            String name1 = sc.next();
            int health1 = sc.nextInt();
            int power1 = sc.nextInt();
            // name, health and power of the ninja
            String name2 = sc.next();
            int health2 = sc.nextInt();
            int power2 = sc.nextInt();

            // Simulate the combat
            CombatResult result = simulateCombat(name1, health1, power1, name2, health2, power2);

            // Output the result
            System.out.println(result.winnerName + " " + result.winnerHealth);
        }
        sc.close();
    }

    // Function to simulate the combat and return the remaining health of the winner
    private static CombatResult simulateCombat(String name1, int health1, int power1, String name2, int health2,
            int power2) {
        int damageDealt1 = 0;
        int damageDealt2 = 0;

        while (health1 > 0 && health2 > 0) {
            // Calculate damage dealt in this exchange
            int damage1 = Math.min(power1, health2);
            int damage2 = Math.min(power2, health1);

            // Subtract damage from health
            health1 -= damage2;
            health2 -= damage1;

            // Accumulate total damage dealt
            damageDealt1 += damage1;
            damageDealt2 += damage2;
        }

        // Determine the winner
        String winnerName = (health1 > 0) ? name1 : name2;
        int winnerHealth = (health1 > 0) ? health1 : health2;
        int damageDealt = (health1 > 0) ? damageDealt1 : damageDealt2;

        return new CombatResult(winnerName, winnerHealth, damageDealt);
    }

    // Helper class to store combat result
    static class CombatResult {
        String winnerName;
        int winnerHealth;
        int damageDealt;

        CombatResult(String winnerName, int winnerHealth, int damageDealt) {
            this.winnerName = winnerName;
            this.winnerHealth = winnerHealth;
            this.damageDealt = damageDealt;
        }
    }
}
