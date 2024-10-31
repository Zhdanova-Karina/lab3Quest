import java.io.IOException;
import java.util.Scanner;

public class Cage {
    private String answerCode;

    public Cage(String code) {
        answerCode = code;
    }

    public String getAnswerCode() { return answerCode; }

    public int getHintForCode() {
        System.out.print("\nНажмите *, чтобы получить подсказку\n");
        try {
            char symbol = (char) System.in.read(); // Читаем один символ

            // Очистка буфера: считываем оставшийся символ новой строки
            System.in.read();

            if (symbol == '*') return 0;
                else return 1; // Неверный ввод
        } catch (IOException e) {
            e.printStackTrace(); // Обработка исключения
            return -1; // Вернуться -1 в случае ошибки
        }
    }
    public void CodeOfCage(Level level, Game game, int number) {
        String input;
        if (getHintForCode() == 0) {
            System.out.print("\nКод состоит из трёх символов. Чтобы найти каждый символ вам нужно:\n1) перевести число 127 в двоичную, восьмеричную и шестнадцатиричную системы счисления;\n2) каждый последний символ будет являться частью кода\nВсе буквы должны быть заглавными!\n\n");
            Scanner scanner = new Scanner(System.in);

            do {
                System.out.print("\nВведите код:\n");
                input = scanner.nextLine();

                // Сравниваем input с кодом ответа
                if (input.equals(getAnswerCode())) {
                    System.out.println("Ура! Вы освободили далматинца!");
                    level.DalmatinFound(level, game, number);
                } else {
                    game.PrintRepeatInput();
                }
            } while (!input.equals(getAnswerCode()));

        } else game.PrintRepeatInput();
    }
}
