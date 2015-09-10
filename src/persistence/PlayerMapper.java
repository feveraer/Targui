package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Player mapper
 *
 * @author Frederic Everaert
 * @author Gilles Baert
 * @author Jonas De Bruycker
 * @author Sander De Quick
 */
class PlayerMapper {

    /**
     * Saves all players.
     *
     * @param playerConf
     * @throws java.sql.SQLException
     */
    public void savePlayers(String[][] playerConf) throws SQLException {
        String[] values = new String[playerConf.length];
        for (int i = 0; i < playerConf.length; i++) {
            values[i] = "";
            for (int j = 0; j < playerConf[i].length; j++) {
                values[i] += "'" + playerConf[i][j] + "'";
                if (j < playerConf[i].length - 1) {
                    values[i] += ",";
                }
            }
        }
        String host = "jdbc:mysql://localhost:3306/targui";
        String uName = "root";
        String uPass = "root";
        Connection con = DriverManager.getConnection(host, uName, uPass);
        Statement stmt = con.createStatement();
        for (String value : values) {
            String SQL = "INSERT INTO player VALUES (" + value + ")";
            stmt.executeUpdate(SQL);
        }
    }

    /**
     * Returns a player's ID.
     *
     * @param name
     * @return
     * @throws java.sql.SQLException
     */
    public String getPlayerId(String name) throws SQLException {
        if (!name.isEmpty()) {
            name = "'" + name + "'";
            String playerId = "";
            String host = "jdbc:mysql://localhost:3306/targui";
            String uName = "root";
            String uPass = "root";
            Connection con = DriverManager.getConnection(host, uName, uPass);
            Statement stmt = con.createStatement();
            String SQL = "SELECT * FROM player WHERE playerName = " + name;
            ResultSet rs = stmt.executeQuery(SQL);
            if (rs.next()) {
                playerId = rs.getString("playerId");
            }
            return playerId;
        } else {
            return null;
        }
    }

    /**
     * Returns all players given a specific game ID.
     *
     * @param gameId
     * @return
     * @throws java.sql.SQLException
     */
    public String[][] getPlayers(int gameId) throws SQLException {
        String host = "jdbc:mysql://localhost:3306/targui";
        String uName = "root";
        String uPass = "root";
        Connection con = DriverManager.getConnection(host, uName, uPass);

        Statement stmt = con.createStatement();
        String SQL = "SELECT * FROM player WHERE gameId = " + gameId;
        ResultSet rs = stmt.executeQuery(SQL);

        String[][] playerConf = new String[4][6];
        int i = 0;
        while (rs.next()) {
            playerConf[i][0] = rs.getString("gameId");
            playerConf[i][1] = rs.getString("playerId");
            playerConf[i][2] = rs.getString("playerName");
            playerConf[i][3] = rs.getString("playerColor");
            playerConf[i][4] = rs.getString("playerSector");
            playerConf[i][5] = rs.getString("playerSilver");
            i++;
        }
        return playerConf;
    }
}
