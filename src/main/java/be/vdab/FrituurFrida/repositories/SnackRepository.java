package be.vdab.FrituurFrida.repositories;

import be.vdab.FrituurFrida.domain.Snack;

import java.util.List;
import java.util.Optional;

public interface SnackRepository {
    Optional<Snack> findById(long id);
    void update(Snack snack);
    List<Snack> findByBeginNaam(String beginNaam);
    //List<Snack> findByBeginMetLetter(Character letter);
    long create(Snack snack);
}
