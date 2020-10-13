package ru.semen.checkers.entities;

public enum CheckerColor {
    WHITE("белого"),
    BLACK("чёрного");

    private final String color;

    CheckerColor(String color){
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
