package ru.semen.checkers;

import ru.semen.checkers.entities.Board;
import ru.semen.checkers.service.Game;
import ru.semen.checkers.view.ConsolePrint;

public class Main {

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в шашки!");
        System.out.println("координаты задаются как X Y от 0 до 7");
        System.out.println("O - белые шашки");
        System.out.println("X - черные шашки");

        Board board = new Board();
        Game game = new Game(board);
        ConsolePrint consolePrint = new ConsolePrint(board);
        consolePrint.printBoard();

        while(true){
            game.play();
            consolePrint.printBoard();
        }

    }
}
