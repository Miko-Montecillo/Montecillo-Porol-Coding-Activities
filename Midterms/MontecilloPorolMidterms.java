package Midterms;

import java.util.Scanner;

public class MontecilloPorolMidterms {

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
                    + " takes round " + (testCase + 1) + " by " + result
                            .getWinner().getHealth()
                    + " health!!\n");

            System.out.println("End of round " + (testCase + 1) + "!");
        }
        
        

        // Close the scanner to prevent resource leak
        scanner.close();
    }

    // Function to simulate the combat between two combatants and return the result
    private static CombatResult simulateCombat(Combatant combatant1, Combatant combatant2) {
        // Loop until one of the combatants' health reaches zero
        while (!combatant1.isDefeated() && !combatant2.isDefeated()) {
            // Inflict damage to each combatant based on the opponent's power
            combatant2.takeDamage(combatant1.getPower());
            combatant1.takeDamage(combatant2.getPower());
        }

        // Determine the winner of the combat
        Combatant winner = (combatant1.isDefeated()) ? combatant2 : combatant1;
        // Create a combat result object to store the winner
        return new CombatResult(winner);
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
            // Ensure that health does not go below zero
            if (health < 0) {
                health = 0;
            }
        }
    }

    // CombatResult class to store the result of the combat
    static class CombatResult {
        // Attribute to store the winning combatant
        private Combatant winner;

        // Constructor to initialize the combat result with the winning combatant
        public CombatResult(Combatant winner) {
            this.winner = winner;
        }

        // Getter method to retrieve the winning combatant
        public Combatant getWinner() {
            return winner;
        }
    }
}