package ru.geekbrains.Homework4;

import java.util.Random;
import java.util.Scanner;

public class Sample01 {

    static final char DOT_HUMAN = 'X';
    static final char DOT_AI = '0';
    static final char DOT_EMPTY = '•';
    static final Scanner scanner = new Scanner(System.in);
    static final Random random = new Random();
    static char[][] field; // Двумерный массив, хранит текущее состояние игрового поля
    static int fieldSizeX;
    static int fieldSizeY;
    static final int WIN_COUNT = 3;


    /**
     * Инициализация объектов игры
     */
    static void initialize(){
        // Устанавливаем размерность игрового поля
        fieldSizeX = 3;
        fieldSizeY = 3;
        // Инициализация массива, описывающего игровое поле
        field = new char[fieldSizeX][fieldSizeY];

        for (int x = 0; x < fieldSizeX; x++){
            for (int y = 0; y < fieldSizeY; y++){
                field[x][y] = DOT_EMPTY;
            }
        }
    }


    /**
     * Отрисовка игрового поля
     */
    static void printField(){
        System.out.print("+");
        for (int i = 0; i < fieldSizeX*2 + 1; i++){
            System.out.print(i % 2 == 0 ? "-" : i/2 + 1);
        }
        System.out.println();
        for (int x = 0; x < fieldSizeX; x++){
            System.out.print(x + 1 + "|");
            for (int y = 0; y < fieldSizeY; y++){
                System.out.print(field[x][y] + "|");
            }
            System.out.println();
        }
        for(int i = 0; i <= fieldSizeX*2 + 1; i++)
            System.out.print("-");
        System.out.println();
    }

    /**
     * Обработка хода игрока (человек)
     */
    static void humanTurm(){
        int x, y;
        do {
            System.out.println("Введите координаты хода X и Y (от 1 до 3)\n через пробел >>> ");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        }
        while (!isCellValid(x, y) || !isCellEmpty(x, y));
        field[x][y] = DOT_HUMAN;
    }

    static void aiTurn(){
        int x, y;
        do {
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        }
        while (!isCellEmpty(x, y));
        field[x][y] = DOT_AI;
    }

    /**
     * Проверка, ячейка является пустой (DOT_EMPTY)
     * @param x координата ячейки
     * @param y координата ячейки
     * @return результат проверки
     */
    static boolean isCellEmpty(int x, int y){
        return field[x][y] == DOT_EMPTY;
    }

    /**
     * Проверка корректности ввода (координаты хода не должны
     * превышать размерность массива, описывающего игровое поле)
     * @param x координата ячейки
     * @param y координата ячейки
     * @return результат проверки
     */
    static boolean isCellValid(int x, int y){
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }
/*
    /**
     * Проверка победы
     * @param c фишка игрока (компьютер/человек)
     * @return результат проверки победы
     /
    //TODO: Переработать метод в домашнем задании
    static boolean checkWin(char c){
        // Проверка по горизонталям
        if (field[0][0] == c && field[0][1] == c && field[0][2] == c) return true;
        if (field[1][0] == c && field[1][1] == c && field[1][2] == c) return true;
        if (field[2][0] == c && field[2][1] == c && field[2][2] == c) return true;

        // Проверка по вертикалям
        if (field[0][0] == c && field[1][0] == c && field[2][0] == c) return true;
        if (field[0][1] == c && field[1][1] == c && field[2][1] == c) return true;
        if (field[0][2] == c && field[1][2] == c && field[2][2] == c) return true;

        // Проверка по диагоналям
        if (field[0][0] == c && field[1][1] == c && field[2][2] == c) return true;
        if (field[0][2] == c && field[1][1] == c && field[2][0] == c) return true;

        return false;
    }*/
            static boolean checkDotHorisontal (char c, int win) {

                for (int i = 0; i < field.length; i++) {
                    int counter = 0;
                    for (int j = 0; j < field.length; j++) {

                        if (field[i][j] != c)
                            break;
                        counter++;
                        if (counter == win) return true;

                    }
                }
                return false;
            }


            static boolean checkDotVertical (char c, int win){

                for (int i = 0; i < field.length; i++) {
                int counter = 0;
                    for (int j = 0; j < field.length; j++) {

                        if (field[j][i] != c)
                        break;
                        counter++;
                        if (counter == win) return true;

                    }
                }
                return false;
            }

    static boolean checkDotDiagonal (char c, int win){

        for (int i = 0; i < field.length; i++) {
            int counter = 0;
            for (int j = 0; j < field.length; j++) {

                if (field[j][j] != c)
                    break;
                counter++;
                if (counter == win) return true;

            }
        }
        return false;
    }
    static boolean checkDotReverseDiagonal (char c, int win){

        for (int i = 0; i < field.length; i++) {
            int counter = 0;
            for (int j = 0; j < field.length; j++) {

                if (field[field.length - 1 - 1 j][j] != c)
                    break;
                counter++;
                if (counter == win) return true;
            }
        }
        return false;
    }


    /**
     * Проверка на ничью (все поле заполнено фишками игрока (человек или компьютер)
     * @return результат проверки
     */
    static boolean checkDraw(){
        for (int x = 0; x < fieldSizeX; x++){
            for (int y = 0; y < fieldSizeY; y++){
                if (field[x][y] ==DOT_EMPTY)
                    return false;
            }
        }
        return true;
    }

    /**
     * Метод проверки состояния игры
     * @param c фишка игрока
     * @param s вспомогательная строка
     * @return результат проверки состояния игры
     */
    static boolean gameChecks(char c, String s){
        if (checkDotHorisontal(c, WIN_COUNT) || checkDotVertical(c, WIN_COUNT) || checkDotDiagonal(c,WIN_COUNT) || checkDotReverseDiagonal(c,WIN_COUNT))  {
            System.out.println(s);
            return true;
        }
        if (checkDraw()){
            System.out.println("Ничья");
            return true;
        }
        return false; // Продолжим игру
    }


    public static void main(String[] args) {
        while (true){
            initialize();
            printField();
            while (true){
                humanTurm(); // Обработка хода игрока (человека)
                printField();
                if (gameChecks(DOT_HUMAN, "Вы победили!"))
                    break;
                aiTurn(); // Обработка хода игрока (компьютера)
                printField();
                if (gameChecks(DOT_AI, "Победил компьютер!"))
                    break;
            }
            System.out.println("Желаете сыграть еще раз? (Y - да)");
            if (!scanner.next().equalsIgnoreCase("Y"))
                break;

        }

    }

}
