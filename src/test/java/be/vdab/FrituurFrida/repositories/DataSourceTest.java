package be.vdab.FrituurFrida.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class DataSourceTest {
    private final DataSource dataSource;
    DataSourceTest(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Test
    void getConnection() throws SQLException {
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(
                     "select count(*) as aantal from luigi.pizzas where pikant=1")) {
             var result = statement.executeQuery();
            result.next();
            assertThat(result.getInt("aantal")).isEqualTo(1);
        }
    }
}
