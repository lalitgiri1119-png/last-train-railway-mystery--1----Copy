package com.lasttrain;

public class Passenger {
    private final String name;
    private final String role;
    private final String secret;
    private boolean questioned;

    public Passenger(String name, String role, String secret) {
        this.name = name;
        this.role = role;
        this.secret = secret;
        this.questioned = false;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String question() {
        if (questioned) {
            return name + " says: \"I've told you everything I know. Think about the clues.\"";
        }
        questioned = true;
        return name + " (" + role + "): " + secret;
    }
}
