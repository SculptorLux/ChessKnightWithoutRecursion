import java.util.ArrayList;
import java.util.List;

public class ChessKnightWithoutRecursion {
    private static final int BOARD_SIZE = 8;
    private static final int[] X_MOVES = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] Y_MOVES = {1, 2, 2, 1, -1, -2, -2, -1};
    private int[][] board;
    private List<int[]> path; // Путь коня
    private int solutionsCount; // Сколько решений удалось найти (Задал условие, чтобы найти 10 решений далее в коде)
    private boolean found; // Фдаг для обозначения, что нашлосьб solutionsCount решений

    public ChessKnightWithoutRecursion() {
        board = new int[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = -100;
            }
        }
        path = new ArrayList<>();
        solutionsCount = 0;
        found = false;
    }

    private boolean canMove(int x, int y) {
        return (x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE && board[x][y] == -100);
    }

    private List<int[]> getNextMoves(int x, int y) {
        List<int[]> nextMoves = new ArrayList<>();
        for (int i = 0; i < X_MOVES.length; i++) {
            int nextX = x + X_MOVES[i];
            int nextY = y + Y_MOVES[i];
            if (canMove(nextX, nextY)) {
                nextMoves.add(new int[]{nextX, nextY});
            }
        }
        return nextMoves;
    }

    public void start(int startX, int startY) {
        board[startX][startY] = 0;
        path.add(new int[]{startX, startY});
        backtrack(startX, startY, 1);
    }

    private void backtrack(int x, int y, int move) {
        if (found) {
            return;
        }

        if (move == BOARD_SIZE * BOARD_SIZE) {
            printBoard();
            solutionsCount++;
            if (solutionsCount == 10) {
                found = true;
                return;
            }
        }

        List<int[]> nextMoves = getNextMoves(x, y);
        for (int[] nextMove : nextMoves) {
            int nextX = nextMove[0];
            int nextY = nextMove[1];
            board[nextX][nextY] = move;
            path.add(new int[]{nextX, nextY});
            backtrack(nextX, nextY, move + 1);
            board[nextX][nextY] = -100;
            path.remove(path.size() - 1);
        }
    }

    private void printBoard() {
        System.out.println("Solutions: " + solutionsCount);
        for (int[] row : board) {
            for (int cell : row) {
                System.out.printf("%4d ", cell);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ChessKnightWithoutRecursion chessKnight = new ChessKnightWithoutRecursion();
        chessKnight.start(0, 0); // Начальная позиция коня
    }
}