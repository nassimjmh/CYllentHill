package cyllenthill.entities;

public class Enemy extends Entity {
    public Enemy(String name){
        super(name,1);
    }
    @Override
    public String toString() {
        return "👤 " + this.getName() + " | " + "❤️".repeat(Math.min(5, Math.max(0, getHealth()))) + "| 📍 (" + this.getxRow() + ", " + this.getyCol() + ")";
    }
}
