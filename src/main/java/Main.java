/**
 *
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Initial Player count : ");

        Player p0 = new Player();
        // System.out.println("Initial Player count : " + p0.getCount());
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");


        p1.updateScore(7);
        p2.updateScore(-9);
        System.out.println("Print p1 : " + p1);
        System.out.println("Print p2 : " + p2);

        // W1L5 TEST EQUALS
        System.out.println("---- EQUALS TEST ----");
        System.out.println(p1.equals("Alice"));
        System.out.println(p1.equals(p2));
        Player p3 = new Player("BOB");
        System.out.println(p2.equals(p3));
        System.out.println(p2 == p3);
        Player b = p2;
        System.out.println(p2 == b);
        System.out.println("---- ----");


        //W1L6
        Player p4 = new Player();
        Player p5 = new Player();
        System.out.println(p0.getName() + " " + p4.getName() + " " + p5.getName());
        // System.out.println("Final Player count : " + p0.getCount());

        System.out.println("---- LEVEL 1 ---- ");
        Level lvl1 = new Level(14);
        char[][] test1 = lvl1.getMatrix();
        for (int i = 0; i < lvl1.getSize(); i++) {
            for (int j = 0; j < lvl1.getSize(); j++) {
                System.out.print(" " + (test1[i][j]));
            }
            System.out.println();
        }
        System.out.println("---- LEVEL 2 ---- ");
        Level lvl2 = new Level(7);
        char[][] test2 = lvl2.getMatrix();
        for (int i = 0; i < lvl2.getSize(); i++) {
            for (int j = 0; j < lvl2.getSize(); j++) {
                System.out.print(" " + (test2[i][j]));
            }
            System.out.println();
        }
    }
}
