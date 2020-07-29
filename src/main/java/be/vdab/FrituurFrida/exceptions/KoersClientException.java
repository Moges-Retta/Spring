package be.vdab.FrituurFrida.exceptions;

import java.util.function.Supplier;

public class KoersClientException extends RuntimeException implements Supplier<String> {
    private static final long serialVersionUID = 1L;
    public KoersClientException(String message) {
        super(message);
    }
    public KoersClientException(String message, Exception oorspronkelijkeFout) {

        super(message, oorspronkelijkeFout);
    }

    @Override
    public String get() {
        return null;
    }
}
