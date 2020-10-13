package ru.semen.checkers.entities;

import java.util.Objects;

public class Checker {

    private int x;
    private int y;
    private CheckerColor color;

    public Checker(int x, int y, CheckerColor color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Checker checker = (Checker) o;
        return x == checker.x &&
                y == checker.y &&
                color == checker.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, color);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public CheckerColor getColor() {
        return color;
    }

    public void setColor(CheckerColor color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Checker{" +
                "x=" + x +
                ", y=" + y +
                ", color='" + color + '\'' +
                '}';
    }
}
