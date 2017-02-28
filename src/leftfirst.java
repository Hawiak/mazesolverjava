import java.util.*;

/**
 * Created by harmakkerman on 2/26/17.
 */
public class leftfirst implements SolveStrategy{

    private List<Node> visited = new ArrayList<Node>();

    public ArrayList<Node> path = new ArrayList<Node>();

    public Stack<Node> nodesTried;


    public ArrayList<Node> solve(Maze maze) {

        this.nodesTried = new Stack<Node>();
        Node start = maze.start;
        Node end = maze.end;

        boolean completed = false;

        Node current = start;

        this.nodesTried.add(current);
        this.path.add(current);


        int count = 0;


        while (completed == false) {
            Node next = this.getNext(current);

            if (count < 3) {
                System.out.println("C: " + count);
                System.out.println("North: " + current.north);
                System.out.println("east: " + current.east);
                System.out.println("south: " + current.south);
                System.out.println("west: " + current.west);
            }
            count++;

            // We've hit a dead end
            if (next == null) {
                this.nodesTried.pop();
                this.visited.add(current);
                current = this.nodesTried.peek();
            } else {
                this.path.add(current);
                this.nodesTried.add(current);
            }

            if (next == end) {
                completed = true;
            } else {
                current = next;
            }
        }

        return this.path;

    }

    public Node getNext(Node node) {
        // South > West > east

        if (node.west != null && !this.visited.contains(node.west)) {
            return node.west;
        } else if (node.south != null && !this.visited.contains(node.south)) {
            return node.south;
        } else if (node.east != null && !this.visited.contains(node.west)) {
            return node.east;
        } else {
            return null;
        }
    }
}
