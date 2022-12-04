package org.example.Models;

public class Reversi {

    private static int[] countPoints(Desk gameDesk) {
        int[] points = new int[]{0, 0};
        for (boolean[] row : gameDesk.xPlayer) {
            for (boolean el : row) {
                if (el) {
                    points[0]++;
                }
            }
        }
        if (points[0] > maxPoints) {
            maxPoints = points[0];
        }
        for (boolean[] row : gameDesk.oPlayer) {
            for (boolean el : row) {
                if (el) {
                    points[1]++;
                }
            }
        }
        return points;
    }

    private static void printWinner(Desk gameDesk) {
        int[] points = countPoints(gameDesk);
        if (points[0] > points[1]) {
            System.out.println("Выиграл первый игрок с " + points[0] + " очками");
        } else if (points[0] < points[1]) {
            System.out.println("Выиграл второй игрок с " + points[1] + " очками");
        } else {
            System.out.println("Ничья");
        }
    }

    public static int maxPoints = 0;

    /**
     * Игра с простым ботом
     */
    public static void easyGame() {
        Desk gameDesk = new Desk();
        gameDesk.showSecondDesk(gameDesk.oPlayer, gameDesk.xPlayer);
        RealPlayer player = new RealPlayer();
        EasyBot bot = new EasyBot();
        while (true) {
            boolean firstCheck = player.move(gameDesk.xPlayer, gameDesk.oPlayer, gameDesk, true);
            if (!firstCheck) {
                System.out.println("Невозможно ходить. Другой игрок ходит");
            }
            boolean secondCheck = bot.move(gameDesk.oPlayer, gameDesk.xPlayer, gameDesk, false);
            if (!secondCheck) {
                if (!firstCheck) {
                    System.out.println("Конец игры!");
                    printWinner(gameDesk);
                    return;
                }
                System.out.println("Невозможно ходить. Другой игрок ходит");
            }
        }
    }

    /**
     * Игра с продвинутым ботом
     */
    public static void coolBotGame() {
        Desk gameDesk = new Desk();
        gameDesk.showSecondDesk(gameDesk.oPlayer, gameDesk.xPlayer);
        RealPlayer player = new RealPlayer();
        ProfiBot bot = new ProfiBot();
        while (true) {
            boolean firstChech = player.move(gameDesk.xPlayer, gameDesk.oPlayer, gameDesk, true);
            if (!firstChech) {
                System.out.println("Невозможно ходить. Другой игрок ходит");
            }
            boolean secondCheck = bot.move(gameDesk.oPlayer, gameDesk.xPlayer, gameDesk, false);
            if (!secondCheck) {
                if (!firstChech) {
                    System.out.println("Конец игры!");
                    printWinner(gameDesk);
                    return;
                }
                System.out.println("Невозможно ходить. Другой игрок ходит");
            }
        }
    }

    /**
     * Игра против игрока
     */
    public static void pvpGame() {
        Desk gameDesk = new Desk();
        gameDesk.showSecondDesk(gameDesk.oPlayer, gameDesk.xPlayer);
        RealPlayer firstPlayer = new RealPlayer();
        RealPlayer secondPlayer = new RealPlayer();
        while (true) {
            boolean firstCheck = firstPlayer.move(gameDesk.xPlayer, gameDesk.oPlayer, gameDesk, true);
            if (!firstCheck) {
                System.out.println("Невозможно ходить. Другой игрок ходит");
            }
            boolean secondCheck = secondPlayer.move(gameDesk.oPlayer, gameDesk.xPlayer, gameDesk, false);
            if (!secondCheck) {
                if (!firstCheck) {
                    System.out.println("Конец игры!");
                    int[] points = countPoints(gameDesk);
                    if (points[1] > maxPoints) {
                        maxPoints = points[1];
                    }
                    printWinner(gameDesk);
                    return;
                }
                System.out.println("Невозможно ходить. Другой игрок ходит");
            }
        }
    }
}
