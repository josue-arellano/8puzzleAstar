/**
 *   Name:      Arellano, Josue
 *   File:      Main.java
 *   Project:   #1
 *   Due:       Sep 18, 2018
 *   Course:    cs14103-w18
 *
 *   Description:
 *              This
 */
package pkg420;

import java.util.Scanner;
import java.io.*;


public class Main {

    public static void main(String[] args) throws Exception {
//        dataTest();
        char choice;
        do {
            System.out.println("Menu: \n 1.Generate a random puzzle. \n"
                    + " 2.Enter a specific puzzle where '0' represents the empty tile.");
            Scanner keyboard = new Scanner(System.in);
            System.out.print("Enter 1 or 2: ");
            choice = keyboard.nextLine().charAt(0);
        } while (choice != '1' && choice != '2');
        Game game;
        switch (choice) {
            case '1':
                game = new Game();
                break;
            case '2':
                String inputPuzzle;
                do {
                    inputPuzzle = requestPuzzle();
                } while (!Board.check(inputPuzzle));
                game = new Game(new Board(inputPuzzle));
                break;
            default:
                game = new Game();
                System.out.println("Error");
                break;
        }
        game.start();
    }
    
    private static void dataTest() throws Exception {
        System.out.println("Running 400 time: ");
        BoardSolutionTest data;
        PrintWriter dataWriter = null;
        try {
            dataWriter = new PrintWriter("puzzle8.txt", "UTF-8");
            for (int j = 2; j < 40; j++, j++) {
                dataWriter.println("depth: " + j);
                for (int i = 0; i < 200; i++) {
                    int[] tempBoard = Board.generateRandom(j);
                    if (!Board.isGoal(tempBoard)) {
                        Game game = new Game(new Board(tempBoard));
                        final long startTime = System.currentTimeMillis();
                        final long h2Time = game.start();
                        final long h1Time = System.currentTimeMillis() - h2Time;
                        data = game.getData();
                        System.out.print(data);
                        dataWriter.println(i + "\t" + data.toString() + "\t" + (h2Time - startTime) + "\t" + h1Time);
                    }
                }
            }
            dataWriter.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (dataWriter != null)
                dataWriter.close();
        }
        System.out.println("Welcome to the 8 puzzle game!");
        System.out.println("Set of input puzzles: ");        
    }

    private static String requestPuzzle() {
        System.out.print("Enter 0-8 in any order with spaces in between each number: ");
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextLine();
    }
}
