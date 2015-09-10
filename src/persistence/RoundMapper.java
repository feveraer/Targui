package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Round mapper.
 *
 * @author Frederic Everaert
 * @author Gilles Baert
 * @author Jonas De Bruycker
 * @author Sander De Quick
 */
class RoundMapper {

    /**
     * Saves all rounds played so far.
     *
     * @param roundConf
     * @throws java.sql.SQLException
     */
    public void saveRounds(String[][] roundConf) throws SQLException {
        String[] values = new String[roundConf.length];
        for (int i = 0; i < roundConf.length; i++) {
            values[i] = "";
            for (int j = 0; j < roundConf[i].length; j++) {
                values[i] += "'" + roundConf[i][j] + "'";
                if (j < roundConf[i].length - 1) {
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
            String SQL = "INSERT INTO round VALUES (" + value + ")";
            stmt.executeUpdate(SQL);
        }
    }

    /**
     * Returns the ID of the next round.
     *
     * @return @throws java.sql.SQLException
     */
    public int getNextRoundId() throws SQLException {
        int roundId = 0;
        String host = "jdbc:mysql://localhost:3306/targui";
        String uName = "root";
        String uPass = "root";
        Connection con = DriverManager.getConnection(host, uName, uPass);
        Statement stmt = con.createStatement();
        String SQL = "SELECT MAX(roundId) + 1 FROM round";
        ResultSet rs = stmt.executeQuery(SQL);
        if (rs.next()) {
            roundId = rs.getInt(1);
        }
        return roundId;
    }

    /**
     * Returns all rounds in a particular game.
     *
     * @param gameId
     * @return
     * @throws java.sql.SQLException
     */
    public String[][] getRounds(int gameId) throws SQLException {
        String host = "jdbc:mysql://localhost:3306/targui";
        String uName = "root";
        String uPass = "root";
        Connection con = DriverManager.getConnection(host, uName, uPass);

        Statement stmt = con.createStatement();
        String SQL = "SELECT * FROM round WHERE gameId = " + gameId;
        ResultSet rs = stmt.executeQuery(SQL);

        Statement stmt2 = con.createStatement();
        String SQL2 = "SELECT COUNT(*) FROM round WHERE gameId = " + gameId;
        ResultSet rs2 = stmt2.executeQuery(SQL2);
        int nrOfRounds = 0;
        if (rs2.next()) {
            nrOfRounds = rs2.getInt(1);
        }
        String[][] roundConf = new String[nrOfRounds][4];
        int i = 0;
        while (rs.next()) {
            roundConf[i][0] = rs.getString("roundId");
            roundConf[i][1] = rs.getString("roundNr");
            roundConf[i][2] = rs.getString("fortune");
            roundConf[i][3] = rs.getString("gameId");
            i++;
        }
        return roundConf;
    }
}
