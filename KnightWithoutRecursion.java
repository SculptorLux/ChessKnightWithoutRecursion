import java.util.Stack;

public class KnightWithoutRecursion {
    private static final int BOARD_SIZE = 8;
    private static final int[] X_MOVES = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] Y_MOVES = {1, 2, 2, 1, -1, -2, -2, -1};
    private int[][] board;
    private int solutionsFound = 0;
    private int toFind;

    private static class MoveInfo {
        // В этом классе храню состояние (положение коня сейчас, номер хода и индекс следующего)
        int x, y, turn, moveIndex;

        MoveInfo(int x, int y, int turn, int moveIndex) {
            this.x = x;
            this.y = y;
            this.turn = turn;
            this.moveIndex = moveIndex;
        }
    }

    public KnightWithoutRecursion(int solutions) {
        this.toFind = solutions;
        board = new int[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = -100;
            }
        }
    }

    private void printBoard() {
        System.out.printf("%4d ----------------------------------\n", this.solutionsFound);
        for (int[] row : board) {
            for (int cell : row) {
                System.out.printf("%4d ", cell);
            }
            System.out.println();
        }
    }

    private boolean canMove(int x, int y) {
        return (x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE && board[x][y] == -100);
    }

    public void start(int startX, int startY) {
        Stack<MoveInfo> stack = new Stack<>();
        stack.push(new MoveInfo(startX, startY, 1, 0));
        board[startX][startY] = 1;
        // Создал стек и руками записал в него первый ход

        while (!stack.isEmpty() && solutionsFound < toFind) {
            MoveInfo current = stack.pop();
            //Извлекаю текущий ход

            if (current.turn == BOARD_SIZE * BOARD_SIZE) {
                solutionsFound++;
                printBoard();
                board[current.x][current.y] = -100;
                if (solutionsFound >= toFind) {
                    break;
                }
                continue;
            }

            boolean hasNextMove = false;
            for (int i = current.moveIndex; i < X_MOVES.length; i++) {
                //Перебр ходов (возможных)
                int nextX = current.x + X_MOVES[i];
                int nextY = current.y + Y_MOVES[i];
                if (canMove(nextX, nextY)) {
                    stack.push(new MoveInfo(current.x, current.y, current.turn, i + 1));
                    stack.push(new MoveInfo(nextX, nextY, current.turn + 1, 0));
                    board[nextX][nextY] = current.turn + 1;
                    hasNextMove = true;
                    // Нашел следующий ход и прервал
                    break;
                }
            }

            if (!hasNextMove) {
                // Не нашел ход и вернул текущий совершенный ход на -100
                board[current.x][current.y] = -100;
            }
        }

        if (solutionsFound == 0) {
            System.out.println("Не решил");
        }
    }

    public static void main(String[] args) {
        KnightWithoutRecursion knightWithoutRecursion = new KnightWithoutRecursion(1);
        knightWithoutRecursion.start(0, 0);
    }
}
