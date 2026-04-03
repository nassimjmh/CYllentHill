package cyllenthill.world;

import cyllenthill.entities.Enemy;
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
    private Enemy enemy;
    private int coins;

    public Level(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        this.height = lines.size();
        this.width = lines.get(0).length();
        this.matrix = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            String line = lines.get(i);
            for (int j = 0; j < width; j++){
                char currentChar = (j < line.length()) ? line.charAt(j) : ' '; // https://pastebin.com/r3aZiH7a
                switch (currentChar){
                    case ' ' -> this.matrix[i][j] = new Cell(i, j, CellType.VIDE, false, false);
                    case '#' -> this.matrix[i][j] = new Cell(i, j, CellType.MUR, false, true);
                    case '*' -> this.matrix[i][j] = new Cell(i, j, CellType.PIEGE, false, false);
                    case '.' -> this.matrix[i][j] = new Cell(i, j, CellType.VIDE, true, false);
                    case 'D' -> this.matrix[i][j] = new Cell(i, j, CellType.PORTE, false, true);
                    case 'R' -> {
                        this.matrix[i][j] = new Cell(i, j, CellType.VIDE, false, false);
                        this.enemy = new Enemy("Yahu");
                        this.place(enemy, i, j);
                    }
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


    public static class EntityOnSolidException extends RuntimeException {
        public EntityOnSolidException(String message) { super(message); }
    }

    public static class EntityOutOfBoundsException extends RuntimeException {
        public EntityOutOfBoundsException(String message) { super(message); }
    }



    public void place(Player p, int row, int col) {
        if (row < 0 || row >= height || col < 0 || col >= width) {
            throw new EntityOutOfBoundsException("Position (" + row + ", " + col + ") hors limites.");
        }
        if (matrix[row][col].getisSolide()) {
            throw new EntityOnSolidException("Position (" + row + ", " + col + ") sur un mur ou une porte fermée.");
        }
        this.player = p;
        this.player.setxRow(row);
        this.player.setyCol(col);
    }

    public void place(Enemy e, int row, int col){
        if (row < 0 || row >= height || col < 0 || col >= width) {
            throw new EntityOutOfBoundsException("Position (" + row + ", " + col + ") hors limites.");
        }
        if (matrix[row][col].getisSolide()) {
            throw new EntityOnSolidException("Position (" + row + ", " + col + ") sur un mur ou une porte fermée.");
        }
        this.enemy = e;
        this.enemy.setxRow(row);
        this.enemy.setyCol(col);
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
        newRow = (newRow + height) % height;
        newCol = (newCol + width) % width;
        if (!matrix[newRow][newCol].getisSolide()) {
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

    public void moveEnemy(){

        int newRow = this.enemy.getxRow();
        int newCol = this.enemy.getyCol();
        int attempts = 0;
        do{
            newRow = this.enemy.getxRow();
            newCol = this.enemy.getyCol();
            int direction = (int)(Math.random() * 4);
            attempts++;
            switch (direction){
                case 0 -> newRow--;
                case 1 -> newRow++;
                case 2 -> newCol--;
                case 3 -> newCol++;
            }
            newRow = (newRow + height) % height;
            newCol = (newCol + width) % width;
        } while ((matrix[newRow][newCol].getisSolide() || matrix[newRow][newCol].getType() == CellType.PIEGE ) && attempts<10 ) ;


        enemy.setxRow(newRow);
        enemy.setyCol(newCol);

    }

    @Override
    public String toString() {
        StringBuilder map = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (player != null && i == player.getxRow() && j == player.getyCol()) {
                    map.append('1');
                } else if (enemy != null && i == enemy.getxRow() && j == enemy.getyCol()) {
                    map.append('R');
                } else if (matrix[i][j].gethasCoin()) {
                    map.append('.');
                } else {
                    switch (matrix[i][j].getType()){
                        case MUR -> map.append('#');
                        case PIEGE -> map.append('*');
                        case VIDE -> map.append(' ');
                        case PORTE -> map.append('D');
                    }
                }
            }
            map.append("\n");
        }
        return map.toString();
    }

}
