package ui;

import java.util.Scanner;
import domain.DomainController;
import java.util.ResourceBundle;

/**
 *
 * @author Jonas
 */
public class UseCase4 {

    private ResourceBundle bundle;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        DomainController dc = new DomainController();
        UseCase1 uc1 = new UseCase1();
        UseCase2 uc2 = new UseCase2();
        UseCase3 uc3 = new UseCase3();
        UseCase4 uc4 = new UseCase4();
        boolean[] geldig = new boolean[4];
        
        
        uc1.setLanguage(input);
        uc2.setBundle(uc1);
        uc3.setBundle(uc1);
        uc4.setBundle(uc1);

        uc1.showSectors(dc);

        uc2.createPredefinedPlayers(dc);

        uc1.showPlayers(dc);

  //Use case 2
        uc2.populateBoard(dc);
        uc2.addGamersToGame(dc, uc2);

//Show populated board
        uc2.showBoard(dc);

        //placePredefinedsettlements
        uc3.placePredefinedSettlements(dc, uc2);

        uc2.showBoard(dc);

//Use Case 3 Begin  !!Nog problemen met InputMismatchException
        System.out.print("\n\n\n");

        uc3.enterNumberOfRounds(dc, input);

        do {
            uc4.requestNextRound();
            String next = input.next();
            next.toLowerCase();
            if (next.charAt(0) == 'y' || next.isEmpty()) {
                String[] turn = dc.getNextTurn();
                uc4.showRoundDetails(dc);

                //UC4 noodlotskaart?
                if (turn[0] == null) {
                    uc4.showFortune(turn);
                } else {
                    uc4.showWhosTurn(turn);
                }

                //toon het spel
                //onderdeel use case 4
                uc2.showBoard(dc);
                //onderdeel use case 4
                if (dc.getCurrentTurnNumber() == dc.getNumberOfTurns()) {
                    uc3.evaluateRound(dc, uc2);
                }
            } else {
                System.exit(0);
            }
        } while (dc.getVictors().isEmpty());

        uc3.showVictor(dc);

        //print board
        uc2.showBoard(dc);

    }//einde main
    
    public void setBundle(UseCase1 uc1) {
        bundle = uc1.getBundle();
    }//einde setBundle
    
    public void requestNextRound()
    {
        System.out.print(bundle.getString("ui.requestNextRound")+" ");
    }//einde requestNextRound
    
    public void showRoundDetails(DomainController dc)
    {
        System.out.printf(bundle.getString("ui.turnDetails"),
                        dc.getCurrentRoundNumber(),
                        dc.getCurrentTurnNumber(),
                        dc.getNumberOfTurns());
    }//einde showRoundDetails
    
    public void showWhosTurn(String[] turn)
    {
        System.out.printf(bundle.getString("ui.whosTurn"), turn[0]);
    }//einde showWhosTurn
    
    public void showFortune(String[] turn)
    {
        System.out.println(bundle.getString(turn[1]));
    }

}
