package be.vdab.FrituurFrida.services;

import be.vdab.FrituurFrida.domain.Gastenboek;
import be.vdab.FrituurFrida.forms.GastenboekForm;
import be.vdab.FrituurFrida.repositories.GastenboekRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class DefaultGastenboekService implements GastenboekService{
    private final GastenboekRepository gastenboekRepository;

    public DefaultGastenboekService(GastenboekRepository gastenboekRepository) {
        this.gastenboekRepository = gastenboekRepository;
    }

    @Override
    public long create(Gastenboek Gastenboek) {
        return gastenboekRepository.create(Gastenboek);
    }

    @Override
    public void delete(long[] ids) {
        Arrays.stream(ids).forEach(gastenboekRepository::delete);
    }

    @Override
    public List<Gastenboek> findAll() {
        return gastenboekRepository.findAll();
    }
}
