package tikape.runko.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.AnnosRaakaAine;

public class AnnosRaakaAineDao implements Dao<AnnosRaakaAine, Integer> {

    private Database database;

    public AnnosRaakaAineDao(Database database) {
        this.database = database;
    }

    @Override
    public List<AnnosRaakaAine> findAll() throws SQLException {
        List<AnnosRaakaAine> ara = new ArrayList<>();
        try (Connection conn = database.getConnection();
                ResultSet result = conn.prepareStatement("SELECT * FROM AnnosRaakaAine").executeQuery()) {

            while (result.next()) {
                ara.add(new AnnosRaakaAine(result.getInt("id"), result.getInt("annos_id"), result.getInt("raakaAine_id"), result.getInt("maara"), result.getString("ohje")));
            }
        };

        return ara;
    }

    // palauttaa Annokseen liittyvän AnnosRaakaAine listan.
    public List<AnnosRaakaAine> findOneList(Integer key) throws SQLException {
        List<AnnosRaakaAine> ara = new ArrayList<>();
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM AnnosRaakaAine "
                + "WHERE id = ?");
        stmt.setInt(1, key);
        ResultSet result = stmt.executeQuery();

        while (result.next()) {
            ara.add(new AnnosRaakaAine(result.getInt("id"), result.getInt("annos_id"), result.getInt("raakaAine_id"), result.getInt("maara"), result.getString("ohje")));
        }

        result.close();
        stmt.close();
        conn.close();

        return ara;
    }

    @Override
    public AnnosRaakaAine save(AnnosRaakaAine ara) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO AnnosRaakaAine (id, annos_id, raakaAine_id, maara, ohje) "
                + "VALUES (null, ?, ?, ?, ?)");
        stmt.setInt(1, ara.getAnnos_id());
        stmt.setInt(2, ara.getAnnos_id());
        stmt.setInt(3, ara.getMaara());
        stmt.setString(4, ara.getOhje());
        stmt.executeUpdate();
        stmt.close();

        PreparedStatement retrieveStatement = connection.prepareStatement("SELECT last_insert_rowid() as id");
        ResultSet rs = retrieveStatement.executeQuery();
        rs.next();

        int latestId = rs.getInt("id");
        ara.setId(latestId);

        rs.close();
        stmt.close();
        connection.close();

        return ara;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AnnosRaakaAine findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
