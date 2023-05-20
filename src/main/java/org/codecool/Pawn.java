package org.codecool;

public class Pawn
{
    private final PawnColor color;
    private Coordinates position;
    private boolean isCrowned;

    private final char sign;

    Pawn(PawnColor color, Coordinates position)
    {
        this.color = color;
        this.position = position;
        this.isCrowned = false;
        if(color == PawnColor.WHITE)
        {
            sign = 'O';
        }
        else
        {
            sign = 'X';
        }
    }

    public Coordinates getPosition()
    {
        return position;
    }

    public boolean isCrowned()
    {
        return isCrowned;
    }

    public PawnColor getColor()
    {
        return color;
    }

    char getSign()
    {
        return sign;
    }

    void setPosition(Coordinates position)
    {
        this.position = position;
    }

    boolean canMove(Coordinates target)
    {
        int distance = 1;
        int px = position.getX();
        int py = position.getY();
        int tx = target.getX();
        int ty = target.getY();

        // Right down
        if(px == tx + distance && py == ty + distance)
        {
            return true;
        }

        // Right up
        if(px == tx + distance && py == ty - distance)
        {
            return true;
        }

        // Left up
        if(px == tx - distance && py == ty - distance)
        {
            return true;
        }

        // Left down
        if(px == tx - distance && py == ty + distance)
        {
            return true;
        }

        return false;
    }
}
