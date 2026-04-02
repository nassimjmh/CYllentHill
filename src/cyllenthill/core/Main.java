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
            System.err.println("[!] ERREUR : Utilisation attendue -> java Main level1.txt ...");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        boolean globalRunning = true;
        int i = 0;

        System.out.print(">>> Saisissez le nom du joueur : ");
        String name = scanner.nextLine().trim();
        Player p1 = name.isEmpty() ? new Player() : new Player(name);

        while (globalRunning) {
            if (i >= args.length){
                System.out.println("\n╔══════════════════════════════╗");
                System.out.println("║          FIN DU JEU          ║");
                System.out.println("╚══════════════════════════════╝\n");
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
                    System.out.println("    ┌───┐");
                    System.out.println("    │ Z │");
                    System.out.println("┌───┼──┼────┐   ┌───┐");
                    System.out.println("│ Q │ S │ D │   │ X │ Quitter");
                    System.out.println("└───┴───┴───┘   └───┘");
                    System.out.print("Action ➔ ");
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
                        default -> System.out.println("⚠ Touche invalide. Veuillez réessayer.");
                    }
                    if (lvl.getCoins() == 0) {
                        System.out.println(lvl);
                        System.out.println("★━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━★");
                        System.out.println("┃        NIVEAU TERMINÉ        ┃");
                        System.out.println("★━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━★\n");
                        System.out.println("★━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━★\n");
                        System.out.println("★━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━★");
                        System.out.println("┃        PROCHAIN NIVEAU       ┃");
                        System.out.println("★━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━★\n");
                        i++;
                        playing = false;
                    }
                    if (p1.getHealth() == 0) {
                        System.out.println("\n☠️ ━━━━━━━━━━━━━━━━━━━━━━━━━━━━ ☠️");
                        System.out.println("┃          GAME OVER           ┃");
                        System.out.println("☠️ ━━━━━━━━━━━━━━━━━━━━━━━━━━━━ ☠️\n");
                        playing = false;
                    }
                }
                if (globalRunning && p1.getHealth()==0) {
                    System.out.println("❓ Voulez-vous rejouer ? [ O = Oui (c la lettre pas un zéro^^) / Autre touche = Non ] ➔ ");
                    String replay = scanner.nextLine().toUpperCase();
                    if (!replay.equals("O")) {
                        globalRunning = false;
                        System.out.println("\n✨ Merci d'avoir joué ! ✨\n");
                    } else{
                        i = 0;
                        p1 = new Player(name);
                    }
                }
            } catch (IOException e) {
                System.err.println("[X] ERREUR CRITIQUE : Impossible de lire le fichier -> " + args[i]);
                return;
            }
        }
        scanner.close();
    }
}