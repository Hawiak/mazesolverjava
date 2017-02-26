import java.util.*;

/**
 * Created by harmakkerman on 2/26/17.
 */
public class depthfirst implements SolveStrategy{

    private List<Node> visited = new ArrayList<Node>();

    public ArrayList<Node> path = new ArrayList<Node>();


    public ArrayList<Node> solve(Maze maze) {
        Node start = maze.start;
        Node end = maze.end;

        boolean completed = false;

        Node current = start;


        while (completed == false) {
            Node next = this.getNext(current);

            // We've hit a dead end
            if (next == null) {
                this.visited.add(current);
            } else {
                this.path.add(current);
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

        if (node.south != null && !this.visited.contains(node.south)) {
            return node.south;
        } else if (node.west != null && !this.visited.contains(node.west)) {
            return node.west;
        } else if (node.east != null && !this.visited.contains(node.west)) {
            return node.east;
        } else {
            return null;
        }
    }
}
