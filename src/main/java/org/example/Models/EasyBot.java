package org.example.Models;

public class EasyBot implements Player {

    /**
     * Возможные варианты
     * @param first
     * @param second
     * @param gameDesk
     * @return
     */
    @Override
    public int[] getMove(boolean[][] first, boolean[][] second, Desk gameDesk) {
        boolean[][] possibleMoves = gameDesk.findPossibleMoves(second, first);
        double[][] weight = new double[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (possibleMoves[i][j]) {
                    double ss = 0;
                    if (i == 0 || i == 7) {
                        if (j == 0 || j == 7) {
                            ss = 0.8;
                        } else {
                            ss = 0.4;
                        }
                    } else if (j == 0 || j == 7) {
                        if (i == 0 || i == 7) {
                            ss = 0.8;
                        } else {
                            ss = 0.4;
                        }
                    }
                    boolean[][] newCells = gameDesk.changeCells(i, j, first, second);
                    double si = 0;
                    for (int i1 = 0; i1 < 8; i1++) {
                        for (int j1 = 0; j1 < 8; j1++) {
                            if (newCells[i1][j1]) {
                                if (i1 == 0 || i1 == 7 || j1 == 0 || j1 == 7) {
                                    si += 2;
                                } else {
                                    si += 1;
                                }
                            }
                        }
                    }
                    weight[i][j] = si + ss;
                } else {
                    weight[i][j] = -100;
                }
            }
        }
        double[] move = new double[]{-1, -1, -1};
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (weight[i][j] > move[0]) {
                    move[0] = weight[i][j];
                    move[1] = i;
                    move[2] = j;
                }
            }
        }
        first[(int) move[1]][(int) move[2]] = true;
        return new int[]{(int) move[1], (int) move[2]};
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
        int[] move = getMove(first, second, gameDesk);
        gameDesk.showDesk(second, first, move[0], move[1], isFirst);
        return true;
    }
}
