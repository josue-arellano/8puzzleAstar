/**
 *   Name:      Arellano, Josue
 *   File:      BoardNode.java
 *   Project:   #1
 *   Due:       Sep 21, 2018
 *   Course:    cs14103-w18
 *
 *   Description:
 *              This
 */
package pkg420;

/**
 *
 * @author josue
 */
public class BoardNode implements Comparable {

    Board board;
    private int cost;
    private BoardNode parent;
    private int f;

    BoardNode() {
        this(new Board());
    }

    BoardNode(Board board) {
        this(board, 0, null);
    }

    BoardNode(Board board, int cost, BoardNode parent) {
        this.board = board;
        this.parent = parent;
        this.cost = cost;
    }
    
    public int getF() {
        return f;
    }

    public void setF1() {
        f = cost + getH1();
    }

    public void setF2() {
        f = cost + getH2();
    }

    private int getH2() {
        int manhattan = 0;
        int[] tempBoard;
        tempBoard = this.getBoard().getArray();
        for (int i = 0; i < 9; i++)
            if (tempBoard[i] != 0) {
                int distance = Math.abs(((int) i / 3) - ((int) tempBoard[i] / 3))
                        + Math.abs((i % 3) - (tempBoard[i] % 3));
                manhattan += distance;
            }
        return manhattan;
    }

    private int getH1() {
        int misplacedTiles = 0;
        int tempBoard[];
        tempBoard = getBoardArray();
        for (int i = 0; i < 9; i++) {
            if (tempBoard[i] != 0 && tempBoard[i] != i) {
                misplacedTiles++;
            }
        }
        return misplacedTiles;
    }

    @Override
    public int compareTo(Object o) {
        BoardNode other = (BoardNode) o;
        if (f > other.f) {
            return 1;
        } else if (f < other.f) {
            return -1;
        } else {
            return 0;
        }
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int[] getBoardArray() {
        return board.getArray();
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public BoardNode getParent() {
        return parent;
    }

    public void setParent(BoardNode parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        int[] tempBoard = this.getBoardArray();
        String boardStr = "";
        for (int i = 0; i < 9; i++) {
            boardStr += tempBoard[i] + " ";
        }
        return boardStr;
    }

}
