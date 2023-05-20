package org.codecool;
import java.io.IOException;
import java.util.Scanner;

public class Console
{
    private static Console instance;
    private String[] clearCommand;

    private final Board board;
    private final Scanner scanner = new Scanner(System.in);

    private Console()
    {
        board = Board.getInstance();
    }

    public static Console getInstance()
    {
        if(instance == null)
        {
            instance = new Console();
            instance.setupClearCommand();
        }
        return instance;
    }

    private void setupClearCommand()
    {
        String osName = System.getProperty("os.name");
        if(osName.startsWith("Windows"))
        {
            clearCommand = new String[] {"cmd", "/c", "cls"};
        }
        else // macOS and Linux
        {
            clearCommand = new String[] {"clear"};
        }
    }

    public void clear()
    {
        try
        {
            new ProcessBuilder(clearCommand).inheritIO().start().waitFor();
        }
        catch (IOException | InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void write(String text)
    {
        System.out.print(text);
    }

    public void write(String text, String color)
    {
        String message = color + text + ConsoleColor.RESET;
        System.out.print(message);
    }

    public void writeLine(String text)
    {
        System.out.println(text);
    }

    public void writeLine(String text, String color)
    {
        String message = color + text + ConsoleColor.RESET;
        System.out.println(message);
    }

    public void printGameView()
    {
        printLettersLine();
        printBoard(board);
        printLettersLine();
    }

    private void printBoard(Board board)
    {
        int boardLength = board.getLength();
        String[][] fieldsColors = board.getFieldsColors();

        for(int y = 0; y < boardLength; y++)
        {
            drawLeftNumber(y+1);

            for(int x = 0; x < boardLength; x++)
            {
                if(board.fields[x][y] != null)
                {
                    String sign = " " + board.fields[x][y].getSign() + " ";
                    write(sign, fieldsColors[x][y]);
                }
                else
                {
                    write("   ", fieldsColors[x][y]);
                }


            }
            drawRightNumber(y+1);
            writeLine("");
        }
    }

    private void drawLeftNumber(int y)
    {
        if(y >= 10)
        {
            write(y + "  ", ConsoleColor.RESET);
        }
        else
        {
            write(" " + y + "  ", ConsoleColor.RESET);
        }
    }

    private void drawRightNumber(int y)
    {
        if(y >= 10)
        {
            write("  " + y, ConsoleColor.RESET);
        }
        else
        {
            write("  " + y + " ", ConsoleColor.RESET);
        }
    }


    private void printLettersLine()
    {
        int boardLength = board.getLength();
        write("    ");
        for(int i = 65; i < boardLength + 65; i++)
        {
            char letter = (char) i;
            System.out.print(" " + letter + " ");

        }
        write("", ConsoleColor.RESET);
        write("\n");
    }

    public int readInt()
    {
        return scanner.nextInt();
    }

    public String readLine()
    {
        return scanner.nextLine();
    }
}
