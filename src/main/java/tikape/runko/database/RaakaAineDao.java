package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.RaakaAine;

/**
 *
 * @author Heidi
 */
public class RaakaAineDao implements Dao<RaakaAine, Integer>{
    private Database database;

    public RaakaAineDao(Database database) {
        this.database = database;
    }

    @Override
    public RaakaAine findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Opiskelija WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String nimi = rs.getString("nimi");

        RaakaAine o = new RaakaAine(id, nimi);

        rs.close();
        stmt.close();
        connection.close();

        return o;
    }

    @Override
    public List<RaakaAine> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Opiskelija");

        ResultSet rs = stmt.executeQuery();
        List<RaakaAine> aineet = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");

            aineet.add(new RaakaAine(id, nimi));
        }

        rs.close();
        stmt.close();
        connection.close();

        return aineet;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }
    
}
