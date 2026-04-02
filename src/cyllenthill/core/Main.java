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
        Scanner scanner = new Scanner(System.in);
        boolean globalRunning = true;
        while (globalRunning) {
            try {
                Player p1 = new Player("Nassim");
                Level lvl = new Level(path);
                p1.setPlace(5, 5);
                lvl.placePlayer(p1, p1.getPlaceX(), p1.getPlaceY());
                boolean playing = true;

                while (playing) {

                    System.out.println(lvl);
                    System.out.println(lvl.getPlayer());
                    System.out.print("Action (Z/Q/S/D pour bouger, X pour quitter) : ");

                    String input = scanner.nextLine().toUpperCase();
                    if (input.isEmpty()) continue;

                    char action = input.charAt(0);

                    if (action=='X'){
                        playing = false;
                        globalRunning = false;
                        break;
                    }
                    switch (action) {
                        case 'Z' -> lvl.movePlayer(Direction.HAUT);
                        case 'S' -> lvl.movePlayer(Direction.BAS);
                        case 'Q' -> lvl.movePlayer(Direction.GAUCHE);
                        case 'D' -> lvl.movePlayer(Direction.DROITE);
                        default -> System.out.println("Touche invalide.");
                    }
                    if (lvl.getCoins() == 0) {
                        System.out.println(lvl);
                        System.out.println("NIVEAU TERMINE");
                        playing = false;
                    }
                    if (p1.getHealth() == 0) {
                        System.out.println("GAME OVER");
                        playing = false;
                    }
                }
                if (globalRunning){
                    System.out.println("Voulez vous rejouer ? OUI : O (c la lettre pas un zéro^^)");
                    String replay = scanner.nextLine().toUpperCase();
                    if (!replay.equals("O")) {
                        globalRunning = false;
                        System.out.println("Merci d'avoir joué !");
                    }
                }
            } catch (IOException e) {
                System.err.println("Erreur : Impossible de lire le fichier '" + path + "'.");
                return;
            }
        }
        scanner.close();
    }
}