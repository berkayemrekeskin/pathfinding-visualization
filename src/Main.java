package src;

import javax.swing.JFrame;

public class Main {
    
    public static void main(String[] args) {

        Graph graph = new Graph(10, 10);
        PathFindingVisualizer visualizer = new PathFindingVisualizer(graph);

        visualizer.frame = new JFrame("Path Finding Visualizer");
        visualizer.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        visualizer.frame.setSize(480, 500);
        visualizer.frame.add(visualizer);
        visualizer.frame.setVisible(true);
        visualizer.requestFocus();

    }
}
