package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

public class PathFindingVisualizer extends JPanel {
    private final Graph graph;
    private Node start, end;
    private static final int NodeSize = 40;
    public JFrame frame;

    public PathFindingVisualizer(Graph graph) {
        this.graph = graph;
        this.start = graph.getNode(0, 0);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = e.getY() / NodeSize;
                int col = e.getX() / NodeSize;

                if (SwingUtilities.isLeftMouseButton(e)) {
                    graph.setWall(row, col, true);
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    graph.setWall(row, col, false);
                }
                else if(SwingUtilities.isMiddleMouseButton(e))
                {
                    end = graph.getNode(row, col);
                }
                repaint();
            }
        });

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
            }

            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if(e.getKeyCode() == java.awt.event.KeyEvent.VK_D)
                {
                    DepthFirstSearch(graph, start, end, getGraphics());
                }
                else if(e.getKeyCode() == java.awt.event.KeyEvent.VK_B)
                {
                    BreadthFirstSearch(graph, start, end, getGraphics());
                }
                else if(e.getKeyCode() == java.awt.event.KeyEvent.VK_C)
                {
                    for(int i = 0; i < graph.getRows(); i++)
                        for(int j = 0; j < graph.getCols(); j++)
                            graph.setWall(i, j, false);
                    end = null;
                    repaint();
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {

            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < graph.getRows(); i++) {
            for (int j = 0; j < graph.getCols(); j++) {
                Node node = graph.getNode(i, j);
                if (node.getWall()) {
                    g.setColor(Color.BLACK);
                } else if (node.equals(start)) {
                    g.setColor(Color.GREEN);
                } else if (node.equals(end)) {
                    g.setColor(Color.RED);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(j * NodeSize, i * NodeSize, NodeSize, NodeSize);
                g.setColor(Color.GRAY);
                g.drawRect(j * NodeSize, i * NodeSize, NodeSize, NodeSize);
            }
        }
    }

    public void BreadthFirstSearch(Graph graph, Node start, Node end, Graphics g)
    {
        Queue<Node> queue = new LinkedList<Node>();
        boolean[][] visited = new boolean[graph.getRows()][graph.getCols()];
        
        queue.offer(start);
        visited[start.getRow()][start.getCol()] = true;
        
        int[][] directions = {{-1,0}, {1,0}, {0,1}, {0,-1}};

        while(!queue.isEmpty())
        {
            Node current = queue.poll();
            if(current.equals(end))
                break;

            g.setColor(Color.BLUE);
            g.fillRect(current.getCol() * NodeSize, current.getRow() * NodeSize, NodeSize, NodeSize);
            g.setColor(Color.GRAY);
            g.drawRect(current.getCol() * NodeSize, current.getRow() * NodeSize, NodeSize, NodeSize);
            
            for(int[] direction : directions)
            {
                int nextRow = current.getRow() + direction[0];
                int nextCol = current.getCol() + direction[1];

                if(isValid(graph, nextRow, nextCol, visited))
                {
                    Node neighbour = graph.getNode(nextRow, nextCol);
                    neighbour.setParent(current);
                    visited[nextRow][nextCol] = true;
                    queue.offer(neighbour);
                }
                try {
                    Thread.sleep(50); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        Node current = end;
        while(current != null)
        {
            g.setColor(Color.YELLOW);
            g.fillRect(current.getCol() * NodeSize, current.getRow() * NodeSize, NodeSize, NodeSize);
            g.setColor(Color.GRAY);
            g.drawRect(current.getCol() * NodeSize, current.getRow() * NodeSize, NodeSize, NodeSize);
            current = current.getParent();
        }
    }

    public void DepthFirstSearch(Graph graph, Node start, Node end, Graphics g)
    {
        Stack<Node> stack = new Stack<>();
        boolean[][] visited = new boolean[graph.getRows()][graph.getCols()];

        stack.add(start);
        visited[start.getRow()][start.getCol()] = true;

        int[][] directions = {{-1,0}, {1,0}, {0,1}, {0,-1}};

        while(!stack.isEmpty())
        {
            Node current = stack.pop();

            g.setColor(Color.BLUE);
            g.fillRect(current.getCol() * NodeSize, current.getRow() * NodeSize, NodeSize, NodeSize);
            g.setColor(Color.GRAY);
            g.drawRect(current.getCol() * NodeSize, current.getRow() * NodeSize, NodeSize, NodeSize);

            if(current.equals(end))
                break;
            for(int[] direction : directions)
            {
                int nextRow = current.getRow() + direction[0];
                int nextCol = current.getCol() + direction[1];

                if(isValid(graph, nextRow, nextCol, visited))
                {
                    Node neighbour = graph.getNode(nextRow, nextCol);
                    neighbour.setParent(current);
                    visited[nextRow][nextCol] = true;
                    stack.add(neighbour);
            }
                try {
                    Thread.sleep(50); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        Node current = end;
        while(current != null)
        {
            g.setColor(Color.YELLOW);
            g.fillRect(current.getCol() * NodeSize, current.getRow() * NodeSize, NodeSize, NodeSize);
            g.setColor(Color.GRAY);
            g.drawRect(current.getCol() * NodeSize, current.getRow() * NodeSize, NodeSize, NodeSize);
            current = current.getParent();
        }
    }
    
    private boolean isValid(Graph graph, int row, int col, boolean[][] visited)
    {
        return row >= 0 && row < graph.getRows() && col >= 0 && col < graph.getCols() && !graph.getNode(row, col).getWall() && !visited[row][col];
    }

}
