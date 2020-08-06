package be.vdab.FrituurFrida.forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SnackBeginMet {
        @NotBlank
        private final String beginLetter;

        public SnackBeginMet(String beginLetter) {
            this.beginLetter = beginLetter;
        }
        public String getBeginLetter() {
            return beginLetter;
        }
}
