package org.codecool;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();
        Console console = Console.getInstance();

        int boardSize;

        do
        {
            console.clear();
            console.writeLine("The board size has to be between 10 and 20 and has to be an even number.", ConsoleColor.TEXT_RED);
            console.write("Set the board size: ");

            boardSize = scanner.nextInt();
        } while(boardSize < 10 || boardSize > 20 || boardSize % 2 != 0);

        game.start(boardSize);

        while(!game.checkForWinner())
        {
            game.playRound();
        }
    }
}