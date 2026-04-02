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
            System.err.println("Utilisation : java Main level1.txt ... ");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        boolean globalRunning = true;
        int i = 0;

        System.out.println("Saisir le nom du joueur : ");
        String name = scanner.nextLine();
        Player p1 = new Player(name);

        while (globalRunning) {
            if (i >= args.length){
                System.out.println("Fin du jeu.");
                break;
            }

            try {
                Level lvl = new Level(args[i]);
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

                    if (action == 'X') {
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
                        i++;
                        playing = false;
                    }
                    if (p1.getHealth() == 0) {
                        System.out.println("GAME OVER");
                        playing = false;
                    }
                }
                if (globalRunning && p1.getHealth()==0) {
                    System.out.println("Voulez vous rejouer ? OUI : O (c la lettre pas un zéro^^)");
                    String replay = scanner.nextLine().toUpperCase();
                    if (!replay.equals("O")) {
                        globalRunning = false;
                        System.out.println("Merci d'avoir joué !");
                    } else{
                        i = 0;
                        p1 = new Player(name);
                    }
                }
            } catch (IOException e) {
                System.err.println("Erreur : Impossible de lire le fichier " + args[i] + " .");
                return;
            }
        }
        scanner.close();
    }
}