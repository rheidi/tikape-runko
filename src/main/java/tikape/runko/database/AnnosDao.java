package tikape.runko.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.Annos;

public class AnnosDao implements Dao<Annos, Integer> {

    private Database database;

    public AnnosDao(Database database) {
        this.database = database;
    }

    @Override
    public List<Annos> findAll() throws SQLException {
        List<Annos> annokset = new ArrayList<>();
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Annos");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String annos = resultSet.getString("nimi");
            annokset.add(new Annos(id, annos));
        }

        resultSet.close();
        statement.close();
        connection.close();

        return annokset;
    }

    @Override
    public Annos findOne(Integer key) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Annos "
                + "WHERE id = ?");
        stmt.setInt(1, key);
        ResultSet rs = stmt.executeQuery();

        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Annos annos = new Annos(rs.getInt("id"), rs.getString("nimi"));

        rs.close();
        stmt.close();
        conn.close();

        return annos;
    }

    @Override
    public Annos save(Annos annos) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement saveStatement = connection.prepareStatement("INSERT INTO Annos (nimi) "
                + "VALUES (?)");
        saveStatement.setString(1, annos.getNimi());
        saveStatement.executeUpdate();
        saveStatement.close();

        PreparedStatement retrieveStatement = connection.prepareStatement("SELECT last_insert_rowid() as id");
        ResultSet rs = retrieveStatement.executeQuery();
        rs.next();

        int latestId = rs.getInt("id");
        annos.setId(latestId);

        rs.close();
        saveStatement.close();
        connection.close();

        return annos;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
