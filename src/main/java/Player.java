/**
 * Player class
 *
 * @author Nassim
 * @version w1.0
 */
public class Player {
    private final String name;
    private int score;
    private static int count = 0;

    /**
     * Player constructor with parameter
     * Score = 0 by default
     *
     * @param name The name of the player
     */
    public Player(String name) {
        this.name = name;
        score = 0;
        count++;
    }

    /**
     * Default Player constructor
     * Score = 0 by default
     * name = PlayerN by default
     */
    public Player() {
        this.name = "Player" + (count + 1);
        score = 0;
        count++;
    }

    public int getCount(){
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

    /**
     * Cannot go negative
     *
     * @param delta point to add or remove
     *
     */
    public void updateScore(int delta) {
        this.score = Math.max(this.score + delta, 0); // score >= 0
    }

    /**
     *
     * @return Formatted String
     */
    @Override
    public String toString() {
        return this.getName() + " : " + this.getScore() + " pt" + ((this.getScore() > 1 ? "s" : ""));
    }

    /**
     *
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
