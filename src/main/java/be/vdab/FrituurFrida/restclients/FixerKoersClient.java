package be.vdab.FrituurFrida.restclients;

import be.vdab.FrituurFrida.exceptions.KoersClientException;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Pattern;


@Component
@Qualifier("Fixer")
public class FixerKoersClient implements KoersClient{
    private static final Pattern PATTERN = Pattern.compile(".*\"USD\":");
    private final URL url;
    public FixerKoersClient(@Value("${fixerKoersURL}") URL url) {
        this.url = url;
        /*try {
            url = new URL(
                    "http://data.fixer.io/api/latest?access_key=42e4cee3f6c40b0739eaa5d61e3083f5&symbols=USD");
        } catch (MalformedURLException ex) {
            throw new KoersClientException("Fixer URL is verkeerd.");
        }*/
    }
    @Override
    public BigDecimal getDollarKoers() {
        try (var scanner = new Scanner(url.openStream())) {
            scanner.skip(PATTERN);
            scanner.useDelimiter("}");
            return new BigDecimal(scanner.next());
        } catch (IOException | NumberFormatException ex) {
            throw new KoersClientException("Kan koers niet lezen via Fixer.", ex);
        }
    }
}
