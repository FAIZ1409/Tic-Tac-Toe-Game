import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    static char[][] board = {
        {' ', ' ', ' '},
        {' ', ' ', ' '},
        {' ', ' ', ' '}
    };
    static char playerSymbol = 'X';
    static char computerSymbol = 'O';
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        boolean playAgain;
        do {
            resetBoard();
            boolean gameWon = false;
            boolean playerTurn = true;
            
            while (!isBoardFull() && !gameWon) {
                printBoard();
                
                if (playerTurn) {
                    playerMove();
                    gameWon = checkWin(playerSymbol);
                    if (gameWon) {
                        printBoard();
                        System.out.println("Congratulations! You have won the game :))");
                    }
                } else {
                    computerMove();
                    gameWon = checkWin(computerSymbol);
                    if (gameWon) {
                        printBoard();
                        System.out.println("Sorry, you lost the game. Try again :((");
                    }
                }
                playerTurn = !playerTurn;
            }
            
            if (!gameWon) {
                printBoard();
                System.out.println("It's a tie!");
            }
            
            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");
        } while (playAgain);
        
        System.out.println("Thank you for playing!");
    }

    public static void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public static void printBoard() {
        System.out.println("Board:");
        for (int i = 0; i < 3; i++) {
            System.out.println(" " + board[i][0] + " | " + board[i][1] + " | " + board[i][2]);
            if (i < 2) System.out.println("---|---|---");
        }
    }

    public static void playerMove() {
        int row, col;
        while (true) {
            System.out.print("Enter your move (row [1-3] and column [1-3]): ");
            row = scanner.nextInt() - 1;
            col = scanner.nextInt() - 1;
            
            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                board[row][col] = playerSymbol;
                break;
            } else {
                System.out.println("This move is not valid.");
            }
        }
    }

    public static void computerMove() {
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (board[row][col] != ' ');
        
        System.out.println("Computer chose position (" + (row + 1) + ", " + (col + 1) + ")");
        board[row][col] = computerSymbol;
    }

    public static boolean checkWin(char symbol) {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) return true;
            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) return true;
        }
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) return true;
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) return true;
        
        return false;
    }

    public static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}