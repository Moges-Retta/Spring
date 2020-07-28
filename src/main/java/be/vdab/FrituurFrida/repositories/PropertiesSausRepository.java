package be.vdab.FrituurFrida.repositories;

import be.vdab.FrituurFrida.domain.Saus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PropertiesSausRepository implements SausRepository{
    private final static Path BESTAND_PATH = Paths.get("/data/sauzen.properties");

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
