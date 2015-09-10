package ui;

import java.util.Scanner;
import domain.DomainController;
import java.util.InputMismatchException;
import java.util.ResourceBundle;

/**
 *
 * @author Jonas
 */
public class UseCase2 {

    private ResourceBundle bundle;
    
    public static void main(String[] args) {
        final Scanner input = new Scanner(System.in);
        DomainController dc = new DomainController();
        UseCase1 uc1 = new UseCase1();
        UseCase2 uc2 = new UseCase2();
        boolean[] geldig = new boolean[4];
        

        uc1.setLanguage(input);
        uc2.setBundle(uc1);
        
        
        uc1.showSectors(dc);
        
        uc2.createPredefinedPlayers(dc);
        
        uc1.showPlayers(dc);
        
        
  //Use case 2
    uc2.populateBoard(dc);

        uc2.addGamersToGame(dc, uc2);
        
        
//Show populated board
        uc2.showBoard(dc);
        
        //Request settlement position + insert settlement position
        
        
        uc2.pickSettlement(dc, uc2, input);
        
        uc2.showBoard(dc);
        

    }//Einde main
    
    public void setBundle(UseCase1 uc1) {
        bundle = uc1.getBundle();
    }
    
    public void createPredefinedPlayers(DomainController dc)
    {
        //player 1
        String name = "a";
        String color = "blue";
        int sector = 0;
        dc.addPlayer(name, color, sector);
    
        //player 2
        name = "b";
        color = "red";
        sector = 1;
        dc.addPlayer(name, color, sector);
    
        //player 3
        name = "c";
        color = "green";
        sector = 2;
        dc.addPlayer(name, color, sector);
    
        //player 4
        name = "d";
        color = "cyan";
        sector = 3;
        dc.addPlayer(name, color, sector);

    }//einde createPredefinedPlayers
    
    
    
    public void addGamersToGame(DomainController dc, UseCase2 uc2) {
        String[] playerNames = uc2.getPlayerNames(dc);
        dc.addPlayersToGame(playerNames);
    }//einde addGamersToGame

    
    
    public String[] getPlayerNames(DomainController dc)
    {
        String[][] players = dc.getPlayers();
        String[] playerNames = new String[players.length];

        for (int i = 0; i < players.length; i++) {
            playerNames[i] = players[i][0];
        }
        return playerNames;
    }//einde getPlayerNames
    
    public void populateBoard(DomainController dc)
    {
        try
        {
        dc.populateBoard(0);
        }
        catch(InterruptedException iex)
        {System.err.println(bundle.getString("ui.terribleError"));}
    }
    
    private void pickSettlement(DomainController dc, UseCase2 uc2, Scanner input)
    {
        String[] playerNames = uc2.getPlayerNames(dc);
        boolean[] good = new boolean[4];
        for(int i = 0; i < playerNames.length; i++)
        {
            while(!good[i])
            {
                try
                {
                    System.out.println(playerNames[i] + bundle.getString("ui.pickSettlement"));
                    System.out.print(bundle.getString("ui.xCoordinate") + " ");
                    int xCoord = (input.nextInt()-1);
                    System.out.print(bundle.getString("ui.yCoordinate") + " ");
                    int yCoord = (input.nextInt()-1);
                    dc.placeSettlement(playerNames[i], xCoord, yCoord);
                    good[i]=true;
                }
                catch(InputMismatchException ime)
                {
                    System.err.println(bundle.getString("ui.wrongSeCoord"));
                    input.nextLine();
                }
                catch(UnsupportedOperationException uoe)
                {
                    System.err.println(bundle.getString(uoe.getMessage()));
                }
                catch(IllegalArgumentException iae)
                {
                    String[] exp = iae.getMessage().split(" ");
                    if(exp.length == 1)
                        System.err.println(bundle.getString(exp[0]));
                    else
                        System.err.println(String.format(bundle.getString(exp[0]), exp[1]));
                }
            }
        }
    }//einde pickSettlement
    
    public void showBoard(DomainController dc)
    {
        String[][][] board = dc.getBoard();
        
        for(int i = 0; i<board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                    
                    System.out.printf("[%10s %2s %2s %2d %2s %5s %2s]",
                        board[i][j][0],
                        board[i][j][1],
                        board[i][j][2],
                        Integer.parseInt(board[i][j][3])+1,
                        board[i][j][4],
                        board[i][j][5],
                        board[i][j][6]);

                System.out.print("  ");
            }
            System.out.print("\n");
        }
    }

}//Einde klasse


