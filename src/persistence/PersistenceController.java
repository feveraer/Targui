/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.SQLException;
import java.util.List;

/**
 * Persistencecontroller of Targui. Gives access to the game database.
 *
 * @author Frederic Everaert
 * @author Gilles Baert
 * @author Jonas De Bruycker
 * @author Sander De Quick
 */
public class PersistenceController {

    private final GameMapper gameMapper = new GameMapper();
    private final SquareMapper squareMapper = new SquareMapper();
    private final PlayerMapper playerMapper = new PlayerMapper();
    private final RoundMapper roundMapper = new RoundMapper();
    private static PersistenceController persistentieController;

    /**
     * Returns an instance of the class itself.
     *
     * @return
     */
    public static PersistenceController getInstance() {
        if (persistentieController == null) {
            persistentieController = new PersistenceController();
        }
        return persistentieController;
    }

    /**
     * Save the game.
     *
     * @param gameName
     * @param board
     * @param currentRound
     * @param players
     * @param fortuneIDs
     * @throws java.sql.SQLException
     */
    public void saveGame(String gameName, int currentRound, String[][][] board, String[][] players, int[] fortuneIDs) throws SQLException {
        if (gameMapper.containsGameName(gameName)) {
            throw new IllegalArgumentException(String.format("db.saveExists %s", gameName));
        }
        String[][][] game = new String[4][][];
        game[0] = new String[1][3];
        game[1] = new String[board.length * board.length][8];
        game[2] = new String[players.length][6];
        game[3] = new String[fortuneIDs.length][4];

        int gameId = gameMapper.getNextGameId();

        game[0][0][0] = Integer.toString(gameId);                             //gameId
        game[0][0][1] = gameName;                           //gameName
        game[0][0][2] = Integer.toString(currentRound);     //currentRound
        gameMapper.saveGame(game[0][0], gameName);

        int i = 0;
        for (String[] p : players) {
            game[2][i][0] = Integer.toString(i + 4 * gameId);    //playerId
            game[2][i][1] = p[0];                   //playerName
            game[2][i][2] = p[1];                   //playerColor
            game[2][i][3] = p[2];                   //playerSector
            game[2][i][4] = p[3];                   //playerSilver
            game[2][i][5] = Integer.toString(gameId);                 //gameId
            i++;
        }
        playerMapper.savePlayers(game[2]);

        i = 0;
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 7; y++) {
                //volgorde 00-01-02...
                game[1][i][0] = Integer.toString(i + 49 * gameId);        //squareId
                game[1][i][1] = Integer.toString(x);        //xCoord
                game[1][i][2] = Integer.toString(y);        //yCoord
                game[1][i][3] = board[x][y][0];             //region
                game[1][i][4] = board[x][y][6];             //camels
                game[1][i][5] = board[x][y][3];             //sector
                game[1][i][6] = Integer.toString(gameId);   //gameId
                game[1][i][7] = playerMapper.getPlayerId(board[x][y][4]);  //playerId
                i++;
            }
        }
        squareMapper.saveSquares(game[1]);

        int roundId = roundMapper.getNextRoundId();
        i = 0;
        for (int fortune : fortuneIDs) {
            game[3][i][0] = Integer.toString(roundId + i);        //roundId
            game[3][i][1] = Integer.toString(i);    //roundNr
            game[3][i][2] = Integer.toString(fortune);  //fortune
            game[3][i][3] = Integer.toString(gameId);           //gameId
            i++;
        }
        roundMapper.saveRounds(game[3]);
    }

    /**
     * Returns a saved game
     *
     * @param gameName
     * @return
     * @throws java.sql.SQLException
     */
    public String[][][] getSavedGame(String gameName) throws SQLException {
        if (!gameMapper.containsGameName(gameName)) {
            throw new IllegalArgumentException(String.format("db.saveNotFound %s", gameName));
        }
        int gameId = Integer.parseInt(gameMapper.getGame(gameName)[0]);
        String[][][] savedGame = new String[4][][];
        savedGame[0] = new String[1][];
        savedGame[0][0] = gameMapper.getGame(gameName);
        savedGame[1] = squareMapper.getSquares(gameId);
        savedGame[2] = playerMapper.getPlayers(gameId);
        savedGame[3] = roundMapper.getRounds(gameId);
        return savedGame;
    }

    /**
     * Returns the names of all saved games in the database.
     *
     * @return
     * @throws SQLException
     */
    public List<String> getSavedGameNames() throws SQLException {
        List<String> savedGameNames = gameMapper.getGameNames();
        if (savedGameNames.isEmpty()) {
            throw new UnsupportedOperationException("db.empty");
        }
        return savedGameNames;
    }
}
