public class HumanMove {

    public static boolean moveComplete = false;

    public static void move(int y, int x) {
        moveComplete = false;
        if (Field.field[y][x] == Field.DEFAULT_SYMBOL) {
            Field.field[y][x] = Field.HUMAN_SYMBOL;
            Main.field[y][x].setText(String.valueOf(Field.HUMAN_SYMBOL));
            moveComplete = true;
        }
    }
}
