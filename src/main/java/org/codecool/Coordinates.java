package org.codecool;

public class Coordinates
{
    public int x;
    public int y;
    private String position;

    private static final int INT_TO_STRING = 65;

    Coordinates(int x, int y)
    {
        set(x, y);
    }

    Coordinates(String position)
    {
        set(position);
    }

    public void set(int x, int y)
    {
        this.x = x;
        this.y = y;
        char letter = (char) (x + INT_TO_STRING);
        this.position = letter + String.valueOf(y + 1);
    }

    public void set(String position)
    {
        this.position = position;
        char letter = position.charAt(0);
        this.x = letter - INT_TO_STRING;
        this.y = (Integer.parseInt(position.substring(1))-1);
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getBoardX()
    {
        return x-1;
    }

    public int getBoardY()
    {
        return y-1;
    }

    public int[] getBoardArray()
    {
        return new int[] {x-1, y-1};
    }

    public int[] getArray()
    {
        return new int[] {x, y};
    }

    public String getString()
    {
        return position;
    }

    private boolean isStringCorrect(String position)
    {
        if(position.length()!= 2)
        {
            return false;
        }

        char firstCharacter = position.charAt(0);
        char secondCharacter = position.charAt(1);

        return Character.isLetter(firstCharacter) && Character.isDigit(secondCharacter);
    }
}
