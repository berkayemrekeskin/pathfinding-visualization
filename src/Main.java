package src;

import javax.swing.JFrame;

public class Main extends JFrame {
    
    

    public static void main(String[] args) {

        Graph graph = new Graph(24, 46);
        JFrame frame = new JFrame();
        PathFindingVisualizer visualizer = new PathFindingVisualizer(graph);

        frame = new JFrame("Path Finding ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.add(visualizer);
        frame.setVisible(true);

    }
}
