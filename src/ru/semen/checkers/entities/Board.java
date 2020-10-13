package ru.semen.checkers.entities;

import java.util.HashSet;
import java.util.Set;

public class Board {

    //Множество шашек
    private Set<Checker> checkers;

    //Создаем множество шашек и заполняем это множество
    public Board() {
        checkers = new HashSet<>();
        initWhiteChecker();
        initBlackChecker();
    }

    public void initWhiteChecker(){
        checkers.add(new Checker(0, 1, CheckerColor.WHITE));
        checkers.add(new Checker(1, 0, CheckerColor.WHITE));
        checkers.add(new Checker(1, 2, CheckerColor.WHITE));
        checkers.add(new Checker(2, 1, CheckerColor.WHITE));
        checkers.add(new Checker(3, 0, CheckerColor.WHITE));
        checkers.add(new Checker(3, 2, CheckerColor.WHITE));
        checkers.add(new Checker(4, 1, CheckerColor.WHITE));
        checkers.add(new Checker(5, 0, CheckerColor.WHITE));
        checkers.add(new Checker(5, 2, CheckerColor.WHITE));
        checkers.add(new Checker(6, 1, CheckerColor.WHITE));
        checkers.add(new Checker(7, 0, CheckerColor.WHITE));
        checkers.add(new Checker(7, 2, CheckerColor.WHITE));
    }

    public void initBlackChecker(){
        checkers.add(new Checker(0,5, CheckerColor.BLACK));
        checkers.add(new Checker(0,7, CheckerColor.BLACK));
        checkers.add(new Checker(1,6, CheckerColor.BLACK));
        checkers.add(new Checker(2,5, CheckerColor.BLACK));
        checkers.add(new Checker(2,7, CheckerColor.BLACK));
        checkers.add(new Checker(3,6, CheckerColor.BLACK));
        checkers.add(new Checker(4,5, CheckerColor.BLACK));
        checkers.add(new Checker(4,7, CheckerColor.BLACK));
        checkers.add(new Checker(5,6, CheckerColor.BLACK));
        checkers.add(new Checker(6,5, CheckerColor.BLACK));
        checkers.add(new Checker(6,7, CheckerColor.BLACK));
        checkers.add(new Checker(7,6, CheckerColor.BLACK));
    }

    public Set<Checker> getCheckers(){
        return checkers;
    }
}
