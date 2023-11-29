import java.util.Scanner;
public class tictactoenonai {
    static char[] square = {'0','1','2','3','4','5','6','7','8','9'};
    static int player = 1;

    public static void main(String[] args) {
        char mark;
        int i, c;

        do {
            clearScreen();
            System.out.println("\n\n\tTic Tac Toe\n\n");
            System.out.println("Player 1 Symbol X  &  Player 2 Symbol O\n\n");

            displayBoard();

            player = (player % 2 == 1) ? 1 : 2;
            System.out.println("Player " + player + ", enter a number: ");
            Scanner scanner = new Scanner(System.in);
            c = scanner.nextInt();

            mark = (player == 1) ? 'X' : 'O';

            if (c >= 1 && c <= 9 && square[c] == c + '0') {
                square[c] = mark;
            } else {
                System.out.println("Invalid move!");
                player--;
            }

            i = checkWin();
            player++;
        } while (i == -1);

        clearScreen();
        System.out.println("\n\n\tTic Tac Toe\n\n");
        displayBoard();

        if (i == 1) {
            System.out.println("Player " + --player + " wins!");
        } else {
            System.out.println("Game draw!");
        }
    }

    static int checkWin() {
        if (square[1] == square[2] && square[2] == square[3])
            return 1;
        else if (square[4] == square[5] && square[5] == square[6])
            return 1;
        else if (square[7] == square[8] && square[8] == square[9])
            return 1;
        else if (square[1] == square[4] && square[4] == square[7])
            return 1;
        else if (square[2] == square[5] && square[5] == square[8])
            return 1;
        else if (square[3] == square[6] && square[6] == square[9])
            return 1;
        else if (square[1] == square[5] && square[5] == square[9])
            return 1;
        else if (square[3] == square[5] && square[5] == square[7])
            return 1;
        else if (square[1] != '1' && square[2] != '2' && square[3] != '3'
                && square[4] != '4' && square[5] != '5' && square[6] != '6'
                && square[7] != '7' && square[8] != '8' && square[9] != '9')
            return 0;
        else
            return -1;
    }

    static void displayBoard() {
        System.out.println("     |     |     ");
        System.out.println("  " + square[1] + "  |  " + square[2] + "  |  " + square[3]);
        System.out.println("_____|_____|_____");
        System.out.println("     |     |     ");
        System.out.println("  " + square[4] + "  |  " + square[5] + "  |  " + square[6]);
        System.out.println("_____|_____|_____");
        System.out.println("     |     |     ");
        System.out.println("  " + square[7] + "  |  " + square[8] + "  |  " + square[9]);
        System.out.println("     |     |     ");
    }

    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}