package be.vdab.FrituurFrida;

import be.vdab.FrituurFrida.restclients.ECBKoersClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(SpringExtension.class)
@PropertySource("application.properties")
@Import(ECBKoersClient.class)

public class ECBKoersClientTest {
    private final ECBKoersClient client;

    public ECBKoersClientTest(ECBKoersClient client) {
        this.client = client;
    }

    /*@BeforeEach
    void beforeEach() {
        client = new ECBKoersClient();
    }*/
    @Test
    void deKoersIsPositief() {
        assertThat(client.getDollarKoers()).isPositive();
    }
}
