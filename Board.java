/**
 *   Name:      Arellano, Josue
 *   File:      Puzzle.java
 *   Project:   #1
 *   Due:       Sep 18, 2018
 *   Course:    cs14103-w18
 *
 *   Description:
 *              This
 */
package pkg420;
import java.util.Scanner;
/**
 *
 * @author josue
 */
public class Board 
{

    private int[] board;

    public Board() {
        board = generator();
    }
    
    public Board(int[] board) {
        if(isSolvable(board)) this.board = board;
        else board = generator();
    }

    public Board(String input) {
        if (check(input)) {
            board = new int[9];
            parseAccepted(input);
        } else {
            System.out.println("Error: random puzzle generated!");
            board = generator();
        }
    }

    public static boolean check(String input) {
        int[] board = new int[9];
        Scanner inputRead = new Scanner(input);
        boolean[] set = {false, false, false, false, false, false, false, 
                          false, false};
        int i = 0;
        while (inputRead.hasNextInt()) {
            if (i > 8) {
                System.out.println("  ERROR: Too many inputs.");
                return false;
            }
            int x = inputRead.nextInt();
            if (x < 0 || x > 8) {
                System.out.println("  ERROR: Invalid input.");
                return false;
            } else if (set[x]) {
                System.out.println("  ERROR: Repeated numbers.");
                return false;
            }
            board[i] = x;
            set[x] = true;
            i++;
        }
        if (i < 9) {
            System.out.println("  ERROR: Too few inputs.");
            return false;
        }
        for (int j = 0; j < set.length; j++) {
            if (!set[j]) {
                System.out.println("  ERROR: Invalid input.");
                return false;
            }
        }
        if (isSolvable(board)) {
            return true;
        } else {
            System.out.println("  ERROR: Not solvable.");
            return false;
        }
    }

    private static int inversionCounter(int[] board) {
        int counter = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (board[i] != 0 && board[j] != 0 && board[i] > board[j])
                {
                    counter++;
                }
            }
        }
        return counter;
    }
    
    public static Board moveZero(Board board, int zeroPos, int newPos) {
        int[] tempBoard = board.getArray();
        int[] newBoard = new int[9];
        System.arraycopy(tempBoard, 0, newBoard, 0, 9);
        if(moveCheck(newPos % 3, newPos / 3)) {
            newBoard[zeroPos] = newBoard[newPos];
            newBoard[newPos] = 0;
        }
        return new Board(newBoard);
    }

    private static boolean isSolvable(int[] board) {
        return inversionCounter(board) % 2 == 0;
    }

    public static boolean isGoal(int[] board) {
        for (int i = 0; i < 9; i++) {
            if (board[i] != 0 && board[i] != i + 1) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean moveCheck(int x, int y) {
        return (x >= 0 && x <= 2 && y >= 0 && y <= 2);
    }
    
    public int findZero() {
        for(int i = 0; i < 9; i++) {
            if(board[i] == 0) return i;
        }
        return 8;
    }

    public static int[] generator() {
        int[] puzzle = new int[9];
        do {
            boolean[] set = {false, false, false, false, false, false, false, 
                             false, false};
            for (int i = 0; i < puzzle.length; i++) {
                int x = (int) (Math.random() * 100) % 9;
                while (set[x] == true)
                    x = (int) (Math.random() * 100) % 9;
                set[x] = true;
                puzzle[i] = x;
            }
        } while (!isSolvable(puzzle));
        return puzzle;
    }

    public static int getH2(int[] board) {
        int manhattan = 0;
        for (int i = 0; i < 9; i++) {
            if (board[i] != 0)
                manhattan += Math.abs((((int) i / 3) - ((int) (board[i] - 1) / 3))
                        + Math.abs(i % 3 - ((board[i] - 1) % 3)));
        }
        return manhattan;
    }

    public int getH2() {
        return getH2(board);
    }

    private void parseAccepted(String input) {
        Scanner inputRead = new Scanner(input);
        int i = 0;
        while (inputRead.hasNextInt()) {
            board[i] = inputRead.nextInt();
            i++;
        }
    }
    
    public int[] getArray() {
        return board;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        String returnString = "";
        for (int i = 0; i < 9; i++) {
            if (i != 0 && i % 3 == 0)
                returnString = returnString + "\n";
            returnString = returnString + board[i] + " ";
        }
        return returnString;
    }
}
