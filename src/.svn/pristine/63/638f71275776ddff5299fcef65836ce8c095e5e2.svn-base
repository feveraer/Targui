package ui;

import java.util.Scanner;
import domain.DomainController;
import java.util.InputMismatchException;
import java.util.List;

/**
 *
 * @author Jonas
 */
public class UseCase6 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        DomainController dc = new DomainController();
        boolean[] geldig = new boolean[4];

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

        String[][] players = dc.getPlayers();
        for (int i = 0; i < players.length; i++) {
            System.out.println("Player " + (i + 1) + ":");
            System.out.println("Name: " + players[i][0]);
            System.out.println("Color: " + players[i][1]);
            System.out.println("Sector: " + players[i][2]);
            System.out.println("Silver: " + players[i][3] + "\n");
        }

        //Use case 2
        String[] playerNames = new String[4];

        for (int i = 0; i < players.length; i++) {
            playerNames[i] = players[i][0];
        }
        dc.addPlayersToGame(playerNames);

        try {
            dc.populateBoard(0);
        } catch (InterruptedException iex) {
            System.err.println("Something went terribly wrong, restart the game!");
        }

        dc.getBoard();

//Show populated board
        String[][][] board = dc.getBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                //for(int k = 0; k < board[i][j].length; k++)
                //{
//                    System.out.printf("%20s",board[i][j][0]);
//                    System.out.printf("%s",board[i][j][1]);
//                    System.out.printf("%s",board[i][j][2]);
//                    System.out.printf("%s",board[i][j][3]);

                System.out.printf("[%10s %2s %2s %2d]",
                        board[i][j][0],
                        board[i][j][1],
                        board[i][j][2],
                        Integer.parseInt(board[i][j][3]) + 1);

                //}
                System.out.print("  ");
            }
            System.out.print("\n");
        }

        //Request settlement position + insert settlement position
        int xCoord = 0;
        int yCoord = 0;
        dc.placeSettlement(playerNames[0], xCoord, yCoord);

        xCoord = 0;
        yCoord = 6;
        dc.placeSettlement(playerNames[1], xCoord, yCoord);

        xCoord = 6;
        yCoord = 0;
        dc.placeSettlement(playerNames[2], xCoord, yCoord);

        xCoord = 6;
        yCoord = 6;
        dc.placeSettlement(playerNames[3], xCoord, yCoord);

        System.out.print("\n\n\n");

        String[][][] boardUpdate = dc.getBoard();

        for (int i = 0; i < boardUpdate.length; i++) {
            for (int j = 0; j < boardUpdate[i].length; j++) {
                //for(int k = 0; k < board[i][j].length; k++)
                //{
//                    System.out.printf("%20s",board[i][j][0]);
//                    System.out.printf("%s",board[i][j][1]);
//                    System.out.printf("%s",board[i][j][2]);
//                    System.out.printf("%s",board[i][j][3]);

                System.out.printf("[%10s %2s %2s %2d %2s %5s %2s]",
                        boardUpdate[i][j][0],
                        boardUpdate[i][j][1],
                        boardUpdate[i][j][2],
                        Integer.parseInt(boardUpdate[i][j][3]) + 1,
                        boardUpdate[i][j][4],
                        boardUpdate[i][j][5],
                        boardUpdate[i][j][6]);

                //}
                System.out.print("  ");
            }
            System.out.print("\n");
        }

