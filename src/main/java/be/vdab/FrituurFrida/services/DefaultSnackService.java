package be.vdab.FrituurFrida.services;

import be.vdab.FrituurFrida.domain.Saus;
import be.vdab.FrituurFrida.domain.Snack;
import be.vdab.FrituurFrida.repositories.SnackRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultSnackService implements SnackService{
    private final SnackRepository snackRepository;

    public DefaultSnackService(SnackRepository snackRepository) {
        this.snackRepository = snackRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Snack> findById(long id) {
        return snackRepository.findById(id);
    }

    @Override
    public void update(Snack snack) {
        snackRepository.update(snack);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Snack> findByBeginNaam(String beginNaam) {
        return snackRepository.findByBeginNaam(beginNaam);
    }

    @Override
    public long create(Snack snack) {
        return snackRepository.create(snack);
    }
}
