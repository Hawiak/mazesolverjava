/**
 * Created by harmakkerman on 2/26/17.
 */
public class Node {
    public Node north;
    public Node south;
    public Node west;
    public Node east;

    public int xPos = 0;
    public int yPos = 0;



    public Node(int yPos, int xPos)
    {
        this.xPos = xPos;
        this.yPos = yPos;
    }
}
