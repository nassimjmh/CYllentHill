package cyllenthill.entities;

public abstract class Entity {
    protected final String name;
    protected int health;
    protected int xRow;
    protected int yCol;
    protected int placeX;
    protected int placeY;

    public Entity(String name, int health){
        this.name = name;
        this.health = health;
    }

    /**
     *
     * @return entity name
     */
    public String getName() {
        return name;
    }


    public int getHealth(){
        return health;
    }

    public int getxRow(){
        return xRow;
    }

    public int getyCol(){
        return yCol;
    }

    public int getPlaceX(){
        return placeX;
    }

    public int getPlaceY(){
        return placeY;
    }

    public void setHealth(int delta){
        this.health = Math.max(this.health + delta,0);
    }

    public void setxRow(int row){
        this.xRow = row;
    }

    public void setyCol(int col){
        this.yCol = col;
    }


    public void setPlace(int placeX, int placeY){
        this.placeX = placeX;
        this.placeY = placeY;
    }

    /**
     * Compare only names ignoring case
     * @param obj the reference object with which to compare.
     * @return boolean whether object are equal or not
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Entity other = (Entity) obj;
        return this.name.equalsIgnoreCase(other.name);
    }
}
