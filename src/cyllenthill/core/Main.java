package cyllenthill.core;

import cyllenthill.entities.Player;
import cyllenthill.world.Level;
import cyllenthill.world.Direction;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Utilisation : java Main niveau.txt");
            return;
        }
        String path = args[0];
        try {
            Player p1 = new Player("Nassim");
            Level lvl = new Level(path);

            lvl.placePlayer(p1, 5, 5);

            Scanner scanner = new Scanner(System.in);
            boolean playing = true;

            while (playing) {
                System.out.println(lvl);
                System.out.println(lvl.getPlayer());
                System.out.print("Action (Z/Q/S/D pour bouger, X pour quitter) : ");

                String input = scanner.nextLine().toUpperCase();
                if (input.isEmpty()) continue;

                char action = input.charAt(0);
                switch (action) {
                    case 'Z' -> lvl.movePlayer(Direction.HAUT);
                    case 'S' -> lvl.movePlayer(Direction.BAS);
                    case 'Q' -> lvl.movePlayer(Direction.GAUCHE);
                    case 'D' -> lvl.movePlayer(Direction.DROITE);
                    case 'X' -> playing = false;
                    default -> System.out.println("Touche invalide.");
                }
                if (lvl.getCoins()==0){
                    playing = false;
                    System.out.println("NIVEAU TERMINE");
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.err.println("Erreur : Impossible de lire le fichier '" + path + "'.");
            return;
        }
    }
}