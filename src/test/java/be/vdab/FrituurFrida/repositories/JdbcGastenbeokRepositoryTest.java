package be.vdab.FrituurFrida.repositories;

import be.vdab.FrituurFrida.domain.Gastenboek;
import be.vdab.FrituurFrida.exceptions.GastenNietGevondenException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@JdbcTest
@Import(JdbcGastenboekRepositoy.class)
@Sql("/insertEntries.sql")
public class JdbcGastenbeokRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final JdbcGastenboekRepositoy repository;

    public JdbcGastenbeokRepositoryTest(JdbcGastenboekRepositoy repository) {
        this.repository = repository;
    }
    @Test
    void create() {
        var id = repository.create(
                new Gastenboek(0,"test", "lekker", Date.valueOf("2020-08-07")));
        assertThat(id).isPositive();
        assertThat(super.countRowsInTableWhere("gastenboek", "id=" + id)).isOne();
    }
    private final RowMapper<Long> idMapper =
            (result, rowNum) -> result.getLong("id");
    private long idVanTestEntry() {
        return super.jdbcTemplate.queryForObject(
                "select id from gastenboek where naam ='test'", Long.class);
    }
    @Test
    void delete() {
        long id = idVanTestEntry();
        repository.delete(id);
        assertThat(super.countRowsInTableWhere("gastenboek", "id=" + id)).isZero();
    }
    @Test
    void findAllGeeftAlleEntriesGesorteerdOpId() {
        assertThat(repository.findAll())
                .hasSize(super.countRowsInTable("gastenboek"))
                .extracting(Gastenboek::getId)
                .isSortedAccordingTo(Comparator.reverseOrder());
    }
}
