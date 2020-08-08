package be.vdab.FrituurFrida.repositories;

import be.vdab.FrituurFrida.domain.Gastenboek;
import be.vdab.FrituurFrida.domain.Pizza;
import be.vdab.FrituurFrida.forms.GastenboekForm;

import java.util.List;

public interface GastenboekRepository {
    long create(Gastenboek gastenboek);
    void delete(long id);
    List<Gastenboek> findAll();
}
