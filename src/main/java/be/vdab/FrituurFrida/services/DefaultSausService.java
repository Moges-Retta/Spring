package be.vdab.FrituurFrida.services;

import be.vdab.FrituurFrida.domain.Saus;
import be.vdab.FrituurFrida.repositories.CSVSausRepository;
import be.vdab.FrituurFrida.repositories.SausRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultSausService implements SausService {
    private final SausRepository[] sausRepositories;
    List<Saus> sauzen = null;

    public DefaultSausService(SausRepository[] sausRepositories) {
        this.sausRepositories = sausRepositories;
    }

    @Override
    public List<Saus> findAll() {
        for (var sausRepository : sausRepositories) {
            try {
                sauzen = sausRepository.findAll();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return sauzen;
    }

    @Override
    public List<Saus> findByNaamBegintMet(char letter) {

        List<Saus> list = null;
        for (var sausRepository : sausRepositories) {
            try {
                sauzen = sausRepository.findAll();
                list = sauzen.stream().filter(
                        saus -> saus.getNaam().charAt(0) == letter)
                        .collect(Collectors.toList());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public Optional<Saus> findById(long id) throws IOException {
        Optional<Saus> sauzen = Optional.empty();
        for (var sausRepository : sausRepositories) {
            sauzen = sausRepository.findAll().stream()
                    .filter(saus -> saus.getNummer() == id).findFirst();
        }
        return sauzen;
    }
}
        /*Optional<Saus> list = Optional.empty();
        try{
            sauzen= sausRepository.findAll();
            list = sauzen
                    .stream()
                    .filter(saus -> saus.getNummer()==id)
                    .findFirst();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return list;*/

