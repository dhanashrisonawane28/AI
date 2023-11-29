
import java.util.Scanner;
public class tictactoeai {
    static char[] square = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("============================================");
        System.out.println("\n\n\tTic Tac Toe\n\n");
        System.out.println("============================================\n\n");
        System.out.println("X - Sign for Player 1\n O - Sign for Player 2 (AI)\n");

        int player = 1, choice;
        char mark;

        do {
            board();
            
            if((player%2) == 1)   //give chance to player first and then to ai
            {
                player=1;
            }
            else
            {
                player=2;
            }

            if (player == 1) {
                System.out.print("Player 1, enter a number:  ");
                choice = scanner.nextInt();
                mark = 'X';
            } 
            else {
                // AI's turn
                choice = findBestMove();
                mark = 'O';
            }

            if (choice >= 1 && choice <= 9 && square[choice] == '0' + choice) {
                square[choice] = mark;    // if available then mark
            } else {
                System.out.println("Invalid move! Try again.");
                player--;
            }

            int result = win();
            if (result == 1) {
                board();
                System.out.println("============================================");
                if (player == 1)
                    System.out.println("Congrats! Player 1 (X) wins!");
                else
                    System.out.println("AI (O) wins!");
                break;
            } else if (result == 0) {
                board();
                System.out.println("============================================");
                System.out.println("It's a draw!");
                break;
            }
            player++;
        } while (true);

        scanner.close();
    }

    static int win() {
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
            return 0;   //draw
        else
            return -1; 
    }

    static void board() {
        System.out.println("============================================");
        System.out.println("\n\n\tTic Tac Toe\n\n");
        System.out.println("============================================\n\n");

        System.out.println("X - Sign for Player 1\n0 - Sign for Player 2\n");

        System.out.println();
        System.out.println("     |     |     ");
        System.out.println("  " + square[1] + "  |  " + square[2] + "  |  " + square[3]);
        System.out.println("_____|_____|_____");
        System.out.println("     |     |     ");
        System.out.println("  " + square[4] + "  |  " + square[5] + "  |  " + square[6]);
        System.out.println("_____|_____|_____");
        System.out.println("     |     |     ");
        System.out.println("  " + square[7] + "  |  " + square[8] + "  |  " + square[9]);
        System.out.println("     |     |     \n");
    }

    static int minimax(boolean isMaximizing) {
        int result = win();

        if (result != -1) { 
            if (result == 0) //draw
                return 0;
            else if (result == 1)  //win
                return isMaximizing ? -1 : 1;  
        }

        if (isMaximizing) {  //ai
            int bestScore = Integer.MIN_VALUE; //-infinity smallest value
            for (int i = 1; i <= 9; i++) {
                if (square[i] == '0' + i) {
                    square[i] = 'O';
                    int score = minimax(false);
                    square[i] = (char) ('0' + i);
                    bestScore = Math.max(bestScore, score);
                } //place o at position
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 1; i <= 9; i++) {
                if (square[i] == '0' + i) {
                    square[i] = 'X';
                    int score = minimax(true);
                    square[i] = (char) ('0' + i);
                    bestScore = Math.min(bestScore, score);
                }
            }
            return bestScore;
        }
    }

    static int findBestMove() {
        int bestMove = -1;
        int bestScore = Integer.MIN_VALUE;  //-infinity smallest value
        for (int i = 1; i <= 9; i++) {  //check occupied or not
            if (square[i] == '0' + i) {   //check for empty space
                square[i] = 'O';
                int score = minimax(false);
                square[i] = (char) ('0' + i);
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i;
                }
            }
        }
        return bestMove;
    }
}
