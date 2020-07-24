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
public class DefaultSausService implements SausService{
    private final SausRepository sausRepository;
    List<Saus> sauzen = null;

    public DefaultSausService(SausRepository sausRepository) {
        this.sausRepository = sausRepository;
    }

    @Override
    public List<Saus> findAll() {
        try{
            sauzen= sausRepository.findAll();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return sauzen;
    }

    @Override
    public List<Saus> findByNaamBegintMet(char letter) {

        List<Saus> list = null;
        try{
            sauzen = sausRepository.findAll();
             list = sauzen.stream().filter(
                    saus -> saus.getNaam().charAt(0)==letter)
            .collect(Collectors.toList());
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public Optional<Saus> findById(long id) throws IOException {
        return sausRepository.findAll().stream()
                .filter(saus -> saus.getNummer() == id).findFirst();

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
    }
}
