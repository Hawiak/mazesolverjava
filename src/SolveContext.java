import java.util.ArrayList;

/**
 * Created by harmakkerman on 2/26/17.
 */
public class SolveContext {
    private SolveStrategy strategy;

    public SolveContext(SolveStrategy strategy) {
        this.strategy = strategy;
    }

    public ArrayList<Node> solve(Maze maze) {
        return this.strategy.solve(maze);
    }
}
