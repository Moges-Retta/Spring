package be.vdab.FrituurFrida.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PizzasRepository extends AbstractRepository {

    public ResultSet selectPizzas() throws SQLException {
        try (var connection = super.getConnection();
             var statement = connection.prepareStatement(
                     "select * from pizzas")) {
            return statement.executeQuery();
        }
    }
}
