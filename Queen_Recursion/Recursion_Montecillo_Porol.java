import java.util.Scanner;

public class Recursion_Montecillo_Porol {
    // Check if there is no queen in the same column or diagonals
    private static boolean noCollision(char[][] chessBoard, int row, int col, int N) {
        // Check if there is a queen in the same column
        for (int i = 0; i < row; i++) {
            if (chessBoard[i][col] == 'Q') {
                return false;
            }
        }

        // Check diagonal on the left side
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (chessBoard[i][j] == 'Q') {
                return false;
            }
        }

        // Check diagonal on the right side
        for (int i = row, j = col; i >= 0 && j < N; i--, j++) {
            if (chessBoard[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    // Recursively place queens on the chess board
    private static boolean placeQueens(char[][] chessBoard, int row, int N) {
        if (row >= N) {
            return true; // All queens have been placed
        }

        for (int col = 0; col < N; col++) {
            if (noCollision(chessBoard, row, col, N)) {
                chessBoard[row][col] = 'Q'; // Place queen at (row, col)
                if (placeQueens(chessBoard, row + 1, N)) {
                    return true; // Queen placed successfully, continue to next row
                }
                chessBoard[row][col] = '.'; // Backtrack: Remove queen and try next column
            }
        }

        return false; // No solution found for this configuration, backtrack
    }

    // Print the chess board with queens placed
    private static void showResult(char[][] chessBoard, int N) {
        // Print the column numbers
        System.out.print("   ");
        for (int i = 1; i <= N; i++) {
            System.out.printf("%-3d", i);
        }
        System.out.println();
    
        for (int i = 0; i < N; i++) {
            // Print the row number
            System.out.printf("%-3d", i + 1);
            for (int j = 0; j < N; j++) {
                System.out.printf("%-3c", chessBoard[i][j]); // Print each cell of the chess board
            }
            System.out.println();
        }
    }    

    // Entry point of the program
    public static void startQueens(int N) {
        char[][] chessBoard = new char[N][N]; // Initialize chess board
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                chessBoard[i][j] = '.'; // Fill the board with empty cells
            }
        }
        if (!placeQueens(chessBoard, 0, N)) {
            System.out.println("No solution exists."); // No solution found
        } else {
            showResult(chessBoard, N); // Print the chess board with queens placed
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the chessboard: ");
        int size = scanner.nextInt(); // Input size (board size)
        startQueens(size); // Solve the Eight Queens problem for the given input size
        scanner.close();
    }
}
