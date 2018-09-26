/**
 *   Name:      Arellano, Josue
 *   File:      BoardSolutionTest.java
 *   Project:   #1
 *   Due:       Sep 23, 2018
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
public class BoardSolutionTest {
    private int depth;
    private int h1Cost;
    private int h2Cost;
    
    public BoardSolutionTest() {
        this(0, 0, 0);
    }
    
    public BoardSolutionTest(int depth, int h1Cost, int h2Cost) {
        this.depth = depth;
        this.h1Cost = h1Cost;
        this.h2Cost = h2Cost;
    }

    /**
     * @return the depth
     */
    public int getDepth() {
        return depth;
    }

    /**
     * @param depth the depth to set
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }

    /**
     * @return the h1Cost
     */
    public int getH1Cost() {
        return h1Cost;
    }

    /**
     * @param h1Cost the h1Cost to set
     */
    public void setH1Cost(int h1Cost) {
        this.h1Cost = h1Cost;
    }

    /**
     * @return the h2Cost
     */
    public int getH2Cost() {
        return h2Cost;
    }

    /**
     * @param h2Cost the h2Cost to set
     */
    public void setH2Cost(int h2Cost) {
        this.h2Cost = h2Cost;
    }
    
    public String toString() {
        return depth + "\t" + h1Cost + "\t" + h2Cost;
    }
}
