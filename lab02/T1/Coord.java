package lab01;

public class Coord {
    
    private int startX, startY, endX, endY;
    public Coord(int startX, int startY, int endX, int endY)
    {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }


    public String toString()
    {
        StringBuilder string = new StringBuilder();

        string.append(startX).append("x").append(startY).append(" to ");
        string.append(endX).append("x").append(endY);

        return string.toString();
    }
}

