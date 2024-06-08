package src;

public class Graph {
    
    private Node[][] table;
    private int rows, cols;
    
    public Graph(int rows, int cols)
    {
        this.rows = rows;
        this.cols = cols;
        this.table = new Node[rows][cols];
        for(int i = 0; i < rows; i++)
            for(int j = 0; j < cols; j++)
                table[i][j] = new Node(i, j, false, 1);
    }

    public Node getNode(int row, int col)
    {
        return table[row][col];
    }

    public void setWall(int row, int col, boolean isWall)
    {
        table[row][col].setWall(isWall);
    }
    
    public int getRows() { return rows; }
    public int getCols() { return cols; }
    
}
