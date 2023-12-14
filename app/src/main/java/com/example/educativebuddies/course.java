package com.example.educativebuddies;

public class course {
    private String name;
    private String description;
    private int credits;

    public course(String name, String description, int credits) {
        this.name = name;
        this.description = description;
        this.credits = credits;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCredits() {
        return credits;
    }
}

