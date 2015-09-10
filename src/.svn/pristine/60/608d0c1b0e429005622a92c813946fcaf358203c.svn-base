package ui;

import java.util.Scanner;
import domain.DomainController;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Jonas
 */
public class UseCase1 {

    private ResourceBundle bundle;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        UseCase1 uc1 = new UseCase1();
        DomainController dc = new DomainController();

        uc1.setLanguage(input);

        uc1.showSectors(dc);
        uc1.createPlayers(input, dc);
        uc1.showPlayers(dc);

    }//einde main

    public void setLanguage(Scanner input) {
        boolean correctLang = false;
        String lang = "";
        do {
            System.out.printf("Choose your language (en) | Kies uw taal (nl) | Choisissez votre langue (fr): ");
            lang = input.next();
            if (lang.equals("en") || lang.equals("nl") || lang.equals("fr")) {
                correctLang = true;
            }
        } while (!correctLang);
        Locale loc = new Locale(lang);
        bundle = ResourceBundle.getBundle("properties/game", loc);
    }
    
    public ResourceBundle getBundle() {
        return bundle;
    }

    private void createPlayers(Scanner input, DomainController dc) {

        boolean[] geldig = new boolean[4];

        for (int i = 0; i < 4; i++) {

            while (!geldig[i]) {
                try {
                    System.out.printf(bundle.getString("ui.enterPlayer") + " ", i + 1);
                    String name = input.next();
                    System.out.print(bundle.getString("ui.chooseColor") + " ");
                    String color = input.next();
                    System.out.print(bundle.getString("ui.enterSector") + " ");
                    int sector = input.nextInt() - 1;
                    dc.addPlayer(name, color, sector);
                    geldig[i] = true;
                } catch (IllegalArgumentException ex) {
                    String[] exp = ex.getMessage().split(" ");
                    if (exp.length == 1) {
                        System.err.println(bundle.getString(ex.getMessage()));
                    } else {
                        System.err.println(String.format(bundle.getString(exp[0]), exp[1]));
                    }
                    //System.err.flush();
                } catch (InputMismatchException exp) {
                    System.err.println(bundle.getString("ui.exception1"));
                    //System.err.flush();
                }
            }

        }

    }//einde methode

    public void showPlayers(DomainController dc) {
        String[][] players = dc.getPlayers();
        for (int i = 0; i < players.length; i++) {
            System.out.printf(bundle.getString("ui.showPlayerP"), (i + 1));
            System.out.println(bundle.getString("ui.showPlayerN") + " " + players[i][0]);
            System.out.println(bundle.getString("ui.showPlayerC") + " " + players[i][1]);
            System.out.println(bundle.getString("ui.showPlayerSe") + " " + players[i][2]);
            System.out.println(bundle.getString("ui.showPlayerSi") + " " + players[i][3] + "\n");
        }
    }

    public void showSectors(DomainController dc) {
        int[][] sectors = dc.getSectors();

        for (int i = 0; i < sectors.length; i++) {
            for (int a = 0; a < sectors[i].length; a++) {
                if (!(sectors[i][a] == -1)) {
                    System.out.printf("%2s", (sectors[i][a] + 1));
                } else {
                    System.out.print("  ");
                }

            }
            System.out.println("");

        }
    }
}//einde klasse

