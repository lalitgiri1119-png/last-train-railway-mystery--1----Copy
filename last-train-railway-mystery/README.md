# The Last Train – Railway Mystery

A text-based Java mystery adventure. Explore train compartments, inspect objects, solve riddles, question passengers, collect clues, and uncover the truth before the train reaches its final station.

## Features

- Five connected train compartments with different puzzles.
- Hidden-object searches and riddles.
- Passengers with secrets and dialogue.
- A final deduction based on the clues you collect.
- Uses the required Java collections:
  - `ArrayList<Passenger>` stores passengers.
  - `List<String>` stores discovered clues.
  - `LinkedList<String>` stores compartment visit history.

## Requirements

- Java JDK 17 or later
- A terminal or IDE such as IntelliJ IDEA, Eclipse, or VS Code

## Run the game

Open a terminal in the project folder.

### Windows

```bash
javac -d out src/main/java/com/lasttrain/*.java
java -cp out com.lasttrain.Main
```

### macOS / Linux

```bash
javac -d out src/main/java/com/lasttrain/*.java
java -cp out com.lasttrain.Main
```

## How to play

1. Choose a compartment to enter.
2. Inspect the compartment for hidden objects and clues.
3. Talk to passengers when available.
4. Solve each compartment's puzzle.
5. Review your clues and compartment history.
6. Make your final accusation before the train reaches its destination.

Some actions unlock only after you collect the right clue. Read each message carefully.

## Project structure

```text
last-train-railway-mystery/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── lasttrain/
│                   ├── Main.java
│                   ├── Game.java
│                   ├── Passenger.java
│                   └── Compartment.java
├── .gitignore
└── README.md
```

## GitHub upload

Create a new **public** repository on GitHub named `last-train-railway-mystery`. Do not add a README, license, or `.gitignore` on GitHub because this project already includes them.

Then run these commands from this project folder, replacing `YOUR-USERNAME` with your GitHub username:

```bash
git init
git add .
git commit -m "Add The Last Train railway mystery game"
git branch -M main
git remote add origin https://github.com/YOUR-USERNAME/last-train-railway-mystery.git
git push -u origin main
```

Your repository URL will be:

`https://github.com/YOUR-USERNAME/last-train-railway-mystery`

If Git asks you to sign in, complete the browser authentication prompt.
