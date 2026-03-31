package cyllenthill.entities;

/**
 * Player class
 *
 * @author nassimjmh
 */
public class Player {
    private final String name;
    private int score;
    private static int count = 0;
    private int xRow;
    private int yCol;
    private int health;

    /**
     * Player constructor with parameter
     * Score = 0 by default
     *
     * @param name The name of the player
     */
    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.health = 5;
        count++;
    }

    /**
     * Default Player constructor
     * Score = 0 by default
     * name = PlayerN by default
     */
    public Player() {
        this("Player" + (count + 1));
    }

    /**
     * Get number of players created
     * @return positive int
     */
    public static int getCount(){
        return count;
    }

    /**
     *
     * @return player name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return score (>= 0)
     */
    public int getScore() {
        return score;
    }

    public int getxRow(){
        return xRow;
    }

    public int getyCol(){
        return yCol;
    }

    public int getHealth(){
        return health;
    }

    /**
     * Cannot go negative
     * @param delta point to add or remove
     *
     */
    public void setScore(int delta) {
        this.score = Math.max(this.score + delta, 0); // score >= 0
    }

    public void setxRow(int row){
        this.xRow = row;
    }

    public void setyCol(int col){
        this.yCol = col;
    }

    /**
     * Format : *Name* : *Score* pt(s)
     * @return Formatted String
     */
    @Override
    public String toString() {
        return this.getName() + " " + "❤️".repeat(Math.min(5, Math.max(0, getHealth()))) + " : " + this.getScore() + " pt" + (this.getScore() > 1 ? "s" : "") + " (" + this.getxRow() + ", " + this.getyCol() + ").";    }

    /**
     * Compare only names ignoring case
     * @param obj the reference object with which to compare.
     * @return boolean whether object are equal or not
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Player other = (Player) obj;
        return this.name.equalsIgnoreCase(other.name);
    }

}