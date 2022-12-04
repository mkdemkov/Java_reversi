package org.example.Models;

import java.util.Scanner;

public class RealPlayer implements Player {

    private void printPossibleMoves(boolean[][] possibleMoves) {
        System.out.println("Возможные ходы:");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (possibleMoves[i][j]) {
                    System.out.println((i + 1) + " " + (j + 1));
                }
            }
        }
    }

    /**
     * Получить возможные ходы
     * @param first
     * @param second
     * @param gameDesk
     * @return
     */
    @Override
    public int[] getMove(boolean[][] first, boolean[][] second, Desk gameDesk) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите номер клетки. Например: 4 5");
        int row = 0, column = 0;
        try {
            boolean isRow = scanner.hasNextInt();
            if (!isRow) {
                throw new IllegalArgumentException("Некорректный выбор хода. Повторите попытку\n");
            }
            row = scanner.nextInt();
            boolean isColumn = scanner.hasNextInt();
            if (!isColumn) {
                throw new IllegalArgumentException("Некорректный выбор хода. Повторите попытку\n");
            }
            column = scanner.nextInt();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        boolean[][] possibleMoves = gameDesk.findPossibleMoves(second, first);
        if (!possibleMoves[row - 1][column - 1]) {
            throw new IllegalArgumentException("Некорректный выбор хода. Повторите попытку\n");
        }
        first[row - 1][column - 1] = true;
        return new int[]{row - 1, column - 1};
    }

    /**
     * Сдвинуться
     * @param first
     * @param second
     * @param gameDesk
     * @param isFirst
     * @return
     */
    @Override
    public boolean move(boolean[][] first, boolean[][] second, Desk gameDesk, boolean isFirst) {
        boolean[][] possibleMoves = gameDesk.findPossibleMoves(second, first);
        printPossibleMoves(possibleMoves);
        boolean flag = false;
        for (boolean[] row : possibleMoves) {
            if (flag) {
                break;
            }
            for (boolean el : row) {
                if (el) {
                    flag = true;
                    break;
                }
            }
        }
        if (!flag) {
            return false;
        }
        while (true) {
            try {
                int[] move = getMove(first, second, gameDesk);
                gameDesk.showDesk(second, first, move[0], move[1], isFirst);
                return true;
            } catch (IllegalArgumentException ex) {
                System.out.println("Некорректный выбор хода. Повторите попытку\n");
            }
        }
    }
}
