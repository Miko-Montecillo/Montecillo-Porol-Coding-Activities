package Midterms;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExcessiveVersion {

    public static void main(String[] args) {
        // Create a scanner object to read input from the user
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter the number of test cases
        System.out.println("Enter the number of rounds:");
        int testCases = scanner.nextInt();

        // Iterate over each test case
        for (int testCase = 0; testCase < testCases; testCase++) {
            // Prompt the user to enter details for each combatant in every test case
            System.out.println("\nWelcome to round " + (testCase + 1)
                    + "!! Give the name, health, and power of each fighter below:");

            // Input details for the first combatant (pirate)
            System.out.print("Pirate: ");
            Combatant pirate = new Combatant(scanner.next(), scanner.nextInt(), scanner.nextInt(), "pirates");
            // Input details for the second combatant (ninja)
            System.out.print("Ninja: ");
            Combatant ninja = new Combatant(scanner.next(), scanner.nextInt(), scanner.nextInt(), "ninjas");

            System.out.println("\nGET READY!!\n\nFIGHT!!!\n");

            // Simulate the combat between the pirate and the ninja and get the result
            CombatResult result = simulateCombat(pirate, ninja);

            // Output the result of the combat
            System.out.println(result.getWinner().getName() + " of the " + result.getWinner().getType()
                    + " takes round " + (testCase + 1) + " by " + result.getWinner().getHealth()
                    + " health!!\n");

            // Ask the user if they want to see the battle summary for this round
            System.out.println("Do you want to see the battle summary for this round? (yes/no)");
            String showSummary = scanner.next();

            if (showSummary.equalsIgnoreCase("yes")) {
                // Display the battle summary table
                BattleSummary.showSummary(result);
            }

            // Display end of round message
            System.out.println("\nEnd of round " + (testCase + 1) + "!");
        }

        // Close the scanner to prevent resource leak
        scanner.close();
    }

    // Function to simulate the combat between two combatants and return the result
    private static CombatResult simulateCombat(Combatant combatant1, Combatant combatant2) {
        List<Integer> pirateHealth = new ArrayList<>();
        List<Integer> ninjaHealth = new ArrayList<>();

        // Add initial health values
        pirateHealth.add(combatant1.getHealth());
        ninjaHealth.add(combatant2.getHealth());

        // Loop until one of the combatants' health reaches zero
        while (!combatant1.isDefeated() && !combatant2.isDefeated()) {
            // Inflict damage to each combatant based on the opponent's power
            combatant2.takeDamage(combatant1.getPower());
            combatant1.takeDamage(combatant2.getPower());

            // Add current health values
            pirateHealth.add(combatant1.getHealth());
            ninjaHealth.add(combatant2.getHealth());
        }

        // Determine the winner of the combat
        Combatant winner = (combatant1.isDefeated()) ? combatant2 : combatant1;

        // Create a combat result object to store the winner
        return new CombatResult(winner, pirateHealth, ninjaHealth);
    }

    // Combatant class to represent each fighter
    static class Combatant {
        // Attributes of a combatant: name, health, and power
        private String name;
        private int health;
        private int power;
        private String type;

        // Constructor to initialize a combatant with name, health, and power
        public Combatant(String name, int health, int power, String type) {
            this.name = name;
            this.health = health;
            this.power = power;
            this.type = type;
        }

        // Getter method to retrieve the name of the combatant
        public String getName() {
            return name;
        }

        // Getter method to retrieve the current health of the combatant
        public int getHealth() {
            return health;
        }

        // Getter method to retrieve the power of the combatant
        public int getPower() {
            return power;
        }

        // Getter method to retrieve the fighter type of the combatant
        public String getType() {
            return type;
        }

        // Method to check if the combatant has been defeated (health <= 0)
        public boolean isDefeated() {
            return health <= 0;
        }

        // Method to apply damage to the combatant's health
        public void takeDamage(int damage) {
            // Subtract the damage from the combatant's health
            health -= damage;
        }
    }

    // CombatResult class to store the result of the combat
    static class CombatResult {
        // Attribute to store the winning combatant
        private Combatant winner;
        // Attributes to store health changes during combat
        private List<Integer> pirateHealth;
        private List<Integer> ninjaHealth;

        // Constructor to initialize the combat result with the winning combatant and
        // health changes
        public CombatResult(Combatant winner, List<Integer> pirateHealth, List<Integer> ninjaHealth) {
            this.winner = winner;
            this.pirateHealth = pirateHealth;
            this.ninjaHealth = ninjaHealth;
        }

        // Getter method to retrieve the winning combatant
        public Combatant getWinner() {
            return winner;
        }

        // Getter method to retrieve the health changes for pirates
        public List<Integer> getPirateHealth() {
            return pirateHealth;
        }

        // Getter method to retrieve the health changes for ninjas
        public List<Integer> getNinjaHealth() {
            return ninjaHealth;
        }
    }

    // BattleSummary class to generate and display the battle summary
    static class BattleSummary {
        // Static method to show the battle summary for each exchange
        public static void showSummary(CombatResult result) {
            System.out.println("\nBattle Summary:");
            System.out.println("--------------------------------------------------");
            System.out.println("Exchange #   Pirate Health   Ninja Health");
            System.out.println("--------------------------------------------------");

            List<Integer> pirateHealth = result.getPirateHealth();
            List<Integer> ninjaHealth = result.getNinjaHealth();

            for (int i = 1; i < pirateHealth.size(); i++) {
                int exchangeNumber = i;
                int pirateHealthNow = pirateHealth.get(i);
                int ninjaHealthNow = ninjaHealth.get(i);
                System.out.printf("%-12d %-15d %-12d%n", exchangeNumber, pirateHealthNow, ninjaHealthNow);
            }

            System.out.println("--------------------------------------------------");
        }
    }
}
