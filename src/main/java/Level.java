/**
 * Level class
 *
 * @author Nassim
 * @version w2.0
 */
public class Level {
    private final int size;
    private char[][] matrix;

    /**
     * Level constructor
     *
     * @param size positive integer
     */
    public Level(int size) {
        this.size = size;
        matrix = new char[size][size];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i == 0 || j == 0 || i == size - 1 || j == size - 1) {
                    matrix[i][j] = '#';
                } else if ((int) (Math.random() * 2) % 2 == 0) {
                    matrix[i][j] = '#';
                } else {
                    matrix[i][j] = ' ';
                }
            }
        }

    }

    /**
     *
     * @return size positive integer
     */
    public int getSize() {
        return size;
    }

    public char[][] getMatrix() {
        return matrix;
    }
}