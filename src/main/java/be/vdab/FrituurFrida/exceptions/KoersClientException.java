package be.vdab.FrituurFrida.exceptions;

public class KoersClientException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public KoersClientException(String message) {
        super(message);
    }
    public KoersClientException(String message, Exception oorspronkelijkeFout) {
        super(message, oorspronkelijkeFout);
    }
}
