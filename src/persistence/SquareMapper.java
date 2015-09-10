package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Square mapper.
 *
 * @author Frederic Everaert
 * @author Gilles Baert
 * @author Jonas De Bruycker
 * @author Sander De Quick
 */
class SquareMapper {

    /**
     * Saves all squares on the board.
     *
     * @param squareConf
     * @throws java.sql.SQLException
     */
    public void saveSquares(String[][] squareConf) throws SQLException {
        String[] values = new String[squareConf.length];
        for (int i = 0; i < squareConf.length; i++) {
            values[i] = "";
            for (int j = 0; j < squareConf[i].length; j++) {
                if (squareConf[i][j] == null) {
                    values[i] += "NULL";
                } else {
                    values[i] += "'" + squareConf[i][j] + "'";
                }
                if (j < squareConf[i].length - 1) {
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
            String SQL = "INSERT INTO square VALUES (" + value + ")";
            stmt.executeUpdate(SQL);
        }
    }

    /**
     * Returns all squares on the board.
     *
     * @param gameId
     * @return
     * @throws java.sql.SQLException
     */
    public String[][] getSquares(int gameId) throws SQLException {
        String host = "jdbc:mysql://localhost:3306/targui";
        String uName = "root";
        String uPass = "root";
        Connection con = DriverManager.getConnection(host, uName, uPass);

        Statement stmt = con.createStatement();
        String SQL = "SELECT * FROM square WHERE gameId = " + gameId;
        ResultSet rs = stmt.executeQuery(SQL);

        String[][] squareConf = new String[49][8];
        int i = 0;
        while (rs.next()) {
            squareConf[i][0] = rs.getString("gameId");
            squareConf[i][1] = rs.getString("squareId");
            squareConf[i][2] = rs.getString("xCoord");
            squareConf[i][3] = rs.getString("yCoord");
            squareConf[i][4] = rs.getString("region");
            squareConf[i][5] = rs.getString("nrOfCamels");
            squareConf[i][7] = rs.getString("sectorNr");
            SQL = "SELECT * FROM player WHERE playerId = " + rs.getString("playerId");
            Statement stmt2 = con.createStatement();
            ResultSet rs2 = stmt2.executeQuery(SQL);
            if (rs2.next()) {
                squareConf[i][6] = rs2.getString("playerName");
            }
            i++;
        }
        return squareConf;
    }
}
