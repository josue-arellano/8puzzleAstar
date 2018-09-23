/**
 *   Name:      Arellano, Josue
 *   File:      Game.java
 *   Project:   #1
 *   Due:       Sep 20, 2018
 *   Course:    cs14103-w18
 *
 *   Description:
 *              This
 */
package pkg420;
import java.util.PriorityQueue;
import java.util.Stack;
/**
 *
 * @author josue
 */
public class Game
{
    private Board initBoard;
    private PriorityQueue<BoardNode> hammingPQ;
    private PriorityQueue<BoardNode> manhattanPQ;
    
    public Game() {
        this(new Board());
    }
    
    public Game(Board initBoard) {
        this.initBoard = initBoard;
        System.out.println("Generated board: \n" + initBoard);
    }
    
    public void start() {
        hammingPQ = new PriorityQueue<BoardNode>();
        manhattanPQ = new PriorityQueue<BoardNode>();
        
        System.out.println("  depth: " + aStar(manhattanPQ, initBoard, 2));
        System.out.println("  depth: " + aStar(hammingPQ, initBoard, 1));
    }
    
    private int aStar(PriorityQueue<BoardNode> PQ, Board initBoard, int h) {
        BoardNode initBoardNode = new BoardNode(initBoard);
        initBoardNode.setCost(0);
        if(h == 1) initBoardNode.setF1();
        else initBoardNode.setF2();
        PQ.add(initBoardNode);
        boolean done2 = false;
        BoardNode finalNode = null;
        int numNode = 0;
        while (!done2 && !PQ.isEmpty()) {
            BoardNode current = PQ.poll();
            if (current != null) {
                if (Board.isGoal(current.getBoardArray())) {
                    finalNode = current;
                    done2 = true;
                }
                if (!done2) {
                    int zeroPos = current.getBoard().findZero();
                    int x = zeroPos % 3;
                    int y = zeroPos / 3;
                    if (Board.moveCheck(x - 1, y)) {
                        Board childBoard = Board.moveZero(current.getBoard(), zeroPos, zeroPos - 1);
                        BoardNode childBoardNode = new BoardNode(childBoard, current.getCost() + 1, current);
                        if (h == 1)
                            childBoardNode.setF1();
                        else
                            childBoardNode.setF2();
                        PQ.add(childBoardNode);
                        numNode++;
                    }
                    if (Board.moveCheck(x + 1, y)) {
                        Board childBoard = Board.moveZero(current.getBoard(), zeroPos, zeroPos + 1);
                        BoardNode childBoardNode = new BoardNode(childBoard, current.getCost() + 1, current);
                        if (h == 1)
                            childBoardNode.setF1();
                        else
                            childBoardNode.setF2();
                        PQ.add(childBoardNode);
                        numNode++;
                    }
                    if (Board.moveCheck(x, y - 1)) {
                        Board childBoard = Board.moveZero(current.getBoard(), zeroPos, zeroPos - 3);
                        BoardNode childBoardNode = new BoardNode(childBoard, current.getCost() + 1, current);
                        if (h == 1)
                            childBoardNode.setF1();
                        else
                            childBoardNode.setF2();
                        PQ.add(childBoardNode);
                        numNode++;
                    }
                    if (Board.moveCheck(x, y + 1)) {
                        Board childBoard = Board.moveZero(current.getBoard(), zeroPos, zeroPos + 3);
                        BoardNode childBoardNode = new BoardNode(childBoard, current.getCost() + 1, current);
                        if (h == 1)
                            childBoardNode.setF1();
                        else
                            childBoardNode.setF2();
                        PQ.add(childBoardNode);
                        numNode++;
                    }
                }
            }
        }
        System.out.println();
        if(h == 1)
            System.out.println(" Hamming Solution: ");
        else
            System.out.println(" Manhattan Solution: ");
        Stack<BoardNode> boardStack = new Stack<BoardNode>();
        
        int depth = 0;

        while (finalNode != null) {
            depth++;
            boardStack.push(finalNode);
            finalNode = finalNode.getParent();
        }
        while (!boardStack.isEmpty()) {
            System.out.println("  " + boardStack.pop());
        }
        System.out.println("  Number of boards created: " + numNode);
        return --depth;
    }
}
