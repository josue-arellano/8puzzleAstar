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
/**
 *
 * @author josue
 */
public class Main
{
public static void main(String[] args) {
    System.out.println("Welcome to the 8 puzzle game!");
    char choice;
    do {
      System.out.println("Menu: \n 1.Generate a random puzzle. \n" + 
            " 2.Enter a specific puzzle where '0' represents the empty tile.");
      Scanner keyboard = new Scanner(System.in);
      System.out.print("Enter 1 or 2: ");
      choice = keyboard.nextLine().charAt(0);
    } while(choice != '1' && choice != '2');
    Game game;
    switch(choice) {
      case '1':
        game = new Game();
        break;
      case '2':
        String inputPuzzle;
        do {
            inputPuzzle = requestPuzzle();
        } while(!Board.check(inputPuzzle));
        game = new Game(new Board(inputPuzzle));
        break;
      default:
        game = new Game();
        System.out.println("Error");
        break;
    }
    game.start();
  }
  
  private static String requestPuzzle() {
    System.out.print("Enter 0-8 in any order with spaces in between each number: ");
    Scanner keyboard = new Scanner(System.in);
    return keyboard.nextLine();
  }
}
