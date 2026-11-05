package com.lasttrain;

public class Compartment {
    private final String name;
    private final String description;
    private final String hiddenObject;
    private final String puzzleQuestion;
    private final String puzzleAnswer;
    private final String clue;
    private boolean searched;
    private boolean solved;

    public Compartment(String name, String description, String hiddenObject,
                       String puzzleQuestion, String puzzleAnswer, String clue) {
        this.name = name;
        this.description = description;
        this.hiddenObject = hiddenObject;
        this.puzzleQuestion = puzzleQuestion;
        this.puzzleAnswer = puzzleAnswer;
        this.clue = clue;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPuzzleQuestion() {
        return puzzleQuestion;
    }

    public String search() {
        if (searched) {
            return "You have already searched here. The hidden object was: " + hiddenObject;
        }
        searched = true;
        return "You search carefully and find: " + hiddenObject;
    }

    public boolean checkAnswer(String answer) {
        if (answer == null) return false;
        String cleaned = answer.trim().toLowerCase();
        boolean correct = cleaned.equals(puzzleAnswer.toLowerCase());
        if (correct) solved = true;
        return correct;
    }

    public boolean isSolved() {
        return solved;
    }

    public String getClue() {
        return clue;
    }
}
