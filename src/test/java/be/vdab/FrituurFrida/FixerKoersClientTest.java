package be.vdab.FrituurFrida;

import be.vdab.FrituurFrida.restclients.FixerKoersClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class FixerKoersClientTest {
   private FixerKoersClient client;

   @BeforeEach
   void beforeEach() {
       client = new FixerKoersClient();
   }
   @Test
   void deKoersIsPositief() {
       assertThat(client.getDollarKoers()).isPositive();
   }
}