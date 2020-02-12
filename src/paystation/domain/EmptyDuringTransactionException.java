package paystation.domain;

public class EmptyDuringTransactionException extends Exception {
    public EmptyDuringTransactionException(String errorMessage) {
        super(errorMessage);
    }
}
