package JAVA;

import java.util.Scanner;

public class CSCC13Proj {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // test case indicates the rounds
        System.out.println("Enter number of test cases:");
        int testCases = sc.nextInt();

        System.out.println("Enter Name, Health, and Power for combatant 1:");
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
            int winnerHealth = simulateCombat(health1, power1, health2, power2);

            // Determine the winner
            String winnerName = (winnerHealth == health1) ? name1 : name2;

            // Output the result
            System.out.println(winnerName + " " + winnerHealth);
        }
        sc.close();
    }

    // Function to simulate the combat and return the remaining health of the winner
    private static int simulateCombat(int health1, int power1, int health2, int power2) {
        while (health1 > 0 && health2 > 0) {
            health2 -= power1;
            if (health2 <= 0) {
                return health1;
            }
            health1 -= power2;
        }
        return health2;
    }
}
