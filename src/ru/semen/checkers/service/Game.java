package ru.semen.checkers.service;

import ru.semen.checkers.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Game {

    private boolean isWhiteMove; //в данный ход должны играть белые(true) или чёрные(false)
    private Board board; //достка с шашками
    private Scanner in; //поток ввода через консоль

    public Game(Board board) {
        this.isWhiteMove = true; //устанавливает флажок, что первые ходят белые
        this.board = board;
        this.in = new Scanner(System.in);
    }

    public void play() {
        howMove();
        Checker checker = getPositionChecker();
        getPositionMove(checker);
        nextMove();
    }

    private void howMove() {
        System.out.println(isWhiteMove ? "Ходят белые" : "Ходят чёрные");
    }

    private void nextMove() {
        isWhiteMove = !isWhiteMove;
    }

    //Вводим координаты шашки, которой будем ходить, дальше происходят проверки попадают ли координаты в диапазон доски
    //
    private Checker getPositionChecker() {
        System.out.print("Введите координату шашки: ");
        int x = in.nextInt();
        int y = in.nextInt();
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            System.out.println("Указан не верный диапазон, попробуйте ещё раз");
            return getPositionChecker();
        }
        CheckerColor checkerColor = isWhiteMove ? CheckerColor.WHITE : CheckerColor.BLACK; //устанавливаем цвет шашки
        Checker checker = new Checker(x, y, checkerColor);
        Set<Checker> checkers = board.getCheckers();
        if (checkers.contains(checker)) { //ищем нашу шашку
            checkers.remove(checker); //удаляем ее из множества
            return checker;
        } else {
            // если  не смогли найти, пропуем еще раз
            System.out.println("В данной клетке нет шашки " + checkerColor.getColor() + " цвета, попробуйте еще раз");
            return getPositionChecker();
        }
    }

    //метод на осуществления хода шашки
    private void getPositionMove(Checker checker) {
        boolean isMove = false; //Получилось ли совершить ход
        System.out.print("Ввидите координату хода: ");
        int x = in.nextInt();
        int y = in.nextInt();
        while (!isInRange(x, y)){
            System.out.print("Указан не верный диапазон, попробуйте ещё раз: ");
            x = in.nextInt();
            y = in.nextInt();
        }
        //хранит значение x, y нашего хода
        MovePosition newPosition = new MovePosition(x, y);

        //это список возможных позиций, которые может выполнить наша шашка
        List<MovePosition> movePositions = new ArrayList<>();
        Set<Checker> checkers = board.getCheckers();

        int x1 = checker.getX() - 1;
        int y1 = checker.getY() - 1;
        //проверка левой верхней диагонали
        if (isInRange(x1, y1)) { //попадает ли сашка в диапазон значений x [0;7] y [0;7]
            //для поиска клетки противника ищем в множестве шашек шашку с координатами x1,y1
            if (checkers.contains(new Checker(x1, y1, revertColor(checker.getColor())))) { //содержит ли клетка шашку противника
                if (isInRange(x1 - 1, y1 - 1)) { //входит ли в диапазон следующая клетка за шашкой противника (сможем ли мы съесть шашку противника)
                    if (!checkers.contains(new Checker(x1 - 1, y1 - 1, revertColor(checker.getColor()))) &&
                            !checkers.contains(new Checker(x1 - 1, y1 - 1, checker.getColor()))) { //проверяем пустая ли клетка за клеткой противника
                        movePositions.add(new AttackPosition(x1 - 1, y1 - 1, x1, y1));// передаем координаты клетки в которую мы поставим шашку и
                        // координаты клетки где находится шашка противника
                    }
                }
            } else if (!checkers.contains(new Checker(x1, y1, checker.getColor()))) { //содержит ли клетка нашу шашку
                movePositions.add(new MovePosition(x1, y1)); //добавляем позицию для возможного хода
            }
        }

        int x2 = checker.getX() + 1;
        int y2 = checker.getY() - 1;
        //проверка правой верхней диагонали
        if (isInRange(x2, y2)) {
            if (checkers.contains(new Checker(x2, y2, revertColor(checker.getColor())))) {
                if (isInRange(x2 + 1, y2 - 1)) {
                    if (!checkers.contains(new Checker(x2 + 1, y2 - 1, revertColor(checker.getColor()))) &&
                            !checkers.contains(new Checker(x2 + 1, y2 - 1, checker.getColor()))) {
                        movePositions.add(new AttackPosition(x2 + 1, y2 - 1, x2, y2));
                    }
                }
            } else if (!checkers.contains(new Checker(x2, y2, checker.getColor()))) {
                movePositions.add(new MovePosition(x2, y2));
            }
        }

        int x3 = checker.getX() + 1;
        int y3 = checker.getY() + 1;
        //проверка правой нижней диагонали
        if (isInRange(x3, y3)) {
            if (checkers.contains(new Checker(x3, y3, revertColor(checker.getColor())))) {
                if (isInRange(x3 + 1, y3 + 1)) {
                    if (!checkers.contains(new Checker(x3 + 1, y3 + 1, revertColor(checker.getColor()))) &&
                            !checkers.contains(new Checker(x3 + 1, y3 + 1, checker.getColor()))) {
                        movePositions.add(new AttackPosition(x3 + 1, y3 + 1, x3, y3));
                    }
                }
            } else if (!checkers.contains(new Checker(x3, y3, checker.getColor()))) {
                movePositions.add(new MovePosition(x3, y3));
            }
        }

        int x4 = checker.getX() - 1;
        int y4 = checker.getY() + 1;
        //проверка левой нижней диагонали
        if (isInRange(x4, y4)) {
            if (checkers.contains(new Checker(x4, y4, revertColor(checker.getColor())))) {
                if (isInRange(x4 - 1, y4 + 1)) {
                    if (!checkers.contains(new Checker(x4 - 1, y4 + 1, revertColor(checker.getColor()))) &&
                            !checkers.contains(new Checker(x4 - 1, y4 + 1, checker.getColor()))) {
                        movePositions.add(new AttackPosition(x4 - 1, y4 + 1, x4, y4));
                    }
                }
            } else if (!checkers.contains(new Checker(x4, y4, checker.getColor()))) {
                movePositions.add(new MovePosition(x4, y4));
            }
        }

       for(MovePosition mp : movePositions){ //через цикл мы проверяем есть ли среди возможных позиций, указанная позиция
           if (mp.equals(newPosition)){ //проверяем равенство позиции с позицией из списка
               if (mp instanceof AttackPosition){
                   AttackPosition amp = (AttackPosition) mp; //объект mp, который является элементом списка преобразуем к классу AttackPosition
                   board.getCheckers().remove(new Checker(amp.getAttackX(), amp.getAttackY(), revertColor(checker.getColor()))); //удаляем съеденную шашку
                   board.getCheckers().add(new Checker(newPosition.getX(), newPosition.getY(), checker.getColor())); //добавляем шашку в множество, на новую позицию, так как до этого мы ее удалили
               } else {
                   board.getCheckers().add(new Checker(newPosition.getX(), newPosition.getY(), checker.getColor())); //добавляем шашку в множество , на новую позицию
               }
               isMove = true;
           }
       }

       if (!isMove) {
           System.out.println("Не возможно сделать такой ход, попробуйте снова");
           getPositionMove(checker);
       }
    }

    private boolean isInRange(int x, int y) { //попадают ли значения координат в поле в диапазон значений от 0 до 7 по x и y (все наше игровое поле)
        return x > -1 && x < 8 && y > -1 && y < 8;
    }

    private CheckerColor revertColor(CheckerColor checkerColor) { //передаем цвет, если передали белый, получим черный и наоборот
        if (checkerColor.equals(CheckerColor.BLACK)) return CheckerColor.WHITE;
        else return CheckerColor.BLACK;
    }
}

