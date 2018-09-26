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
import java.util.Vector;
import java.util.Arrays;
/**
 *
 * @author josue
 */
public class Game
{
    private Board initBoard;
    private PriorityQueue<BoardNode> hammingPQ;
    private PriorityQueue<BoardNode> manhattanPQ;
    private Vector<BoardNode> exploredBoards;
    private BoardSolutionTest data;
    
    public Game() {
        this(new Board());
    }
    
    public Game(Board initBoard) {
        this.initBoard = initBoard;
        System.out.println("Generated board: \n" + initBoard);
    }
    
    public long start() {
        hammingPQ = new PriorityQueue<>();
        manhattanPQ = new PriorityQueue<>();
        data = new BoardSolutionTest();
        exploredBoards = new Vector<>();
        
        int tempDepth = aStar(manhattanPQ, initBoard, 2);
        long currentTime = System.currentTimeMillis();
        System.out.println("  depth: " + tempDepth);
        System.out.println("  depth: " + aStar(hammingPQ, initBoard, 1));
        System.out.println(data);
        return currentTime;
    }
    
    private int aStar(PriorityQueue<BoardNode> PQ, Board initBoard, int h) {
        BoardNode initBoardNode = new BoardNode(initBoard);
        initBoardNode.setCost(0);
        if(h == 1) initBoardNode.setF1();
        else initBoardNode.setF2();
        PQ.add(initBoardNode);
        boolean done = false;
        BoardNode finalNode = null;
        int numNode = 0;
        
        while (!done && !PQ.isEmpty()) {
            BoardNode current = PQ.poll();
            exploredBoards.add(current);
            if (current != null) {
                if (Board.isGoal(current.getBoardArray())) {
                    finalNode = current;
                    done = true;
                }
                if (!done) {
                    int zeroPos = current.getBoard().findZero();
                    int x = zeroPos % 3;
                    int y = zeroPos / 3;
                    if (Board.moveCheck(x - 1, y)) {
                        int[] childBoardArr = Board.moveZero(current.getBoard(), zeroPos, zeroPos - 1);
                        if(!isExplored(childBoardArr)){
                            Board childBoard = new Board(childBoardArr);
                            BoardNode childBoardNode = new BoardNode(childBoard, current.getCost() + 1, current);
                            if (h == 1)
                                childBoardNode.setF1();
                            else
                                childBoardNode.setF2();
                            PQ.add(childBoardNode);
                            numNode++;                            
                        }
                    }
                    if (Board.moveCheck(x + 1, y)) {
                        int[] childBoardArr = Board.moveZero(current.getBoard(), zeroPos, zeroPos + 1);
                        if (!isExplored(childBoardArr)) {
                            Board childBoard = new Board(childBoardArr);
                            BoardNode childBoardNode = new BoardNode(childBoard, current.getCost() + 1, current);
                            if (h == 1)
                                childBoardNode.setF1();
                            else
                                childBoardNode.setF2();
                            PQ.add(childBoardNode);
                            numNode++;
                        }
                    }
                    if (Board.moveCheck(x, y - 1)) {
                        int[] childBoardArr = Board.moveZero(current.getBoard(), zeroPos, zeroPos - 3);
                        if (!isExplored(childBoardArr)) {
                            Board childBoard = new Board(childBoardArr);
                            BoardNode childBoardNode = new BoardNode(childBoard, current.getCost() + 1, current);
                            if (h == 1)
                                childBoardNode.setF1();
                            else
                                childBoardNode.setF2();
                            PQ.add(childBoardNode);
                            numNode++;
                        }
                    }
                    if (Board.moveCheck(x, y + 1)) {
                        int[] childBoardArr = Board.moveZero(current.getBoard(), zeroPos, zeroPos + 3);
                        if (!isExplored(childBoardArr)) {
                            Board childBoard = new Board(childBoardArr);
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
        }
        exploredBoards.clear();
        System.out.println();
        if(h == 1)
            System.out.println(" Hamming Solution: ");
        else
            System.out.println(" Manhattan Solution: ");
        Stack<BoardNode> boardStack = new Stack<>();
        
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
        if(h == 1) {
            data.setDepth(depth);
            data.setH1Cost(numNode);
        }
        else {
            data.setH2Cost(numNode);
        }
        return depth;
    }
    
    private boolean isExplored(int[] board) {
        for(int i = 0; i < exploredBoards.size(); i++) {
            if(Arrays.equals(exploredBoards.get(i).getBoardArray(), board))
                return true;
        }
        return false;
    }
    
    public BoardSolutionTest getData() {
        return data;
    }
}
