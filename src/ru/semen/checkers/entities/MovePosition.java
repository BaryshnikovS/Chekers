package ru.semen.checkers.entities;

public class MovePosition {

    private int x;
    private int y;

    public MovePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override //используется для сравнения элементов
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovePosition)) return false;
        MovePosition that = (MovePosition) o;
        return x == that.x &&
                y == that.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
