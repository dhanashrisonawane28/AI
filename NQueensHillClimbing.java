import java.util.Arrays;
import java.util.Random;

public class NQueensHillClimbing {
	static final int N = 8;

	public static void main(String[] args) {
		int[] state = new int[N];
		int[][] board = new int[N][N];

		configureRandomly(board, state);
		hillClimbing(board, state);
	}

	static void configureRandomly(int[][] board, int[] state) {
		Random rand = new Random();

		for (int i = 0; i < N; i++) {
			state[i] = rand.nextInt(N);
			board[state[i]][i] = 1;
		}
	}

	static void printBoard(int[][] board) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(" " + board[i][j] + " ");
			}
			System.out.println();
		}
	}

	static void printState(int[] state) {
		System.out.println(" " + Arrays.toString(state) + " ");
	}

	static boolean compareStates(int[] state1, int[] state2) {
		return Arrays.equals(state1, state2);
	}

	static void fill(int[][] board, int value) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] = value;
			}
		}
	}

	static int calculateObjective(int[][] board, int[] state) {
		int attacking = 0;

		for (int i = 0; i < N; i++) {
			int row, col;
			row = state[i];
			col = i - 1;

			while (col >= 0 && board[row][col] != 1) {
				col--;
			}
			if (col >= 0 && board[row][col] == 1) {
				attacking++;
			}

			row = state[i];
			col = i + 1;
			while (col < N && board[row][col] != 1) {
				col++;
			}
			if (col < N && board[row][col] == 1) {
				attacking++;
			}

			row = state[i] - 1;
			col = i - 1;
			while (col >= 0 && row >= 0 && board[row][col] != 1) {
				col--;
				row--;
			}
			if (col >= 0 && row >= 0 && board[row][col] == 1) {
				attacking++;
			}

			row = state[i] + 1;
			col = i + 1;
			while (col < N && row < N && board[row][col] != 1) {
				col++;
				row++;
			}
			if (col < N && row < N && board[row][col] == 1) {
				attacking++;
			}

			row = state[i] + 1;
			col = i - 1;
			while (col >= 0 && row < N && board[row][col] != 1) {
				col--;
				row++;
			}
			if (col >= 0 && row < N && board[row][col] == 1) {
				attacking++;
			}

			row = state[i] - 1;
			col = i + 1;
			while (col < N && row >= 0 && board[row][col] != 1) {
				col++;
				row--;
			}
			if (col < N && row >= 0 && board[row][col] == 1) {
				attacking++;
			}
		}
		return attacking / 2;
	}

	static void generateBoard(int[][] board, int[] state) {
		fill(board, 0);
		for (int i = 0; i < N; i++) {
			board[state[i]][i] = 1;
		}
	}

	static void copyState(int[] state1, int[] state2) {
		System.arraycopy(state2, 0, state1, 0, N);
	}

	static void getNeighbour(int[][] board, int[] state) {
		int[][] opBoard = new int[N][N];
		int[] opState = new int[N];
		copyState(opState, state);
		generateBoard(opBoard, opState);

		int opObjective = calculateObjective(opBoard, opState);

		int[][] neighbourBoard = new int[N][N];
		int[] neighbourState = new int[N];
		copyState(neighbourState, state);
		generateBoard(neighbourBoard, neighbourState);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (j != state[i]) {
					neighbourState[i] = j;
					neighbourBoard[neighbourState[i]][i] = 1;
					neighbourBoard[state[i]][i] = 0;

					int temp = calculateObjective(neighbourBoard, neighbourState);

					if (temp <= opObjective) {
						opObjective = temp;
						copyState(opState, neighbourState);
						generateBoard(opBoard, opState);
					}

					neighbourBoard[neighbourState[i]][i] = 0;
					neighbourState[i] = state[i];
					neighbourBoard[state[i]][i] = 1;
				}
			}
		}

		copyState(state, opState);
		fill(board, 0);
		generateBoard(board, state);
	}

	static void hillClimbing(int[][] board, int[] state) {
		int[][] neighbourBoard = new int[N][N];
		int[] neighbourState = new int[N];
		generateBoard(neighbourBoard, neighbourState);

		do {
			copyState(state, neighbourState);
			generateBoard(board, state);
			getNeighbour(neighbourBoard, neighbourState);

			if (compareStates(state, neighbourState)) {
				printBoard(board);
				break;
			} else if (calculateObjective(board, state) == calculateObjective(neighbourBoard,
																			neighbourState)) {
				neighbourState[(int) (Math.random() * N)] = (int) (Math.random() * N);
				generateBoard(neighbourBoard, neighbourState);
			}
		} while (true);
	}
}
