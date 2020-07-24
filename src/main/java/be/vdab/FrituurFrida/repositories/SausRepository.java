package be.vdab.FrituurFrida.repositories;

import be.vdab.FrituurFrida.domain.Saus;

import java.io.IOException;
import java.util.List;

public interface SausRepository {
    List<Saus> findAll() throws IOException;
}
