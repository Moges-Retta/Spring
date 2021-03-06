package be.vdab.luigi;

import be.vdab.luigi.restclients.ECBKoersClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
public class ECBKoersClientTest {
    private ECBKoersClient client;
    @BeforeEach
    void beforeEach() {
        client = new ECBKoersClient();
    }
    @Test
    void deKoersIsPositief() {
        assertThat(client.getDollarKoers()).isPositive();
    }
}
