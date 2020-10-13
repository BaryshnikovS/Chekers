package ru.semen.checkers.entities;

public class AttackPosition extends MovePosition{

    private int attackX;
    private int attackY;

    public AttackPosition(int x, int y, int attackX, int attackY) {
        super(x, y); //через супер мы используем конструктор MovePosition
        this.attackX = attackX;
        this.attackY = attackY;
    }

    public int getAttackX() {
        return attackX;
    }

    public int getAttackY() {
        return attackY;
    }
}
