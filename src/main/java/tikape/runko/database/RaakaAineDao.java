package tikape.runko.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.RaakaAine;

public class RaakaAineDao implements Dao<RaakaAine, Integer> {

    private Database database;

    public RaakaAineDao(Database database) {
        this.database = database;
    }

    @Override
    public List<RaakaAine> findAll() throws SQLException {
        List<RaakaAine> raakaAineet = new ArrayList<>();
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM RaakaAine");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String raakaAine = resultSet.getString("nimi");
            raakaAineet.add(new RaakaAine(id, raakaAine));
        }

        resultSet.close();
        statement.close();
        connection.close();

        return raakaAineet;
    }

    @Override
    public RaakaAine findOne(Integer key) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM RaakaAine "
                + "WHERE id = ?");
        stmt.setInt(1, key);
        ResultSet rs = stmt.executeQuery();

        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        RaakaAine raakaAine = new RaakaAine(rs.getInt("id"), rs.getString("nimi"));

        rs.close();
        stmt.close();
        conn.close();

        return raakaAine;
    }

    @Override
    public RaakaAine save(RaakaAine object) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO RaakaAine (nimi) "
                + "VALUES (?)");
        statement.setString(1, object.getNimi());
        statement.executeUpdate();
        statement.close();

        statement = connection.prepareStatement("SELECT * FROM RaakaAine "
                + "WHERE nimi = ?");
        statement.setString(1, object.getNimi());
        ResultSet rs = statement.executeQuery();

        RaakaAine raakaAine = new RaakaAine(rs.getInt("id"), rs.getString("nimi"));

        rs.close();
        statement.close();
        connection.close();

        return raakaAine;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
