// Montecillo & Porol 
// CSCC 13 CCB

package Queen_Recursion;

public class Recursion_Montecillo_Porol {
    // Check if there is no queen in the same column or diagonals
    private static boolean noCollision(int[][] chessBoard, int row, int col, int N) {
        // Check if there is a queen in the same column
        for (int i = 0; i < row; i++) {
            if (chessBoard[i][col] == 1) {
                return false;
            }
        }

        // Check diagonal on the left side
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (chessBoard[i][j] == 1) {
                return false;
            }
        }

        // Check diagonal on the right side
        for (int i = row, j = col; i >= 0 && j < N; i--, j++) {
            if (chessBoard[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    // Recursively place queens on the chess board
    private static boolean placeQueens(int[][] chessBoard, int row, int N) {
        if (row >= N) {
            return true; // All queens have been placed
        }

        for (int col = 0; col < N; col++) {
            if (noCollision(chessBoard, row, col, N)) {
                chessBoard[row][col] = 1; // Place queen at (row, col)
                if (placeQueens(chessBoard, row + 1, N)) {
                    return true; // Queen placed successfully, continue to next row
                }
                chessBoard[row][col] = 0; // Backtrack: Remove queen and try next column
            }
        }

        return false; // No solution found for this configuration, backtrack
    }

    // Print the chess board with queens placed
    private static void showResult(int[][] chessBoard) {
        for (int[] row : chessBoard) {
            for (int cell : row) {
                System.out.print(cell + " "); // Print each cell of the chess board
            }
            System.out.println();
        }
    }

    // Entry point of the program
    public static void startQueens(int N) {
        int[][] chessBoard = new int[N][N]; // Initialize chess board
        if (!placeQueens(chessBoard, 0, N)) {
            System.out.println("No solution exists."); // No solution found
        } else {
            showResult(chessBoard); // Print the chess board with queens placed
        }
    }

    public static void main(String[] args) {
        int size = 8; // Input size (board size)
        startQueens(size); // Solve the Eight Queens problem for the given input size
    }
}