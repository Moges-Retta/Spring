package be.vdab.FrituurFrida.repositories;

import be.vdab.FrituurFrida.domain.Pizza;
import be.vdab.FrituurFrida.domain.Snack;
import be.vdab.FrituurFrida.exceptions.SnackNietGevondenException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@JdbcTest
@Import(JdbcSnackRepository.class)
@Sql("/insertSnack.sql")
public class JdbcSnackRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final JdbcSnackRepository jdbcSnackRepository;

    public JdbcSnackRepositoryTest(JdbcSnackRepository jdbcSnackRepository) {
        this.jdbcSnackRepository = jdbcSnackRepository;
    }
    private long idVanTestSnack() {
        return super.jdbcTemplate.queryForObject(
                "select id from snacks where naam ='test'", Long.class);
    }
    private long idVanTest2Snack() {
        return super.jdbcTemplate
                .queryForObject( "select id from snacks where naam ='test2'", Long.class); }
    @Test
    void findById(){
        assertThat(jdbcSnackRepository.findById(idVanTestSnack()).get().getNaam()).isEqualTo("test");
    }
    @Test
    public void update(){
        var id = idVanTestSnack();
        var snack = new Snack(id, "test", BigDecimal.ONE);
        jdbcSnackRepository.update(snack);
        assertThat(super.jdbcTemplate
                .queryForObject("select prijs from snacks where id=?", BigDecimal.class, id))
                .isOne();
    }
    @Test
    void findByBeginNaam(){
        assertThat(jdbcSnackRepository.findById(idVanTestSnack()).get().getNaam()).startsWith("t");
    }
    @Test
    void updateOnbestaandeSnack() {
        assertThatExceptionOfType(SnackNietGevondenException.class)
                .isThrownBy(() -> jdbcSnackRepository.update(new Snack(-1,"test",BigDecimal.ONE)));
    }
}
