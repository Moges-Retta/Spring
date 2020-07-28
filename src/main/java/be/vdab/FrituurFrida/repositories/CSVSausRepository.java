package be.vdab.FrituurFrida.repositories;

import be.vdab.FrituurFrida.domain.Saus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Qualifier("csvBESTAND_PATH")
public class CSVSausRepository implements SausRepository{
    private final Path BESTAND_PATH;
    private final LinkedList<Saus> sausList = new LinkedList<>();
    private final String[] ingredienten = new String[]{};

    public CSVSausRepository(@Value("${csvBESTAND_PATH}") Path bestand_path) {
        BESTAND_PATH = bestand_path;
    }

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
