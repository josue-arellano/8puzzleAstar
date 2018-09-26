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
import java.util.Random;
import java.util.Vector;
import java.util.Arrays;
/**
 *
 * @author josue
 */
public class Board 
{
    private int[] board;
    private Vector<Arrays> createdBoards;

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
            board = new int[] {0,1,2,3,4,5,6,7,8};
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
    
    public static int[] generateRandom(int n) {
        int[] puzzle = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        Vector<Board> boards = new Vector<>();
        boards.add(new Board(puzzle));
        for(int i = 0; i < n; i++) {
            Random rand = new Random();
            int randCase = rand.nextInt(4);
            int zeroPos = findZero(puzzle);
            int x = zeroPos % 3;
            int y = zeroPos / 3;
            switch(randCase) {
                case 0:
                    if(moveCheck(x, y - 1)) puzzle = moveZero(puzzle, zeroPos, zeroPos - 3);
                    else puzzle = moveZero(puzzle, zeroPos, zeroPos + 3);
                    break;
                case 1:
                    if (moveCheck(x, y + 1))
                        puzzle = moveZero(puzzle, zeroPos, zeroPos + 3);
                    else
                        puzzle = moveZero(puzzle, zeroPos, zeroPos - 3);
                    break;
                case 2:
                    if (moveCheck(x - 1, y))
                        puzzle = moveZero(puzzle, zeroPos, zeroPos - 1);
                    else
                        puzzle = moveZero(puzzle, zeroPos, zeroPos + 1);
                    break;
                default:
                    if (moveCheck(x + 1, y))
                        puzzle = moveZero(puzzle, zeroPos, zeroPos + 1);
                    else
                        puzzle = moveZero(puzzle, zeroPos, zeroPos - 1);
                    break;
            }
            Board tempBoard = new Board(puzzle);
            if(boards.contains(tempBoard)) {
                i--;
            } else {
                boards.add(tempBoard);
            }
        }
        return puzzle;
    }
    
    private static int findZero(int[] board) {
        for (int i = 0; i < 9; i++) {
            if (board[i] == 0)
                return i;
        }
        return 8;
    }

    public static int[] moveZero(int[] board, int zeroPos, int newPos) {
        int[] newBoard = new int[9];
        System.arraycopy(board, 0, newBoard, 0, 9);
        if (moveCheck(newPos % 3, newPos / 3)) {
            newBoard[zeroPos] = newBoard[newPos];
            newBoard[newPos] = 0;
        }
        return newBoard;
    }
    
    public static int[] moveZero(Board board, int zeroPos, int newPos) {
        int[] tempBoard = board.getArray();
        int[] newBoard = new int[9];
        System.arraycopy(tempBoard, 0, newBoard, 0, 9);
        if(moveCheck(newPos % 3, newPos / 3)) {
            newBoard[zeroPos] = newBoard[newPos];
            newBoard[newPos] = 0;
        }
        return newBoard;
    }

    private static boolean isSolvable(int[] board) {
        return inversionCounter(board) % 2 == 0;
    }

    public static boolean isGoal(int[] board) {
        for (int i = 0; i < 9; i++) {
            if (board[i] != 0 && board[i] != i) {
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
