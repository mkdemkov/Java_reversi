package org.example.Static;

import org.example.Models.Reversi;

import java.util.Scanner;

public class GameSetup {
    /**
     * Private constructor that not allows to create objects
     */
    private GameSetup() {
        throw new RuntimeException("You can't create an instance of this class");
    }

    /**
     * Обработка ввода
     */
    public static void parseInput() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        do {
            input = scanner.nextLine();
            switch (input) {
                case "0":
                    break;
                case "1":
                    Reversi.easyGame();
                    break;
                case "2":
                    Reversi.coolBotGame();
                    break;
                case "3":
                    Reversi.pvpGame();
                    break;
                case "4":
                    System.out.println("Максимальное число очков: " + Reversi.maxPoints);
                    break;
                default:
                    System.out.println(Constants.BAD_INPUT);
                    break;
            }
        } while (!"0".equals(input));
    }

    public static void showHelloMessage() {
        System.out.println(Constants.WELCOME_MESSAGE);
    }

}
