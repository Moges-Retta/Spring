package be.vdab.FrituurFrida.services;

import be.vdab.FrituurFrida.domain.Saus;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface SausService {
    List<Saus> findAll();
    List<Saus> findByNaamBegintMet(char letter) ;
    Optional<Saus> findById(long id) throws IOException;
}
