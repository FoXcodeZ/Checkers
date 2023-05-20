package org.codecool;

import java.util.Objects;

public class Board
{
    private Board()
    {
    }

    private static Board instance;
    public Pawn[][] fields;
    private String[][] fieldsColors;
    private int length;

    void setBackgroundColor(Coordinates cord, String color)
    {
        fieldsColors[cord.getX()][cord.getY()] = color;
    }

    public static Board getInstance()
    {
        if(instance == null)
        {
            instance = new Board();
        }
        return instance;
    }

    void init(int length)
    {
        this.length = length;
        this.fields = new Pawn[length][length];
        this.fieldsColors = new String[length][length];
        setupFieldsColors();
        setupPawns();
    }

    public boolean isPlaceOccupied(Coordinates coordinates)
    {
        return fields[coordinates.getX()][coordinates.getY()]!= null;
    }

    public void movePawn(Coordinates origin, Coordinates destination)
    {
        fields[destination.getX()][destination.getY()] = fields[origin.getX()][origin.getY()];
        removePawn(origin);
    }



    String[][] getFieldsColors()
    {
        return fieldsColors;
    }

    private void setupPawns()
    {
        for(int y = 0; y < length; y++)
        {
            for(int x = 0; x < length; x++)
            {
                if(Objects.equals(fieldsColors[x][y], ConsoleColor.BACKGROUND_BLACK))
                {
                    Coordinates cord = new Coordinates(x, y);
                    if(y > (length / 2))
                    {
                        Pawn pawn = new Pawn(PawnColor.WHITE, cord);
                        placePawn(pawn);
                    }
                    else if(y < ((length / 2) - 1))
                    {
                        Pawn pawn = new Pawn(PawnColor.BLACK, cord);
                        placePawn(pawn);
                    }
                }
            }
        }
    }

    private void setupFieldsColors()
    {
        boolean oddLines = false;
        String sign = "   ";

        for(int y = length-1; y >= 0; y--)
        {

            for(int x = length-1; x >= 0; x--)
            {

                if(oddLines)
                {
                    if(x % 2 == 0)
                    {
                        fieldsColors[x][y] = ConsoleColor.BACKGROUND_WHITE;
                    }
                    else
                    {
                        fieldsColors[x][y] = ConsoleColor.BACKGROUND_BLACK;
                    }
                }
                else
                {
                    if(x % 2 == 1)
                    {
                        fieldsColors[x][y] = ConsoleColor.BACKGROUND_WHITE;
                    }
                    else
                    {
                        fieldsColors[x][y] = ConsoleColor.BACKGROUND_BLACK;
                    }
                }
            }
            oddLines = !oddLines;
        }
    }

    public int getLength()
    {
        return length;
    }

    public void placePawn(Pawn pawn)
    {
        Coordinates position = pawn.getPosition();
        int x = position.getX();
        int y = position.getY();
        fields[x][y] = pawn;
        Console console = Console.getInstance();
    }

    public boolean removePawn(Coordinates coordinates)
    {
        if (!isPlaceOccupied(coordinates))
        {
            return false;
        }
        fields[coordinates.getX()][coordinates.getY()] = null;
        return true;
    }

    Pawn getPawn(int x, int y)
    {
        return fields[x-1][y-1];
    }

    public boolean canMove(Coordinates origin, Coordinates target)
    {
        return fields[origin.getX()][origin.getY()].canMove(target);
    }

}
