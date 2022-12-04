package org.example.Models;

public class Desk {

    private boolean moveDown(int i, int j, boolean[][] first, boolean[][] second) {
        if (first[i + 1][j]) {
            for (int k = i + 2; k < 8; k++) {
                if (!first[k][j] && !second[k][j]) {
                    return false;
                }
                if (second[k][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean moveAhead(int i, int j, boolean[][] first, boolean[][] second) {
        if (first[i][j + 1]) {
            for (int k = j + 2; k < 8; k++) {
                if (!first[i][k] && !second[i][k]) {
                    return false;
                }
                if (second[i][k]) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean moveUp(int i, int j, boolean[][] first, boolean[][] second) {
        if (first[i - 1][j]) {
            for (int k = i - 2; k != -1; k--) {
                if (!first[k][j] && !second[k][j]) {
                    return false;
                }
                if (second[k][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean moveBack(int i, int j, boolean[][] first, boolean[][] second) {
        if (first[i][j - 1]) {
            for (int k = j - 2; k != -1; k--) {
                if (!first[i][k] && !second[i][k]) {
                    return false;
                }
                if (second[i][k]) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean moveDownBack(int i, int j, boolean[][] first, boolean[][] second) {
        int copy = j - 1;
        if (first[i + 1][j - 1]) {
            for (int k = i + 2; k < 8; k++) {
                if (copy - 1 < 0) {
                    continue;
                }
                copy--;
                if (!first[k][copy] && !second[k][copy]) {
                    return false;
                }
                if (second[k][copy]) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean moveDownAhead(int i, int j, boolean[][] first, boolean[][] second) {
        int copy = j + 1;
        if (first[i + 1][j + 1]) {
            for (int k = i + 2; k < 8; k++) {
                if (copy + 1 > 7) {
                    continue;
                }
                copy++;
                if (!first[k][copy] && !second[k][copy]) {
                    return false;
                }
                if (second[k][copy]) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean moveUpAhead(int i, int j, boolean[][] first, boolean[][] second) {
        int copy = j + 1;
        if (first[i - 1][j + 1]) {
            for (int k = i - 2; k != -1; k--) {
                if (copy + 1 > 7) {
                    continue;
                }
                copy++;
                if (!first[k][copy] && !second[k][copy]) {
                    return false;
                }
                if (second[k][copy]) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean moveUpBack(int i, int j, boolean[][] first, boolean[][] second) {
        int copy = j - 1;
        if (first[i - 1][j - 1]) {
            for (int k = i - 2; k != -1; k--) {
                if (copy - 1 < 0) {
                    continue;
                }
                copy--;
                if (!first[k][copy] && !second[k][copy]) {
                    return false;
                }
                if (second[k][copy]) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean[][] xPlayer = new boolean[8][8]; // первый игрок
    public boolean[][] oPlayer = new boolean[8][8]; // второй игрок

    /**
     * Constructor that configures beginning positions
     */
    public Desk() {
        xPlayer[3][4] = true;
        xPlayer[4][3] = true;
        oPlayer[3][3] = true;
        oPlayer[4][4] = true;
    }

    /**
     * Отображение доски
     * @param first
     * @param second
     * @param row
     * @param column
     * @param isFirst
     */
    public void showDesk(boolean[][] first, boolean[][] second, int row, int column, boolean isFirst) {
        boolean[][] changer = changeCells(row, column, first, second);
        first = changeFirst(first, changer);
        second = changeSecond(second, changer);
        if (isFirst) {
            showFirstDesk(first, second);
        } else {
            showSecondDesk(second, first);
        }
    }

    /**
     * Меняем первого игрока
     * @param arr
     * @param changer
     * @return
     */
    public boolean[][] changeFirst(boolean[][] arr, boolean[][] changer) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (changer[i][j]) {
                    arr[i][j] = false;
                }
            }
        }
        return arr;
    }

    /**
     * Меняем второго игрока
     * @param arr
     * @param changer
     * @return
     */
    public boolean[][] changeSecond(boolean[][] arr, boolean[][] changer) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (changer[i][j]) {
                    arr[i][j] = true;
                }
            }
        }
        return arr;
    }

    /**
     * Отобразить первого
     * @param first
     * @param second
     */
    public void showFirstDesk(boolean[][] first, boolean[][] second) {
        oPlayer = first;
        xPlayer = second;
        boolean[][] possibleMoves = findPossibleMoves(second, first);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (possibleMoves[i][j]) {
                    System.out.print("  ◌  ");
                } else if (second[i][j]) {
                    System.out.print("  ●  "); // mb swap
                } else if (first[i][j]) {
                    System.out.print("  ○  ");
                } else {
                    System.out.print("  -  ");
                }
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    /**
     * Отобразить второго
     * @param first
     * @param second
     */
    public void showSecondDesk(boolean[][] first, boolean[][] second) {
        oPlayer = first;
        xPlayer = second;
        boolean[][] possibleMoves = findPossibleMoves(first, second);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (possibleMoves[i][j]) {
                    System.out.print("  ◌  ");
                } else if (second[i][j]) {
                    System.out.print("  ●  "); // mb swap
                } else if (first[i][j]) {
                    System.out.print("  ○  ");
                } else {
                    System.out.print("  -  ");
                }
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    /**
     * Поменять клетки
     * @param row
     * @param column
     * @param first
     * @param second
     * @return
     */
    public boolean[][] changeCells(int row, int column, boolean[][] first, boolean[][] second) {
        boolean[][] forChange = new boolean[8][8];
        boolean[][] copy;
        if (row != 7) {
            copy = changeDown(row, column, first, second);
            forChange = addCells(copy, forChange);
            if (column != 0) {
                copy = changeDownBack(row, column, first, second);
                forChange = addCells(copy, forChange);
            }
        }
        if (column != 7) {
            copy = changeAhead(row, column, first, second);
            forChange = addCells(copy, forChange);
            if (row != 7) {
                copy = changeDownAhead(row, column, first, second);
                forChange = addCells(copy, forChange);
            }
        }
        if (row != 0) {
            copy = changeUp(row, column, first, second);
            forChange = addCells(copy, forChange);
            if (column != 7) {
                copy = changeUpAhead(row, column, first, second);
                forChange = addCells(copy, forChange);
            }
        }
        if (column != 0) {
            copy = changeBack(row, column, first, second);
            forChange = addCells(copy, forChange);
            if (row != 0) {
                copy = changeUpBack(row, column, first, second);
                forChange = addCells(copy, forChange);
            }
        }
        return forChange;
    }

    /**
     * Добавить клетки
     * @param first
     * @param second
     * @return
     */
    public boolean[][] addCells(boolean[][] first, boolean[][] second) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (first[i][j]) {
                    second[i][j] = true;
                }
            }
        }
        return second;
    }

    public boolean[][] changeDown(int row, int column, boolean[][] first, boolean[][] second) {
        boolean[][] res = new boolean[8][8];
        if (first[row + 1][column]) {
            for (int k = row + 2; k < 8; k++) {
                if (second[k][column]) {
                    for (int m = row + 1; m < k; m++) {
                        res[m][column] = true;
                    }
                    return res;
                }
            }
        }
        return res;
    }

    public boolean[][] changeAhead(int row, int column, boolean[][] first, boolean[][] second) {
        boolean[][] res = new boolean[8][8];
        if (first[row][column + 1]) {
            for (int k = column + 2; k < 8; k++) {
                if (second[row][k]) {
                    for (int m = column + 1; m < k; m++) {
                        res[row][m] = true;
                    }
                    return res;
                }
            }
        }
        return res;
    }

    public boolean[][] changeUp(int row, int column, boolean[][] first, boolean[][] second) {
        boolean[][] res = new boolean[8][8];
        if (first[row - 1][column]) {
            for (int k = row - 2; k != -1; k--) {
                if (second[k][column]) {
                    for (int m = row - 1; m != k; m--) {
                        res[m][column] = true;
                    }
                    return res;
                }
            }
        }
        return res;
    }

    public boolean[][] changeBack(int row, int column, boolean[][] first, boolean[][] second) {
        boolean[][] res = new boolean[8][8];
        if (first[row][column - 1]) {
            for (int k = column - 2; k != -1; k--) {
                if (second[row][k]) {
                    for (int m = column - 1; m != k; m--) {
                        res[row][m] = true;
                    }
                    return res;
                }
            }
        }
        return res;
    }

    public boolean[][] changeDownBack(int row, int column, boolean[][] first, boolean[][] second) {
        boolean[][] res = new boolean[8][8];
        int copy = column - 1;
        if (first[row + 1][column - 1]) {
            for (int k = row + 2; k < 8; k++) {
                if (copy - 1 < 0) {
                    continue;
                }
                copy--;
                if (second[k][copy]) {
                    int copy2 = column - 1;
                    for (int m = row + 1; m < k; m++) {
                        res[m][copy2] = true;
                        copy2--;
                    }
                    return res;
                }
            }
        }
        return res;
    }

    public boolean[][] changeDownAhead(int row, int column, boolean[][] first, boolean[][] second) {
        boolean[][] res = new boolean[8][8];
        int copy = column + 1;
        if (first[row + 1][column + 1]) {
            for (int k = row + 2; k < 8; k++) {
                if (copy + 1 > 7) {
                    continue;
                }
                copy++;
                if (second[k][copy]) {
                    int copy2 = column + 1;
                    for (int m = row + 1; m < k; m++) {
                        res[m][copy2] = true;
                        copy2++;
                    }
                    return res;
                }
            }
        }
        return res;
    }

    public boolean[][] changeUpAhead(int row, int column, boolean[][] first, boolean[][] second) {
        boolean[][] res = new boolean[8][8];
        int copy = column + 1;
        if (first[row-1][column+1]) {
            for (int k = row-2; k!=-1; k--) {
                if (copy + 1 > 7) {
                    continue;
                }
                copy++;
                if (second[k][copy]) {
                    int copy2 = column + 1;
                    for (int m = row - 1; m != k; m--) {
                        res[m][copy2] = true;
                        copy2++;
                    }
                    return res;
                }
            }
        }
        return res;
    }

    public boolean[][] changeUpBack(int row, int column, boolean[][] first, boolean[][] second) {
        int copy = column - 1;
        boolean[][] res = new boolean[8][8];
        if (first[row-1][column-1]) {
            for (int k = row - 2; k != -1; k--) {
                if (copy - 1 < 0) {
                    continue;
                }
                copy--;
                if (second[k][copy]) {
                    int copy2 = column - 1;
                    for (int m = row - 1; m != k; m--) {
                        res[m][copy2] = true;
                        copy2--;
                    }
                    return res;
                }
            }
        }
        return res;
    }

    public boolean[][] findPossibleMoves(boolean[][] first, boolean[][] second) {
        boolean[][] possibleMoves = new boolean[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!first[i][j] && !second[i][j]) {
                    if (i != 7) {
                        if (moveDown(i, j, first, second)) {
                            possibleMoves[i][j] = true;
                        }
                        if (j != 0) {
                            if (moveDownBack(i, j, first, second)) {
                                possibleMoves[i][j] = true;
                            }
                        }
                    }
                    if (j != 7) {
                        if (moveAhead(i, j, first, second)) {
                            possibleMoves[i][j] = true;
                        }
                        if (i != 7) {
                            if (moveDownAhead(i, j, first, second)) {
                                possibleMoves[i][j] = true;
                            }
                        }
                    }
                    if (i != 0) {
                        if (moveUp(i, j, first, second)) {
                            possibleMoves[i][j] = true;
                        }
                        if (j != 7) {
                            if (moveUpAhead(i, j, first, second)) {
                                possibleMoves[i][j] = true;
                            }
                        }
                    }
                    if (j != 0) {
                        if (moveBack(i, j, first, second)) {
                            possibleMoves[i][j] = true;
                        }
                        if (i != 0) {
                            if (moveUpBack(i, j, first, second)) {
                                possibleMoves[i][j] = true;
                            }
                        }
                    }
                }
            }
        }
        return possibleMoves;
    }
}
