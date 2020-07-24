package be.vdab.FrituurFrida.repositories;

import be.vdab.FrituurFrida.domain.Saus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CSVSausRepository implements SausRepository{
    private final static Path BESTAND_PATH = Paths.get("/data/sauzen.csv");
    private final LinkedList<Saus> sausList = new LinkedList<>();
    private final String[] ingredienten = new String[]{};
    @Override
    public List<Saus> findAll() throws IOException {
        return Files.lines(BESTAND_PATH)
                .map(this::makeSaus)
                .collect(Collectors.toList());
    }
    private Saus makeSaus(String string){
        var gesplit = string.split(",");
        return new Saus(Long.parseLong(gesplit[0]),
                gesplit[1],
                Arrays.copyOfRange(gesplit, 2, gesplit.length));
    }
}
