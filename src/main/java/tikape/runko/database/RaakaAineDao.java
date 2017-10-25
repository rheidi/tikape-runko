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
    public RaakaAine saveOrUpdate(RaakaAine raakaAine) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement saveStatement = connection.prepareStatement("INSERT INTO RaakaAine (nimi) "
                + "VALUES (?)");
        saveStatement.setString(1, raakaAine.getNimi());
        saveStatement.executeUpdate();
        saveStatement.close();

        PreparedStatement retrieveStatement = connection.prepareStatement("SELECT last_insert_rowid() as id");
        ResultSet rs = retrieveStatement.executeQuery();
        rs.next();

        int latestId = rs.getInt("id");
        raakaAine.setId(latestId);

        rs.close();
        saveStatement.close();
        connection.close();

        return raakaAine;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM RaakaAine WHERE RaakaAine.id = (?)");
        statement.setInt(1, key);
        statement.executeUpdate();
        
        statement.close();
        connection.close();
    }

}
