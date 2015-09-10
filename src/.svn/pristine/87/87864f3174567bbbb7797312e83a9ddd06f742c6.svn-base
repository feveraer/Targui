package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Game Mapper.
 *
 * @author Frederic Everaert
 * @author Gilles Baert
 * @author Jonas De Bruycker
 * @author Sander De Quick
 */
class GameMapper {

    /**
     * Save the game.
     *
     * @param gameConf
     * @param gameName
     * @throws java.sql.SQLException
     */
    public void saveGame(String[] gameConf, String gameNaam) throws SQLException {
        String values = "";
        for (int i = 0; i < gameConf.length; i++) {
            values += "'" + gameConf[i] + "'";
            if (i < gameConf.length - 1) {
                values += ",";
            }
        }
        String host = "jdbc:mysql://localhost:3306/targui";
        String uName = "root";
        String uPass = "root";
        Connection con = DriverManager.getConnection(host, uName, uPass);
        Statement stmt = con.createStatement();
        String SQL = "INSERT INTO game VALUES (" + values + ")";
        stmt.executeUpdate(SQL);
    }

    /**
     * Returns the next game ID.
     *
     * @return
     * @throws SQLException
     */
    public int getNextGameId() throws SQLException {
        int gameId = 0;
        String host = "jdbc:mysql://localhost:3306/targui";
        String uName = "root";
        String uPass = "root";
        Connection con = DriverManager.getConnection(host, uName, uPass);
        Statement stmt = con.createStatement();
        String SQL = "SELECT MAX(gameId) + 1 FROM game";
        ResultSet rs = stmt.executeQuery(SQL);
        if (rs.next()) {
            gameId = rs.getInt(1);
        }
        return gameId;
    }

    /**
     * Checks whether the database contains a game with the name specified.
     *
     * @param gameName
     * @return
     * @throws SQLException
     */
    public boolean containsGameName(String gameName) throws SQLException {
        boolean contains = false;
        String host = "jdbc:mysql://localhost:3306/targui";
        String uName = "root";
        String uPass = "root";
        Connection con = DriverManager.getConnection(host, uName, uPass);
        Statement stmt = con.createStatement();
        String SQL = "SELECT * FROM game WHERE gameName = '" + gameName + "'";
        ResultSet rs = stmt.executeQuery(SQL);
        if (rs.next()) {
            contains = true;
        }
        return contains;
    }

    /**
     * Returns a specific game.
     *
     * @param gameName
     * @return
     * @throws SQLException
     */
    public String[] getGame(String gameName) throws SQLException {
        String host = "jdbc:mysql://localhost:3306/targui";
        String uName = "root";
        String uPass = "root";
        Connection con = DriverManager.getConnection(host, uName, uPass);
        Statement stmt = con.createStatement();
        String SQL = "SELECT * FROM game WHERE gameName = '" + gameName + "'";
        ResultSet rs = stmt.executeQuery(SQL);

        String[] gameConf = new String[3];
        while (rs.next()) {
            gameConf[0] = rs.getString("gameId");
            gameConf[1] = rs.getString("gameName");
            gameConf[2] = rs.getString("currentRound");
        }
        return gameConf;
    }

    /**
     * Returns all game names currently in the database.
     *
     * @return
     * @throws SQLException
     */
    public List<String> getGameNames() throws SQLException {
        String host = "jdbc:mysql://localhost:3306/targui";
        String uName = "root";
        String uPass = "root";
        Connection con = DriverManager.getConnection(host, uName, uPass);
        Statement stmt = con.createStatement();
        String SQL = "SELECT * FROM game";
        ResultSet rs = stmt.executeQuery(SQL);

        List<String> gameNames = new ArrayList<>();
        while (rs.next()) {
            gameNames.add(rs.getString("gameName"));
        }
        return gameNames;
    }
}
