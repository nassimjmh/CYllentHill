package cyllenthill.world;

public class Cell {
    private final int xRow;
    private final int yCol;
    private CellType type;
    private boolean hasCoin;
    private boolean isSolid;

    public Cell(int xRow, int yCol,CellType type, boolean hasCoin, boolean isSolid){
        this.xRow = xRow;
        this.yCol = yCol;
        this.type = type;
        this.hasCoin = hasCoin;
        this.isSolid = isSolid;
    }

    public CellType getType(){
        return type;
    }

    public boolean gethasCoin(){
        return hasCoin;
    }


    public boolean getisSolide(){
        return isSolid;
    }

    public void setType(CellType type){
        this.type = type;
    }

    public void setHasCoin(boolean hasCoin){
        this.hasCoin = hasCoin;
    }

    public void setisSolid(boolean isSolid){
        this.isSolid = isSolid;
    }



}
