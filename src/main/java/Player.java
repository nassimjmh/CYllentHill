/**
 * Player class
 *
 * @author Nassim
 * @version w1.0
 */
public class Player {
    private final String name;
    private int score;

    /**
     * Player constructor
     * Score = 0 by default
     *
     * @param name The name of the player
     */
    public Player(String name) {
        this.name = name;
        score = 0;
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
        if (obj == null || getClass() != obj.getClass()) return false;
        Player other = (Player) obj;
        return this.name.equalsIgnoreCase(other.name);
    }


    public static void main(String[] args) {
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");

        System.out.println(p1);
        System.out.println(p2);

        p1.updateScore(7);
        p2.updateScore(-9);

        System.out.println(p1);
        System.out.println(p2);

        System.out.print(p1.equals("Alice"));
    }

}
