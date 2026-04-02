package cyllenthill.world;

import cyllenthill.entities.Player;
import cyllenthill.world.Direction;

import javax.swing.plaf.IconUIResource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Level {
    private final int height;
    private final int width;
    private Cell[][] matrix;
    private Player player;
    private int coins;

    public Level(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        this.height = lines.size();
        this.width = lines.get(0).length();
        this.matrix = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            String line = lines.get(i);
            for (int j = 0; j < width; j++){
                char currentChar = line.charAt(j);
                switch (currentChar){
                    case ' ' -> this.matrix[i][j] = new Cell(i, j, false, CellType.VIDE);
                    case '#' -> this.matrix[i][j] = new Cell(i, j, false, CellType.MUR);
                    case '*' -> this.matrix[i][j] = new Cell(i, j, false, CellType.PIEGE);
                    case '.' -> this.matrix[i][j] = new Cell(i, j, true, CellType.VIDE);
                }
            }
        }
        countCoin();
    }

    public void countCoin(){
        for (int i = 0; i<this.height;i++){
            for (int j=0;j<this.width;j++){
                if (this.matrix[i][j].gethasCoin()){
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

    public Cell[][] getMatrix(){
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
        if (matrix[row][col].getType() == CellType.MUR) {
            throw new PlayerOnWallException("Position (" + row + ", " + col + ") sur un mur.");
        }
        this.player = p;
        this.player.setxRow(row);
        this.player.setyCol(col);
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
        if (newRow >= 0 && newRow < height && newCol >= 0 && newCol < width && matrix[newRow][newCol].getType() != CellType.MUR) {
            if (matrix[newRow][newCol].gethasCoin()){
                player.setScore(10);
                updateCoins(-1);
                matrix[newRow][newCol].setHasCoin(false);
            }
            if (matrix[newRow][newCol].getType() == CellType.PIEGE){
                player.setHealth(-2);
                matrix[newRow][newCol].setType(CellType.VIDE);
                newRow = player.getPlaceX();
                newCol = player.getPlaceY();
            }
            player.setxRow(newRow);
            player.setyCol(newCol);
        }

    }

    @Override
    public String toString() {
        StringBuilder map = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (player != null && i == player.getxRow() && j == player.getyCol()) {
                    map.append('1');
                } else if (matrix[i][j].gethasCoin()) {
                    map.append('.');
                } else {
                    switch (matrix[i][j].getType()){
                        case MUR -> map.append('#');
                        case PIEGE -> map.append('*');
                        case VIDE -> map.append(' ');
                    }
                }
            }
            map.append("\n");
        }
        return map.toString();
    }

}
