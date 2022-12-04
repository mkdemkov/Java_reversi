package org.example.Static;

public class Constants {
    /**
     * Private constructor that not allows to create objects
     */
    private Constants() {
        throw new RuntimeException("You can't create an instance of this class");
    }

    public static final String WELCOME_MESSAGE = "Консольная игра \"Models.Reversi\".\n" +
            "Чтобы играть против легкого бота, введите \"1\"\n" +
            "Чтобы играть против сложного бота, введите \"2\"\n" +
            "Чтобы играть против игрока, введите \"3\"\n" +
            "Чтобы вывести лучший результат, введите \"4\"\n" +
            "Чтобы выйти, введите \"0\"";

    public static final String BAD_INPUT = "Введите что-то из доступных команд!";
}
