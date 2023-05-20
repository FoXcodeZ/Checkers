package org.codecool;

public class Game
{
    private final Board board = Board.getInstance();
    private final Console console = Console.getInstance();

    void start(int boardSize)
    {
        board.init(boardSize);
    }

    public boolean tryToMakeMove(String move)
    {
        String[] parts = move.split("-");
        Coordinates origin = new Coordinates(parts[0]);
        Coordinates target = new Coordinates(parts[1]);

        if(!canDoMove(origin, target))
        {
            return false;
        }
        if(!board.isPlaceOccupied(origin) || board.isPlaceOccupied(target)) {
            return false;
        }

        if (!board.canMove(origin, target))
        {
            return false;
        }

        try
        {
            console.clear();
            board.setBackgroundColor(origin, ConsoleColor.BACKGROUND_YELLOW);
            console.printGameView();
            Thread.sleep(500);

            console.clear();
            board.setBackgroundColor(origin, ConsoleColor.BACKGROUND_BLACK);
            console.printGameView();

            console.clear();
            board.movePawn(origin, target);
            board.setBackgroundColor(target, ConsoleColor.BACKGROUND_YELLOW);
            console.printGameView();
            Thread.sleep(500);
            board.setBackgroundColor(target, ConsoleColor.BACKGROUND_BLACK);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }


        return true;
    }

    boolean canDoMove(Coordinates origin, Coordinates target)
    {
        if(origin.getX() < 0 || origin.getX() > (board.getLength() - 1))
        {
            return false;
        }
        if(origin.getY() < 0 || origin.getY() > (board.getLength() - 1))
        {
            return false;
        }
        if(target.getX() < 0 || target.getX() > (board.getLength() - 1))
        {
            return false;
        }
        if(target.getY() < 0 || target.getY() > (board.getLength() - 1))
        {
            return false;
        }

        return true;
    }

    boolean checkForWinner()
    {
        return false;
    }

    void playRound()
    {
        console.clear();
        console.printGameView();
        console.writeLine("You can write your move like this: <origin>-<destination>");
        console.writeLine("For example if you want to move from B7 to C6, just write B7-C6");
        console.write("Your move: ", ConsoleColor.TEXT_RED);
        String move = console.readLine();

        while(!tryToMakeMove(move))
        {
            console.writeLine("Wrong move!", ConsoleColor.TEXT_RED);
            move = console.readLine();
        }
    }
}
