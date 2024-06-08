package src;

public class Node
{
    private int row, col;
    private boolean isWall;
    private Node parent;
    private int weight;

    public Node(int _row, int _col, boolean _isWall, int _weight)
    {
        this.row = _row;
        this.col = _col;
        this.isWall = _isWall;
        this.parent = null;
        this.weight = _weight;
    }

    public void setWall(boolean isWall) { this.isWall = isWall; }
    public boolean getWall() { return isWall; }
    public int getRow() { return row; }
    public void setRow(int row) { this.row = row; }
    public int getCol() { return col; }
    public void setCol(int col) { this.col = col; }
    public void setParent(Node parent) { this.parent = parent; }
    public Node getParent() { return parent; }
    public int getWeight() { return weight; }
    public void setWeight(int weight) { this.weight = weight; }

}