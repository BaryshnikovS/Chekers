package ru.semen.checkers.view;

import ru.semen.checkers.entities.Board;
import ru.semen.checkers.entities.Checker;
import ru.semen.checkers.entities.CheckerColor;

import java.util.Set;

//Класс котоырй в конструкторе принимает аргументы - доску с шашками
public class ConsolePrint {

    private Board board;

    public ConsolePrint(Board board) {
        this.board = board;
    }

    public void printBoard(){
        Set<Checker> checkers = board.getCheckers();
        System.out.println("___|_0_|_1_|_2_|_3_|_4_|_5_|_6_|_7_|");
        for (int i=0; i<8; i++){
            System.out.print("_"+i+"_|");
            for (int j=0; j<8; j++){
                if (checkers.contains(new Checker(j, i, CheckerColor.WHITE))){
                    System.out.print("_O_|");
                } else if (checkers.contains(new Checker(j, i, CheckerColor.BLACK))){
                    System.out.print("_X_|");
                } else {
                    System.out.print("___|");
                }
            }
            System.out.println("");
        }
    }

    public Board getBoard() {
        return board;
    }
}
