package cyllenthill.entities;

public class Entity {
    protected final String name;
    protected int health;
    protected int xRow;
    protected int yCol;
    protected int placeX;
    protected int placeY;

    public Entity(String name){
        this.name = name;
    }
}
