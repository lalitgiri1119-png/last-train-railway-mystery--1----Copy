package com.lasttrain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private final Scanner scanner = new Scanner(System.in);

    // Required collection: ArrayList stores all passengers on the train.
    private final ArrayList<Passenger> passengers = new ArrayList<>();

    // Required collection: List stores clues discovered by the player.
    private final List<String> clues = new ArrayList<>();

    // Required collection: LinkedList records the order of visited compartments.
    private final LinkedList<String> compartmentHistory = new LinkedList<>();

    private final ArrayList<Compartment> compartments = new ArrayList<>();
    private int movesLeft = 14;
    private boolean gameOver;

    public Game() {
        loadPassengers();
        loadCompartments();
    }

    private void loadPassengers() {
        passengers.add(new Passenger("Eleanor Voss", "the conductor",
                "\"The engine stopped for exactly two minutes at 11:40. I logged it, but someone removed that page.\""));
        passengers.add(new Passenger("Dr. Adrian Vale", "a physician",
                "\"I saw a passenger leave the first-class coach carrying a wet umbrella. It had not rained.\""));
        passengers.add(new Passenger("Mira Sen", "a travel journalist",
                "\"The missing passenger, Mr. Rowan, was carrying a silver pocket watch. I heard it chime after midnight.\""));
        passengers.add(new Passenger("Mr. Finch", "a quiet businessman",
                "\"I was asleep. I know nothing about Rowan or the locked luggage room.\""));
    }

    private void loadCompartments() {
        compartments.add(new Compartment(
                "Dining Car",
                "An abandoned dinner sits on a table. One chair is pulled away from the table.",
                "a torn ticket marked 11:40",
                "I have hands but cannot clap. I have a face but cannot speak. What am I?",
                "clock",
                "A torn ticket places someone in the dining car at 11:40."
        ));
        compartments.add(new Compartment(
                "Sleeping Coach",
                "The corridor is silent. One berth has fresh scratches near its lock.",
                "a brass key tucked beneath a pillow",
                "What has keys but cannot open locks?",
                "piano",
                "The brass key fits the luggage room. The scratches suggest the berth was searched."
        ));
        compartments.add(new Compartment(
                "Luggage Room",
                "Suitcases are stacked to the ceiling. A faint metallic sound comes from one trunk.",
                "a silver pocket watch stopped at 11:42",
                "The more you take, the more you leave behind. What are they?",
                "footsteps",
                "The pocket watch stopped two minutes after the engine interruption."
        ));
        compartments.add(new Compartment(
                "Engine Room",
                "The engine hums unevenly. A maintenance panel hangs open.",
                "a folded page from the conductor's log",
                "I go up but never come down. What am I?",
                "age",
                "The missing log page records a manual brake release from the luggage-room access corridor."
        ));
        compartments.add(new Compartment(
                "First-Class Coach",
                "A luxury compartment. A damp umbrella rests beside an empty seat.",
                "a small vial labelled 'sleeping drops'",
                "What can travel around the world while staying in one corner?",
                "stamp",
                "The vial contains a sedative. The umbrella is dry inside, but wet on its handle."
        ));
    }

    public void start() {
        printIntro();

        while (!gameOver && movesLeft > 0) {
            showStatus();
            System.out.println("\nChoose an action:");
            System.out.println("1. Explore a compartment");
            System.out.println("2. Question a passenger");
            System.out.println("3. Review collected clues");
            System.out.println("4. View compartment history");
            System.out.println("5. Make final accusation");
            System.out.println("0. Quit");
            System.out.print("> ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> exploreCompartment();
                case "2" -> questionPassenger();
                case "3" -> showClues();
                case "4" -> showHistory();
                case "5" -> makeAccusation();
                case "0" -> {
                    System.out.println("\nYou leave the mystery unsolved. The train disappears into the fog...");
                    gameOver = true;
                }
                default -> System.out.println("Invalid choice. Enter a number from 0 to 5.");
            }
        }

        if (!gameOver) {
            System.out.println("\nThe final station is approaching. You ran out of time.");
            System.out.println("The truth remains buried aboard The Last Train.");
        }
        scanner.close();
    }

    private void printIntro() {
        System.out.println("==========================================");
        System.out.println("       THE LAST TRAIN: RAILWAY MYSTERY");
        System.out.println("==========================================");
        System.out.println("At 11:40 PM, the train stopped in the middle of nowhere.");
        System.out.println("When it started again, passenger Mr. Rowan had vanished.");
        System.out.println("Four passengers have stories. One of them is lying.");
        System.out.println("Find the clues and identify who arranged Rowan's disappearance.");
        System.out.println("\nYou have " + movesLeft + " actions before the final station.");
    }

    private void showStatus() {
        System.out.println("\n------------------------------------------");
        System.out.println("Actions remaining: " + movesLeft + " | Clues found: " + clues.size()
                + " | Compartments visited: " + compartmentHistory.size());
        System.out.println("------------------------------------------");
    }

    private void exploreCompartment() {
        System.out.println("\nChoose a compartment:");
        for (int i = 0; i < compartments.size(); i++) {
            Compartment c = compartments.get(i);
            System.out.println((i + 1) + ". " + c.getName()
                    + (c.isSolved() ? " [SOLVED]" : ""));
        }
        System.out.print("> ");
        int index = readNumber();
        if (index < 1 || index > compartments.size()) {
            System.out.println("That compartment does not exist.");
            return;
        }

        Compartment compartment = compartments.get(index - 1);
        if (!compartmentHistory.contains(compartment.getName())) {
            compartmentHistory.add(compartment.getName());
        }

        System.out.println("\n" + compartment.getName());
        System.out.println(compartment.getDescription());
        System.out.println("1. Search for hidden objects");
        System.out.println("2. Solve the compartment riddle");
        System.out.println("3. Return");
        System.out.print("> ");
        String action = scanner.nextLine().trim();

        if (action.equals("1")) {
            System.out.println(compartment.search());
            movesLeft--;
        } else if (action.equals("2")) {
            if (compartment.isSolved()) {
                System.out.println("You already solved this riddle.");
                return;
            }
            System.out.println("RIDDLE: " + compartment.getPuzzleQuestion());
            System.out.print("Your answer: ");
            String answer = scanner.nextLine();
            movesLeft--;
            if (compartment.checkAnswer(answer)) {
                System.out.println("Correct. You found an important clue.");
                addClue(compartment.getClue());
            } else {
                System.out.println("That answer is incorrect. Keep investigating.");
            }
        } else if (!action.equals("3")) {
            System.out.println("Invalid action.");
        }
    }

    private void questionPassenger() {
        System.out.println("\nWho do you want to question?");
        for (int i = 0; i < passengers.size(); i++) {
            Passenger p = passengers.get(i);
            System.out.println((i + 1) + ". " + p.getName() + " - " + p.getRole());
        }
        System.out.print("> ");
        int index = readNumber();
        if (index < 1 || index > passengers.size()) {
            System.out.println("No passenger with that number.");
            return;
        }
        System.out.println(passengers.get(index - 1).question());
        movesLeft--;
    }

    private void addClue(String clue) {
        if (!clues.contains(clue)) {
            clues.add(clue);
            System.out.println("CLUE ADDED: " + clue);
        }
    }

    private void showClues() {
        System.out.println("\n--- YOUR CLUE NOTES ---");
        if (clues.isEmpty()) {
            System.out.println("No clues collected yet.");
            return;
        }
        for (int i = 0; i < clues.size(); i++) {
            System.out.println((i + 1) + ". " + clues.get(i));
        }
    }

    private void showHistory() {
        System.out.println("\n--- COMPARTMENT HISTORY ---");
        if (compartmentHistory.isEmpty()) {
            System.out.println("You have not explored any compartments.");
            return;
        }
        for (int i = 0; i < compartmentHistory.size(); i++) {
            System.out.println((i + 1) + ". " + compartmentHistory.get(i));
        }
    }

    private void makeAccusation() {
        System.out.println("\nWho arranged Rowan's disappearance?");
        for (int i = 0; i < passengers.size(); i++) {
            System.out.println((i + 1) + ". " + passengers.get(i).getName());
        }
        System.out.print("> ");
        int index = readNumber();
        if (index < 1 || index > passengers.size()) {
            System.out.println("Invalid passenger.");
            return;
        }

        movesLeft--;
        String suspect = passengers.get(index - 1).getName();
        if (suspect.equals("Mr. Finch") && clues.size() >= 3) {
            System.out.println("\nYou accuse Mr. Finch.");
            System.out.println("The clues fit. Finch used the sedative to incapacitate Rowan,");
            System.out.println("used the engine stop to move him, and hid the watch in the luggage room.");
            System.out.println("The conductor's log proves the route. Finch is arrested at the final station.");
            System.out.println("\nCASE CLOSED. You solved The Last Train!");
        } else if (suspect.equals("Mr. Finch")) {
            System.out.println("You suspect the right passenger, but your evidence is too weak.");
            System.out.println("Collect at least 3 clues before making the accusation.");
            return;
        } else {
            System.out.println("\nYour accusation is wrong. " + suspect + " is not responsible.");
            System.out.println("The real culprit slips away as the train reaches its final station.");
            System.out.println("CASE FAILED.");
        }
        gameOver = true;
    }

    private int readNumber() {
        String input = scanner.nextLine().trim();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            return -1;
        }
    }
}
