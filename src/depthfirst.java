import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by harmakkerman on 2/26/17.
 */
public class depthfirst implements SolveStrategy{
    public void solve(Maze maze) {

            Node start = maze.start;
            Node end = maze.end;
            int width = maze.width;
            Deque stack = new ArrayDeque<Node>();
            boolean completed = false;
            int count = 0;

            stack.add(start);

            while(stack) {
                count++;



                if (current == end) {
                    completed = true;
                    break;
                }
            }


    }
}
