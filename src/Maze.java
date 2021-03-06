import java.util.*;

/**
 * Created by harmakkerman on 2/26/17.
 */
public class Maze {

    public int[][] maze;

    public int height;
    public int width;


    Node start = null;
    Node end = null;

    Map<Integer, Node> topRows = new HashMap<Integer, Node>();


    public List<Node> nodes = new ArrayList<Node>();

    public Node lastNode = null;

    public Maze()
    {
    }

    public void setMaze(int[][] maze)
    {
        this.maze = maze;

        this.setDimensionAccessors();
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

    public void addNewTopRow(int x, Node node) {
        this.topRows.put(x, node);
    }

    public Node getLowestTopRow(int x, Node otherNode) {
        Node node = this.topRows.get(x);

        int y1 = otherNode.yPos;

        if (node != null) {
            int y2 = node.yPos;
            boolean possible = true;

            for (int i = y1; i > y2; i--) {
                if (this.getValue(i, otherNode.xPos) == 1) {
                    possible = false;
                }
            }

            if (!possible) {
                return null;
            } else {
                return node;
            }
        } else {
            return null;
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

        Node northNode = this.getLowestTopRow(node.xPos, node);
        if (northNode != null) {
            node.north = northNode;
            northNode.south = node;
        }


        this.nodes.add(node);
        this.topRows.put(node.xPos, node);

        System.out.println("Node: on X: " + node.xPos + " and Y: " + node.yPos);
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
