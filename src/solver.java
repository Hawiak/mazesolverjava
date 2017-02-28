import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class solver {

    public Image image;

    public static void main(String [] args)
    {
        solver solver = new solver();
        Maze maze = solver.fetchMazeMap();

        solver.mapNodes(maze);

        // Strategy pattern implementation
        SolveContext solve = new SolveContext(new depthfirst());
        ArrayList<Node> path = solve.solve(maze);

        solver.generateImage(maze, path);



        System.out.println("Nodes created: " + maze.nodes.size());
    }

    public Maze fetchMazeMap()
    {
        this.image = new Image();

        byte[] imageBytemap = new byte[0];

        try {
            imageBytemap = this.image.extractBytes("tiny.png");
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

        int imageHeight = this.image.bufferedImage.getHeight();
        int imageWidth = this.image.bufferedImage.getWidth();


        int[][] mazeArr = new int[imageWidth][imageHeight];

        for (int y = 0; y < imageWidth; y++) {
            mazeArr[y] = new int[imageHeight];
            for (int x = 0; x < imageWidth; x++) {
                int black = 0;
                final int clr = this.image.bufferedImage.getRGB(x, y);
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
                    if (y > 0 && y < (maze.height - 1)){
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
                        if (y < (maze.height - 1)) {
                            down = maze.maze[y + 1][x];
                        } else {
                            down = 1;
                        }


                        /**
                         * Solving logic
                         */
                        if (prev == 0) {
                            if (next == 0) {
                                // Path, only create new node when theres a path above and down

                                if (up == 0 || down == 0) {
                                    maze.createNode(y, x);
                                }
                            } else {
                                // Prev = 0, next = 1. We're hitting a wall, create a node
                                maze.createNode(y, x);
                            }

                            // Previous is a wall, we'll need a new node
                        } else {
                            if (next == 0) {
                                maze.createNode(y, x);
                            } else {
                                if (up == 1 || down == 1) {
                                    maze.createNode(y, x);
                                }
                            }
                        }

                    } else if (y == 0) {
                        // Start node
                        Node startNode = maze.createNode(y, x);
                        maze.start = startNode;

                    } else if (y == (maze.height - 1)) {
                        // End node
                        Node endNode = maze.createNode(y, x);
                        maze.end = endNode;
                    }
                }

                leftnode = maze.getLastNode();
            }
        }
    }

    public void generateImage(Maze maze, ArrayList<Node> path)
    {

        List<Point2D> nodePositions = new ArrayList<Point2D>();

        for (Node node: path){

            Point2D n = new Point2D.Double(node.xPos, node.yPos);
            nodePositions.add(n);
        }

        BufferedImage bufImg = this.image.bufferedImage;
        BufferedImage convertedImg = new BufferedImage(bufImg.getWidth(), bufImg.getHeight(), BufferedImage.TYPE_USHORT_565_RGB);
        convertedImg.getGraphics().drawImage(bufImg, 0, 0, null);

        for (int i = 0; i < path.size(); i++) {
            Node curNode = path.get(i);
            Node nextNode = null;

            if (i < (path.size() - 1)) {
                nextNode = path.get(i + 1);
            } else {
                nextNode = maze.end;
            }

            int x1 = curNode.xPos;
            int x2 = nextNode.xPos;

            int y1 = curNode.yPos;
            int y2 = nextNode.yPos;

            System.out.println("x1: " + x1 + ", y1: " + y1 + ", x2: " + x2 + ", y2: " + y2);

            int blue = ((255 / path.size()) * i);

            Color c = new Color(155, 0, blue);


            if (y1 != y2) {
                if (y1 < y2) {
                    for (int y = y1; y <= y2; y++) {
                        convertedImg.setRGB(x1, y, c.getRGB());
                    }
                } else {
                    for (int y = y2; y >= y1; y--) {
                        convertedImg.setRGB(x1, y, c.getRGB());
                    }
                }
            }
            if (x1 != x2) {
                if (x1 < x2) {
                    for (int x = x1; x <= x2; x++) {
                        convertedImg.setRGB(x, y1, c.getRGB());
                    }
                } else {
                    for (int x = x1; x >= x2; x--) {
                        convertedImg.setRGB(x, y1, c.getRGB());
                    }
                }
            }
        }

        /*for (int y = 0; y < maze.height; y++) {
            for (int x = 0; x < maze.width; x ++) {
                int r = ((x / maze.width) * 255);

                Point2D check = new Point2D.Double(x, y);

                if (nodePositions.contains(check)) {
                    // Node position
                    Color c = new Color(r, 0, 255 - r);
                    newImage.setRGB(x, y, c.getRGB());
                } else {
                    if (maze.getValue(y, x) == 1) {
                        newImage.setRGB(x, y, Color.BLACK.getRGB());
                    } else {
                        newImage.setRGB(x, y, Color.WHITE.getRGB());
                    }
                }
            }
        }*/



        File outputfile = new File("test.png");
        try {
            ImageIO.write(convertedImg, "png", outputfile);
        } catch (IOException e) {

        }



    }
}
