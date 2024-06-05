import java.util.ArrayList;
import java.util.List;

public class ChessKnightWithoutRecursion {
    private static final int BOARD_SIZE = 8;
    private static final int[] X_MOVES = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] Y_MOVES = {1, 2, 2, 1, -1, -2, -2, -1};
    private int[][] board;

    public ChessKnightWithoutRecursion() {
        board = new int[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = -100;
            }
        }
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
                int moveCount = 0;
                for (int j = 0; j < X_MOVES.length; j++) {
                    int nx = nextX + X_MOVES[j];
                    int ny = nextY + Y_MOVES[j];
                    if (canMove(nx, ny)) {
                        moveCount++;
                    }
                }
                nextMoves.add(new int[]{nextX, nextY, moveCount});
            }
        }
        return nextMoves;
    }

    public void start(int startX, int startY) {
        board[startX][startY] = 0;
        int move = 1;
        int x = startX;
        int y = startY;

        while (move < BOARD_SIZE * BOARD_SIZE) {
            List<int[]> nextMoves = getNextMoves(x, y);

            int minMoves = Integer.MAX_VALUE;
            int nextX = -1;
            int nextY = -1;
            for (int[] moveInfo : nextMoves) {
                if (moveInfo[2] < minMoves) {
                    minMoves = moveInfo[2];
                    nextX = moveInfo[0];
                    nextY = moveInfo[1];
                }
            }

            if (nextX == -1 && nextY == -1) {
                System.out.println("Невозможно");
                return;
            }

            board[nextX][nextY] = move;
            x = nextX;
            y = nextY;
            move++;
        }

        printBoard();
    }

    private void printBoard() {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.printf("%4d ", cell);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ChessKnightWithoutRecursion chessKnight = new ChessKnightWithoutRecursion();
        chessKnight.start(0, 0); // Начальная позиция коня
    }
}
