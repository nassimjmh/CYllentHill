package cyllenthill.world;

public class Cell {
    private final int xRow;
    private final int yCol;
    private boolean hasCoin;
    private CellType type;

    public Cell(int xRow, int yCol, boolean hasCoin, CellType type){
        this.xRow = xRow;
        this.yCol = yCol;
        this.hasCoin = hasCoin;
        this.type = type;
    }

    public boolean gethasCoin(){
        return this.hasCoin;
    }

    public CellType getType(){
        return this.type;
    }

    public void setType(CellType type){
        this.type = type;
    }

}
