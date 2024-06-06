
public class Knight {
    private static final int BOARD_SIZE = 8;
    private static final int[] X_MOVES = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] Y_MOVES = {1, 2, 2, 1, -1, -2, -2, -1};
    private int[][] board;
    private int solutionsFound = 0;
    private int toFind = 1;
    
    public Knight(int solutions) {
    	this.toFind = solutions;
        board = new int[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = -100;
            }
        }
    }

    private void printBoard() {
        //System.out.println("Solutions: " + solutionsCount);
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
        moveKnight(startX, startY, 1);
     
    }    
    
    private boolean moveKnight(int x, int y, int turn) {
    	if (!canMove(x,y)) return false;
    	if (turn==64) {
    		solutionsFound ++;
    		board[x][y] = turn;
    		printBoard();
    		if (solutionsFound>=this.toFind) {System.exit(0);}
    		return true;
    	}
    	
    	board[x][y] = turn;
    	//printBoard();
        for (int i = 0; i < X_MOVES.length; i++) {
            int nextX = x + X_MOVES[i];
            int nextY = y + Y_MOVES[i];
            
            if (moveKnight(nextX,nextY,turn+1)) {
            	board[nextX][nextY]=-100;
            }
        }
    	
    	board[x][y] = -100;
    	return false;
    	
    }
    
	public static void main(String[] args) {
		
        Knight k = new Knight(10);
        k.start(0, 0);
        
	}

}
