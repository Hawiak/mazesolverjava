/**
 * Created by harmakkerman on 2/26/17.
 */
public class SolveContext {
    private SolveStrategy strategy;

    public SolveContext(SolveStrategy strategy) {
        this.strategy = strategy;
    }

    public void solve(Maze maze) {
        this.strategy.solve(maze);
    }
}
