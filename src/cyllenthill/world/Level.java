package cyllenthill.world;

import cyllenthill.entities.Player;
import cyllenthill.world.Direction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Level {
    private final int height;
    private final int width;
    private char[][] matrix;
    private Player player;
    private int coins;

    public Level(int height, int width) {
        this.height = height;
        this.width = width;
        matrix = new char[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || j == 0 || i == height - 1 || j == width - 1) {
                    matrix[i][j] = '#';
                } else {
                    matrix[i][j] = (Math.random() < 0.2) ? '#' : ' ';
                }
            }
        }
    }

    public Level(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        this.height = lines.size();
        this.width = lines.get(0).length();
        this.matrix = new char[height][width];
        for (int i = 0; i < height; i++) {
            this.matrix[i] = lines.get(i).toCharArray();
        }
        countCoin();
    }

    public void countCoin(){
        for (int i = 0; i<this.height;i++){
            for (int j=0;j<this.width;j++){
                if (this.matrix[i][j]=='.'){
                    this.updateCoins(1);
                }
            }
        }
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    public char[][] getMatrix(){
        return matrix;
    }

    public Player getPlayer(){
        return player;
    }

    public int getCoins(){
        return this.coins;
    }

    public void updateCoins(int delta){
        this.coins+= delta;
    }


    public static class PlayerOnWallException extends RuntimeException {
        public PlayerOnWallException(String message) { super(message); }
    }

    public static class PlayerOutOfBoundsException extends RuntimeException {
        public PlayerOutOfBoundsException(String message) { super(message); }
    }


    public void placePlayer(Player p, int row, int col) {
        if (row < 0 || row >= height || col < 0 || col >= width) {
            throw new PlayerOutOfBoundsException("Position (" + row + ", " + col + ") hors limites.");
        }
        if (matrix[row][col] == '#') {
            throw new PlayerOnWallException("Position (" + row + ", " + col + ") sur un mur.");
        }
        this.player = p;
        this.player.setxRow(row);
        this.player.setyCol(col);
        this.matrix[row][col] = '1';
    }

    public void movePlayer(Direction d){
        if (this.player == null) return;

        int newRow = this.player.getxRow();
        int newCol = this.player.getyCol();

        switch(d){
            case HAUT -> newRow--;
            case BAS -> newRow++;
            case GAUCHE -> newCol--;
            case DROITE -> newCol++;
        }
        if (newRow >= 0 && newRow < height && newCol >= 0 && newCol < width && matrix[newRow][newCol] != '#') {
            if (matrix[newRow][newCol] ==  '.'){
                player.setScore(10);
                updateCoins(-1);
            }
            if (matrix[newRow][newCol] ==  '*'){
                player.setHealth(-2);
                matrix[newRow][newCol] = ' ';
                newRow = player.getPlaceX();
                newCol = player.getPlaceY();
            }


            matrix[player.getxRow()][player.getyCol()] = ' ';
            player.setxRow(newRow);
            player.setyCol(newCol);
            matrix[player.getxRow()][player.getyCol()] = '1';
        }

    }

    @Override
    public String toString() {
        StringBuilder map = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                map.append(matrix[i][j]);
            }
            map.append("\n");
        }
        return map.toString();
    }

}
