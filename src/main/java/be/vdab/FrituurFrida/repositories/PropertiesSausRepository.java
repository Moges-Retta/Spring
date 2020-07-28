package be.vdab.FrituurFrida.repositories;

import be.vdab.FrituurFrida.domain.Saus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Qualifier("proBESTAND_PATH")
public class PropertiesSausRepository implements SausRepository{
    private final Path BESTAND_PATH;

    public PropertiesSausRepository(@Value("${proBESTAND_PATH}") Path bestand_path) {
        BESTAND_PATH = bestand_path;
    }

    @Override
    public List<Saus> findAll() throws IOException {
        return Files.lines(BESTAND_PATH)
                .map(this::makeSaus)
                .collect(Collectors.toList());    }
    private Saus makeSaus(String string){
        var gesplit = string.split(",");
        var sausId = gesplit[0].split(":")[0];
        var sausNaam = gesplit[0].split(":")[1];
        return new Saus(Long.parseLong(sausId),
                sausNaam,
                Arrays.copyOfRange(gesplit, 1, gesplit.length));
    }
}
