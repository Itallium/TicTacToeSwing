public class Field {

    public static final int FIELD_SIZE = 3;
    public static final char HUMAN_SYMBOL = 'X';
    public static final char COMPUTER_SYMBOL = '0';
    public static final char DEFAULT_SYMBOL = ' ';

    public static char[][] field = new char[FIELD_SIZE][FIELD_SIZE];

    public static void clearField() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                field[j][i] = DEFAULT_SYMBOL;
                Main.field[j][i].setText(String.valueOf(DEFAULT_SYMBOL));
            }
        }
    }

}