//Use Case 3 Begin  
        System.out.print("\n\n\n");
        boolean good = false;
        while (!good) {
            try {
                System.out.print("Enter number of rounds (1-16):");
                int rounds = Integer.parseInt(input.nextLine());
                dc.setupRounds(rounds);
                good = true;
            } catch (NumberFormatException nfe) {
                System.err.println("Enter a number from 1 to 16!");
            } catch (IllegalArgumentException e) {
                e.getMessage();
            }
        }

        System.out.println("Number of rounds: " + dc.getNumberOfRounds());

        do {
            System.out.print("Next round? Enter Y: ");
            String next = input.next();
            if (next.toLowerCase().charAt(0) == 'y' || next.isEmpty()) {
                String[] turn = dc.getNextTurn();
                System.out.printf("\nRound %d\nTurn %d of %d\n",
                        dc.getCurrentRoundNumber(),
                        dc.getCurrentTurnNumber(),
                        dc.getNumberOfTurns());

                //UC4 noodlotskaart?
                if (turn[0] == null) {
                    System.out.println(turn[1]);
                } else {
                    System.out.printf("It's %s's turn.\n", turn[0]);

                    boardUpdate = dc.getBoard();

                    for (int j = 0; j < boardUpdate.length; j++) {
                        for (int k = 0; k < boardUpdate[j].length; k++) {
                            if (boardUpdate[j][k][4].equals(turn[0])) {
                                System.out.printf("[%10s %2s %2s %2d %2s %5s %2s]",
                                        boardUpdate[j][k][0],
                                        boardUpdate[j][k][1],
                                        boardUpdate[j][k][2],
                                        Integer.parseInt(boardUpdate[j][k][3]) + 1,
                                        boardUpdate[j][k][4],
                                        boardUpdate[j][k][5],
                                        boardUpdate[j][k][6]);

                                System.out.print("  ");
                            } else {
                                System.out.printf("[        x=%d y=%d         ]", j + 1, k + 1);
                            }
                        }
                        System.out.print("\n");
                    }

                    // select + getAdjacentSquares
                    boolean[] check = new boolean[4];
                    int xCo = 0, yCo = 0, xTo = 0, yTo = 0, camels = 0;
                    int buyCamels = 0;

                    while (!check[0]) {
                        try {
                            System.out.println("Select a square under your command.");

                            System.out.print("Enter x coordinate: ");
                            xCo = Integer.parseInt(input.nextLine()) - 1;
                            System.out.print("Enter y coordinate: ");
                            yCo = Integer.parseInt(input.nextLine()) - 1;
                            if (!boardUpdate[xCo][yCo][4].equals(turn[0])) {
                                System.err.println("You do not own this square!");
                            } else {
                                check[0] = true;
                            }
                        } catch (NumberFormatException nfe) {
                            System.err.println("Enter a number from 1 to 7!");
                        }
                    }

                    //UC6 Val Aan
                    int ans = 0;
                    int[] battleStatus = new int[5];
                    try {
                        System.out.println("Do you wish to attack? 0 = No, 1 = Yes");
                        ans = Integer.parseInt(input.nextLine());
                    } catch (NumberFormatException nfe) {
                        System.err.println("Please enter a number!");
                    }
                    if (ans == 1) {
                        int[][] adjecent = dc.getAdjacentSquares(xCo, yCo);

                        for (int i = 0; i < adjecent.length; i++) {
                            System.out.printf("Adjacent square coordinates: x=%d y=%d\n", adjecent[i][0] + 1, adjecent[i][1] + 1);
                        }
                        while (!check[1]) {
                            try {
                                System.out.println("Enter the coordinates of the square you want to move to.");

                                System.out.print("Enter x coordinate of destination: ");
                                xTo = Integer.parseInt(input.nextLine()) - 1;
                                System.out.print("Enter y coordinate of destination: ");
                                yTo = Integer.parseInt(input.nextLine()) - 1;
                                System.out.print("Enter the amount of camels you want to attack with: ");
                                camels = Integer.parseInt(input.nextLine());

                                for (int i = 0; i < adjecent.length; i++) {
                                    if (adjecent[i][0] == xTo && adjecent[i][1] == yTo) {
                                        dc.initiateAttack(xCo, yCo, xTo, yTo);
                                        do {
                                            battleStatus = dc.attack();
                                            switch (battleStatus[0]) {
                                                case 0:
                                                    System.out.println("Attacking player won");
                                                    boardUpdate = dc.getBoard();

                                                    for (i = 0; i < boardUpdate.length; i++) {
                                                        for (int j = 0; j < boardUpdate[i].length; j++) {
                                                            System.out.printf("[%10s %2s %2s %2d %2s %5s %2s]",
                                                                    boardUpdate[i][j][0],
                                                                    boardUpdate[i][j][1],
                                                                    boardUpdate[i][j][2],
                                                                    Integer.parseInt(boardUpdate[i][j][3]) + 1,
                                                                    boardUpdate[i][j][4],
                                                                    boardUpdate[i][j][5],
                                                                    boardUpdate[i][j][6]);
                                                            System.out.print("  ");
                                                        }
                                                        System.out.print("\n");
                                                    }
                                                    break;
                                                case 1:
                                                    System.out.println("Defending player won");
                                                    boardUpdate = dc.getBoard();

                                                    for (i = 0; i < boardUpdate.length; i++) {
                                                        for (int j = 0; j < boardUpdate[i].length; j++) {
                                                            System.out.printf("[%10s %2s %2s %2d %2s %5s %2s]",
                                                                    boardUpdate[i][j][0],
                                                                    boardUpdate[i][j][1],
                                                                    boardUpdate[i][j][2],
                                                                    Integer.parseInt(boardUpdate[i][j][3]) + 1,
                                                                    boardUpdate[i][j][4],
                                                                    boardUpdate[i][j][5],
                                                                    boardUpdate[i][j][6]);
                                                            System.out.print("  ");
                                                        }
                                                        System.out.print("\n");
                                                    }
                                                    break;
                                                case 2:
                                                    System.out.println("Both players still have camels");
                                                    boardUpdate = dc.getBoard();

                                                    for (i = 0; i < boardUpdate.length; i++) {
                                                        for (int j = 0; j < boardUpdate[i].length; j++) {
                                                            System.out.printf("[%10s %2s %2s %2d %2s %5s %2s]",
                                                                    boardUpdate[i][j][0],
                                                                    boardUpdate[i][j][1],
                                                                    boardUpdate[i][j][2],
                                                                    Integer.parseInt(boardUpdate[i][j][3]) + 1,
                                                                    boardUpdate[i][j][4],
                                                                    boardUpdate[i][j][5],
                                                                    boardUpdate[i][j][6]);
                                                            System.out.print("  ");
                                                        }
                                                        System.out.print("\n");
                                                    }
                                                    break;
                                            }
                                            if (battleStatus[0] == 2) {
                                                System.out.println("Do you wish to continue attacking? 0 for No, 1 for Yes");
                                                ans = Integer.parseInt(input.nextLine());
                                            } else {
                                                ans = 1;
                                            }
                                        } while (ans == 1);
                                        check[1] = true;
                                        break;
                                    }

                                }
                                if (!check[1]) {
                                    System.err.println("Enter the coordinates of an adjacent square!");
                                }

                            } catch (NumberFormatException nfe) {
                                System.err.println("Please enter a number!");
                            } catch (IllegalArgumentException iae) {
                                System.err.println(iae.getMessage());
                            }

                        }

                    } else {
                        int[][] adjecent = dc.getAdjacentSquares(xCo, yCo);

                        for (int i = 0; i < adjecent.length; i++) {
                            System.out.printf("Adjacent square coordinates: x=%d y=%d\n", adjecent[i][0] + 1, adjecent[i][1] + 1);
                        }
                        while (!check[1]) {
                            try {
                                System.out.println("Enter the coordinates of the square you want to move to.");

                                System.out.print("Enter x coordinate of destination: ");
                                xTo = Integer.parseInt(input.nextLine()) - 1;
                                System.out.print("Enter y coordinate of destination: ");
                                yTo = Integer.parseInt(input.nextLine()) - 1;
                                System.out.print("Enter the amount of camels you want to move: ");
                                camels = Integer.parseInt(input.nextLine());

                                for (int i = 0; i < adjecent.length; i++) {
                                    if (adjecent[i][0] == xTo && adjecent[i][1] == yTo) {
                                        dc.move(xCo, yCo, xTo, yTo, camels);
                                        check[1] = true;
                                        break;
                                    }

                                }
                                if (!check[1]) {
                                    System.err.println("Enter the coordinates of an adjacent square!");
                                }

                            } catch (NumberFormatException nfe) {
                                System.err.println("Please enter a number!");
                            } catch (IllegalArgumentException iae) {
                                System.err.println(iae.getMessage());
                            }

                        }
                    }

                    //Show board before buying camels
                    boardUpdate = dc.getBoard();

                    for (int i = 0; i < boardUpdate.length; i++) {
                        for (int j = 0; j < boardUpdate[i].length; j++) {

                            System.out.printf("[%10s %2s %2s %2d %2s %5s %2s]",
                                    boardUpdate[i][j][0],
                                    boardUpdate[i][j][1],
                                    boardUpdate[i][j][2],
                                    Integer.parseInt(boardUpdate[i][j][3]) + 1,
                                    boardUpdate[i][j][4],
                                    boardUpdate[i][j][5],
                                    boardUpdate[i][j][6]);

                            System.out.print("  ");
                        }
                        System.out.print("\n");
                    }

                    while (!check[2]) {
                        try {
                            System.out.print("Enter the amount of camels you want to buy: ");
                            buyCamels = Integer.parseInt(input.nextLine());

                            //toont gebieden waarop kamelen geplaatst kunnen worden
                            boardUpdate = dc.getBoard();

                            for (int j = 0; j < boardUpdate.length; j++) {
                                for (int k = 0; k < boardUpdate[j].length; k++) {
                                    if (boardUpdate[j][k][4].equals(turn[0])) {
                                        System.out.printf("[%10s %2s %2s %2d %2s %5s %2s]",
                                                boardUpdate[j][k][0],
                                                boardUpdate[j][k][1],
                                                boardUpdate[j][k][2],
                                                Integer.parseInt(boardUpdate[j][k][3]) + 1,
                                                boardUpdate[j][k][4],
                                                boardUpdate[j][k][5],
                                                boardUpdate[j][k][6]);

                                        System.out.print("  ");
                                    } else {
                                        System.out.printf("[        x=%d y=%d         ]", j + 1, k + 1);
                                    }
                                }
                                System.out.print("\n");
                            }
                            //enter coordinates of place where you want to put your bought camels
                            System.out.println("On which square do you want to place your bought camels?");
                            System.out.print("Enter x Coordinate: ");
                            int buyXCo = Integer.parseInt(input.nextLine()) - 1;
                            System.out.print("Enter y COordinate: ");
                            int buyYCo = Integer.parseInt(input.nextLine()) - 1;

                            if (!boardUpdate[buyXCo][buyYCo][4].equals(turn[0])) {
                                System.err.println("You do not own this square!");
                            } else {
                                dc.purchaseCamels(buyXCo, buyYCo, buyCamels);
                                check[2] = true;
                            }

                        } catch (NumberFormatException nfe) {
                            System.err.println("Enter a number!");
                        } catch (IllegalArgumentException iae) {
                            System.err.println(iae.getMessage());
                        }

                    }

                }//end of 'else'

                //toon het spel
                //onderdeel use case 4
                boardUpdate = dc.getBoard();

                for (int i = 0; i < boardUpdate.length; i++) {
                    for (int j = 0; j < boardUpdate[i].length; j++) {

                        System.out.printf("[%10s %2s %2s %2d %2s %5s %2s]",
                                boardUpdate[i][j][0],
                                boardUpdate[i][j][1],
                                boardUpdate[i][j][2],
                                Integer.parseInt(boardUpdate[i][j][3]) + 1,
                                boardUpdate[i][j][4],
                                boardUpdate[i][j][5],
                                boardUpdate[i][j][6]);

                        System.out.print("  ");
                    }
                    System.out.print("\n");
                }
                //onderdeel use case 4
                if (dc.getCurrentTurnNumber() == dc.getNumberOfTurns()) {
                    int[] roundResult = dc.evaluateRound();
                    System.out.printf("\n%s gets %d Silver\n%s gets %d Silver\n%s gets %d Silver\n%s gets %d Silver\n\n",
                            playerNames[0], roundResult[0], playerNames[1], roundResult[1],
                            playerNames[2], roundResult[2], playerNames[3], roundResult[3]);
                }
            } else {
                System.exit(0);
            }
        } while (dc.getVictors().isEmpty());

        List<String> victors = dc.getVictors();
        int nrOfVictors = victors.size();
        if (nrOfVictors > 0) {
            switch (nrOfVictors) {
                case 1:
                    System.out.println(victors.get(0) + " is the winner! Congratulations!");
                    break;
                case 2:
                    System.out.println(String.format("Tie game between %s and %s!",
                            victors.get(0), victors.get(1)));
                    break;
                case 3:
                    System.out.println(String.format("Tie game between %s, %s and %s!",
                            victors.get(0), victors.get(1), victors.get(2)));
                    break;
                case 4:
                    System.out.println("Tie game! Nobody won! Wasn't this a waste of time? :)");
                    break;
            }

        }

        System.out.println(dc.getVictors() + " = Victor(s)\n");

        //print board
        boardUpdate = dc.getBoard();

        for (int i = 0; i < boardUpdate.length; i++) {
            for (int j = 0; j < boardUpdate[i].length; j++) {

                System.out.printf("[%10s %2s %2s %2d %2s %5s %2s]",
                        boardUpdate[i][j][0],
                        boardUpdate[i][j][1],
                        boardUpdate[i][j][2],
                        Integer.parseInt(boardUpdate[i][j][3]) + 1,
                        boardUpdate[i][j][4],
                        boardUpdate[i][j][5],
                        boardUpdate[i][j][6]);

                System.out.print("  ");
            }
            System.out.print("\n");
        }

    }

}
