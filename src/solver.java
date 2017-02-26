import java.io.IOException;
import java.util.Arrays;

public class solver {
    public static void main(String [] args)
    {
        solver solver = new solver();
        Maze maze = solver.fetchMazeMap();

        solver.mapNodes(maze);
    }

    public Maze fetchMazeMap()
    {
        ImageProvider imageProvider = new ImageProvider();

        byte[] imageBytemap = new byte[0];

        try {
            imageBytemap = imageProvider.extractBytes("tiny.png");
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

        int imageHeight = imageProvider.bufferedImage.getHeight();
        int imageWidth = imageProvider.bufferedImage.getWidth();


        int[][] mazeArr = new int[imageWidth][imageHeight];

        for (int y = 0; y < imageWidth; y++) {
            mazeArr[y] = new int[imageHeight];
            for (int x = 0; x < imageWidth; x++) {
                int black = 0;
                final int clr = imageProvider.bufferedImage.getRGB(x, y);
                if (clr == -16777216) {
                    black = 1;
                } else if (clr == -1){
                }

                mazeArr[y][x] = black;
            }
        }

        Maze maze = new Maze();
        maze.setMaze(mazeArr);

        maze.print();

        System.out.print(Arrays.toString(maze.begin));
        System.out.print(Arrays.toString(maze.end));

        return maze;
    }

    public void mapNodes(Maze maze)
    {
        for (int y = 0; y < maze.width; y++)
        {
            Node leftnode = null;

            for (int x = 0; x < maze.maze[y].length; x++)
            {
                int filledIn = maze.getValue(y, x);

                // If location is a wall, do nothing
                if (filledIn != 1) {
                    // Not the top row, check for additional values
                    if (y > 0){
                        int prev;
                        int next;
                        int up;
                        int down;

                        // Get previous position value
                        if (x > 0){
                            prev = maze.maze[y][x - 1];
                        } else {
                            prev = 1;
                        }

                        // Get next position value
                        if (x < maze.width) {
                            next = maze.maze[y][x + 1];
                        } else {
                            next = 1;
                        }

                        // Get position up value
                        if (y > 0) {
                            up = maze.maze[y - 1][x];
                        } else {
                            up = 1;
                        }

                        // Get position down value
                        if (y < maze.height) {
                            down = maze.maze[y + 1][x];
                        } else {
                            down = 1;
                        }




                        if (prev == 0) {
                            if (next == 0)
                            // Path, only create new node when theres a path above and down

                            if (up == 0 || down == 0) {
                                maze.createNode(y, x);
                            }
                            // Previous is a wall, we'll need a new node
                        } else {
                            if (next == 0) {
                                maze.createNode(y, x);
                            }
                        }




                    } else {
                        maze.createNode(y, x);
                    }
                }

                leftnode = maze.getLastNode();
            }
        }
    }
}
