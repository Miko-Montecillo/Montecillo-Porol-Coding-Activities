package Midterms;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlainVanillaSoyBeanBobaMilkTea {
    public static void main(String[] args) {
        // Create a scanner object to read input from the user
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter the number of test cases
        int testCases = scanner.nextInt();

        // Create a list to store combatants' details for each test case
        List<Combatant[]> allCombatants = new ArrayList<>();

        // Read all the inputs for each test case and store them
        for (int testCase = 0; testCase < testCases; testCase++) {
            // Input details for the first combatant (pirate)
            String pirateName = scanner.next();
            int pirateHealth = scanner.nextInt();
            int piratePower = scanner.nextInt();

            // Input details for the second combatant (ninja)
            String ninjaName = scanner.next();
            int ninjaHealth = scanner.nextInt();
            int ninjaPower = scanner.nextInt();

            // Create Combatant objects for each combatant
            Combatant pirate = new Combatant(pirateName, pirateHealth, piratePower, "pirates");
            Combatant ninja = new Combatant(ninjaName, ninjaHealth, ninjaPower, "ninjas");

            // Store the combatants' details in the list
            allCombatants.add(new Combatant[] { pirate, ninja });
        }

        // Close the scanner to prevent resource leak
        scanner.close();

        // Simulate combats for each test case and output the results
        for (Combatant[] combatants : allCombatants) {
            CombatResult result = simulateCombat(combatants[0], combatants[1]);
            System.out.println(result.getWinner().getName() + " " + result.getWinner().getHealth());
        }
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

        // Getter methods and other methods remain unchanged
        public String getName() {
            return name;
        }

        public int getHealth() {
            return health;
        }

        public int getPower() {
            return power;
        }

        public boolean isDefeated() {
            return health <= 0;
        }

        public void takeDamage(int damage) {
            health -= damage;
            if (health < 0) {
                health = 0;
            }
        }
    }

    // CombatResult class remains unchanged
    static class CombatResult {
        private Combatant winner;

        public CombatResult(Combatant winner) {
            this.winner = winner;
        }

        public Combatant getWinner() {
            return winner;
        }
    }
}
