package be.vdab.FrituurFrida.repositories;

import be.vdab.FrituurFrida.domain.Gastenboek;
import be.vdab.FrituurFrida.exceptions.GastenNietGevondenException;
import be.vdab.FrituurFrida.exceptions.SnackNietGevondenException;
import be.vdab.FrituurFrida.forms.GastenboekForm;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcGastenboekRepositoy implements GastenboekRepository{
    private final JdbcTemplate template;
    private final SimpleJdbcInsert insert;
    private final RowMapper<Gastenboek> gastMapper =
            (result, rowNum) ->
                    new Gastenboek(result.getLong("id"),result.getString("naam"),
                            result.getString("bericht"),
                            result.getDate("date")
                    );

    public JdbcGastenboekRepositoy(JdbcTemplate template) {
        this.template = template;
        this.insert  = new SimpleJdbcInsert(template)
                .withTableName("gastenboek")
                .usingGeneratedKeyColumns("id");;
    }

    @Override
    public long create(Gastenboek gastenboek) {
        var kolomWaarden
                = Map.of("naam", gastenboek.getName(),
                "bericht", gastenboek.getBericht(),
                "date",gastenboek.getDate());
        var id = insert.executeAndReturnKey(kolomWaarden);
        return id.longValue();
    }

    @Override
    public void delete(long id) {
        var sql = "delete from gastenboek where id =?";
        template.update(sql, id);
    }

    @Override
    public List<Gastenboek> findAll() {
        var sql = "select * from gastenboek order by id desc";
        return template.query(sql, gastMapper);
    }
}
