package be.vdab.FrituurFrida.services;

import be.vdab.FrituurFrida.domain.Gastenboek;
import be.vdab.FrituurFrida.domain.Pizza;
import be.vdab.FrituurFrida.forms.GastenboekForm;

import java.util.List;

public interface GastenboekService {
    long create(Gastenboek gastenboek);
    void delete(long[] id);
    List<Gastenboek> findAll();

}
