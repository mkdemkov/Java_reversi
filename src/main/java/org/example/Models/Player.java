package org.example.Models;

public interface Player {
    int[] getMove(boolean[][] first, boolean[][] second, Desk gameDesk);

    boolean move(boolean[][] first, boolean[][] second, Desk gameDesk, boolean isFirst);
}
