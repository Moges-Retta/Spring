package be.vdab.luigi;

import be.vdab.luigi.restclients.KoersClient;
import be.vdab.luigi.services.DefaultEuroService;
import be.vdab.luigi.services.EuroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigDecimal;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EuroServiceTest {
    @Mock
    private KoersClient koersClient;
    private EuroService euroService;
    @BeforeEach
    void beforeEach() {
        euroService = new DefaultEuroService(new KoersClient[] {koersClient});
    }
    @Test
    void naarDollar() {
        when(koersClient.getDollarKoers()).thenReturn(BigDecimal.valueOf(1.5));
        assertThat(euroService.naarDollar(BigDecimal.valueOf(2))) .isEqualByComparingTo("3");
        verify(koersClient).getDollarKoers();
    }
}
