import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;



public class Level {
    private String name;
    private int countDalmatins, countPlace;

    public ArrayList<Dalmatin> dalmatins = new ArrayList<>();
    public ArrayList<Place> places = new ArrayList<>();
    public static final int MAX_DOGS = 4;

    Level(boolean isBedroom) {
        // Инициализация далматинцев
        dalmatins.add(new Dalmatin("Патч"));
        dalmatins.add(new Dalmatin("Пэдди"));
        dalmatins.add(new Dalmatin("Понго"));
        dalmatins.add(new Dalmatin("Ролли"));

        // Инициализация места
        if (isBedroom) {
            name = "Спальня";
            countDalmatins = 3;
            places.add(new Place("\n 1. Шкаф", false));
            places.add(new Place("\n 2. Тумба", false));
            places.add(new Place("\n 3. Кровать", true));
            places.add(new Place("\n 4. Коробка", true));
            places.add(new Place("\n 5. Полка", true));

        }
        else {
            name = "Подвал";
            countDalmatins = 1;
            places.add(new Place("\n 1. Бочка", false));
            places.add(new Place("\n 2. Клетка", true));
            places.add(new Place("\n 3. Шкаф", false));
        }
    }

    public int getCountPlace() {
        return countPlace;
    }

    public int getCountDalmatins() { return countDalmatins; }
    public String getName(){ return name; }

    public int PrintLocationPlace(Level level, int countPlace){
        // Печатаем места
        for (int i = 0; i < countPlace; i++) {
            System.out.println(level.places.get(i).getName());
        }

        // Запрашиваем ввод от пользователя
        System.out.println("\nВведите пункт: ");
        Scanner scanner = new Scanner(System.in);
        int numberPlace = scanner.nextInt();

        // Очищаем буфер ввода
        scanner.nextLine();

        return numberPlace;
    }

    public void ViewingFoundDalmatians(Level level, Game player) {
        System.out.printf("\nВы нашли %d/4 далматинцев:\n", player.getCountDalmatins());
        for (int i = 0; i < player.getCountDalmatins(); i++) {
            System.out.println(level.dalmatins.get(i).getName());
        }
    }

   public void DalmatinFound(Level level, Game player, int number){
       if (level.places.get(number - 1).getExistDalmatian() == true) {
           player.PlusOne();
           level.places.get(number - 1).setExistDalmatian(false);

           ViewingFoundDalmatians(level, player);
       }
       else {
           System.out.println("Увы, здесь никого нет\n");
       }
   }

    public int TransferLastLocation(Level level, Game player) {
        char symbol  = '\0';
        if (player.getCountDalmatins() == level.getCountDalmatins() && player.getCountDalmatins() < MAX_DOGS) {
            System.out.println("\n\nНажмите *, чтобы перейти к следующей локации\n");

            // Используем Scanner для получения ввода
            Scanner scanner = new Scanner(System.in);
            symbol = scanner.next().charAt(0); // Читаем первый символ ввода
        }
        if (symbol == '*') return 0;
        else return 1;
    }

    public int RangeCheck(int number, int range) {
        if (number > 0 && number <= range+1) return 0;
        else return 1;
    }

    public void Level_1(Level level, Game game) {
        while (game.getCountDalmatins() < level.getCountDalmatins()) {
            int number;
            number = PrintLocationPlace(level, 5);
            if (level.RangeCheck(number, 5) == 0) level.DalmatinFound(level, game, number);
            else game.PrintRepeatInput();
        }
    }
    public void Level_2(Level level, Game game) {
        Cage cage = new Cage("17F");
        while (game.getCountDalmatins() < MAX_DOGS) {
            int number;
            number = PrintLocationPlace(level, 3);
            if (level.RangeCheck(number, 3) == 0) {
                if (number != 2) level.DalmatinFound(level, game, number);
                else {
                    System.out.println("\nО нет! Клетка закрыта на замок! Вам нужно отгадать код!\n");
                    cage.CodeOfCage(level, game, number);
                }
            }
            else game.PrintRepeatInput();
        }
    }

}
