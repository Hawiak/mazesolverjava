import java.util.Arrays;
import java.util.List;

/**
 * Created by harmakkerman on 2/26/17.
 */
public class Maze {

    public int[][] maze;

    public int height;
    public int width;

    public int[] begin = new int[2];
    public int[] end = new int[2];

    public List<Node> nodes;

    public Node lastNode = null;

    public void setMaze(int[][] maze)
    {
        this.maze = maze;

        this.setDimensionAccessors();
        this.determineBegin();
        this.determineEnd();
    }

    // Conveniant way to print the maze
    public void print()
    {
        for (int i = 0; i < this.maze.length; i ++)
        {
            System.out.println(Arrays.toString(this.maze[i]));
        }
    }

    public int getValue(int yPos, int xPos)
    {
        return this.maze[yPos][xPos];
    }

    // Easy accessors to determine the height and width of the maze in PXs
    private void setDimensionAccessors()
    {
        if (this.maze.length > 0){
            this.height = this.maze[0].length;
            this.width = this.maze.length;
        }
    }

    // Get Y, X coordinates of the beginning of the maze
    private void determineBegin()
    {
        for(int i = 0; i < this.maze[0].length; i++)
        {
            if (this.maze[0][i] == 0)
            {
                this.begin[0] = 0;
                this.begin[1] = i;
                break;
            }

        }
    }

    // Get Y, X coordinates of the end of the maze
    private void determineEnd()
    {

        int endArray = this.maze.length - 1;
        for(int i = 0; i < this.maze[endArray].length; i++)
        {
            if (this.maze[endArray][i] == 0)
            {
                this.end[0] = endArray;
                this.end[1] = i;
            }
        }
    }

    public Node createNode(int y, int x)
    {
        Node node = new Node(y,x);
        this.addNode(node);
        this.setLastNode(node);
        return node;
    }

    public void addNode(Node node)
    {
        if (this.lastNode != null) {
            node.west = this.getLastNode();
        }
        this.nodes.add(node);
    }

    public List<Node> getNodes()
    {
        return this.nodes;
    }

    public Node setLastNode(Node node)
    {
        this.lastNode = node;
        return this.lastNode;
    }

    public Node getLastNode()
    {
        return this.lastNode;
    }


}
